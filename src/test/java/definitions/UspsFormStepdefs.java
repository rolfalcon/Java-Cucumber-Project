package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.UspsCalculatePage;
import pages.UspsForm;

public class UspsFormStepdefs {
    @Given("^I go to usps page$")
    public void iGoToUspsPage() {
        new UspsForm().open();
    }

    @When("^I go to Lookup ZIP page by address form$")
    public void iGoToLookupZIPPageByAddressForm() {
        new UspsForm().goToZipPage();
    }


    @And("^I fill out \"([^\"]*)\" street, \"([^\"]*)\" city, \"([^\"]*)\" state form$")
    public void iFillOutStreetCityStateForm(String streetAddress, String city, String state) throws Throwable {
        new UspsForm().fillAddress(streetAddress, city, state);

    }

    @Then("^I validate \"([^\"]*)\" zip code exists in the result form$")
    public void iValidateZipCodeExistsInTheResultForm(String zip) throws Throwable {
       new UspsForm().verifyZip(zip);
    }

    @When("^I go to Stamps form$")
    public void iGoToStampsForm() {

    }

    @And("^I select \"([^\"]*)\" form$")
    public void iSelectForm(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I verify \"([^\"]*)\" stamp form$")
    public void iVerifyStampForm(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I go to Calculate Price page object$")
    public void iGoToCalculatePricePageObject() {
        new UspsForm().selectPriceBox();
        
    }

    @And("^I select \"([^\"]*)\" with \"([^\"]*)\" shape$")
    public void iSelectWithShape(String country, String shape) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        UspsCalculatePage page = new UspsCalculatePage();
        page.setSelectCountry(country);
        page.setPostCardButton();

    }

    @And("^Idefine \"([^\"]*)\" quantity page object$")
    public void idefineQuantityPageObject(String value) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        new UspsCalculatePage().setSelectPostCardQty(value);
     }

    @Then("^I calculate the price and validate cost is \"([^\"]*)\" page$")
    public void iCalculateThePriceAndValidateCostIsPage(String price) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        UspsCalculatePage page = new UspsCalculatePage();
        page.clickCalculate();
        page.verifyPrice(price);

    }

    @When("^I go to Postal Store tab$")
    public void iGoToPostalStoreTab() {
        new UspsForm().selectPostalStore();
        
    }

    @And("^I enter \"([^\"]*)\" into store search page object$")
    public void iEnterIntoStoreSearchPageObject(String value) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        new UspsForm().enterPostalSearch(value);
    }

    @Then("^I search and validate no products found page object$")
    public void iSearchAndValidateNoProductsFoundPageObject() {
    }
}
