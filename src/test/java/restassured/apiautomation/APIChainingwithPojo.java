package restassured.apiautomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIChainingwithPojo {
	UserDataPojo datapojo=new UserDataPojo();
	
	
String userId;	
	@Test
	public void verifyPostApi() {
		//Now setting the value of the pojo class first we will set the value
		datapojo.setName("Rajesh");
		datapojo.setJob("QA");
		// Convert the POJO to JSON using Gson
		Gson gson = new Gson();
		String jsonBody = gson.toJson(datapojo);
	
        RestAssured.baseURI = "https://reqres.in";
        Response response = given().body(jsonBody).when().post("/api/users/").then().statusCode(201)
                
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
	 
	 
	 

