Feature: [SUC:01-10] De-Register Taxpayer_Organization

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto DeRegister

  @SUC:01-10 @UAT_TCS-01.15.1
  Scenario Outline: UAT_TCS 01.15.1 To verify the Process of Deregistering a Tax type
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then Click on deregister checkbox
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
    Then Approve taxtype deregistration from dropdown
    Then Click save CRM
    Then Status should be "Approved"
    Examples:
      | ClasificationType | TIN      | EDD        | Reason      |
      | Individual        | P0020699 | 06/04/2029 | Liquidation |

  @SUC:01-10 @UAT_TCS-01.15.2
  Scenario: UAT_TCS-01.15.2-To verify the process of not finding a Taxpayer for Deregistration
    Then Enter TIN number "P0028182"
    And Click on search
    Then Verify no data is found in table

  @SUC:01-10 @UAT_TCS-01.15.3
  Scenario: UAT_TCS-01.15.3-To verify the process of checking Validation error during Deregistration
    Then Enter TIN number "C0028116"
    And Click on search
    Then Click table column "//*[@id='DeregisterRegime:deregTable_data']/tr[1]/td[3]"
    And Select EDD "06/04/2029"
    Then Click on DeRegister button
    Then Verify error message "Reason: Validation Error: Value is required."

  @SUC:01-10 @UAT_TCS-01.15.4
  Scenario: UAT_TCS-01.15.4-To verify the process of Abandoning Deregistration process
    Then Enter TIN number "C0028116"
    And Click on search
    Then Click Cancel "DeregisterRegime:Cancel"
    Then Verify abandon process "http://trips-nra:8001/trips-ui/faces/login/Welcome.xhtml"

  @SUC:01-10 @UAT_TCS-01.15.5
  Scenario Outline: UAT_TCS 01.15.5 To verify the process of Rejecting Taxpayer Deregistration Application
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then Click on deregister checkbox
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
    Then Wait for text "Reason for De-Registration" to load in frame "WebResource_RegistrationApplicationAngular"
    And Select rejection option on taxtype deregistration
    Then Enter Outcome Notes "Invalid data"
    And Enter Outcome Reason for Taxtype deregistration
    Then Click save CRM
    Then Status should be "Rejected"
    Examples:
      | ClasificationType | TIN      | EDD        | Reason      |
      | Individual        | P0020706 | 06/04/2029 | Liquidation |


