Feature: [SUC:02-04] Re-register Tax Type Individual: Reregister Tax Type
  #@[SUC:02-04]
  Scenario Outline: UAT_TCS 01.23.1	To verify the Process of Reregistering a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Individual                         | 1 |
      | TaxPayer_TIN            | P0020537                           | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EOR                     | 16092002                           | 4 |
      | Amend Reson             | Change of Additional Details       | 5 |
      | Return Type             | Pay as yoy earn                    | 6 |
    Then ReRegister ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then wait for webpage to load
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Approve                            | Read     |
      | Taxable Turnover exceeds threshold | Approved |

    #  @[SUC:02-04]
  Scenario Outline: UAT_TCS 01.11.2(UAT_TCS 01.17.1) To verify the process of Successful Registration application search,
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And  Goto Find taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then wait for webpage to load


    Examples:
      | username   |password | browser|  ClasificationType|     TIN|
      | tripsuser  |Passw0rd | FireFox|      Individual| P0020466|

#  @[SUC:02-04]
  Scenario Outline: UAT_TCS 01.11.2(UAT_TCS 01.17.1) To verify the process of Successful Registration application search,
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in

    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax |Re-Register Tax|0 |
      | TaxpayerClassificationT |Individual|1 |
      | TaxPayer_TIN |P0020466|2|
      | Re-Reg Reason||3|
      | EOR |07042029|4|
      | Amend Reson |Change of Additional Details|5|
      | Return Type |Pay as yoy earn|6|
    And click reRegister Button
    Then message is displayed "Reason: Validation Error: Value is required"


    Examples:
      | username   |password |
      | tripsuser  |Passw0rd |

#  @[SUC:02-04]
  Scenario Outline: UAT_TCS 01.23.3	To verify the Process of Providing Valid EDR
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax |Re-Register Tax|0 |
      | TaxpayerClassificationT |Individual|1 |
      | TaxPayer_TIN |P0020466|2|
      | Re-Reg Reason|Incur Taxable Income|3|
      | EOR |16092002|4|
      | Amend Reson |Change of Additional Details|5|
      | Return Type |Pay as yoy earn|6|
    And click reRegister Button
    Then message is displayed "The Tax Type Re-registration Date must be greater than the Tax Type De-registration date"


    Examples:
      | username   |password |
      | tripsuser  |Passw0rd |


  #@[SUC:02-04]
  Scenario Outline: UAT_TCS 01.23.4	To verify the Process of Reregistering a Periodic Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax |Re-Register Tax|0 |
      | TaxpayerClassificationT |Individual|1 |
      | TaxPayer_TIN |C0018547|2|
      | Re-Reg Reason|Taxable Turnover exceeds threshold|3|
      | EOR |07042029|4|
      | Amend Reson |Change of Additional Details|5|
      | Return Type |Pay as you earn|6|
    Then ReRegister ARN number will generate
      |ARN number |    Processing Completed - Reference Number - ARN|0|
    Then wait for webpage to load
    And  Click on regisration link
    And  Goto Manage taxpayer
    And  Goto Find taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed


    Examples:
      | username   |password | browser|  ClasificationType|     TIN|
      | tripsuser  |Passw0rd | FireFox|      Individual| C0018547|

  #@[SUC:02-04]
  Scenario Outline: UAT_TCS 01.23.5	To verify the Process of Rejecting Reregistering Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax |Re-Register Tax|0 |
      | TaxpayerClassificationT |Individual|1 |
      | TaxPayer_TIN |Q0016023|2|
      | Re-Reg Reason|Taxable Turnover exceeds threshold|3|
      | EOR |07042029|4|
      | Amend Reson |Change of Additional Details|5|
      | Return Type |Pay as yoy earn|6|
    Then ReRegister ARN number will generate
      |ARN number |    Processing Completed - Reference Number - ARN|0|
    Then wait for webpage to load

    Examples:
      | username   |password | browser|
      | tripsuser  |Passw0rd | FireFox|

  @[SUC:02-04]
  Scenario Outline:  Reject Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
#    And Click on NextStage button
    Then switch to frame
    Then clicks Decline from the dropdown <Approve>
    Then Enter Outcome Notes <Notes>
    And Enter Outcome Reason for Taxpayer accounting
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      |    Notes            |Approve |Read |
      |Invalid Documentation|Taxable Turnover exceeds threshold|Rejected|

    
 
    
    
   
   
    