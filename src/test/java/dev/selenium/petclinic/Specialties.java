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

public class Specialties {

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
    public void creationNewSpecialty(){
        String newSpeciality = "Nose";

        forwardToSpecialityCreation();
        driver.findElement(By.xpath("//button[text()= ' Add ']")).click();
        fillAndSaveNewPetField(newSpeciality);
        driver.navigate().refresh();
        newSpecialtyChecker(newSpeciality);

    }

    @Test
    public void creationEmptySpeciality(){
        forwardToSpecialityCreation();
        urlChecker("http://localhost:8000/petclinic/specialties");
        int countOfSpecialitiesExpected = driver.findElements(By.xpath("//td/input[@name='spec_name']")).size();
        driver.findElement(By.xpath("//button[text()= ' Add ']")).click();

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        int countOfSpecialitiesActual = driver.findElements(By.xpath("//td/input[@name='spec_name']")).size();
        assertEquals(countOfSpecialitiesActual, countOfSpecialitiesExpected);

    }


    private void newSpecialtyChecker(String specialty){
        try{
        List <WebElement> allPetTypesInputs = driver.findElements(By.xpath("//td/input[@name='spec_name']"));
        List <String> allPetTypesText = new ArrayList<>();
        for(WebElement input : allPetTypesInputs){
            allPetTypesText.add(input.getAttribute("ng-reflect-model"));
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


    private void urlChecker(String url){
        //System.out.println(url);
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url, "Wrong Url !");

    }

    private void forwardToSpecialityCreation(){
        String link = "http://localhost:8000/petclinic/";
        driver.get(link);
        driver.findElement(By.xpath("//a[@href='/petclinic/specialties']")).click();
    }


}
