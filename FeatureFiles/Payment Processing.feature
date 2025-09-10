Feature: Payment Processing
  As a user,
  I want to pay for my booking,
  So that I can confirm my ticket purchase.

  Scenario: Payment with valid card details
    Given the user is on the payment page
    When the user enters valid card details
    And clicks on the 'Pay' button
    Then the payment should be successful and the confirmation page with ticket details should be shown

  Scenario: Payment with invalid card details
    Given the user is on the payment page
    When the user enters invalid card details
    And clicks on the 'Pay' button
    Then the payment should fail and an error message should be displayed
