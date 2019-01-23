package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getWait;


public class UspsForm extends Page{

    @FindBy(xpath = "//a[@id='mail-ship-width']")
    private WebElement mailShip;

    @FindBy(xpath = "//img[@id='icon-zip']")
    private WebElement zipIcon;

    @FindBy(xpath="//a[contains(@class,'zip-code-address')]")
    private WebElement zipCodeAddress;

    @FindBy(xpath = "//input[@id='tAddress']")
    private WebElement street ;

    @FindBy(xpath = "//input[@id='tCity']")
    private WebElement city;

    @FindBy(xpath="//select[@id='tState']")
    private WebElement state;

    @FindBy(xpath="//div[@id='zipByAddressDiv']")
    private WebElement zip;

    @FindBy(xpath = "//a[@id='zip-by-address']")
    private WebElement zipButton;

    @FindBy(xpath="//div[@id='zipByAddressDiv']//li")
    private List<WebElement> zipList;

    @FindBy(xpath="//a[@class='menuitem'][contains(text(),'Postal Store')]")
    private WebElement postalStore;

    @FindBy(xpath="//li[@class='tool-stamps']/a[@role='menuitem'][contains(@href, 'stamps')]")
    private WebElement stamps;

    public UspsForm() {
        setUrl("https://www.usps.com/");
    }

    public void goToZipPage() {
        mailShip.click();
        zipIcon.click();
        zipCodeAddress.click();
    }

    public void fillAddress(String street, String city, String state) {
        this.street.sendKeys(street);
        this.city.sendKeys(city);
        Select stateSelect = new Select(this.state);
        stateSelect.selectByValue(state);
        zipButton.click();


    }

    public void verifyZip (String value) {
        getWait().until(ExpectedConditions.visibilityOf(zip));

        for (WebElement element: zipList) {
            assertThat(element.getText()).containsIgnoringCase(value);
        }


    }

    public void selectCheckBox (String value) {



    }

}
