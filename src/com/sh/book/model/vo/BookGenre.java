package com.sh.book.model.vo;

import java.sql.Date;

import com.sh.book.model.entity.Book;

public class BookGenre extends Book{

    
    private  String genreId;
    private  String genreTitle;
    public BookGenre() {
        super();
        // TODO Auto-generated constructor stub
    }
    public BookGenre(int bookNo, String bookName, String author, String publisher, String bookGenre, Date pbDate) {
        super(bookNo, bookName, author, publisher, bookGenre, pbDate);
        // TODO Auto-generated constructor stub
    }
    public BookGenre(String genreId, String genreTitle) {
        super();
        this.genreId = genreId;
        this.genreTitle = genreTitle;
    }
    public String getGenreId() {
        return genreId;
    }
    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }
    public String getGenreTitle() {
        return genreTitle;
    }
    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }
    @Override
    public String toString() {
        return "BookGenre [toString()=" + super.toString() + ", genreId=" + genreId + ", genreTitle=" + genreTitle
                + "]";
    }
    
    
}