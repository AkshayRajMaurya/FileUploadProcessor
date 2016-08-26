package brd_3_13742;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Dbconnection
{
  Connection con;
  
  public Connection conn()
  {
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    try
    {
      this.con = DriverManager.getConnection("jdbc:Oracle:thin:@finn:1521:orcl", "hr", "hr");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return this.con;
  }
  
  public void disconn()
  {
    try
    {
      this.con.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
