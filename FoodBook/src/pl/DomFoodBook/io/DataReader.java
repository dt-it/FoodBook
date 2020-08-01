package pl.DomFoodBook.io;

import pl.DomFoodBook.model.UserFoodBook;
import pl.DomFoodBook.model.OfflineRecipe;
import pl.DomFoodBook.model.OnlineRecipe;

import java.time.Year;
import java.util.Scanner;

public class DataReader {

    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public OfflineRecipe readAndCreateOfflineRecipe() {
        printer.printLine("Title: ");
        String title = scanner.nextLine();
        printer.printLine("Author: ");
        String author = scanner.nextLine();
        printer.printLine("Release Year: ");
        Year releaseYear = Year.of(scanner.nextInt());
        scanner.nextLine();
        printer.printLine("Cuisine: ");
        String cuisine = scanner.nextLine();
        printer.printLine("Special Diet: ");
        String specialDiet = scanner.nextLine();
        printer.printLine("Meal: ");
        String meal = scanner.nextLine();
        printer.printLine("Preparation Time: ");
        int prepTime = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Level: ");
        String level = scanner.nextLine();
        printer.printLine("Source name: ");
        String sourceName = scanner.nextLine();
        printer.printLine("Page number: ");
        int pageNumber = scanner.nextInt();

        return new OfflineRecipe(title, author, releaseYear, cuisine, specialDiet, meal, prepTime, level, sourceName, pageNumber);
    }

    public OnlineRecipe readAndCreateOnlineRecipe() {
        printer.printLine("Title: ");
        String title = scanner.nextLine();
        printer.printLine("Author: ");
        String author = scanner.nextLine();
        printer.printLine("Release Year: ");
        Year releaseYear = Year.of(scanner.nextInt());
        scanner.nextLine();
        printer.printLine("Cuisine: ");
        String cuisine = scanner.nextLine();
        printer.printLine("Special Diet: ");
        String specialDiet = scanner.nextLine();
        printer.printLine("Meal: ");
        String meal = scanner.nextLine();
        printer.printLine("Preparation Time: ");
        int prepTime = scanner.nextInt();
        scanner.nextLine();
        printer.printLine("Level: ");
        String level = scanner.nextLine();
        printer.printLine("Link: ");
        String link = scanner.nextLine();
        printer.printLine("Language: ");
        String language = scanner.nextLine();

        return new OnlineRecipe(title, author, releaseYear, cuisine, specialDiet, meal, prepTime, level, link, language);
    }

    public UserFoodBook readAndCreateFoodBookUser () {
        printer.printLine("Please provide first name: ");
        String firstName = scanner.nextLine();
        printer.printLine("Please provide last name: ");
        String lastName = scanner.nextLine();
        printer.printLine("Please provide email address: ");
        String email = scanner.nextLine();

        return new UserFoodBook(firstName, lastName, email);
    }


}
