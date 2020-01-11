package dev.selenium.petclinic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TestBase {
    public WebDriver driver;

    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    };

    @BeforeMethod
    public void driverCreation(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void driverClean(){
        driver.quit();
    }

    @AfterClass
    public void deletingDriver(){
        driver = null;
    }




    protected void isHereValidationMessage(String xpathString, String message ){
        String actualMessage;
        try {
            WebElement messageElement = driver.findElement(By.xpath(xpathString));
            actualMessage = messageElement.getText();
        } catch(NoSuchElementException ex){
            actualMessage = "No Such element ! Exception !";
        }

        assertEquals(actualMessage, message);
    }
}
