package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuViewDataset {
	private MenuViewData[] data;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private boolean state;

	public MenuViewDataset() {
		super();
		data = new MenuViewData[0];

	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public MenuViewData[] getData() {
		return data;
	}

	public void setData(MenuViewData[] data) {
		this.data = data;
	}

}
