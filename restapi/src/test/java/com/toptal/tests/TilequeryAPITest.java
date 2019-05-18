package com.toptal.tests;

import com.jayway.jsonpath.JsonPath;
import com.toptal.rest.utils.Config;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.toptal.rest.utils.RestUtil.*;

/**
 * @author askeledzija
 */

public class TilequeryAPITest {

    private static final Logger logger = LogManager.getLogger(TilequeryAPITest.class);
    String dId = null;
    //First, I declared Response and JsonPath objects.

    @BeforeTest
    public void setup() {
    }

    @Test(description = "TC10 Tilequery - Retrieve - 200", groups = {"Maps service"}, priority = 10)
    public void TC10_GET_200() throws IOException, InterruptedException {

        Response response = getResponseOkHttpClientNoHeader("mapbox.mapbox-streets-v8", "-122.42901", "37.80633", "20", Config.USER_ACCESS_TOKEN);
        logger.info(response.body().string());
        Assert.assertEquals(response.code(), 200);
        //Get Headers
        logger.info(response.headers());
    }


    @Test(description = "TC10 Tilequery - Is Modified since - 304", groups = {"Maps service"}, priority = 11)
    public void TC11_GET_304() throws IOException, InterruptedException {

        Response response = getResponseOkHttpClient("mapbox.mapbox-streets-v8", "-122.42901", "37.80633", "20", Config.USER_ACCESS_TOKEN);
        logger.info(response.body().string());

        Assert.assertEquals(response.code(), 304);

    }


    @Test(description = "TC11 Tilequery - Unauthorized - 401", groups = {"Maps service"}, priority = 12)
    public void TC12_GET_401() throws IOException, InterruptedException {

        Response response = getResponseOkHttpClient("mapbox.mapbox-streets-v8", "-122.42901", "37.80633", "20", Config.USER_ACCESS_TOKEN + "x");

        //https://github.com/json-path/JsonPath
        String message = JsonPath.read(response.body().string(), "$.message");
        logger.info(message);

        Assert.assertEquals(message, "Not Authorized - Invalid Token", "Message is not as expected!");
        Assert.assertEquals(response.code(), 401);


    }

    @AfterTest
    public void afterTest() {
        //Reset Values
        resetBaseURI();
        resetBasePath();
    }
}

