package pl.DomFoodBook.io;

import pl.DomFoodBook.model.UserFoodBook;
import pl.DomFoodBook.model.OfflineRecipe;
import pl.DomFoodBook.model.OnlineRecipe;
import pl.DomFoodBook.model.Recipe;

import java.util.Collection;
import java.util.HashSet;

public class ConsolePrinter {

    public void printOfflineRecipe(Collection<Recipe> recipes) {
        long count = recipes.stream()
                .filter(r -> r instanceof OfflineRecipe)
                .map(Recipe::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("There are no offline recipes to display!");
        }
    }

    public void printOnlineRecipe(Collection<Recipe> recipes) {
        long count = recipes.stream()
                .filter(r -> r instanceof OnlineRecipe)
                .map(Recipe::toString)
                .peek(this::printLine)
                .count();
        if (count == 0) {
            printLine("There are no online recipes to display!");
        }
    }

    public void printFoodBookUser(Collection<UserFoodBook> users) {
        for (UserFoodBook user : users) {
            printLine(user);
        }
    }

    public <E> void printLine(E e) {
        System.out.println(e);
    }
}
