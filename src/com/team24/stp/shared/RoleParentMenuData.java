package com.team24.stp.shared;

public class RoleParentMenuData {

	private long syskey;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private long n1;
	private long n2;
	private long n3;

	private RoleMenuData[] menu;

	public RoleParentMenuData() {
		clearProperties();
	}

	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public long getN1() {
		return n1;
	}

	public void setN1(long n1) {
		this.n1 = n1;
	}

	public long getN2() {
		return n2;
	}

	public void setN2(long n2) {
		this.n2 = n2;
	}

	public long getN3() {
		return n3;
	}

	public void setN3(long n3) {
		this.n3 = n3;
	}

	public RoleMenuData[] getMenu() {
		return menu;
	}

	public void setMenu(RoleMenuData[] menu) {
		this.menu = menu;
	}

	private void clearProperties() {
		this.syskey = 0;
		this.t1 = "";
		this.t2 = "";
		this.t3 = "";
		this.t4 = "";
		this.n1 = 0;
		this.n2 = 0;
		this.n3 = 0;
		this.menu = new RoleMenuData[0];
	}

}
