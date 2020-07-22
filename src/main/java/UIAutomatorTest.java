import com.google.common.base.Verify;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UIAutomatorTest extends DriverManager{

    @BeforeTest
    public void beforeTest(){
        capabilities("realDevice");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTest(){
        quitting();
    }

    @Test
    public void uiautomatorTest(){

        //getDriver().findElementByAndroidUIAutomator("text(\"Views\")").click();
        int tot =getDriver().findElementsByAndroidUIAutomator("new UiSelector().clickable(false)").size();
        System.out.println("Total "+tot+ " clickable set as True ");
        //Verify.verify(tot == 0);
        List<AndroidElement> element = getDriver().findElementsByXPath("//android.widget.TextView");
        for (AndroidElement ele:element) {
            System.out.println(ele.getText());
            if(ele.getText().equalsIgnoreCase("Views")){
                ele.click();
            }
        }


        //getDriver().findElementByXPath("(//android.widget.TextView)[1]").click();
    }

}
