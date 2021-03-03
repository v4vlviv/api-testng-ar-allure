package booking;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utility.FrameworkUtility;

import static io.qameta.allure.Allure.step;


public abstract class BaseTest extends FrameworkUtility {
	
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;
	
	@BeforeSuite
	public void setBaseURI() {
		
        step("The base URI is : "+readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder().
                		setBaseUri(readConfigurationFile("Base_URI")).
                		build();
        
	}

	/*****************************************************************************************************************/
//	@AfterSuite
	public void afterSuite() {

	}
	
	/****************************************************************************************************************/
//	@BeforeClass
	public void beforeClass() {
	}
	
	/****************************************************************************************************************/	
//	@AfterClass
	public void afterClass(){

	}
	
	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
    	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();        
	}

//	@AfterMethod
	public void afterMethod() {

	}

}
/*****************************************************************************************************************/