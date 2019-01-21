package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.SampleForm;

public class FormStepDevs {
    @Given("^I open sample page$")
    public void iOpenSamplePage() {
        new SampleForm().open();
    }


    @When("^I fill out sample fields$")
    public void iFillOutSampleFields() {
        SampleForm form = new SampleForm();

        form.enterUserName("stupidUser");
        form.fillEmail("stupid@stupid.com");
    }

    @And("^I submit form$")
    public void iSubmitForm() {
        new SampleForm().clickSubmit();

    }
}
