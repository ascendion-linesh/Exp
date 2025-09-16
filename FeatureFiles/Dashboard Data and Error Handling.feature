Feature: Dashboard Data and Error Handling
  As a logged-in user,
  I want to view my personalized dashboard data,
  So that I can access information relevant to me.

  Scenario: Display user-specific data on dashboard
    Given the user is logged in
    When the user navigates to the dashboard
    Then the user's data should be displayed correctly

  Scenario: Handle API errors gracefully on dashboard
    Given the API is unavailable
    And the user is logged in
    When the user refreshes the dashboard
    Then an error message should be shown
    And the application should not crash

  Scenario: Show loading indicator during API calls
    Given the user is logged in
    When the dashboard is loading data from the API
    Then a loading spinner or indicator should be displayed
