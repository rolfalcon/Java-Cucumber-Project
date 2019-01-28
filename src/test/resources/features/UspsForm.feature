@uspsform
Feature: USPS scenarios

  @usps7
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

  @usps9
  Scenario: Calculate price logic page object
    Given I go to usps page
    When I go to Calculate Price page object
    And I select "United Kingdom" with "Postcard" shape
    And Idefine "2" quantity page object
    Then I calculate the price and validate cost is "$2.30" page

  @usps10
  Scenario: Wrong store id does not match page object
    Given I go to usps page
    When I go to Postal Store tab
    And I enter "12345" into store search page object
    Then I search and validate no products found page object
