@usps
  Feature: USPS scenarios

    @usps1 @usps_zip
    Scenario: Validate ZIP code for Portnov Computer School
      Given I go to "usps" page
      When I go to Lookup ZIP page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      Then I validate "94022" zip code exists in the result

    @usps2 @task3
    Scenario: Postal Store
      Given I go to "usps" page
      And I fill "12345" on Search Postal textfield
      Then I validate it did not match any products

#      * 1) Go to "Stamps and Supplies"
##      * 2) Go to Stamps
##      * 3) Select Mail Priority Mail
##      * 4) Verify 1 item on the page

    @usps2 @task4
      Scenario: Verify priority mail on stampes
      Given I go to "usps" page
      When I go to Stamps
      And I select "Priority Mail"
      Then I verify "Joshua Tree" stamp

#      * 1) Go to "Stamps and Supplies"
#      * 2) Go to Stamps
#      * 3) Unselect "Stamps" checkbox
#      * 4) Select Size "18 Months"
#      * 5) Click pink color
#      * 6) Verify <Pink X> and <1 Month X> hint: //span[contains(text(),
#    'Pink')][contains(@onclick, '18-months')]
#      * 7) Verify <Pink Just Arrived Onesie> text

    @usps3 @task5
      Scenario: Verify 24 month selection
        Given I go to "usps" page
        When I go to Stamps
        And I unselect "Stamps" checkbox
        And I select size "24 Months"
        Then I verify "Pink" and "24 month"
        And I verify "Pink Just Arrived Onesie" text
