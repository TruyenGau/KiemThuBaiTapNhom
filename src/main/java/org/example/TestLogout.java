package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogout {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/manager/Managerhomepage.php"); // Đảm bảo đã đăng nhập trước khi test
        driver.manage().window().maximize();
    }

    @Test
    public void testLogoutFunctionality() {
        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        logoutButton.click();

        driver.switchTo().alert().accept();

        String expectedUrl = "https://www.demo.guru99.com/V4/index.php"; // URL trang đăng nhập
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);

        // Kiểm tra thông báo đăng xuất thành công (nếu có)
        WebElement confirmationMessage = driver.findElement(By.cssSelector("h2")); // Cập nhật selector nếu khác
        String expectedMessage = "You Have Succesfully Logged Out!!";
        String actualMessage = confirmationMessage.getText();
        assertEquals(expectedMessage, actualMessage);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
