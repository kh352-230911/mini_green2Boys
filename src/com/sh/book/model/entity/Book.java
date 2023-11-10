package com.sh.book.model.entity;

import java.sql.Date;

public class Book {
    private int bookNo;
    private String bookName;
    private String author;
    private String publisher;
    private String bookGenre;
    private Date pbDate;
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Book(int bookNo, String bookName, String author, String publisher, String bookGenre, Date pbDate) {
        super();
        this.bookNo = bookNo;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.bookGenre = bookGenre;
        this.pbDate = pbDate;
    }
    public int getBookNo() {
        return bookNo;
    }
    public void setBookNo(int bookNo) {
        this.bookNo = bookNo;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getBookGenre() {
        return bookGenre;
    }
    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }
    public Date getPbDate() {
        return pbDate;
    }
    public void setPbDate(Date pbDate) {
        this.pbDate = pbDate;
    }
}