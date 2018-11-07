package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nirvasoft.database.SysKeyMgr;
import com.team24.stp.Dao.UserDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.UserData;
import com.team24.stp.shared.UserDataset;
import com.team24.stp.shared.MenuRole;
import com.team24.stp.shared.ValueCaptionDataSet;
import com.team24.stp.util.ServerUtil;

public class UserMgr {

	public static Result saveUserData(UserData data, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			// long rolemenu = data.getSyskey();
			res = saveUserData(data, user, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Result saveUserData(UserData data, MrBean user, Connection conn) throws SQLException {
		Result res = new Result();
		data = initData(data, user, conn);
		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn(user.getUser().getOrganizationID())));
			res = UserDao.insert(data, conn);
		} else {
			res = UserDao.update(data, conn);
		}
		if (res.isState()) {
		}
		return res;
	}

	public static MenuRole initMenuRoleData(MenuRole mr, UserData data, MrBean user, Connection con) {

		String date23 = new SimpleDateFormat("yyyyMMdd").format(new Date());
		mr.setUserId(user.getUser().getUserId());
		mr.setUserName(user.getUser().getUserName());
		mr.setModifiedDate(date23);
		mr.setN1(1);
		mr.setN2(data.getSyskey());
		if (mr.getSyskey() == 0) {
			mr.setCreatedDate(date23);
			mr.setRecordStatus(1);
			mr.setSyncBatch(0);
			mr.setSyncStatus(1);
		}
		return mr;
	}

	public static UserData initData(UserData data, MrBean user, Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		data.setModifieddate(date);
		if (data.getSyskey() == 0) {
			data.setCreateddate(date);
			data.setRecordstatus(1);
		}
		return data;
	}

	public static UserData readDataBySyskey(long pKey, MrBean user) {
		UserData res = new UserData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = UserDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Result deleteUserData(long syskey, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = UserDao.delete(syskey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static int getMenuCount(String searchVal, MrBean user) {

		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		int res = 0;
		try {
			res = UserDao.getMenuCount(searchVal, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static UserDataset searchUserbyValue(String searchVal, String start, String end, String sort,
			String order, MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		UserDataset res = new UserDataset();
		try {
			res = UserDao.searchUser(searchVal, start, end, sort, order, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static ValueCaptionDataSet getmainmenulist(MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		try {
			res = UserDao.getmainmenulist(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
