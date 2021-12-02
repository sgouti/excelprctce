package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
@Listeners(ExtendedL.class)
public class NewTest1 {
  
@BeforeClass
public void before()
{
	System.out.println("before class");
}
 
  @Test(dataProvider="dp")
  public void Test1(int a,String b)
  {
  	System.out.println("test completed");
  }
  @DataProvider
  public Object[][] dp() {
	  System.out.println("data provider");
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
    
   
  }
}
