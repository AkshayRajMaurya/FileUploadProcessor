package brd_3_13742;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Perform_operations
{
  PreparedStatement ps = null;
  
  public String[] getInitials()
  {
    String[] arr = new String[3];
    Dbconnection n = new Dbconnection();
    Connection con = n.conn();
    String str = "select * from system_parameter_13742 where rownum=1";
    try
    {
      this.ps = con.prepareStatement(str);
      ResultSet rs = this.ps.executeQuery();
      while (rs.next())
      {
        arr[0] = rs.getString(1);
        arr[1] = rs.getString(2);
        arr[2] = rs.getString(3);
      }
      n.disconn();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return arr;
  }
  
  public void do_insert(ArrayList<ProcessRecords> l)
  {
    String str = "insert into customer_master_13742 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    Dbconnection n = new Dbconnection();
    
    Connection con = n.conn();
    for (int i = 0; i < l.size(); i++)
    {
      ProcessRecords p = (ProcessRecords)l.get(i);
      try
      {
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        this.ps = con.prepareStatement(str);
        this.ps.setInt(1, i + 1);
        this.ps.setString(2, p.Customer_Code);
        this.ps.setString(3, p.Customer_Name);
        this.ps.setString(4, p.Customer_Address_1);
        this.ps.setString(5, p.Customer_Address_2);
        this.ps.setInt(6, p.Customer_Pin_Code);
        this.ps.setString(7, p.Email_address);
        this.ps.setLong(8, p.Contact_Number);
        this.ps.setString(9, p.Primary_Contact_Person);
        this.ps.setString(10, p.Record_Status);
        this.ps.setString(11, p.Active_Inactive_Flag);
        try
        {
          java.util.Date daa = df.parse(p.Create_Date);
          java.sql.Date sqlDate = new java.sql.Date(daa.getTime());
          this.ps.setDate(12, sqlDate);
        }
        catch (ParseException e1)
        {
          e1.printStackTrace();
        }
        this.ps.setString(13, p.Created_By);
        try
        {
          java.util.Date daa = df.parse(p.Modified_Date);
          java.sql.Date sqlDate = new java.sql.Date(daa.getTime());
          this.ps.setDate(14, sqlDate);
        }
        catch (ParseException e1)
        {
          this.ps.setNull(14, 91);
        }
        catch (Exception e1)
        {
          this.ps.setNull(14, 91);
        }
        this.ps.setString(15, p.Modified_By);
        try
        {
          java.util.Date daa = df.parse(p.Authorized_Date);
          java.sql.Date sqlDate = new java.sql.Date(daa.getTime());
          this.ps.setDate(16, sqlDate);
        }
        catch (ParseException e1)
        {
          this.ps.setNull(16, 91);
        }
        catch (Exception e1)
        {
          this.ps.setNull(16, 91);
        }
        this.ps.setString(17, p.Authorized_By);
        
        this.ps.executeUpdate();
        System.out.println("Data Insertion Successful");
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
}
