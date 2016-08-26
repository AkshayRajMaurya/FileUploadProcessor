package brd_3_13742;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class ValidateRecords
{
  static int recordflag = 0;
  static int fileflag = 0;
  
  ValidateRecords()
  {
    recordflag = 0;
  }
  
  public boolean CheckFieldLength(String str, int len, String str1)
  {
    if (str.length() > len)
    {
      fileflag = 1;
      recordflag = 1;
      try
      {
        ProcessRecords.bw.write("The Length of " + str1 + " field is not Appropriate in " + str + " ...(MAX LENGTH : " + len + " )");
        ProcessRecords.bw.newLine();
        ProcessRecords.bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      System.out.println("The Length of " + str1 + " field is not Appropriate... in " + str + " (MAX LENGTH : " + len + " )");
      return false;
    }
    return true;
  }
  
  public boolean CheckDataType(String str, String datatype, String str1)
  {
    if (datatype.equals("Number")) {
      try
      {
        Long.parseLong(str);
        return true;
      }
      catch (Exception e)
      {
        try
        {
          ProcessRecords.bw.write("The datatype of " + str1 + " field is not appropriate in " + str);
          ProcessRecords.bw.newLine();
          ProcessRecords.bw.flush();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
        System.out.println("The datatype of " + str1 + " field is not appropriate in  " + str);
        fileflag = 1;
        recordflag = 1;
        return false;
      }
    }
    if (datatype.equals("Date"))
    {
      SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
      try
      {
        df.parse(str);
        return true;
      }
      catch (Exception e)
      {
        try
        {
          ProcessRecords.bw.flush();
          ProcessRecords.bw.write("The datatype of " + str1 + " field is not appropriate in " + str);
          ProcessRecords.bw.newLine();
          ProcessRecords.bw.flush();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
        System.out.println("The datatype of " + str1 + " field is not appropriate in " + str);
        fileflag = 1;
        recordflag = 1;
        return false;
      }
    }
    if (datatype.equals("AlphaNumeric"))
    {
      String pattern = "[A-Za-z0-9\\s]*";
      if (str.matches(pattern)) {
        return true;
      }
      try
      {
        ProcessRecords.bw.flush();
        ProcessRecords.bw.write("The datatype of " + str1 + " field is not appropriate in " + str);
        ProcessRecords.bw.newLine();
        ProcessRecords.bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      System.out.println("The datatype of " + str1 + " field is not appropriate in " + str);
      fileflag = 1;
      recordflag = 1;
      return false;
    }
    try
    {
      ProcessRecords.bw.flush();
      ProcessRecords.bw.write("The datatype of " + str1 + " field is not appropriate in " + str);
      ProcessRecords.bw.newLine();
      ProcessRecords.bw.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("The datatype of " + str1 + " field is not appropriate in " + str);fileflag = 1;
    recordflag = 1;
    return false;
  }
  
  public boolean Mandatory_NonMandatory(String str, String str1)
  {
    if ((str == null) || (str.isEmpty()))
    {
      try
      {
        ProcessRecords.bw.flush();
        ProcessRecords.bw.write(str1 + " Is a Mandatory Field");
        ProcessRecords.bw.newLine();
        ProcessRecords.bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      System.out.println(str1 + " Is a Mandatory Field");
      fileflag = 1;
      recordflag = 1;
      return false;
    }
    return true;
  }
  
  public boolean checkRecordStatus(String str)
  {
    str = str.trim();
    if ((str.equals("N")) || (str.equals("M")) || (str.equals("D")) || (str.equals("A")) || (str.equals("R"))) {
      return true;
    }
    try
    {
      ProcessRecords.bw.write("Recordstatus Value is not Valid For " + str + " record");
      ProcessRecords.bw.newLine();
      ProcessRecords.bw.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("Recordstatus Value is not Valid For " + str + " record");
    fileflag = 1;
    recordflag = 1;
    return false;
  }
  
  public boolean checkActiveInactiveFlag(String str)
  {
    str = str.trim();
    if ((str.equals("A")) || (str.equals("I"))) {
      return true;
    }
    try
    {
      ProcessRecords.bw.flush();
      ProcessRecords.bw.write("Active Inactive Flag Must Be Filled With A or I for " + str);
      ProcessRecords.bw.newLine();
      ProcessRecords.bw.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("Active Inactive Flag Must Be Filled With A or I for " + str);
    fileflag = 1;
    recordflag = 1;
    return false;
  }
  
  public boolean checkEmail(String str)
  {
    String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    if (str.matches(pattern)) {
      return true;
    }
    try
    {
      ProcessRecords.bw.flush();
      ProcessRecords.bw.write("Email Address Format Is Invalid For " + str + " Record");
      ProcessRecords.bw.newLine();
      ProcessRecords.bw.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("Email Address Format Is Invalid For " + str + " Record");
    fileflag = 1;
    recordflag = 1;
    return false;
  }
  
  public void checkCustomer_Code(String str)
  {
    for (int i = 0; i < ProcessRecords.d.size(); i++)
    {
      String b = (String)ProcessRecords.d.get(i);
      if (b.equals(str))
      {
        fileflag = 1;
        recordflag = 1;
        try
        {
          ProcessRecords.bw.write("The " + str + " record already exists for customer code field");
          ProcessRecords.bw.newLine();
          ProcessRecords.bw.flush();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    ProcessRecords.d.add(str);
    CheckFieldLength(str, 10, "Customer Code");
    CheckDataType(str, "AlphaNumeric", "Customer Code");
    Mandatory_NonMandatory(str, "Customer Code");
  }
  
  public void checkCustomer_Name(String str)
  {
    CheckFieldLength(str, 30, "Customer Name");
    CheckDataType(str, "AlphaNumeric", "Customer Name");
    Mandatory_NonMandatory(str, "Customer Name");
  }
  
  public void checkCustomer_Address_1(String str)
  {
    CheckFieldLength(str, 100, "Customer Address Line 1");
    
    Mandatory_NonMandatory(str, "Customer Address Line 1");
  }
  
  public void checkCustomer_Address_2(String str)
  {
    CheckFieldLength(str, 100, "Customer Address Line 2");
  }
  
  public void checkCustomer_Pin_Code(String str)
  {
    if (str.length() == 6)
    {
      CheckDataType(str, "Number", "PinCode");
      Mandatory_NonMandatory(str, "PinCode");
    }
    else
    {
      fileflag = 1;
      recordflag = 1;
      try
      {
        ProcessRecords.bw.flush();
        ProcessRecords.bw.write("The Length of PINCODE field is not Appropriate for " + str + "...(Req.LENGTH :6)");
        ProcessRecords.bw.newLine();
        ProcessRecords.bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      System.out.println("The Length of PINCODE field is not Appropriate for " + str + "...(Req.LENGTH :6)");
    }
  }
  
  public void checkEmail_address(String str)
  {
    CheckFieldLength(str, 100, "Email Address");
    checkEmail(str);
    Mandatory_NonMandatory(str, "Email Address");
  }
  
  public void checkContact_Number(String str)
  {
    CheckFieldLength(str, 20, "Contact_Number");
    CheckDataType(str, "Number", "Contact_Number");
  }
  
  public void checkPrimary_Contact_Person(String str)
  {
    CheckFieldLength(str, 100, "Primary_Contact_Person");
    CheckDataType(str, "AlphaNumeric", "Primary_Contact_Person");
    Mandatory_NonMandatory(str, "Primary_Contact_Person");
  }
  
  public void checkRecord_Status(String str)
  {
    CheckFieldLength(str, 1, "Record_Status");
    CheckDataType(str, "AlphaNumeric", "Record_Status");
    Mandatory_NonMandatory(str, "Record_Status");
    checkRecordStatus(str);
  }
  
  public void checkActive_Inactive_Flag(String str)
  {
    CheckFieldLength(str, 1, "Active_Inactive_Flag");
    CheckDataType(str, "AlphaNumeric", "Active_Inactive_Flag");
    Mandatory_NonMandatory(str, "Active_Inactive_Flag");
    checkActiveInactiveFlag(str);
  }
  
  public void checkCreate_Date(String str)
  {
    CheckDataType(str, "Date", "Create_Date");
    Mandatory_NonMandatory(str, "Create_Date");
  }
  
  public void checkCreated_By(String str)
  {
    CheckFieldLength(str, 30, "Created_By");
    CheckDataType(str, "AlphaNumeric", "Created_By");
    Mandatory_NonMandatory(str, "Created_By");
  }
  
  public void checkModified_Date(String str)
  {
    SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
    try
    {
      df.parse(str);
    }
    catch (Exception e)
    {
      try
      {
        ProcessRecords.bw.flush();
        
        ProcessRecords.bw.flush();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      System.out.println("The date format for Modified_Date field is not appropriate");
    }
  }
  
  public void checkModified_By(String str)
  {
    CheckFieldLength(str, 30, "Modified_By");
    CheckDataType(str, "AlphaNumeric", "Modified_By");
  }
  
  public void checkAuthorized_Date(String str)
  {
    SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
    try
    {
      df.parse(str);
    }
    catch (Exception e)
    {
      try
      {
        ProcessRecords.bw.flush();
        ProcessRecords.bw.write("The date format for Authorized Date field is not appropriate");
        ProcessRecords.bw.newLine();
        ProcessRecords.bw.flush();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      System.out.println("The date format for Authorized Date field is not appropriate");
    }
  }
  
  public void checkAuthorized_By(String str)
  {
    CheckFieldLength(str, 30, "Authorized_By");
    CheckDataType(str, "AlphaNumeric", "Authorized_By");
  }
}
