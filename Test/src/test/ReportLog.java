package test;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportLog {
	public static ExtentTest Test=null;
	public static ExtentReports _instance=null;
	public static ExtentReports Instance() 
	{	if(_instance==null)
	{
		 _instance=new ExtentReports("HFmrkReport22.html");
		return _instance;
	}
	return _instance;
	}
	
	public static ExtentTest GetInstance(String Arg1,String Arg2)
	{
		if(Test==null)
		{
			Test=ReportLog.Instance().startTest(Arg1,Arg2);
			return Test;
		}
			
		return Test;	
		
	}
	public static ExtentTest GetInstance()
     {
         if (Test == null)
         {
             throw new InvalidOperationException("Singleton not created - use GetInstance(arg1, arg2)");
         }
         return Test;
     }
	public static void RemoveInstance()
	{
		if(Test!=null)
			Test=null;
		
	}
}
