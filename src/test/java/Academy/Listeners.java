package Academy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal <ExtentTest> extentTest = new ThreadLocal();


    @Override
    public void onTestStart(ITestResult iTestResult) {

         test = extent.createTest(iTestResult.getMethod().getMethodName());
         extentTest.set(test);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS,"Test passed");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());

        String testMethodName = iTestResult.getMethod().getMethodName();
        WebDriver driver = null;
        try {
            driver = (WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            //Zeby screeny byly tez w html'u
            extentTest.get().addScreenCaptureFromPath( getScreenShotPath(testMethodName,driver),iTestResult.getMethod().getMethodName());
            //getScreenShotPath(testMethodName,driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
