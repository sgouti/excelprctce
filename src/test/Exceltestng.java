package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceltestng {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//FileInputStream file=new FileInputStream("Sid.xlsx");
		//XSSFWorkbook Wrkbk=new XSSFWorkbook(file);
	//	Sheet ShtNme=Sheetnme(Wrkbk);
		//ClmnHdr(ShtNme,"name");
		DataDriven D=new DataDriven();
	//	D.getdata();1
	//	DataDriven.ExecutFnctn("Sid","");
		DataDriven.ExecutableTStCntrl("TestCntrl");
	
	
	}
	//to Get specific sheet
			public static Sheet Sheetnme(XSSFWorkbook Wrkbk)

			{
				Sheet sheet=null;
				int TotlShts=Wrkbk.getNumberOfSheets();
				for(int i=0;i<TotlShts;i++)
				{
					if(Wrkbk.getSheetAt(i).getSheetName().equalsIgnoreCase("empstatus"))
					{
						sheet=Wrkbk.getSheetAt(i);
						System.out.println(sheet.getSheetName());
					}
				}
				return sheet;
			}
	//To get identify Job column
			public static int ClmnHdr(Sheet shtnme,String HeaderNme)
			{	int ClmnhdrNo=0;
				Iterator<Row> rows=shtnme.iterator();
				Row Firstrw= rows.next();
				Iterator<Cell> Clvalue=Firstrw.iterator();
				while(Clvalue.hasNext())
				{
					Cell clv=Clvalue.next();
					if(clv.getStringCellValue().equalsIgnoreCase(HeaderNme))
					{
						System.out.println("you got it");
						break;
					}
					ClmnhdrNo++;
					System.out.println(ClmnhdrNo);
				}
				
				return ClmnhdrNo;
			}
			
}