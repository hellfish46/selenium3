package api.endpoints;

import api.objects.ApiOwner;
import api.objects.ApiType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TypeEndpoint {
    private final String ENDPOINT = "/pettypes";
    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
    }

    public ApiType getTypeObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiType.class);
    }
    public Integer getStatusCodeFromResponse (Response response){
        return response.getStatusCode();
    }

    public ApiType createDefaultType(){
        ApiType type = new ApiType();
        type.setName("some type");
        return getTypeObjectFromResponse(apiTypeCreation(type));
    }

    public Response apiTypeCreation(ApiType petType){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petType)
                .when()
                .post(ENDPOINT);
//                .then()
//                .extract().body()
//                .as(ApiType.class);
        return response;
    }

    public ApiType getApiTypeViaId(Integer typeId){
        ApiType petType = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT + "/" + typeId)
                .then()
                .statusCode(200)
                .extract().body()
                .as(ApiType.class);
        return petType;
    }

    public void apiDeleteTypeViaId(Integer typeId){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT + "/" + typeId)
                .then()
                .statusCode(200);
    }

    public Integer apiEditType(ApiType type){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(type)
                .when()
                .put(ENDPOINT + "/" + type.getId());
        return response.getStatusCode();
    }



}
