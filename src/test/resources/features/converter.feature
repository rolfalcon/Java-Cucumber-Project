@converter
Feature: Converter functions

  @converter1
  Scenario: Validate Fahrenheit to Celsius
    Given I go to "converter" page
    When I click on "Temperature"
    And I set "Fahrenheit" to "Celsius"
    Then I enter into From field "54" and verify "12.2" result

  @converter2
  Scenario Outline: Validate table with unit conversions
    Given I go to "converter" page
    When I click on "<tab>"
    And I set "<from_unit>" to "<to_unit>"
    Then I enter into From field "<from_value>" and verify "<expected_value>" result
    Examples:
      | tab          |  from_unit  | to_unit   | from_value | expected_value |
      | Temperature  |  Fahrenheit | Celsius   | 54         | 12.2           |
      | Weight       |  Pound      | Kilogram  | 170        | 77             |
      | Length       |  Mile       | Kilometer | 50         | 80.4           |

