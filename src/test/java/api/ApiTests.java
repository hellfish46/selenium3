package api;

import api.endpoints.OwnerEndpoint;
import api.endpoints.PetEndpoint;
import api.endpoints.TypeEndpoint;
import api.objects.ApiOwner;
import api.objects.ApiPet;
import api.objects.ApiPetId;
import api.objects.ApiType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTests {

//    @BeforeClass
//    public void setUp() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 9966;
//        RestAssured.basePath = "/petclinic/api";
//    }


//Owner tests
    @Test
    public void creatingNewOwner(){
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setFirstName("AAAJenkins");
        apiOwner.setLastName("Studio");
        apiOwner.setAddress("huliano grimau");
        apiOwner.setCity("Dnipro2");
        apiOwner.setTelephone("0923456789");
        ApiOwner createdOwner = ownerEndpoint.getOwnerObjectFromResponse(ownerEndpoint.apiOwnerCreationViaObj(apiOwner));
        assertThat(apiOwner).isEqualTo(createdOwner);
    }

    @Test
    public void creatingNewOwnerWithLongPhone(){
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
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
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
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
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
        ApiOwner defaultOwner = ownerEndpoint.CreateDefaultOwner();
        Response response = ownerEndpoint.apiOwnerDeletingViaId(defaultOwner.getId());
        Integer statusCode = ownerEndpoint.getStatusCodeFromResponse(response);
        assertThat(statusCode).isEqualTo(204);
    }



    @Test
    public void getAllOwners(){
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
        List<ApiOwner> allOwners = ownerEndpoint.apiOwnerGetAll();
        System.out.println(allOwners.size());

    }

    @Test
    public void getOwner(){
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
        Response response = ownerEndpoint.apiOwnerGet(6);
        ApiOwner owner = ownerEndpoint.getOwnerObjectFromResponse(response);
        System.out.println(owner.toString());
    }

    @Test
    public void editOwner(){
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();
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
    public void creatingNewPetType(){
        TypeEndpoint petTypeEndpoint = new TypeEndpoint();
        ApiType petType = new ApiType();
        petType.setName("wife");
        //ApiType createdPetType = petTypeEndpoint.apiTypeCreation(petType);

        //assertThat(createdPetType).isEqualTo(petType);
    }

    //Pet tests
    @Test
    public void createNewPet(){
        PetEndpoint petEndpoint = new PetEndpoint();
        TypeEndpoint petTypeEndpoint = new TypeEndpoint();
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();

        Response response = ownerEndpoint.apiOwnerGet(11);
        ApiOwner owner = ownerEndpoint.getOwnerObjectFromResponse(response);

        ApiType petType = petTypeEndpoint.getApiTypeViaId(1);

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
        PetEndpoint petEndpoint = new PetEndpoint();
        TypeEndpoint petTypeEndpoint = new TypeEndpoint();
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();

        ApiOwner owner =  ownerEndpoint.CreateDefaultOwner();
        ApiType petType = petTypeEndpoint.createDefaultType();
        ApiPet newPet = petEndpoint.getDefaultPet();

        newPet.setApiOwner(owner);
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        ApiPet createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        Response response = petEndpoint.apiDeletePetViaObject(createdPet);
        Integer statusCode = petEndpoint.getStatusCodeFromResponse(response);
        assertThat(statusCode).isEqualTo(204);
    }

    @Test
    public void editPet(){
        PetEndpoint petEndpoint = new PetEndpoint();
        TypeEndpoint petTypeEndpoint = new TypeEndpoint();
        OwnerEndpoint ownerEndpoint = new OwnerEndpoint();

        ApiOwner owner =  ownerEndpoint.CreateDefaultOwner();
        ApiType petType = petTypeEndpoint.createDefaultType();
        ApiPet newPet = petEndpoint.getDefaultPet();

        newPet.setApiOwner(owner);
        newPet.setApiType(petType);

        Response petResponse = petEndpoint.apiCreatePetViaObj(newPet);
        ApiPet createdPet = petEndpoint.getPetObjectFromResponse(petResponse);

        createdPet.setName("changedName");
        createdPet.setBirthDate("1990/12/01");

        petEndpoint.apiEditPetViaObj(createdPet);
        ApiPet editedPet = petEndpoint.getPetObjectFromResponse(petEndpoint.apiGetPetViaObj(createdPet));

        assertThat(editedPet).isEqualTo(createdPet);
    }

//    @Test
//    public void createNewOwner(){
//        ApiOwner apiOwner = new ApiOwner();
//        apiOwner.setFirstName("Adrian324");
//        apiOwner.setLastName("Cho3242");
//        apiOwner.setAddress("Some street 9121b");
//        apiOwner.setCity("Dnipro");
//        apiOwner.setTelephone("09091212");
//        ApiOwner createdApiOwnerObj = RestAssured.given()
//                .contentType(ContentType.JSON)
//                //.baseUri("http://localhost:9966/petclinic")
//                .body(apiOwner)
//          .when()
//                .post("/owners")
//          .then()
//                .log().all()
//                .statusCode(201)
//                .extract().body().as(ApiOwner.class);
//        System.out.println(createdApiOwnerObj);
//
//    }
//
//    @Test
//    public void newPetTypeCreate(){
//        ApiType petApiType = new ApiType();
//        petApiType.setName("Dino12");
//        ApiType createdApiType = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(petApiType)
//           .when()
//                .post("/pettypes")
//           .then()
//                .log().all()
//                .extract().body().as(ApiType.class);
//        System.out.println(createdApiType);
//
//    }
//    @Test
//    public void deleteOwner(){
//        int id = 61;
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .when()
//                .delete("/owners/" + id)
//                .then()
//                .log().all();
//
//
//    }


}
