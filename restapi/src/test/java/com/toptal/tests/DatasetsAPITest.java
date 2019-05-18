package com.toptal.tests;

import com.toptal.rest.utils.Config;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.toptal.rest.utils.RestUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author askeledzija
 */

public class DatasetsAPITest {

    private static final Logger logger = LogManager.getLogger(DatasetsAPITest.class);
    String dId = null;
    //First, I declared Response and JsonPath objects.
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object

    @BeforeTest
    public void setup() {
        setBaseURI(Config.USER_BASE_URI); //Setup Base URI
        setBasePath(Config.V1_BASEPATH); //Setup Base Path
        given().contentType(ContentType.JSON); //Setup Content Type
    }


    @Test(description = "TC01 Create a dataset - POST - 200", groups = {"Maps service"}, priority = 1)
    public void TC01_POST_CreateDataset() throws InterruptedException {

        String body = "{\n  \"name\": \"Andrej Dataset\",\n  \"description\": \"Toptal dataset description\"\n}";

        res = postResponse(body);
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath
        checkStatusIs200(res);

        dId = jp.get("id");
        logger.info(dId);

        res.then().statusCode(200);
        //Get Headers
        logger.info(res.getHeaders());
    }


    @Test(description = "TC02 Retrieve a dataset by Id - GET - 200", groups = {"MapBox"}, priority = 2)
    public void TC02_GET_RetrieveDatasetById() throws InterruptedException {

        createPathDatasetsById(Config.USER_USERNAME, Config.USER_ACCESS_TOKEN, dId);
        res = getResponseWithHeaders();
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath
        checkStatusIs200(res);

        res.then()
                .body("owner", equalTo("askeledzija"))
                .body("id", is(dId))
                .body("name", is("Andrej Dataset"))
                .body("features", is(0))
                .body("size", is(0))
                .statusCode(200);

    }


    @Test(description = "TC03 Update a dataset by Id - PATCH - 200", groups = {"Maps service"}, priority = 3)
    public void TC03_PATCH_UpdateDatasetById() throws InterruptedException {

        String body = "{\n  \"name\": \"Andrej Dataset\",\n  \"description\": \"Toptal dataset description - Modified\"\n}";

        res = patchResponse(body, dId);
        res.body().prettyPrint(); //Prints JSON
        jp = getJsonPath(res); //Get JsonPath
        checkStatusIs200(res);

        res.then()
                .body("description", equalTo("Toptal dataset description - Modified"))
                .body("id", is(dId))
                .statusCode(200);

    }


    @Test(description = "TC04 Delete a dataset by Id - DELETE - 204", groups = {"Maps service"}, priority = 4)
    public void TC04_DELETE_DeleteDatasetById() throws InterruptedException, IOException {
        okhttp3.Response response = deleteResponseOkHttpClient(Config.USER_USERNAME, Config.USER_ACCESS_TOKEN, dId);
        //https://github.com/json-path/JsonPath
        Assert.assertEquals(response.code(), 204);
    }


    //ToDo - Excluded - Include and Retest once REST0001 is resolved
    @Test(description = "TC05 Check dataset is deleted - GET - 200", groups = {"MapBox"}, priority = 5, enabled = true)
    public void TC05_GET_CheckDatasetIsDeleted() throws InterruptedException {
        createPathDatasetsById(Config.USER_USERNAME, Config.USER_ACCESS_TOKEN, dId);
        res = getResponseWithHeaders();
        res.body().prettyPrint(); //Prints JSON
        res.then().statusCode(422);
    }


    @AfterTest
    public void afterTest() {
        //Reset Values
        resetBaseURI();
        resetBasePath();
    }


}

