Feature: [SUC:02-04] Re-register Tax Type	Organisation-ReRegister TaxType
#  @[SUC:02-04]
  Scenario Outline: UAT_TCS 01.24.1	To verify the Process of Reregistering a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Organization page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Organisation                       | 1 |
      | TaxPayer_TIN            | V0020111                           | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EOR                     | 16092002                           | 4 |
      | Amend Reson             | Change of Additional Details       | 5 |
      | Return Type             | Pay as yoy earn                    | 6 |


    Then ReRegister ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then wait for webpage to load

    Examples:
      | username  | password | browser |
      | tripsuser | Passw0rd | FireFox |

#  @[SUC:02-04]
  Scenario Outline:  Approve Scenario
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

  #@[SUC:02-04]
  Scenario Outline: UAT_TCS 01.18.1	To verify the Process of Finding a Taxpayer by TPIN
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
      | username  | password | browser | ClasificationType | TIN      |
      | tripsuser | Passw0rd | FireFox | Organisation      | V0019534 |
    
 
    
    
   
   
    