package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressDataList {
	private AddressData[] data;
	private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;

	public AddressDataList() {
		clearProperty();
	}

	private void clearProperty() {
		searchText = "";
		totalCount = 0;
		currentPage = 0;
		pageSize = 0;
		data = null;
	}

	public AddressData[] getData() {
		return data;
	}

	public void setData(AddressData[] data) {
		this.data = data;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

}
