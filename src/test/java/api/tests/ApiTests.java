package api.tests;

import api.endpoints.*;
import api.objects.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests extends TestHelper {


//Owner tests
    @Test
    public void creatingNewOwner(){
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setFirstName("AAAJenkins");
        apiOwner.setLastName("Studio");
        apiOwner.setAddress("huliano grimau");
        apiOwner.setCity("Dnipro2");
        apiOwner.setTelephone("0923456789");
        owner = ownerEndpoint.getOwnerObjectFromResponse(ownerEndpoint.apiOwnerCreationViaObj(apiOwner));
        assertThat(apiOwner).isEqualTo(owner);
    }

    @Test
    public void creatingNewOwnerWithLongPhone(){
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setFirstName("AAAJenkins");
        apiOwner.setLastName("Studio");
        apiOwner.setAddress("huliano grimau");
        apiOwner.setCity("Dnipro2");
        apiOwner.setTelephone("12345678901");
        Integer statusCode = ownerEndpoint.getStatusCodeFromResponse(ownerEndpoint.apiOwnerCreationViaObj(apiOwner));
        assertThat(statusCode).isEqualTo(400);
    }

    @Test
    public void creatingNewOwnerWithNotValidPhone(){
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setFirstName("AAAJenkins");
        apiOwner.setLastName("Studio");
        apiOwner.setAddress("huliano grimau");
        apiOwner.setCity("Dnipro2");
        apiOwner.setTelephone("123456asd");
        Integer statusCode = ownerEndpoint.getStatusCodeFromResponse(ownerEndpoint.apiOwnerCreationViaObj(apiOwner));
        assertThat(statusCode).isEqualTo(400);
    }



    @Test
    public void deletingOwner(){
        ApiOwner owner = ownerEndpoint.CreateDefaultOwner();
        Response response = ownerEndpoint.apiOwnerDeletingViaId(owner.getId());
        Integer statusCode = ownerEndpoint.getStatusCodeFromResponse(response);
        assertThat(statusCode).isEqualTo(204);
    }



    @Test
    public void getAllOwners(){
        List<ApiOwner> allOwners = ownerEndpoint.apiOwnerGetAll();
        System.out.println(allOwners.size());

    }

    @Test
    public void getOwner(){
        Response response = ownerEndpoint.apiOwnerGet(6);
        ApiOwner owner = ownerEndpoint.getOwnerObjectFromResponse(response);
        System.out.println(owner.toString());
    }

    @Test
    public void editOwner(){
        ApiOwner owner = ownerEndpoint.CreateDefaultOwner();
        owner.setFirstName("Joxi");
        owner.setTelephone("987654");
        owner.setLastName("Man");
        owner.setAddress("some address");
        owner.setCity("Dnipro");

        ownerEndpoint.apiEditOwnerViaObject(owner);

        ApiOwner editedOwner = ownerEndpoint.getOwnerObjectFromResponse(ownerEndpoint.apiOwnerGet(owner.getId()));
        assertThat(editedOwner).isEqualTo(owner);
    }


// Pet type tests
    @Test
    public void createNewPetType(){
        ApiType newPetType = new ApiType();
        newPetType.setName("wife");
        petType = petTypeEndpoint.getTypeObjectFromResponse(petTypeEndpoint.apiTypeCreation(newPetType));

        assertThat(petType).isEqualTo(newPetType);
    }

    @Test
    public void createEmptyPetType(){
        ApiType petType = new ApiType();
        Integer statusCode = petTypeEndpoint.getStatusCodeFromResponse(petTypeEndpoint.apiTypeCreation(petType));
        assertThat(statusCode).isEqualTo(400);
    }

    @Test
    public void editPetType(){
        petType = petTypeEndpoint.createDefaultType();
        petType.setName("new type");
        petTypeEndpoint.apiEditType(petType);
        Response response = petTypeEndpoint.getApiTypeViaId(petType.getId());
        ApiType editedType = petTypeEndpoint.getTypeObjectFromResponse(response);

        assertThat(editedType).isEqualTo(petType);
    }

    //Pet tests
    @Test
    public void createNewPet(){
        owner =  ownerEndpoint.CreateDefaultOwner();

        petType = petTypeEndpoint.createDefaultType();

        ApiPet newPet = new ApiPet();

        newPet.setApiOwner(owner);
        newPet.setBirthDate("2020/01/01");
        newPet.setName("Tyzik");
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        ApiPet createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        assertThat(createdPet).isEqualTo(newPet);

    }

    @Test
    public void deletePet(){
        owner =  ownerEndpoint.CreateDefaultOwner();
        petType = petTypeEndpoint.createDefaultType();
        ApiPet newPet = petEndpoint.getDefaultPet();

        newPet.setApiOwner(owner);
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        Response response = petEndpoint.apiDeletePetViaObject(createdPet);
        Integer statusCode = petEndpoint.getStatusCodeFromResponse(response);
        assertThat(statusCode).isEqualTo(204);
    }

    @Test
    public void editPet(){
        owner =  ownerEndpoint.CreateDefaultOwner();
        petType = petTypeEndpoint.createDefaultType();
        ApiPet newPet = petEndpoint.getDefaultPet();

        newPet.setApiOwner(owner);
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        createdPet.setName("changedName");
        createdPet.setBirthDate("1990/12/01");

        petEndpoint.apiEditPetViaObj(createdPet);
        ApiPet editedPet = petEndpoint.getPetObjectFromResponse(petEndpoint.apiGetPetViaObj(createdPet));

        assertThat(editedPet).isEqualTo(createdPet);
    }

    //Veterinarian tests
    @Test
    public void createNewVet(){
        ApiVeterinarian vet =  new ApiVeterinarian();
        vet.setFirstName("Jameson");
        vet.setLastName("Statham");
        createdVet = vetEndpoint.getVetObjectFromResponse(vetEndpoint.apiCreateVet(vet));
        assertThat(createdVet).isEqualTo(vet);
    }

    @Test
    public void editVet(){
        ApiSpeciality spec = specEndpoint.getDefaultSpec();
        createdSpec = specEndpoint.getSpecObjectFromResponse(specEndpoint.apiCreateSpecialty(spec));

        ApiVeterinarian vet = vetEndpoint.getDefaultVet();
        List<ApiSpeciality> specs = new ArrayList<>();

        specs.add(createdSpec);
        vet.setSpecialties(specs);
        createdVet = vetEndpoint.getVetObjectFromResponse(vetEndpoint.apiCreateVet(vet));

        createdVet.setFirstName("Adrian");
        createdVet.setLastName("Chikini");
        System.out.println(createdVet.toString());

        Integer statusCode = vetEndpoint.getStatusCodeFromResponse(vetEndpoint.apiEditPet(createdVet));

        assertThat(statusCode).isEqualTo(204);
    }

    @Test
    public void editSpecialty(){
        ApiSpeciality spec = specEndpoint.getDefaultSpec();
        createdSpec = specEndpoint.getSpecObjectFromResponse(specEndpoint.apiCreateSpecialty(spec));
        createdSpec.setName("editedSpec");
        Integer statusCode = specEndpoint.getStatusCodeFromResponse(specEndpoint.apiEditSpecialty(createdSpec));
        assertThat(statusCode).isEqualTo(204);

    }

    //Visits tests

    @Test
    public void createNewVisit(){
        owner =  ownerEndpoint.CreateDefaultOwner();
        petType = petTypeEndpoint.createDefaultType();
        ApiPet newPet = petEndpoint.getDefaultPet();

        newPet.setApiOwner(owner);
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        ApiVisitPetObj visit = new ApiVisitPetObj();
        visit.setDate("2020/10/12");
        visit.setDescription("Some description");
        visit.setPet(createdPet);
        System.out.println(visit.toString());


//       Integer statusCode = visitEndpoint.getStatusCodeFromResponse(visitEndpoint.apiCreateVisit(visit));
//       System.out.println(statusCode);
        visitEndpoint.apiCreateVisit(visit);
    }

}
