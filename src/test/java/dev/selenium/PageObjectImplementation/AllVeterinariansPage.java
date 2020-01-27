package dev.selenium.PageObjectImplementation;

import dev.selenium.objects.Veterinarian;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AllVeterinariansPage {
    private WebDriver driver;

    public AllVeterinariansPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public List<Veterinarian> getAllVeterinarians(){
        List<Veterinarian> allVetObj = new ArrayList<>();
        List <WebElement> allVetTrs = driver.findElements(By.xpath("//tbody/tr"));


        for (WebElement tr: allVetTrs) {

            allVetObj.add(createVetFromTr(tr));
        }
        return allVetObj;
    }

    private Veterinarian createVetFromTr(WebElement tr){
        Veterinarian vet = new Veterinarian();


        String fullName = tr.findElement(By.xpath("./td[1]")).getText().trim();
        //System.out.println(fullName);
        String[] fullNameArr = fullName.split(" ");
        if(fullNameArr.length >1){
            vet.setFirstName(fullNameArr[0]);
            vet.setLastName(fullNameArr[1]);
        } else {
            vet.setFirstName(fullNameArr[0]);
        }


        System.out.println(tr.findElements(By.xpath("./td[2]/div")).size() > 0);
        if(tr.findElements(By.xpath("./td[2]/div")).size() > 0) {
            List<String> specialitiesList = new ArrayList<>();
            List<WebElement> listOfSpecialities = tr.findElements(By.xpath("./td[2]/div"));

            for (WebElement speciality : listOfSpecialities) {
                specialitiesList.add(speciality.getText());
            }
            vet.setListOfSpecialities(specialitiesList);
        }

        return vet;
    }
}
