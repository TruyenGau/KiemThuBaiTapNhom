package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLogin {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Cấu hình đường dẫn tới ChromeDriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        // Khởi tạo ChromeDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demo.guru99.com/V4/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        // Tìm và nhập tên đăng nhập
        WebElement userNameElement = driver.findElement(By.name("uid"));
        userNameElement.sendKeys("mngr600275");

        // Tìm và nhập mật khẩu
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys("saqUmyg");

        // Nhấn nút đăng nhập
        driver.findElement(By.name("btnLogin")).click();

        // Kiểm tra xem có xuất hiện cảnh báo hay không
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            System.out.println("Alert detected: " + alertText);
            assertTrue(false, "Login failed with alert: " + alertText);
        } catch (NoAlertPresentException e) {
            // Không có cảnh báo, tiếp tục kiểm tra đăng nhập
        }

        // Kiểm tra tiêu đề trang để xác nhận đăng nhập thành công
        System.out.println("Page title is: " + driver.getTitle());
        assertTrue(driver.getTitle().contains("Guru99 Bank Manager HomePage"), "Login failed, incorrect page title.");
    }

    @AfterEach
    public void tearDown() {
        // Đóng trình duyệt
        driver.quit();
    }
}
