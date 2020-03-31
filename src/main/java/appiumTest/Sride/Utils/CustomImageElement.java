package appiumTest.Sride.Utils;

import io.appium.java_client.AppiumDriver;

import static java.lang.Thread.sleep;

public class CustomImageElement implements ImageElement {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int waitAfterClick;
    private AppiumDriver driver;

    public CustomImageElement(AppiumDriver driver, int x, int y, int width, int height, int waitAfterClick) {
        this.driver = driver;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.waitAfterClick = waitAfterClick;
    }

 
    private void waitAfterClick() throws InterruptedException {
        sleep(1000 * waitAfterClick);
    }


	@Override
	public ImageElement tap() {
		// TODO Auto-generated method stub
		return null;
	}
}
