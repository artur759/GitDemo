package Academy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.IOException;


public class HomePage extends Base {
    public WebDriver driver;
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @Test(dataProvider = "getData")
    public void basePageNavigation(String userName, String password) throws IOException {
        // Nie mozemy tego zrobi w Before Test, poniewz musi sie wykonac przy kazdym podanym zestawie danych
        driver.get(prop.getProperty("url"));
        LandingPage l = new LandingPage(driver);
        LoginPage lp =   l.getLogin();
        lp.getEmail().sendKeys(userName);
        lp.getPassword().sendKeys(password);
        lp.getLogin().click();
        lp.getLogin().click();
        ForgotPassword fp = lp.forgotPassword();
        fp.getEmail().sendKeys("fsdfsfs");
        fp.getSendMeInstructions().click();
    }

    @DataProvider
    public Object[][] getData(){
        // Row stands for how many diffrent data type should run
        // Columns stands for how many values per each test

        // Jesli chcemy uzyc wiecej danych, to musimy w metodzi wywolujacej zrobic tablice stringow i wykonac je w petli
        Object[][] data = new Object[2][2];
        data[0][0] =  "nonrestricteduser@gq.com"; //username
        data[0][1] = "123456"; // pasword
        data[1][0] =  "restricteduser@gq.com"; //username
        data[1][1] = "456788"; // pasword
        log.info("Succesfully wrote username and password");
        return data;
    }
    @AfterTest
    public void teardown(){
        driver.close();
    }
    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

}
