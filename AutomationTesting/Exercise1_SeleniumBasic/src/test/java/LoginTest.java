import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests for the-internet.herokuapp.com")
public class LoginTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.javascript", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    @DisplayName("Should login successfully with valid credentials")
    void testLoginSuccess() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.success"))
        );
        assertTrue(successMsg.getText().contains("You logged into a secure area!"));
    }

    @Test
    @Order(2)
    @DisplayName("Should display error when logging in with invalid credentials")
    void testLoginFail() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("invalid");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flash.error"))
        );
        assertTrue(errorMsg.getText().contains("Your username is invalid!"));
    }

    @ParameterizedTest(name = "Test Login - Username: {0}, Password: {1}")
    @CsvSource({
            "tomsmith, SuperSecretPassword!, success",
            "wronguser, SuperSecretPassword!, error",
            "tomsmith, wrongpassword, error",
            "'', '', error"
    })
    @Order(3)
    @DisplayName("Multiple login attempts using @CsvSource")
    void testLoginWithMultipleParameters(String username, String password, String expectedResult) {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        By messageLocator = expectedResult.equals("success")
                ? By.cssSelector(".flash.success")
                : By.cssSelector(".flash.error");

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid"));
        }
    }

    @ParameterizedTest(name = "Test login with: {0} / {1}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(4)
    @DisplayName("Login with data from external CSV file")
    void testLoginWithCSV(String username, String password, String expectedResult) {
        driver.get("https://the-internet.herokuapp.com/login");

        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password.trim();

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        By messageLocator = expectedResult.equals("success")
                ? By.cssSelector(".flash.success")
                : By.cssSelector(".flash.error");

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageLocator));

        if (expectedResult.equals("success")) {
            assertTrue(message.getText().contains("You logged into a secure area!"));
        } else {
            assertTrue(message.getText().toLowerCase().contains("invalid"));
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
