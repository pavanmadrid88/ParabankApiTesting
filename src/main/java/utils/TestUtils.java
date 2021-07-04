package utils;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class TestUtils {

    private static Logger logger = LoggerFactory.getLogger(TestUtils.class);

    /**
     * This method validates a given models.response with a given models.response statusCode
     *
     * @param response   - models.response to be checked
     * @param statusCode - expected status code
     */
    public static void validateResponseStatusCode(Response response, int statusCode) {
        Assert.assertEquals("Response status code validation.Expected Status code:" + statusCode + ";Actual status code:" + response.statusCode(), statusCode, response.statusCode());
    }


    /**
     * This method validates a given models.response with a given models.response time
     *
     * @param response             - models.response to be checked
     * @param expectedResponseTime - expected models.response time
     */
    public static void validateResponseTime(Response response, long expectedResponseTime) {
        long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        logger.info("Response time:" + actualResponseTime);
        Assert.assertTrue("Actual Response time:" + actualResponseTime + ";Expected models.response time:" + expectedResponseTime, actualResponseTime < expectedResponseTime);
    }


    /**
     * This method validates a given models.response with a given models.response statusLine
     *
     * @param response   - models.response to be checked
     * @param statusLine - expected status Line
     */
    public static void validateResponseStatusLine(Response response, String statusLine) {
        Assert.assertEquals(statusLine, response.statusLine());
    }


    /**
     * This method validates a given models.response with a given models.response statusLine
     *
     * @param response         - models.response to be checked
     * @param contentToLookFor - content to look for in the models.response body
     */
    public static void validateResponseBodyContent(Response response, String contentToLookFor) {
        Assert.assertTrue(response.body().asString().toUpperCase().contains(contentToLookFor.toUpperCase()));
    }


}