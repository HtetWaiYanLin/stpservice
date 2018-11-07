package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UploadDataSet {
	
	private UploadData[] data;
	private String[] uploads;
	private int totalCount;
	private int currentPage;
	private int pageSize;
	private boolean state;
	private String videoUpload[];
	
	public UploadDataSet(){
		data = new UploadData[0];
		uploads = new String[0];
		videoUpload = new String[0];
	}

	public UploadData[] getData() {
		return data;
	}

	public void setData(UploadData[] data) {
		this.data = data;
	}

	public String[] getUploads() {
		return uploads;
	}

	public void setUploads(String[] uploads) {
		this.uploads = uploads;
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
	public String[] getVideoUpload() {
		return videoUpload;
	}

	public void setVideoUpload(String[] videoUpload) {
		this.videoUpload = videoUpload;
	}
	
	
}
