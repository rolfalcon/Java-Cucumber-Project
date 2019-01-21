package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ConverterForm;

public class ConverterFormDefs {
    @Given("^I open the converter page form$")
    public void iOpenTheConverterPageForm() {
        new ConverterForm().open();
    }

    @When("^I click on \"([^\"]*)\" form$")
    public void iClickOnForm(String measure) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        new ConverterForm().chooseMeasure(measure);
    }

    @And("^I set \"([^\"]*)\" to \"([^\"]*)\" form$")
    public void iSetToForm(String from, String to) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        new ConverterForm().chooseFromTo(from, to);
    }

    @Then("^I enter into From field \"([^\"]*)\" and verify \"([^\"]*)\" result form$")
    public void iEnterIntoFromFieldAndVerifyResultForm(String from, String verify) throws Throwable {
        new ConverterForm().chooseFromValueandVerify(from, verify);

    }
}
