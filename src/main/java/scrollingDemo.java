
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class scrollingDemo extends DriverManager{

    @BeforeTest
    public void setup(){
        capabilities("real");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterTest
    public void destroy(){
        quitting();
    }

    @Test
    public void scrollingTest(){

        getDriver().findElementByAndroidUIAutomator("text(\"Views\")").click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Radio Group\"));");

    }
}
