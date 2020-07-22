import com.google.common.base.Verify;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class TestCase5 extends DriverManager {

    @BeforeTest
    public void setup() throws MalformedURLException {
        setCap();
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        //quitting();
    }

    @Test
    public void switchToWebView() {

        getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        getDriver().hideKeyboard();
        getDriver().findElement(By.xpath("//*[@text='Female']")).click();
        getDriver().findElement(By.id("android:id/text1")).click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        getDriver().findElement(By.xpath("//*[@text='Argentina']")).click();
        getDriver().findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        scrollToProduct("Air Jordan 1 Mid SE");
        getDriver().findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AndroidElement contract = getDriver().findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        TouchAction t = new TouchAction(getDriver());
        t.longPress(longPressOptions().withElement(element(contract))).release().perform();
        getDriver().findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
        t.tap(tapOptions().withElement(element(getDriver().findElement(By.className("android.widget.CheckBox"))))).perform();
        getDriver().findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> context = getDriver().getContextHandles();
        for (String contextName:context ) {
            System.out.println(contextName);
        }
        getDriver().context("WEBVIEW_com.androidsample.generalstore");
        getDriver().findElement(By.name("q")).sendKeys("Selenium");
        getDriver().findElement(By.name("q")).sendKeys(Keys.ENTER);
        getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
        getDriver().context("NATIVE_APP");
        getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Good Bye");
    }
}