package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nirvasoft.database.SysKeyMgr;
import com.team24.stp.Dao.RoleDao;
import com.team24.stp.Dao.RoleMenuDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.framework.Result;
import com.team24.stp.shared.RoleData;
import com.team24.stp.shared.RoleDataset;
import com.team24.stp.shared.RoleMenuData;
import com.team24.stp.shared.ValueCaptionDataSet;
import com.team24.stp.util.ServerUtil;

public class RoleMgr {

	public static Result saveRoleData(RoleData data, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = saveRoleData(data, user, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}
		return res;
	}

	public static Result saveRoleData(RoleData data, MrBean user, Connection conn) throws SQLException {
		Result res = new Result();
		data = initData(data, user, conn);

		if (data.getSyskey() == 0) {
			data.setSyskey(SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn(user.getUser().getOrganizationID())));
			res = RoleDao.insert(data, conn);
		} else {
			res = RoleDao.update(data, conn);
		}
		if (res.isState()) {
		}
		return res;
	}

	public static RoleData initData(RoleData data, MrBean user, Connection con) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		data.setModifieddate(date);
		if (data.getSyskey() == 0) {
			String rolecode = RoleDao.getRoleCode(con);
			data.setT1(rolecode);
			data.setCreateddate(date);
			data.setRecordstatus(1);
		}
		data.setT3(SyskeytoStringforParent(data.getMenu()));
		data.setT4(SyskeytoStringforChild(data.getMenu()));

		return data;
	}

	public static String SyskeytoStringforParent(RoleMenuData[] menu) {
		String parentkey = "";
		for (int i = 0; i < menu.length; i++) {
			if (menu[i].isResult()) {
				if (i == 0) {
					parentkey += menu[i].getSyskey();
				} else {
					parentkey += ",";
					parentkey += menu[i].getSyskey();
				}
			}
		}
		System.out.println("Hello I am parent Key  : " + parentkey);
		return parentkey;
	}

	public static String SyskeytoStringforChild(RoleMenuData[] menu) {
		String childkey = "";
		for (int i = 0; i < menu.length; i++) {
			for (int j = 0; j < menu[i].getChildmenus().length; j++) {
				if (menu[i].getChildmenus()[j].isResult()) {
					if (j == 0 && i == 0) {
						childkey += menu[i].getChildmenus()[j].getSyskey();
					} else {
						childkey += ",";
						childkey += menu[i].getChildmenus()[j].getSyskey();
					}
				}
			}
		}
		System.out.println("Hello I am child Key  : " + childkey);
		return childkey;
	}

	public static RoleData readDataBySyskey(long pKey, MrBean user) {
		RoleData res = new RoleData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = RoleDao.read(pKey, conn);
			RoleMenuData[] dataarray;
			dataarray = RoleMenuDao.getRoleMenuList(pKey, res.getSyskey(), conn);
			res.setMenu(dataarray);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Result deleteRoleData(long syskey, MrBean user) {
		Result res = new Result();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = RoleDao.delete(syskey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static RoleDataset searchRolebyValue(String searchVal, String start, String end, String sort, String order,
			MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		RoleDataset res = new RoleDataset();
		try {
			res = RoleDao.searchRole(searchVal, start, end, sort, order, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static ValueCaptionDataSet getRoleCombo(MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ValueCaptionDataSet res = new ValueCaptionDataSet();
		try {
			res = RoleDao.getRoleCombo(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static RoleMenuData[] getRoleMenuList(MrBean user) throws SQLException {
		RoleMenuData[] dataarray;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		dataarray = RoleMenuDao.getRoleMenuList(conn);
		return dataarray;
	}

	public static Result insertRoleMenu(MrBean user, RoleData ur, Connection conn) {
		Result res = new Result();
		try {

			for (int i = 0; i < ur.getMenu().length; i++) {
				if (ur.getMenu()[i].isResult()) {
					res = new Result();
					RoleData obj = new RoleData();
					String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
					obj.setSyskey(
							SysKeyMgr.getSysKey(1, "syskey", ConnAdmin.getConn(user.getUser().getOrganizationID())));
					obj.setCreateddate(date);
					obj.setModifieddate(date);
					obj.setRecordstatus(1);
					obj.setN6(ur.getSyskey()); // row syskey
					obj.setN7(ur.getMenu()[i].getSyskey()); // menu syskey
					res = RoleDao.insert(obj, conn);

					for (int j = 0; j < ur.getMenu()[i].getChildmenus().length; j++) {
						if (ur.getMenu()[i].getChildmenus()[j].isResult()) {
							res = new Result();
							obj = new RoleData();
							date = new SimpleDateFormat("yyyyMMdd").format(new Date());
							obj.setCreateddate(date);
							obj.setModifieddate(date);
							obj.setRecordstatus(1);
							obj.setN6(ur.getSyskey());
							obj.setN7(ur.getMenu()[i].getChildmenus()[j].getSyskey()); // menu

							// res = RoleDao.insertRoleMenu(obj, conn);
						}
					}

				} else {
					res.setMsgDesc("Please select Menu button!!!");
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return res;
	}

}
