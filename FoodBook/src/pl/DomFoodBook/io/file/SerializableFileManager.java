package pl.DomFoodBook.io.file;

import pl.DomFoodBook.exceptions.DataExportException;
import pl.DomFoodBook.exceptions.DataImportException;
import pl.DomFoodBook.model.FoodBook;

import java.io.*;

class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "FoodBook.o";

    @Override
    public FoodBook importData() {

        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (FoodBook) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("No file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Error occurred during file import " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Invalid data type in file " + FILE_NAME);
        }

    }

    @Override
    public void exportData(FoodBook foodBook) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(foodBook);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No file " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Error occurred during file export " + FILE_NAME);
        }

    }
}
