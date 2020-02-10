package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test(priority = 1, enabled = true)
    public void incorrectUsernameTest() {
        System.out.println("Starting incorrectUsernameTest.");

        // Create driver
        System.setProperty("webdriver.chrome.driver", "D:/Install/Selenium for beginners/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // maximize browser window
        driver.manage().window().maximize();

        // open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");

        // enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("incorrectUsername");

        // enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        // click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        // verifications:

        // successful login message
        WebElement resultMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMessage = "Your username is invalid!";
        String actualMessage = resultMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not correspond with expected message.\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

        sleepForSeconds(3000);

        // Close driver
        driver.quit();
        System.out.println("End incorrectUsernameTest.");
    }

    @Test(priority = 2, enabled = true)
    public void incorrectPasswordTest() {
        System.out.println("Starting incorrectPasswordTest.");

        // Create driver
        System.setProperty("webdriver.chrome.driver", "D:/Install/Selenium for beginners/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // maximize browser window
        driver.manage().window().maximize();

        // open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened.");

        // enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        // enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("incorrectPassword");

        // click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        // verifications:

        // successful login message
        WebElement resultMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMessage = "Your password is invalid!";
        String actualMessage = resultMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not correspond with expected message.\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

        sleepForSeconds(3000);

        // Close driver
        driver.quit();
        System.out.println("End incorrectPasswordTest.");
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
