package com.team24.stp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nirvasoft.database.DBField;
import com.nirvasoft.database.DBMgr;
import com.nirvasoft.database.DBRecord;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.MenuData;
import com.team24.stp.shared.MenuViewData;
import com.team24.stp.shared.MenuViewDataset;
import com.team24.stp.shared.ValueCaptionData;
import com.team24.stp.shared.ValueCaptionDataSet;

/**
 * @author Htet Wai Yan Lin
 *
 */
public class MenuDao {

	final static String Table_Name = "STPP_Menu";

	public static DBRecord define() {
		DBRecord ret = new DBRecord();
		ret.setTableName(Table_Name);
		ret.setFields(new ArrayList<DBField>());
		ret.getFields().add(new DBField("syskey", (byte) 2));
		ret.getFields().add(new DBField("createddate", (byte) 5));
		ret.getFields().add(new DBField("modifieddate", (byte) 5));
		ret.getFields().add(new DBField("recordstatus", (byte) 1));
		ret.getFields().add(new DBField("usersyskey", (byte) 2));

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

	public static MenuData getDBRecord(DBRecord adbr) {
		MenuData ret = new MenuData();
		ret.setSyskey(adbr.getLong("syskey"));
		ret.setCreateddate(adbr.getString("createddate"));
		ret.setModifieddate(adbr.getString("modifieddate"));
		ret.setRecordstatus(adbr.getInt("recordstatus"));
		ret.setUsersyskey(adbr.getLong("usersysKey"));
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
		ret.setN1(adbr.getInt("n1"));
		ret.setN2(adbr.getInt("n2"));
		ret.setN3(adbr.getInt("n3"));
		ret.setN4(adbr.getInt("n4"));
		ret.setN5(adbr.getInt("n5"));
		ret.setN6(adbr.getLong("n6"));
		ret.setN7(adbr.getLong("n7"));
		ret.setN8(adbr.getLong("n8"));
		ret.setN9(adbr.getLong("n9"));
		ret.setN10(adbr.getLong("10"));

		return ret;
	}

	public static DBRecord setDBRecord(MenuData data) {
		DBRecord ret = define();
		ret.setValue("syskey", data.getSyskey());
		ret.setValue("createddate", data.getCreateddate());
		ret.setValue("modifieddate", data.getModifieddate());
		ret.setValue("recordstatus", data.getRecordstatus());
		ret.setValue("usersysKey", data.getUsersyskey());
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

	public static MenuData read(long syskey, Connection conn) throws SQLException {
		MenuData ret = new MenuData();
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), "WHERE recordstatus<>4 AND syskey=" + syskey, "", conn);
		if (dbrs.size() > 0)
			ret = getDBRecord(dbrs.get(0));
		return ret;
	}

	public static boolean isMenu(MenuData obj, Connection conn) throws SQLException {
		boolean flag = false;
		if (obj.getN1() == 1) {
			flag = isCodeExist(obj, conn);
		} else if (obj.getN1() == 2) {
			flag = isChildMenuExist(obj, conn);
		}
		return flag;
	}

	public static boolean isCodeExist(MenuData obj, Connection conn) throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(), " WHERE recordstatus<>4 AND syskey =" + obj.getSyskey(),
				"", conn);
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isChildMenuExist(MenuData obj, Connection conn) throws SQLException {
		ArrayList<DBRecord> dbrs = DBMgr.getDBRecords(define(),
				" WHERE RecordStatus<>4 AND  T1 LIKE '" + obj.getT1() + "' AND n2= " + obj.getN2(), "", conn);
		if (dbrs.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Result insert(MenuData obj, Connection conn) throws SQLException {
		Result res = new Result();

		if (!isMenu(obj, conn)) {
			String sql = DBMgr.insertString(define(), conn);
			PreparedStatement stmt = conn.prepareStatement(sql);
			DBRecord dbr = setDBRecord(obj);
			DBMgr.setValues(stmt, dbr);
			stmt.executeUpdate();
			res.setMsgDesc("Saved Successfully!");
			res.setState(true);
			res.setKeyResult(obj.getSyskey());
			return res;
		} else {
			res.setMsgDesc("Menu already exist!");
			res.setState(false);
			return res;
		}

	}

	public static Result update(MenuData obj, Connection conn) throws SQLException {
		Result res = new Result();
		String sql = DBMgr.updateString(" WHERE recordstatus <>4 AND Syskey=" + obj.getSyskey(), define(), conn);
		PreparedStatement stmt = conn.prepareStatement(sql);
		DBRecord dbr = setDBRecord(obj);
		DBMgr.setValues(stmt, dbr);
		stmt.executeUpdate();
		res.setState(true);
		res.setMsgDesc("Updated Successfully!");
		res.setKeyResult(obj.getSyskey());
		return res;
	}

	public static Result delete(long syskey, Connection conn) throws SQLException {

		Result res = new Result();
		String sql = "UPDATE " + Table_Name + " SET recordstatus=4 WHERE syskey=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, syskey);
		int rs = stmt.executeUpdate();
		if (rs > 0) {
			res.setState(true);
			res.setMsgDesc("Deleted Successfully!");
		} else {
			res.setState(false);
			res.setMsgDesc("Delete Fail!");
			return res;
		}

		return res;
	}

	public static ValueCaptionDataSet getmainmenulist(Connection conn) throws SQLException {
		ValueCaptionDataSet result = new ValueCaptionDataSet();
		ArrayList<ValueCaptionData> datalist = new ArrayList<ValueCaptionData>();

		String sql = "SELECT syskey,t3 FROM " + Table_Name + " WHERE recordstatus<>4 AND n1=1 order by t3";
		PreparedStatement stat = conn.prepareStatement(sql);
		ResultSet res = stat.executeQuery();
		while (res.next()) {
			ValueCaptionData combo = new ValueCaptionData();
			combo.setValue(String.valueOf(res.getLong("syskey")));
			combo.setCaption(res.getString("t3"));
			datalist.add(combo);
		}
		ValueCaptionData[] dataarry = new ValueCaptionData[datalist.size()];
		for (int i = 0; i < datalist.size(); i++) {
			dataarry[i] = datalist.get(i);
		}
		result.setData(dataarry);
		return result;
	}


	public static int getMenuCount(String searchVal, Connection conn) throws SQLException {
		String whereclause = " WHERE RecordStatus<>4 ";
		if (!searchVal.equals("")) {

			whereclause += "AND t1 LIKE '%" + searchVal + "%' OR t2 LIKE '%" + searchVal + "%' " + " OR syskey LIKE '%"
					+ searchVal + "%'";
		}
		int res = 1;
		PreparedStatement stat = conn.prepareStatement("SELECT COUNT(*) AS recCount FROM UVM022" + whereclause);
		ResultSet result = stat.executeQuery();
		result.next();
		res = result.getInt("recCount");
		return res;
	}

	public static MenuViewDataset searchMenu(String searchVal, String start, String end, String sort, String order,
			Connection conn) throws SQLException {
		MenuViewDataset res = new MenuViewDataset();
		ArrayList<MenuViewData> datalist = new ArrayList<MenuViewData>();

		String whereclause = " WHERE recordstatus <> 4 ";
		if (searchVal != null) {
			if (!searchVal.isEmpty()) {
				whereclause += " AND   t1 LIKE '%" + searchVal + "%' OR t2 LIKE '%" + searchVal + "%' "
						+ "OR  syskey LIKE '%" + searchVal + "%'";
			}
		}

		String sql = "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY t2 " + order + ") AS RowNum,* FROM " + Table_Name
				+ whereclause + " ) AS RowConstrainedResult  WHERE RowNum >= " + start + "  and RowNum <= " + end;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rset = stmt.executeQuery();

		while (rset.next()) {
			MenuViewData ret = new MenuViewData();
			ret.setSyskey(rset.getLong("syskey"));
			ret.setT1(rset.getString("t1"));
			ret.setT2(rset.getString("t2"));
			ret.setT3(rset.getString("t3"));
			ret.setN1(rset.getInt("n1"));
			ret.setN2(rset.getInt("n2"));
			datalist.add(ret);
		}
		if (datalist.size() > 0) {
			res.setState(true);
		} else {
			res.setState(false);
		}
		PreparedStatement stat = conn.prepareStatement("SELECT COUNT(*) AS recCount FROM " + Table_Name + whereclause);
		ResultSet result = stat.executeQuery();
		result.next();
		res.setTotalCount(result.getInt("recCount"));
		// res.setPageSize(pager.getSize());
		// res.setCurrentPage(pager.getCurrent());
		MenuViewData[] dataarry = new MenuViewData[datalist.size()];
		dataarry = datalist.toArray(dataarry);
		res.setData(dataarry);
		return res;
	}
	
	public static String getMenuCode(Connection aConn) {
		String ret = "";
		try {
			String sql = " SELECT 'M-'+RIGHT\r\n"
					+ " ('00'+Cast(ISNULL((SUBSTRING(MAX(t1),3,5))+1,1) AS varchar),6) AS menucode  FROM " + Table_Name;
			PreparedStatement ps = aConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				ret = rs.getString("menucode");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}