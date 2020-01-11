package dev.selenium.PageObjectImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage {
    private static final String BASE_URL = "http://localhost:8000/petclinic";

    protected WebDriver driver;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    public PetTypePage goToPetTypesPage(){
        goToUrl("/pettypes", "Pet Types");
        return new PetTypePage(driver);
    }

    public SpecialitiesPage goToSpecialitiesPage(){
        goToUrl("/specialties", "Specialties");
        return new SpecialitiesPage(driver);
    }

    protected WebDriverWait waitFor() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        return wait;
    }

    protected void goToUrl(String url, String title) {

        driver.get(BASE_URL + url);

        waitFor().withMessage(title+ " page is not opened!")
                .until(ExpectedConditions.textToBe(By.xpath("//h2"), title));

    }

    // go to all owners page. It returns the page.
    public AllOwnersPage goToAllOwnersPage(){
        goToUrl("/owners", "Owners");
        return new AllOwnersPage(driver);
    }

    public AddNewOwnerPage goToAddNewOwnerPage(){
        goToUrl("/owners/add", "New Owner");
        return new AddNewOwnerPage(driver);
    }

    public AddNewVeterinarianPage goToAddNewVeterinarianPage(){
        goToUrl("/vets/add", "New Veterinarian");
        return new AddNewVeterinarianPage(driver);
    }
    public AllVeterinariansPage goToAllVeterinariansPage(){
        goToUrl("/vets", "Veterinarians");
        return new AllVeterinariansPage(driver);
    }
}
