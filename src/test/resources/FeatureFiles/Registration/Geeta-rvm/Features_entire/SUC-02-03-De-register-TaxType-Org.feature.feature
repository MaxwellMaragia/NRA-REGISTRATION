Feature: SUC:02-03 De-register Tax Type	Organization

  @SUC:02-03 @UAT_TCS-01.22.1
  Scenario Outline: UAT_TCS 01.22.1	To verify the Process of Deregistering a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And CLick on taxtype in grid
    And Select EDD "<EDD>"
    And Select de register Reason "<Reason>"
    Then Click on DeRegister button
    And  Verify the ARN number "<ARN>"
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
      | ClasificationType | TIN      | EDD        | Reason      | ARN                                           | Approve                 | Read     |
      | Individual        | P0020537 | 06/04/2029 | Liquidation | Processing Completed - Reference Number - ARN | Tax Type To De-Register | Approved |

  @SUC:02-03 @UAT_TCS-01.22.2
  Scenario: UAT_TCS-01.22.2	To verify the Process of Validation Error during Deregister Tax Type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Organisation"
    And Enter TIN number "V0013138"
    And Click on search
    And CLick on taxtype in grid
    And Select EDD "06/04/2029"
    Then Click on DeRegister button
    Then Verify error message "Reason: Validation Error: Value is required"

  @SUC:02-03 @UAT_TCS-01.22.4
  Scenario Outline: UAT_TCS 01.22.4	To verify the Process of Supervisor rejecting deregistration application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then Click table column "//*[@id='DeregisterRegime:deregTable_data']/tr[3]/td[3]"
    And Select EDD "<EDD>"
    And Select de register Reason "<Reason>"
    Then Click on DeRegister button
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Wait for text "Tax Type To De-Register" to load in frame "WebResource_RegistrationApplicationAngular"
    And Select rejection option on taxtype deregistration
    Then Enter Outcome Notes "Invalid data"
    And Enter Outcome Reason for Taxtype deregistration
    Then Click save CRM
    Then Status should be "Rejected"
    Examples:
      | ClasificationType | TIN      | EDD        | Reason                |
      | Organisation      | C0019582 | 06/04/2029 | Change of Return Type |

  @SUC:02-03 @UAT_TCS-01.22.5
  Scenario: UAT_TCS-01.22.5	To verify the Process of Deregistering Company Income Tax when other Taxes are active
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Organisation"
    And Enter TIN number "C0019582"
    And Click on search
    Then Click table column "//td[contains(text(),'Capital Gain Tax(CGT)')]"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "deregistered"

  @SUC:02-03 @UAT_TCS-01.22.6
  Scenario: UAT_TCS-01.22.6	To verify the Process of Deregistering a Tax Type when Balance is greater than Zero
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Organisation"
    And Enter TIN number "C0019582"
    And Click on search
    Then Click table column "//td[contains(text(),'Domestic VAT')]"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "Domestic VAT - Liability exists, the taxtype cannot be de-registered."

  @SUC:02-03 @UAT_TCS-01.22.7
  Scenario: UAT_TCS-01.22.7	To verify the Process of Providing a Valid EDD
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Enter TIN number "P0015169"
    And Click on search
    Then Click table column "//*[@id='DeregisterRegime:deregTable_data']/tr[2]/td[3]"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "EDD should be greater than the period end date of the latest transaction."

  @SUC:02-03 @UAT_TCS-01.22.8
  Scenario: UAT_TCS-01.22.8	To verify the Process of Deregistering Capital Gains Tax  type when the registered rental properties are greater than Zero
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "V0020675"
    And Click on search
    Then Click table column "//td[contains(text(),'Capital Gain Tax(CGT)')]"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "Property rent income exists. Taxtype cannot be deregistered"



