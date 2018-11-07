package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatusDataSet {
	private StatusData[] data;
	
	public StatusDataSet(){
		data = new StatusData[0];
	}

	public StatusData[] getData() {
		return data;
	}

	public void setData(StatusData[] data) {
		this.data = data;
	}

}
