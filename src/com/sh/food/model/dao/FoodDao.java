package com.sh.food.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.book.model.exception.BookException;
import com.sh.food.model.entity.Food;
import com.sh.food.model.exception.FoodException;

public class FoodDao {
    private Properties prop = new Properties();
    
    public FoodDao() {
        try {
            prop.load(new FileReader("resources/food-query.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Food> findByCategory(Connection conn, String foodCategory) {
            String sql = prop.getProperty("findByCategory");
            List<Food> foods = new ArrayList<>();
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, foodCategory);
                try (ResultSet rset = pstmt.executeQuery()) {
                    while(rset.next()) {
                        foods.add(handlefoodResultSet(rset));
                    }
                }
            } catch (SQLException e) {
                throw new FoodException("음식 카테고리별 조회 오류!", e);
            }

            return foods;
        
    }
    
    public int insertFood(Connection conn, Food food) {
		int result = 0;
		String sql = prop.getProperty("insertFood");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, food.getFoodNO());
			pstmt.setString(2, food.getFoodName());
			pstmt.setInt(3, food.getFoodPrice());
			pstmt.setString(4, food.getFoodCategory());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BookException("음식정보 추가 오류입니다!! ", e);
		}
		return result;
    }
    
    
    public int updateFoodPrice(Connection conn, int foodNo, int foodPrice) {
		int result = 0;
		String sql = prop.getProperty("updateFoodPrice");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, foodPrice);
			pstmt.setInt(2, foodNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new FoodException("음식 가격 수정 오류입니다!! ", e);
		}
		return result;
    }
    
    public int deleteFood(Connection conn, int foodNo) {
		int result = 0;
		String sql = prop.getProperty("deleteFood");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, foodNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new FoodException("음식 삭제 오류입니다!! ", e);
		}
		return result;
    }
    
    
    
    private Food handlefoodResultSet(ResultSet rset) throws SQLException{
        int foodNo = rset.getInt("food_no");
        String foodName = rset.getString("food_name");
        int foodPrice = rset.getInt("food_price");
        String foodCategory = rset.getString("food_category");
        return new Food(foodNo, foodName, foodPrice, foodCategory);
    }
    
    
    
    
    

}