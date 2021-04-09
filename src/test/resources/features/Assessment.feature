Feature: Assessment test for BDD

  Scenario: TC001-Login
    Given I open the application
    When I verify your logo new experience is displayed
    Then I click on signin link
    And I enter invalid username "pramodhv24@gmail.com" and password "qurate"
    Then I verify the error message as "Authentication failed."
    And I enter valid username "arunkumar.c1993@gmail.com" and password "qurate"
    Then I verify user tab of name "t t" is displayed


  Scenario: TC002-Add Item To Cart
    When I move mouse to "Dresses"
    And I click on "SUMMER DRESSES"
    And I select the list view
    Then I add "1st" item to the cart
    And I add "2nd" item to the cart
    Then I verify number of items in cart is "2"

  Scenario: TC_003_OrderPlacement
    When I checkout the products
    Then I verify the product name and price in order summary
    And I click on proceed to checkout
    Then I verify application is in Addresses tab
    And I click on proceed to checkout in address
    Then I verify application is in Shipping tab
    And I verify terms and condition is not selected
    And I click on terms and condition checkbox
    And I click on proceed to checkout in shipping
    Then I verify the total amount
    And I verify pay by bank and pay by wire option is available
    And I close the browser




