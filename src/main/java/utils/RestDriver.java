package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.apache.regexp.RE;

import java.util.HashMap;
import java.util.Properties;

public class RestDriver {

    RequestSpecification requestSpecification;
    RequestSpecBuilder requestSpecBuilder;

    public RestDriver(Properties properties) {

        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setBaseUri(properties.getProperty("baseUrl"));
        requestSpecBuilder.setBasePath(properties.getProperty("basePath"));
        requestSpecBuilder.setUrlEncodingEnabled(false);
        requestSpecBuilder.setAccept(ContentType.JSON);
        requestSpecification = requestSpecBuilder.build();

    }

    public Response postRequestWithFormParams(String endPoint, HashMap formParams){
       return SerenityRest.given().spec(requestSpecification).contentType("application/x-www-form-urlencoded").formParams(formParams).log().all().post(endPoint);
    }

    /**
     * This method makes a get request for the endpoint specified
     *
     * @param endpoint - get request endPoint
     * @return - models.response object of get request
     */
    public Response getRequest(String endpoint) {
        return SerenityRest.given().spec(requestSpecification).log().all().get(endpoint);
    }

    /**
     * This method makes a get request with specified query and path parameters for the endpoint specified
     *
     * @param endpoint    - get request endPoint
     * @param queryParams - get request query parameters
     * @param pathParams  - get request path parameters
     * @return - models.response object of get request
     */
    public Response getRequestWithPathAndQueryParams(String endpoint, HashMap queryParams, HashMap pathParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().queryParams(queryParams).pathParams(pathParams).get(endpoint);
    }

    /**
     * This method makes a get request with specified query parameters for the endpoint specified
     *
     * @param endpoint    - get request endPoint
     * @param queryParams - get request query parameters
     * @return - models.response object of get request
     */
    public Response getRequestWithQueryParams(String endpoint, HashMap queryParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().queryParams(queryParams).get(endpoint);
    }


    /**
     * This method makes a get request with specified query parameters for the endpoint specified
     *
     * @param endpoint   - get request endPoint
     * @param pathParams - get request query parameters
     * @return - models.response object of get request
     */
    public Response getRequestWithPathParams(String endpoint, HashMap pathParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().pathParams(pathParams).get(endpoint);
    }


    /**
     * This method makes a post request with specified requestBody for the endpoint specified
     *
     * @param endPoint    - post request endPoint
     * @param requestBody - requestBody
     * @return - models.response object of get request
     */
    public Response postRequest(String endPoint, Object requestBody) {
        return SerenityRest.given().spec(requestSpecification).log().all().body(requestBody).post(endPoint);
    }


    /**
     * This method makes a post request with specified requestBody for the endpoint specified
     *
     * @param endPoint    - post request endPoint
     * @param requestBody - requestBody
     * @return - models.response object of get request
     */
    public Response postRequestWithQueryParamsAndCookies(String endPoint, Object requestBody,HashMap queryParams,HashMap cookies) {
        return SerenityRest.given().spec(requestSpecification).log().all().body(requestBody).queryParams(queryParams).cookies(cookies).post(endPoint);
    }

    public Response postRequestWithQueryParams(String endPoint, Object requestBody,HashMap queryParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().body(requestBody).queryParams(queryParams).post(endPoint);
    }


    /**
     * This method makes a get request with specified query parameters for the endpoint specified
     *
     * @param endpoint   - put request endPoint
     * @param pathParams - get request query parameters
     * @return - models.response object of get request
     */
    public Response putRequestWithPathParams(String endpoint, Object requestBody, HashMap pathParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().body(requestBody).pathParams(pathParams).put(endpoint);
    }


    /**
     * This method makes a get request with specified query parameters for the endpoint specified
     *
     * @param endpoint   - delete request endPoint
     * @param pathParams - get request query parameters
     * @return - models.response object of get request
     */
    public Response deleteRequestWithPathParams(String endpoint, HashMap pathParams) {
        return SerenityRest.given().spec(requestSpecification).log().all().pathParams(pathParams).delete(endpoint);
    }
}