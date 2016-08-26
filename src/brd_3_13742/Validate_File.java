package brd_3_13742;

import java.io.PrintStream;

public class Validate_File
{
  static String RejectionLevel;
  
  public static void main(String[] args)
  {
    String[] arr = new String[3];
    ProcessRecords r = new ProcessRecords();
    Perform_operations po = new Perform_operations();
    arr = po.getInitials();
    String filepath = arr[0];
    String filename = arr[1];
    RejectionLevel = arr[2];
    String file = filepath + "\\" + filename;
    if (r.check(filename)) {
      r.readFile(file);
    } else {
      System.out.println("The Provided File Is Invalid");
    }
  }
}
