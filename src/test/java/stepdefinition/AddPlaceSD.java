package stepdefinition;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.Location;

public class AddPlaceSD {

	
	ResponseSpecification res;
	RequestSpecification request ;
	Response response;
	
	@Given("Add Place Payload")
	public void add_place_payload() {
		
	

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
					.log(LogDetail.ALL).build();
			res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).log(LogDetail.ALL)
					.build();

	
			AddPlacePojo addPlace = new AddPlacePojo();
			Location loc = new Location();
			loc.setLat(-38.383494);
			loc.setLng(33.427362);
			List<String> typelist = new ArrayList<String>();
			typelist.add("shoe park");
			typelist.add("shop");
			addPlace.setAccuracy(50);
			addPlace.setName("Frontline house");
			addPlace.setPhone_number("(+91) 983 893 3937");
			addPlace.setAddress("29, side layout, cohen 09");
			addPlace.setWebsite("http://google.com");
			addPlace.setLanguage("French-IN");
			addPlace.setLocation(loc);
			addPlace.setTypes(typelist);

			 request = given().spec(req);
		
		
	}
	@When("User call {string} with  Post Http request")
	public void user_call_with_post_http_request(String string) {
		
		 response = request.when().post("/maps/api/place/add/json").then().spec(res).extract().response();
				
		
	}
	@Then("Api call should be success with {int} Status code")
	public void api_call_should_be_success_with_status_code(Integer int1) {
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String statusKey, String statusValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		Assert.assertEquals(js.get(statusKey).toString(),(statusValue));
		
		
	}



}
