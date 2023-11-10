package com.sh.food.controller;

import java.util.List;
import com.sh.food.model.service.FoodService;

import com.sh.food.model.entity.Food;

public class FoodController {
    private FoodService foodService = new FoodService();

    public List<Food> findByCategory(String foodCategory) {
        List<Food> foods = null;
        try {
            foods = foodService.findByCategory(foodCategory);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 불편을 드려 죄송합니다 : " + e.getMessage());
        }
        return foods;
    }

    public int insertFood(Food food) {
        int result = 0;
        try {
            result = foodService.insertFood(food);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 관리자님 음식정보 추가 오류입니다!! " + e.getMessage());
        }
        return result;
    }

    public int updateFoodPrice(int foodNo, int foodPrice) {
        int result = 0;
        try {
            result = foodService.updateFoodPrice(foodNo, foodPrice);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 관리자님 음식가격 수정 오류입니다!! " + e.getMessage());
        }
        return result;
    }

    public int deletefood(int foodNo) {
        int result = 0;
        try {
            result = foodService.deleteFood(foodNo);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 관리자님 음식삭제 오류입니다!!" + e.getMessage());
        }
        return result;
    }



}