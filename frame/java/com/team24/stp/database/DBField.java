package com.team24.stp.database;

import java.sql.Date;

public class DBField {
	private String fieldName;
	private byte fieldType;
	private double doubleValue;
	private int intValue;
	private long longValue;
	private String svalue;
	private Date dateValue;

	public DBField() {
	}

	public DBField(DBField a) {
		this.fieldName = a.fieldName;
		this.fieldType = a.fieldType;
	}

	public DBField(String aFieldName, byte aFieldType) {
		this.fieldName = aFieldName;
		this.fieldType = aFieldType;
	}

	public int getIntValue() {
		return this.intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public long getLongValue() {
		return this.longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public double getDoubleValue() {
		return this.doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public String getSvalue() {
		return this.svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public byte getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(byte fieldType) {
		this.fieldType = fieldType;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

}
