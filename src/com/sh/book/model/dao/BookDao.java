package com.sh.book.model.dao;

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

import com.sh.book.model.entity.Book;
import com.sh.book.model.entity.Del_book_log;
import com.sh.book.model.exception.BookException;
import com.sh.book.model.vo.BookGenre;

public class BookDao {
	
	Properties prop = new Properties();
	
	public BookDao() {
		try {
			prop.load(new FileReader("resources/book-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//무진 11-08
	public List<BookGenre> findAll(Connection conn) {
		List<BookGenre> books = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					books.add(handleBookGenreResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new BookException("도서정보 조회 오류입니다!! ", e);
		}
		return books;
	}
	
	// 무진 11-08 genretitle  추가
	private BookGenre handleBookGenreResultSet(ResultSet rset) throws SQLException {
		BookGenre book = new BookGenre();
		book.setBookNo(rset.getInt("book_no"));
		book.setBookName(rset.getString("book_name"));
		book.setAuthor(rset.getString("author"));
		book.setPublisher(rset.getString("publisher"));
		book.setBookGenre(rset.getString("book_genre"));
		book.setGenreTitle(rset.getString("genre_title"));
		book.setPbDate(rset.getDate("pb_date"));
		return book;
	}
	
	
    private Book handleBookResultSet(ResultSet rset) throws SQLException {
        Book book = new Book();
        book.setBookNo(rset.getInt("book_no"));
        book.setBookName(rset.getString("book_name"));
        book.setAuthor(rset.getString("author"));
        book.setPublisher(rset.getString("publisher"));
        book.setBookGenre(rset.getString("book_genre"));
        book.setPbDate(rset.getDate("pb_date"));
        return book;
    }
	
	public List<Book> findByNo(Connection conn, int bookNo) {
		List<Book> book = new ArrayList<>();
		String sql = prop.getProperty("findByNo");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookNo);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					book.add(handleBookResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new BookException("도서 번호 조회 오류입니다!! ", e);
		}
		return book;
	}

	public int insertBook(Connection conn, Book book) {
		int result = 0;
		String sql = prop.getProperty("insertBook");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getBookGenre());
			pstmt.setDate(5, book.getPbDate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BookException("도서정보 추가 오류입니다!! ", e);
		}
		return result;
	}

	public int deleteBook(Connection conn, int bookNo) {
		int result = 0;
		String sql = prop.getProperty("deleteBook");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bookNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BookException("도서정보 삭제 오류입니다!! ", e);
		}
		return result;
	}

	public int updateBookName(Connection conn, int bookNo, String newBookName) {
		int result = 0;
		String sql = prop.getProperty("updateBookName");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newBookName);
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("도서 제목 수정 오류입니다!! ", e);
		}
		return result;
	}

	public int updateAuthor(Connection conn, int bookNo, String newAuthor) {
		int result = 0;
		String sql = prop.getProperty("updateAuthor");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newAuthor);
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("저자 수정 오류입니다!! ", e);
		}
		return result;
	}

	public int updatePublisher(Connection conn, int bookNo, String newPublisher) {
		int result = 0;
		String sql = prop.getProperty("updatePublisher");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPublisher);
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("출판사 수정 오류입니다!! ", e);
		}
		return result;
	}

	public int updateBookGenre(Connection conn, int bookNo, String newBookGenre) {
		int result = 0;
		String sql = prop.getProperty("updateBookGenre");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newBookGenre);
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("책 장르 수정 오류입니다!! ", e);
		}
		return result;
	}

	public int updatePbDate(Connection conn, int bookNo, Date newPbDate) {
		int result = 0;
		String sql = prop.getProperty("updatePbDate");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, newPbDate);
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BookException("출고 날짜 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}
	//무진 11-08
	public List<BookGenre> findPbDate(Connection conn) {
		List<BookGenre> books = new ArrayList<>();
		String sql = prop.getProperty("findPbDate");		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					books.add(handleBookGenreResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new BookException("도서정보 조회 오류입니다!! ", e);
		}
		return books;
	
	}
	//무진 11-08
	public List<BookGenre> findCommend(Connection conn, String favoriteGenre) {
		
		List<BookGenre> books = new ArrayList<>();
		String sql = prop.getProperty("findCommend");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, favoriteGenre);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					books.add(handleBookGenreResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new BookException("도서정보 조회 오류입니다!! ", e);
		}
		return books;	
	}

	public List<Del_book_log> findFireAll(Connection conn) {
		List<Del_book_log> delBookLog = new ArrayList<>();
		String sql = prop.getProperty("findFireAll");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					delBookLog.add(handleDelBookResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new BookException("책 삭제 출력 오류" + e);
		}
		return delBookLog;
	}

	private Del_book_log handleDelBookResultSet (ResultSet rset) throws SQLException {
		Del_book_log book = new Del_book_log();
		book.setBookNo(rset.getInt("book_no"));
		book.setBookName(rset.getString("book_name"));
		book.setAuthor(rset.getString("author"));
		book.setPublisher(rset.getString("publisher"));
		book.setBookGenre(rset.getString("book_genre"));
		book.setPbDate(rset.getDate("pb_date"));
		book.setDelDate(rset.getTimestamp("del_date"));
		
		return book;
	}

	public  List<BookGenre> findByName(Connection conn, String bookName) {
		List<BookGenre> bookGenre = new ArrayList<>();
		String sql = prop.getProperty("findByName");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bookName);
		try(ResultSet rset = pstmt.executeQuery()){
			while(rset.next()) {
				bookGenre.add(handleBookGenreResultSet(rset));
			}
		}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookGenre;
	}

}