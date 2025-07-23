package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import pages.RegisterPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterTest extends BaseTest {

    @BeforeEach
    void setUpEach() {
        driver.get("http://localhost:8080/RegisterJobSeeker.jsp");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/register-data.csv", numLinesToSkip = 1)
    void testRegister(String username, String email, String password, String confirmPassword, String expected) {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(username, email, password, confirmPassword);

        String actual;
        try {
            boolean isSuccess = driver.findElement(By.className("alert-success")).isDisplayed();
            actual = isSuccess ? "success" : "error";
        } catch (Exception e) {
            actual = "error";
        }

        System.out.println("Expected: " + expected + ", Actual: " + actual);
        Assertions.assertEquals(expected.trim(), actual.trim());
    }
}
