package com.team24.stp.framework;

public class InternalUserData {
	private String userId;
	private String userName;
	private String password;
	private String organizationID;

	public InternalUserData() {
		clearProperties();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	protected void clearProperties() {
		this.userId = "";
		this.userName = "";
		this.password = "";
		this.organizationID = "";

	}

}
