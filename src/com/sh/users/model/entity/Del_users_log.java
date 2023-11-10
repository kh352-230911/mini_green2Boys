package com.sh.users.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Del_users_log extends Users{

	private Timestamp delDate;

	public Del_users_log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Del_users_log(String id, String password, String name, String gender, Date birthday, String phone,
			String email, Date createdAt, int chargeCash, int balance, int point, String isManager,
			String favoriteGenre) {
		super(id, password, name, gender, birthday, phone, email, createdAt, chargeCash, balance, point, isManager,
				favoriteGenre);
		// TODO Auto-generated constructor stub
	}

	public Timestamp getDelDate() {
		return delDate;
	}

	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}

	@Override
	public String toString() {
		return "Del_users_log [toString()=" + super.toString() + ", delDate=" + delDate + "]";
	}
		
	
	

}
