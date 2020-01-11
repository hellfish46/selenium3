package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.Veterinarian;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddNewVeterinarianPage extends BasePage {

    public AddNewVeterinarianPage(WebDriver driver) {
        super(driver);
    }

    //Validation Message
    public String firstNameValidationMessage(){
        WebElement input = driver.findElement(By.id("firstName"));
        return getValidationMessageViaId(input);
    }

    public String lastNameValidationMessage(){
        WebElement input = driver.findElement(By.id("lastName"));
        return getValidationMessageViaId(input);
    }

    // Set info
    public void setFirstName(String firstName){
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }


    public void setLastName(String lastName){
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }


    public void setType(String typeString){
        WebElement type = driver.findElement(By.id("specialties"));
        type.click();
        try {
            WebElement option = driver.findElement(By.xpath("//select/option[normalize-space(text()) = '"+typeString+"']"));
            option.click();
        } catch(NoSuchElementException ex){
            System.out.println("There isn't such type (element) in list of vet types: " + typeString + "! Exception !");
        }

    }

    //Set all fields
    public void fillWholeForm(Veterinarian veterinarian){
        setFirstName(veterinarian.getFirstName());
        setLastName(veterinarian.getLastName());
        setType(veterinarian.getType());
    }

    //click buttons
    public AllVeterinariansPage clickBackBtn(){
        WebElement backBtn = driver.findElement(By.xpath("//div/button[@type='button']"));
        backBtn.click();
        return new AllVeterinariansPage(driver);
    }

    public AllVeterinariansPage clickSaveVetBtn(){
        WebElement saveBtn = driver.findElement(By.xpath("//div/button[@type='submit']"));
        saveBtn.click();
        return new AllVeterinariansPage(driver);
    }





}
