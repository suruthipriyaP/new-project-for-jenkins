package baseclass;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;



import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.*;

public class test  {
	static{
		  try {
			PropertyFile.initialize1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			ReadExcel.ReadExcelvalue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static WebDriver driver;
	@BeforeClass
	
		public static void initialize() throws Exception{
		  
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(PropertyFile.prop.getProperty("url"));
			
	
}

	public static WebElement getlocator(String LocatorElement) {
	String repolocator=PropertyFile.prop.getProperty(LocatorElement);
	String[] Locators=repolocator.split(";");
	String locatorType=Locators[Locators.length-2];
	String strlocator=Locators[Locators.length-1];
	WebElement webElement =null;
	try{

		if(locatorType.equalsIgnoreCase("XPATH")){

		webElement = driver.findElement(By.xpath(strlocator));

		}else if(locatorType.equalsIgnoreCase("ID")){

		webElement = driver.findElement(By.id(strlocator));

		}else if(locatorType.equalsIgnoreCase("NAME")){

		webElement = driver.findElement(By.name(strlocator));

		}else if(locatorType.equalsIgnoreCase("CSS")){

		webElement = driver.findElement(By.cssSelector(strlocator));

		}else if(locatorType.equalsIgnoreCase("LINKTEXT")){

		webElement = driver.findElement(By.linkText(strlocator));
		}

		}catch(NoSuchElementException e){

		e.printStackTrace();
		Assert.fail(strlocator + " Element not found");

		}

		return webElement;
}
	@AfterSuite

	public void TeardownTest() {
   //driver.quit();
	}
}

