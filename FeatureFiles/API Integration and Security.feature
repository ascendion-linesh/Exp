Feature: API Integration and Security
  As a developer,
  I want the application to interact securely and correctly with backend APIs,
  So that user data and authentication are handled properly.

  Scenario: Use correct API URL from environment variables
    Given the correct API URL is set in the environment configuration
    When the application starts
    And the user attempts to log in
    Then the application should use the specified API URL for requests

  Scenario: API call for login
    Given the user is on the login page
    When the user enters credentials
    And clicks the 'Login' button
    Then an API call should be made to the /login endpoint with the correct payload

  Scenario: API call for dashboard data
    Given the user is logged in
    When the user navigates to the dashboard
    Then an API call should be made to the /dashboard endpoint

  Scenario: Error message for network failure
    Given the network is disconnected
    When the user attempts to log in or access the dashboard
    Then the user should see a network error message

  Scenario: Authentication token is not stored in plain text
    Given the user is logged in
    When the user inspects local storage, session storage, and cookies
    Then the authentication token should be stored securely
    And should not be stored in plain text
