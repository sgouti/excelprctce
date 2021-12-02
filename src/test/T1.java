package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class T1 {
 public static String TestCntrlSheet="TestCntrl";
 public static String TestCaseSheet="TestCase";
 public static String KeywordSheet="Keyword";
 public static String ObjectLbSheet="ObjectLb";
 public static String TestdataSheet="Testdata";

 public static void main(String[] args)
 {
	// System.out.println(FunctionName("","VantivAndNonVantiv_13660"));
	 for(LinkedHashMap<String,String> sm:TestContlr() )
		 System.out.println(sm);
	 System.out.println("\nSiddharth\n");
	 for(LinkedHashMap<String,String> ss:ExecutableList(FunctionName("","VantivAndNonVantiv_PFEListDdl"),"","Execute") )
	 System.out.println(ss);
 }
 public static ArrayList<LinkedHashMap<String,String>> TestContlr()
 {
	 ArrayList<LinkedHashMap<String,String>> TstCntlr= new ArrayList<>();
	 LinkedHashMap<String,String> TCRows;
	 Sheet TCC=SheetName(TestCntrlSheet);
	 Cell[] HdrRows=TCC.getRow(0);
	 for(int i=1;i<TCC.getRows()-1;i++)
	 {
		 Cell[] RowCells=TCC.getRow(i);
		  TCRows=new LinkedHashMap<>();
		 SKIP:
		 for(int j=0;j<HdrRows.length-1;j++)
		 {
			 
				 TCRows.put(HdrRows[j].getContents(), RowCells[j].getContents());
				 continue SKIP;
		 }
		 TstCntlr.add(TCRows);
	 }
	 
	 return TstCntlr;
 }
  public static Workbook workbook()
  {
	  Workbook wb=null;
	  try {
		  wb=Workbook.getWorkbook(new File("Sid.xls"));
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  return wb;
  }
  public static Sheet SheetName(String SheetName)
  {
	  return workbook().getSheet(SheetName);
  }
  public static ArrayList<LinkedHashMap<String,String>> ExecutableList(ArrayList<LinkedHashMap<String,String>> TCs,String Key,String status)
  {
 	 ArrayList<LinkedHashMap<String,String>> TstCntlr= new ArrayList<>();
 	Iterator<LinkedHashMap<String, String>> ItrTC = TCs.iterator();
 	while(ItrTC.hasNext())
 	{
 		LinkedHashMap<String,String> TC=ItrTC.next();
 		if(TC.get(status).equalsIgnoreCase("YS")||TC.get(status).equalsIgnoreCase("YES"))
 			TstCntlr.add(TC);	
 	} 
 	 return TstCntlr;
  }
  
  public static ArrayList<LinkedHashMap<String,String>> FunctionName(String SheetName,String FGKey)
  {
 	 ArrayList<LinkedHashMap<String,String>> TstCntlr= new ArrayList<>();
 	 LinkedHashMap<String,String> TCRows;
 	 Sheet TCC=SheetName(KeywordSheet);
 	 Cell[] HdrRows=TCC.getRow(0);
 	 System.out.println(TCC.getRows()+"numbers");
 	 for(int i=1;i<TCC.getRows()-1;i++)
 	 {
 		 Cell[] RowCells=TCC.getRow(i);
 		 int MinClmn=(RowCells.length>HdrRows.length)?HdrRows.length:RowCells.length;
 		  TCRows=new LinkedHashMap<>();
 		 if(RowCells[0].getContents().equals(FGKey)) {
 		 SKIP:
 		 for(int j=0;j<MinClmn-1;j++)
 		 {
 			 TCRows.put(HdrRows[j].getContents(), RowCells[j].getContents());
 			 continue SKIP;
 		 }
 		while (TCRows.values().remove(null));
 		 TstCntlr.add(TCRows);
 	 }
 	 }
 	 return TstCntlr;
  }
  
}
