package pl.DomFoodBook.model;

import pl.DomFoodBook.exceptions.RecipeAlreadyExistsException;
import pl.DomFoodBook.exceptions.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class FoodBook implements Serializable {

    private Map<String, Recipe> recipes = new HashMap<>();
    private Map<String, UserFoodBook> users = new HashMap<>();

    public Map<String, Recipe> getRecipes() {
        return recipes;
    }

    public Collection<Recipe> getSortedRecipes (Comparator <Recipe> comparator) {
        List<Recipe> list = new ArrayList<>(recipes.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, UserFoodBook> getUsers() {
        return users;
    }

    public Collection<UserFoodBook> getSortedUsers (Comparator <UserFoodBook> comparator) {
        List<UserFoodBook> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public Optional<Recipe> findRecipeByTitle (String title) {
        return Optional.ofNullable(recipes.get(title));
    }

    public void addRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.getTitle())) {
            throw new RecipeAlreadyExistsException("Recipe already exists.");
        } else {
            recipes.put(recipe.getTitle(), recipe);
        }
    }

    public void addUser(UserFoodBook user) {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistsException("User already exists.");
        } else {
            users.put(user.getEmail(), user);
        }

    }

    public boolean removeRecipe(Recipe recipe) {
        if (recipes.containsValue(recipe)) {
            recipes.remove(recipe.getTitle());
            return true;
        } else {
            return false;
        }
    }


}