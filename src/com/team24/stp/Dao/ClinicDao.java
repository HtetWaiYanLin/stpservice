package com.team24.stp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team24.stp.database.DBField;
import com.team24.stp.database.DBMgr;
import com.team24.stp.database.DBRecord;
import com.team24.stp.shared.ClinicData;
import com.team24.stp.shared.ClinicDataset;

public class ClinicDao {

	final static String Table_Name = "STPP_Clinic";

	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName(Table_Name);
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("created_date", (byte) 6));
		ret.getFields().add(new DBField("modified_date", (byte) 6));
		ret.getFields().add(new DBField("record_status", (byte) 1));
		ret.getFields().add(new DBField("user_syskey", (byte) 2));
		ret.getFields().add(new DBField("t1", (byte) 5));
		ret.getFields().add(new DBField("t2", (byte) 5));
		ret.getFields().add(new DBField("t3", (byte) 5));
		ret.getFields().add(new DBField("t4", (byte) 5));
		ret.getFields().add(new DBField("t5", (byte) 5));
		ret.getFields().add(new DBField("t6", (byte) 5));
		ret.getFields().add(new DBField("t7", (byte) 5));
		ret.getFields().add(new DBField("t8", (byte) 5));
		ret.getFields().add(new DBField("t9", (byte) 5));
		ret.getFields().add(new DBField("t10", (byte) 5));
		ret.getFields().add(new DBField("t11", (byte) 5));
		ret.getFields().add(new DBField("t12", (byte) 5));
		ret.getFields().add(new DBField("t13", (byte) 5));
		ret.getFields().add(new DBField("t14", (byte) 5));
		ret.getFields().add(new DBField("t15", (byte) 5));
		ret.getFields().add(new DBField("t16", (byte) 5));
		ret.getFields().add(new DBField("t17", (byte) 5));
		ret.getFields().add(new DBField("t18", (byte) 5));
		ret.getFields().add(new DBField("t19", (byte) 5));
		ret.getFields().add(new DBField("t20", (byte) 5));
		ret.getFields().add(new DBField("n1", (byte) 1));
		ret.getFields().add(new DBField("n2", (byte) 1));
		ret.getFields().add(new DBField("n3", (byte) 1));
		ret.getFields().add(new DBField("n4", (byte) 1));
		ret.getFields().add(new DBField("n5", (byte) 1));
		ret.getFields().add(new DBField("n6", (byte) 2));
		ret.getFields().add(new DBField("n7", (byte) 2));
		ret.getFields().add(new DBField("n8", (byte) 2));
		ret.getFields().add(new DBField("n9", (byte) 2));
		ret.getFields().add(new DBField("n10", (byte) 2));
		return ret;
	}

	public static ClinicData getDBRecord(DBRecord adbr) {
		ClinicData ret = new ClinicData();
		ret.setSyskey(adbr.getLong("syskey"));
		ret.setCreated_date(adbr.getDate("created_date"));
		ret.setModified_date(adbr.getDate("modified_date"));
		ret.setRecord_status(adbr.getInt("record_status"));
		ret.setUser_syskey(adbr.getLong("user_syskey"));
		ret.setT1(adbr.getString("t1"));
		ret.setT2(adbr.getString("t2"));
		ret.setT3(adbr.getString("t3"));
		ret.setT4(adbr.getString("t4"));
		ret.setT5(adbr.getString("t5"));
		ret.setT6(adbr.getString("t6"));
		ret.setT7(adbr.getString("t7"));
		ret.setT8(adbr.getString("t8"));
		ret.setT9(adbr.getString("t9"));
		ret.setT10(adbr.getString("t10"));
		ret.setT11(adbr.getString("t11"));
		ret.setT12(adbr.getString("t12"));
		ret.setT13(adbr.getString("t13"));
		ret.setT14(adbr.getString("t14"));
		ret.setT15(adbr.getString("t15"));
		ret.setT16(adbr.getString("t16"));
		ret.setT17(adbr.getString("t17"));
		ret.setT18(adbr.getString("t18"));
		ret.setT19(adbr.getString("t19"));
		ret.setT20(adbr.getString("t20"));
		ret.setN1(adbr.getInt("n1"));
		ret.setN2(adbr.getInt("n2"));
		ret.setN3(adbr.getInt("n3"));
		ret.setN4(adbr.getInt("n4"));
		ret.setN5(adbr.getInt("n5"));
		ret.setN6(adbr.getLong("n6"));
		ret.setN7(adbr.getLong("n7"));
		ret.setN8(adbr.getLong("n8"));
		ret.setN9(adbr.getLong("n9"));
		ret.setN10(adbr.getLong("n10"));

		return ret;
	}

	public static DBRecord setDBRecord(ClinicData data) {
		DBRecord ret = define();
		ret.setValue("syskey", data.getSyskey());
		ret.setValue("created_date", data.getCreated_date());
		ret.setValue("modified_date", data.getModified_date());
		ret.setValue("record_status", data.getRecord_status());
		ret.setValue("user_sysKey", data.getUser_syskey());
		ret.setValue("t1", data.getT1());
		ret.setValue("t2", data.getT2());
		ret.setValue("t3", data.getT3());
		ret.setValue("t4", data.getT4());
		ret.setValue("t5", data.getT5());
		ret.setValue("t6", data.getT6());
		ret.setValue("t7", data.getT7());
		ret.setValue("t8", data.getT8());
		ret.setValue("t9", data.getT9());
		ret.setValue("t10", data.getT10());
		ret.setValue("t11", data.getT11());
		ret.setValue("t12", data.getT12());
		ret.setValue("t13", data.getT13());
		ret.setValue("t14", data.getT14());
		ret.setValue("t15", data.getT15());
		ret.setValue("t16", data.getT16());
		ret.setValue("t17", data.getT17());
		ret.setValue("t18", data.getT18());
		ret.setValue("t19", data.getT19());
		ret.setValue("t20", data.getT20());
		ret.setValue("n1", data.getN1());
		ret.setValue("n2", data.getN2());
		ret.setValue("n3", data.getN3());
		ret.setValue("n4", data.getN4());
		ret.setValue("n5", data.getN5());
		ret.setValue("n6", data.getN6());
		ret.setValue("n7", data.getN7());
		ret.setValue("n8", data.getN8());
		ret.setValue("n9", data.getN9());
		ret.setValue("n10", data.getN10());

		return ret;
	}

	public static ClinicData read(long syskey, Connection conn) throws SQLException {
		ClinicData ret = new ClinicData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "WHERE record_status<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));
		return ret;
	}

	public static ClinicDataset searchClinic(String searchVal, String start, String end, Connection conn)
			throws SQLException {
		ClinicDataset res = new ClinicDataset();
		ArrayList<ClinicData> datalist = new ArrayList<ClinicData>();

		String whereclause = " WHERE record_status <> 4 ";
		if (searchVal != null) {
			if (!searchVal.isEmpty()) {
				whereclause += " AND   t2 LIKE '%" + searchVal + "%' ";
			}
		}

		String sql = "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY syskey) AS RowNum,* FROM " + Table_Name
				+ whereclause + " ) AS RowConstrainedResult  WHERE RowNum >= " + start + "  and RowNum <= " + end;
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(sql);
		ResultSet rset = stmt.executeQuery();

		while (rset.next()) {
			ClinicData ret = new ClinicData();
			ret.setSyskey(rset.getLong("syskey"));
			ret.setT1(rset.getString("t1"));
			ret.setT2(rset.getString("t2"));
			ret.setT3(rset.getString("t3"));	
			ret.setT4(rset.getString("t4"));
			ret.setT5(rset.getString("t5"));
			ret.setT6(rset.getString("t6"));
			ret.setT7(rset.getString("t7"));
			ret.setT8(rset.getString("t8"));
			ret.setT9(rset.getString("t9"));
			ret.setT10(rset.getString("t10"));
			ret.setT11(rset.getString("t11"));
			ret.setT12(rset.getString("t12"));	
			ret.setT13(rset.getString("t13"));
			ret.setN1(rset.getInt("n1"));
			datalist.add(ret);
		}

		PreparedStatement stat = conn.prepareStatement("SELECT COUNT(*) AS recCount FROM " + Table_Name + whereclause);
		ResultSet result = stat.executeQuery();
		result.next();
		res.setTotalCount(result.getInt("recCount"));
		// res.setPageSize(pager.getSize());
		// res.setCurrentPage(pager.getCurrent());
		// ClinicData[] dataarry = new ClinicData[datalist.size()];
		// dataarry = datalist.toArray(dataarry);
		// res.add(dataarry);
		res.setData(datalist);
		return res;
	}

}
