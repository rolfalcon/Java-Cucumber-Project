@form
  Feature: Sample form with Page Objet

    @form1
    Scenario: Sample form Page Object required Fields
      Given I open sample page
      When I fill out sample fields
      And I submit form
      Then I verify all fields