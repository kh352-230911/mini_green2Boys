package com.sh.book.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Del_book_log extends Book {


        private Timestamp delDate;

        public Del_book_log() {
            super();
            // TODO Auto-generated constructor stub
        }

        public Del_book_log(int bookNo, String bookName, String author, String publisher, String bookGenre,
                Date pbDate) {
            super(bookNo, bookName, author, publisher, bookGenre, pbDate);
            // TODO Auto-generated constructor stub
        }

        public Del_book_log(Timestamp delDate) {
            super();
            this.delDate = delDate;
        }

        public Timestamp getDelDate() {
            return delDate;
        }

        public void setDelDate(Timestamp delDate) {
            this.delDate = delDate;
        }

        @Override
        public String toString() {
            return "Del_book_log [toString()=" + super.toString() + ", delDate=" + delDate + "]";
        }


}