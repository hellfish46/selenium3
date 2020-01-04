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

public class Veterinarians extends TestBase {

    @Test
    public void createNewVeterinarian(){
        String firstName = "James4";
        String lastName = "Anderson4";
        int type = 3;


        goToVeterinarianCreationPage();

        fillFormOfNewVeterinarian(firstName,lastName, type);

        String typeText = driver.findElement(By.xpath("//*[@value][" + type + "]")).getText().trim();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        newVetChecker(firstName, lastName, typeText);

    }
    @Test
    public void firstNameLessTwoSymbols(){
        String firstName = "J";
        String lastName = "Anderson4";
        int type = 3;
        goToVeterinarianCreationPage();

        fillFormOfNewVeterinarian(firstName,lastName, type);


        String pathToElement = "//div[input[@id='firstName']]/span[@class='help-block']";
        String expectedMessage = "First name must be at least 2 characters long";
        isHereValidationMessage(pathToElement, expectedMessage);

    }

    @Test
    public void lastNameLessTwoSymbols(){
        String firstName = "James4";
        String lastName = "A";
        int type = 3;
        goToVeterinarianCreationPage();
        fillFormOfNewVeterinarian(firstName,lastName, type);

        String pathToElement = "//div[input[@id='lastName']]/span[@class='help-block']";
        String expectedMessage = "Last name must be at least 2 characters long";
        isHereValidationMessage(pathToElement, expectedMessage);
    }

    @Test
    public void fieldsAreEmpty(){
        goToVeterinarianCreationPage();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }




    private void newVetChecker(String firstName, String lastName, String typeText){
        WebElement tr = driver.findElement(By.xpath("//tbody/tr[last()]"));
        List <String> tdsText = new ArrayList<>();

        tdsText.add(tr.findElement(By.xpath("./td[1]")).getText());
        tdsText.add(tr.findElement(By.xpath("./td[2]/div")).getText());

        assertTrue(tdsText.contains(firstName + " " + lastName));
        assertTrue(tdsText.contains(typeText));

    }

    private void fillFormOfNewVeterinarian(String firstNameVar, String lastNameVar, int typeVar){
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.click();
        firstName.clear();
        firstName.sendKeys(firstNameVar);

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.click();
        lastName.clear();
        lastName.sendKeys(lastNameVar);

        WebElement type = driver.findElement(By.id("specialties"));
        type.click();
        try {
            WebElement option = driver.findElement(By.xpath("//*[@value][" + typeVar + "]"));
            option.click();
        } catch(NoSuchElementException ex){
            System.out.println("There isn't such type (element) ! Exception !");
        }



    }






}
