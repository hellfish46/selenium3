package dev.selenium.petclinic;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Owners extends TestBase {


    @Test
    public void creatingNewOwnerValidData(){
        // New Owner Data
        String firstNameVar = "Frank9";
        String lastNameVar = "Sinatra9";
        String addressVar = "Hollywood";
        String cityVar = "PentHouse";
        String telephoneVar = "555274568";
        //go to new owner creation page
        goToOwnerCreationPage();
        //fill all fields in the form
        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);

        WebElement submit = driver.findElement(By.cssSelector("button[type=submit]"));
        submit.click();

        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.withMessage("Wrong URl").until(ExpectedConditions.urlToBe("http://localhost:8000/petclinic/owners"));

        List<WebElement> tdsOfCreatedOwner = driver.findElements(By.xpath("//tbody/tr[last()]/td"));

        List <String> listOfOwnerTextInColumns = new ArrayList<>();
        boolean linkWasRead = false;
        try {
            for (WebElement td : tdsOfCreatedOwner) {
                String text = null;
                if (!linkWasRead){
                    WebElement link = td.findElement(By.tagName("a"));
                    text = link.getText();
                    linkWasRead = true;
                } else {
                    text = td.getText();
                }

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

        goToOwnerCreationPage();
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

        goToOwnerCreationPage();
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

        goToOwnerCreationPage();
        fillFormOfNewOwner(firstNameVar, lastNameVar, addressVar, cityVar, telephoneVar);
        isSubmitButtonDisabled();
        String pathToElement = "//div[input[@id='telephone']]/span[@class='help-block']";
        String expectedMessage = "Phone number only accept digits";
        isHereValidationMessage(pathToElement, expectedMessage);
    }

    @Test
    public void fieldsAreEmpty(){

        goToOwnerCreationPage();
        isSubmitButtonDisabled();
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



}
