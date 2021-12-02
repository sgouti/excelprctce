package test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Angulartest {

	  private static WebDriver driver;
	    private static NgWebDriver ngDriver;

	    @BeforeMethod
	    void Login() throws InterruptedException {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();

	        driver.get("http://juliemr.github.io/protractor-demo/");
	        Thread.sleep(2000);
	    }

	    @Test
	    public void AngTest() throws InterruptedException {
	        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
	        ngDriver.waitForAngularRequestsToFinish();

	        driver.findElement(ByAngular.model("first")).sendKeys("2");
	        driver.findElement(ByAngular.model("second")).sendKeys("2");
	        //driver.findElement(By.id("gobutton")).click();
	        driver.findElement(ByAngular.buttonText("Go!")).click();
	        ngDriver.waitForAngularRequestsToFinish();

	        int count = driver.findElements(ByAngular.repeater("result in memory")).size();
	        Assert.assertEquals(count, 1);

	        Thread.sleep(2000);
	        driver.quit();
	    }
}
