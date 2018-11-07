package com.team24.stp.shared;

public class MenuViewData extends MenuData {
	
	private String parentMenu;	
	
	public MenuViewData(){
		clearProperties();
	}

	public String getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	public void clearProperties() {
		super.clearProperties();
		this.parentMenu = "";
	}

}
