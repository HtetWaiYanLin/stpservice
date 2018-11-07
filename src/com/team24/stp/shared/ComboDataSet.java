package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ComboDataSet {
	
	private ComboData[] data;
	
	public ComboDataSet(){
		data = new ComboData[0];
	}

	public ComboData[] getData() {
		return data;
	}

	public void setData(ComboData[] data) {
		this.data = data;
	}
	
}
