import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import services.base.BaseService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RestDriver;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/", tags = "", glue = "services")
public class SuiteTestRunner {

    private static Properties properties;
    private static Properties envProperties;
    private static RestDriver restDriver;
    private static BaseService baseService;
    private static Logger logger = LoggerFactory.getLogger(SuiteTestRunner.class);


    @BeforeClass
    public static void init() throws IOException {

        logger.info("*****Starting init()*******");
        InputStream fileInputStream = SuiteTestRunner.class.getResourceAsStream("/data/configuration/testConfig.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        restDriver = new RestDriver(properties);
        baseService = new BaseService();
        baseService.setRestDriver(restDriver);
        baseService.setProperties(properties);

    }


    @AfterClass
    public static void tearDown() {
        logger.info("*****Entering final teardown() *******");
        baseService = null;
        properties = null;
        restDriver = null;
        logger = null;
    }

}