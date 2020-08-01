package pl.DomFoodBook.model;

import java.util.ArrayList;
import java.util.List;

public class UserFoodBook extends User {

    private List <Recipe> orderHistory = new ArrayList<>();
    private List <Recipe> currentOrders = new ArrayList <>();

    public UserFoodBook(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public List<Recipe> getOrderHistory() {
        return orderHistory;
    }

    public List<Recipe> getCurrentOrders() {
        return currentOrders;
    }

    @Override
    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getEmail();
    }

    private void addRecipeToOrderHistory (Recipe recipe){
        orderHistory.add(recipe);
    }

    private void addRecipeToCurrentOrders (Recipe recipe) {
        currentOrders.add(recipe);
    }

    public boolean cancelOrder (Recipe recipe) {
        boolean result = false;
        if (currentOrders.contains(recipe)) {
            currentOrders.remove(recipe);
            addRecipeToOrderHistory(recipe);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserFoodBook that = (UserFoodBook) o;

        if (orderHistory != null ? !orderHistory.equals(that.orderHistory) : that.orderHistory != null) return false;
        return currentOrders != null ? currentOrders.equals(that.currentOrders) : that.currentOrders == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderHistory != null ? orderHistory.hashCode() : 0);
        result = 31 * result + (currentOrders != null ? currentOrders.hashCode() : 0);
        return result;
    }
}
