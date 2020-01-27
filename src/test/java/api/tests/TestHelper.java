package api.tests;

import api.endpoints.*;
import api.objects.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TestHelper {
    protected ApiOwner owner;
    protected ApiPet createdPet;
    protected ApiType petType;
    protected ApiVeterinarian createdVet;
    protected ApiSpeciality createdSpec;

    protected PetEndpoint petEndpoint;
    protected TypeEndpoint petTypeEndpoint;
    protected OwnerEndpoint ownerEndpoint;
    protected VisitEndpoint visitEndpoint;
    protected SpecialityEndpoint specEndpoint;
    protected VeterinarianEndpoint vetEndpoint;

   @BeforeClass
   public void setEndpoints(){
       petEndpoint = new PetEndpoint();
       petTypeEndpoint = new TypeEndpoint();
       ownerEndpoint = new OwnerEndpoint();
       visitEndpoint = new VisitEndpoint();
       specEndpoint = new SpecialityEndpoint();
       vetEndpoint = new VeterinarianEndpoint();
   }


    @AfterMethod
    public void cleanData() {
        if (owner != null) {
            OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
            ownerEndpoint.apiOwnerDeletingViaId(owner.getId());
        }

        if (petType != null) {
            TypeEndpoint petTypeEndPoint = new TypeEndpoint();
            petTypeEndPoint.apiDeleteTypeViaId(petType.getId());

        }

        if(createdPet != null){
            PetEndpoint petEndpoint = new PetEndpoint();
            petEndpoint.apiDeletePetViaObject(createdPet);
        }

        if(createdVet != null){
            VeterinarianEndpoint vetEndpoint = new VeterinarianEndpoint();
            vetEndpoint.apiDeleteVet(createdVet.getId());
        }

        if(createdSpec != null){
            SpecialityEndpoint specEndpoint = new SpecialityEndpoint();
            specEndpoint.apiDeleteSpecialty(createdSpec.getId());
        }
    }

}
