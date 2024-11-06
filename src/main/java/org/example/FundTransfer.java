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

public class FundTransfer {
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
        driver.get("https://www.demo.guru99.com/V4/manager/FundTransInput.php"); // Thay bằng URL của ứng dụng
    }

    @Test
    public void testPayersAccountNoNotBlank() {
        WebElement payersAccountInput = driver.findElement(By.name("payersaccount"));

        payersAccountInput.clear(); // Để trống trường
        payersAccountInput.sendKeys(""); // Để trống trường
        payersAccountInput.sendKeys(Keys.TAB); // Nhấn Tab
        expectedOutput = "Payers Account Number must not be blank";
        errorMessage = driver.findElement(By.id("message10"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersInPayersAccountNo() {
        WebElement payersAccountInput = driver.findElement(By.name("payersaccount"));
        payersAccountInput.clear();
        payersAccountInput.sendKeys("@#$%^&*"); // Nhập ký tự đặc biệt
        payersAccountInput.sendKeys(Keys.TAB);

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message10"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInPayersAccountNo() {
        WebElement payersAccountInput = driver.findElement(By.name("payersaccount"));
        payersAccountInput.clear();
        payersAccountInput.sendKeys("abcdefg"); // Nhập ký tự chữ
        payersAccountInput.sendKeys(Keys.TAB);

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message10"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPayeesAccountNoNotBlank() {
        WebElement payeesAccountInput = driver.findElement(By.name("payeeaccount"));

        payeesAccountInput.clear(); // Để trống trường
        payeesAccountInput.sendKeys(""); // Để trống trường
        payeesAccountInput.sendKeys(Keys.TAB); // Nhấn Tab
        expectedOutput = "Payees Account Number must not be blank";
        errorMessage = driver.findElement(By.id("message11"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSpecialCharactersInPayeesAccountNo() {
        WebElement payeesAccountInput = driver.findElement(By.name("payeeaccount"));
        payeesAccountInput.clear();
        payeesAccountInput.sendKeys("@#$%^&*"); // Nhập ký tự đặc biệt
        payeesAccountInput.sendKeys(Keys.TAB);

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message11"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInPayeesAccountNo() {
        WebElement payeesAccountInput = driver.findElement(By.name("payeeaccount"));
        payeesAccountInput.clear();
        payeesAccountInput.sendKeys("abcdefg"); // Nhập ký tự chữ
        payeesAccountInput.sendKeys(Keys.TAB);

        expectedOutput = "Characters are not allowed";
        errorMessage = driver.findElement(By.id("message11"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAmountFieldNotBlank() {
        WebElement amountInput = driver.findElement(By.name("ammount"));

        // TC120: Amount Field must not be blank
        amountInput.clear(); // Để trống trường
        amountInput.sendKeys(""); // Để trống trường
        amountInput.sendKeys(Keys.TAB); // Nhấn Tab
        expectedOutput = "Amount field must not be blank";
        errorMessage = driver.findElement(By.id("message1"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCharactersNotAllowedInAmountField() {
        WebElement amountInput = driver.findElement(By.name("ammount"));
        amountInput.clear();
        amountInput.sendKeys("abc123"); // Nhập ký tự chữ
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
        amountInput.sendKeys("@#$%^&*"); // Nhập ký tự đặc biệt
        amountInput.sendKeys(Keys.TAB);

        expectedOutput = "Special characters are not allowed";
        errorMessage = driver.findElement(By.id("message1"));
        String actualOutput = errorMessage.getText();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDescriptionCannotBeBlank() {
        WebElement descriptionInput = driver.findElement(By.name("desc"));

        // TC123: Description cannot be blank
        descriptionInput.clear(); // Để trống trường
        descriptionInput.sendKeys(""); // Để trống trường
        descriptionInput.sendKeys(Keys.TAB); // Nhấn Tab
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
