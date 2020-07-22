;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestCase1 extends DriverManager{

    @BeforeTest
    public void setup(){
        capabilities("realDevice");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterTest
    public void tearDown(){
        quitting();
    }

    @Test
    public void gotoPreference() {
		System.out.println("Print line 1");
		System.out.println("Print line 2");
		System.out.println("Print line 3");
        getDriver().findElementByXPath("(//android.widget.TextView)[10]").click();
        getDriver().findElementByXPath("//android.widget.TextView[@content-desc='3. Preference dependencies']").click();
        getDriver().findElementByXPath("//android.widget.CheckBox[@resource-id='android:id/checkbox']").click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
        getDriver().findElementByClassName("android.widget.EditText").sendKeys("Hello");
        getDriver().findElementByXPath("//android.widget.Button[@text='OK']").click();
    }
    @Test
    public void gotoPreference_New() {
        System.out.println("Print line 1");
        System.out.println("Print line 2");
        System.out.println("Print line 3");
        getDriver().findElementByXPath("(//android.widget.TextView)[10]").click();
        getDriver().findElementByXPath("//android.widget.TextView[@content-desc='3. Preference dependencies']").click();
        getDriver().findElementByXPath("//android.widget.CheckBox[@resource-id='android:id/checkbox']").click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
        getDriver().findElementByClassName("android.widget.EditText").sendKeys("Hello");
        getDriver().findElementByXPath("//android.widget.Button[@text='OK']").click();
    }

}
