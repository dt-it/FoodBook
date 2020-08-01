package pl.DomFoodBook.io.file;

import pl.DomFoodBook.exceptions.NoSuchFileTypeException;
import pl.DomFoodBook.io.ConsolePrinter;
import pl.DomFoodBook.io.DataReader;

public class FileManagerBuilder {
   private DataReader reader;
   private ConsolePrinter printer;

    public FileManagerBuilder(DataReader reader, ConsolePrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public FileManager build () {
        printer.printLine("Choose data type: ");
        FileType fileType = getFileType();
        switch (fileType){
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Invalid data type in file");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Invalid data type in file, please choose data type again.");
            }

        } while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
