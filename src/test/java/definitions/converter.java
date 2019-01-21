package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.Animal;
import pages.Cat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import static support.TestContext.getDriver;
import static support.TestContext.getWait;

public class converter {
    @When("^I click on \"([^\"]*)\"$")
    public void iClickOn(String tab) throws Throwable {
        String xpath = String.format("//a[contains(@href, '%s')]", tab);
////        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        getDriver().findElement(By.xpath(xpath)).click();
//        getDriver().findElement(By.xpath("//a[contains(@href,'" + tab + "')]")).click();


    }

    @And("^I set \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iSetTo(String from, String to) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='calFrom']")));
        WebElement fromElement = getDriver().findElement(By.xpath("//*[@id='calFrom']"));
        Select selectFrom = new Select(fromElement);
        selectFrom.selectByVisibleText(from);

        WebElement toElement = getDriver().findElement(By.xpath("//*[@id='calTo']"));
        Select selectTo = new Select(toElement);
        selectTo.selectByVisibleText(to);


    }

    @Then("^I enter into From field \"([^\"]*)\" and verify \"([^\"]*)\" result$")
    public void iEnterIntoFromFieldAndVerifyResult(String from, String to) throws Throwable {
        WebElement fromElement = getDriver().findElement(By.xpath("//*[@name='fromVal']"));
        fromElement.sendKeys(from);

        WebElement toElement = getDriver().findElement(By.xpath("//*[@name='toVal']"));
        String value = toElement.getAttribute("value");
        assertThat(value).containsIgnoringCase(to);

    }

    @Given("^I work with classes$")
    public void iWorkWithClasses() {

        Cat myCat = new Cat();
        myCat.setName("Fluffy");
        myCat.meow();
    }
}
