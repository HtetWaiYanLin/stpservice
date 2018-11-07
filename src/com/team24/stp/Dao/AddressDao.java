package com.team24.stp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBRecord;
import com.team24.stp.shared.AddressCombo;
import com.team24.stp.shared.AddressRefData;

public class AddressDao {

	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName("addressref");
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("code", (byte) 5));
		ret.getFields().add(new DBField("despMyan", (byte) 5));
		ret.getFields().add(new DBField("despEng", (byte) 5));
		ret.getFields().add(new DBField("isLocal", (byte) 2));
		ret.getFields().add(new DBField("minLat", (byte) 5));
		ret.getFields().add(new DBField("minLon", (byte) 5));
		ret.getFields().add(new DBField("maxLat", (byte) 5));
		ret.getFields().add(new DBField("maxLon", (byte) 5));
		return ret;
	}

	public static DBRecord define(String tabName) {
		DBRecord ret = new DBRecord();
		ret.setTableName(tabName);
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("code", (byte) 5));
		ret.getFields().add(new DBField("despMyan", (byte) 5));
		ret.getFields().add(new DBField("despEng", (byte) 5));
		ret.getFields().add(new DBField("isLocal", (byte) 2));
		ret.getFields().add(new DBField("minLat", (byte) 5));
		ret.getFields().add(new DBField("minLon", (byte) 5));
		ret.getFields().add(new DBField("maxLat", (byte) 5));
		ret.getFields().add(new DBField("maxLon", (byte) 5));

		return ret;
	}

	public static AddressRefData getDBRecord(DBRecord adbr) {
		AddressRefData ret = new AddressRefData();
		ret.setCode(adbr.getString("code"));
		ret.setDespMyan(adbr.getString("despMyan"));
		ret.setDespEng(adbr.getString("despEng"));
		ret.setIsLocal(adbr.getLong("isLocal"));
		ret.setMinLat(adbr.getString("minLat"));
		ret.setMinLon(adbr.getString("minLon"));
		ret.setMaxLat(adbr.getString("maxLat"));
		ret.setMaxLon(adbr.getString("maxLon"));
		return ret;
	}

	public static DBRecord setDBRecord(AddressRefData data) {
		DBRecord ret = define();
		ret.setValue("code", data.getCode());
		ret.setValue("despMyan", data.getDespMyan());
		ret.setValue("despEng", data.getDespEng());
		ret.setValue("isLocal", data.getIsLocal());
		ret.setValue("minLat", data.getMinLat());
		ret.setValue("minLon", data.getMinLon());
		ret.setValue("maxLat", data.getMaxLon());
		ret.setValue("maxLon", data.getMaxLon());
		return ret;
	}

	public static AddressCombo[] getDivision(Connection aConnection) throws SQLException {
		AddressCombo[] arr = null;
		AddressCombo ref = new AddressCombo();
		int count = 0;

		String l_Query = "select COUNT(*) c from dbo.AddressRef where SUBSTRING(code,3,6) = '000000' and code<>'00000000'";
		PreparedStatement pstmt = aConnection.prepareStatement(l_Query);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt("c");
			pstmt.close();
		}
		arr = new AddressCombo[count];

		l_Query = "select Code ,despMyan from dbo.AddressRef where SUBSTRING(code,3,6) = '000000' and code<>'00000000' order by code ";

		pstmt = aConnection.prepareStatement(l_Query);
		rs = pstmt.executeQuery();

		int index = 0;
		while (rs.next()) {
			ref = new AddressCombo();
			ref.setValue(rs.getString("Code"));
			ref.setCaption(rs.getString("despMyan"));
			arr[index] = ref;
			index++;
		}
		return arr;
	}

	public static AddressCombo[] getDistrictbyDiv(String division, Connection l_Conn) throws SQLException {
		AddressCombo[] arr = null;
		AddressCombo ref = new AddressCombo();
		int count = 0;
		String l_Query = "select COUNT(*) c from dbo.AddressRef "
				+ " where SUBSTRING(code,1,2) = ? and SUBSTRING(code,3,3) <> '000' and "
				+ " SUBSTRING(code,6,3) = '000'";
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, division.substring(0, 2));
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt("c");
			pstmt.close();
		}
		arr = new AddressCombo[count];
		l_Query = "select Code ,despMyan from dbo.AddressRef "
				+ " where SUBSTRING(code,1,2) = ? and SUBSTRING(code,3,3) <> '000' and "
				+ " SUBSTRING(code,6,3) = '000' order by code ";

		pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, division.substring(0, 2));
		rs = pstmt.executeQuery();

		int index = 0;
		while (rs.next()) {
			ref = new AddressCombo();
			ref.setValue(rs.getString("Code"));
			ref.setCaption(rs.getString("despMyan"));
			arr[index] = ref;
			index++;
		}
		return arr;
	}

	public static AddressCombo[] getTownshipByDistrict(String district, Connection l_Conn) throws SQLException {
		AddressCombo[] arr = null;
		AddressCombo ref = new AddressCombo();
		int count = 0;
		/*
		 * int div = 0; if (distinct == null || distinct.trim().equals("")) { div = 0; }
		 * else { div = Integer.parseInt(distinct); }
		 */
		String l_Query = "select COUNT(*) c from dbo.AddressRef " + " where SUBSTRING(code,1,5) = ? and  "
				+ " SUBSTRING(code,6,3) <> '000' ";
		PreparedStatement pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, district.substring(0, 5));
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			count = rs.getInt("c");
			pstmt.close();
		}

		arr = new AddressCombo[count];

		l_Query = "select Code ,despMyan from dbo.AddressRef " + " where SUBSTRING(code,1,5) = ? and  "
				+ " SUBSTRING(code,6,3) <> '000' order by code ";
		pstmt = l_Conn.prepareStatement(l_Query);
		pstmt.setString(1, district.substring(0, 5));
		rs = pstmt.executeQuery();

		int index = 0;
		while (rs.next()) {
			ref = new AddressCombo();
			ref.setValue(rs.getString("Code"));
			ref.setCaption(rs.getString("despMyan"));
			arr[index] = ref;
			index++;
		}
		return arr;
	}

}
