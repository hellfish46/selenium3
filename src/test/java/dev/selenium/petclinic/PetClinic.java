package dev.selenium.petclinic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PetClinic {
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
    public void creatingNewOwnerValidData(){
        // New Owner Data
        String firstNameVar = "Frank8";
        String lastNameVar = "Sinatra8";
        String addressVar = "Hollywood";
        String cityVar = "PentHouse";
        String telephoneVar = "555274568";


        forwardToRegistrationNewOwner();

        String expectedUrl = "http://localhost:8000/petclinic/owners/add";
        urlChecker(expectedUrl);

        //fill all fields in the form
        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);

        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        submit.click();

        urlChecker("http://localhost:8000/petclinic/owners");

        List<WebElement> tdsOfCreatedOwner = driver.findElements(By.xpath("//tbody/tr[last()]/td"));

        List <String> listOfOwnerTextInColumns = new ArrayList<>();
        try {
            for (WebElement td : tdsOfCreatedOwner) {

                String text = null;
                try {
                    WebElement link = td.findElement(By.tagName("a"));
                    text = link.getText();
                } catch (NoSuchElementException ex) {
                    System.out.println("Tag 'a' isn't here !");
                }

                text = td.getText();


                listOfOwnerTextInColumns.add(text);

            }
        } catch (NoSuchElementException ex){
            System.out.println("No such element ! Exception");
        }

        assertTrue(listOfOwnerTextInColumns.contains(firstNameVar + " " + lastNameVar));
        assertTrue(listOfOwnerTextInColumns.contains(addressVar));
        assertTrue(listOfOwnerTextInColumns.contains(cityVar));
        assertTrue(listOfOwnerTextInColumns.contains(telephoneVar));

    }

    @Test
    public void firstNameLessTwoSymbols(){
        String firstNameVar = "o";
        String lastNameVar = "qw";
        String addressVar = "123qwe";
        String cityVar = "Pit2";
        String telephoneVar = "44444";

       forwardToRegistrationNewOwner();

        String expectedUrl = "http://localhost:8000/petclinic/owners/add";
        urlChecker(expectedUrl);

        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);

        isSubmitButtonDisabled();


       String pathToElement = "//div[input[@id='firstName']]/span[@class='help-block']";
       String expectedMessage = "First name must be at least 2 characters long";
       isHereValidationMessage(pathToElement, expectedMessage);
    }

    @Test
    public void lastNameLessTwoSymbols(){
        String firstNameVar = "oo";
        String lastNameVar = "q";
        String addressVar = "123qwe";
        String cityVar = "Pit2";
        String telephoneVar = "44444";

        forwardToRegistrationNewOwner();

        String expectedUrl = "http://localhost:8000/petclinic/owners/add";
        urlChecker(expectedUrl);

        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);

        isSubmitButtonDisabled();

        String pathToElement = "//div[input[@id='lastName']]/span[@class='help-block']";
        String expectedMessage = "Last name must be at least 2 characters long";
        isHereValidationMessage(pathToElement, expectedMessage);
    }

    @Test
    public void telephoneMustContainOnlyDigits(){
        String firstNameVar = "Andrew";
        String lastNameVar = "Sega";
        String addressVar = "123qwe";
        String cityVar = "Pit2";
        String telephoneVar = "123f";
        forwardToRegistrationNewOwner();
        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);
        isSubmitButtonDisabled();
        String pathToElement = "//div[input[@id='telephone']]/span[@class='help-block']";
        String expectedMessage = "Phone number only accept digits";
        isHereValidationMessage(pathToElement, expectedMessage);
    }

    @Test
    public void fieldsAreEmpty(){
        forwardToRegistrationNewOwner();
        isSubmitButtonDisabled();
    }


    private void urlChecker(String url){
        //System.out.println(url);
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url, "Wrong Url !");

    }

    private void fillFormOfNewOwner(String firstNameVar, String lastNameVar, String addressVar, String cityVar, String telephoneVar){
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys(firstNameVar);

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.click();
        lastName.clear();
        lastName.sendKeys(lastNameVar);

        WebElement address = driver.findElement(By.id("address"));
        address.click();
        address.clear();
        address.sendKeys(addressVar);

        WebElement city = driver.findElement(By.id("city"));
        city.click();
        city.clear();
        city.sendKeys(cityVar);

        WebElement telephone = driver.findElement(By.id("telephone"));
        telephone.click();
        telephone.clear();
        telephone.sendKeys(telephoneVar);
    }

    private void isSubmitButtonDisabled(){
        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        boolean isDisabled = true;
        String disabled = submit.getAttribute("disabled");
        if (disabled == null){
            isDisabled = false;
        }
        assertTrue(isDisabled, "The button isn't blocked !");

    }

    private void isHereValidationMessage(String xpathString, String message ){
        String actualMessage;
        try {
            WebElement messageElement = driver.findElement(By.xpath(xpathString));
            actualMessage = messageElement.getText();
        } catch(NoSuchElementException ex){
            actualMessage = "No Such element ! Exception !";
        }

        assertEquals(actualMessage, message);
    }

    private void forwardToRegistrationNewOwner(){
        String url = "http://localhost:8000";
        driver.get(url);
        driver.findElements(By.cssSelector("a[role=button]")).get(0).click();
        driver.findElement(By.cssSelector("a[href=\"/petclinic/owners/add\"]")).click();
    }



}
