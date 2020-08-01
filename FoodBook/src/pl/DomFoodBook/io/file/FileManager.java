package pl.DomFoodBook.io.file;

import pl.DomFoodBook.model.FoodBook;

public interface FileManager {
    FoodBook importData();
    void exportData(FoodBook foodBook);
}
