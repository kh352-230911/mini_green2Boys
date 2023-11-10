package com.sh.users.model.entity;

import java.sql.Date;

public class Users {
	private String id;
	private String password;
	private String name;
	private String gender;
	private Date birthday;
	private String phone;
	private String email;
	private Date createdAt;
	private int chargeCash;
	private int balance;
	private int point;
	private String isManager;
	private String FavoriteGenre;
	
	public Users() {
		super();
	}

	public Users(String id, String password, String name, String gender, Date birthday, String phone, String email,
			Date createdAt, int chargeCash, int balance, int point, String isManager, String favoriteGenre) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
		this.createdAt = createdAt;
		this.chargeCash = chargeCash;
		this.balance = balance;
		this.point = point;
		this.isManager = isManager;
		FavoriteGenre = favoriteGenre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getChargeCash() {
		return chargeCash;
	}

	public void setChargeCash(int chargeCash) {
		this.chargeCash = chargeCash;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String getFavoriteGenre() {
		return FavoriteGenre;
	}

	public void setFavoriteGenre(String favoriteGenre) {
		FavoriteGenre = favoriteGenre;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", birthday="
				+ birthday + ", phone=" + phone + ", email=" + email + ", createdAt=" + createdAt + ", chargeCash="
				+ chargeCash + ", balance=" + balance + ", point=" + point + ", isManager=" + isManager
				+ ", FavoriteGenre=" + FavoriteGenre + "]";
	}

	
	
	
}
