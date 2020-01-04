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



public class PetType extends TestBase{



    @Test
    public void newPetTypeCreation(){
        String petType = "Kaiju";
        goToPetTypePage();
        driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']")).click();
        fillNewPetField(petType);
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        driver.navigate().refresh();
        newPetTypeChecker(petType);

    }

    @Test
    public void newPetTypeEmptyField(){

        goToPetTypePage();
        int countOfPetTypesExpected = driver.findElements(By.xpath("//td/input[@name='pettype_name']")).size();
        driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']")).click();
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        driver.navigate().refresh();
        int countOfPetTypesActual = driver.findElements(By.xpath("//td/input[@name='pettype_name']")).size();
        assertEquals(countOfPetTypesActual, countOfPetTypesExpected);

    }

    private void newPetTypeChecker(String petType){
        List <WebElement> allPetTypesInputs = driver.findElements(By.xpath("//td/input[@name='pettype_name']"));
        List <String> allPetTypesText = new ArrayList<>();
        for(WebElement input : allPetTypesInputs){
            allPetTypesText.add(input.getAttribute("value"));
        }
        assertTrue(allPetTypesText.contains(petType));
    }

    private void fillNewPetField(String petType){
        WebElement petTypeField = driver.findElement(By.xpath("//input[@id='name']"));
        petTypeField.click();
        petTypeField.clear();
        petTypeField.sendKeys(petType);
    }








}
