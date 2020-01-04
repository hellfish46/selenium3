package dev.selenium.PajeObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewOwnerPage {
    private WebDriver driver;

    public NewOwnerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstNameVar){
        WebElement name = driver.findElement(By.id("firstName"));
        name.clear();
        name.sendKeys(firstNameVar);
    }

    public void setLastName(String lastNameVar){
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.click();
        lastName.clear();
        lastName.sendKeys(lastNameVar);
    }

    public void setAddress(String addressVar){
        WebElement address = driver.findElement(By.id("address"));
        address.click();
        address.clear();
        address.sendKeys(addressVar);
    }

    public void setCity(String cityVar){
        WebElement city = driver.findElement(By.id("city"));
        city.click();
        city.clear();
        city.sendKeys(cityVar);
    }

    public void setTelephone(String telephoneVar){
        WebElement telephone = driver.findElement(By.id("telephone"));
        telephone.click();
        telephone.clear();
        telephone.sendKeys(telephoneVar);
    }

}
