package com.sh.food.model.entity;

public class Food {
    private int foodNO;
    private String foodName;
    private int foodPrice;
    private String foodCategory;
    
    public Food() {
        super();
    }
    
    public Food(int foodNO, String foodName, int foodPrice, String foodCategory) {
        super();
        this.foodNO = foodNO;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodCategory = foodCategory;
    }
    
    public int getFoodNO() {
        return foodNO;
    }
    public void setFoodNO(int foodNO) {
        this.foodNO = foodNO;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public int getFoodPrice() {
        return foodPrice;
    }
    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
    public String getFoodCategory() {
        return foodCategory;
    }
    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }
    @Override
    public String toString() {
        return "Food [foodNO=" + foodNO + ", foodName=" + foodName + ", foodPrice=" + foodPrice + ", foodCategory="
                + foodCategory + "]";
    }


}