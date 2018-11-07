package com.team24.stp.shared;

public class AddressRefDataSet {

	private AddressRefData[] data;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private boolean state;

	public AddressRefDataSet() {
		data = new AddressRefData[0];

	}

	public AddressRefData[] getData() {
		return data;
	}

	public void setData(AddressRefData[] data) {
		this.data = data;
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

}
