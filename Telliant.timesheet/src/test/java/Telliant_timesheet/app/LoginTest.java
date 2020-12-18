 package Telliant_timesheet.app;

import java.lang.reflect.Method;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import baseclass.test;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.TimesheetPage;
import utilities.PropertyFile;
import testlink.TestLinkIntegration;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class LoginTest extends test {
	public static String Actual;
	public static String Expected;
	static String notes = null;
	static String testresult = null;
    HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
	TimesheetPage timeSheetPage = PageFactory.initElements(driver, TimesheetPage.class);

	@BeforeClass
	public void f() throws Exception {

		loginpage.Clickloginlink();
	}

	@Test(priority = 1, testName = "test1", description = "LoginTimesheet")
	public void login() throws TestLinkAPIException {
		
		
		
			loginpage.enterUserName(PropertyFile.prop.getProperty("Myusername"));
			loginpage.enterPassword(PropertyFile.prop.getProperty("Mypassword"));
			loginpage.submitLogin();
			Actual = loginpage.validateLoginScreen();
			Expected = PropertyFile.prop.getProperty("ExpectedLoggedUser");
			Assert.assertEquals(Actual, Expected);
			
		
		
	}

	@Test(priority = 2, testName = "test2", description = "ViewTimesheet")
	public void viewtimesheet() throws TestLinkAPIException {

		homePage.clickOnViewTimesheetLink();
		Actual = homePage.validateViewTimeSheetpage();
		Expected = PropertyFile.prop.getProperty("ExpectedURl");
		Assert.assertEquals(Actual, Expected);
		
		}
		@AfterMethod

		public static void writeResult(ITestResult result) throws Exception {
			try {
				if (result.getStatus() == ITestResult.SUCCESS) {

					notes = "Executed successfully-from jenkins";
					testresult = TestLinkAPIResults.TEST_PASSED;
					// testlink_configuration.updateResults(TestcaseID, null,testresult, notes);
				}

				else if (result.getStatus() == ITestResult.FAILURE) {

					System.out.println("Log Message:: @AfterMethod: Method-" + "- has Failed");
					notes = "Execution Failedfrom jenkins";
					testresult = TestLinkAPIResults.TEST_FAILED;
					// testlink_configuration.updateResults("testcaseID, null,testresult, notes);
					

				}
			} catch (Exception e) {
				System.out.println("\nLog Message::@AfterMethod: Exception caught");
				notes = "Caught new Expection";
				
				testresult = TestLinkAPIResults.TEST_FAILED;
				// testlink_configuration.updateResults("testcaseID, null,testresult, notes);
				
			} finally {

				TestLinkIntegration.updateResults(result.getMethod().getDescription(), null, testresult, notes);
			}

		}
		 @BeforeMethod
		    public void testSetup(@Optional Method method) throws Exception {
		        String description = method.getAnnotation(Test.class).description();
		    System.out.println(""+description);
		    }
	}
//
//	@Test(priority = 3, testName = "test3", description = "Clear existing actions")
//	public void clearExistingAction() throws Exception {
//
//		
//		
//	try {
//		TestLinkIntegration.updateResults("DP-59",null,TestLinkAPIResults.TEST_PASSED);
//	timeSheetPage.clearExistingProjects();
//	}
//		catch(Exception e){
//			TestLinkIntegration.updateResults("DP-59",e.getMessage(),TestLinkAPIResults.TEST_FAILED);
//		}
//
//		
//	}
//}
