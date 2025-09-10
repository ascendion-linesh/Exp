Feature: City Filter
  As a user,
  I want to filter events and movies by city,
  So that I can see options available in my location.

  Scenario: Filter events and movies by city
    Given the user is on the home page
    When the user clicks on the city selector
    And chooses a different city
    Then the events and movies should update according to the selected city
