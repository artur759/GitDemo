package resources;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        //FileInputStream fis = new FileInputStream("D:\\JavaNaukaWakacje\\Selenium\\FreameworkFromScratchProject\\src\\main\\java\\resources\\data.properties");
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        // mvn test -Dbrowser=chrome // Przesyłemy informacje o przegladarce za pomoca komendy w Mavenie
        String browserName =  System.getProperty("browser");
        //prop.getProperty("browser");
        //String browserName = prop.getProperty("browser");
        if(browserName.contains("chrome")){
            System.setProperty("webdriver.chrome.driver", "D:\\JavaNaukaWakacje\\Selenium\\Instalacja\\ChromeDriver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
           if(browserName.contains("headless")) {
               options.addArguments("headless"); // nie beda otwiera sie nowe przgladarki
           }
            driver = new ChromeDriver(options);
        }
        else{
            System.setProperty("webdriver.ie.driver", "D:\\JavaNaukaWakacje\\Selenium\\Instalacja\\IEdriver\\IEDriverServer.exe");
            driver  = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Trzeba zaimportowac do maven dependency apache common IO
        String destinationFile = System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
        org.apache.commons.io.FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;
    }
}
