package dev.selenium.petclinic;

import dev.selenium.PageObjectImplementation.NavigationPage;
import dev.selenium.PageObjectImplementation.SpecialitiesPage;
import dev.selenium.objects.Speciality;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SpecialtiesTest extends TestBase{



    @Test
    public void creationNewSpecialty(){
        NavigationPage navigation = new NavigationPage(driver);
        SpecialitiesPage specialitiesPage = navigation.goToSpecialitiesPage();
        Speciality speciality = new Speciality();
        speciality.setSpeciality("blood");
        specialitiesPage.clickAddBtn();
        specialitiesPage.fillNewSpeciality(speciality);
        specialitiesPage.clickSaveBtn();
        driver.navigate().refresh();
        List<Speciality> allSpecialities = specialitiesPage.getAllSpecialities();
        assertThat(allSpecialities).contains(speciality);
    }

    @Test
    public void creationEmptySpeciality(){

        NavigationPage navigation = new NavigationPage(driver);
        SpecialitiesPage specialitiesPage = navigation.goToSpecialitiesPage();
        List<Speciality> allSpecialitiesBefore = specialitiesPage.getAllSpecialities();
        specialitiesPage.clickAddBtn();
        specialitiesPage.clickSaveBtn();
        driver.navigate().refresh();
        List<Speciality> allSpecialitiesAfter = specialitiesPage.getAllSpecialities();
        assertEquals(allSpecialitiesBefore, allSpecialitiesAfter);
    }



}
