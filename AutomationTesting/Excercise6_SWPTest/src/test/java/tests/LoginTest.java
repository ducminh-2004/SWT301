package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginTest extends BaseTest {

    @ParameterizedTest(name = "Test login with email={0}, password={1} => expect {2}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    void testLogin(String email, String password, String expected) {
        driver.get("http://localhost:8080/Login.jsp");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        boolean isSuccess = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            isSuccess = wait.until(ExpectedConditions.urlContains("JobSeekerDashboard"));
        } catch (Exception e) {
            isSuccess = false;
        }

        String actual = isSuccess ? "success" : "error";
        Assertions.assertEquals(expected, actual);
    }
}
