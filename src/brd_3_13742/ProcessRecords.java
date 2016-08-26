package brd_3_13742;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

class ProcessRecords
{
  static File f;
  static FileWriter fw;
  static BufferedWriter bw;
  ArrayList<ProcessRecords> l = new ArrayList();
  static ArrayList<String> d = new ArrayList();
  int Customer_ID;
  int Customer_Pin_Code;
  long Contact_Number;
  String filename1;
  String Customer_Code;
  String Customer_Name;
  String Customer_Address_1;
  String Customer_Address_2;
  String Primary_Contact_Person;
  String Create_Date;
  String Record_Status;
  String Active_Inactive_Flag;
  String Created_By;
  String Modified_By;
  String Authorized_By;
  String Email_address;
  String Modified_Date;
  String Authorized_Date;
  public static int count1 = 0;
  String[] tokens = new String[16];
  
  public boolean check(String filename)
  {
    this.filename1 = filename;
    String file = filename;
    int index = file.lastIndexOf(".");
    String ext = file.substring(index + 1);
    if (ext.equals("txt")) {
      return true;
    }
    return false;
  }
  
  public void readFile(String filepath)
  {
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(filepath));
      f = new File("Error_Log_" + this.filename1 + ".txt");
      try
      {
        f.createNewFile();
        fw = new FileWriter(f);
        bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write("******************************************************************************");
        bw.newLine();
        bw.write("******************************************************************************");
        bw.newLine();
        bw.write("-------------------------------ERROR LOG--------------------------------------");
        bw.newLine();
        bw.write("******************************************************************************");
        bw.newLine();
        bw.write("******************************************************************************");
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.flush();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      try
      {
        String str;
        while ((str = br.readLine()) != null)
        {
          String str;
          String[] tokens = str.split("~");
          try
          {
            validate(tokens);
          }
          catch (ParseException e)
          {
            e.printStackTrace();
          }
        }
        System.out.println(count1);
        print_rec();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      return;
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }
  
  public void validate(String[] s)
    throws ParseException
  {
    ValidateRecords v = new ValidateRecords();
    int count = 0;
    for (int i = 0; i < s.length; i++)
    {
      switch (i)
      {
      case 0: 
        v.checkCustomer_Code(s[0]); break;
      case 1: 
        v.checkCustomer_Name(s[1]); break;
      case 2: 
        v.checkCustomer_Address_1(s[2]); break;
      case 3: 
        v.checkCustomer_Address_2(s[3]); break;
      case 4: 
        v.checkCustomer_Pin_Code(s[4]); break;
      case 5: 
        v.checkEmail_address(s[5]); break;
      case 6: 
        v.checkContact_Number(s[6]); break;
      case 7: 
        v.checkPrimary_Contact_Person(s[7]); break;
      case 8: 
        v.checkRecord_Status(s[8]); break;
      case 9: 
        v.checkActive_Inactive_Flag(s[9]); break;
      case 10: 
        v.checkCreate_Date(s[10]); break;
      case 11: 
        v.checkCreated_By(s[11]); break;
      case 12: 
        v.checkModified_Date(s[12]); break;
      case 13: 
        v.checkModified_By(s[13]); break;
      case 14: 
        v.checkAuthorized_Date(s[14]); break;
      case 15: 
        v.checkAuthorized_By(s[15]);
      }
      count++;
    }
    count1 += 1;
    System.out.print(count1 + "      " + count + "      ");
    System.out.println(ValidateRecords.recordflag);
    if (ValidateRecords.recordflag == 0)
    {
      ProcessRecords p = new ProcessRecords();
      p.Customer_Code = s[0];
      p.Customer_Name = s[1];
      p.Customer_Address_1 = s[2];
      p.Customer_Address_2 = s[3];
      p.Customer_Pin_Code = Integer.parseInt(s[4]);
      p.Email_address = s[5];
      p.Contact_Number = Long.parseLong(s[6]);
      p.Primary_Contact_Person = s[7];
      p.Record_Status = s[8];
      p.Active_Inactive_Flag = s[9];
      p.Create_Date = s[10];
      p.Created_By = s[11];
      if (s.length - 1 > 11)
      {
        p.Modified_Date = s[12];
        if (s.length - 1 > 12)
        {
          p.Modified_By = s[13];
          if (s.length - 1 > 13)
          {
            p.Authorized_Date = s[14];
            if (s.length - 1 > 14) {
              p.Authorized_By = s[15];
            }
          }
        }
      }
      else
      {
        p.Modified_Date = null;
        p.Modified_By = null;
        p.Authorized_Date = null;
        p.Authorized_By = null;
      }
      this.l.add(p);
    }
    else
    {
      try
      {
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.write("------------------------------------------------------------------------------------------------------");
        bw.newLine();
        bw.write("The Following Record Was Diagnosed With The Problems Reported Above = Record Number " + count1);
        bw.newLine();
        bw.write("------------------------------------------------------------------------------------------------------");
        bw.newLine();
        bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      System.out.println("The Following Record Was Diagnosed With The Problems Reported Above:");
      for (int i = 0; i < s.length; i++)
      {
        System.out.print(s[i] + " ");
        try
        {
          bw.write(s[i] + " ");
          bw.flush();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      try
      {
        bw.newLine();
        bw.newLine();
        bw.write("*********************************************************************************************************");
        bw.newLine();
        bw.write("*********************************************************************************************************");
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public String toString()
  {
    return 
    
      "ProcessRecords [Customer_ID=" + this.Customer_ID + ", Customer_Pin_Code=" + this.Customer_Pin_Code + ", Contact_Number=" + this.Contact_Number + ", Customer_Code=" + this.Customer_Code + ", Customer_Name=" + this.Customer_Name + ", Customer_Address_1=" + this.Customer_Address_1 + ", Customer_Address_2=" + this.Customer_Address_2 + ", Primary_Contact_Person=" + this.Primary_Contact_Person + ", Record_Status=" + this.Record_Status + ", Active_Inactive_Flag=" + this.Active_Inactive_Flag + ", Created_By=" + this.Created_By + ", Modified_By=" + this.Modified_By + ", Authorized_By=" + this.Authorized_By + ", Email_address=" + this.Email_address + ", Create_Date=" + this.Create_Date + ", Modified_Date=" + this.Modified_Date + ", Authorized_Date=" + this.Authorized_Date + "]";
  }
  
  public void print_rec()
  {
    int i = 0;
    for (Iterator localIterator = this.l.iterator(); localIterator.hasNext(); i++)
    {
      ProcessRecords p = (ProcessRecords)localIterator.next();
      System.out.println(p);
    }
    System.out.println(i);
    System.out.println(ValidateRecords.fileflag);
    insertRecordsToDB();
  }
  
  public void insertRecordsToDB()
  {
    Perform_operations pf = new Perform_operations();
    if (Validate_File.RejectionLevel.equals("F"))
    {
      if (ValidateRecords.fileflag == 0) {
        pf.do_insert(this.l);
      }
    }
    else {
      pf.do_insert(this.l);
    }
  }
}
