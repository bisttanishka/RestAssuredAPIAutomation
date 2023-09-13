package restassured.apiautomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetApiTest {

    @Test
    public void verifyGetApi() {
        RestAssured.baseURI = "https://reqres.in";
        Response response = given().queryParam("page", "2").when().get("/api/users/").then().statusCode(200)
                .body("total", equalTo(12))
                .body("data.size()", equalTo(6))
                .body("data[5].email", equalTo("rachel.howell@reqres.in"))
                .body("support.text", containsString("contributions"))
                .extract().response();

        String responseBody = response.asString();
        System.out.println("Response Body: " + responseBody);
    }
}

/*The code you provided is a Java test class using the Rest Assured library for API testing. It sends a GET request to the "https://reqres.in/api/users/" endpoint and then performs various assertions on the response.

Here's a breakdown of what this code does:

Setting the Base URI: RestAssured.baseURI = "https://reqres.in"; sets the base URI for the API requests. All subsequent requests will be made relative to this base URI.

Sending a GET Request: given().queryParam("page", "2").when().get("/api/users/"); constructs and sends a GET request to the specified endpoint ("/api/users/") with a query parameter "page" set to "2."

Response Assertions:

.then().statusCode(200) asserts that the HTTP status code of the response is 200 (OK).
.body("total", equalTo(12)) asserts that the "total" field in the response body is equal to 12.
.body("data[5].email", equalTo("rachel.howell@reqres.in")) asserts that the email address of the 6th element (index 5) in the "data" array of the response body is equal to "rachel.howell@reqres.in."
.body("support.text", containsString("contributions")) asserts that the "text" field in the "support" object of the response body contains the substring "contributions."
Extracting the Response: .extract().response(); extracts the response object so that you can work with it further if needed.

Printing the Response Body: String responseBody = response.asString(); converts the response body to a string, and System.out.println("Response Body: " + responseBody); prints the response body to the console.

In summary, this code sends a GET request to a specific API endpoint, verifies the HTTP status code and specific fields in the JSON response, and then prints the response body to the console. It demonstrates a basic API test using Rest Assured with response validation.*/