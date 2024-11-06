package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestNewAccount {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/manager/addAccount.php");
        driver.manage().window().maximize();
    }

    @Test
    public void testCustomerIdIsRequired() {
        WebElement customerIdElement = driver.findElement(By.name("cusid"));
        customerIdElement.sendKeys(Keys.TAB);  // Tab qua trường khác để kích hoạt kiểm tra
        String errorMessage = driver.findElement(By.id("message14")).getText();
        assertEquals("Customer ID is required", errorMessage);
    }

    @Test
    public void testCustomerIdNoSpecialCharacters() {
        WebElement customerIdElement = driver.findElement(By.name("cusid"));
        customerIdElement.sendKeys("12345@");

        String errorMessage = driver.findElement(By.id("message14")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testCustomerIdNoCharactersAllowed() {
        WebElement customerIdElement = driver.findElement(By.name("cusid"));
        customerIdElement.sendKeys("ABC123");

        String errorMessage = driver.findElement(By.id("message14")).getText();
        assertEquals("Characters are not allowed", errorMessage);
    }

    @Test
    public void testCustomerIdNoLeadingSpace() {
        WebElement customerIdElement = driver.findElement(By.name("cusid"));
        customerIdElement.sendKeys(" 12345");

        String errorMessage = driver.findElement(By.id("message14")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testAllFieldsValid() {
        WebElement customerIdElement = driver.findElement(By.name("cusid"));
        customerIdElement.sendKeys("12345");

        WebElement accountTypeElement = driver.findElement(By.name("selaccount"));
        accountTypeElement.sendKeys("Savings");

        WebElement initialDepositElement = driver.findElement(By.name("inideposit"));
        initialDepositElement.sendKeys("10000");

        WebElement submitButton = driver.findElement(By.name("button2"));
        submitButton.click();

        String currentTitle = driver.getTitle();
        System.out.println("Actual Title: " + currentTitle); // Print the actual title
        assertTrue(currentTitle.contains("Add Account Success"), "Page did not load as expected after account creation");
    }


    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
