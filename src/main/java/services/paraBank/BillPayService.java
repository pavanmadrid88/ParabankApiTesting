package services.paraBank;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.request.BillPayRequestPojo;
import models.response.AddressObject;
import models.response.BillPayResponsePojo;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.base.BaseService;
import utils.TestUtils;

import java.util.HashMap;

public class BillPayService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BillPayService.class);
    Response response;





    @Then("bill pay should be successful for amount {string},payee name {string}")
    public void billPayShouldBeSuccesfull(String amount,String payeeName) {
        TestUtils.validateResponseStatusCode(response,200);
        logger.info("Bill Pay Response:" + response.getBody().asPrettyString());
        BillPayResponsePojo billPayResponsePojo = response.getBody().as(BillPayResponsePojo.class);
        Assert.assertEquals(amount,billPayResponsePojo.getAmount());
        Assert.assertEquals(payeeName,billPayResponsePojo.getPayeeName());
    }



    @And("I pay a bill with amount {string},payee name {string},payee address street {string},payee address city {string},payee address state {string},payee address zipcode {string},payee phoneNumber {string}")
    public void billPay(String amount, String payeeName, String payeeAddressStreet,String payeeAddressCity,String payeeAddressState,String payeeAddressZipcode, String payeePhoneNumber) {
        String endPoint = properties.getProperty("billPayEndpoint").toString();
        String sourceAccount = String.valueOf(CreateNewAccountService.accountIds.get(0));
        String payeeAccountNumber =  String.valueOf(CreateNewAccountService.accountIds.get(1));
        BillPayRequestPojo billPayRequestPojo = new BillPayRequestPojo();
        AddressObject addressObject = new AddressObject();
        addressObject.setCity(payeeAddressCity);
        addressObject.setState(payeeAddressState);
        addressObject.setStreet(payeeAddressStreet);
        addressObject.setZipCode(payeeAddressZipcode);
        billPayRequestPojo.setAccountNumber(payeeAccountNumber);
        billPayRequestPojo.setName(payeeName);;
        billPayRequestPojo.setPhoneNumber(payeePhoneNumber);
        billPayRequestPojo.setAddress(addressObject);

        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put("amount",amount);
        queryParams.put("accountId",sourceAccount);
        response = restDriver.postRequestWithQueryParams(endPoint,billPayRequestPojo,queryParams);
        logger.info("Bill Pay Response:" + response.getBody().asPrettyString());
    }
}
