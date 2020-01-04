package PetClininc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OwnerTest {
    private WebDriver driver;


    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    };

    @BeforeMethod
    public void driverCreation(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void driverClean(){
        driver.quit();
    }

    @AfterClass
    public void deletingDriver(){
        driver = null;
    }


    @Test
    public void addOwner(){
        driver.get("http://139.59.149.247:8000/petclinic/owners");

        WebElement ownersMenuItem = driver.findElement(By.xpath("//a[@class='dropdown-toggle'][text()='Owners']"));
        ownersMenuItem.click();

        WebElement allOwners = driver.findElement(By.xpath("//a[@routerlink='/owners']"));
        allOwners.click();


        WebElement ownersTable = driver.findElement(By.xpath("//*[@class='table-responsive']"));
        assertTrue(ownersTable.isDisplayed());

        List<WebElement> ownerList = ownersTable.findElements(By.xpath(".//tbody/tr"));
        assertFalse(ownerList.isEmpty());

        WebElement userRow = ownerList.get(0);
        userRow.click();

        User user = new User();


    }




}
