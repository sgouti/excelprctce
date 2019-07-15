package test;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

public class DataDriven {

	public void getdata() throws IOException {
		FileInputStream file = new FileInputStream("Sid.xls");
		HSSFWorkbook Wrkbk = new HSSFWorkbook(file);
		/*
		 * Sheet TestCase = Sheetnme("TestCntrl"); ArrayList HeadrNme =
		 * Headersvlue(TestCase); System.out.println("Column Number " + Hdrcnt(HeadrNme,
		 * "TC Id")); ArrayList Rowvlue = Rwvlue(HeadrNme, TestCase, "Test Desc",
		 * "Status"); for (Object s : Rowvlue) { System.out.println(s);
		 * 
		 * }
		 */
		Wrkbk.close();
	}

	public static void ExecutableTStCntrl(String Shtnme)  {
		try {
			
			Sheet TestCntrlSheet = Sheetnme(Shtnme, "");
			ArrayList<String> ExecutableTstCases = ExecutableTestIDs(TestCntrlSheet, GlobalV.TestCaseID, GlobalV.Run);
			for(int i=0;i<ExecutableTstCases.size();i++)
			{
			int TCROwNmbr=RowNumbr(TestCntrlSheet,GlobalV.TestCaseID,ExecutableTstCases.get(i));
			String Browser= CellData(TestCntrlSheet,TCROwNmbr,GlobalV.Browsers);
			System.out.println("TestIDNames  "+ ExecutableTstCases.get(i));
			ReportLog.RemoveInstance();
			ReportLog.GetInstance(ExecutableTstCases.get(i),"");
			TestScript(GlobalV.TestCaseFilePath,ExecutableTstCases.get(i));
			//Use Testscript method
			AfterSuit();
			}
			
			

		} catch (IOException e) {
			System.out.println("Error is :-" + e);
		}

	}

	public static void TestScript(String ShtNme,String TestCaseID) throws IOException {
		Sheet TestCntrlSheet = Sheetnme(ShtNme, "");
		ArrayList<String> ExecutableFnNme = new ArrayList<String>();
		ArrayList<Integer> RowsNumbrs = ExcutbleRows(TestCntrlSheet, GlobalV.TestCaseID,TestCaseID, GlobalV.Execute);
		System.out.println("Script Rows ID  "+RowsNumbrs);
		
		for(int i=0;i<RowsNumbrs.size();i++)
		{
			ExecutableFnNme.add(CellData(TestCntrlSheet,RowsNumbrs.get(i),GlobalV.FunctionName));
			String FunctionNme=CellData(TestCntrlSheet,RowsNumbrs.get(i),GlobalV.FunctionName);
			System.out.println("Function Nme executing _" + FunctionNme);
			Keyword(GlobalV.KeywordLibraryFilePath,FunctionNme);
		}
	System.out.println("FunctionNames"+ExecutableFnNme);
	
	
	}
	
	public static void Keyword(String ShtNme,String FunctNme) throws IOException
	{
		Sheet KeywrdSheet=Sheetnme(ShtNme, "");
		ArrayList<Integer> RowsNumbrs = ExcutbleRows(KeywrdSheet, GlobalV.FunctionName,FunctNme, GlobalV.Execute);
		System.out.println("Keywordscripts No.-"+RowsNumbrs);
		
		for(int i=0;i<RowsNumbrs.size();i++)
		{
			String Description=CellData(KeywrdSheet,RowsNumbrs.get(i),GlobalV.StepDescription);
			String ActionKyrd=CellData(KeywrdSheet,RowsNumbrs.get(i),GlobalV.ActionOrKeyword);
			String LogicalNme=CellData(KeywrdSheet,RowsNumbrs.get(i),GlobalV.ObjectLogicalName);
			ReportLog.GetInstance().log(LogStatus.INFO,ActionKyrd,Description);
			
			
		System.out.println((i+1)+" )  "+Description+" :: "+ActionKyrd+" :: " + LogicalNme);
		}
		
	
	}
	public static void ExecutFnctn(String FunctNme,String Para) {
		try {
			Test_class cls=new Test_class();
			
			Method m;
			Object Execute;
			if(Para.isEmpty())
			{
				m= cls.getClass().getDeclaredMethod(FunctNme, null);  
				Execute=m.invoke(cls, null);
			
			}else
			{
				m= cls.getClass().getDeclaredMethod(FunctNme, String.class);
				Execute=m.invoke(cls, Para);
			}
		}
		catch(Exception e)
		{
			System.out.println("Erros are  "+e);
		}
		}
	
	
	
public static String[] paramdata(String Kywrdshnme,String TCSrptShnme,int KywrdRW,int TCsrptRw) throws IOException
{
	Sheet KywrdShnme= Sheetnme(Kywrdshnme, "");
	Sheet TCscrptShnme= Sheetnme(TCSrptShnme, "");
	String para="#";
	int cnt=0;
	String[] temp;
	String Kywrdparavlue=null;
	Row row= KywrdShnme.getRow(KywrdRW);
	for(int i=0;i<=9;i++)
	{
		String parav="Parameter"+(i+1);
		Kywrdparavlue=CellData(KywrdShnme,KywrdRW,parav);
		if(!Kywrdparavlue.isEmpty()&&Kywrdparavlue!=null)
		{
		String TcscrptParaVlue=CellData(TCscrptShnme,TCsrptRw,Kywrdparavlue);
		System.out.println(TcscrptParaVlue);
		if(!TcscrptParaVlue.isEmpty()&&TcscrptParaVlue!=null)
		{
		para=para+TcscrptParaVlue+"~";
		}
		}
	}
	temp=para.replaceAll("#","").split("~");
	return temp;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void AfterSuit() {
		ReportLog.Instance().endTest(ReportLog.GetInstance());
		ReportLog.Instance().flush();
	}

	
	// Taking sheetname from excel
	public static Sheet Sheetnme(String Sheetname, String FilePath) {
		try {
			FileInputStream file = new FileInputStream("Sid.xls");
			HSSFWorkbook Wrkbk = new HSSFWorkbook(file);
			Sheet sheetName = null;
			int TotlShts = Wrkbk.getNumberOfSheets();
			int i = 0;
			while (i < TotlShts) {
				if (Wrkbk.getSheetAt(i).getSheetName().equalsIgnoreCase(Sheetname)) {
					sheetName = Wrkbk.getSheetAt(i);
					break;
				}
				i++;
			}
			Wrkbk.close();
			return sheetName;
		} catch (IOException e) {
			System.out.println("Failed get sheetNaame from given filepath: " + e);
			return null;
		}
	}

	// Taking all headersName
	public static ArrayList<String> Headersvlue(Sheet Shtnme) {
		Iterator<Row> Row = Shtnme.iterator();
		Row FirstRw = Row.next();
		ArrayList<String> HdrNme = new ArrayList<>();
		Iterator<Cell> CellV = FirstRw.iterator();
		while (CellV.hasNext()) {
			Cell clv = CellV.next();
			if (clv.getCellType() == CellType.STRING) {
				String CV = clv.getStringCellValue();
				HdrNme.add(CV);
			} else {
				HdrNme.add(NumberToTextConverter.toText(clv.getNumericCellValue()));
			}

		}
	//	System.out.println(HdrNme);
		return HdrNme;

	}

	// Taking selected HeaderClm Number
	public static int Hdrcnt(Sheet Sheetname, String Hdrnme) throws IOException {
		ArrayList<String> HeadrNme = Headersvlue(Sheetname);
		int HdrcntN = 0;
		for (int i = 0; i < HeadrNme.size(); i++) {
			if (HeadrNme.get(i).toString().equalsIgnoreCase(Hdrnme)) {
				break;

			}
			HdrcntN++;
		}

		return HdrcntN;

	}

	// Taking All Executable Testcases
	public static ArrayList<String> ExecutableTestIDs(Sheet SheetName, String TCIDHdr, String ExecuteHdr) throws IOException {
	
		Iterator<Row> Row = SheetName.iterator();
		ArrayList<String> RowVlue = new ArrayList<String>();
		int Hdr = Hdrcnt(SheetName, TCIDHdr);
		int StatusNmr = Hdrcnt(SheetName, ExecuteHdr);
	//	System.out.println("Status Header Number:-" + StatusNmr);
		while (Row.hasNext()) {
			Row Row1 = Row.next();
			Demo: {
				while (Row1.getCell(Hdr).getStringCellValue().equalsIgnoreCase(TCIDHdr)) {
					break Demo;
				}

				if (Row1.getCell(Hdr).getCellType() == CellType.STRING
						& (Row1.getCell(StatusNmr).toString().equalsIgnoreCase("Yes")
								|| Row1.getCell(StatusNmr).toString().equalsIgnoreCase("Ys"))) {
					RowVlue.add(Row1.getCell(Hdr).getStringCellValue());
				} else if ((Row1.getCell(StatusNmr).toString().equalsIgnoreCase("Yes")
						|| Row1.getCell(StatusNmr).toString().equalsIgnoreCase("Ys"))) {
					RowVlue.add(NumberToTextConverter.toText(Row1.getCell(Hdr).getNumericCellValue()));
				}

			}

		}
		return RowVlue;

	}

	// Taking All selected Rows Number for Executing
	public static ArrayList<Integer> ExcutbleRows(Sheet Shtnme, String Clmnme, String CellValue,String Excute) throws IOException {
		// Sheet ActiveSheet = Sheetnme(Shtnme);
		Iterator<Row> Row = Shtnme.iterator();
		int Hdr = Hdrcnt(Shtnme, Clmnme);
		int StatusHDr = Hdrcnt(Shtnme, Excute);
		ArrayList<Integer> RowNumbr = new ArrayList<>();
		while (Row.hasNext()) {
			Row Row1 = Row.next();

			if (Row1.getCell(Hdr).toString().equalsIgnoreCase(CellValue)
					& (Row1.getCell(StatusHDr).toString().equalsIgnoreCase("YS"))
					|| Row1.getCell(StatusHDr).toString().equalsIgnoreCase("Yes")) {
				RowNumbr.add(Row1.getRowNum());

			}
		}
	
	return RowNumbr;

	}

	// Taking only one Row Number for executing
	public static int RowNumbr(Sheet Shtnme, String Clmnme, String CellValue) throws IOException {
		Iterator<Row> Row = Shtnme.iterator();
		int Hdr = Hdrcnt(Shtnme, Clmnme);

		int RowNumbr = 0;
		while (Row.hasNext()) {
			Row Row1 = Row.next();

			if (Row1.getCell(Hdr).toString().equalsIgnoreCase(CellValue)) {
				RowNumbr = Row1.getRowNum();
				break;

			}

		}
		return RowNumbr;
	}

	// Taking All values from Selected Row
	public static ArrayList<String> RowValues(Sheet shtNme, int RownNumbr) throws IOException {
		// Sheet ActiveSheet = Sheetnme(shtNme);
		ArrayList<String> RowValue = new ArrayList<String>();
		Row RowNmbr = shtNme.getRow(RownNumbr);
		Iterator<Cell> CellV = RowNmbr.iterator();
		while (CellV.hasNext()) {
			Cell clv = CellV.next();
			if (clv.getCellType() == CellType.STRING) {
				RowValue.add(clv.getStringCellValue());
			} else {
				RowValue.add(NumberToTextConverter.toText(clv.getNumericCellValue()));
			}
		}
		return RowValue;

	}
	
	public static String CellData(Sheet shtNme, int RownNumbr,String ColumnNme) throws IOException {
		
		int HeadrsNmr = Hdrcnt(shtNme,ColumnNme);
		Row Rowvlue=shtNme.getRow(RownNumbr);
		String Datavlue=null;
		if(Rowvlue.getCell(HeadrsNmr)!=null)
		{
			 Datavlue=Rowvlue.getCell(HeadrsNmr).getStringCellValue();
			return Datavlue;
			
		}
		else
		{
			return "";
			
		}
		
	}

}
