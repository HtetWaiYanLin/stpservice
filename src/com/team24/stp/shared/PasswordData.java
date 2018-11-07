package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PasswordData {

	private String userid;
	private String oldpass;
	private String newpass;
	private String confirm;
	
	public PasswordData() {
		super();
		clearProperties();
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOldpass() {
		return oldpass;
	}

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public void clearProperties() {
		this.userid = "";
		this.oldpass = "";
		this.newpass = "";
		this.confirm = "";
	}

}
