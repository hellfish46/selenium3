package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.PetType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PetTypePage extends BasePage{

    public PetTypePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open form for new pet creation")
    public void clickAddBtn(){
        WebElement addBtn = driver.findElement(By.xpath("//button[normalize-space(text()) = 'Add']"));
        addBtn.click();
    }
    @Step
    public void fillNewPetType(PetType petType){
        WebElement nameElement = driver.findElement(By.id("name"));
        nameElement.click();
        nameElement.clear();
        nameElement.sendKeys(petType.getType());
    }
    @Step("Save new pet type")
    public void clickSaveBtn(){
       WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
       submitBtn.click();
    }

    @Step
    public List<PetType> getAllPetTypes(){

        List<PetType> petTypeList = new ArrayList<>();
        List<WebElement> inputes = driver.findElements(By.xpath("//tbody/tr//input"));
        for (WebElement input: inputes) {
            petTypeList.add(createPetTypeFromInput(input));
        }
        return petTypeList;

    }

    private PetType createPetTypeFromInput(WebElement input){
        PetType petType = new PetType();
        String value = input.getAttribute("value");
        petType.setType(value);
        return petType;
    }



}
