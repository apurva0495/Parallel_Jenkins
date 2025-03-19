package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        if (isRunningInJenkins()) {
            System.out.println("Running on Jenkins... Using System.setProperty()");
            String driverPath = getDriverPath(); // Get driver based on OS
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        } else {
            System.out.println("Running locally... Using Selenium Manager.");
            driver = new ChromeDriver(); // Use Selenium Manager for local runs
        }

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Check if running in Jenkins environment
    private boolean isRunningInJenkins() {
        String jenkinsHome = System.getenv("JENKINS_HOME"); // Jenkins Home
        String buildId = System.getenv("BUILD_ID");        // Build ID
        return (jenkinsHome != null || buildId != null);
    }

    // Determine driver path based on OS
    private String getDriverPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath;

        if (os.contains("win")) {
            driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe";
        } else if (os.contains("mac")) {
            driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver";
        } else {
            throw new RuntimeException("Unsupported operating system: " + os);
        }

        System.out.println("Detected OS: " + os);
        System.out.println("Using driver path: " + driverPath);
        return driverPath;
    }
}
