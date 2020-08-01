package pl.DomFoodBook.model;

import java.time.Year;

public class OnlineRecipe extends Recipe {
    public static final String TYPE = "Online Recipe";
    private String link;
    private String language;

    public OnlineRecipe(String title, String author, Year releaseYear, String cuisine, String specialDiet, String mealType, int prepTime, String level, String link, String language) {
        super(title, author, releaseYear, cuisine, specialDiet, mealType, prepTime, level);
        this.link = link;
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return super.toString() + ", \nLink: " + link + ", \nLanguage: " + language;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") + getTitle() + ";" + getAuthor() + ";" + getReleaseYear() + ";" + getCuisine() + ";"
                + getSpecialDiet() + ";" + getMealType() + ";" + getPrepTime() + ";" + getLevel() + ";"
                + language + ";" + link;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OnlineRecipe that = (OnlineRecipe) o;

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return language != null ? language.equals(that.language) : that.language == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}
