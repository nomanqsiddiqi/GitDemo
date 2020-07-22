import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverManager {
    private static File fs;
    private static DesiredCapabilities cap;
    private static AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;

    public void capabilities(String deviceType) {
        fs = new File( new File("src/main/resources"), "ApiDemos-debug.apk");
        cap = new DesiredCapabilities();
        cap.setCapability("NoResetValue", false);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        if(deviceType.equalsIgnoreCase("realDevice")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        }else if(deviceType.equalsIgnoreCase("emulator")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2");
        }
        cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        try {
            driver= new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setCap() throws MalformedURLException {
        cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554"); //Give Device ID of your mobile phone
        //cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
        // App is already installed on device, so can be launched by Package name and Activity
        cap.setCapability("appPackage","com.androidsample.generalstore");
        cap.setCapability("appActivity","com.androidsample.generalstore.SplashActivity");


        // Skip the installation of io.appium.settings app and the UIAutomator 2 server.
        cap.setCapability("skipDeviceInitialization", true);
        cap.setCapability("skipServerInstallation", true);
        driver= new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        //wait = new WebDriverWait(driver, 10);
    }

    public void browsercap() throws MalformedURLException {

        cap = new DesiredCapabilities();

        // cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("udid", "0715f741e0da2a38"); //Give Device ID of your mobile phone
        cap.setCapability("deviceName", "Galaxy Note 5");
//        cap.setCapability("udid","emulator-5554");
//        cap.setCapability("deviceName", "Pixel3");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9.0");
        cap.setCapability("browserName", "Chrome");
        cap.setCapability("chromedriverExecutable", "C:\\tools\\bin\\chromedriver.exe");
        cap.setCapability("noReset", true);
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), cap);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
    public AndroidDriver<AndroidElement> getDriver(){

        return driver;
    }

    public void quitting(){
        if( driver != null)
            driver.quit();
    }

    public void clickonLink(String value){

        getDriver().findElementByAndroidUIAutomator("text(\"+value+\")").click();
    }

    public LinkedHashMap<String, String> scrollToProduct(String prodName){
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+prodName+"\").instance(0))"));
        int count = getDriver().findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < count; i++) {
            String productName = getDriver().findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            String price = getDriver().findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            if (productName.matches(prodName)) {
                data.put(productName,price);
                getDriver().findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        return data;
    }
}