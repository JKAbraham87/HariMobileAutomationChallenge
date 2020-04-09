package amazonScript;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class AmazonDemo {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "AmazonResult";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();
    
    
    
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "3200ab93e86a462d");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "in.amazon.mShop.android.shopping");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amazon.mShop.home.HomeActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() {
        //Login********************************************************************************
    	driver.findElement(By.xpath("//*[@text='Already a customer? Sign in']")).click();
        driver.findElement(By.xpath("//*[@id='ap_email_login']")).sendKeys("9944626329");        
        driver.findElement(By.xpath("//*[@text='Continue']")).click();
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys("Electronic@99");
        driver.findElement(By.xpath("//*[@id='signInSubmit']")).click();
        
        //Input values for search**************************************************************
        driver.findElement(By.xpath("//*[@id='rs_search_src_text']")).sendKeys("Sanitizers");
        driver.executeScript("seetest:client.deviceAction(\"Enter\")");
        
        // Storing the selected value from product screen**************************************************************
        String product=driver.findElement(By.xpath("//*[@text='Himalaya PureHands Hand Sanitizer, 100ml']")).getText();
        driver.findElement(By.xpath("//*[@text='Himalaya PureHands Hand Sanitizer, 100ml']")).click();
        
        // Storing the value from addcart page**************************************************************************
        String Addcart=driver.findElement(By.xpath("//*[@text='Himalaya PureHands Hand Sanitizer, 100ml']")).getText();
        
        //Comparing the value
        if(Addcart.equals(product))
        {
        	System.out.println("Both are equal");
        }
        else
        	System.out.println("Fail");
        driver.swipe(926, 1226, 873, 630, 1373);
        driver.swipe(780, 1850, 784, 1250, 797);
        driver.findElement(By.xpath("//*[@text='Add to Cart']")).click();        
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}