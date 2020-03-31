package appiumTest.Sride.Pages;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MyRidesPage {

	private AndroidDriver<AndroidElement> driver;

	@AndroidFindBy(id = "co.sride:id/txtTime")
	private WebElement TimePeriod;

	@AndroidFindBy(id = "co.sride:id/txtSeats")
	private WebElement SeatingCapacity;

	public MyRidesPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void verifyTimePeriod() {
		
		// Time value from application
		
		String TimePeriodValue = TimePeriod.getText();

		String riderPostTimeHrsTxt = TimePeriodValue.substring(0, 2);

		System.out.println("riderPostTimeHrsTxt : " + riderPostTimeHrsTxt);

		String riderPostTimeMnsTxt = TimePeriodValue.substring(3, 5);

		System.out.println("riderPostTimeMnsTxt : " + riderPostTimeMnsTxt);

		int riderPostTimeHrsInt = 0;
		System.out.println("PMvalue : " + TimePeriodValue.substring(6, 8));

		if (TimePeriodValue.substring(6, 8).equalsIgnoreCase("pm")) {

			riderPostTimeHrsInt = Integer.parseInt(riderPostTimeHrsTxt) + 12;
		} else {
			riderPostTimeHrsInt = Integer.parseInt(riderPostTimeHrsTxt);
		}
		int riderPostTimeMnsInt = Integer.parseInt(riderPostTimeMnsTxt);

		int TotalMillisHrs = riderPostTimeHrsInt * 60 * 60 * 1000;
		int TotalMillisMin = riderPostTimeMnsInt * 60 * 1000;

		int TotalMillis = TotalMillisHrs + TotalMillisMin;

		
		// Current Time
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("hh:mm");

		String currentTimeStr = format.format(date);
		System.out.println(format.format(date));

		String currentTimeHrsTxt = currentTimeStr.substring(0, 2);

		System.out.println("currentTimeHrsTxt : " + currentTimeHrsTxt);

		String currentTimeMnsTxt = currentTimeStr.substring(3, 5);

		System.out.println("currentTimeMnsTxt : " + currentTimeMnsTxt);

		int currentTimeHrsInt = Integer.parseInt(currentTimeHrsTxt);
		int currentTimeMnsInt = Integer.parseInt(currentTimeMnsTxt);

		int TotalcurrentTimeMillisHrs = currentTimeHrsInt * 60 * 60 * 1000;
		int TotalcurrentTimeMillisMin = currentTimeMnsInt * 60 * 1000;

		int TotalcurrentTimeMillis = TotalcurrentTimeMillisHrs + TotalcurrentTimeMillisMin;

		long DiffMillis = TotalcurrentTimeMillis - TotalMillis;

		long DiffMinitues = DiffMillis / 60000;

		System.out.println("DiffMinitues : " + DiffMinitues);

		if (DiffMinitues > 30) {
			assertTrue("Time is greater than 30 minutes ", false);
		}

	}

	public void verifySeatingCapacity() {
		
		String SeatingCapacityValue = SeatingCapacity.getText();

		System.out.println("Seating Capacity is : " + SeatingCapacityValue);

		if (!SeatingCapacityValue.contains("3")) {
			assertTrue("Seating Capacity is not 3 Seating ", false);
		}
	}

}
