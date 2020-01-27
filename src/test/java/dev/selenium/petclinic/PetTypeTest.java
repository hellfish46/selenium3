package dev.selenium.petclinic;



import dev.selenium.PageObjectImplementation.NavigationPage;
import dev.selenium.PageObjectImplementation.PetTypePage;
import dev.selenium.objects.PetType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class PetTypeTest extends TestBase{


    @Story("Pet Type")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void newPetTypeCreation(){
        NavigationPage navigation = new NavigationPage(driver);
        PetTypePage petTypePage = navigation.goToPetTypesPage();

        PetType petType = new PetType();
        petType.setType("dragon");

        petTypePage.clickAddBtn();
        petTypePage.fillNewPetType(petType);
        petTypePage.clickSaveBtn();
        driver.navigate().refresh();
        List<PetType> allPetTypes = petTypePage.getAllPetTypes();
        assertThat(allPetTypes).contains(petType);
    }

    @Story("Pet Type")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void newPetTypeEmptyField(){
        NavigationPage navigation = new NavigationPage(driver);
        PetTypePage petTypePage = navigation.goToPetTypesPage();

        List<PetType> allPetTypesOld = petTypePage.getAllPetTypes();
        petTypePage.clickAddBtn();
        petTypePage.clickSaveBtn();
        driver.navigate().refresh();
        List<PetType> allPetTypesNew = petTypePage.getAllPetTypes();
        assertEquals(allPetTypesNew, allPetTypesOld);
    }


}
