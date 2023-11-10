package com.sh.users.model.entity;

import java.sql.Date;

public class Charge_cash_log {
	
	private int no;
	private String id;
	private String name;
	private int chargeCash;
	private Date chargeDate;
	
	public Charge_cash_log() {
		super();
	}

	public Charge_cash_log(int no, String id, String name, int chargeCash, Date chargeDate) {
		super();
		this.no = no;
		this.id = id;
		this.name = name;
		this.chargeCash = chargeCash;
		this.chargeDate = chargeDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChargeCash() {
		return chargeCash;
	}

	public void setChargeCash(int chargeCash) {
		this.chargeCash = chargeCash;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	@Override
	public String toString() {
		return "Charge_cash_log [no=" + no + ", id=" + id + ", name=" + name + ", chargeCash=" + chargeCash
				+ ", chargeDate=" + chargeDate + "]";
	}
	
	
}
