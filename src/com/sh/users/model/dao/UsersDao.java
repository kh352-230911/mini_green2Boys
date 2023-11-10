package com.sh.users.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.users.model.entity.Charge_cash_log;
import com.sh.users.model.entity.Del_users_log;
import com.sh.users.model.entity.Users;
import com.sh.users.model.exception.UsersException;
import com.sh.users.model.vo.UsersGenre;

public class UsersDao {
	Properties prop = new Properties();
	
	public UsersDao() {
		try {
			prop.load(new FileReader("resources/users-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Users> findById(Connection conn, String id) {
		List<Users> users = new ArrayList<>();
		String sql = prop.getProperty("findById");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					users.add(handleUserResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new UsersException("아이디 조회 오류!", e);
		}
		return users;
	}
	// 2023-11-08 UsersGenre resultSet추가
	private UsersGenre handleUsersGenreResultSet(ResultSet rset) throws SQLException {
		UsersGenre user = new UsersGenre();
		user.setId(rset.getString("id"));
		user.setPassword(rset.getString("password"));
		user.setName(rset.getString("name"));
		user.setGender(rset.getString("gender"));
		user.setBirthday(rset.getDate("birthday"));
		user.setPhone(rset.getString("phone"));
		user.setEmail(rset.getString("email"));
		user.setCreatedAt(rset.getDate("created_at"));
		user.setChargeCash(rset.getInt("charge_cash"));
		user.setBalance(rset.getInt("balance"));
		user.setPoint(rset.getInt("point"));
		user.setIsManager(rset.getString("is_manager"));
		user.setFavoriteGenre(rset.getString("favorite_genre"));
		user.setGenreTitle(rset.getString("genre_title"));
		return user;
	}
	
	public int insertUser(Connection conn, Users user) {
		int result = 0;
		String sql = prop.getProperty("insertUser");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getGender());
			pstmt.setDate(5, user.getBirthday());
			pstmt.setString(6, user.getPhone());
			pstmt.setString(7, user.getEmail());
			pstmt.setString(8, user.getFavoriteGenre());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UsersException("회원가입 오류!", e);
		}
		return result;
	}
	
	public Users isUser(Connection conn, String id, String pw) {
		Users user = null;
		String sql = prop.getProperty("isUser");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next())
					user = handleUserResultSet(rset);
			}
		} catch (SQLException e) {
			throw new UsersException("로그인 오류!", e);
		}
				
				
		return user;
	}
	
	private Del_users_log handleDelUsersLogResultSet(ResultSet rset) throws SQLException{
		Del_users_log user = new Del_users_log();
		user.setId(rset.getString("id"));
		user.setPassword(rset.getString("password"));
		user.setName(rset.getString("name"));
		user.setGender(rset.getString("gender"));
		user.setBirthday(rset.getDate("birthday"));
		user.setPhone(rset.getString("phone"));
		user.setEmail(rset.getString("email"));
		user.setCreatedAt(rset.getDate("created_at"));
		user.setChargeCash(rset.getInt("charge_cash"));
		user.setBalance(rset.getInt("balance"));
		user.setPoint(rset.getInt("point"));
		user.setIsManager(rset.getString("is_manager"));
		user.setFavoriteGenre(rset.getString("favorite_genre"));
		user.setDelDate(rset.getTimestamp("del_date"));
		return user;
	}
	
	 private Users handleUserResultSet(ResultSet rset) throws SQLException {
	        Users user = new Users();
	        user.setId(rset.getString("id"));
	        user.setPassword(rset.getString("password"));
	        user.setName(rset.getString("name"));
	        user.setGender(rset.getString("gender"));
	        user.setBirthday(rset.getDate("birthday"));
	        user.setPhone(rset.getString("phone"));
	        user.setEmail(rset.getString("email"));
	        user.setCreatedAt(rset.getDate("created_at"));
	        user.setChargeCash(rset.getInt("charge_cash"));
	        user.setBalance(rset.getInt("balance"));
	        user.setPoint(rset.getInt("point"));
	        user.setIsManager(rset.getString("is_manager"));
	        user.setFavoriteGenre(rset.getString("favorite_genre"));
	        return user;
	    }
	// 2023-11-08 - 우진
	// (관리자용) 회원정보 관리 페이지 일단 users 테이블만 뜨는데 다른 테이블과 조인 해서 더 많은 정보를 띄울라면 수정 필요!!
	public List<UsersGenre> findAll(Connection conn) {
		List<UsersGenre> users = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					users.add(handleUsersGenreResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new UsersException("회원 정보 관리 오류!!", e);
		}
		return users;
	}
	public int updateUsersName(Connection conn, String id, String newName) {
		int result = 0;
		String sql = prop.getProperty("updateUsersName");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newName);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 이름 변경 오류!! ", e);
		}
		return result;
	}
	public int updateUsersBirthday(Connection conn, String id, Date newBirthday) {
		int result = 0;
		String sql = prop.getProperty("updateUsersBirthday");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, newBirthday);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 생일 변경 오류!! ", e);
		}
		return result;
	}
	public int updateUsersPhone(Connection conn, String id, String newPhone) {
		int result = 0;
		String sql = prop.getProperty("updateUsersPhone");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPhone);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 핸드폰 번호 변경 오류!! ", e);
		}
		return result;
	}
	public int updateUsersEmail(Connection conn, String id, String newEmail) {
		int result = 0;
		String sql = prop.getProperty("updateUsersEmail");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newEmail);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 이메일 변경 오류!! ", e);
		}
		return result;
	}
	public int updateUsersFavoriteGenre(Connection conn, String id, String newFavoriteGenre) {
		int result = 0;
		String sql = prop.getProperty("updateUsersFavoriteGenre");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newFavoriteGenre);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 좋아하는 장르 변경 오류!! ", e);
		}
		return result;
	}
	public int deleteUser(Connection conn, String id) {
		int result = 0;
		String sql = prop.getProperty("deleteUser");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 삭제 변경 오류!! ", e);
		}
		return result;
	}
	public List<Del_users_log> findFireAll(Connection conn) {
		List<Del_users_log> delUsersLog = new ArrayList<>();
		String sql = prop.getProperty("findFireAll");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					delUsersLog.add(handleDelUsersLogResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new UsersException("탈퇴 회원 출력 오류!! ", e);
		}
		return delUsersLog;
	}
	public int updateChargeCashToUsers(Connection conn, String id, int price) {
		int result = 0;
		String sql = prop.getProperty("updateChargeCashToUsers");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, price);
			pstmt.setInt(2, price);
			pstmt.setInt(3, price);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("금액 충전하기 오류!! ", e);
		}
		return result;
		
	}
	public int updateChargeCashToLog(Connection conn, String id, String name, int price) {
		int result = 0;
		String sql = prop.getProperty("updateChargeCashToLog");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("금액 로그 테이블에 삽입 오류!!! ", e);
		}
		return result;
	}
	public List<Charge_cash_log> salesStatus(Connection conn) {
		List<Charge_cash_log> chargeCashLog = new ArrayList<>();
		String sql = prop.getProperty("salesStatus");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					chargeCashLog.add(handleChargeCashLogResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new UsersException("매출현황 조회 오류!! ", e);
		}
		return chargeCashLog;
	}
	
	private Charge_cash_log handleChargeCashLogResultSet(ResultSet rset) throws SQLException{
		Charge_cash_log user = new Charge_cash_log();
		user.setNo(rset.getInt("No"));
		user.setId(rset.getString("id"));
		user.setName(rset.getString("name"));
		user.setChargeCash(rset.getInt("charge_cash"));
		user.setChargeDate(rset.getDate("charge_date"));
		return user;
	}
	
	/**
	 * 유신 - 어플머니로 결제하는 메소드
	 * @return 
	 */
	public int payByBalance(Connection conn, String id, int price) {
		int result = 0;
		String sql = prop.getProperty("payByBalance");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, price);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("어플머니 결제 오류!!", e);
		}
		return result;
	}
	
	/**
	 * 유신 - 포인트로 결제하는 메소드
	 * @return 
	 */
	public int payByPoint(Connection conn, String id, int price) {
		int result = 0;
		String sql = prop.getProperty("payByPoint");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, price);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("포인트 결제 오류!!", e);
		}
		return result;
	}
	public int changePassword(Connection conn, String id, String pw, String newPw) {
		int result = 0;
		String sql = prop.getProperty("changePassword");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("패스워드 결제 오류!! ", e);
		}
		return result;
	}
	
	//무진
    public Users findId(Connection conn, String id) {
        Users user = null;
        String sql = prop.getProperty("findId");
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, id);
            try(ResultSet rset = pstmt.executeQuery()){
                if(rset.next()) 
                    user = handleUserResultSet(rset);        
            }
        } catch (SQLException e) {
            throw new UsersException("회원 아이디 조회 오류!", e);
        }
        return user;
    }
	public int changeName(Connection conn, String id, String pw, String newName) {
		int result = 0;
		String sql = prop.getProperty("changeName");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newName);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 이름 변경 오류!!!", e);
		}
		return result;
	}
	public int changeBirthday(Connection conn, String id, String pw, String newBirthday) {
		int result = 0;
		String sql = prop.getProperty("changeBirthday");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newBirthday);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("회원 생일 변경 오류!! ", e);
		}
		return result;
	}
	public int changePhone(Connection conn, String id, String pw, String newPhone) {
		int result = 0;
		String sql = prop.getProperty("changePhone");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPhone);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new UsersException("핸드폰 번호 변경 오류!! ", e);
		}
		return result;
	}

}