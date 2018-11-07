package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRoleViewData{
	private long syskey;
	private String t2;
	private boolean flag;
	private long []rolesyskey;
	

	public UserRoleViewData() {
		clearProperties();
	}

	public long getSyskey() {
		return syskey;
	}


	public long[] getRolesyskey() {
		return rolesyskey;
	}

	public void setRolesyskey(long[] rolesyskey) {
		this.rolesyskey = rolesyskey;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	private void clearProperties() {
		this.syskey = 0;		
		this.t2 = "";
		this.flag=false;
		
		

	}
}
