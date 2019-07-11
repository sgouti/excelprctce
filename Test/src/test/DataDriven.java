package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public void getdata() throws IOException {
		FileInputStream file = new FileInputStream("Sid.xls");
		HSSFWorkbook Wrkbk = new HSSFWorkbook(file);
		Sheet TestCase = Sheetnme("TestCntrl");
		ArrayList HeadrNme = Headersvlue(TestCase);

		System.out.println("Column Number " + Hdrcnt(HeadrNme, "TC Id"));
		ArrayList Rowvlue = Rwvlue(HeadrNme, TestCase, "Test Desc", "Status");
		for (Object s : Rowvlue) {
			System.out.println(s);
		}
		Wrkbk.close();
	}

	// Taking sheetname from excel
	public static Sheet Sheetnme(String Sheetname) throws IOException {
		FileInputStream file = new FileInputStream("Sid.xls");
		HSSFWorkbook Wrkbk = new HSSFWorkbook(file);
		Sheet sheetName = null;
		int TotlShts = Wrkbk.getNumberOfSheets();
		int i = 0;
		while (i < TotlShts) {
			if (Wrkbk.getSheetAt(i).getSheetName().equalsIgnoreCase(Sheetname)) {
				sheetName = Wrkbk.getSheetAt(i);
				System.out.println(sheetName.getSheetName());
				break;
			}
			i++;
		}
		Wrkbk.close();
		return sheetName;
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

		return HdrNme;

	}

//Taking all HeaderClm Number
	public static int Hdrcnt(ArrayList HeadrNme, String Hdrnme) {
		int HdrcntN = 0;
		for (int i = 0; i < HeadrNme.size(); i++) {
			if (HeadrNme.get(i).toString().equalsIgnoreCase(Hdrnme)) {
				break;

			}
			HdrcntN++;
		}

		return HdrcntN;

	}

	public static ArrayList<String> Rwvlue(ArrayList HeadrNme, Sheet Shtnme, String Clmnme, String Status) {
		Iterator<Row> Row = Shtnme.iterator();
		ArrayList<String> RowVlue = new ArrayList<String>();
		int Hdr = Hdrcnt(HeadrNme, Clmnme);
		int StatusNmr = Hdrcnt(HeadrNme, Status);
		System.out.println("Status Header Number:-"+StatusNmr);
		while (Row.hasNext()) {
			Row Row1 = Row.next();
			// System.out.println(Row1.getCell(Hdr).getStringCellValue());

			Demo: {
				while (Row1.getCell(Hdr).getStringCellValue().equalsIgnoreCase(Clmnme)) {
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

	public static ArrayList<String> ExecutableTStCntrl(String Shtnme) {
		try {
			Sheet TestCntrlSheet = Sheetnme(Shtnme);
			ArrayList<String> TestCntrlHdrs = Headersvlue(TestCntrlSheet);
			// int TestIDRow=Hdrcnt(TestCntrlHdrs,"TC Id");

			ArrayList<String> ExecutableTstCase = Rwvlue(TestCntrlHdrs, TestCntrlSheet, "TC Id", "Status");
			System.out.println(ExecutableTstCase + "" + TestCntrlHdrs);
			TestScript(ExecutableTstCase,"");
			return ExecutableTstCase;
		} catch (IOException e) {
			System.out.println("Error is :-" + e);
		}
		return null;

	}

	public static void TestScript(ArrayList<String> ExecutbleScript,String ShtNme) throws IOException
	{
		String TcID=ExecutbleScript.get(0);
		System.out.println("Test Id is"+TcID);
		Sheet TestCntrlSheet = Sheetnme("TestCase");
		ArrayList<String> TestCntrlHdrs = Headersvlue(TestCntrlSheet);
		// int TestIDRow=Hdrcnt(TestCntrlHdrs,"TC Id");
		ArrayList<String> ExecutableTstCase = Rwvlue(TestCntrlHdrs, TestCntrlSheet, "TC Id", "Status");
		
		System.out.println(ExecutableTstCase + "" + TestCntrlHdrs);
		ExcutbleRows("TestCase",TcID,"TC Id");
	
	}
	//Taking All selected Rows Number for Executing
	public static ArrayList<Integer> ExcutbleRows(String Shtnme, String CellValue,String Clmnme) throws IOException
	{	
		Sheet ActiveSheet = Sheetnme(Shtnme);
		ArrayList<String> HeadrNme = Headersvlue(ActiveSheet);
		Iterator<Row> Row = ActiveSheet.iterator();
		int Hdr = Hdrcnt(HeadrNme,Clmnme);
		ArrayList<Integer> RowNumbr= new ArrayList<>();
		while (Row.hasNext()) {
			Row Row1 = Row.next();
			
			if(Row1.getCell(Hdr).toString().equalsIgnoreCase(CellValue))
			{
				RowNumbr.add(Row1.getRowNum());
				
			}
		}
		System.out.println(RowNumbr);
		for(int a=0;a<RowNumbr.size();a++)
		System.out.println(RowsValue(Shtnme,RowNumbr.get(a)));
		
		return RowNumbr;
		
	}

	//Taking All values from Selected Rows
	public static ArrayList<String> RowsValue(String shtNme,int RownNumbr) throws IOException
	{	Sheet ActiveSheet = Sheetnme(shtNme);
	ArrayList<String> RowValue=new ArrayList<String>();
		Row RowNmbr=ActiveSheet.getRow(RownNumbr);
		Iterator<Cell> CellV = RowNmbr.iterator();
		while(CellV.hasNext())
		{
			Cell clv = CellV.next();
			if (clv.getCellType() == CellType.STRING) {
				RowValue.add(clv.getStringCellValue());
			} else {
				RowValue.add(NumberToTextConverter.toText(clv.getNumericCellValue()));

		}
		
	}
		return RowValue;

}
}
