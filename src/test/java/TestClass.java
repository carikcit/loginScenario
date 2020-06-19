import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestClass {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty( "webdriver.chrome.driver", "/Users/tahs/Selenium/ChromeDriver/chromedriver" );
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS );

        driver.get( "http://testing.todvachev.com/test-scenarios/login-form/" );
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

    private String fillInForm(String username, String password) {
        WebElement text = driver.findElement( By.name( "userid" ) );
        text.sendKeys( username );
        WebElement webElement = driver.findElement( By.name( "passid" ) );
        webElement.sendKeys( password );
        WebElement textarea = driver.findElement( By.name( "repeatpassid" ) );
        textarea.sendKeys( password );
        WebElement checkbox = driver.findElement( By.name( "submit" ) );
        checkbox.click();
        String alert = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alert;
    }
    @Test
    public void login(){
        String alert=fillInForm( "Username", "Password");
        Assert.assertEquals(alert,"Succesful login!");
    }

}
