package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AllOwnersPage {
    private WebDriver driver;

    public AllOwnersPage(WebDriver driver) {
        this.driver = driver;
    }

    public AddNewOwnerPage clickAddNewOwnerBtn(){
        WebElement addNewOwnerBtn = driver.findElement(By.xpath("//button[text()='Add Owner']"));
        addNewOwnerBtn.click();
        return new AddNewOwnerPage(driver);
    }

    private Owner createOwnerFromTr(WebElement userRow){
        String fullName = userRow.findElement(By.xpath(".//td/a")).getText();
        String[] fullNameArray = fullName.split(" ");
        Owner owner = new Owner();
        owner.setFirstName(fullNameArray[0]);
        if(fullNameArray.length > 1) {
            owner.setLastName(fullNameArray[1]);
        }
        owner.setAddress(userRow.findElement(By.xpath(".//td[2]")).getText());
        owner.setCity(userRow.findElement(By.xpath(".//td[3]")).getText());
        owner.setTelephone(userRow.findElement(By.xpath(".//td[4]")).getText());

        String pets = userRow.findElement(By.xpath(".//td[5]")).getText();
        if(pets.isEmpty()){
           owner.setPets(null);
        }
        else {
            owner.setPets(pets);
        }

        return owner;

    }

    public List<Owner> getAllOwners(){
        List<Owner> allOwnersObj= new ArrayList<>();
        List<WebElement> allOwnersTr = driver.findElements(By.xpath("//tbody/tr"));


       for (WebElement tr: allOwnersTr) {
           allOwnersObj.add(createOwnerFromTr(tr));
        }

       return allOwnersObj;
    }

}
