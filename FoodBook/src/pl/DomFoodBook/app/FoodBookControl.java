package pl.DomFoodBook.app;

import pl.DomFoodBook.exceptions.*;
import pl.DomFoodBook.io.ConsolePrinter;
import pl.DomFoodBook.io.DataReader;
import pl.DomFoodBook.io.file.FileManager;
import pl.DomFoodBook.io.file.FileManagerBuilder;
import pl.DomFoodBook.model.*;

import java.util.Comparator;
import java.util.InputMismatchException;

public class FoodBookControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private FoodBook foodBook;

    public FoodBookControl() {
        fileManager = new FileManagerBuilder(dataReader, printer).build();
        try {
            foodBook = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę");
            foodBook = new FoodBook();
        }

    }

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_OFFLINE_RECIPE:
                    addOfflineRecipe();
                    break;
                case ADD_ONLINE_RECIPE:
                    addOnlineRecipe();
                    break;
                case PRINT_OFFLINE_RECIPES:
                    printOfflineRecipes();
                    break;
                case PRINT_ONLINE_RECIPES:
                    printOnlineRecipes();
                    break;
                case DELETE_OFFLINE_RECIPES:
                    deleteOfflineRecipes();
                    break;
                case DELETE_ONLINE_RECIPES:
                    deleteOnlineRecipes();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case PRINT_USERS:
                    printUsers();
                    break;
                case FIND_RECIPE_BY_TITLE:
                    findRecipeByTitle();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Selected option is invalid. Please try again.");

            }
        } while (option != Option.EXIT);
    }

    private void findRecipeByTitle() {
        printer.printLine("Please provide title of recipe");
        String title = dataReader.getString();
        String notFoundMessage = "Recipe with this title not found";
        foodBook.findRecipeByTitle(title)
                .map(Recipe::toString)
                .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
    }

    private void addUser() {
        UserFoodBook userFoodBook = dataReader.readAndCreateFoodBookUser();
        try {
            foodBook.addUser(userFoodBook);
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printUsers() {
        printer.printFoodBookUser(foodBook.getSortedUsers(
//                (u1, u2) -> u1.getLastName().compareToIgnoreCase(u2.getLastName())
                Comparator.comparing(User :: getLastName, String.CASE_INSENSITIVE_ORDER)
        ));

    }

    private Option getOption() {
        boolean noError = false;
        Option option = null;
        while (!noError) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                noError = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", try again.");
            } catch (InputMismatchException e) {
                printer.printLine("Provided input is not a number, try again.");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("Choose an option: ");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addOfflineRecipe() {
        try {
            OfflineRecipe offlineRecipe = dataReader.readAndCreateOfflineRecipe();
            foodBook.addRecipe(offlineRecipe);
        } catch (InputMismatchException e) {
            printer.printLine("Error, wrong data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Max capacity achieved ");
        }
    }

    private void deleteOfflineRecipes() {
        try {
            OfflineRecipe offlineRecipe = dataReader.readAndCreateOfflineRecipe();
            if (foodBook.removeRecipe(offlineRecipe)) {
                printer.printLine("Offline recipe has been removed");
            } else {
                printer.printLine("No such a offline recipe in FoodBook");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Incorrect data. Problem with deleting offline recipe occurred.");
        }

    }

    private void addOnlineRecipe() {
        try {
            OnlineRecipe onlineRecipe = dataReader.readAndCreateOnlineRecipe();
            foodBook.addRecipe(onlineRecipe);
        } catch (InputMismatchException e) {
            printer.printLine("Error, wrong data");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Max capacity achieved ");
        }

    }

    private void deleteOnlineRecipes() {
        try {
            OnlineRecipe onlineRecipe = dataReader.readAndCreateOnlineRecipe();
            if (foodBook.removeRecipe(onlineRecipe)) {
                printer.printLine("Online recipe has been removed");
            } else {
                printer.printLine("No such a online recipe in FoodBook");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Incorrect data. Problem with deleting online recipe occurred.");
        }
    }

    private void printOfflineRecipes() {
        printer.printOfflineRecipe(foodBook.getSortedRecipes(
//                (offline1, offline2) -> offline1.getMealType().compareToIgnoreCase(offline2.getMealType())
                Comparator.comparing(Recipe::getMealType, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void printOnlineRecipes() {
        printer.printOnlineRecipe(foodBook.getSortedRecipes(
//                (online1, online2) -> online1.getMealType().compareToIgnoreCase(online2.getMealType())
                Comparator.comparing(Recipe::getMealType, String.CASE_INSENSITIVE_ORDER)
        ));
    }

    private void exit() {
        try {
            fileManager.exportData(foodBook);
            printer.printLine("Eksport of the data to file succeded");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }

        printer.printLine("Closing the FoodBook ... See you!");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "Exit"),
        ADD_OFFLINE_RECIPE(1, "Add new offline recipe"),
        ADD_ONLINE_RECIPE(2, "Add new online recipe"),
        PRINT_OFFLINE_RECIPES(3, "Display available offline recipes"),
        PRINT_ONLINE_RECIPES(4, "Display available online recipes"),
        DELETE_OFFLINE_RECIPES(5, "Delete offline recipes"),
        DELETE_ONLINE_RECIPES(6, "Delete online recipes"),
        ADD_USER(7, "Add new user"),
        PRINT_USERS(8, "Display users"),
        FIND_RECIPE_BY_TITLE (9, "Find recipe by title");


        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No such optio as " + option);

            }

        }
    }

}
