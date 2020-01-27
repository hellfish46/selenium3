package api.endpoints;

import api.objects.ApiOwner;
import api.objects.ApiVeterinarian;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VeterinarianEndpoint extends EndpointBase {

    private final String ENDPOINT = "/vets";

    public ApiVeterinarian getVetObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiVeterinarian.class);
    }


    public ApiVeterinarian getDefaultVet(){
        ApiVeterinarian defVet = new ApiVeterinarian();
        defVet.setFirstName("Maxim");
        defVet.setLastName("Fedorkov");
        return defVet;
    }

    public Response apiCreateVet(ApiVeterinarian vet){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vet)
                .when()
                .post(ENDPOINT);
    }

    public Response apiGetVet(Integer vetId){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT + "/" + vetId);
    }

    public Response apiDeleteVet(Integer vetId){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT + "/" + vetId);
    }

    public Response apiEditPet(ApiVeterinarian vet){
         return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(vet)
                .when()
                .put(ENDPOINT + "/" + vet.getId());

    }

}
