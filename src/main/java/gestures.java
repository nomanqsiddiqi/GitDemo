import com.google.common.base.Verify;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class gestures extends DriverManager {

    @BeforeTest
    public void setup(){
        capabilities("realDevice");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void discharge(){
        quitting();
    }

    @Test
    public void gestureTesting(){
        getDriver().findElementByAndroidUIAutomator("text(\"Views\")").click();
        getDriver().findElementByAndroidUIAutomator("text(\"Expandable Lists\")").click();

        TouchAction touchAction = new TouchAction(getDriver());
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(getDriver().findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")")))).perform();

        AndroidElement element = getDriver().findElementByAndroidUIAutomator("text(\"People Names\")");
        // longPress first method to achieved by below code.
        TouchAction action = new TouchAction(getDriver());
        action.longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(element)))
                .perform();
        WebElement webElement = getDriver().findElementByXPath("//android.widget.TextView[@text='Sample action']");
        System.out.println("Print the text " + webElement.getText());
        Verify.verify(webElement.isDisplayed());

// longPress second method to achieved by below code.
//        AndroidTouchAction touch = new AndroidTouchAction (getDriver());
//        touch.longPress(LongPressOptions.longPressOptions()
//                .withElement (ElementOption.element (element)))
//                .perform ();
    }

}
