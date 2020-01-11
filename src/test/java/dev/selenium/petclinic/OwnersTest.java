package dev.selenium.petclinic;

import dev.selenium.PageObjectImplementation.AddNewOwnerPage;
import dev.selenium.PageObjectImplementation.AllOwnersPage;
import dev.selenium.PageObjectImplementation.NavigationPage;
import dev.selenium.objects.Owner;
import org.testng.annotations.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class OwnersTest extends TestBase {


    @Test
    public void creatingNewOwnerValidData(){
        //go to new owner creation page
        NavigationPage navigation = new NavigationPage(driver);
        AddNewOwnerPage addNewOwnerPage = navigation.goToAddNewOwnerPage();
        Owner owner = new Owner();
        owner.setFirstName("Denis");
        owner.setLastName("KrasaveTs");
        owner.setAddress("England, 49000");
        owner.setCity("London");
        owner.setTelephone("123456789");
        addNewOwnerPage.fillWholeForm(owner);
        AllOwnersPage allOwnersPage = addNewOwnerPage.clickAddOwnerBtn();
        List<Owner> allOwners = allOwnersPage.getAllOwners();
        assertThat(allOwners).contains(owner);
    }

    @Test
    public void firstNameLessTwoSymbols(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewOwnerPage addNewOwnerPage = navigation.goToAddNewOwnerPage();
        Owner owner = new Owner();
        owner.setFirstName("J");
        owner.setLastName("Elto2");
        owner.setAddress("England, 49000");
        owner.setCity("London");
        owner.setTelephone("123456789");
        addNewOwnerPage.fillWholeForm(owner);

        assertEquals(addNewOwnerPage.firstNameValidationMessage(), ValidationMessage.FIRSTNAMELESSTWOSYMBOLS.getMessage());
        assertFalse(addNewOwnerPage.isAddOwnerButtonEnabled());

    }

    @Test
    public void lastNameLessTwoSymbols(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewOwnerPage addNewOwnerPage = navigation.goToAddNewOwnerPage();
        Owner owner = new Owner();
        owner.setFirstName("John1");
        owner.setLastName("E");
        owner.setAddress("England, 49000");
        owner.setCity("London");
        owner.setTelephone("123456789");
        addNewOwnerPage.fillWholeForm(owner);


        assertEquals(addNewOwnerPage.lastNameValidationMessage(), ValidationMessage.LASTNAMELESSTWOSYMBOLS.getMessage());
        assertFalse(addNewOwnerPage.isAddOwnerButtonEnabled());
    }

    @Test
    public void telephoneMustContainOnlyDigits(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewOwnerPage addNewOwnerPage = navigation.goToAddNewOwnerPage();
        Owner owner = new Owner();
        owner.setFirstName("Johnqwe");
        owner.setLastName("Elton2");
        owner.setAddress("England, 49000");
        owner.setCity("London");
        owner.setTelephone("12345678f");
        addNewOwnerPage.fillWholeForm(owner);



        assertEquals(addNewOwnerPage.telephoneValidationMessage(), ValidationMessage.PHONEMUSTBEDIGITS.getMessage());
        assertFalse(addNewOwnerPage.isAddOwnerButtonEnabled());
    }

    @Test
    public void fieldsAreEmpty(){
        NavigationPage navigation = new NavigationPage(driver);
        AddNewOwnerPage addNewOwnerPage = navigation.goToAddNewOwnerPage();


        assertFalse(addNewOwnerPage.isAddOwnerButtonEnabled());
    }



}
