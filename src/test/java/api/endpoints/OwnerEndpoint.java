package api.endpoints;

import api.objects.ApiOwner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.List;

public class OwnerEndpoint extends EndpointBase {


    private final String ENDPOINT = "/owners";



    public ApiOwner getOwnerObjectFromResponse(Response response){
        return response.then().extract().body().as(ApiOwner.class);
    }


    public ApiOwner CreateDefaultOwner(){
        ApiOwner apiOwner = new ApiOwner();
        apiOwner.setFirstName("Bob");
        apiOwner.setLastName("Jordan");
        apiOwner.setAddress("Marksa Street, 39");
        apiOwner.setCity("San Diego");
        apiOwner.setTelephone("093453445");
        return getOwnerObjectFromResponse(apiOwnerCreationViaObj(apiOwner));
    }

    public Response apiOwnerCreationViaObj(ApiOwner apiOwner){

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apiOwner)
                .when()
                .post(ENDPOINT);

        return response;
    }

    public Response apiOwnerDeletingViaId(int id){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ENDPOINT + "/" + id);
    }

    public List<ApiOwner> apiOwnerGetAll(){
        List<ApiOwner> allOwners = Arrays.asList(RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT)
                .then()
                .statusCode(200)
                .extract().body()
                .as(ApiOwner[].class));

        return allOwners;

    }

    public Response apiOwnerGet(int id){
       Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ENDPOINT + "/" + id);

        return response;


    }

    public Response apiEditOwnerViaObject(ApiOwner apiOwner){
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apiOwner)
                .when()
                .put(ENDPOINT + "/" + apiOwner.getId());
        return response;

    }


}
