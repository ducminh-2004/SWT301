package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Registration Tests using Page Object Model")
public class  RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;
    static WebDriverWait wait;

    @BeforeAll
    static void initPage() {
        registrationPage = new RegistrationPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Should register successfully with valid data")
    void testRegisterSuccess() {
        registrationPage.navigate();
        registrationPage.hideAds();
        registrationPage.enterFirstName("Minh");
        registrationPage.enterLastName("Pham");
        registrationPage.enterEmail("minh.pham@example.com");
        registrationPage.selectGender("Male");
        registrationPage.enterMobile("0912345678");
        registrationPage.enterDOB("22 Jul 2025");
        registrationPage.enterSubjects("Maths");
        registrationPage.selectHobby("Sports");
        // Bỏ qua uploadPicture nếu không cần thiết
        registrationPage.enterAddress("Hanoi, Vietnam");
        registrationPage.selectState("NCR");
        registrationPage.selectCity("Delhi");
        registrationPage.submitForm();
        assertTrue(registrationPage.isSuccessModalVisible(), "Success modal should be visible after registration");
    }

    @ParameterizedTest(name = "Test case: {0} {1}, {2}, {3}, {4} -> Expect {11}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @DisplayName("Register with various data from CSV")
    void testRegisterWithCSV(String firstName, String lastName, String email, String gender, String mobile, String dob, String subject, String hobby, String address, String state, String city, String expected) {
        registrationPage.navigate();
        registrationPage.hideAds();
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.selectGender(gender);
        registrationPage.enterMobile(mobile);
        registrationPage.enterDOB(dob);
        registrationPage.enterSubjects(subject);
        registrationPage.selectHobby(hobby);
        registrationPage.enterAddress(address);
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.submitForm();
        if ("success".equalsIgnoreCase(expected)) {
            assertTrue(registrationPage.isSuccessModalVisible(), "Success modal should be visible after registration");
            assertTrue(registrationPage.getSuccessModalText().contains("Thanks for submitting the form"), "Modal should contain thank you message");
        } else {
            assertFalse(registrationPage.isSuccessModalVisible(), "Success modal should NOT be visible for invalid data");
        }
    }
} 