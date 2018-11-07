package com.team24.stp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMgr {
	private static int type = 0;
	private static String driver = "";
	private static String URL = "";
	private static String userName = "";
	private static String password = "";
	private static String connString = "";

	public ConnMgr() {
		if (driver.equals("")) {
			driver = "net.sourceforge.jtds.jdbc.Driver";
		}
		if (URL.equals("")) {
			URL = "jdbc:jtds:sqlserver://localhost:1433/ICBS;Instance=NSDB";
		}
		if (userName.equals("")) {
			userName = "sa";
		}
		if (password.equals("")) {
			password = "123";
		}
		if ((connString.equals("")) && (type == 0)) {
			connString = "jdbc:jtds:sqlserver://localhost:1433;databaseName=SuccessRP;user=sa;password=123";
		}
	}

	public ConnMgr(String aServer, int aPort, String aInstance, String aDatabase, String aUser, String aPassword,
			int aType) {
		if (aType == 3) {
			driver = "net.sourceforge.jtds.jdbc.Driver";
			URL = "jdbc:jtds:sqlserver://" + aServer + ":" + aPort + "/" + aDatabase + ";Instance=" + aInstance + ";";
		} else if (aType == 0) {
			driver = "net.sourceforge.jtds.jdbc.Driver";
			URL = "jdbc:jtds:sqlserver://" + aServer + ":" + aPort + "/" + aDatabase + ";Instance=" + aInstance + ";";
		} else if (aType == 9) {
			driver = "oracle.jdbc.driver.OracleDriver";
			URL = "jdbc:oracle:thin:" + aServer + ":" + aPort + ":" + aDatabase;
		} else {
			driver = "net.sourceforge.jtds.jdbc.Driver";
			URL = "jdbc:jtds:sqlserver://192.168.1.244:1433;databaseName=SuccessRP;user=sa;password=123";
		}
		userName = aUser;
		password = aPassword;
		type = aType;
	}

	public ConnMgr(String aMDB, String aUser, String aPassword) {
		URL = "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=" + aMDB;
		userName = aUser;
		password = aPassword;
		driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	}

	public static String connString(String aMDB) {
		return "jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=" + aMDB;
	}

	public static Connection getConn(String aConnString) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(aConnString, "", "");
	}

	public Connection getConn() {
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(URL, userName, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
