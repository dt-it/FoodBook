package pl.DomFoodBook.io.file;

import pl.DomFoodBook.exceptions.DataExportException;
import pl.DomFoodBook.exceptions.DataImportException;
import pl.DomFoodBook.exceptions.InvalidDataException;
import pl.DomFoodBook.model.*;

import java.io.*;
import java.time.Year;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

class CsvFileManager implements FileManager {
    private static final String RECIPES_FILE_NAME = "FoodBook.csv";
    private static final String USERS_FILE_NAME = "Users.csv";


    @Override
    public FoodBook importData() {
        FoodBook foodBook = new FoodBook();
        importRecipes(foodBook);
        importUsers(foodBook);

        return foodBook;
    }

    private void importUsers(FoodBook foodBook) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE_NAME));
        ) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(foodBook::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("File not found " + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("File reading error " + USERS_FILE_NAME);
        }
    }

    private void importRecipes(FoodBook foodBook) {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(RECIPES_FILE_NAME))
        ) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(foodBook::addRecipe);

        } catch (FileNotFoundException e) {
            throw new DataImportException("File not found " + RECIPES_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("File reading error " + RECIPES_FILE_NAME);
        }
    }

    private Recipe createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (OnlineRecipe.TYPE.equals(type)) {
            return createOnlineRecipe(split);
        } else if (OfflineRecipe.TYPE.equals(type)) {
            return createOfflineRecipe(split);
        }
        throw new InvalidDataException("Unknown type of recipe " + type);
    }

    private UserFoodBook createUserFromString(String line) {
        String[] split = line.split(";");
        return createUser(split);

    }

    private UserFoodBook createUser(String[] data) {
        String firstName = data[0];
        String lastName = data[1];
        String email = data[2];
        return new UserFoodBook(firstName, lastName, email);
    }

    private OnlineRecipe createOnlineRecipe(String[] data) {
        String title = data[1];
        String author = data[2];
        Year releaseYear = Year.of(Integer.parseInt(data[3]));
        String cuisine = data[4];
        String specialDiet = data[5];
        String meal = data[6];
        int prepTime = Integer.parseInt(data[7]);
        String level = data[8];
        String link = data[9];
        String language = data[10];
        return new OnlineRecipe(title, author, releaseYear, cuisine, specialDiet, meal, prepTime, level, link, language);
    }

    private OfflineRecipe createOfflineRecipe(String[] data) {
        String title = data[1];
        String author = data[2];
        Year releaseYear = Year.of(Integer.parseInt(data[3]));
        String cuisine = data[4];
        String specialDiet = data[5];
        String meal = data[6];
        int prepTime = Integer.parseInt(data[7]);
        String level = data[8];
        String sourceName = data[9];
        int pageNumber = Integer.parseInt(data[10]);
        return new OfflineRecipe(title, author, releaseYear, cuisine, specialDiet, meal, prepTime, level, sourceName, pageNumber);
    }

    @Override
    public void exportData(FoodBook foodBook) {
        exportRecipes(foodBook);
        exportUsers(foodBook);
    }

    private void exportUsers(FoodBook foodBook) {
        Collection<UserFoodBook> users = foodBook.getUsers().values();
        exportToCsv(users, USERS_FILE_NAME);
    }

    private void exportRecipes(FoodBook foodBook) {
        Collection<Recipe> recipes = foodBook.getRecipes().values();
        exportToCsv(recipes, RECIPES_FILE_NAME);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (
                FileWriter fileWriter = new FileWriter(fileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            for (T t : collection) {
                bufferedWriter.write(t.toCsv());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new DataExportException("Error occurred during users export " + fileName);
        }
    }


}
