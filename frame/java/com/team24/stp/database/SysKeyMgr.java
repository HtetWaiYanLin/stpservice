package com.team24.stp.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SysKeyMgr
{
  public static int readAutokey(long syskey, String aTableName, Connection aConnection)
  {
    int autokey = 0;
    String l_query = "";
    if (!aTableName.equals("")) {
      l_query = "Select id,syskey from " + aTableName + " where syskey=" + syskey;
    } else {
      l_query = "Select id,syskey from syskey where syskey=" + syskey;
    }
    try
    {
      PreparedStatement l_Pstmt = aConnection.prepareStatement(l_query);
      ResultSet l_RS = l_Pstmt.executeQuery();
      if (l_RS.next()) {
        autokey = l_RS.getInt("id");
      }
      l_RS.close();
      l_Pstmt.close();
    }
    catch (SQLException localSQLException) {}
    return autokey;
  }
  
  public static long getSysKey(int aModule, String aTableName, Connection aConnection, boolean aAutocommit)
    throws SQLException
  {
    int syskey = 0;
    boolean l_result = false;
    try
    {
      aConnection.setAutoCommit(aAutocommit);
      try
      {
        syskey = 0;
        DBRecord syskeytable = defineSysKey();
        PreparedStatement stmt = aConnection.prepareStatement(DBMgr.insertString(syskeytable, aConnection));
        syskeytable.setValue("syskey", syskey);
        syskeytable.setValue("module", aModule);
        syskeytable.setValue("code", aTableName);
        DBMgr.setValues(stmt, syskeytable);
        if (stmt.executeUpdate() > 0) {
          l_result = true;
        }
        int autokey = readAutokey(syskey, "syskey", aConnection);
        
        Statement updateStmt = aConnection.createStatement();
        updateStmt.execute("update syskey set syskey=" + autokey + " where syskey=" + syskey);
        syskey = autokey;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      if (!aAutocommit) {
        if (l_result) {
          aConnection.commit();
        } else {
          aConnection.rollback();
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return syskey;
  }
  
  public static long getSysKey(int aModule, String aCode, Connection aConnection, boolean aAutocommit, String aTableName)
    throws SQLException
  {
    int syskey = 0;
    boolean l_result = false;
    try
    {
      aConnection.setAutoCommit(aAutocommit);
      try
      {
        syskey = 0;
        DBRecord syskeytable = defineSysKey();
        PreparedStatement stmt = aConnection.prepareStatement(DBMgr.insertString(syskeytable, aConnection));
        syskeytable.setValue("syskey", syskey);
        syskeytable.setValue("module", aModule);
        syskeytable.setValue("code", aTableName);
        DBMgr.setValues(stmt, syskeytable);
        if (stmt.executeUpdate() > 0) {
          l_result = true;
        }
        int autokey = readAutokey(syskey, "syskey", aConnection);
        
        Statement updateStmt = aConnection.createStatement();
        updateStmt.execute("update syskey set syskey=" + autokey + " where syskey=" + syskey);
        syskey = autokey;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      if (!aAutocommit) {
        if (l_result) {
          aConnection.commit();
        } else {
          aConnection.rollback();
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return syskey;
  }
  
  public static long getSysKey(int aModule, String aCode, Connection aConnection)
    throws SQLException
  {
    int syskey = 0;
    try
    {
      aConnection.setAutoCommit(false);
      try
      {
        syskey = 0;
        DBRecord syskeytable = defineSysKey();
        PreparedStatement stmt = aConnection.prepareStatement(DBMgr.insertString(syskeytable, aConnection));
        syskeytable.setValue("syskey", syskey);
        syskeytable.setValue("module", aModule);
        syskeytable.setValue("code", "syskey");
        DBMgr.setValues(stmt, syskeytable);
        int autokey = readAutokey(syskey, "syskey", aConnection);
        
        Statement updateStmt = aConnection.createStatement();
        updateStmt.execute("update syskey set syskey=" + autokey + " where syskey=" + syskey);
        syskey = autokey;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      aConnection.commit();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return syskey;
  }
  
  public static long getSysKey(int aModule, String aCode, Connection aConnection, String aTableName)
    throws SQLException
  {
    int syskey = 0;
    try
    {
      aConnection.setAutoCommit(false);
      try
      {
        syskey = 0;
        DBRecord syskeytable = defineSysKey();
        PreparedStatement stmt = aConnection.prepareStatement(DBMgr.insertString(syskeytable, aConnection));
        syskeytable.setValue("syskey", syskey);
        syskeytable.setValue("module", aModule);
        syskeytable.setValue("code", aTableName);
        DBMgr.setValues(stmt, syskeytable);
        int autokey = readAutokey(syskey, "syskey", aConnection);
        
        Statement updateStmt = aConnection.createStatement();
        updateStmt.execute("update syskey set syskey=" + autokey + " where syskey=" + syskey);
        syskey = autokey;
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      aConnection.commit();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return syskey;
  }
  
  public static DBRecord defineSysKey()
  {
    DBRecord ret = new DBRecord();
    ret.setTableName("SysKey");
    ret.setFields(new ArrayList<DBField>());
    ret.getFields().add(new DBField("syskey", (byte)1));
    ret.getFields().add(new DBField("module", (byte)1));
    ret.getFields().add(new DBField("code", (byte)5));
    return ret;
  }
  
  public static DBRecord defineSysKey(String aTableName)
  {
    DBRecord ret = new DBRecord();
    ret.setTableName(aTableName);
    ret.setFields(new ArrayList<DBField>());
    ret.getFields().add(new DBField("syskey", (byte)1));
    ret.getFields().add(new DBField("module", (byte)1));
    ret.getFields().add(new DBField("code", (byte)5));
    return ret;
  }
}
