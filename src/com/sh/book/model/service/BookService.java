package com.sh.book.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.sh.book.model.dao.BookDao;
import com.sh.book.model.entity.Book;
import com.sh.book.model.entity.Del_book_log;
import com.sh.book.model.vo.BookGenre;

public class BookService {

	private BookDao bookDao = new BookDao();

	//무진 11-08
	public List<BookGenre> findAll() {
		Connection conn = getConnection();
		List<BookGenre> books = bookDao.findAll(conn);
		close(conn);
		return books;
	}

	public List<Book> findByNo(int bookNo) {
		Connection conn = getConnection();
		List<Book> book = bookDao.findByNo(conn, bookNo);
		close(conn);
		return book;
	}

	public int insertBook(Book book) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.insertBook(conn, book);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteBook(int bookNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.deleteBook(conn, bookNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateBookName(int bookNo, String newBookName) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.updateBookName(conn, bookNo, newBookName);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateAuthor(int bookNo, String newAuthor) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.updateAuthor(conn, bookNo, newAuthor);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePublisher(int bookNo, String newPublisher) {
		int result =0;
		Connection conn = getConnection();
		try {
			result = bookDao.updatePublisher(conn, bookNo, newPublisher);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateBookGenre(int bookNo, String newBookGenre) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.updateBookGenre(conn, bookNo, newBookGenre);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updatePbDate(int bookNo, Date newPbDate) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bookDao.updatePbDate(conn, bookNo, newPbDate);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	//무진 11-08
	public List<BookGenre> findPbDate() {
		Connection conn = getConnection();
		List<BookGenre> books;
		try {
			books = bookDao.findPbDate(conn);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally{
				close(conn);
				}
		return books;		
		}
	
	
	//무진 11-08
	public List<BookGenre> findCommend(String favoriteGenre) {
		Connection conn = getConnection();
		List<BookGenre> books;
		books = bookDao.findCommend(conn, favoriteGenre);
		close(conn);
		return books;	
	}

	public List<Del_book_log> findFireAll(){
		Connection conn =getConnection();
		List<Del_book_log> delBookLog = bookDao.findFireAll(conn);
		close(conn);
		return delBookLog;
		
	}

	

	public List<BookGenre> findByName(String bookName) {
		Connection conn = getConnection();
		List<BookGenre> bookGenre = bookDao.findByName(conn , bookName);
		close(conn);
		return bookGenre;
	}
	
	
}