package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;

import com.team24.stp.Dao.ClinicDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.shared.ClinicData;
import com.team24.stp.shared.ClinicDataset;

public class ClinicMgr {



	public static ClinicData readDataBySyskey(long pKey, MrBean user) {
		ClinicData res = new ClinicData();
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			res = ClinicDao.read(pKey, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}


	public static ClinicDataset searchClinicbyValue(String searchVal, String start, String end,  MrBean user) {
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		ClinicDataset res = new ClinicDataset();
		try {
			res = ClinicDao.searchClinic(searchVal, start, end, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
