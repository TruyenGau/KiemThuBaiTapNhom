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

public class TestDeposit {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/index.php"); // URL trang đăng nhập
        driver.manage().window().maximize();

        // Đăng nhập tự động
        driver.findElement(By.name("uid")).sendKeys("mngr600275"); // Điền username
        driver.findElement(By.name("password")).sendKeys("saqUmyg"); // Điền password
        driver.findElement(By.name("btnLogin")).click(); // Nhấn nút Login

        // Điều hướng đến trang Deposit sau khi đăng nhập thành công
        driver.get("https://www.demo.guru99.com/V4/manager/DepositInput.php");
    }

    @Test
    public void testAccountNoMustNotBeBlank() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("");
        accountNoInput.sendKeys(Keys.TAB);

        String expectedOutput = "Account Number must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        assertEquals(expectedOutput, actualOutput, "Expected: " + expectedOutput + ", but found: " + actualOutput);
    }

    @Test
    public void testOnlyNumbersAllowedInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("abc123");
        accountNoInput.sendKeys(Keys.TAB);

        String expectedOutput = "Characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        assertEquals(expectedOutput, actualOutput, "Expected: " + expectedOutput + ", but found: " + actualOutput);
    }

    @Test
    public void testAmountMustNotBeBlank() {
        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("");
        amountInput.sendKeys(Keys.TAB);

        String expectedOutput = "Amount must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        assertEquals(expectedOutput, actualOutput, "Expected: " + expectedOutput + ", but found: " + actualOutput);
    }

    @Test
    public void testOnlyNumbersAllowedInAmount() {
        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("abc123");
        amountInput.sendKeys(Keys.TAB);

        String expectedOutput = "Characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        assertEquals(expectedOutput, actualOutput, "Expected: " + expectedOutput + ", but found: " + actualOutput);
    }

    @Test
    public void testDescriptionMustNotBeBlank() {
        WebElement descriptionInput = driver.findElement(By.name("description"));
        descriptionInput.clear();
        descriptionInput.sendKeys("");
        descriptionInput.sendKeys(Keys.TAB);

        String expectedOutput = "Description must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutput = errorMessage.getText();

        System.out.println("Expected Output: " + expectedOutput);
        System.out.println("Actual Output: " + actualOutput);

        assertEquals(expectedOutput, actualOutput, "Expected: " + expectedOutput + ", but found: " + actualOutput);
    }

    @Test
    public void testResetButtonClearsFields() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("12345");

        WebElement amountInput = driver.findElement(By.name("amount"));
        amountInput.clear();
        amountInput.sendKeys("100");

        WebElement descriptionInput = driver.findElement(By.name("description"));
        descriptionInput.clear();
        descriptionInput.sendKeys("Test");

        WebElement resetButton = driver.findElement(By.name("reset"));
        resetButton.click();

        System.out.println("Expected all fields to be cleared.");
        System.out.println("Actual Account No: " + accountNoInput.getAttribute("value"));
        System.out.println("Actual Amount: " + amountInput.getAttribute("value"));
        System.out.println("Actual Description: " + descriptionInput.getAttribute("value"));

        assertEquals("", accountNoInput.getAttribute("value"), "Expected account number field to be cleared.");
        assertEquals("", amountInput.getAttribute("value"), "Expected amount field to be cleared.");
        assertEquals("", descriptionInput.getAttribute("value"), "Expected description field to be cleared.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
