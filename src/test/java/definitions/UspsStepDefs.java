package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static support.TestContext.*;
import static org.assertj.core.api.Assertions.*;


public class UspsStepDefs {
    @When("^I go to Lookup ZIP page by address$")
    public void iGoToLookupZIPPageByAddress() throws Exception {
        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getDriver().findElement(By.xpath("//img[@id='icon-zip']")).click();
        getDriver().findElement(By.xpath("//a[contains(@class,'zip-code-address')]")).click();
    }

    @And("^I fill out \"([^\"]*)\" street, \"([^\"]*)\" city, \"([^\"]*)\" state$")
    public void iFillOutStreetCityState(String street, String city, String state) throws Throwable {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);

        getDriver().findElement(By.xpath("//select[@id='tState']/option[@value='" + state + "']")).click();
        // Another approach below
        // getDriver().findElement(By.xpath(String.format("//select[@id='tState']/option[@value='%s']", state))).click();

        getDriver().findElement(By.xpath("//a[@id='zip-by-address']"));
        getDriver().wait(100);

    }

    @Then("^I validate \"([^\"]*)\" zip code exists in the result$")
    public void iValidateZipCodeExistsInTheResult(String zip) throws Throwable {

        WebDriverWait wait = new WebDriverWait(getDriver(), 5);

        // this wait checking that text is not empty, then we proceed. Using lambda function
        WebElement zipSearchResultElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        System.out.println(String.format("Search result: |%s|", zipSearchResultElement.getText()));
        wait.until((ExpectedCondition<Boolean>) driver -> zipSearchResultElement.getText().length() > 0);
        System.out.println(String.format("Search result after wait: |%s|", zipSearchResultElement.getText()));

        // this wait checking that LI element was generated inside of DIV, then we proceed (same purpose as above, redundant)
        By firstResultItem = By.xpath("(//div[@id='zipByAddressDiv']//li)[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstResultItem));

        String zipSearchResult = zipSearchResultElement.getText();
        assertThat(zipSearchResult).contains(zip);

        // Notice findsElements - i.e. plural
        List<WebElement> items = getDriver().findElements(By.xpath("//div[@id='zipByAddressDiv']//li"));

        for (WebElement item : items) {
            assertThat(item.getText()).contains(zip);
        }

    }

    @And("^I fill \"([^\"]*)\" on Search Postal textfield$")
    public void iFillOnSearchPostalTextfield(String field) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement store = getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'Postal Store')]"));
        Actions actions = new Actions(getDriver());

        actions.moveToElement(store).perform();

        By xpathID = By.xpath("//input[@id='global-header--search-track-store']");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpathID));
        getDriver().findElement(xpathID).sendKeys(field + Keys.ENTER);



    }

    @Then("^I validate it did not match any products$")
    public void iValidateItDidNotMatchAnyProducts() throws InterruptedException {
        boolean notFound = getDriver().findElement(By.xpath("//div[@class='no-results-found']")).isDisplayed();
        assertThat(notFound).isTrue();
        Thread.sleep(100);
    }

    @When("^I go to Stamps$")
    public void iGoToStamps() {
        WebElement store = getDriver().findElement(By.xpath("//a[@class='menuitem'][contains(text(),'Postal Store')]"));
        Actions actions = new Actions(getDriver());

        actions.moveToElement(store).perform();

        By xpathID = By.xpath("//li[@class='tool-stamps']/a[@role='menuitem'][contains(@href, 'stamps')]");
        getDriver().findElement(xpathID).click();


    }

    @And("^I select Priority Mail$")
    public void iSelectPriorityMail() {
        By xpath = By.xpath("//input[contains(@id, 'Priority Mail')]/../label");
        WebElement priorityMail = getDriver().findElement(xpath);

        clickWithJS(priorityMail);

    }

    @Then("^I verify \"([^\"]*)\" stamp$")
    public void iVerifyStamp(String stamp) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        By xpath = By.xpath("//div[@class='result-products-holder']");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpath));

        String verifyText = getDriver().findElement(xpath).getText();

        assertThat(verifyText).containsIgnoringCase(stamp);
    }


    @And("^I select \"([^\"]*)\"$")
    public void iSelect(String select) throws Throwable {
        By xpath = By.xpath(String.format("//input[contains(@id, '%s')]/../label", select));
        WebElement priorityMail = getDriver().findElement(xpath);

        clickWithJS(priorityMail);
    }

    @And("^I unselect \"([^\"]*)\" checkbox$")
    public void iUnselectCheckbox(String select) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        By xpath = By.xpath(String.format("//input[contains(@id, '%s')]/../label", select));
        WebElement unselectBox = getDriver().findElement(xpath);
        clickWithJS(unselectBox);
//        if (unselectBox.isSelected()) {
//            clickWithJS(unselectBox);
//        }
    }

    @And("^I select size \"([^\"]*)\"$")
    public void iSelectSize(String select) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        By xpath = By.xpath("//*[contains(text(), 'Size')]/..//*[contains(text(), 'Show More')]");

        getWait().until(ExpectedConditions.visibilityOfElementLocated(xpath));
        getDriver().findElement(xpath).click();


        By xpathSelect = By.xpath(String.format("//input[contains(@id, '%s')]/../label", select));
        WebElement priorityMail = getDriver().findElement(xpathSelect);

        clickWithJS(priorityMail);



        throw new PendingException();
    }

    @Then("^I verify \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iVerifyAnd(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^I verify \"([^\"]*)\" text$")
    public void iVerifyText(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
