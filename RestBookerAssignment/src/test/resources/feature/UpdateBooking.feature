Feature: Validate Update Booking Endpoint

  Scenario: Verify user can update the existing booking
    #create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_booking.json"
    When user performs post call
    Then verify status code is 200
    And store "bookingid" into "booking.id"

    #create token
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_token.json"
    When user performs post call
    Then verify status code is 200
    And store "token" into "auth.token"

    #update booking
    Given user wants to call "/booking/@id" end point
    And set header "Content-Type" to "application/json"
    And set header "Cookie" to "token=@token"
    And set request body from file "create_booking.json" with "additionalneeds" value "Veg Breakfast Only"
    When user performs put call
    Then verify status code is 200
    And verify response body has a field "additionalneeds" is "Veg Breakfast Only"
