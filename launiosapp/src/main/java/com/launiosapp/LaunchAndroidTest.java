package com.launiosapp;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class LaunchAndroidTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        // Set desired capabilities for the iOS app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("userName", "praveen.chippa@accenture.com");
        capabilities.setCapability("password", "Welc0me");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "SM-G991U1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
        capabilities.setCapability(MobileCapabilityType.UDID, "RFCT317SKDE");
        capabilities.setCapability("appPackage", "com.swaglabsmobileapp"); 
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");

        // Set the URL of the UFT Mobile Cloud server
        URL serverUrl = new URL("https://uftm-accenture.saas.microfocus.com/wd/hub");

        // Create an iOS driver instance
        driver = new AndroidDriver(serverUrl, capabilities);
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
        driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
        driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");

        // Click on the Login button
        driver.findElementByAccessibilityId("test-LOGIN").click();

        // Verify that the products page is displayed
        assert driver.findElementByAccessibilityId("test-PRODUCTS").isDisplayed();
        
        driver.findElementByAccessibilityId("test-Menu").click();

        // Click on the Logout button
        driver.findElementByAccessibilityId("test-LOGOUT").click();

        // Verify that the login page is displayed
        assert driver.findElementByAccessibilityId("test-LOGIN").isDisplayed();
        
        
        driver.closeApp();
    }
    
    @Test
    public void scenario2() {
        // Launch the app
        driver.launchApp();

        // Enter username and password
        driver.findElementByAccessibilityId("test-Username").sendKeys("locked_out_user");
        driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
     // Click on the Login button
        driver.findElementByAccessibilityId("test-LOGIN").click();
        
        assert driver.findElementByAccessibilityId("test-Error message").isDisplayed();
        
        driver.findElementByAccessibilityId("test-Username").clear();
        driver.findElementByAccessibilityId("test-Password").clear();

        driver.closeApp();
       
    }

    @Test
    public void scenario3() {
        // Launch the app
        driver.launchApp();

        // Enter username and password
        driver.findElementByAccessibilityId("test-Username").sendKeys("invalid_username");
        driver.findElementByAccessibilityId("test-Password").sendKeys("password");

        // Click on the Login button
        driver.findElementByAccessibilityId("test-LOGIN").click();

        // Verify that the products page is displayed
        assert driver.findElementByAccessibilityId("test-Error message").isDisplayed();
        
        driver.findElementByAccessibilityId("test-Username").clear();
        driver.findElementByAccessibilityId("test-Password").clear();

        driver.closeApp();
       
    }
    
    
    
}
