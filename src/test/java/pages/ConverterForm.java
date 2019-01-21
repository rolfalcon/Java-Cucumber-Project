package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static support.TestContext.getWait;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterForm extends Page {

    @FindBy(xpath = "//a[contains(@href, 'Temperature')]")
    private WebElement temp;

    @FindBy(xpath = "//a[contains(@href, 'Weight')]")
    private WebElement weight;

    @FindBy(xpath = "//a[contains(@href, 'Length')]")
    private WebElement length;

    @FindBy(xpath = "//*[@id='calFrom']")
    private WebElement calFrom;

    @FindBy(xpath = "//*[@id='calTo']")
    private WebElement calTo;

    @FindBy(xpath = "//*[@name='fromVal']")
    private WebElement fromVal;

    @FindBy(xpath = "//*[@name='toVal']")
    private WebElement toVal;

    public ConverterForm () {
        setUrl("https://www.unitconverters.net/");
    }

    public void chooseMeasure(String measure) {
        switch (measure) {

            case "Temperature" : temp.click(); break;
            case "Weight": weight.click(); break;
            case "Length": length.click(); break;


        }

    }
    public void chooseFromTo(String from, String to) {
        getWait().until(ExpectedConditions.visibilityOf(calFrom));
        Select selectFrom = new Select(calFrom);
        selectFrom.selectByVisibleText(from);

        Select selectTo = new Select(calTo);
        selectTo.selectByVisibleText(to);

    }

    public void chooseFromValueandVerify(String from, String verify) {
        fromVal.sendKeys(from);

        String value = toVal.getAttribute("value");
        assertThat(value).containsIgnoringCase(verify);
    }




}
