package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExceptionTests {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser){
        //Create driver
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "D:/Install/Selenium for beginners/src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Default browser is chrome.");
                System.setProperty("webdriver.chrome.driver", "D:/Install/Selenium for beginners/src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        //Close browser
        driver.quit();
    }

    @Test
    public void notVisibleTest() {
        System.out.println("notVisibleTest.");

        // open test page
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println("Page is opened.");

        // Find locator for startButton and click on it
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // Then get finish element text
        WebElement finishElement = driver.findElement(By.id("finish"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(finishElement));
        String finishText = finishElement.getText();

        // compare actual finish element text with expected "Hello World!" using Test NG Assert class
        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);

        System.out.println("End notVisibleTest.");
    }

    @Test
    public void timeoutTest() {
        System.out.println("timeoutTest.");

        // open test page
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        System.out.println("Page is opened.");

        // Find locator for startButton and click on it
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // Then get finish element text
        WebElement finishElement = driver.findElement(By.id("finish"));

        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            wait.until(ExpectedConditions.visibilityOf(finishElement));
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("Timeout exception is caught " + e.getMessage());
            sleepForSeconds(5000);
        }
        String finishText = finishElement.getText();

        // compare actual finish element text with expected "Hello World!" using Test NG Assert class
        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);

        System.out.println("End timeoutTest.");
    }

    private void sleepForSeconds(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
