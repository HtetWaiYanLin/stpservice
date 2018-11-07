package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;

import com.team24.stp.Dao.ContentDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.shared.ContentData;
import com.team24.stp.shared.ContentDataSet;

public class ContentMgr {

	public static ContentData readDataBySyskey(long pKey, MrBean user) {
		ContentData res = new ContentData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = ContentDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static ContentDataSet searchContentbyValue(String searchVal, String start, String end, MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ContentDataSet res = new ContentDataSet();
		try {
			res = ContentDao.searchContent(searchVal, start, end, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
