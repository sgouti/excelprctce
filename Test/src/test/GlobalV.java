package test;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;

public class GlobalV {
	
	public static String TCDesc="TCDesc";
	public static String Status;
	public static String Browser;
	public static int ExcutableRWNmbr;
	public static ArrayList<String> ExecutableRowValue=new ArrayList<String>();
	public static int TcsrtRw=0;
	public static int KywrdtRw=0;
	
	
		//HeadersNme
	public static String TCID="TCID";
	public static String TestCaseID="TestCaseID";
	public static String TestScriptName="TestScriptName"	;
	public static String  TestScriptDescription="TestScriptDescription"	;
	public static String Run="Run";	
	public static String Iterations="Iterations";
	public static String Browsers="Browsers";
	
	public static String FunctionDescription="FunctionDescription"	;
	public static String FunctionName="FunctionName";
	public static String Execute="Execute";
	
	public static String StepDescription="StepDescription";
	public static String ActionOrKeyword="ActionOrKeyword"	;
	public static String ObjectLogicalName="ObjectLogicalName";
	
	public static String LogicalName="LogicalName";	
	public static String FindMethod	="FindMethod";
	public static String XpathQuery_="XpathQuery_";
	public static String PropertyName="PropertyName";
	
	 public static String ControllerFilePath;
     public static String TestDataFilePath;
     public static String TestCaseFilePath="TestCase";
     public static String KeywordLibraryFilePath="Keyword";
     public static String ObjectRepositoryFilePath="ObjectLb";


	
}
