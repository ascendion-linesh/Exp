Feature: Home Page Load
  As a user,
  I want to access the home page,
  So that I can start browsing movies and events.

  Scenario: Successful home page load
    Given the user navigates to https://in.bookmyshow.com/
    Then the home page should display banners, a search bar, and a navigation menu
