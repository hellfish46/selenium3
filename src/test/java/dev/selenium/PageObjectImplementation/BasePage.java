package dev.selenium.PageObjectImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected String getValidationMessageViaId(WebElement idInput){
        WebElement validationMessageElement = idInput.findElement(By.xpath("./following-sibling::span[@class='help-block']"));
        return validationMessageElement.getText();

    }

}
