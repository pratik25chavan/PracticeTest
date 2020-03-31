package appiumTest.Sride;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;

import appiumTest.Sride.Pages.HomePage;
import appiumTest.Sride.Pages.MyRidesPage;
import appiumTest.Sride.Utils.CapabilitiesFactory;
import appiumTest.Sride.Utils.GetScreenShot;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/*
Build Extra Required Jars from Location: /Sride/Jars

*/
public class SRideMobileAppTest {

	public static HomePage homePage;

	public static AndroidDriver driver;

	public static MyRidesPage myRidesPage;

	ExtentReports extent;
	ExtentTest test;

	public String getReferenceImageB64(String imgPath) throws URISyntaxException, IOException {
		URL refImgUrl = getClass().getClassLoader().getResource(imgPath);

		System.out.println("refImgUrl : " + refImgUrl);
		File refImgFile = Paths.get(refImgUrl.toURI()).toFile();
		return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
	}

	@BeforeTest
	public void init() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentScreenshot.html", true);
	}

	@Test(testName = "SRide_T001", description = "Verifying Myrides in My rides Tag")
	@MethodOwner(owner = "PratikChavan")
	public void test() throws Exception {

		test = extent.startTest("captureScreenshot");

		// Initialize driver
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), CapabilitiesFactory.getCapabilities());

		// Home Page

		homePage = new HomePage(driver);

		Thread.sleep(5000);

		try {
			homePage.clickOncloseAddIcon();
		} catch (Exception e) {

		}

		Thread.sleep(3000);

		homePage.clickOnMenuButton();

		homePage.clickOnMyRidesTab();

		Thread.sleep(5000);

		// My Rides Page

		myRidesPage = new MyRidesPage(driver);

		myRidesPage.verifySeatingCapacity();

		myRidesPage.verifyTimePeriod();

		// Installation of OpenCV Required here

		String refImageBase64 = getReferenceImageB64("carOwner.png");


		Assert.assertEquals("Car Owner Sign is not Displayed",driver.findElementByImage(refImageBase64).isDisplayed(), true);

		test.log(LogStatus.PASS, "Test Passed");

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		Date date = new Date();
		DateFormat format = new SimpleDateFormat("hh_mm");

		String currentTimeStr = format.format(date);
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = GetScreenShot.capture(driver,
					getClass().getSimpleName() + "Failure_" + currentTimeStr + "");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}
		extent.endTest(test);
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
		}
	}

}
