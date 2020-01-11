package test;

import org.testng.annotations.Test;

public class T1 {
  @Test
  public void t12() {
	  System.out.println("1");
  }
  @Test(priority=1,enabled=true)
	  public void t13()
	  {
		  System.out.println("2");
	  }
 
  
}
