Feature: Personal Loan Calculator Functionality
  As a user,
  I want to use the personal loan calculator,
  So that I can estimate my loan payments accurately.

  Scenario: Loan calculator page loads successfully
    Given the user has an active internet connection
    When the user navigates to the personal loan calculator page
    Then the calculator page should load with all UI elements visible

  Scenario: Required input fields are present
    Given the user is on the personal loan calculator page
    When the user views the input section
    Then the Loan Amount, Interest Rate, Loan Term, and other relevant input fields should be present and visible

  Scenario: Calculation with valid inputs
    Given the user is on the personal loan calculator page
    When the user enters a valid loan amount
    And enters a valid interest rate
    And enters a valid loan term
    And clicks the 'Calculate' button
    Then the calculator should display the correct monthly payment and total interest values

  Scenario: Error handling for blank required fields
    Given the user is on the personal loan calculator page
    When the user leaves all required input fields blank
    And clicks the 'Calculate' button
    Then appropriate error messages should be displayed for each blank required field

  Scenario: Error handling for invalid loan amount
    Given the user is on the personal loan calculator page
    When the user enters an invalid loan amount
    And enters valid values for other fields
    And clicks the 'Calculate' button
    Then an error message should be displayed for the invalid loan amount

  Scenario: Error handling for invalid interest rate
    Given the user is on the personal loan calculator page
    When the user enters a valid loan amount
    And enters an invalid interest rate
    And enters a valid loan term
    And clicks the 'Calculate' button
    Then an error message should be displayed for the invalid interest rate

  Scenario: Error handling for invalid loan term
    Given the user is on the personal loan calculator page
    When the user enters a valid loan amount
    And enters a valid interest rate
    And enters an invalid loan term
    And clicks the 'Calculate' button
    Then an error message should be displayed for the invalid loan term

  Scenario: Reset or clear functionality
    Given the user is on the personal loan calculator page
    When the user enters values in all input fields
    And clicks the 'Reset' or 'Clear' button
    Then all input fields and result areas should be cleared

  Scenario: Calculation with large input values
    Given the user is on the personal loan calculator page
    When the user enters a large loan amount
    And enters a valid interest rate and loan term
    And clicks the 'Calculate' button
    Then the calculator should display correct results without errors or performance issues

  Scenario: Input field format validation
    Given the user is on the personal loan calculator page
    When the user tries to enter alphabets or special characters in numeric fields
    Then the fields should restrict input to valid formats or show error messages

  Scenario: Calculation with edge case inputs
    Given the user is on the personal loan calculator page
    When the user enters edge case values such as 0% interest or a 1 month term
    And clicks the 'Calculate' button
    Then the calculator should process the edge cases and display correct results or appropriate messages
