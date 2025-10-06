Feature: Validate Get Booking Endpoint

  Scenario: Verify user can get list of booking
    #create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json"
    When user performs post call
    Then verify status code is 200
    And store "bookingid" into "booking.id"

    #get booking with recently created id
    Given user wants to call "/booking/@id" end point
    When user performs get call
    Then verify status code is 200
