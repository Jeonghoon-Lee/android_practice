package ca.jeonghoon.day7component.spinner.Model;

public class MealRating implements Comparable<MealRating> {
    String mealName;
    double rating;

    public MealRating(String mealName, double rating) {
        this.mealName = mealName;
        this.rating = rating;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int compareTo(MealRating o) {
        return (int) (o.getRating() - rating);
    }

    @Override
    public String toString() {
        return String.format("{name='%s', rating=%.2f}\n", mealName, rating);
    }
}
