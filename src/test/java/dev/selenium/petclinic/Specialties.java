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

public class Specialties extends TestBase{



    @Test
    public void creationNewSpecialty(){
        String newSpeciality = "breath";

        goToSpecialtiesPage();
        driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']")).click();
        fillAndSaveNewPetField(newSpeciality);
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table")));

        newSpecialtyChecker(newSpeciality);

    }

    @Test
    public void creationEmptySpeciality(){

        goToSpecialtiesPage();
        driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']")).click();
        int countOfSpecialitiesExpected = getCountOfSpecialties();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        int countOfSpecialitiesActual = getCountOfSpecialties();

        assertEquals(countOfSpecialitiesActual, countOfSpecialitiesExpected);

    }

    private int getCountOfSpecialties(){
        return driver.findElements(By.xpath("//td/input[@name='spec_name']")).size();
        }

    private void newSpecialtyChecker(String specialty){
        try{
        List <WebElement> allPetTypesInputs = driver.findElements(By.xpath("//td/input[@name='spec_name']"));
        List <String> allPetTypesText = new ArrayList<>();
        for(WebElement input : allPetTypesInputs){
            allPetTypesText.add(input.getAttribute("value"));
        }
            assertTrue(allPetTypesText.contains(specialty));
        } catch (NoSuchElementException ex){
            System.out.println("Something is wrong ! No such element ! Exception !");
        }


    }
    private void fillAndSaveNewPetField(String speciality){
        WebElement petTypeField = driver.findElement(By.xpath("//input[@id='name']"));
        petTypeField.click();
        petTypeField.clear();
        petTypeField.sendKeys(speciality);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


}
