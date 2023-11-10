package com.sh.book.controller;

import java.sql.Date;
import java.util.List;

import com.sh.book.model.entity.Book;
import com.sh.book.model.entity.Del_book_log;
import com.sh.book.model.service.BookService;
import com.sh.book.model.vo.BookGenre;

public class BookController {
	private BookService bookService = new BookService();
	//무진 11-08
	public List<BookGenre> findAll() {
		List<BookGenre> books = null;
		try {
			books = bookService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 도서정보 조회 출력 오류입니다!! " + e.getMessage());
		}
		return books;
	}

	public List<Book> findByNo(int bookNo) {
		List<Book> book = null;
		try {
			book = bookService.findByNo(bookNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 도서정보 조회 오류입니다!! " + e.getMessage());
		}
		return book;
	}

	public int insertBook(Book book) {
		int result = 0;
		try {
			result = bookService.insertBook(book);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 도서정보 추가 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int deleteBook(int bookNo) {
		int result = 0;
		try {
			result = bookService.deleteBook(bookNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 도서정보 삭제 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int updateBookName(int bookNo, String newBookName) {
		int result = 0;
		try {
			result = bookService.updateBookName(bookNo, newBookName);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 도서 제목 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int updateAuthor(int bookNo, String newAuthor) {
		int result = 0;
		try {
			result = bookService.updateAuthor(bookNo, newAuthor);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 저자 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int updatePublisher(int bookNo, String newPublisher) {
		int result = 0;
		try {
			result = bookService.updatePublisher(bookNo, newPublisher);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 출판사 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int updateBookGenre(int bookNo, String newBookGenre) {
		int result = 0;
		try {
			result = bookService.updateBookGenre(bookNo, newBookGenre);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 책 장르 수정 오류입니다!! " + e.getMessage());
		}
		return result;
	}

	public int updatePbDate(int bookNo, Date newPbDate) {
		int result = 0;
		try {
			result = bookService.updatePbDate(bookNo, newPbDate);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 책 출고 날짜 수정 오류입니다!!" + e.getMessage());
		}
		return result;
	}
	//무진 11-08
	public List<BookGenre> findPbDate() {
		List<BookGenre> books = null;
		try {
			books = bookService.findPbDate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 신규 도서 조회 오류입니다!!" + e.getMessage());
		}

		return books;
	}

	public List<BookGenre> findCommend(String favoriteGenre) {
		List<BookGenre> books = null;
		try {
			books = bookService.findCommend(favoriteGenre);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 회원님 추천 도서 조회 오류입니다 " + e.getMessage());
		}
		return books;
	}

	public List<Del_book_log> findFireAll() {
		List<Del_book_log> delBookLog = null;
		try {
			delBookLog = bookService.findFireAll();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 관리자님 책 삭제 로그 출력 오류입니다!!"+ e.getMessage());
		}
		return delBookLog;
	}

	public List<BookGenre> findByName(String bookName) {
		List<BookGenre> bookGenre =null;
		 try {
			bookGenre = bookService.findByName(bookName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("> 도서 조회 오류입니다! : "+ e.getMessage());
		}
		return bookGenre;
	}

	

	

}