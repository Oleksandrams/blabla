package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void positiveLoginTest() {
        System.out.println("Starting loginTest.");

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
        password.sendKeys("SuperSecretPassword!");

        // click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        // verifications:
        // new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page is not the same as expected.");

        // logout button is visible
        WebElement logoutButton = driver.findElement(By.xpath("//div[@id='content']//a[@href='/logout']/i[@class='icon-2x icon-signout']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed.");

        // successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not correspond with expected message.\nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

        sleepForSeconds(3000);

        // Close driver
        driver.quit();
        System.out.println("End loginTest.");
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
