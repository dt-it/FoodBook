package pl.DomFoodBook.app;


public class FoodBookApp {
    public final static String APP_NAME = "FoodBook v2.1";

    public static void main(String[] args) {

        System.out.println(APP_NAME);
        FoodBookControl foodBookControl = new FoodBookControl();
        foodBookControl.controlLoop();

    }
}
