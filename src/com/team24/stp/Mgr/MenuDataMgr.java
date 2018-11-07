package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nirvasoft.database.SysKeyMgr;
import com.team24.stp.Dao.MenuDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.MenuData;
import com.team24.stp.shared.MenuRole;
import com.team24.stp.shared.MenuViewDataset;
import com.team24.stp.shared.ValueCaptionDataSet;
import com.team24.stp.util.ServerUtil;

public class MenuDataMgr {

	public static Result saveMenuData(MenuData data, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			// long rolemenu = data.getSyskey();
			res = saveMenuData(data, user, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Result saveMenuData(MenuData data, MrBean user, Connection conn) throws SQLException {
		Result res = new Result();
		data = initData(data, user, conn);
		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn(user.getUser().getOrganizationID())));
			res = MenuDao.insert(data, conn);
		} else {
			res = MenuDao.update(data, conn);
		}
		if (res.isState()) {
		}
		return res;
	}

	public static MenuRole initMenuRoleData(MenuRole mr, MenuData data, MrBean user, Connection con) {

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

	public static MenuData initData(MenuData data, MrBean user, Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		data.setModifieddate(date);
		if (data.getSyskey() == 0) {
			String menucode = MenuDao.getMenuCode(con);
			data.setT1(menucode);
			data.setCreateddate(date);
			data.setRecordstatus(1);
		}
		return data;
	}

	public static MenuData readDataBySyskey(long pKey, MrBean user) {
		MenuData res = new MenuData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = MenuDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Result deleteMenuData(long syskey,  MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = MenuDao.delete(syskey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public static MenuViewDataset searchMenubyValue(String searchVal, String start, String end, String sort, String order, MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		MenuViewDataset res = new MenuViewDataset();
		try {
			res = MenuDao.searchMenu(searchVal, start,end, sort, order, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static ValueCaptionDataSet getmainmenulist(MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		try {
			res = MenuDao.getmainmenulist(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
