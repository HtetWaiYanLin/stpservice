package com.team24.stp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team24.stp.shared.RoleMenuData;

public class RoleMenuDao {

	public static RoleMenuData[] getRoleMenuList(Connection conn) {
		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try {
			String sql = "SELECT syskey,t2,t3 FROM STPP_Menu WHERE n1=1";
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setT3(result.getString("t3"));
				data.setChildmenus(RoleMenuDao.getChildMenuData(result.getLong("syskey"), conn));
				ret.add(data);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		dataarray = ret.toArray(dataarray);

		return dataarray;

	}

	public static RoleMenuData[] getChildMenuData(long skey, Connection conn) throws SQLException {

		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try {
			String sql = "SELECT DISTINCT t2, t3, MIN(syskey) AS syskey,MIN(n1) AS n1 FROM STPP_Menu WHERE t4= '"
					+ skey + "' GROUP BY t2, t3 ORDER BY syskey";
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				String t3 = result.getString("t3");
				data.setT3(t3);
				data.setN2(result.getLong("n1"));
				ret.add(data);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		dataarray = ret.toArray(dataarray);

		return dataarray;
	}

	
	public static RoleMenuData[] getRoleMenuList(long pkey, long skey, Connection conn) {
		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try {
			String sql = "SELECT syskey,t2,t3 FROM STPP_Menu WHERE n1=1";
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();
			while (result.next()) {
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setT3(result.getString("t3"));
				long s[] = RoleMenuDao.getMenuResult(pkey, conn);
				for (int i = 0; i < s.length; i++) {
					if (s[i] == result.getLong("syskey")) {
						data.setResult(true);
					}
				}
				data.setChildmenus(RoleMenuDao.getChildMenuData(pkey, skey, result.getLong("syskey"), conn));
				ret.add(data);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		dataarray = ret.toArray(dataarray);

		return dataarray;

	}

	public static RoleMenuData[] getChildMenuData(long pkey, long skey1, long skey2, Connection conn)
			throws SQLException {

		ArrayList<RoleMenuData> ret = new ArrayList<RoleMenuData>();
		RoleMenuData data = new RoleMenuData();
		try {
			String sql = "SELECT syskey,t2,t3 FROM STPP_Menu WHERE n1=2 AND t4 = '"+skey2+ "'";
			System.out.println(sql);
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				data = new RoleMenuData();
				data.setSyskey(result.getLong("syskey"));
				data.setT2(result.getString("t2"));
				data.setT3(result.getString("t3"));
				long s[] = RoleMenuDao.getChildResult(pkey, conn);
				for (int i = 0; i < s.length; i++) {
					if (s[i] == (result.getLong("syskey")))
						data.setResult(true);
				}
				ret.add(data);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		RoleMenuData[] dataarray = new RoleMenuData[ret.size()];
		dataarray = ret.toArray(dataarray);

		return dataarray;
	}

	public static long[] getMenuResult(long skey, Connection conn) throws SQLException {
		String parentkey = "";
		String temp = "";
		try {
			String sql = "Select t3 from STPP_Role Where syskey = " + skey;
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				parentkey = res.getString("t3");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		String[] result = (parentkey.split(","));
		
		long[] parent = new long[result.length];
		if(result.length>0) {
			for(int i=0; i<result.length; i++ ) {
				temp = result[i];
				if(!temp.equals("") || !temp.isEmpty()) {
					parent[i] =Long.parseLong(temp);
				}
				
			}	
		}
	
		return parent;
	}

	
	
	public static long[] getChildResult(long skey, Connection conn) throws SQLException {
		String childkey = "";
		String temp = "";
		try {
			String sql = "Select t4 from STPP_Role Where syskey = " + skey;
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				childkey = res.getString("t4");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		String[] result = (childkey.split(","));
		
		long[] child = new long[result.length];
		
		if(result.length>0) {
			for(int i=0; i<result.length; i++ ) {
				temp = result[i];
				if(!temp.equals("") || !temp.isEmpty()) {
					child[i] =Long.parseLong(temp);	
				}
				
			}
		}
		return child;
	}

}