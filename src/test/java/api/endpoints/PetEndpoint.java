package api.endpoints;

import api.objects.ApiOwner;
import api.objects.ApiPet;
import api.objects.ApiPetId;
import api.objects.ApiType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;

public class PetEndpoint extends EndpointBase{

    private final String PETENDPOINT = "/pets";

    private final String TYPEPETENDPOINT = "/pettypes";

    public ApiPet getPetObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiPet.class);
    }



    public ApiPet getDefaultPet(){
        ApiPet newPet = new ApiPet();

        newPet.setBirthDate("2020/01/01");
        newPet.setName("Marisa");
        return newPet;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    public Response apiCreatePetViaObj(ApiPet pet){
       pet.getApiOwner().setApiPets(new ArrayList<>());
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(PETENDPOINT);
    }

    public Response apiDeletePetViaObject(ApiPet pet){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PETENDPOINT + "/" + pet.getId());
        return response;


    }

    public Response apiEditPetViaObj(ApiPet pet){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put(PETENDPOINT + "/" + pet.getId());
        return response;

    }

    public Response apiGetPetViaObj(ApiPet pet){
       return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PETENDPOINT + "/" + pet.getId());

    }








}
