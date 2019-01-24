package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SampleForm;

import java.util.HashMap;

import static support.TestContext.getSample;

public class FormStepDevs {
    @Given("^I open sample page$")
    public void iOpenSamplePage() {
        new SampleForm().open();
    }


    @When("^I fill out sample fields$")
    public void iFillOutSampleFields() throws Exception{
        SampleForm form = new SampleForm();

        HashMap<String, String> sample = getSample();


        form.enterUserName(sample.get("username"));
        form.fillEmail(sample.get("email"));
        form.enterName(sample.get("firstname"), sample.get("lastname"), "F");
        form.enterPassword(sample.get("password"), sample.get("password"));
        form.clickPrivacyCheckbox();
    }

    @And("^I submit form$")
    public void iSubmitForm() {
        new SampleForm().clickSubmit();

    }

    @Then("^I verify all fields$")
    public void iVerifyAllFields() {
    }
}
