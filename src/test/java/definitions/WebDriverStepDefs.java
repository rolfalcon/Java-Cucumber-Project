package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class WebDriverStepDefs {
    @Given("^I go to \"([^\"]*)\" page$")
    public void iGoToPage(String page) throws Throwable {
        if (page.equalsIgnoreCase("sample")) {
            getDriver().get("https://skryabin.com/webdriver/html/sample.html");
        } else if (page.equalsIgnoreCase("google")) {
            getDriver().get("https://www.google.com/");
        } else if (page.equalsIgnoreCase("usps")) {
            getDriver().get("https://www.usps.com/");
        } else if (page.equalsIgnoreCase("ups_global")) {
            getDriver().get("https://www.ups.com");
        } else if (page.equalsIgnoreCase("ups")) {
            getDriver().get("https://www.ups.com/us/en/Home.page");
        } else if (page.equalsIgnoreCase("converter")) {
            getDriver().get("https://www.unitconverters.net/");
        }
        else {
            throw new Exception("Page not recognized: " + page);
        }
    }

    @And("^I print page details$")
    public void iPrintPageDetails() {

        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getWindowHandle());
//        System.out.println(getDriver().getPageSource());
    }

    @When("^I go back and forward, then refresh the page$")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
    }

    @And("^I change resolution to \"([^\"]*)\"$")
    public void iChangeResolutionTo(String layout) throws Throwable {

        if (layout.equalsIgnoreCase("phone")) {
            Dimension phone = new Dimension(400, 768);
            getDriver().manage().window().setSize(phone);
        } else if (layout.equalsIgnoreCase("desktop")) {
            Dimension desktop = new Dimension(1600, 768);
            getDriver().manage().window().setSize(desktop);
        } else {
            throw new Exception("Unknown layout: " + layout);
        }

    }

    @And("^I fill out required fields$")
    public void iFillOutRequiredFields() throws Exception {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("skryabin");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("slava@skryabin.com");
        getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("password1");
        getDriver().findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("password1");

        getDriver().findElement(By.xpath("//input[@name='name']")).click();
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("Slava");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Skryabin");
        getDriver().findElement(By.xpath("//div[@aria-describedby='nameDialog']//span[contains(text(),'Save')]")).click();

        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();

    }

    @And("^I submit the page$")
    public void iSubmitThePage() throws Exception {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }

    @Then("^I assert required fields$")
    public void iAssertRequiredFields() {
        WebElement resultLegend = getDriver().findElement(By.xpath("//legend[@class='applicationResult']"));
        assertThat(resultLegend.isDisplayed()).isTrue();

        String actualPrivacyPolicy = getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText();
        assertThat(actualPrivacyPolicy).isEqualToIgnoringCase("true");

        String resultText = getDriver().findElement(By.xpath("//div[@id='samplePageResult']//section")).getText();
        System.out.println(resultText);
        assertThat(resultText).containsIgnoringCase("Slava Skryabin");
        assertThat(resultText).containsIgnoringCase("slava@skryabin.com");
        assertThat(resultText).containsIgnoringCase("[entered]");
        assertThat(resultText).doesNotContain("password1");
    }

    @And("^I print browser logs$")
    public void iPrintBrowserLogs() {
        LogEntries logs = getDriver().manage().logs().get("browser");
        for(LogEntry log : logs) {
            System.out.println(log);
        }

    }

    @And("^I \"([^\"]*)\" third party agreement$")
    public void iThirdPartyAgreement(String action) throws Throwable {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        if (action.equals("accept")) {
            getDriver().switchTo().alert().accept();
        } else if (action.equals("dismiss")) {
            getDriver().switchTo().alert().dismiss();
        } else {
            throw new Exception("Unsupported action: " + action);
        }
    }

    @And("^I add contact \"([^\"]*)\" with phone \"([^\"]*)\"$")
    public void iAddContactWithPhone(String name, String phone) throws Throwable {

        getDriver().switchTo().frame("additionalInfo");

        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys(name);
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys(phone);

        getDriver().switchTo().defaultContent();

    }

    @And("^I verify \"([^\"]*)\" in related documents$")
    public void iVerifyInRelatedDocuments(String documentName) throws Throwable {

        String originalWindow = getDriver().getWindowHandle();

        getDriver().findElement(By.xpath("//button[contains(@onclick,'window')]")).click();

        Set<String> windows =  getDriver().getWindowHandles();
        for (String window : windows) {
            getDriver().switchTo().window(window);
        }

        String documentPageText = getDriver().findElement(By.xpath("//body")).getText();
        assertThat(documentPageText).containsIgnoringCase(documentName);

        getDriver().switchTo().window(originalWindow);

    }
}
