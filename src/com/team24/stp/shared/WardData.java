package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WardData {
	private long syskey;
	private String code;
	private String wardcode;
	private long IsLocal;
	private int recordstatus;
	private String division;
	private String distinc;
	private String township;
	private String DespMyan;
	private String DespEng;
	private String errorcode;
	private String errormessage;

	private void clearProperty() {
		this.syskey = 0;
		this.division = "";
		this.distinc = "";
		this.township = "";
		this.DespEng = "";
		this.DespMyan = "";
		this.errorcode = "";
		this.errormessage = "";
		this.wardcode = "";
		this.IsLocal = 0;
		this.recordstatus = 0;
		this.code = "";

	}

	public WardData() {
		clearProperty();
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDistinc() {
		return distinc;
	}

	public void setDistinc(String distinc) {
		this.distinc = distinc;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
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

	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public String getWardcode() {
		return wardcode;
	}

	public void setWardcode(String wardcode) {
		this.wardcode = wardcode;
	}

	public long getIsLocal() {
		return IsLocal;
	}

	public void setIsLocal(long isLocal) {
		IsLocal = isLocal;
	}

	public int getRecordstatus() {
		return recordstatus;
	}

	public void setRecordstatus(int recordstatus) {
		this.recordstatus = recordstatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
