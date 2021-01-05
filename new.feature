Feature: BDD task
  Scenario Outline: Place order
    Given I want to navigate to home
    When I click the login button
    And I give <email> and <password>
    And I login
    And I click Women Section
    And From style select2 <2ndOption>
    And select 5th option from sort by
    And Search for <SearchValue>
    And I click on 'Quick View'
    And I add the quanity as '2' and click on 'Add to cart'
    And I click the cart button
    And I verify
    And I click 'Proceed to Checkout' and navigate to Address
    And I click 'Proceed to Checkout' and navigate to Shipping 
    And I check if error message is displayed
    And I click 'Proceed to Checkout' and navigate to Payment
    And I select a mode of payment and confirm the order
    Then My order is placed successfully
    
    Examples:
    | email					      | password  | 2ndOption |     SearchValue           |
    | antogifta@gmail.com |   12345   |  Dressy   |Faded short sleeve t-shirt |  
    