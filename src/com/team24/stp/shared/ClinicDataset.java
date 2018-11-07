package com.team24.stp.shared;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Htet Wai Yan Lin
 *
 */
@XmlRootElement
public class ClinicDataset {

	private String searchText;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private ArrayList<ClinicData> data;

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

	public ArrayList<ClinicData> getData() {
		return data;
	}

	public void setData(ArrayList<ClinicData> data) {
		this.data = data;
	}

}
