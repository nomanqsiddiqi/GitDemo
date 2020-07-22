import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class SwipeDemo extends DriverManager{

    @BeforeTest
    public void setup(){
        capabilities("realDevice");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void destroy(){
        quitting();
    }

    @Test
    public void swipeTest(){

        getDriver().findElementByAndroidUIAutomator("text(\"Views\")").click();
        getDriver().findElementsByClassName("android.widget.TextView").get(7).click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='2. Inline']").click();
        WebElement source = getDriver().findElementsByClassName("android.widget.RadialTimePickerView$RadialPickerTouchHelper").get(2);
        WebElement target = getDriver().findElementsByClassName("android.widget.RadialTimePickerView$RadialPickerTouchHelper").get(8);
        getDriver().findElementsByClassName("android.widget.RadialTimePickerView$RadialPickerTouchHelper").get(2).click();


        TouchAction dragNDrop = new TouchAction(getDriver())
                .longPress(element(source))
                .moveTo(element(target))
                .release();
        dragNDrop.perform();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
