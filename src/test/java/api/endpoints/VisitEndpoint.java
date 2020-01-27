package api.endpoints;

import api.objects.ApiVeterinarian;
import api.objects.ApiVisit;
import api.objects.ApiVisitPetObj;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class VisitEndpoint extends EndpointBase{
    private final String ENDPOINT = "/visits";

    public ApiVisitPetObj getVisitObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiVisitPetObj.class);
    }

    public void apiCreateVisit(ApiVisitPetObj visit){
         RestAssured.given()
                .contentType(ContentType.JSON)
                .body(visit)
                .when()
                .post(ENDPOINT)
                .then()
                .log().all();
    }

}
