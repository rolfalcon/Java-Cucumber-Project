package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.Page;
import support.TestContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class UpsStepDefs {
    private String currentCharge;


    @And("^I select \"([^\"]*)\" and \"([^\"]*)\" on global page$")
    public void iSelectAndOnGlobalPage(String region, String country) throws Throwable {
        WebElement topHeader = getDriver().findElement(By.xpath("//*[@class='page-title_cell']"));
        new Actions(getDriver()).moveToElement(topHeader).perform();
        By countrySelector = By.xpath("//a[text()='" + country + "']");

        if (getDriver().findElements(countrySelector).size() == 0) {
            getDriver().findElement(By.xpath("//a[text()='" + region + "']")).click();
        }

        getDriver().findElement(countrySelector).click();
    }

    @And("^I open Shipping menu$")
    public void iOpenShippingMenu() {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks1']")).click();
    }

    @And("^I go to Create a Shipment$")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//*[@id='ups-menuPanel1']//a[contains(@href,'ship?loc')]")).click();
    }

    @When("^I fill out origin shipment fields$")
    public void iFillOutOriginShipmentFields() throws Exception {
        HashMap<String, String> sender = getSender();

        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys(sender.get("name"));
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys(sender.get("address"));
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys(sender.get("zip"));

        getWait().until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@id='origincity']"), sender.get("city").toUpperCase()));
        getWait().until(ExpectedConditions.elementToBeSelected(By.xpath("//select[@id='originstate']/option[text()='California']")));

        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys(sender.get("email"));
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys(sender.get("phone"));
    }

    @And("^I submit the shipment form$")
    public void iSubmitTheShipmentForm() {
        String oldUrl = getDriver().getCurrentUrl();
//        WebElement nextButton = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
        WebElement nextButton = getDriver().findElement(By.xpath("//*[contains(@id, 'nbsBackForwardNavigationContinueButton') or contains(@id, 'nbsBackForwardNavigationReviewPrimaryButton')]"));
        Page.clickWithJS(nextButton);
        getWait().until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
    }

    @Then("^I verify origin shipment fields submitted$")
    public void iVerifyOriginShipmentFieldsSubmitted() throws Exception {
        HashMap<String, String> sender = getSender();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='originnbsAgentSummaryName']")));
        String textToVerify = getDriver().findElement(By.xpath("//*[@prefix='origin']")).getText();

        assertThat(textToVerify).containsIgnoringCase(sender.get("name"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("address"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("zip"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("email"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("phone"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("city"));
        assertThat(textToVerify).containsIgnoringCase(sender.get("state"));
    }

    @And("^I cancel the shipment form$")
    public void iCancelTheShipmentForm() {

        WebElement cancelButton = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));

        Actions actions = new Actions(getDriver());

        actions.moveToElement(cancelButton).perform();
        Page.clickWithJS(cancelButton);

        By yesXpathButton = By.xpath("//button[@id='nbsCancelShipmentWarningYes']");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(yesXpathButton));
        getDriver().findElement(yesXpathButton).click();


    }

    @Then("^I verify shipment form is reset$")
    public void iVerifyShipmentFormIsReset() {
        String value = getDriver().findElement(By.xpath("//input[@id='originname']")).getAttribute("value");
        assertThat(value).isEmpty();
    }

    @When("^I fill out destination shipment fields$")
    public void iFillOutDestinationShipmentFields() throws Exception {
        HashMap<String, String> receiver = TestContext.getReceiver();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='destinationname']")));
        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys(receiver.get("name"));
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys(receiver.get("address"));
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys(receiver.get("zip"));
        getWait().until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@id='destinationcity']"), receiver.get("city").toUpperCase()));
    }

    @Then("^I verify total charges appear$")
    public void iVerifyTotalChargesAppear() {
        By total = By.xpath("//span[@id='total-charges-spinner']");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(total));
        WebElement totalCharges = getDriver().findElement(total);
        assertThat(totalCharges.getText()).isNotEmpty();
        currentCharge = totalCharges.getText();
    }

    @And("^I set packaging type$")
    public void iSetPackagingType() {

        By packageXpath = By.xpath("//*[@id='nbsPackagePackagingTypeDropdown0']");
///*[@value='3: Object']

        getWait().until(ExpectedConditions.visibilityOfElementLocated(packageXpath));
        Select select = new Select(getDriver().findElement(packageXpath));
        select.selectByVisibleText("UPS Letter");
        //getDriver().findElement(packageXpath).click();
        getDriver().findElement(By.xpath("//*[@id='nbsPackagePackageWeightField0']")).sendKeys("1");

    }

    @Then("^I verify total charges changed$")
    public void iVerifyTotalChargesChanged() {
        By total = By.xpath("//span[@id='total-charges-spinner']");

        getWait().until(ExpectedConditions.invisibilityOfElementWithText(total, currentCharge));
        String totalChargesNow = getDriver().findElement(total).getText();

        assertThat(currentCharge.equals(totalChargesNow)).isFalse();
        currentCharge = totalChargesNow;
    }

    @And("^I select cheapest delivery option$")
    public void iSelectCheapestDeliveryOption() {

        By priceXpath = By.xpath("//*[contains(@id, 'nbsServiceTileTotalCharge')]");
       // new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(price));
        getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceXpath));


        List<WebElement> pricesElements = getDriver().findElements(priceXpath);

        WebElement cheapElement = pricesElements.get(0);
        String priceString = cheapElement.getText().replace("$", "").trim();
        BigDecimal currentPrice = new BigDecimal(priceString);


        for (WebElement element: pricesElements) {
            priceString = element.getText().replace("$", "").trim();
            BigDecimal price = new BigDecimal(priceString);

            if (price.doubleValue() < currentPrice.doubleValue()) {
                cheapElement = element;
                currentPrice = price;
            }
            Actions actions = new Actions(getDriver());
            actions.moveToElement(cheapElement);
            actions.click(cheapElement);
            actions.perform();

        }



    }

    @When("^I set Saturday Delivery type$")
    public void iSetSaturdayDeliveryType() {
        if (getDriver().findElement(By.xpath("//*[@optionname='nbsSaturdayDeliveryOptionBaseOption']//*[contains(@class, 'ups-lever_switch_no')]")).isDisplayed() ) {
            WebElement sat = getDriver().findElement(By.xpath("//*[@optionname='nbsSaturdayDeliveryOptionBaseOption']//*[@class='ups-lever_switch_bg']"));

            Page.clickWithJS(sat);
//            JavascriptExecutor executor = (JavascriptExecutor)getDriver();
//            executor.executeScript("arguments[0].click();", sat);
        }
        
    }

    @And("^I select Paypal payment type$")
    public void iSelectPaypalPaymentType() {

        By otherXpath = By.xpath("//*[@for='other-ways-to-pay-tile']//span");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(otherXpath));
        WebElement otherPay = getDriver().findElement(otherXpath);

        Actions actions = new Actions(getDriver());

        actions.moveToElement(otherPay).click().perform();

        By paypalXpath = By.xpath("//img[contains(@src, 'paypal')]");
        getWait().until(ExpectedConditions.visibilityOfElementLocated(paypalXpath));

        WebElement paypal = getDriver().findElement(paypalXpath);

        actions.click(paypal).perform();
        
    }

    @Then("^I review all recorded details on the review page$")
    public void iReviewAllRecordedDetailsOnTheReviewPage() throws Exception {

        WebElement textElement = getDriver().findElement(By.xpath("//*[@id='main']"));
        getWait().until(ExpectedConditions.visibilityOf(textElement));
        String bodyText = textElement.getText();

        ArrayList<String> vData = new ArrayList<String>();

        vData.addAll(getReceiver().values());
        vData.addAll(getSender().values());
        vData.add("saturday");
        vData.add(currentCharge);

        for (String data: getReceiver().values()) {
            assertThat(bodyText).containsIgnoringCase(data);

        }

    }
}
