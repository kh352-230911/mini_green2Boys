package com.sh.users.controller;

import java.sql.Date;
import java.util.List;

import com.sh.users.model.entity.Charge_cash_log;
import com.sh.users.model.entity.Del_users_log;
import com.sh.users.model.entity.Users;
import com.sh.users.model.service.UsersService;
import com.sh.users.model.vo.UsersGenre;

public class UsersController {
    private UsersService usersService = new UsersService();
    public List<Users> findById(String id) {
        List<Users> users = null;
        try {
            users = usersService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
        }
        return users;
    }
    
    
    public int insertUser(Users user) {
        int result = 0;
        try {
            result = usersService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 불편을 드려 죄송합니다" + e.getMessage());
        }
        return result;
    }
    
    /**
     * id 와 비밀번호가 둘다 맞아야 user를 반환하는 메소드
     * 
     * @param id
     * @param pw
     * @return
     */
	public Users isUser(String id, String pw) {
		Users user = null;
		try {
			user = usersService.isUser(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. " + e.getMessage());
		}
		return user;
	}
	// 2023-11-08 - 우진 UsersGenre로 수정
	public List<UsersGenre> findAll() {
		List<UsersGenre> users = null;
		try {
			users = usersService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. " + e.getMessage());
		}
		return users;
	}
	public int updateUsersName(String id, String newName) {
		int result = 0;
		try {
			result = usersService.updateUsersName(id, newName);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 이름 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	public int updateUsersBirthday(String id, Date newBirthday) {
		int result = 0;
		try {
			result = usersService.updateUsersBirthday(id, newBirthday);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 생일 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	public int updateUsersPhone(String id, String newPhone) {
		int result = 0;
		try {
			result = usersService.updateUsersPhone(id, newPhone);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 핸드폰 번호 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	public int updateUsersEmail(String id, String newEmail) {
		int result = 0;
		try {
			result = usersService.updateUsersEmail(id, newEmail);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 이메일 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	public int updateUsersFavoriteGenre(String id, String newFavoriteGenre) {
		int result = 0;
		try {
			result = usersService.updateUsersFavoriteGenre(id, newFavoriteGenre);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 좋아하는 장르 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	
	public int deleteUser(String id) {
		int result = 0;
		try {
			result = usersService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 삭제 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	
	public List<Del_users_log> findFireAll() {
		List<Del_users_log> delUsersLog = null;
		try {
			delUsersLog = usersService.findFireAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 회원 삭제 로그 출력 오류입니다!! " + e.getMessage());
		}
		return delUsersLog;
	}
	
	public int updateChargeCash(String id, String name, int price) {
		int result = 0;
		try {
			result = usersService.updateChargeCash(id, name, price);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 금액 충전 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	public List<Charge_cash_log> salesStatus() {
		List<Charge_cash_log> chargeCashLog = null;
		try {
			chargeCashLog = usersService.salesStatus();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 판매현황 오류입니다!! " + e.getMessage());
		}
		return chargeCashLog;
	}


	/**
	 * 유신 - 어플머니로 결제하는 메소드
	 * @return 
	 */
	public int payByBalance(String id, int price) {
		int result = 0;
		try {
			result = usersService.payByBalance(id, price);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 어플머니 결제 오류입니다! " + e.getMessage());
		}
		return result;
	}

	/**
	 * 유신 - 포인트로 결제하는 메소드
	 * @return 
	 */
	public int payByPoint(String id, int price) {
		int result = 0;
		try {
			result = usersService.payByPoint(id, price);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 어플머니 결제 오류입니다! " + e.getMessage());
		}
		return result;
	}


	public int changePassword(String id, String pw, String newPw) {
		int result = 0;
		try {
			result = usersService.changePassword(id, pw, newPw);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 비밀번호 변경 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	
	//무진
    public Users findId(String id) {
        Users user = null;
            try {
                user = usersService.findId(id);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(" > 불편을 드려 죄송합니다 : " + e.getMessage());
            }
        return user;
    }


	public int changeName(String id, String pw, String newName) {
		int result = 0;
		try {
			result = usersService.changeName(id, pw, newName);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 이름 변경 오류입니다!! " + e.getMessage());
		}
		return result;
	}


	public int changeBirthday(String id, String pw, String newBirthday) {
		int result = 0;
		try {
			result = usersService.changeBirthday(id, pw, newBirthday);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 생일 변경 오류입니다!! " + e.getMessage());
		}
		return result;
	}


	public int changePhone(String id, String pw, String newPhone) {
		int result = 0;
		try {
			result = usersService.changePhone(id, pw, newPhone);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 핸드폰 번호 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	
}