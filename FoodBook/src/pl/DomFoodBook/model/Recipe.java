package pl.DomFoodBook.model;

import java.io.Serializable;
import java.time.Year;

public abstract class Recipe implements Serializable, Comparable<Recipe>, CsvConvertible {
    private String title;
    private String author;
    private Year releaseYear;
    private String cuisine;
    private String specialDiet;
    private String mealType;
    private int prepTime;
    private String level;

    public Recipe(String title, String author, Year releaseYear, String cuisine, String specialDiet, String mealType, int prepTime, String level) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.cuisine = cuisine;
        this.specialDiet = specialDiet;
        this.mealType = mealType;
        this.prepTime = prepTime;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getSpecialDiet() {
        return specialDiet;
    }

    public void setSpecialDiet(String specialDiet) {
        this.specialDiet = specialDiet;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Recipe title: " + title + ", \nRecipe author: " + author + ", \nCusine: " + cuisine + ", \nSpecial diet: "
                + specialDiet + ", \nMeal: " + mealType + ", \nPreparation time: " + prepTime + "min, \nLevel: " + level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (prepTime != recipe.prepTime) return false;
        if (title != null ? !title.equals(recipe.title) : recipe.title != null) return false;
        if (author != null ? !author.equals(recipe.author) : recipe.author != null) return false;
        if (releaseYear != null ? !releaseYear.equals(recipe.releaseYear) : recipe.releaseYear != null) return false;
        if (cuisine != null ? !cuisine.equals(recipe.cuisine) : recipe.cuisine != null) return false;
        if (specialDiet != null ? !specialDiet.equals(recipe.specialDiet) : recipe.specialDiet != null) return false;
        if (mealType != null ? !mealType.equals(recipe.mealType) : recipe.mealType != null) return false;
        return level != null ? level.equals(recipe.level) : recipe.level == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (cuisine != null ? cuisine.hashCode() : 0);
        result = 31 * result + (specialDiet != null ? specialDiet.hashCode() : 0);
        result = 31 * result + (mealType != null ? mealType.hashCode() : 0);
        result = 31 * result + prepTime;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Recipe r) {
        return mealType.compareTo(r.mealType);
    }
}
