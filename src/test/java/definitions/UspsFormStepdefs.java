package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
}
