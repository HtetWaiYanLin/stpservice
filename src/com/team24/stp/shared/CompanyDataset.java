package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Htet Wai Yan Lin
 *
 */
@XmlRootElement
public class CompanyDataset {

	private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private CompanyData[] data;
	private boolean state;

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

	public CompanyData[] getData() {
		return data;
	}

	public void setData(CompanyData[] data) {
		this.data = data;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
