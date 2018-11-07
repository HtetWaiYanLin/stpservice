package com.team24.stp.shared;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressData {
	private String wardcode;
	private int srno;
	private long syskey;
	private String division;
	private String distinct;
	private String distinc;
	private String township;
	private String code;
	private String despmyan;
	private String despeng;
	private String islocal;
	private String messagecode;
	private String messagedesc;
	private String divisioncode;
	private int recordStatus;
	private void clearProperty(){
		messagecode = "";
		messagedesc = "";
		division = "";
		distinct = "";
		distinc = "";
		township = "";
		code = "";
		despmyan ="";
		despeng ="";
		islocal = "";
		srno = 0;
		syskey = 0;
		divisioncode = "";
		wardcode = "";
	}
	
	public AddressData(){
		clearProperty();
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDespmyan() {
		return despmyan;
	}
	public void setDespmyan(String despmyan) {
		this.despmyan = despmyan;
	}
	public String getDespeng() {
		return despeng;
	}
	public void setDespeng(String despeng) {
		this.despeng = despeng;
	}
	public String getIslocal() {
		return islocal;
	}
	public void setIslocal(String islocal) {
		this.islocal = islocal;
	}
	public String getMessagecode() {
		return messagecode;
	}
	public void setMessagecode(String messagecode) {
		this.messagecode = messagecode;
	}
	public String getMessagedesc() {
		return messagedesc;
	}
	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
	}

	public long getSyskey() {
		return syskey;
	}

	public void setSyskey(long syskey) {
		this.syskey = syskey;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDistinct() {
		return distinct;
	}

	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getDivisioncode() {
		return divisioncode;
	}

	public void setDivisioncode(String divisioncode) {
		this.divisioncode = divisioncode;
	}

	public String getWardcode() {
		return wardcode;
	}

	public void setWardcode(String wardcode) {
		this.wardcode = wardcode;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getDistinc() {
		return distinc;
	}

	public void setDistinc(String distinc) {
		this.distinc = distinc;
	}
	
	
	

}
