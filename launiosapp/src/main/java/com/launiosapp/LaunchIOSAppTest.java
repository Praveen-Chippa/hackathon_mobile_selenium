package com.launiosapp;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class LaunchIOSAppTest {
    private IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        // Set desired capabilities for the iOS app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("userName", "praveen.chippa@accenture.com");
        capabilities.setCapability("password", "Welc0me");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 13");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0.2");
        capabilities.setCapability(MobileCapabilityType.UDID, "00008110-00146CA90E92801E");
        capabilities.setCapability("bundleId", "com.saucelabs.SwagLabsMobileApp");

        // Set the URL of the UFT Mobile Cloud server
        URL serverUrl = new URL("https://uftm-accenture.saas.microfocus.com/wd/hub");

        // Create an iOS driver instance
        driver = new IOSDriver(serverUrl, capabilities);
    }

    @After
    public void tearDown() {
    	 // Close the driver
        driver.quit();
    }

    @Test
    public void scenario1() {
        // Launch the app
        driver.launchApp();

        // Enter username and password
        driver.findElement(By.name("test-Username")).sendKeys("standard_user");
        driver.findElement(By.name("test-Password")).sendKeys("secret_sauce");

        // Click on the Login button
        driver.findElement(By.name("test-LOGIN")).click();

        // Verify that the products page is displayed
        assert driver.findElement(By.name("test-PRODUCTS")).isDisplayed();
        
        driver.findElement(By.name("test-Menu")).click();

        // Click on the Logout button
        driver.findElement(By.name("test-LOGOUT")).click();

        // Verify that the login page is displayed
        assert driver.findElement(By.name("test-LOGIN")).isDisplayed();
        
        
        driver.closeApp();
    }
    
    @Test
    public void scenario2() {
        // Launch the app
        driver.launchApp();

        // Enter username and password
        driver.findElement(By.name("test-Username")).sendKeys("locked_out_user");
        driver.findElement(By.name("test-Password")).sendKeys("secret_sauce");
     // Click on the Login button
        driver.findElement(By.name("test-LOGIN")).click();
        
        assert driver.findElement(By.name("test-Error message")).isDisplayed();
        
        driver.findElement(By.name("test-Username")).clear();
        driver.findElement(By.name("test-Password")).clear();

        driver.closeApp();
       
    }

    @Test
    public void scenario3() {
        // Launch the app
        driver.launchApp();

        // Enter username and password
        driver.findElement(By.name("test-Username")).sendKeys("invalid_username");
        driver.findElement(By.name("test-Password")).sendKeys("password");

        // Click on the Login button
        driver.findElement(By.name("test-LOGIN")).click();

        // Verify that the products page is displayed
        assert driver.findElement(By.name("test-Error message")).isDisplayed();
        
        driver.findElement(By.name("test-Username")).clear();
        driver.findElement(By.name("test-Password")).clear();

        driver.closeApp();
       
    }
    
    
    
}
