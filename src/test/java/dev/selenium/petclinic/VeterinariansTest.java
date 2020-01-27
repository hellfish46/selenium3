package dev.selenium.petclinic;

import dev.selenium.PageObjectImplementation.AddNewVeterinarianPage;
import dev.selenium.PageObjectImplementation.AllVeterinariansPage;
import dev.selenium.PageObjectImplementation.NavigationPage;
import dev.selenium.objects.Veterinarian;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VeterinariansTest extends TestBase {

    @Story("Veterinarians")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createNewVeterinarian(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewVeterinarianPage addNewVetPage = navigation.goToAddNewVeterinarianPage();
        Veterinarian vet = new Veterinarian();
        vet.setFirstName("Antony");
        vet.setLastName("Hopkins");
        vet.setType("brain");
        addNewVetPage.fillWholeForm(vet);
        AllVeterinariansPage allVetPage = addNewVetPage.clickSaveVetBtn();
        List <Veterinarian> allVets = allVetPage.getAllVeterinarians();
        assertThat(allVets).contains(vet);
    }

    @Story("Veterinarians")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void firstNameLessTwoSymbols() throws InterruptedException {
        NavigationPage navigation = new NavigationPage(driver);
        AddNewVeterinarianPage addNewVetPage = navigation.goToAddNewVeterinarianPage();
        Veterinarian vet = new Veterinarian();
        vet.setFirstName("A");
        vet.setLastName("Hopkins");
        vet.setType("brain");
        addNewVetPage.fillWholeForm(vet);
        assertThat(addNewVetPage.firstNameValidationMessage()).contains(ValidationMessage.FIRSTNAMELESSTWOSYMBOLS.getMessage());


    }

    @Story("Veterinarians")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void lastNameLessTwoSymbols(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewVeterinarianPage addNewVetPage = navigation.goToAddNewVeterinarianPage();
        Veterinarian vet = new Veterinarian();
        vet.setFirstName("Antony");
        vet.setLastName("H");
        vet.setType("brain");
        addNewVetPage.fillWholeForm(vet);
        assertThat(addNewVetPage.lastNameValidationMessage()).contains(ValidationMessage.LASTNAMELESSTWOSYMBOLS.getMessage());
    }

}







