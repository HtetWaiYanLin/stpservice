package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.nirvasoft.database.SysKeyMgr;
import com.team24.stp.Dao.CompanyDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.CompanyData;
import com.team24.stp.shared.CompanyDataset;
import com.team24.stp.shared.ValueCaptionDataSet;
import com.team24.stp.util.ServerUtil;

public class CompanyMgr {
	
	public static Result saveCompanyData(CompanyData data, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = saveCompanyData(data, user, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Result saveCompanyData(CompanyData data, MrBean user, Connection conn) throws SQLException {
		Result res = new Result();
		data = initData(data, user, conn);
		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn(user.getUser().getOrganizationID())));
			res = CompanyDao.insert(data, conn);
		} else {
			res = CompanyDao.update(data, conn);
		}
		if (res.isState()) {
		}
		return res;
	}


	public static CompanyData initData(CompanyData data, MrBean user, Connection con) {
		data.setModifieddate(new java.sql.Date(new Date().getTime()));
		if (data.getSyskey() == 0) {
			String id = CompanyDao.getCompanyID(con);
			data.setT1(id);
			data.setCreateddate(new java.sql.Date(new Date().getTime()));
			data.setRecordstatus(1);
		}
		return data;
	}

	public static CompanyData readDataBySyskey(long pKey, MrBean user) {
		CompanyData res = new CompanyData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = CompanyDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Result deleteCompanyData(long syskey, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = CompanyDao.delete(syskey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static int getMenuCount(String searchVal, MrBean user) {

		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		int res = 0;
		try {
			res = CompanyDao.getMenuCount(searchVal, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static CompanyDataset searchCompanybyValue(String searchVal, String start, String end, String sort,
			String order, MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		CompanyDataset res = new CompanyDataset();
		try {
			res = CompanyDao.searchCompany(searchVal, start, end, sort, order, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	
	public static ValueCaptionDataSet getCompanyName(MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		try {
			res = CompanyDao.getCompanyName(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
