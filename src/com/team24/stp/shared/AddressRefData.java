package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressRefData {
	private String Code;
	private String DespMyan;
	private String DespEng;
	private long IsLocal;
	private String MinLat;
	private String MinLon;
	private String MaxLat;
	private String MaxLon;
	private String errorcode;
	private String errormessage;
	private String division;
	private String distric;
	private String township;

	public AddressRefData() {
		super();
		clearProperties();
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getDespMyan() {
		return DespMyan;
	}

	public void setDespMyan(String despMyan) {
		DespMyan = despMyan;
	}

	public String getDespEng() {
		return DespEng;
	}

	public void setDespEng(String despEng) {
		DespEng = despEng;
	}

	public long getIsLocal() {
		return IsLocal;
	}

	public void setIsLocal(long isLocal) {
		IsLocal = isLocal;
	}

	public String getMinLat() {
		return MinLat;
	}

	public void setMinLat(String minLat) {
		MinLat = minLat;
	}

	public String getMinLon() {
		return MinLon;
	}

	public void setMinLon(String minLon) {
		MinLon = minLon;
	}

	public String getMaxLat() {
		return MaxLat;
	}

	public void setMaxLat(String maxLat) {
		MaxLat = maxLat;
	}

	public String getMaxLon() {
		return MaxLon;
	}

	public void setMaxLon(String maxLon) {
		MaxLon = maxLon;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDistric() {
		return distric;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	protected void clearProperties() {
		this.Code = "";
		this.DespMyan = "";
		this.DespEng = "";
		this.IsLocal = 0;
		this.MinLat = "";
		this.MinLon = "";
		this.MaxLat = "";
		this.MaxLon = "";
		this.errorcode = "";
		this.errormessage = "";
		this.division = "";
		this.township = "";
		this.distric = "";

	}

}
