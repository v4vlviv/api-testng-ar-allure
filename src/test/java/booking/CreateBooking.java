package booking;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import poc.BookingDates;
import poc.BookingDetails;

import utility.ExcelLib;

public class CreateBooking extends BaseTest {
	
	public static String newID = "";
	
	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
		return xl.getTestdata();
	}
    
	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs") 
	public void createNewBooking(String firstname, 
							  String lastname,
							  String totalprice, 
							  String depositpaid, 
							  String checkin, 
							  String checkout, 
							  String additionalneeds, String dontUseThis
							  ){
		
		step("Starting the test to create new details");
		/*******************************************************
		 * Send a POST request to /booking/{id}
		 * and check that the response has HTTP status code 200
		 ******************************************************/
		
		//Sending the GET request for a specific booking id and receiving the response
		step("Posting a new booking detail");
		
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setFirstname(firstname);
		bookingDetails.setLastname(lastname);
		bookingDetails.setTotalprice(Integer.parseInt(totalprice));
		bookingDetails.setDepositpaid(Boolean.parseBoolean(depositpaid));
		bookingDetails.setAdditionalneeds(additionalneeds);
		
		BookingDates bookingDates = new BookingDates();
		bookingDates.setCheckin(checkin);
		bookingDates.setCheckout(checkout);
		bookingDetails.setBookingdates(bookingDates);
				
		step("Sending the POST request to create new booking");
		Response response = given().
								spec(requestSpec).
								contentType("application/json").
					            body(bookingDetails).log().body().
					        when().
					        	post("/booking");
		
		//Verify the response code
		step("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);
		
		
		//To get the newly created bookign id
		System.out.println(response.then().extract().path("bookingid").toString());
		newID = response.then().extract().path("bookingid").toString();
		step("Retrieved booking id : "+response.then().extract().path("bookingid"));
		
	}	
}
