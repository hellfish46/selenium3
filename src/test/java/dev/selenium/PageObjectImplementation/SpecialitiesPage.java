package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.Speciality;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesPage {

    private WebDriver driver;

    public SpecialitiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddBtn(){
        WebElement addBtn = driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']"));
        addBtn.click();
    }

    public void fillNewSpeciality(Speciality speciality){
        WebElement nameElement = driver.findElement(By.id("name"));
        nameElement.click();
        nameElement.clear();
        nameElement.sendKeys(speciality.getSpeciality());
    }

    public void clickSaveBtn(){
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
    }

    public List<Speciality> getAllSpecialities(){

        List<Speciality> specialitiesList = new ArrayList<>();
        List<WebElement> inputes = driver.findElements(By.xpath("//tbody/tr//input"));
        for (WebElement input: inputes) {
            specialitiesList.add(createSpecialityFromInput(input));
        }
        return specialitiesList;

    }

    private Speciality createSpecialityFromInput(WebElement input){
        Speciality speciality = new Speciality();
        String value = input.getAttribute("value");
        speciality.setSpeciality(value);
        return speciality;
    }
}
