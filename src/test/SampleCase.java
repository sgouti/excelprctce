package test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SampleCase {
	public static String TestCntrlSheet = "TestCntrl";
	public static String TestCaseSheet = "TestCase";
	public static String KeywordSheet = "Keyword";
	public static String ObjectLbSheet = "ObjectLb";
	public static String TestdataSheet = "Testdata";

	public static String ExecuteHdr = "Execute";
	public static String RunHdr = "Run";
	public static String TestCase_FK = "TestCaseID";
	public static String Keyword_FK = "FunctionName";
	public static String Object_FK = "LogicalName";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<LinkedHashMap<String, String>> Listof_Tests=KeyWordDataDriven.TestController();
		ArrayList<LinkedHashMap<String, String>> Listof_ExceTests=KeyWordDataDriven.ExecutableList(Listof_Tests, RunHdr, "YES");
		for(LinkedHashMap<String, String> TC:Listof_ExceTests)
		{
			System.out.println(TC.get(TestCase_FK));
			
			try {
				ArrayList<LinkedHashMap<String, String>> ListofSteps_Testcase=KeyWordDataDriven.ArrayofDataList(TestCaseSheet, TestCase_FK, TC.get(TestCase_FK));
				ArrayList<LinkedHashMap<String, String>> ListofSteps_ExceTestcase=KeyWordDataDriven.ExecutableList(ListofSteps_Testcase, ExecuteHdr, "YS");
				for(LinkedHashMap<String, String> F_Name:ListofSteps_ExceTestcase)
				{
					System.out.println(">>>>>>>>>>"+F_Name.get(Keyword_FK));
					ArrayList<LinkedHashMap<String, String>> ListofSteps_FunctName=KeyWordDataDriven.ArrayofDataList(KeywordSheet, Keyword_FK, F_Name.get(Keyword_FK));
					ArrayList<LinkedHashMap<String, String>> ListofSteps_ExceFunctName=KeyWordDataDriven.ExecutableList(ListofSteps_FunctName, ExecuteHdr, "YS");
					for(LinkedHashMap<String, String> KeyWord_Name:ListofSteps_ExceFunctName)
					{
						ArrayList<LinkedHashMap<String, String>> ListofTestData=KeyWordDataDriven.ArrayofDataList(TestdataSheet, TestCase_FK, TC.get(TestCase_FK));
						System.out.println("*******************************"+KeyWord_Name.get("ActionOrKeyword")+"#############"+KeyWord_Name.get("ObjectLogicalName"));
						System.out.println(ListofTestData.get(0));
						
					}
					
				}
			}
			catch(Exception e)
			{
				
			}
		}
	}

}
