package com.sh.book.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.sh.book.controller.BookController;
import com.sh.book.model.entity.Book;
import com.sh.book.model.entity.Del_book_log;
import com.sh.book.model.vo.BookGenre;
import com.sh.users.model.entity.Users;
import com.sh.users.view.UsersMenu;

public class BookMenu {
	private BookController bookController = new BookController();
	private Scanner sc = new Scanner(System.in);
	
	public void bookManagerMenu() {
		String menu = """
				=+=+=+=+=+=+도서관리+=+=+=+=+=+=
				1. 도서정보 조회
				2. 도서명 조회
				3. 도서정보 추가
				4. 도서정보 수정
				5. 도서정보 삭제
				6. 삭제도서 조회
				0. 이전페이지 
				=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
				선택 : """;
		while(true) {
			System.out.println();
			System.out.print(menu);

			String choice = sc.next();
			List<BookGenre> bookGenre = null;
			List<Book> books = null;
			List<Del_book_log> delBookLog = null; 
			Book book = null;
			int result = 0;
			int bookNo = 0;
			String bookName = null;
			
			switch(choice) {
			case "1" : 
				bookGenre = bookController.findAll();
				displayBooks(bookGenre);
				break;
			case "2" :  // 무진 추가
				bookName = inputBookName();
				bookGenre = bookController.findByName(bookName);
				displayBooks(bookGenre);
				break;
			case "3" : 
				book = inputBook();
				result = bookController.insertBook(book);
				displayResult("도서 추가 ", result);
				break;
			case "4" :
				updateBookMenu();
				break;
			case "5" :
				bookNo = inputBookNo();
				result = bookController.deleteBook(bookNo);
				displayResult("도서 삭제", result);
				break;
			case "6" :  // 무진
				delBookLog = bookController.findFireAll();
				displayDelBookLog(delBookLog);
				break;
			case "0" : return;
			default : System.out.println("> 잘못입력하셨습니다.");
			
			}
			
		}
	}
	
			private void displayBook(Book book) {
				if(book == null) {
		            System.out.println("> 조회된 책이 없습니다.");
		        }
		        else {
		            System.out.println("----------------------------");
		            System.out.printf("BookNo     : %s\n", book.getBookNo());
		            System.out.printf("BookName     : %s\n", book.getBookName());
		            System.out.printf("Author     : %s\n", book.getAuthor());
		            System.out.printf("Publisher : %s\n", book.getPublisher());
		            System.out.printf("BookGenre     : %s\n", book.getBookGenre());
		            System.out.printf("pbDate     : %s\n", book.getPbDate());
		            System.out.println("----------------------------");
		        }
	}

			private String inputBookName() {
			System.out.print("도서 이름을 입력하세요 : ");
				return sc.next();
	}

			// 무진 
		   private void displayDelBookLog(List<Del_book_log> DelBookLog) {
		        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		        System.out.printf("%-10s%-10s%-10s%-15s%-15s%-30s%-25s\n",
		        		"BookNo","BookName", "Author", "Publisher", "BookGenre", "PbDate","DelDate");
		        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		        if(DelBookLog == null || DelBookLog.isEmpty()) {
		            System.out.println("\t\t 조회된 결과가 없습니다.");
		        }
		        else {
		            for(Del_book_log book : DelBookLog) {
		                System.out.printf("%-10s%-10s%-10s%-15s%-15s%-30s%-25s\n", 
		                		  book.getBookNo(),
		                          book.getBookName(),
		                          book.getAuthor(), 
		                          book.getPublisher(), 
		                          book.getBookGenre(),
		                          new SimpleDateFormat("yyyy-MM-dd HH:mm").format(book.getPbDate()),
		                          new SimpleDateFormat("yyyy-MM-dd HH:mm").format(book.getDelDate()));
		             
		            }
		        }
		        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		    }
		
		
		
	

	private void updateBookMenu() {
		String menu = """
				+++++++++ 도서 정보 수정 +++++++++
				1. 도서 제목 수정
				2. 저자 수정
				3. 출판사 수정
				4. 장르 수정
				5. 출고 날짜 수정
				0. 메인메뉴로 돌아가기
				++++++++++++++++++++++++++++++
				선택 : """;
		
		int bookNo = inputBookNo();
		List<Book> books = null;
		List<BookGenre> bookGenre = null;

		while(true) {
			books = bookController.findByNo(bookNo);
			bookGenre = bookController.findAll();
//			displayBooks(bookGenre);
			if (books.isEmpty()) {
				System.out.println("올바른 번호를 입력해주세요");
				return;
			}
			System.out.print(menu);
			String choice = sc.next();
			int result = 0;
			
			switch(choice) {
			case "1": 
				System.out.print("변경할 제목 : ");
				String newBookName = sc.next();
				result = bookController.updateBookName(bookNo, newBookName);
				System.out.println("도서제목 수정 완료!");
				break;
			case "2": 
				System.out.print("변경할 저자 이름 : ");
				String newAuthor = sc.next();
				result = bookController.updateAuthor(bookNo, newAuthor);
				System.out.println("저자 수정 완료!");
				break;
			case "3":
				System.out.print("변경할 출판사 : ");
				String newPublisher = sc.next();
				result = bookController.updatePublisher(bookNo, newPublisher);
				System.out.println("출판사 수정 완료!");
				break;
			case "4": 
				System.out.print("변경할 장르 : ");
				String newBookGenre = inputGenre();
				result = bookController.updateBookGenre(bookNo, newBookGenre);
				System.out.println("장르 수정 완료!");
				break;
			case "5":
				System.out.print("변경할 출고 날짜 : ");
				Date newPbDate = Date.valueOf(sc.next());
				result = bookController.updatePbDate(bookNo, newPbDate);
				System.out.println("출고 날짜 수정 완료!");
				break;
			case "0": return;
			default: System.out.println("> 잘못입력하셨습니다.");
			
			}
			
		}
		
	}

	private int inputBookNo() {
		System.out.print("> 책 번호 입력 : ");
		return sc.nextInt();
	}

	private Book inputBook() {
		Book book = new Book();
		System.out.println("> 도서정보를 입력하세요.");
        System.out.print("> 제목 : ");
        sc.nextLine();
        book.setBookName(sc.nextLine());
        System.out.print("> 저자 : ");
		book.setAuthor(sc.nextLine());
		System.out.print("> 출판사 : ");
		book.setPublisher(sc.nextLine());
		System.out.println("장르를 입력해주세요");
		book.setBookGenre(inputGenre());
		System.out.print("> 출판 날짜 (ex 2000-10-10) : ");
		book.setPbDate(Date.valueOf(sc.next()));
		return book;
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
     * n건의 도서 결과를 출력
     * 11-08 무진
     * List<book> -> List<BookGenre>  GenreTitle  추가
     * 
     */
    private void displayBooks(List<BookGenre> books) {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-25s%-15s%-10s%-20s%-20s\n", 
                "BookNo","BookName", "Author", "Publisher", "BookGenre", "GenreTitle" ,"PbDate");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        if(books == null || books.isEmpty()) {
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            for(BookGenre book : books) {
                System.out.printf("%-10s%-20s%-20s%-15s%-10s%-15s%-20s\n", 
                        book.getBookNo(),
                        book.getBookName(),
                        book.getAuthor(), 
                        book.getPublisher(), 
                        book.getBookGenre(),
                        book.getGenreTitle(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(book.getPbDate()));
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }
    
    private void displayResult(String type, int result) {
		if(result > 0)
            System.out.println("🎉🎉 " + type + " 성공! 🎉🎉");
        else
        	System.out.println("😭😭 " + type + " 실패! 😭😭");  
    }


    public void mainMenuOrder(Users user) {
    	String menu = """
    			===========================
    			도서 현황
    			===========================
    			1. 도서 조회
    			2. 도서명 조회
    			3. 신간 도서  
    			4. 추천 도서
    			0. 종료
    			===========================
    			선택  : """;
    	while(true) {
    		System.out.println();
    		System.out.print(menu);

			String choice = sc.next();
			List<Book> books = null;
			List<BookGenre> bookGenre = null;
			String bookName = null;
			Book book = null;
			int result = 0;
			int bookNo = 0;
    		String favoriteGenre = user.getFavoriteGenre();
    		switch (choice) {
			case "1":
				bookGenre = bookController.findAll();
				displayBooks(bookGenre);
				break;
			case "2": //무진 추가
				bookName = inputBookName();
				bookGenre = bookController.findByName(bookName);
				displayBooks(bookGenre);
				break;
			case "3":
				bookGenre = bookController.findPbDate();
				displayBooks(bookGenre);
				break;
			case "4":
				bookGenre = bookController.findCommend(favoriteGenre);
				displayBooks(bookGenre);
				break;
			case "0":return;
			default:
				System.out.println(" > 잘못 입력하셨습니다");
				break;
			}
    				
    	}

    }

   
	
}