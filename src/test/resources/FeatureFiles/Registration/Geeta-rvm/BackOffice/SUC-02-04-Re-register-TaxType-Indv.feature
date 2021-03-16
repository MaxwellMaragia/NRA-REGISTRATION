Feature: [SUC:02-04] Re-register Tax Type Individual: Reregister Tax Type

  @UAT_TCS-01.23.1 @Red-Dereg
  Scenario Outline: UAT_TCS 01.23.1	To verify the Process of Reregistering a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Individual                         | 1 |
      | TaxPayer_TIN            | N0000036455                        | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EOR                     | 16092002                           | 4 |
      | Amend Reson             | Change of Additional Details       | 5 |
      | Taxtype Type            | Personal Income Tax                | 6 |
    Then ReRegister ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click save CRM
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Approve                            | Read     |Approve|
      | Taxable Turnover exceeds threshold | Approved |Tax Type To Re-Register|

  @UAT_TCS-01.23.2
  Scenario: UAT_TCS 01.23.2 To verify the Process of checking Validation Error during Reregistration of Tax Type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax      | 0 |
      | TaxpayerClassificationT | Individual           | 1 |
      | TaxPayer_TIN            | N0000033472          | 2 |
      | Re-Reg Reason           | Please select        | 3 |
      | EOR                     | 16091990             | 4 |
      | Amend Reson             | Incur Taxable Income | 5 |
      | Return Type             | Personal Income Tax  | 6 |
    And click reRegister Button
    Then message is displayed "Reason: Validation Error: Value is required"


  @UAT_TCS-01.23.3
  Scenario: UAT_TCS-01.23.3 To verify the Process of Providing InValid EDR
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Individual                         | 1 |
      | TaxPayer_TIN            | N0000033472                        | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EDR                     | 16091990                           | 4 |
      | Amend Reson             | Incur Taxable Income               | 5 |
      | Return Type             | Personal Income Tax                | 6 |
    And click reRegister Button
    Then message is displayed "The Tax Type Re-registration Date must be greater than the Tax Type De-registration date"


  @UAT_TCS-01.23.4
  Scenario Outline: UAT_TCS 01.23.4	To verify the Process of Reregistering a Periodic Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Individual                         | 1 |
      | TaxPayer_TIN            | N0000033472                        | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EOR                     | 16092002                           | 4 |
      | Amend Reson             | Change of Additional Details       | 5 |
      | Return Type             | Personal Income Tax                | 6 |
    Then ReRegister ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then switch to frame
    Then Wait for text "Tax Type To Re-Register" to load in frame "WebResource_RegistrationApplicationAngular"
    Then Approve taxtype deregistration from dropdown
    Then Click save CRM
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Approve                            | Read     |
      | Taxable Turnover exceeds threshold | Approved |

  @UAT_TCS-01.23.5
  Scenario Outline: UAT_TCS 01.23.5	To verify the Process of Rejecting Reregistering Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the Re-RegisterTaxType Individual page
      | Re-Register Tax         | Re-Register Tax                    | 0 |
      | TaxpayerClassificationT | Individual                         | 1 |
      | TaxPayer_TIN            | N0000033472                        | 2 |
      | Re-Reg Reason           | Taxable Turnover exceeds threshold | 3 |
      | EOR                     | 16092002                           | 4 |
      | Amend Reson             | Change of Additional Details       | 5 |
      | Return Type             | Personal Income Tax                | 6 |
    Then ReRegister ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    Then clicks Decline from the dropdown <Reject>
    Then Enter Outcome Notes <Notes>
    And Enter Outcome Reason for Taxpayer accounting
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Read     | Reject                  | Notes                 |
      | Rejected | Tax Type To Re-Register | Error in data capture |

    
 
    
    
   
   
    