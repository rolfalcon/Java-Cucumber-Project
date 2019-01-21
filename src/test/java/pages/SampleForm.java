package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SampleForm extends Page{

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy (xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;

    @FindBy (xpath = "//input[@name='username']")
    private WebElement email;

    @FindBy (xpath = "//input[@id='name']")
    private WebElement name;

    @FindBy (xpath = "//input[@id='firstName']")
    private WebElement firstName;

    @FindBy (xpath = "//input[@id='middleName']")
    private WebElement middleName;

    @FindBy (xpath = "//input[@id='lastName']")
    private WebElement lastName;

    @FindBy (xpath = "//button//span[contains(text(),'Save')]")
    private WebElement saveNameButton;

    @FindBy (xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy (xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPassword;

    @FindBy (xpath = "//input[@name='agreedToPrivacyPolicy']")
    private WebElement privacyPolicy;



    public SampleForm() {
        setUrl("https://skryabin.com/webdriver/html/sample.html");
    }

    public void enterUserName (String user) {
        username.sendKeys(user);
    }

    public void enterName (String first, String last, String middle ){

        name.click();
        firstName.sendKeys(first);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);
        saveNameButton.click();

    }

    public void enterPassword (String password, String passwordConfirm) {
        this.password.sendKeys(password);
        confirmPassword.sendKeys(passwordConfirm);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void fillEmail (String value) {
        email.sendKeys(value);
    }

    public void clickPrivacyCheckbox () {
        privacyPolicy.click();
    }


}
