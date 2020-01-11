package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Exa extends tem{

	public static void main(String[] args) 
	{
		Exa a = new Exa();
		a.mm();
		System.out.println("na");
		
		String s="maths asd";
		String[] a1= s.split("a");
		for(int i=0;i<a1.length;i++)
		System.out.println(a1[i]);
		
	System.out.println(s.charAt(3)+ s.concat("ok"));
	System.out.println(s.contains("m"));
	}
	
 	
}

 abstract class tems
{
	 abstract void mm();
	
}
  class tem extends tems
 {
	final  public  void mm() {
			System.out.println("ok");	// TODO Auto-generated method stub
				
			} 
 }