package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressCombo {
	private String value;
	private String caption;
	private String t2;

	void clearProperty() {
		value = "";
		caption = "";
		t2 = "";
	}

	public AddressCombo() {
		clearProperty();
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

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

}
