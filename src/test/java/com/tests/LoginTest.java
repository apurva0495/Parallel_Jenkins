package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement productsTitle = driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login Failed!");
    }
    
    @Test
    public void logintest() {
    	System.out.println("this is paralle for Logintest");
    }
}
