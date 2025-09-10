Feature: Search Functionality
  As a user,
  I want to search for movies or events,
  So that I can find what I am interested in.

  Scenario: Search for a movie or event
    Given the user is on the home page
    When the user enters a movie or event name in the search bar
    And clicks on the 'Search' button
    Then relevant search results should be displayed
