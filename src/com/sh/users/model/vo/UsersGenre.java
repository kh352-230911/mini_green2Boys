package com.sh.users.model.vo;

import java.sql.Date;

import com.sh.users.model.entity.Users;

public class UsersGenre extends Users{
	private String genreId;
	private String genreTitle;
	
	public UsersGenre() {
		super();
	}
	public UsersGenre(String id, String password, String name, String gender, Date birthday, String phone, String email,
			Date createdAt, int chargeCash, int balance, int point, String isManager, String favoriteGenre) {
		super(id, password, name, gender, birthday, phone, email, createdAt, chargeCash, balance, point, isManager,
				favoriteGenre);
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
		return "UsersGenre [toString()=" + super.toString() + ", genreId=" + genreId + ", genreTitle=" + genreTitle
				+ "]";
	}
	
	
}
