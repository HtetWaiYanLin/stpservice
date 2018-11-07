package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Htet Wai Yan Lin
 *
 */
@XmlRootElement
public class ContentDataSet {
	private int totalCount;
	private ContentData[] data;
	private boolean state;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public ContentData[] getData() {
		return data;
	}

	public void setData(ContentData[] data) {
		this.data = data;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
