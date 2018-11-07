package com.team24.stp.Mgr;

import java.sql.Connection;
import java.sql.SQLException;

import com.team24.stp.Dao.AddressDao;
import com.team24.stp.framework.ConnAdmin;
import com.team24.stp.framework.MrBean;
import com.team24.stp.shared.AddressCombo;

public class AddressMgr {
	
	public static AddressCombo[] getDivision(MrBean user) {
		AddressCombo[] ref = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			ref = AddressDao.getDivision(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ref;
	}

	public static AddressCombo[] getDistrictbyDiv(String division, MrBean user) {
		AddressCombo[] ref = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			ref = AddressDao.getDistrictbyDiv(division, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ref;
	}

	public static AddressCombo[] getTownshipByDistrict(String district, MrBean user) {
		AddressCombo[] ref = null;
		Connection conn = ConnAdmin.getConn(user.getUser().getOrganizationID());
		try {
			ref = AddressDao.getTownshipByDistrict(district, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ref;
	}

}
