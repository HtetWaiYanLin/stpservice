package com.team24.stp.database;

import java.util.ArrayList;
import java.sql.Date;

public class DBRecord {
	private String tableName;
	private ArrayList<DBField> fields;

	public DBRecord() {
	}

	public DBRecord(DBRecord a) {
		setFields(new ArrayList<DBField>());
		for (int i = 0; i < a.getFields().size(); i++) {
			getFields().add(new DBField((DBField) a.getFields().get(i)));
		}
	}

	public ArrayList<DBField> getFields() {
		return this.fields;
	}

	public void setFields(ArrayList<DBField> fields) {
		this.fields = fields;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getInt(String aFieldName) {
		int ret = 0;
		for (int i = 0; i < this.fields.size(); i++) {
			if (aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName())) {
				ret = ((DBField) this.fields.get(i)).getIntValue();
			}
		}
		return ret;
	}

	public long getLong(String aFieldName) {
		long ret = 0L;
		for (int i = 0; i < this.fields.size(); i++) {
			if (aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName())) {
				ret = ((DBField) this.fields.get(i)).getLongValue();
			}
		}
		return ret;
	}

	public double getDouble(String aFieldName) {
		double ret = 0.0D;
		for (int i = 0; i < this.fields.size(); i++) {
			if (aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName())) {
				ret = ((DBField) this.fields.get(i)).getDoubleValue();
			}
		}
		return ret;
	}

	public String getString(String aFieldName) {
		String ret = "";
		for (int i = 0; i < this.fields.size(); i++) {
			if (aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName())) {
				ret = ((DBField) this.fields.get(i)).getSvalue();
			}
		}
		return ret;
	}

	public Date getDate(String aFieldName) {
		Date ret = new Date(new java.util.Date().getTime());
		for (int i = 0; i < this.fields.size(); i++) {
			if (aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName())) {
				ret = ((DBField) this.fields.get(i)).getDateValue();
			}
		}
		return ret;
	}

	public void setValue(String aFieldName, int value) {
		for (int i = 0; i < this.fields.size(); i++) {
			if ((aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName()))
					&& (((DBField) this.fields.get(i)).getFieldType() == 1)) {
				((DBField) this.fields.get(i)).setIntValue(value);
			}
		}
	}

	public void setValue(String aFieldName, long value) {
		for (int i = 0; i < this.fields.size(); i++) {
			if ((aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName()))
					&& (((DBField) this.fields.get(i)).getFieldType() == 2)) {
				((DBField) this.fields.get(i)).setLongValue(value);
			}
		}
	}

	public void setValue(String aFieldName, double value) {
		for (int i = 0; i < this.fields.size(); i++) {
			if ((aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName()))
					&& (((DBField) this.fields.get(i)).getFieldType() == 3)) {
				((DBField) this.fields.get(i)).setDoubleValue(value);
			}
		}
	}

	public void setValue(String aFieldName, String value) {
		for (int i = 0; i < this.fields.size(); i++) {
			if ((aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName()))
					&& (((DBField) this.fields.get(i)).getFieldType() == 5)) {
				((DBField) this.fields.get(i)).setSvalue(value);
			}
		}
	}

	public void setValue(String aFieldName, Date value) {
		for (int i = 0; i < this.fields.size(); i++) {
			if ((aFieldName.equalsIgnoreCase(((DBField) this.fields.get(i)).getFieldName()))
					&& (((DBField) this.fields.get(i)).getFieldType() == 6)) {
				((DBField) this.fields.get(i)).setDateValue(value);
			}
		}
	}
}
