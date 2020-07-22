import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class DragAndDrop extends DriverManager{

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
    public void dragAndDropTest(){
        getDriver().findElementByAndroidUIAutomator("text(\"Views\")").click();
        getDriver().findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement source=getDriver().findElementsByClassName("android.view.View").get(0);
        WebElement destination=getDriver().findElementsByClassName("android.view.View").get(1);

        TouchAction dragNDrop = new TouchAction(getDriver())
                .longPress(element(source))
                .moveTo(element(destination))
                .release();
        dragNDrop.perform();
//        new TouchAction(getDriver())
//                .longPress(longPressOptions()
//                .withElement(element(source))
//                .withDuration(Duration.ofMillis(10000)))
//                .moveTo(element(destination))
//                .release()
//                .perform();
       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
