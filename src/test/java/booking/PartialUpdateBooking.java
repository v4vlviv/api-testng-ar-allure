package booking;

import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class PartialUpdateBooking extends BaseTest {
	
	@Test(description="To update the details of the booking IDs") 
	public void updateBooking(){
		
		step("Starting the test to update details");
		/*******************************************************
		 * Send a PUT request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the PUT request for a specific booking id and receiving the response after updating the detals
		step("PUT update booking detail");
		
		//To get the auth token
		String newAuthToken = AuthToken.post_CreateAuth();
		step("Auth token is : "+newAuthToken);
		
		//Created a new booking
		CreateBooking createBooking = new CreateBooking();
		createBooking.createNewBooking("Sanda", "Varys", "114", "false", "2018-01-03", "2018-01-05", "Dinner", null);
		String IDtoUpdate = createBooking.newID;
		step("New Booking ID created is : "+IDtoUpdate);
		
		//Setting the alue to be sent in the patch request
		String setValue = "{\"firstname\":\"sam\"}";
		
		String cookieValue = "token="+newAuthToken;
		System.out.println("cookieValue is :"+cookieValue);
		
		//Sending the PATCH request
		step("Sending the PATCH request to partially update the booking detail of booking id : "+IDtoUpdate);
		Response response = given().
			spec(requestSpec).
			header("Content-Type", "application/json").
			header("Accept", "application/json").
			header("Cookie", cookieValue).
	        pathParam("id", IDtoUpdate).
	        body(setValue).log().body().
	    when().
			patch("/booking/{id}");
		
		//Verify the response code
		step("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
	}
}
