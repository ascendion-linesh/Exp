Feature: Movie Details Page
  As a user,
  I want to view details of a selected movie,
  So that I can learn more before booking.

  Scenario: View movie details
    Given the movie is listed
    When the user searches for and selects a movie
    And clicks on the movie title
    Then the movie details page should display the title, genre, duration, synopsis, and other relevant information
