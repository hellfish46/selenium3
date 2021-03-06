package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewOwnerPage extends BasePage {



    public AddNewOwnerPage(WebDriver driver) {
        super(driver);
    }

    private AllOwnersPage AllOwnerPageReturner(){
        return new AllOwnersPage(driver);
    }

    private WebElement AddOwnerBntFounder(){
        return driver.findElement(By.xpath("//button[text()='Add Owner']"));
    }

    public void fillWholeForm(Owner owner){

        setFirstName(owner.getFirstName());
        setLastName(owner.getLastName());
        setAddress(owner.getAddress());
        setCity(owner.getCity());
        setTelephone(owner.getTelephone());


    }

    @Step
    public String firstNameValidationMessage(){
        WebElement input = driver.findElement(By.id("firstName"));
        return getValidationMessageViaId(input);
    }

    @Step
    public String lastNameValidationMessage(){
        WebElement input = driver.findElement(By.id("lastName"));
        return getValidationMessageViaId(input);
    }

    @Step
    public String addresskValidationMessage(){
        WebElement input = driver.findElement(By.id("address"));
        return getValidationMessageViaId(input);
    }

    @Step
    public String cityValidationMessage(){
        WebElement input = driver.findElement(By.id("city"));
        return getValidationMessageViaId(input);
    }

    @Step
    public String telephoneValidationMessage(){
        WebElement input = driver.findElement(By.id("telephone"));
        return getValidationMessageViaId(input);
    }

    @Step
    public void setFirstName(String firstName){
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @Step
    public void setLastName(String lastName){
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @Step
    public void setAddress(String address){
        WebElement addressField = driver.findElement(By.id("address"));
        addressField.click();
        addressField.clear();
        addressField.sendKeys(address);
    }

    @Step
    public void setCity(String city){
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.click();
        cityField.clear();
        cityField.sendKeys(city);
    }

    @Step
    public void setTelephone(String telephone){
        WebElement telephoneField = driver.findElement(By.id("telephone"));
        telephoneField.click();
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
    }
    @Step
    public AllOwnersPage clickAddOwnerBtn(){
        WebElement addOwnerBtn = AddOwnerBntFounder();
        addOwnerBtn.click();
        return AllOwnerPageReturner();
    }
    @Step
    public AllOwnersPage clickBackBtn(){
        WebElement addOwnerBtn = driver.findElement(By.xpath("//button[text()='Back']"));
        addOwnerBtn.click();
        return AllOwnerPageReturner();
    }

    public boolean isAddOwnerButtonEnabled(){
        WebElement addOwnerBtn = AddOwnerBntFounder();
        return addOwnerBtn.isEnabled();
    }







}
