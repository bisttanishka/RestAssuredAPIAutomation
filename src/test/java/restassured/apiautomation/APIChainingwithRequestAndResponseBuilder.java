package restassured.apiautomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIChainingwithRequestAndResponseBuilder {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
String userId;	
@BeforeTest
public void setupRequestSpec() {
	
	requestSpec= new RequestSpecBuilder().setBaseUri("https://reqres.in").build();
	System.out.println("beforeclass call");
	
}
@BeforeTest
public void setupResponseSpec() {
	responseSpec= new ResponseSpecBuilder().expectStatusCode(200).build();
}

	@Test
	public void verifyPostApi() {
		
       // RestAssured.baseURI = "https://reqres.in";
        Response response = given().spec(requestSpec).body(UserData.getUserData()).when().post("/api/users/").then().statusLine("HTTP/1.1 201 Created").statusCode(201)
                
        		.body("createdAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
                .extract().response();

        String responseBody = response.asPrettyString();
        System.out.println("Response Body: " + responseBody);
        userId = response.jsonPath().getString("id");
        System.out.println(userId);
    }
	
	 @Test(dependsOnMethods = "verifyPostApi")
	    public void verifyPutApi() {
	    	
	   // RestAssured.baseURI="https://reqres.in";
	    Response response=given().spec(requestSpec).body("\"name\": \"Tanishka1\",\r\n"
	    		+ "\"job\" : \"Engineer123\"").when().put("api/users/" + userId).then().spec(responseSpec).and().
	    		extract().response();
	    
	    System.out.println(response.asPrettyString());
	    System.out.println(userId);
	    		
	    	
	    }
	 
	 @Test(dependsOnMethods = "verifyPostApi")
	    public void verifyDelApi() {
	    	
	  //  RestAssured.baseURI="https://reqres.in";
	    Response response=given().spec(requestSpec).when().delete("api/users/" + userId).then().statusCode(204).and().
	    		extract().response();
	    
	    System.out.println(response.asPrettyString());
	    System.out.println(userId);
	    		
	    	
	    }
	 
	
	 }
	 
	 
	 

