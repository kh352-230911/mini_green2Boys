package com.sh.food.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;


import com.sh.food.model.entity.Food;
import com.sh.food.model.dao.FoodDao;

public class FoodService {
    private FoodDao foodDao = new FoodDao();
    
    
    public List<Food> findByCategory(String foodCategory) {
        Connection conn = getConnection();
        List<Food> foods = foodDao.findByCategory(conn, foodCategory);
        close(conn);
        return foods;
    }


    public int insertFood(Food food) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = foodDao.insertFood(conn, food);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }


    public int updateFoodPrice(int foodNo, int foodPrice) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = foodDao.updateFoodPrice(conn, foodNo, foodPrice);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }


    public int deleteFood(int foodNo) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = foodDao.deleteFood(conn, foodNo);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }
}