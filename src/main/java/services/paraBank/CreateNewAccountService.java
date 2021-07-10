package services.paraBank;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.response.CreateAccountResponsePojo;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.base.BaseService;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateNewAccountService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(CreateNewAccountService.class);
    Response response;
    public static ArrayList<Integer> accountIds = new ArrayList<>();

    @When("I create an account of type {string} from accountId {string}")
    public void iCreateAnAccountOfType(String accountType, String fromAccountId) {
        String endpoint = properties.getProperty("createAccountEndpoint");
        int newAccountTypeId = getAccountType(accountType);
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("customerId", String.valueOf(LoginService.loginCustomerId));
        queryParams.put("newAccountType", String.valueOf(newAccountTypeId));
        queryParams.put("fromAccountId", fromAccountId);
        HashMap<String, String> cookies = new HashMap<>();
        response = restDriver.postRequestWithQueryParamsAndCookies(endpoint, "", queryParams, cookies);
        logger.info("create account response:" + response.getBody().asPrettyString());
    }

    @Then("account of type {string} must be created")
    public void accountOfTypeMustBeCreated(String accountType) {
        TestUtils.validateResponseStatusCode(response, 200);
        CreateAccountResponsePojo createAccountResponsePojo = response.getBody().as(CreateAccountResponsePojo.class);
        Assert.assertEquals(accountType.toUpperCase().trim(), createAccountResponsePojo.getType().toUpperCase().trim());
        Assert.assertNotNull(createAccountResponsePojo.getId());
        logger.info("New account ID:" + createAccountResponsePojo.getId() + " of type:" + accountType + " created");
        accountIds.add(createAccountResponsePojo.getId());
    }

    private int getAccountType(String accountType) {
        int accountTypeid = 0;
        switch (accountType) {
            case "CHECKING":
                accountTypeid = 0;
                break;
            case "SAVINGS":
                accountTypeid = 1;
                break;
            case "LOAN":
                accountTypeid = 2;
            default:
                Assert.fail("Invalid account type specified as input");
        }
        return accountTypeid;
    }


    @When("I create an account of following types")
    public void iCreateAnAccountOfFollowingTypes(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < rows.size(); i++) {
            String accountType = rows.get(i).get("accountType");
            String fromAccountId = rows.get(i).get("fromAccountId");
            iCreateAnAccountOfType(accountType, fromAccountId);
            accountOfTypeMustBeCreated(accountType);
        }

    }
}
