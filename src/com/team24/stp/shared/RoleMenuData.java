package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleMenuData {
	private long syskey;
	private String t2;
	private String t3;
	private boolean result;
	private boolean show;
	private long n2;
	private RoleMenuData[] childmenus;

	public RoleMenuData() {
		clearProperties();
	}

	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public long getN2() {
		return n2;
	}

	public void setN2(long n2) {
		this.n2 = n2;
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

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public RoleMenuData[] getChildmenus() {
		return childmenus;
	}

	public void setChildmenus(RoleMenuData[] childmenus) {
		this.childmenus = childmenus;
	}

	protected void clearProperties() {
		this.syskey = 0;
		this.t2 = "";
		this.t3 = "";
		this.result = false;
		this.show = false;
		this.childmenus = new RoleMenuData[0];
	}

}
