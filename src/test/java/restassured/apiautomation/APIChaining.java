package restassured.apiautomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIChaining {

String userId;	
	@Test
	public void verifyPostApi() {
        RestAssured.baseURI = "https://reqres.in";
        Response response = given().body(UserData.getUserData()).when().post("/api/users/").then().statusLine("HTTP/1.1 201 Created").statusCode(201)
                
        		.body("createdAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
                .extract().response();

        String responseBody = response.asPrettyString();
        System.out.println("Response Body: " + responseBody);
        userId = response.jsonPath().getString("id");
        System.out.println(userId);
    }
	
	 @Test(dependsOnMethods = "verifyPostApi")
	    public void verifyPutApi() {
	    	
	    RestAssured.baseURI="https://reqres.in";
	    Response response=given().body("\"name\": \"Tanishka1\",\r\n"
	    		+ "\"job\" : \"Engineer123\"").when().put("api/users/" + userId).then().statusCode(200).and().
	    		extract().response();
	    
	    System.out.println(response.asPrettyString());
	    System.out.println(userId);
	    		
	    	
	    }
	 
	 @Test(dependsOnMethods = "verifyPostApi")
	    public void verifyDelApi() {
	    	
	    RestAssured.baseURI="https://reqres.in";
	    Response response=given().when().delete("api/users/" + userId).then().statusCode(204).and().
	    		extract().response();
	    
	    System.out.println(response.asPrettyString());
	    System.out.println(userId);
	    		
	    	
	    }
	 
	
	 }
	 
	 
	 

