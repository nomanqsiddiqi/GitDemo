import com.google.common.base.Verify;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class TestCase2 extends DriverManager{

    @BeforeTest
    public void setup() throws MalformedURLException {
        browsercap();
        getDriver().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        quitting();
    }

    @Test
    public void gotoFacebook(){
        getDriver().get("https://www.facebook.com/");
        getDriver().findElementByXPath("//input[@name='email']").sendKeys("tehmina90@hotmail.com");
        //getDriver().getKeyboard().sendKeys("tehmina90@hotmail.com");
        getDriver().findElementByXPath("//input[@name='pass']").sendKeys("Pakistan786");
        getDriver().findElementByXPath("//button[@name='login']").click();
    }

    @Test
    public void gotoCricbuzz(){
        getDriver().get("https://cricbuzz.com/");
        System.out.println(getDriver().getCurrentUrl() + " --- " + getDriver().getCurrentPackage());
        Verify.verify(getDriver().getCurrentUrl().equalsIgnoreCase("https://m.cricbuzz.com/"));
        getDriver().findElementByXPath("//a[@href='#menu']").click();
        getDriver().findElementByXPath("//a[text()='Home']").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        //Find element by link text and store in variable "Element"
        AndroidElement Element = getDriver().findElementByCssSelector("a[title='Cricbuzz Facebook Page']");
        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}