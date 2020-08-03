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

public class ValidateNavbar extends Base {

    public WebDriver driver;
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @Test
    public void baseOageNavigation() throws IOException {

      //  driver = initializeDriver();
        // Juz w klasie Base raz to zdefiniowalismy, wiec tutaj wystarczy samo wywolanie
       // driver.get(prop.getProperty("url"));
        LandingPage l = new LandingPage(driver);

        //Jesli false to rzuci blad
        Assert.assertTrue(l.getNavbar().isDisplayed());
        Assert.assertFalse(false);
        log.info("Navbar is printed. OK");
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