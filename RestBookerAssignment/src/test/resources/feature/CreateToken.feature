Feature: Validate Create Token Endpoint

  Scenario: Verify user can a create a token
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set request body from file "create_token.json"
    When user performs post call
    Then verify status code is 200
    And verify response schema with "create_token_schema.json"

