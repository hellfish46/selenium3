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



public class PetType {
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
    public void newPetTypeCreation(){
        String petType = "Kaiju";
        forwardToPetTypeCreation();
        urlChecker("http://localhost:8000/petclinic/pettypes");
        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
        fillNewPetField(petType);
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        driver.navigate().refresh();
        newPetTypeChecker(petType);

    }

    @Test
    public void newPetTypeEmptyField(){
        forwardToPetTypeCreation();
        urlChecker("http://localhost:8000/petclinic/pettypes");
        int countOfPetTypesExpected = driver.findElements(By.xpath("//td/input[@name='pettype_name']")).size();
        driver.findElement(By.xpath("//button[text()=' Add ']")).click();
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
            allPetTypesText.add(input.getAttribute("ng-reflect-model"));
        }
        assertTrue(allPetTypesText.contains(petType));
    }

    private void fillNewPetField(String petType){
        WebElement petTypeField = driver.findElement(By.xpath("//input[@id='name']"));
        petTypeField.click();
        petTypeField.clear();
        petTypeField.sendKeys(petType);
    }

    private void forwardToPetTypeCreation(){
        String link = "http://localhost:8000/petclinic/";
        driver.get(link);
        driver.findElement(By.xpath("//a[@href='/petclinic/pettypes']")).click();
    }

    private void urlChecker(String url){
        //System.out.println(url);
        driver.get(url);
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, url, "Wrong Url !");

    }




}
