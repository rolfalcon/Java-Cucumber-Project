@converter
Feature: Converter page using forms

  Scenario Outline: Converter page using form page factory
    Given I open the converter page form
    When I click on "<tab>" form
    And I set "<from_unit>" to "<to_unit>" form
    Then I enter into From field "<from_value>" and verify "<expected_value>" result form
    Examples:
      | tab          |  from_unit  | to_unit   | from_value | expected_value |
      | Temperature  |  Fahrenheit | Celsius   | 54         | 12.2           |
      | Weight       |  Pound      | Kilogram  | 170        | 77             |
      | Length       |  Mile       | Kilometer | 50         | 80.4           |

