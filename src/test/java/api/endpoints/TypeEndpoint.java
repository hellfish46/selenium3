package api.endpoints;

import api.objects.ApiOwner;
import api.objects.ApiType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TypeEndpoint extends EndpointBase{
    private final String ENDPOINT = "/pettypes";




    public ApiType getTypeObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiType.class);
    }

    public ApiType createDefaultType(){
        ApiType type = new ApiType();
        type.setName("Duck");
        return getTypeObjectFromResponse(apiTypeCreation(type));
    }

    public Response apiTypeCreation(ApiType petType){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petType)
                .when()
                .post(ENDPOINT);
        return response;
    }

    public Response getApiTypeViaId(Integer typeId){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT + "/" + typeId);

        return response;
    }

    public Response apiDeleteTypeViaId(Integer typeId){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT + "/" + typeId);
    }

    public Response apiEditType(ApiType type){
       return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .when()
                .put(ENDPOINT + "/" + type.getId());
    }



}
