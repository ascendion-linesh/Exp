Feature: Event Booking
  As a logged-in user,
  I want to book tickets for an event,
  So that I can attend the event.

  Scenario: Book tickets for an event
    Given the user is logged in
    When the user searches for and selects an event
    And clicks on 'Book Tickets'
    And selects the date, time, and seats
    And proceeds to payment
    Then the user should be redirected to the payment page with a booking summary
