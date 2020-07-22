import com.google.common.base.Verify;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class TestCase4 extends DriverManager {

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
    public void gotoGeneralStore() {
        LinkedHashMap<String, String> prodDetails = null;
        LinkedHashMap<String, String> prodDetail_list1, prodDetail_list2,prodDetail_list3;
        getDriver().findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        getDriver().hideKeyboard();
        getDriver().findElement(By.xpath("//*[@text='Female']")).click();
        getDriver().findElement(By.id("android:id/text1")).click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        //   getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
        getDriver().findElement(By.xpath("//*[@text='Argentina']")).click();
        getDriver().findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        prodDetails = scrollToProduct("Air Jordan 1 Mid SE");
        prodDetail_list1 = scrollToProduct("PG 3");
        prodDetail_list2 =scrollToProduct("Converse All Star");
        prodDetails.putAll(prodDetail_list1);
        prodDetails.putAll(prodDetail_list2);
        getDriver().findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        validateProduct(prodDetails);

    }
    public void validateProduct(LinkedHashMap<String, String> prodDetails){
        String prodName,prodPrice;
        String productName=null;
        String productPrice=null;
        double totalBalance=0.00;
        getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Converse All Star\").instance(0))"));
        int count = getDriver().findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        int i=0;
        System.out.println("size of cart: " + count + " the size of Hashtable: " + prodDetails.size());
        for (Map.Entry<String,String> entry : prodDetails.entrySet()) System.out.println("Key: "+entry.getKey() +" Value: "+entry.getValue());
            for (Map.Entry<String,String> entry : prodDetails.entrySet()) {
                if(i < count){
                    productName = getDriver().findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
                    productPrice = getDriver().findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
                    i++;
                }
                prodName = entry.getKey();
                prodPrice = entry.getValue();

                totalBalance = Double.parseDouble(productPrice.substring(1))+totalBalance;
                System.out.println("HashTable prodName: " + prodName + " HashTable: " + prodPrice);
                System.out.println("Cart productName: " + productName + " Cart productPrice: " + productPrice);
                System.out.println("totalBalance: " + totalBalance);

                Assert.assertEquals(prodName, productName);
                Verify.verify(prodPrice.equalsIgnoreCase(productPrice));
            }
            String totalAmount = getDriver().findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
            Assert.assertEquals(Double.parseDouble(totalAmount.substring(1)), totalBalance, "Failed to Matched the total balance");

            AndroidElement contract = getDriver().findElement(By.id("com.androidsample.generalstore:id/termsButton"));
            TouchAction t = new TouchAction(getDriver());
            t.longPress(longPressOptions().withElement(element(contract))).release().perform();
            getDriver().findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
            t.tap(tapOptions().withElement(element(getDriver().findElement(By.className("android.widget.CheckBox"))))).perform();
            getDriver().findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    }
}