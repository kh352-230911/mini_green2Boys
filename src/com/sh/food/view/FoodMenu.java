package com.sh.food.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sh.book.model.entity.Book;
import com.sh.food.controller.FoodController;
import com.sh.food.model.entity.Food;



public class FoodMenu {
	private Scanner sc = new Scanner(System.in);
	private FoodController foodController = new FoodController();
	
	
	// 관리자용 콘솔에서 dml할때 사용하는 메소드
	
	public void foodManagerMenu() {
		
		String menu = """
				=+=+=+=+=+=+음식관리+=+=+=+=+=+=
				1. 음식정보 조회
				2. 음식정보 추가
				3. 음식가격 수정
				4. 음식정보 삭제
				0. 이전페이지 
				=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
				선택 : """;
		
		
		
		while(true) {
			System.out.println();
			System.out.print(menu);

			String choice = sc.next();
			
			Food food = null;
			int result = 0;
			int foodNo = 0;
			int foodPrice = 0;
			
			switch(choice) {
			case "1" : 
				mainMenuSearch();
				break;
			case "2" : 
				food = inputFood();
				result = foodController.insertFood(food);
				displayResult("음식 추가", result);
				break;
			case "3" :
				foodNo = inputFoodNo();
				foodPrice = inputFoodPrice();
				result = foodController.updateFoodPrice(foodNo, foodPrice);
				displayResult("음식 가격 수정", result);
				break;
			case "4" :
				foodNo = inputFoodNo();
				result = foodController.deletefood(foodNo);
				displayResult("음식 삭제", result);
				break;
			case "0" : return;
			default : System.out.println("> 잘못입력하셨습니다.");
			
			}
			
		}
				
	}

	
	/**
	 * 변경할 가격을 입력받는 메소드
	 * @return
	 */
	private int inputFoodPrice() {
		System.out.print("변경할 가격을 입력 해주세요");
		return sc.nextInt();
	}

	/**
	 * dml 처리 결과 리턴 메소드
	 *  
	 */
	private void displayResult(String type, int result) {
		if(result > 0)
            System.out.println("🎉🎉 " + type + " 성공! 🎉🎉");
        else
        	System.out.println("😭😭 " + type + " 실패! 😭😭");  
    }
	
	
	/**
	 * 음식의 고유번호를 입력받고 리턴하는 메소드
	 * @return
	 */
	private int inputFoodNo() {
		System.out.print("고유번호를 입력해주세요 : ");
		return sc.nextInt();
	}


	/**
	 * food 객체 정보를 입력받는 메소드
	 * @return
	 */
	private Food inputFood() {
		Food food = new Food();
		System.out.println("> 음식정보를 입력하세요.");
        System.out.print("> 고유번호 : ");
        food.setFoodNO(sc.nextInt());
        System.out.print("> 음식 이름 : ");
		food.setFoodName(sc.next());
		System.out.print("> 음식 가격 : ");
		food.setFoodPrice(sc.nextInt());
		System.out.print("> 음식 카테고리 : ");
		food.setFoodCategory(sc.next());
		return food;
	}
	
	public int mainMenuOrder() {
		String menu = """
				===========================
				  메뉴판 (카테고리)
				===========================
				1. 에스프레소
				2. 라떼
				3. 티/베버리지
				4. 에이드/블랜디드
				5. 라이스류
				6. 분식류
				7. 베이커리
				8. 사이드메뉴
				0. 결제하기
				===========================
				선택: """;
		
		// 11-08 유신 수정
		int stackedPrice = 0;
		
		outer:
		while(true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			String foodCategory = "";
			int totalPrice = 0;
			
			List <Food> foods = new ArrayList<>();
			switch(choice) {
			case "1":foodCategory = "에스프레소";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "2":foodCategory = "라떼";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "3":foodCategory = "티/배버리지";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "4":foodCategory = "에이드/블랜디드";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "5":foodCategory = "라이스류";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "6":foodCategory = "분식류";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "7":foodCategory = "베이커리";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "8":foodCategory = "사이드메뉴";
					 foods = foodController.findByCategory(foodCategory);
					 totalPrice = displayFoodsAndGetPrice(foods);
					 // 11-08 -유신
					 stackedPrice += totalPrice;
					 showPrice(totalPrice);
					 showStackedPrice(stackedPrice);
					 break;
			case "0":
				System.out.println("고르신 음식의 총 금액은: " + stackedPrice + "원 입니다.");
				break outer;
			default :
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
		return stackedPrice;
		
	}
	
	

	/**
	 * 메뉴를 선택할때 마다 현재까지 고른 모든 메뉴의 값들의 합 출력하는 메소드
	 */
	private void showStackedPrice(int stackcedPrice) {
		System.out.println("현재까지 주문하신 음식의 총 금액은 " + stackcedPrice + "원 입니다.");
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * 메뉴를 선택을 마칠 때마다 가격과 수량을 곱한 합계 금액을 출력하는 메소드
	 */
	private void showPrice(int price) {
		System.out.println("선택하신 메뉴의 금액은: " + price + "원 입니다.");
	}
	
	
	
	
	/**
	 * n건의 Food 조회하고 그 가격을 리턴하는 메소드 (일반사용자가 음식 주문시 사용)
	 * @param members
	 */
	private int displayFoodsAndGetPrice(List<Food> foods) {
		System.out.println("-----------------------------------------------------------------");
		System.out.printf("%-10s%-20s%-20s%-10s\n", 
				"메뉴번호", "카테고리", "음식명", "가격");
		System.out.println("-----------------------------------------------------------------");
		if(foods == null || foods.isEmpty()) {
			System.out.println("\t\t조회된 내용이 없습니다.");
		}
		else {
			int count = 0;
			for(Food food : foods) {
				System.out.printf("%-10s%-20s%-20s%-10s\n", 
						(count+1) + ".",
						food.getFoodCategory(), 
						food.getFoodName(), 
						food.getFoodPrice());
				count++;
			}
		}
		System.out.println("-----------------------------------------------------------------");
		System.out.print("주문하실 메뉴의 번호를 입력해 주세요. : ");
		
		// 11-08 유신수정
		int price = 0;
		int howMany = 0;
		int totalPrice = 0;
		try {
			price = foods.get(sc.nextInt()-1).getFoodPrice();
			System.out.print("몇개 주문하시겠습니까? : ");
			howMany = sc.nextInt();
			totalPrice = price * howMany;
		} catch (Exception e) {
			System.out.println("잘못 입력하셨습니다.");
		}
		return totalPrice;
	}
	
	public void mainMenuSearch() {
		String menu = """
				===========================
				  메뉴판 (카테고리)
				===========================
				1. 에스프레소
				2. 라떼
				3. 티/베버리지
				4. 에이드/블랜디드
				5. 라이스류
				6. 분식류
				7. 베이커리
				8. 사이드메뉴
				0. 뒤로가기
				===========================
				선택: """;
		
		
		outer:
		while(true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			String foodCategory = "";

			
			List <Food> foods = new ArrayList<>();
			switch(choice) {
			case "1":foodCategory = "에스프레소";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
					 break;
			case "2":foodCategory = "라떼";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "3":foodCategory = "티/배버리지";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "4":foodCategory = "에이드/블랜디드";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "5":foodCategory = "라이스류";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "6":foodCategory = "분식류";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "7":foodCategory = "베이커리";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
			  		 break;
			case "8":foodCategory = "사이드메뉴";
					 foods = foodController.findByCategory(foodCategory);
					 displayFoods(foods);
					 break;
			case "0":
				System.out.println("이전 화면으로 돌아갑니다. ");
				break outer;
			default :
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}
	
	
	
	/**
	 * n건의 Food 조회하는 메소드 (관리자 콘솔에서 음식 목록 조회시 사용할 메소드)
	 * @param members
	 */
	private void displayFoods(List<Food> foods) {
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.printf("%-10s%-20s%-20s%-20s%-10s\n", 
				"메뉴번호", "음식고유번호", "카테고리", "음식명", "가격");
		System.out.println("--------------------------------------------------------------------------------------------------");
		if(foods == null || foods.isEmpty()) {
			System.out.println("\t\t조회된 내용이 없습니다.");
		}
		else {
			int count = 0;
			for(Food food : foods) {
				System.out.printf("%-10s%-20s%-20s%-20s%-10s\n", 
						(count+1) + ".",
						food.getFoodNO(),
						food.getFoodCategory(), 
						food.getFoodName(), 
						food.getFoodPrice());
				count++;
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------");
	}

}