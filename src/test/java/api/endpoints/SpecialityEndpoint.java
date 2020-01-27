package api.endpoints;

import api.objects.ApiOwner;
import api.objects.ApiSpeciality;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpecialityEndpoint extends EndpointBase{

    private final String ENDPOINT = "/specialties";



    public ApiSpeciality getSpecObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiSpeciality.class);
    }

    public ApiSpeciality getDefaultSpec(){
        ApiSpeciality spec = new ApiSpeciality();
        spec.setName("bones");
        return spec;
    }

    public Response apiCreateSpecialty(ApiSpeciality spec){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(spec)
                .when()
                .post(ENDPOINT);
    }

    public Response apiDeleteSpecialty(Integer specId){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT + "/" + specId);
    }

    public Response apiEditSpecialty(ApiSpeciality spec){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(spec)
                .when()
                .put(ENDPOINT + "/" + spec.getId());
    }

}
