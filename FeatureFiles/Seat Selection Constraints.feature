Feature: Seat Selection Constraints
  As a user booking tickets,
  I want to be restricted from selecting more seats than allowed,
  So that booking policies are enforced.

  Scenario: Attempt to select more seats than allowed
    Given the user is booking tickets
    When the user attempts to select more than the allowed number of seats
    Then an error message or restriction should be shown
