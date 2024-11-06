package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Widthdraw {
    private WebDriver driver;
    static WebElement errorMessage;
    static String expectedOutput;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.demo.guru99.com/V4/manager/WithdrawalInput.php"); // URL của ứng dụng
    }

    @Test
    public void testAccountNoMustNotBeBlank() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("");
        accountNoInput.sendKeys(Keys.TAB);

        expectedOutput = "Account Number must not be blank";
        errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersNotAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("@#$%^&*");
        accountNoInput.sendKeys(Keys.TAB);

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("abcdefg");
        accountNoInput.sendKeys(Keys.TAB);

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAmountFieldMustNotBeBlank() {
        WebElement amountInput = driver.findElement(By.name("ammount"));
        amountInput.clear();
        amountInput.sendKeys("");
        amountInput.sendKeys(Keys.TAB);

        expectedOutput = "Amount field must not be blank";
        errorMessage = driver.findElement(By.id("message1"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInAmountField() {
        WebElement amountInput = driver.findElement(By.name("ammount"));
        amountInput.clear();
        amountInput.sendKeys("abc123");
        amountInput.sendKeys(Keys.TAB);

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message1"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersNotAllowedInAmountField() {
        WebElement amountInput = driver.findElement(By.name("ammount"));
        amountInput.clear();
        amountInput.sendKeys("@#$%^&*");
        amountInput.sendKeys(Keys.TAB);

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message1"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDescriptionCannotBeBlank() {
        WebElement descriptionInput = driver.findElement(By.name("desc"));
        descriptionInput.clear();
        descriptionInput.sendKeys("");
        descriptionInput.sendKeys(Keys.TAB);

        expectedOutput = "Description can not be blank";
        errorMessage = driver.findElement(By.id("message17"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
