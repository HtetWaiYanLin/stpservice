package com.team24.stp.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMgr
{
  public static String insertString(DBRecord aTable, Connection aConnection)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Insert into [");
    sb.append(aTable.getTableName());
    sb.append("] (");
    for (int i = 0; i < aTable.getFields().size(); i++)
    {
      if (i != 0) {
        sb.append(", ");
      }
      sb.append(((DBField)aTable.getFields().get(i)).getFieldName());
    }
    sb.append(") values (");
    for (int i = 0; i < aTable.getFields().size(); i++)
    {
      if (i != 0) {
        sb.append(", ");
      }
      sb.append("?");
    }
    sb.append(")");
    return sb.toString();
  }
  
  public static String deleteString(String aFilter, DBRecord aTable, Connection aConnection)
  {
    StringBuilder sb = new StringBuilder();
    if (aConnection.toString().startsWith("net.sourceforge.jtds.jdbc.")) {
      sb.append("Delete from");
    } else if (aConnection.toString().startsWith("sun.jdbc.odbc.")) {
      sb.append("Delete * from ");
    } else {
      sb.append("Delete * from ");
    }
    sb.append("[");
    sb.append(aTable.getTableName());
    sb.append("]");
    sb.append(" ");
    sb.append(filter(aFilter, aConnection));
    return sb.toString();
  }
  
  public static String updateString(String aFilter, DBRecord aTable, Connection aConnection)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Update [");
    sb.append(aTable.getTableName());
    sb.append("] set ");
    for (int i = 0; i < aTable.getFields().size(); i++)
    {
      if (i != 0) {
        sb.append(" , ");
      }
      sb.append(((DBField)aTable.getFields().get(i)).getFieldName());
      sb.append("=? ");
    }
    sb.append(" ");
    sb.append(filter(aFilter, aConnection));
    return sb.toString();
  }
  
  public static String updateString(String[] aFieldsName, String aFilter, DBRecord aTable, Connection aConnection)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("Update [");
    sb.append(aTable.getTableName());
    sb.append("] set ");
    int size = aFieldsName.length;
    for (int i = 0; i < size; i++)
    {
      if (i != 0) {
        sb.append(" , ");
      }
      sb.append(aFieldsName[i]);
      sb.append("=? ");
    }
    sb.append(" ");
    sb.append(filter(aFilter, aConnection));
    return sb.toString();
  }
  
  public static void setValues(PreparedStatement stmt, DBRecord aTable)
    throws SQLException
  {
    for (int i = 0; i < aTable.getFields().size(); i++) {
      if (((DBField)aTable.getFields().get(i)).getFieldType() == 1) {
        stmt.setInt(i + 1, ((DBField)aTable.getFields().get(i)).getIntValue());
      } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 2) {
        try
        {
          stmt.setLong(i + 1, ((DBField)aTable.getFields().get(i)).getLongValue());
        }
        catch (SQLException e)
        {
          if (e.getErrorCode() == 106)
          {
            stmt.setInt(i + 1, ((DBField)aTable.getFields().get(i)).getIntValue());
            System.out.println(" Forced Int Casting @ DBMgr.setValues(): " + e.getMessage()); continue;
          }
          throw e;
        }
      } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 3) {
        stmt.setDouble(i + 1, ((DBField)aTable.getFields().get(i)).getDoubleValue());
      } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 5) {
        stmt.setString(i + 1, ((DBField)aTable.getFields().get(i)).getSvalue());
      }else if (((DBField)aTable.getFields().get(i)).getFieldType() == 6) {
          stmt.setDate(i + 1, ((DBField)aTable.getFields().get(i)).getDateValue());
          System.out.println("I am Date  : " +((DBField)aTable.getFields().get(i)).getDateValue());
        }
    }
  }
  
  public static void setValues(String[] aFields, PreparedStatement stmt, DBRecord aTable)
    throws SQLException
  {
    int size = aFields.length;
    for (int j = 0; j < size; j++)
    {
      String fieldName = aFields[j];
      for (int i = 0; i < aTable.getFields().size(); i++)
      {
        String fieldNameFromTable = ((DBField)aTable.getFields().get(i)).getFieldName();
        if (fieldName.equalsIgnoreCase(fieldNameFromTable)) {
          if (((DBField)aTable.getFields().get(i)).getFieldType() == 1) {
            stmt.setInt(i + 1, ((DBField)aTable.getFields().get(i)).getIntValue());
          } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 2) {
            try
            {
              stmt.setLong(i + 1, ((DBField)aTable.getFields().get(i)).getLongValue());
            }
            catch (SQLException e)
            {
              if (e.getErrorCode() == 106)
              {
                stmt.setInt(i + 1, ((DBField)aTable.getFields().get(i)).getIntValue());
                System.out.println(" Forced Int Casting @ DBMgr.setValues(): " + e.getMessage()); continue;
              }
              throw e;
            }
          } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 3) {
            stmt.setDouble(i + 1, ((DBField)aTable.getFields().get(i)).getDoubleValue());
          } else if (((DBField)aTable.getFields().get(i)).getFieldType() == 5) {
            stmt.setString(i + 1, ((DBField)aTable.getFields().get(i)).getSvalue());
          }
        }
      }
    }
  }
  
  public static DBRecord readDBRecord(DBRecord aDBRecord, ResultSet aResultSet)
    throws SQLException
  {
    DBRecord ret = new DBRecord(aDBRecord);
    for (int i = 0; i < aDBRecord.getFields().size(); i++) {
      if (((DBField)aDBRecord.getFields().get(i)).getFieldType() == 1) {
        ret.setValue(((DBField)aDBRecord.getFields().get(i)).getFieldName(), aResultSet.getInt(((DBField)aDBRecord.getFields().get(i)).getFieldName()));
      } else if (((DBField)aDBRecord.getFields().get(i)).getFieldType() == 2) {
        ret.setValue(((DBField)aDBRecord.getFields().get(i)).getFieldName(), aResultSet.getLong(((DBField)aDBRecord.getFields().get(i)).getFieldName()));
      } else if (((DBField)aDBRecord.getFields().get(i)).getFieldType() == 3) {
        ret.setValue(((DBField)aDBRecord.getFields().get(i)).getFieldName(), aResultSet.getDouble(((DBField)aDBRecord.getFields().get(i)).getFieldName()));
      } else if (((DBField)aDBRecord.getFields().get(i)).getFieldType() == 5) {
        ret.setValue(((DBField)aDBRecord.getFields().get(i)).getFieldName(), aResultSet.getString(((DBField)aDBRecord.getFields().get(i)).getFieldName()));
      } else if (((DBField)aDBRecord.getFields().get(i)).getFieldType() == 6) {
          ret.setValue(((DBField)aDBRecord.getFields().get(i)).getFieldName(), aResultSet.getDate(((DBField)aDBRecord.getFields().get(i)).getFieldName()));
        }
    }
    return ret;
  }
  
  public static ResultSet getResultSet(DBRecord aDBRecord, Connection aConnection)
    throws SQLException
  {
    return getResultSet(aDBRecord, "", "", aConnection);
  }
  
  public static ResultSet getResultSet(DBRecord aDBRecord, String aFilter, String aOrder, Connection aConnection)
    throws SQLException
  {
    Statement stmt = aConnection.createStatement();
    String sql = "select * from " + aDBRecord.getTableName() + " " + aFilter + " " + aOrder;
    ResultSet ret = stmt.executeQuery(sql);
    return ret;
  }
  
  public static ResultSet getResultSetWithSandE(DBRecord aDBRecord, String aFilter, String aOrder, int start, int end, int queryTimeoutSecs, Connection aConnection)
    throws SQLException
  {
    Statement stmt = aConnection.createStatement();
    String sql = " select * from ( select ROW_NUMBER() OVER ( " + aOrder + " ) AS RowNum,* from " + aDBRecord.getTableName() + aFilter + ") as RowConstrainedResult ";
    if ((start == 0) && (end == 0)) {
      sql = "";
    } else {
      sql = sql + " where RowNum > " + start + " and RowNum <= " + end;
    }
    if (queryTimeoutSecs != 0) {
      stmt.setQueryTimeout(queryTimeoutSecs);
    }
    ResultSet ret = stmt.executeQuery(sql);
    return ret;
  }
  
  public static ArrayList<DBRecord> getDBRecords(DBRecord aDBRecord, Connection aConnection)
    throws SQLException
  {
    return getDBRecords(aDBRecord, "", "", aConnection);
  }
  
  public static ArrayList<DBRecord> getDBRecords(DBRecord aDBRecord, String aFilter, String aOrder, Connection aConnection)
    throws SQLException
  {
    ArrayList<DBRecord> ret = new ArrayList<DBRecord>();
    ResultSet rs = getResultSet(aDBRecord, aFilter, aOrder, aConnection);
    while (rs.next())
    {
      DBRecord dbr = readDBRecord(aDBRecord, rs);
      ret.add(dbr);
    }
    return ret;
  }
  
  public static ArrayList<DBRecord> getDBRecordSandE(DBRecord aDBRecord, String aFilter, String aOrder, int start, int end, int queryTimeoutSecs, Connection aConnection)
    throws SQLException
  {
    ArrayList<DBRecord> ret = new ArrayList<DBRecord>();
    ResultSet rs = getResultSetWithSandE(aDBRecord, aFilter, aOrder, start, end, queryTimeoutSecs, aConnection);
    while (rs.next())
    {
      DBRecord dbr = readDBRecord(aDBRecord, rs);
      ret.add(dbr);
    }
    return ret;
  }
  
  public static String filter(String aFilter, Connection aConnection)
  {
    if (aConnection.toString().startsWith("net.sourceforge.jtds.jdbc.")) {
      return aFilter;
    }
    if (aConnection.toString().startsWith("sun.jdbc.odbc.")) {
      return aFilter;
    }
    return aFilter;
  }
}
