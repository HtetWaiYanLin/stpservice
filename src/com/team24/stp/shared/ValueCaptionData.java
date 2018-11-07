package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValueCaptionData {

	private String value;
	private String caption;

	public ValueCaptionData() {
		super();
		clearProperties();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void clearProperties() {
		this.value = "";
		this.caption = "";
	}

}
