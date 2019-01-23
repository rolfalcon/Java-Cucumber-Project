@uspsform
Feature: USPS scenarios

  @usps1form @usps_zip_form
  Scenario: Validate ZIP code for Portnov Computer School
    Given I go to usps page
    When I go to Lookup ZIP page by address form
    And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state form
    Then I validate "94022" zip code exists in the result form

  @usps2form @usps_zip_form
  Scenario: Validate ZIP code for Portnov Computer School
    Given I go to usps page
    When I go to Lookup ZIP page by address form
    And I fill out "3874 Kirk Rd" street, "San Jose" city, "CA" state form
    Then I validate "95124" zip code exists in the result form

  @usps2 @task4
  Scenario: Verify priority mail on stampes
    Given I go to usps page
    When I go to Stamps form
    And I select "Priority Mail" form
    Then I verify "Joshua Tree" stamp form