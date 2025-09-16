Feature: Input Validation
  As a user,
  I want to be informed of invalid inputs,
  So that I can correct them before submitting forms.

  Scenario: Password validation rules
    Given the user is on the login page
    When the user enters a password that does not meet validation requirements
    And attempts to log in
    Then an inline error should be shown for the invalid password

  Scenario: Email validation rules
    Given the user is on the login page
    When the user enters an invalid email format
    And attempts to log in
    Then an inline error should be shown for the invalid email
