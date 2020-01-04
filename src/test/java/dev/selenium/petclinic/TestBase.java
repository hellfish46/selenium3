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

    private final String BASE_URL = "http://localhost:8000/petclinic";


    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    };

    @BeforeMethod
    public void driverCreation(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void driverClean(){
        driver.quit();
    }

    @AfterClass
    public void deletingDriver(){
        driver = null;
    }

    protected void goToOwnerCreationPage(){
        goToUrl(BASE_URL + "/owners/add", "New Owner");
    }

    protected void goToVeterinarianCreationPage(){
        goToUrl(BASE_URL + "/vets/add", "New Veterinarian");
    }

    protected void goToSpecialtiesPage(){
        goToUrl(BASE_URL + "/specialties", "Specialties");
    }

    protected void goToPetTypePage(){
        goToUrl(BASE_URL + "/pettypes", "Pet Types");
    }

    protected WebDriverWait waitFor(){
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait;
    }

    private void goToUrl(String url, String title){
        driver.get(url);
        waitFor().withMessage(title + " page is not opened!").until(ExpectedConditions.textToBe(By.xpath("//h2"), title));
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
