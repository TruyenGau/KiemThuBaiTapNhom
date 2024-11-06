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

public class TestNewCustomer extends TestLogin{
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/manager/addcustomerpage.php");
        driver.manage().window().maximize();
    }

    @Test
    public void testCustomerNameNoNumbersAllowed() {
        WebElement customerNameElement = driver.findElement(By.name("name"));
        customerNameElement.sendKeys("12345");

        String errorMessage = driver.findElement(By.id("message")).getText();
        assertEquals("Numbers are not allowed", errorMessage);
    }

    @Test
    public void testCustomerNameNoSpecialCharactersAllowed() {
        WebElement customerNameElement = driver.findElement(By.name("name"));
        customerNameElement.sendKeys("John@");

        String errorMessage = driver.findElement(By.id("message")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testCustomerNameNotBlank() {
        WebElement customerNameElement = driver.findElement(By.name("name"));
        customerNameElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message")).getText();
        assertEquals("Customer name must not be blank", errorMessage);
    }

    @Test
    public void testCustomerNameNoLeadingSpace() {
        WebElement customerNameElement = driver.findElement(By.name("name"));
        customerNameElement.sendKeys(" John");

        String errorMessage = driver.findElement(By.id("message")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testAddressNotBlank() {
        WebElement addressElement = driver.findElement(By.name("addr"));
        addressElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message3")).getText();
        assertEquals("Address Field must not be blank", errorMessage);
    }

    @Test
    public void testAddressNoLeadingSpace() {
        WebElement addressElement = driver.findElement(By.name("addr"));
        addressElement.sendKeys(" Address");

        String errorMessage = driver.findElement(By.id("message3")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testAddressNoSpecialCharacters() {
        WebElement addressElement = driver.findElement(By.name("addr"));
        addressElement.sendKeys("123 Main St@");

        String errorMessage = driver.findElement(By.id("message3")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testCityNotBlank() {
        WebElement cityElement = driver.findElement(By.name("city"));
        cityElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message4")).getText();
        assertEquals("City Field must not be blank", errorMessage);
    }

    @Test
    public void testCityNoNumbersAllowed() {
        WebElement cityElement = driver.findElement(By.name("city"));
        cityElement.sendKeys("123City");

        String errorMessage = driver.findElement(By.id("message4")).getText();
        assertEquals("Numbers are not allowed", errorMessage);
    }

    @Test
    public void testCityNoSpecialCharacters() {
        WebElement cityElement = driver.findElement(By.name("city"));
        cityElement.sendKeys("City@!");

        String errorMessage = driver.findElement(By.id("message4")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testCityNoLeadingSpace() {
        WebElement cityElement = driver.findElement(By.name("city"));
        cityElement.sendKeys(" City");

        String errorMessage = driver.findElement(By.id("message4")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testStateNotBlank() {
        WebElement stateElement = driver.findElement(By.name("state"));
        stateElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message5")).getText();
        assertEquals("State must not be blank", errorMessage);
    }

    @Test
    public void testStateNoNumbersAllowed() {
        WebElement stateElement = driver.findElement(By.name("state"));
        stateElement.sendKeys("123State");

        String errorMessage = driver.findElement(By.id("message5")).getText();
        assertEquals("Numbers are not allowed", errorMessage);
    }

    @Test
    public void testStateNoSpecialCharacters() {
        WebElement stateElement = driver.findElement(By.name("state"));
        stateElement.sendKeys("State@!");

        String errorMessage = driver.findElement(By.id("message5")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testStateNoLeadingSpace() {
        WebElement stateElement = driver.findElement(By.name("state"));
        stateElement.sendKeys(" State");

        String errorMessage = driver.findElement(By.id("message5")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testPinMustBeNumeric() {
        WebElement pinElement = driver.findElement(By.name("pinno"));
        pinElement.sendKeys("PINabc");

        String errorMessage = driver.findElement(By.id("message6")).getText();
        assertEquals("Characters are not allowed", errorMessage);
    }

    @Test
    public void testPinMustNotBeBlank() {
        WebElement pinElement = driver.findElement(By.name("pinno"));
        pinElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message6")).getText();
        assertEquals("PIN Code must not be blank", errorMessage);
    }

    @Test
    public void testPinNoSpecialCharacters() {
        WebElement pinElement = driver.findElement(By.name("pinno"));
        pinElement.sendKeys("12345!");

        String errorMessage = driver.findElement(By.id("message6")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testPinLength() {
        WebElement pinElement = driver.findElement(By.name("pinno"));
        pinElement.sendKeys("123");

        String errorMessage = driver.findElement(By.id("message6")).getText();
        assertEquals("PIN Code must have 6 Digits", errorMessage);
    }

    @Test
    public void testPinNoLeadingSpace() {
        WebElement pinElement = driver.findElement(By.name("pinno"));
        pinElement.sendKeys(" 123456");

        String errorMessage = driver.findElement(By.id("message6")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testTelephoneMustNotBeBlank() {
        WebElement telephoneElement = driver.findElement(By.name("telephoneno"));
        telephoneElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message7")).getText();
        assertEquals("Mobile no must not be blank", errorMessage);
    }

    @Test
    public void testTelephoneNoSpecialCharacters() {
        WebElement telephoneElement = driver.findElement(By.name("telephoneno"));
        telephoneElement.sendKeys("123-456!");

        String errorMessage = driver.findElement(By.id("message7")).getText();
        assertEquals("Special characters are not allowed", errorMessage);
    }

    @Test
    public void testTelephoneNoCharacters() {
        WebElement telephoneElement = driver.findElement(By.name("telephoneno"));
        telephoneElement.sendKeys("Phone123");

        String errorMessage = driver.findElement(By.id("message7")).getText();
        assertEquals("Characters are not allowed", errorMessage);
    }

    @Test
    public void testTelephoneNoLeadingSpace() {
        WebElement telephoneElement = driver.findElement(By.name("telephoneno"));
        telephoneElement.sendKeys(" 0123456789");

        String errorMessage = driver.findElement(By.id("message7")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testEmailNotBlank() {
        WebElement emailElement = driver.findElement(By.name("emailid"));
        emailElement.sendKeys(Keys.TAB);

        String errorMessage = driver.findElement(By.id("message9")).getText();
        assertEquals("Email-ID must not be blank", errorMessage);
    }

    @Test
    public void testEmailValidFormat() {
        WebElement emailElement = driver.findElement(By.name("emailid"));
        emailElement.sendKeys("invalidEmail");

        String errorMessage = driver.findElement(By.id("message9")).getText();
        assertEquals("Email-ID is not valid", errorMessage);
    }

    @Test
    public void testEmailNoLeadingSpace() {
        WebElement emailElement = driver.findElement(By.name("emailid"));
        emailElement.sendKeys(" email@example.com");

        String errorMessage = driver.findElement(By.id("message9")).getText();
        assertEquals("First character can not have space", errorMessage);
    }

    @Test
    public void testAllFieldsValid() {
        driver.findElement(By.name("name")).sendKeys("cong");
        driver.findElement(By.cssSelector("input[value='m']")).click();
        driver.findElement(By.name("dob")).sendKeys("11/10/2024");
        driver.findElement(By.name("addr")).sendKeys("470 tran daiq nghia");
        driver.findElement(By.name("city")).sendKeys("danang");
        driver.findElement(By.name("state")).sendKeys("danang");
        driver.findElement(By.name("pinno")).sendKeys("000000");
        driver.findElement(By.name("telephoneno")).sendKeys("123456789");
        driver.findElement(By.name("emailid")).sendKeys("congnt.21it@vku.udn.vn");
        driver.findElement(By.name("password")).sendKeys("123456");

        driver.findElement(By.name("sub")).click();

        String currentTitle = driver.getTitle();
        assertTrue(currentTitle.contains("Customer Registered Successfully"), "Trang không hiển thị thông báo thành công như mong đợi");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
