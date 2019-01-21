package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SampleForm extends Page{

    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;

    @FindBy (xpath = "//button[@id='formSubmit]")
    private WebElement submitButton;

    @FindBy (xpath = "//input[@name='username']")
    private WebElement email;


    public SampleForm() {
        setUrl("https://skryabin.com/webdriver/html/sample.html");
    }

    public void enterUserName (String user) {
        username.sendKeys(user);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void fillEmail (String value) {
        email.sendKeys(value);
    }


}
