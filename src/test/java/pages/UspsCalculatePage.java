package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static support.TestContext.getWait;
import static org.assertj.core.api.Assertions.assertThat;

public class UspsCalculatePage extends Page{

    @FindBy (xpath="//option[@value='10143']")
    private WebElement selectUK;

    @FindBy (xpath="//input[@id='quantity-0']")
    private WebElement selectPostCardQty;

    @FindBy (xpath = "//input[@value='Postcard']")
    private WebElement postCardButton;

    @FindBy (xpath = "//input[@value='Calculate']")
    private WebElement calculateButton;

    @FindBy (xpath = "//div[@id='total']")
    private WebElement total;


    public UspsCalculatePage () {
        setUrl("https://postcalc.usps.com/");
    }

    public void waitPage () {
        getWait().until(ExpectedConditions.urlToBe("https://postcalc.usps.com/"));
    }

    public void setSelectCountry(String value) {

        switch (value) {
            case "United Kingdom": selectUK.click();
            default: break;

        }
    }

    public void setPostCardButton() {
        postCardButton.click();
    }

    public void setSelectPostCardQty(String value) {
        selectPostCardQty.sendKeys(value);
    }

    public void clickCalculate() {
        calculateButton.click();

    }

    public void verifyPrice(String value) {

        assertThat(total.getText()).containsIgnoringCase(value);

    }

}
