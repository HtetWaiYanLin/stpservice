package com.team24.stp.framework;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	private String userid;
	private String password;
	private String passcode;
	private String username;
	private String domain;
	private String logotext;
	private String logolink;
	private long role;
	private boolean commandcenter;
	private Menu[] menus; 
	private Menu[] rightmenus; 
	private long syskey; 
	private String t1;  
	private String t2;  
	private String t3;  
	private String t4;  
	private String t5;  
	private String t6;  
	private String userPhoto;
	
	private double n1; 
	private double n2; 
	private double n3;
	private long status;
	private int loginStatus;
	private long usersyskey;
	
	public Profile(){
		username="";
		logotext="B2B";
		logolink="/dashboard";
		role=99;
		commandcenter=false;
		//standard menu
		menus = new Menu[0];		
		rightmenus = new Menu[0];	
	}
	
	public String getUserID() { return userid; } 
	public void setUserID(String p) {this.userid = p;}
	public String getPassword() { return password; } 
	public void setPassword(String p) {this.password = p;}
	public String getPasscode() { return passcode; } 
	public void setPasscode(String p) {this.passcode = p;}
	public String getUserName() { return username; } 
	public void setUserName(String p) {this.username = p;}
	public String getDomain() { return domain; } 
	public long getRole() { return role; } 
	public void setRole(long p) {this.role = p;}
	public void setDomain(String p) {this.domain = p;}
	public String getLogoText() { return logotext; } 
	public void setLogoText(String p) {this.logotext = p;}	
	public String getLogoLink() { return logolink; } 
	public void setLogoLink(String p) {this.logolink = p;}
	public Menu[] getMenus() { return menus; } 
	public void setMenus(Menu[] p) {this.menus = p;}
	public Menu[] getRightMenus() { return rightmenus; } 
	public void setRightMenus(Menu[] p) {this.rightmenus = p;}
	public boolean getCommandCenter() { return commandcenter; } 
	public void setCommandCenter(boolean p) {this.commandcenter = p;}
	public long getSysKey() { return syskey; } 
	public void setSysKey(long p) {this.syskey= p;}
	public String getT1() { return t1; } 
	public void setT1(String p) {this.t1 = p;}
	public String getT2() { return t2; } 
	public void setT2(String p) {this.t2 = p;}
	public String getT3() { return t3; } 
	public void setT3(String p) {this.t3 = p;}
	public String getT4() { return t4; } 
	public void setT4(String p) {this.t4 = p;}
	public String getT5() { return t5; } 
	public void setT5(String p) {this.t5 = p;}
	public String getT6() { return t6; } 
	public void setT6(String p) {this.t6 = p;}
	public double getN1() { return n1; } 
	public void setN1(double p) {this.n1 = p;}
	public double getN2() { return n2; } 
	public void setN2(double p) {this.n2 = p;}
	public double getN3() { return n3; } 
	public void setN3(double p) {this.n3 = p;}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public long getUsersyskey() {
		return usersyskey;
	}

	public void setUsersyskey(long usersyskey) {
		this.usersyskey = usersyskey;
	}
	
	
	
}
