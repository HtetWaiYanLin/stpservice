package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValueCaptionDataSet {
	
	private ValueCaptionData[] data;
	
	public ValueCaptionDataSet(){
		data = new ValueCaptionData[0];
	}

	public ValueCaptionData[] getData() {
		return data;
	}

	public void setData(ValueCaptionData[] data) {
		this.data = data;
	}
	
}
