package com.toptal.rest.utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.*;

/**
 * @author askeledzija
 */


public class RestUtil {
    //Global Setup Variables
    public static String path; //Rest request path

    private static final Logger logger = LogManager.getLogger(RestUtil.class);
    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
        //  logger.info("baseURI: " + baseURI + "\n");
    }

    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
        //  logger.info("basePath: " + basePath + "\n");
    }

    /*
    ***Sets ContentType***
    We should set content type as JSON or XML before starting the test
    */
    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }

    // DataSets path
    public static void createPathListDatasets(String username, String token) {
        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + username + "?" + "access_token=" + token;
        logger.info("path: " + path + "\n");
    }

    public static void createPathDatasetsById(String username, String token, String id) {
        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + username + "/" + id + "?" + "access_token=" + token;
        logger.info("path: " + path + "\n");
    }


    //Navigation path
    public static void createPathRetrieveDirections(String lon, String lat, String token) {
        path = Config.USER_BASE_URI + Config.V5_BASEPATH + "/mapbox/cycling/" + lon + ";" + lat + "?" + "access_token=" + token;
        logger.info("path: " + path + "\n");
    }


    public static void createPath(String endpoint) {
        path = "/" + endpoint;
        logger.info("path: " + baseURI + basePath + path + "\n");
    }

    //Tilesquery path
    public static void createPathTilesQuery(String mapId, String lon, String lat, String lim, String token) {
        path = "/" + mapId + "/tilequery/" + lon + "," + lat + ".json?" + "limit=" + lim + "&access_token=" + token;
        logger.info("path: " + baseURI + basePath + path + "\n");
    }


    /*
    Verify the http response status returned. Check Status Code is 200?
    We can use Rest Assured library's response's getStatusCode method
    */
    public static void checkStatusIs200(Response res) {
        Assert.assertEquals(200, res.getStatusCode(), "Status Check Failed!");
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI() {
        baseURI = null;
    }

    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath() {
        basePath = null;
    }


    /*
    ***search query path of first example***
    It is  equal to "barack obama/videos.json?num_of_videos=4"
    */
    public static void createSearchQueryPath(String searchTerm, String jsonPathTerm, String param, String paramValue) {

        path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
//        logger.info("path: " + path +"\n");
    }


    /*
    ***Returns response***
    We send "path" as a parameter to the Rest Assured'a "get" method
    and "get" method returns response of API
    */
    public static Response getResponse() {
        return get(path);
    }


    /*
   ***Returns response***
   We send "path" as a parameter to the Rest Assured'a "get" method with headers
   and "get" method returns response of API
   */
    public static Response getResponseWithHeaders() {
        return
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(path);

    }


    /*
      ***Returns response *** okhttp3.OkHttpClient
      We send "path" as a parameter to the Rest Assured'a "get" method with headers
      and "get" method returns response of API
      */
    public static okhttp3.Response getResponseOkHttpClient(String mapId, String lon, String lat, String lim, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String pattern = "EEE, dd MMM yyyy HH:mm:ss z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        logger.info("Current: " + date);
        logger.info("Example: Sun, 09 Dec 2018 04:22:02 GMT");

        path = Config.USER_BASE_URI + Config.V4_BASEPATH + "/" + mapId + "/tilequery/" + lon + "," + lat + ".json?" + "limit=" + lim + "&access_token=" + token;
        logger.info(path);
        Request request = new Request.Builder()
                .url(path)
                .get()
                .addHeader("If-Modified-Since", date)
                .build();
        return client.newCall(request).execute();
    }


    /*
    ***Returns response *** okhttp3.OkHttpClient
    We send "path" as a parameter to the Rest Assured'a "get" method with headers
    and "get" method returns response of API
    */
    public static okhttp3.Response deleteResponseOkHttpClient(String username, String token, String id) throws IOException {

        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + username + "/" + id + "?" + "access_token=" + token;
        logger.info(path);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(path)
                .delete(null)
                .addHeader("Content-Type", "application/json")
                .build();
        return client.newCall(request).execute();
    }


    /*
    ***Returns response *** okhttp3.OkHttpClient
    We send "path" as a parameter to the Rest Assured'a "get" method with headers
    and "get" method returns response of API
    */
    public static okhttp3.Response getResponseOkHttpClientNoHeader(String mapId, String lon, String lat, String lim, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();

        path = Config.USER_BASE_URI + Config.V4_BASEPATH + "/" + mapId + "/tilequery/" + lon + "," + lat + ".json?" + "limit=" + lim + "&access_token=" + token;
        logger.info(path);
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        return client.newCall(request).execute();
    }


    /*
     ***Returns Response object***
     * POST Method
     */
    public static Response postResponse(String body) {

        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + Config.USER_USERNAME + "?access_token=" + Config.USER_ACCESS_TOKEN;
        logger.info(path);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(path);
        //  logger.info("POST Response\n" + response.asString());
        return response;
    }


    //PATCH "https://api.mapbox.com/datasets/v1/askeledzija/{dataset_id}?access_token=YOUR_MAPBOX_ACCESS_TOKEN
    //This endpoint requires a token with datasets:write scope.
    //" \
    //  -d @data.json

    public static Response patchResponse(String body, String dId) {

        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + Config.USER_USERNAME + "/" + dId + "?access_token=" + Config.USER_ACCESS_TOKEN;
        logger.info(path);
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .patch(path);
        //  logger.info("POST Response\n" + response.asString());
        return response;
    }


    //DELETE "https://api.mapbox.com/datasets/v1/askeledzija/{dataset_id}?access_token=YOUR_MAPBOX_ACCESS_TOKEN
    //This endpoint requires a token with datasets:write scope.
    //" \
    //  -d @data.json

    public static Response deleteResponseById(String dId) {
        path = Config.USER_BASE_URI + Config.V1_BASEPATH + "/" + Config.USER_USERNAME + "/" + dId + "?access_token=" + Config.USER_ACCESS_TOKEN;
        logger.info(path);
        Response response =
                given()
                        .when()
                        .delete(path);
        logger.info("POST Response\n" + response.asString());
        return response;
    }


    /*
     ***Returns Response object***
     * DELETE Method
     */
    public static Response deleteResponse(String path) {
        Response response = delete(path);
        //   logger.info("DELETE Response\n" + response.asString());
        return response;
    }


    /*
     ***Returns JsonPath object***
     * First convert the API's response to String type with "asString()" method.
     * Then, send this String formatted json response to the JsonPath class and return the JsonPath
     */
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
//        logger.info("returned json: " + json +"\n");
        return new JsonPath(json);
    }


}