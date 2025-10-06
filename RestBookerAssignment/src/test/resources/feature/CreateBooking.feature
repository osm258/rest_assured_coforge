Feature: Validate Create Booking Endpoint

  Scenario: Verify user can a create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json" using POJO class
    When user performs post call
    Then verify status code is 200
    And verify response body has same data as request
    And verify response body has a field "booking.firstname" is "Test"
    And verify response schema with "create_booking_schema.json"
