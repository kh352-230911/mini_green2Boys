package com.sh.users.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.sh.users.model.entity.Charge_cash_log;
import com.sh.users.model.entity.Del_users_log;
import com.sh.users.model.entity.Users;
import com.sh.users.model.vo.UsersGenre;
import com.sh.book.view.BookMenu;
import com.sh.food.view.FoodMenu;
import com.sh.users.controller.UsersController;

public class UsersMenu {
	private Scanner sc = new Scanner(System.in);
	private UsersController usersController = new UsersController();
	private BookMenu bookMenu = new BookMenu(); // 우진 BookMenu테이블에 접근 용이하도록 만들었음
	private FoodMenu foodMenu = new FoodMenu(); // 유신 FoodMenu테이블에 접근 용이하도록 만들었음
	
	public void mainMenu() {
		String menu = """
					===========================
					   북 카페 어플 시작 페이지
					===========================
					1. 로그인
					2. 회원 가입
					3. 프로그램 안내
					0. 종료
					===========================
					선택 : """;
		while(true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			
			Users user = null;
			int result = 0;
			String id = null;
			String pw = null;
			
			switch (choice) {
			case "1": 
				id = inputId();
				pw = inputPw();
				user = usersController.isUser(id, pw);
				isManager(user);
			
				break;
			case "2": 
				user = inputUser();
				result = usersController.insertUser(user);
				displayResult("회원가입", result);
				break;
			case "3": 
				programMenu();
				break;
			case "0": return;

			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}
	
	
	private void normalUserMenu(Users user) {
		String menu = """
				===========================
				  북 카페 회원메뉴 (일반 회원)
				===========================
				1. 충전하기
				2. 잔액, 포인트 조회
				3. 음식 주문하기
				4. 시간권 구매하기
				5. 도서현황 조회
				6. 회원 정보 변경
				0. 로그아웃
				===========================
				선택 : """;
		String id = user.getId(); // 유신 - 접속한 user의 id 정보가 사용될 경우를 대비해 만들어 두었음
		
		
		while(true) {
			System.out.println();
			System.out.print(menu);
			int price = 0; // 유신 - case3,4에서 사용하려고 선언
			int result = 0; // 유신 - dml실행 결과값을 받을 변수
			String choice = sc.next();
			
			switch(choice) {
			case "1" :
				insertCharge(user);
				break;
			case "2" :
				displayBalancePoint(usersController.findById(id)); // 유신 - id로 검색
				break;
			case "3" :
				price = foodMenu.mainMenuOrder(); // 유신 - 음식 메뉴를 출력하고 고른 음식의 총 금액을 리턴하는 메소드
				result = payFoodPrice(id, price);
				displayResult("결제", result);
				if(result == 0) {
					break;
				}
				displayBalancePoint(usersController.findById(id));
				break;
			case "4" :
				// 우진 
				
				price = purchaseTime();
				
				if (price == 0) {
					break;
				} else {
					result = payFoodPrice(id, price);
					if(result == 0) {
						break;
					}
					displayResult("결제", result);
					displayBalancePoint(usersController.findById(id));
					if(result == 1) {
						sortTime(price);
					}
					break;
				}
			case "5" :
				bookMenu.mainMenuOrder(user); // 무진 book에서 도서 현황 조회 메소드 호출
				break;
			case "6" :
				normalUpdate(user);
				break;
			case "0" :
				return;
			default : System.out.println("잘못 입력하셨습니다.");
				break;
			
				
			}
		}
		
	}
	
	// 우진 - 일반회원 회원 정보 변경!!
	private void normalUpdate(Users user) {
		String menu = """
				+++++++++++ 회원 정보 변경 +++++++++++
				1. 비밀번호 수정
				2. 이름 수정
				3. 생일 수정
				4. 핸드폰 번호 수정
				0. 이전페이지
				++++++++++++++++++++++++++++++++++
				선택 : """;
		while (true) {
			
			System.out.println();
			System.out.print(menu);
			
			String choice = sc.next();
			String pw = null;
			
			String id = user.getId();
			int result = 0;
			
			switch(choice) {
			case "1" :
				pw = inputPw();
				id = user.getId();
				System.out.print("> 변경할 비밀번호를 입력해주세요 : ");
				String newPw = sc.next();
				result = usersController.changePassword(id, pw, newPw);
				displayResult("패스워드 수정", result);
				break;
			case "2" : 
				pw = inputPw();
				id = user.getId();
				System.out.print("> 변경할 이름을 입력해주세요 : ");
				String newName = sc.next();
				result = usersController.changeName(id, pw,newName);
				displayResult("이름 수정", result);
				break;
			case "3" : 
				pw = inputPw();
				id = user.getId();
				System.out.print("> 변경할 생일을 입력해주세요(ex 1999-09-09) : ");
				String newBirthday = sc.next();
				result = usersController.changeBirthday(id,pw, newBirthday);
				displayResult("생일 수정", result);
				break;
			case "4" :
				pw = inputPw();
				id = user.getId();
				System.out.print("> 변경할 핸드폰 번호를 입력해주세요(ex 010-1234-5678) : ");
				String newPhone = sc.next();
				result = usersController.changePhone(id, pw, newPhone);
				displayResult("핸드폰 번호 수정", result);
				break;
			case "0" : return;
			default : System.out.println("잘못입력하셨습니다."); break;
			}
		}
		
	}


	// 우진 - 이용권 구매에 따른 시간 표시
	private void sortTime(int price) {
		int time = 0;
		switch(price) {
		case 2000 :
			time = 1;
			displayTimeRemaining(time);
			break;
		case 3000 :
			time = 2;
			displayTimeRemaining(time);
			break;
		case 5000 :
			time = 3;
			displayTimeRemaining(time);
			break;
		case 7000 :
			time = 5;
			displayTimeRemaining(time);
			break;
		case 12000 :
			time = 12;
			displayTimeRemaining(time);
		}
		
	}
	// 우진 - 시간 관련 메서드
	private void displayTimeRemaining(int time) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String today = null;
		cal.add(Calendar.HOUR, time);
		today = sdformat.format(cal.getTime());
		System.out.println("회원님의 이용 시간은 " + today + "까지 입니다.");
		System.out.println("즐거운 시간 되세요.");
	}


	/**
	 * 11-09 유신
	 * 유신 - 어플머니로 결제할지 포인트로 결제할지 입력 받고 선택 방법으로 결제하는 메소드
	 *  
	 */
	private int payFoodPrice(String id, int price) {
		String menu = """
				-------------결제 방법-------------
				1. 어플머니
				2. 포인트
				0. 이전 페이지로
				--------------------------------
				선택 : """;
		
		int result = 0;
		
		outer:
		while(true) {
			displayBalancePoint(usersController.findById(id));
			System.out.print(menu);
			String choice = sc.next();
			switch(choice) {
			case "1" : 
				if(usersController.findById(id).get(0).getBalance() < price) {
					System.out.println("어플머니가 부족합니다. 어플머니를 충전해주세요");
					break;
				}else {
					System.out.println("어플머니로 결제를 진행합니다.");
					result = usersController.payByBalance(id, price);
				}
				break outer;
			case "2" :
				if(usersController.findById(id).get(0).getPoint() < price) {
					System.out.println("포인트가 부족합니다.");
					break;
				}else {
					System.out.println("포인트로 결제를 진행합니다.");
					result = usersController.payByPoint(id, price);
				}
				break outer;
			case "0" :
				break outer;
			default :
				System.out.println("잘못 입력하셨습니다."); 
				break;

			}
		}
		return result;
	}

	/**
	 * 유신 - 잔액과 포인트를 보여주는 메소드 
	 */

	private void displayBalancePoint(List<Users> users) {
			System.out.println("---------------------------");
			System.out.println(users.get(0).getName() + "님의 잔액, 포인트 현황");
			System.out.println("---------------------------");
			System.out.println("현재 잔액 : " + users.get(0).getBalance());
			System.out.println("현재 포인트 : " + users.get(0).getPoint());
			System.out.println("---------------------------");
		}
	


	private void insertCharge(Users user) { // 우진 - 충전하기
		String menu = """
				-----------충전 금액----------
				1. 5000원
				2. 10000원
				3. 20000원
				4. 30000원
				5. 50000원
				0. 이전페이지
				---------------------------
				선택 : """;
		while (true) {
			System.out.println();
			System.out.print(menu);
			
			String choice = sc.next();
			
			int price = 0;
			String id = user.getId();
			String name = user.getName();
			int result = 0;
			
			switch(choice) {
			case "1" :
				price = 5000;
				result = usersController.updateChargeCash(id, name, price); 
				displayResult("충전 ",result);
				displayBalancePoint(usersController.findById(id));
				break;
			case "2" :
				price = 10000;
				result = usersController.updateChargeCash(id, name, price);
				displayResult("충전 ",result);
				displayBalancePoint(usersController.findById(id));
				break;
			case "3" :
				price = 20000;
				result = usersController.updateChargeCash(id, name, price);
				displayResult("충전 ",result);
				displayBalancePoint(usersController.findById(id));
				break;
			case "4" :
				price = 30000;
				result = usersController.updateChargeCash(id, name, price);
				displayResult("충전 ",result);
				displayBalancePoint(usersController.findById(id));
				break;
			case "5" :
				price = 50000;
				result = usersController.updateChargeCash(id, name, price);
				displayResult("충전 ",result);
				displayBalancePoint(usersController.findById(id));
				break;
			case "0" : return;
			default: System.out.println("잘못입력하셨습니다!! ");
			break;
			
			}
		}
		
	}


	private int purchaseTime() { // 우진
		String menu = """
				++++++++++++ 가격표 ++++++++++++
				1. 1시간 - 2000원
				2. 2시간 - 3500원
				3. 3시간 - 5000원
				4. 5시간 - 7000원
				5. 종일권 - 12000원
				0. 이전으로 돌아가기
				++++++++++++++++++++++++++++++
				선택 : """;
		
			System.out.println();
			
			System.out.print(menu);
			String choice = sc.next();
			Users user = null;
			int price = 0;
			int result = 0;
			
			while(true) {
				switch (choice) {
				case "1":
					price = 2000;
					break;
				case "2": 
					price = 3500; 
					break;
				case "3": 
					price = 5000;
					break;
				case "4": 
					price = 7000;
					break;
				case "5": 
					price = 12000;
					break;
				case "0": return 0;
				
				default: System.out.println("잘못입력하셨습니다!! "); break;
				}
				return price;
			}
	}


	private void displayChargeCashLog(Charge_cash_log users) {
		if(users == null) {
			System.out.println("> 조회된 회원이 없습니다.");
		}
		else {
			System.out.println("---------------------------------");
			System.out.printf("No : %c\n", users.getNo());
			System.out.printf("ID : %s\n", users.getId());
			System.out.printf("Name : %s\n", users.getName());
			System.out.printf("ChargeCash : %c\n", users.getChargeCash());
			System.out.printf("CreatedAt : %s\n", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(users.getChargeDate()));			
			System.out.println("---------------------------------");
		}
	}
		
	
	

	private void ManagerUserMenu() {
		String menu = """
				===========================
				  북 카페 회원메뉴 (관리자용)
				===========================
				1. 회원정보 관리
				2. 매출현황
				3. 도서관리
				4. 음식관리
				0. 로그아웃
				===========================
				선택 : """;

		while(true) {
			System.out.println();
			System.out.print(menu);

			String choice = sc.next();
			List<Users> users = null;
			Charge_cash_log chargeCash = null;
			List<Charge_cash_log> chargeCashLog = null;
			
			switch(choice) {
			case "1" :
				UsersManagerMenu();
				break;
			case "2" :
				chargeCashLog = usersController.salesStatus();
				displayChargeCashLog(chargeCashLog);
				break;
			case "3" :
				bookMenu.bookManagerMenu();
				break;
			case "4" :
				foodMenu.foodManagerMenu(); // 유신 푸드 패키지에서 음식관리 콘솔로 이동하는 메소드
				break;
			case "0" :
				return;
			default : System.out.println("잘못 입력하셨습니다.");
			break;
			}
		}
	}
	// (관리자용) 1. 회원정보 관리 메뉴
    private void UsersManagerMenu() {
        String menu = """
                +++++++++회원정보 관리+++++++++
                1. 회원정보 조회
                2. 회원아이디로 조회
                3. 회원정보 수정
                4. 회원정보 삭제
                5. 삭제된 회원 조회
                0. 이전 페이지
                ++++++++++++++++++++++++++++
                선택 : """;
        
        while(true) {
            System.out.println();
            System.out.print(menu);

            String choice = sc.next();
            List<Users> users = null;
            List<Del_users_log> delUsersLog = null;
            List<UsersGenre> usersGenre = null;
            String id = null;
            int result = 0;
            Users user = null;
            
            switch(choice) {
            case "1" :
                usersGenre = usersController.findAll();
                displayUsersGenre(usersGenre);
                break;
            case "2" :
                id = inputId();
                user = usersController.findId(id);
                displayUsers(user);
                break;
            case "3" :
                UpdateMenu();
                break;
            case "4" :
                id = inputId();
                result = usersController.deleteUser(id);
                displayResult("회원탈퇴", result);
                break;
            case "5" :
                delUsersLog = usersController.findFireAll();
                displayDelUsersLog(delUsersLog);
                break;
            case "0" :
                return;
            default : System.out.println("잘못 입력하셨습니다.");
            break;
            }
        }
    }
    
 // 무진
    private void displayUsers(Users user) {
        if(user == null) {
            System.out.println("> 조회된 회원이 없습니다.");
        }
        else {
            System.out.println("----------------------------");
            System.out.printf("ID     : %s\n", user.getId());
            System.out.printf("Password     : %s\n", user.getPassword());
            System.out.printf("Name     : %s\n", user.getName());
            System.out.printf("Gender : %s\n", user.getGender());
            System.out.printf("Birthday     : %s\n", user.getBirthday());
            System.out.printf("Phone     : %s\n", user.getPhone());
            System.out.printf("Email     : %s\n", user.getEmail());
            System.out.printf("CreatedAt: %s\n", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getCreatedAt()));
            System.out.printf("ChargeCash     : %s\n", user.getChargeCash());
            System.out.printf("Balance     : %s\n", user.getBalance());
            System.out.printf("Point     : %s\n", user.getPoint());
            System.out.printf("IsManager     : %s\n", user.getIsManager());
            System.out.printf("FavoriteGenre     : %s\n", user.getFavoriteGenre());
            System.out.println("----------------------------");
        }
        
    }
	
	// 우진 - (관리자용) 회원 정보수정
	
	private void UpdateMenu() {
		String menu = """
				+++++++++ 회원 정보수정 +++++++++
				1. 이름 수정
				2. 생일 수정
				3. 핸드폰 번호 수정
				4. 이메일 수정
				5. 좋아하는 장르 수정
				0. 메인메뉴로 돌아가기
				++++++++++++++++++++++++++++++
				선택 : """;
		
		String id = inputId();
		List<Users> users = null;
		
		while(true) {
			users = usersController.findById(id);
			displayUsers(users);
			if (users.isEmpty()) 
				return;
			System.out.print(menu);
			String choice = sc.next();
			int result = 0;
			
			switch(choice) {
			case "1": 
				System.out.print("변경할 이름 : ");
				String newName = sc.next();
				result = usersController.updateUsersName(id, newName);
				displayResult("이름 변경", result);
				break;
			case "2": 
				System.out.print("변경할 생일 (ex 1998-03-23) : ");
				Date newBirthday = Date.valueOf(sc.next());
				result = usersController.updateUsersBirthday(id, newBirthday);
				displayResult("생일 변경", result);
				break;
			case "3":
				System.out.print("변경할 번호 (ex 010-1234-5678) : ");
				String newPhone = sc.next();
				result = usersController.updateUsersPhone(id, newPhone);
				displayResult("번호 변경", result);
				break;
			case "4": 
				System.out.print("변경할 이메일 : ");
				String newEmail = sc.next();
				result = usersController.updateUsersEmail(id, newEmail);
				displayResult("이메일 변경", result);
				break;
			case "5":
				// 11-08 유신 수정
				System.out.println("변경하고 싶은 좋아하는 장르");
				result = usersController.updateUsersFavoriteGenre(id, inputGenre());
				System.out.println("좋아하는 장르 수정 완료!");
				break;
			case "0": return;
			default: System.out.println("> 잘못입력하셨습니다.");
			
			}
			
		}
	}

	private void isManager(Users user) {
		if(user == null) {
			System.out.println("ID가 존재하지 않거나 비밀번호가 틀렸습니다.");
			return;
		}else if(user.getIsManager().charAt(0) == 'Y') {
			System.out.println("---------------------------");
			System.out.println("로그인에 성공하셨습니다.");
			System.out.println("환영합니다. " + user.getName() + " 매니저님");
			System.out.println("---------------------------");
			ManagerUserMenu();
		}else if(user.getIsManager().charAt(0) == 'N') {
			System.out.println("---------------------------");
			System.out.println("로그인에 성공하셨습니다.");
			System.out.println("환영합니다. " + user.getName() + "님");
			System.out.println("---------------------------");
			normalUserMenu(user);
		}
	}

	private String inputId() {
		System.out.print("> 아이디 입력 : ");
		return sc.next();
	}
	private String inputPw() {
		System.out.print("> 비밀번호 입력: ");
		return sc.next();
	}


	private void displayResult(String type, int result) {
		if(result > 0)
            System.out.println("🎉🎉 " + type + " 성공! 🎉🎉");
        else
        	System.out.println("😭😭 " + type + " 실패! 😭😭");  
    }
		
	private Users inputUser() {
		Users user = new Users();
		System.out.println("> 회원정보를 입력하세요.");
        for (;;) {
            System.out.print("> 아이디 : ");
            sc.nextLine();
            user.setId(sc.nextLine());
            if (usersController.findById(user.getId()).isEmpty()) {
                System.out.println("> 사용가능한 아이디입니다 :)");
                break;
            } else {
                System.out.println("> 사용불가능한 아이디입니다. 다시 입력하세요.");
                continue;
            }
        }
        System.out.print("> 비밀번호 : ");
        user.setPassword(sc.nextLine());
        System.out.print("> 이름 : ");
		user.setName(sc.nextLine());
		System.out.print("> 성별(M/F) : ");
		user.setGender(sc.nextLine());
		System.out.print("> 생일(ex 1998-03-23) : ");
		user.setBirthday(Date.valueOf(sc.nextLine()));
		System.out.print("> 핸드폰 번호(ex 010-1234-5678) : ");
		user.setPhone(sc.nextLine());
		System.out.print("> 이메일 : ");
		user.setEmail(sc.nextLine());
		// 11-08 유신 수정
		System.out.println("좋아하는 장르의 번호를 입력해주세요");
		user.setFavoriteGenre(inputGenre());
		return user;
	}
	
	/**
	 * 11-08 유신 수정 
	 */
	private String inputGenre() {
		String choice = null;
		String genre = null;
		String menu = """
				------------- 장르 목록 -------------
				1. 액션
				2. 성장만화
				3. 수사, 범죄
				4. 코미디
				5. 스릴러
				6. SF, 판타지
				7. 로맨스
				8. 무협
				9. 역사
				10. 기타
				------------------------------------
				선택: """;
		outer:
		while(true) {
			System.out.printf(menu);
			choice = sc.next();		
			switch(choice){
			case"1" : genre = "G1"; break outer;
			case"2" : genre = "G2"; break outer;
			case"3" : genre = "G3"; break outer;
			case"4" : genre = "G4"; break outer;
			case"5" : genre = "G5"; break outer;
			case"6" : genre = "G6"; break outer;
			case"7" : genre = "G7"; break outer;
			case"8" : genre = "G8"; break outer;
			case"9" : genre = "G9"; break outer;
			case"10" : genre = "G10"; break outer;
			default : 
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요!");
				break;
			}
		}
		return genre;
	}
	
	/**
	 * 2023-11-08 - 우진
     * n건의 회원조회 결과를 출력
     * UsersGenre로 수정 조인시 보여지게 활용
     * @param UsersGenre
     */
    private void displayUsersGenre(List<UsersGenre> users) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s%-10s\n", 
                "ID","Password", "Name", "Gender", "Birthday", "Phone","Email", "CreatedAt","ChargeCash", "Balance","Point","IsManager","FavoriteGenre", "GenreTitle" );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if(users == null || users.isEmpty()) {
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            for(UsersGenre user : users) {
                System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s%-10s\n", 
                        user.getId(),
                        user.getPassword(),
                        user.getName(), 
                        user.getGender(), 
                        user.getBirthday(),
                        user.getPhone(),
                        user.getEmail(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getCreatedAt()),
                        user.getChargeCash(),
                        user.getBalance(),
                        user.getPoint(),
                        user.getIsManager(),
                        user.getFavoriteGenre(),
                        user.getGenreTitle());
             
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    private void displayUsers(List<Users> users) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s\n", 
                "ID","Password", "Name", "Gender", "Birthday", "Phone","Email", "CreatedAt","ChargeCash", "Balance","Point","IsManager","FavoriteGenre" );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if(users == null || users.isEmpty()) {
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            for(Users user : users) {
                System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s\n", 
                        user.getId(),
                        user.getPassword(),
                        user.getName(), 
                        user.getGender(), 
                        user.getBirthday(),
                        user.getPhone(),
                        user.getEmail(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getCreatedAt()),
                        user.getChargeCash(),
                        user.getBalance(),
                        user.getPoint(),
                        user.getIsManager(),
                        user.getFavoriteGenre());
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * del_users_log
     * n건의 회원조회 결과를 출력
     * @param Del_users_log
     */
    private void displayDelUsersLog(List<Del_users_log> DelUsersLog) {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s%-20s\n", 
                "ID","Password", "Name", "Gender", "Birthday", "Phone","Email", "CreatedAt","ChargeCash", "Balance","Point","IsManager","FavoriteGenre", "DelDate" );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if(DelUsersLog == null || DelUsersLog.isEmpty()) {
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            for(Del_users_log user : DelUsersLog) {
                System.out.printf("%-10s%-10s%-10s%-10s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%-10s%-10s%-20s\n", 
                        user.getId(),
                        user.getPassword(),
                        user.getName(), 
                        user.getGender(), 
                        user.getBirthday(),
                        user.getPhone(),
                        user.getEmail(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getCreatedAt()),
                        user.getChargeCash(),
                        user.getBalance(),
                        user.getPoint(),
                        user.getIsManager(),
                        user.getFavoriteGenre(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getDelDate()));
             
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    // 프로그램 안내
    private void programMenu() {
    	String menu = """
    			=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
    			              프로그램 안내
    			=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
    			안녕하세요 저희 만화 카페를 이용해주셔서 감사합니다.
    			저희 만화 카페는 회원 가입 후 선불 시스템으로 이용
    			가능합니다.
    			먼저 회원가입 후 금액을 충전 하시고 맛있는 먹거리와
    			재밌는 만화책을 즐겨주시면 감사하겠습니다.
    			이용해주셔서 감사합니다 항상 발전하는 만화 카페가 되
    			겠습니다.
    			=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
    			이전 페이지로 가는 버튼은 0번 입니다.
    			                        이전 페이지 : """;
    	while(true) {
    		
    		System.out.println();
    		System.out.print(menu);
    		String choice = sc.next();
    		
    		switch(choice) {
    		case "0": return;
    		}
    	}
    }
    
    private void displayChargeCashLog(List<Charge_cash_log> chargeCashLog) {
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-10s%-10s%-10s%-10s%-20s\n", 
        		"No", "ID", "Name",  "ChargeCash", "  ChargeDate");
        System.out.println("--------------------------------------------------------------------");
        if(chargeCashLog == null || chargeCashLog.isEmpty()) {
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            for(Charge_cash_log user : chargeCashLog) {
                System.out.printf("%-10s%-10s%-10s%-10s%-20s\n", 
                		user.getNo(),
                        user.getId(),
                        user.getName(), 
                        user.getChargeCash(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(user.getChargeDate()));
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }
    
    
    
}