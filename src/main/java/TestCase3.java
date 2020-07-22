import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class TestCase3 extends DriverManager{

    @BeforeTest
    public void setup() throws MalformedURLException {
        setCap();
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        quitting();
    }

    @Test
    public void VerifyingTostMessage(){
        getDriver().findElementByXPath("//android.widget.Spinner[@resource-id='com.androidsample.generalstore:id/spinnerCountry']").click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));").click();
        //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Canada\").instance(0))"));
       // getDriver().findElementByXPath("//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']").sendKeys("Lady Diana");
        getDriver().findElementByXPath("//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioFemale']").click();
        getDriver().findElementByXPath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']").click();
        String toastMessage=getDriver().findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println("Toast Message: " + toastMessage);
        Verify.verify(toastMessage.equalsIgnoreCase("Please enter your name"));

    }
    @Test
    public void VerifyingTostMessage_New(){
        getDriver().findElementByXPath("//android.widget.Spinner[@resource-id='com.androidsample.generalstore:id/spinnerCountry']").click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));").click();
        //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Canada\").instance(0))"));
        // getDriver().findElementByXPath("//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']").sendKeys("Lady Diana");
        getDriver().findElementByXPath("//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioFemale']").click();
        getDriver().findElementByXPath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']").click();
        String toastMessage=getDriver().findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println("Toast Message: " + toastMessage);
        Verify.verify(toastMessage.equalsIgnoreCase("Please enter your name"));

    }


}
