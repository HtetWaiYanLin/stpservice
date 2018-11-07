package com.team24.stp.framework;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MrBean {
	private InternalUserData user;
	private String message = "";
	private String logoText = "";
	private boolean status = false;

	public MrBean() {
		this.user = new InternalUserData();
		this.message = "";
		this.status = false;
	}

	public InternalUserData getUser() {
		return user;
	}

	public void setUser(InternalUserData user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String command) {
		this.message = command;
	}

	public String getLogoText() {
		return logoText;
	}

	public void setLogoText(String logoText) {
		this.logoText = logoText;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean p) {
		status = p;
	}
}
