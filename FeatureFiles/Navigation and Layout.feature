Feature: Navigation and Layout
  As a user,
  I want to navigate the application easily,
  So that I can access different sections and have a consistent experience.

  Scenario: Header navigation links
    Given the user is logged in
    When the user observes the header
    Then the 'Dashboard' and 'Logout' links should be visible

    When the user clicks the 'Dashboard' link
    Then the dashboard page should be displayed

    When the user clicks the 'Logout' link
    Then the user should be logged out
    And redirected to the login page

  Scenario: Footer presence on all pages
    Given the user is on the login page
    When the user logs in
    And navigates to the dashboard
    Then the footer should be visible on all pages

  Scenario: Responsive layout on desktop
    Given the user opens the application on a desktop browser
    Then the layout should be user-friendly and not broken

  Scenario: Responsive layout on mobile
    Given the user opens the application on a mobile device or emulator
    Then the layout should adapt to the small screen
    And navigation should work correctly
