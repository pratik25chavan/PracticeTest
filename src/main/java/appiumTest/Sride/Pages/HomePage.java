package appiumTest.Sride.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	private AndroidDriver<AndroidElement> driver;

	public HomePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "co.sride:id/closeImageView")
	public WebElement closeAddIcon;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='open']")
	public WebElement MenuButton;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='My Rides']")
	public WebElement MyRidesTab;

	public void clickOncloseAddIcon() {
		closeAddIcon.click();

	}

	public void clickOnMyRidesTab() {
		MyRidesTab.click();

	}

	public void clickOnMenuButton() {

		MenuButton.click();

	}

}
