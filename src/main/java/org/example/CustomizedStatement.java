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

public class CustomizedStatement {
    private WebDriver driver;
    static WebElement errorMessage;
    static String expectedOutput;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Chạy ở chế độ không hiển thị, có thể bỏ dòng này nếu không cần
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.demo.guru99.com/V4/manager/CustomisedStatementInput.php");
    }

    @Test
    public void testAccountNoInput() {
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
    public void testAccountCharacters() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("ABC12345");
        accountNoInput.sendKeys(Keys.TAB);
        errorMessage = driver.findElement(By.id("message2"));
        expectedOutput = "Characters are not allowed";
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersInAccountNo() {
        WebElement accountNoInput = driver.findElement(By.name("accountno"));
        accountNoInput.clear();
        accountNoInput.sendKeys("@12345!");
        accountNoInput.sendKeys(Keys.TAB);

        String expectedOutputT35 = "Special characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message2"));
        String actualOutputT35 = errorMessage.getText();
        assertEquals(expectedOutputT35, actualOutputT35);
    }

    @Test
    public void testSpecialCharactersInTransactionValue() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit"));
        transactionInput.clear();
        transactionInput.sendKeys("@1234!");
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT39 = "Special characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message12"));
        String actualOutputT39 = errorMessage.getText();
        assertEquals(expectedOutputT39, actualOutputT39);
    }

    @Test
    public void testTransactionValueNotBlank() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit"));
        transactionInput.clear();
        transactionInput.sendKeys("");
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT40 = "Number of Transaction must not be blank";
        WebElement errorMessage = driver.findElement(By.id("message12"));
        String actualOutputT40 = errorMessage.getText();
        assertEquals(expectedOutputT40, actualOutputT40);
    }

    @Test
    public void testTransactionValueCharactersNotAllowed() {
        WebElement transactionInput = driver.findElement(By.name("amountlowerlimit"));
        transactionInput.clear();
        transactionInput.sendKeys("ABC123");
        transactionInput.sendKeys(Keys.TAB);

        String expectedOutputT41 = "Characters are not allowed";
        WebElement errorMessage = driver.findElement(By.id("message12"));
        String actualOutputT41 = errorMessage.getText();
        assertEquals(expectedOutputT41, actualOutputT41);
    }

    @Test
    public void testFromDateNotBlank() {
        WebElement fromDateInput = driver.findElement(By.name("fdate"));
        fromDateInput.clear();
        fromDateInput.sendKeys(Keys.TAB);

        String expectedFromDateError = "From Date Field must not be blank";
        WebElement fromDateError = driver.findElement(By.id("message26"));
        String actualFromDateError = fromDateError.getText();
        assertEquals(expectedFromDateError, actualFromDateError);
    }

    @Test
    public void testToDateNotBlank() {
        WebElement toDateInput = driver.findElement(By.name("tdate"));
        toDateInput.clear();
        toDateInput.sendKeys(Keys.TAB);

        String expectedToDateError = "To Date Field must not be blank";
        WebElement toDateError = driver.findElement(By.id("message27"));
        String actualToDateError = toDateError.getText();
        assertEquals(expectedToDateError, actualToDateError);
    }

    @Test
    public void testFromDateBeforeToDate() {
        WebElement fromDateInput = driver.findElement(By.name("fdate"));
        fromDateInput.clear();
        fromDateInput.sendKeys("10/10/2024");

        WebElement toDateInput = driver.findElement(By.name("tdate"));
        toDateInput.clear();
        toDateInput.sendKeys("01/10/2024");
        toDateInput.sendKeys(Keys.TAB);

        String expectedDateError = "From Date must be earlier than To Date";
        WebElement toDateError = driver.findElement(By.id("message27"));
        String actualDateError = toDateError.getText();
        assertEquals(expectedDateError, actualDateError);
    }

    @Test
    public void testValidFromAndToDate() {
        WebElement fromDateInput = driver.findElement(By.name("fdate"));
        fromDateInput.clear();
        fromDateInput.sendKeys("01/10/2024");

        WebElement toDateInput = driver.findElement(By.name("tdate"));
        toDateInput.clear();
        toDateInput.sendKeys("10/10/2024");
        toDateInput.sendKeys(Keys.TAB);

        String expectedValidOutput = "";
        String actualValidOutput = driver.findElement(By.id("successMessage")).getText();
        assertEquals(expectedValidOutput, actualValidOutput);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
