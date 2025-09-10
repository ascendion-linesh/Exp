Feature: Booking History
  As a logged-in user,
  I want to view my booking history,
  So that I can see my past bookings.

  Scenario: View booking history
    Given the user is logged in and has previous bookings
    When the user clicks on the user profile
    And selects 'My Bookings'
    Then a list of past bookings should be displayed
