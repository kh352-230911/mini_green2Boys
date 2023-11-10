package com.sh.users.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.sh.users.model.dao.UsersDao;
import com.sh.users.model.entity.Charge_cash_log;
import com.sh.users.model.entity.Del_users_log;
import com.sh.users.model.entity.Users;
import com.sh.users.model.vo.UsersGenre;

public class UsersService {
    private UsersDao usersDao = new UsersDao();
    
    public List<Users> findById(String id) {
        Connection conn = getConnection();
        List<Users> users = usersDao.findById(conn, id);
        close(conn);
        return users;
    }
    
    public int insertUser(Users user) {
        int result = 0;
        Connection conn = getConnection();
        
        try {
            result = usersDao.insertUser(conn, user);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        
        return result;
    }
    
	public Users isUser(String id, String pw) {
		Connection conn = getConnection();
		Users user = usersDao.isUser(conn, id, pw);
		close(conn);
		return user;
	}
	// 2023-11-08 우진 UsersGenre로 수정
	public List<UsersGenre> findAll() {
		Connection conn = getConnection();
		List<UsersGenre> users = usersDao.findAll(conn);
		close(conn);
		return users;
	}

	public int updateUsersName(String id, String newName) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateUsersName(conn, id, newName);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateUsersBirthday(String id, Date newBirthday) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateUsersBirthday(conn, id, newBirthday);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateUsersPhone(String id, String newPhone) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateUsersPhone(conn, id, newPhone);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateUsersEmail(String id, String newEmail) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateUsersEmail(conn, id, newEmail);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateUsersFavoriteGenre(String id, String newFavoriteGenre) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateUsersFavoriteGenre(conn, id, newFavoriteGenre);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteUser(String id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.deleteUser(conn, id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Del_users_log> findFireAll() {
		Connection conn = getConnection();
		List<Del_users_log> delUsersLog = usersDao.findFireAll(conn);
		close(conn);
		return delUsersLog;
	}

	public int updateChargeCash(String id, String name, int price) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.updateChargeCashToUsers(conn, id, price);
			
			result = usersDao.updateChargeCashToLog(conn, id, name, price);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<Charge_cash_log> salesStatus() {
		List<Charge_cash_log> chargeCashLog = null;
		Connection conn = getConnection();
		chargeCashLog = usersDao.salesStatus(conn);
		close(conn);
		return chargeCashLog;
	}

	/**
	 * 유신 - 어플머니로 결제하는 메소드
	 * @return 
	 */
	
	public int payByBalance(String id, int price) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.payByBalance(conn, id, price);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/**
	 * 유신 - 포인트로 결제하는 메소드
	 * @return 
	 */
	
	public int payByPoint(String id, int price) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.payByPoint(conn, id, price);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int changePassword(String id, String pw, String newPw) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.changePassword(conn, id, pw, newPw);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	//무진
    public Users findId(String id) {
        Connection conn = getConnection();
        Users user = usersDao.findId(conn, id);
        close(conn);
        return user;
    }

	public int changeName(String id, String pw, String newName) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.changeName(conn, id, pw, newName);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int changeBirthday(String id, String pw, String newBirthday) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.changeBirthday(conn, id, pw, newBirthday);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int changePhone(String id, String pw, String newPhone) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = usersDao.changePhone(conn, id, pw, newPhone);
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