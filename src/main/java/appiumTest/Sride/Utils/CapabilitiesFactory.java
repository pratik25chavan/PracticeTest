package appiumTest.Sride.Utils;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesFactory {

	public static DesiredCapabilities caps;

	public static DesiredCapabilities getCapabilities() throws Exception {

		 caps = DesiredCapabilities.android();
		caps.setCapability("appiumVersion", "1.15.1");
		caps.setCapability("deviceName", "Nokia 4.2");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion", "9");
		caps.setCapability("platformName", "Android");
		caps.setCapability("appActivity", "co.sride.activity.SplashActivity");
		caps.setCapability("appPackage", "co.sride");
		caps.setCapability("automationName", "uiautomator2");
		
		caps.setCapability("udid", "DT89571GA19C2605507");
		caps.setCapability("noReset", true);
	
System.out.println(caps);
		return caps;
	}

}
