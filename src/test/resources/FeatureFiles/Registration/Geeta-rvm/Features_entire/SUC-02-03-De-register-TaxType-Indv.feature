
Feature: SUC:02-03 De-register Tax Type	Individual - De-Register Tax Type

  @SUC:02-03- @UAT_TCS-01.21.1 @Red-Dereg
  Scenario Outline: UAT_TCS 01.21.1	To verify the Process of Deregistering a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
#    And CLick on taxtype in grid
    Then Click table column taxtype "Personal Income Tax"
    And Select EDD "<EDD>"
    And Select de register Reason "<Reason>"
    Then Click on DeRegister button
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
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
      | ClasificationType | TIN      | EDD        | Reason      | Read     |Approve|
      | Individual        | N0000036218 | 06/04/2021 | Liquidation | Approved |Tax Type To De-Register|

  @SUC:02-03 @UAT_TCS-01.21.2
  Scenario: UAT_TCS-01.21.2	To verify the Process of Validation Error during Deregister Tax Type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "N0000033472"
    And Click on search
    And CLick on taxtype in grid
    And Select EDD "06/04/2029"
    Then Click on DeRegister button
    Then Verify error message "Reason: Validation Error: Value is required"

  @SUC:02-03 @UAT_TCS-01.21.3
  Scenario Outline: UAT_TCS 01.21.3	To verify the Process of Supervisor rejecting deregistration application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
#    And CLick on taxtype in grid
    Then Click table column taxtype "Personal Income Tax"
    And Select EDD "<EDD>"
    And Select de register Reason "<Reason>"
    Then Click on DeRegister button
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then switch to frame
    Then Wait for text "Tax Type To De-Register" to load in frame "WebResource_RegistrationApplicationAngular"
    And Select rejection option on taxtype deregistration
    Then Enter Outcome Notes "Invalid data"
    And Enter Outcome Reason for Taxtype deregistration
    Then Click save CRM
    Then switch to frame
    And Verify the String "<Read>"
    Examples:
      | ClasificationType | TIN      | EDD        | Reason      |Read|
      | Individual        | N0000036455 | 06/04/2029 | Liquidation |Rejected|

  @SUC:02-03 @UAT_TCS-01.21.4
  Scenario: UAT_TCS-01.21.4	To verify the Process of Deregistering Personal Income Tax when other Taxes are active
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0015169"
    And Click on search
    Then Click table column taxtype "Personal Income Tax"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "Personal Income Tax should be the last"

  @SUC:02-03 @UAT_TCS-01.21.5
  Scenario: UAT_TCS-01.21.5	To verify the Process of Deregistering a Tax Type when Balance is greater than Zero
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "C0019788"
    And Click on search
    Then Click table column taxtype "Withholding Tax(WHT)"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "Withholding Tax(WHT) - Liability exists, the taxtype cannot be de-registered."

  @SUC:02-03 @UAT_TCS-01.21.6
  Scenario: UAT_TCS-01.21.6	To verify the Process of Providing a Valid EDD
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0015169"
    And Click on search
    Then Click table column taxtype "Withholding Tax(WHT)"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "EDD should be greater than the period end date of the latest transaction."

  @SUC:02-03 @UAT_TCS-01.21.7
  Scenario: UAT_TCS-01.21.7	To verify the Process of Deregistering Capital Gains Tax  type when the registered rental properties are greater than Zero
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020653"
    And Click on search
    Then Click table column taxtype "Capital Gain Tax(CGT)"
    And Select EDD "06/04/2029"
    And Select de register Reason "Liquidation"
    Then Click on DeRegister button
    Then Verify error message "Property rent income exists. Taxtype cannot be deregistered"



