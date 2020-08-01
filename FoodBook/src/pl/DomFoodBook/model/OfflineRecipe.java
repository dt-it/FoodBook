package pl.DomFoodBook.model;

import java.time.Year;

public class OfflineRecipe extends Recipe {
    public static final String TYPE = "Offline Recipe";
    private String sourceName;
    private int pageNumber;

    public OfflineRecipe(String title, String author, Year releaseYear, String cuisine, String specialDiet, String mealType, int prepTime, String level, String sourceName, int pageNumber) {
        super(title, author, releaseYear, cuisine, specialDiet, mealType, prepTime, level);
        this.sourceName = sourceName;
        this.pageNumber = pageNumber;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return super.toString() + ", \nSource Name: " + sourceName + ", \nPage number: " + pageNumber;
    }

    @Override
    public String toCsv() {
        return (TYPE + ";") + getTitle() + ";" + getAuthor() + ";" + getReleaseYear() + ";" + getCuisine() + ";"
                + getSpecialDiet() + ";" + getMealType() + ";" + getPrepTime() + ";" + getLevel() + ";"
                + sourceName + ";" + pageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OfflineRecipe that = (OfflineRecipe) o;

        if (pageNumber != that.pageNumber) return false;
        return sourceName != null ? sourceName.equals(that.sourceName) : that.sourceName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sourceName != null ? sourceName.hashCode() : 0);
        result = 31 * result + pageNumber;
        return result;
    }
}
