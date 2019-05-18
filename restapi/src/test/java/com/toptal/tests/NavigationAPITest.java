package com.toptal.tests;

import com.toptal.rest.utils.Config;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.toptal.rest.utils.RestUtil.*;
import static io.restassured.RestAssured.*;

/**
 * @author askeledzija
 */

public class NavigationAPITest {

    private static final Logger logger = LogManager.getLogger(NavigationAPITest.class);
    String dId = null;
    //First, I declared Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object

    @BeforeTest
    public void setup() {
        baseURI = Config.USER_BASE_URI; //Setup Base URI
        basePath = Config.V5_BASEPATH; //Setup Base Path
        given().contentType(ContentType.JSON); //Setup Content Type
    }

    //-122.42,37.78;-77.03,38.91
    @Test(description = "TC06 Retrieve directions - Correct data - OK 200", groups = {"Navigation service"}, priority = 6)
    public void TC06_GET_Directions() throws InterruptedException {
        createPathRetrieveDirections("-122.42,37.78", "-77.03,38.91", Config.USER_ACCESS_TOKEN);
        res = getResponse();
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath
        checkStatusIs200(res);
        //Get Headers
        logger.info(res.getHeaders());
    }

    @Test(description = "TC07 Retrieve directions - Invalide data - Error code 422", groups = {"Navigation service"}, priority = 7)
    public void TC07_GET_DirectionsInvalidData() throws InterruptedException {
        createPathRetrieveDirections("-122.42,37.78" + "xxx", "-77.03,38.91", Config.USER_ACCESS_TOKEN);
        res = getResponse();
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath

        res.then().statusCode(422);

    }


    @Test(description = "TC08 Retrieve directions - Incorect format - Not Found 404", groups = {"Navigation service"}, priority = 8)
    public void TC08_GET_DirectionsNotFound() throws InterruptedException {
        createPathRetrieveDirections("//-122.42,37.78", "-77.03,38.91", Config.USER_ACCESS_TOKEN);
        res = getResponse();
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath

        res.then().statusCode(404);

    }

    @Test(description = "TC09 Retrieve directions - Incorect format - Unauthorized 401", groups = {"Navigation service"}, priority = 9)
    public void TC09_GET_DirectionsUnauthorized() throws InterruptedException {
        createPathRetrieveDirections("-122.42,37.78", "-77.03,38.91", Config.USER_ACCESS_TOKEN + "x");
        res = getResponse();
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath

        res.then().statusCode(401);

    }


    @AfterTest
    public void afterTest() {
        //Reset Values
        resetBaseURI();
        resetBasePath();
    }
}

