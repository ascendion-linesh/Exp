Feature: User Authentication and Session Management
  As a registered user,
  I want to securely log in, log out, and manage my session,
  So that I can access my personal dashboard and data safely.

  Scenario: Login with valid credentials
    Given the user is registered
    And the user is on the login page
    When the user enters a valid email and password
    And clicks the 'Login' button
    Then the user should be redirected to the dashboard
    And a session should be established

  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid email or password
    And clicks the 'Login' button
    Then an error message should be displayed
    And the user should not be logged in

  Scenario: Login form validation for empty and invalid inputs
    Given the user is on the login page
    When the user leaves the email and password fields empty
    And clicks the 'Login' button
    Then inline validation errors should be shown

    When the user enters an invalid email format
    And clicks the 'Login' button
    Then inline validation errors should be shown

  Scenario: Redirect to dashboard on successful login
    Given the user is registered
    When the user logs in with valid credentials
    Then the user should be redirected to the dashboard page

  Scenario: Session expiry after logout or timeout
    Given the user is logged in
    When the user logs out
    And tries to access the dashboard
    Then the user should be redirected to the login page

    When the user waits for the session to timeout
    And tries to access the dashboard
    Then the user should be redirected to the login page
