package com.team24.stp.shared;

public class UserViewData extends UserData {

	private String username;
	private int serialno;

	public UserViewData() {
		clearProperties();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getSerialno() {
		return serialno;
	}

	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}

	public void clearProperties() {
		super.clearProperties();
		this.username = "";
		this.serialno = 0;
	}
}
