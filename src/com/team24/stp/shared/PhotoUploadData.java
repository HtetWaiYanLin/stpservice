package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhotoUploadData {
	private int serial;
	private String name;
	private String desc;
	private String order;
	private String url;
	private String imgdesc;
	
	public PhotoUploadData() {
		super();
		this.clearProperties();
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	public String getImgdesc() {
		return imgdesc;
	}

	public void setImgdesc(String imgdesc) {
		this.imgdesc = imgdesc;
	}

	private void clearProperties() {
		this.serial = 0;
		this.name = "";
		this.desc = "";
		this.order="";
		this.url="";
		this.imgdesc="";
	}

}
