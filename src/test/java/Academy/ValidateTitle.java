package Academy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.Base;

import java.io.IOException;

public class ValidateTitle extends Base {

    public LandingPage l; // dzieki ustawieniu tej zmiennej globalnie to dwa testy zostana wykonane w tym samym oknie przegladarki
    public WebDriver driver; // Pamietaj ze jesli uzywazsz wielu watkow to nie moze to zmienna byc statyczna
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @Test
    public void baseOageNavigation() throws IOException {

        l = new LandingPage(driver);
        //Compare te text from website and eventually throw error
        Assert.assertEquals(l.getTitle().getText(), "Error!!!");
        log.info("Succesfully validated");
    }
    @Test
    public void validateHeader() throws IOException{
        Assert.assertEquals(l.getHeader().getText(), "An Academy to learn Everything about Testing");
        log.info("Succesfully validated");
    }
    @AfterTest
    public void teardown(){
        driver.close();
    }
    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialize");
        driver.get(prop.getProperty("url"));
        log.info("Navigated to HomePage");
    }

}