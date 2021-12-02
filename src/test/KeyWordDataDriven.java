package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class KeyWordDataDriven {

	public static String ResourceFile = "Sid.xls";
	public static String TestCntrlSheet = "TestCntrl";
	public static String TestCaseSheet = "TestCase";
	public static String KeywordSheet = "Keyword";
	public static String ObjectLbSheet = "ObjectLb";
	public static String TestdataSheet = "Testdata";

	public static String ExecuteStatus = "Run";
	public static String TestCase_FK = "TestCaseID";
	public static String Keyword_FK = "FunctionName";
	public static String Object_FK = "LogicalName";

	public static Workbook workbook() {
		Workbook wb = null;
		try {
			wb = Workbook.getWorkbook(new File(ResourceFile));
		} catch (Exception e) {
			System.out.println(e);
		}
		return wb;
	}

	public static Sheet GetSheet(String SheetName) {
		return workbook().getSheet(SheetName);
	}

	public static ArrayList<LinkedHashMap<String, String>> TestController() {
		ArrayList<LinkedHashMap<String, String>> TestCntlr = new ArrayList<>();
		LinkedHashMap<String, String> TCRows;
		Sheet TCC = GetSheet(TestCntrlSheet);
		Cell[] HdrRows = TCC.getRow(0);
		for (int i = 1; i < TCC.getRows() - 1; i++) {
			Cell[] RowCells = TCC.getRow(i);
			TCRows = new LinkedHashMap<>();
			SKIP: for (int j = 0; j < HdrRows.length - 1; j++) {

				TCRows.put(HdrRows[j].getContents(), RowCells[j].getContents());
				continue SKIP;
			}
			TestCntlr.add(TCRows);
		}
		return TestCntlr;
	}

	public static ArrayList<LinkedHashMap<String, String>> ExecutableList(
			ArrayList<LinkedHashMap<String, String>> ArrayofList, String Key, String value) {
		ArrayList<LinkedHashMap<String, String>> ExecutableList = new ArrayList<>();
		Iterator<LinkedHashMap<String, String>> ItrTC = ArrayofList.iterator();
		while (ItrTC.hasNext()) {
			LinkedHashMap<String, String> TC = ItrTC.next();
			if (TC.get(Key).equalsIgnoreCase(value))
				ExecutableList.add(TC);
		}
		return ExecutableList;
	}

	public static ArrayList<LinkedHashMap<String, String>> ArrayofDataList(String SheetName, String FGKey,
			String Value) {
		ArrayList<LinkedHashMap<String, String>> ArrayofDataList = new ArrayList<>();
		LinkedHashMap<String, String> TCRows;
		Sheet sheet = GetSheet(SheetName);
		Cell[] HdrRows = sheet.getRow(0);
		Integer nmbr=GetColumnNmbr(sheet,FGKey);
		System.out.println("Header Column Number is:"+nmbr);
		for (int i = 1; i < sheet.getRows() - 1; i++) {
			Cell[] RowCells = sheet.getRow(i);
			
			int MinClmn = (RowCells.length > HdrRows.length) ? HdrRows.length : RowCells.length;
			TCRows = new LinkedHashMap<>();
			if (RowCells[nmbr].getContents().equalsIgnoreCase(Value)) {
				SKIP: for (int j = 0; j < MinClmn - 1; j++) {
					TCRows.put(HdrRows[j].getContents(), RowCells[j].getContents());
					continue SKIP;
				}
				ArrayofDataList.add(TCRows);
			}
		}
		return ArrayofDataList;
	}

	public static Integer GetColumnNmbr(Sheet sheet,String HdrName)
	{
		try {
			Sheet DataSheet=sheet;
			Cell[] FrstRowCells=DataSheet.getRow(0);
			for(Cell cell:FrstRowCells)
			{
				if(cell.getContents().equalsIgnoreCase(HdrName))
					return cell.getColumn();
			}
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
}
