package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By mobileField = By.id("userNumber");
    private By dobInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By uploadPicture = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateDropdown = By.id("react-select-3-input");
    private By cityDropdown = By.id("react-select-4-input");
    private By submitButton = By.id("submit");
    private By successModal = By.className("modal-content");

    // Dynamic locators
    private By genderRadio(String gender) {
        return By.xpath("//label[text()='" + gender + "']");
    }
    private By hobbiesCheckbox(String hobby) {
        return By.xpath("//label[text()='" + hobby + "']");
    }

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String firstName) {
        if (firstName != null && !firstName.isEmpty()) {
            type(firstNameField, firstName);
        }
    }

    public void enterLastName(String lastName) {
        if (lastName != null && !lastName.isEmpty()) {
            type(lastNameField, lastName);
        }
    }

    public void enterEmail(String email) {
        if (email != null && !email.isEmpty()) {
            type(emailField, email);
        }
    }

    public void selectGender(String gender) {
        if (gender != null && !gender.isEmpty()) {
            click(genderRadio(gender));
        }
    }

    public void enterMobile(String mobile) {
        if (mobile != null && !mobile.isEmpty()) {
            type(mobileField, mobile);
        }
    }

    public void enterDOB(String dob) {
        if (dob != null && !dob.isEmpty()) {
            click(dobInput);
            WebElement dobElem = waitForVisibility(dobInput);
            dobElem.clear();
            dobElem.sendKeys(dob);
            dobElem.sendKeys("\n");
        }
    }

    public void enterSubjects(String subject) {
        if (subject != null && !subject.isEmpty()) {
            type(subjectsInput, subject);
            WebElement elem = waitForVisibility(subjectsInput);
            elem.sendKeys("\n");
        }
    }

    public void selectHobby(String hobby) {
        if (hobby != null && !hobby.isEmpty()) {
            hideAds(); // Ẩn quảng cáo trước khi thao tác
            WebElement elem = waitForVisibility(hobbiesCheckbox(hobby));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
            elem.click();
        }
    }

    public void uploadPicture(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            WebElement uploadElem = waitForVisibility(uploadPicture);
            uploadElem.sendKeys(filePath);
        }
    }

    public void enterAddress(String address) {
        if (address != null && !address.isEmpty()) {
            type(addressField, address);
        }
    }

    public void selectState(String state) {
        if (state != null && !state.isEmpty()) {
            try {
                WebElement elem = waitForVisibility(stateDropdown);
                if (elem.isDisplayed() && elem.isEnabled()) {
                    elem.sendKeys(state);
                    elem.sendKeys("\n");
                }
            } catch (Exception e) {
                // Bỏ qua nếu không tìm thấy hoặc không thể thao tác
            }
        }
    }

    public void selectCity(String city) {
        if (city != null && !city.isEmpty()) {
            try {
                WebElement elem = waitForVisibility(cityDropdown);
                if (elem.isDisplayed() && elem.isEnabled()) {
                    elem.sendKeys(city);
                    elem.sendKeys("\n");
                }
            } catch (Exception e) {
                // Bỏ qua nếu không tìm thấy hoặc không thể thao tác
            }
        }
    }

    public void submitForm() {
        click(submitButton);
    }

    public boolean isSuccessModalVisible() {
        return isElementVisible(successModal);
    }

    public String getSuccessModalText() {
        return getText(successModal);
    }

    public void hideAds() {
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "document.querySelectorAll('iframe, .adsbygoogle, [id^=\"google_ads_\"]').forEach(e => e.remove());"
        );
    }
} 