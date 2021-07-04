package services.paraBank;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.response.LoginResponsePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.base.BaseService;

import java.util.HashMap;

public class LoginService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    Response response;
    public static int loginCustomerId;



    @Given("I am logged in with user {string} and password {string}")
    public void iAmLoggedInWithUserAndPassword(String userName, String password) {
        String endpoint = properties.getProperty("loginEndpoint") + "/{username}/{password}";
        HashMap<String,String> pathParams = new HashMap<>();
        pathParams.put("username",properties.getProperty("username").toString());
        pathParams.put("password",properties.getProperty("password").toString());
        response = restDriver.getRequestWithPathParams(endpoint,pathParams);
        logger.info("Response Code:" + response.statusCode());
        logger.info("Response body:" + response.getBody().asPrettyString());
        logger.info("Response headers:" + response.getCookie("JSESSIONID"));
        LoginResponsePojo loginResponsePojo = response.getBody().as(LoginResponsePojo.class);
        loginCustomerId = loginResponsePojo.getId();

    }






}
