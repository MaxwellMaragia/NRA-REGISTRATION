Feature: SUC:02-05 Suspend/Dormant Tax Type

  @SUC:02-05 @UAT_TCS-01.25.1 @UAT_TCS-01.25.6
  Scenario: UAT_TCS 01.25.1 To verify the Process of Paper Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020537"
    And Click on search
    Then Click table column "//td[contains(text(),'PAYE')]"
    And Enter suspension start date as todays date
    And Enter Suspension End Date as "03/09/2021"
    And Select outcome reason for suspension
    And Click suspend
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then approve transaction
    Then Click save CRM
    Then Status should be "Approved"

  @SUC:02-05 @UAT_TCS-01.25.2
  Scenario: UAT_TCS 01.25.2 To verify the Process of suspending Tax Type whose status is Suspended
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020180"
    And Click on search
    Then Verify suspended tax type "Domestic Excise" is not present in the table

  @SUC:02-05 @UAT_TCS-01.25.3
  Scenario: UAT_TCS 01.25.3 To verify the Process of suspending Tax Type whose status is Deregistered
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020180"
    And Click on search
    Then Verify suspended tax type "Turnover Tax(TOT)" is not present in the table

  @SUC:02-05 @UAT_TCS-01.25.4
  Scenario: UAT_TCS 01.25.4 To verify the Process of checking Validation Error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020180"
    And Click on search
    Then Click table column "//*[@id='SuspendRegime:suspendRegimeTableHandler_data']/tr[1]/td[4]"
    And Click suspend
    Then Verify error message "Reason: Validation Error: Value is required."
    And Enter suspension start date as todays date
    And Enter Suspension End Date as "03/09/2011"
    And Select outcome reason for suspension
    And Click suspend
    Then Verify error message "Suspension End Date must be greater than Suspension Start Date"
    And Enter suspension start date as "03/09/2011"
    And Select outcome reason for suspension
    And Click suspend
    Then Verify error message "Suspension Start Date must be greater than Effective Date of Registration"


  @SUC:02-05 @UAT_TCS-01.25.5
  Scenario Outline:  UAT_TCS 01.25.5 To verify the Process of Suspending Personal Income Tax when other Taxes are active
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "<SuspendTaxType>"
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then Enter Suspension Satrt Date "<StartDate>"
    And Enter Suspension End Date "<EndDate>"
      | Suspension Reason | No longer liable to tax type | 0 |
    And  get the message  "<validation>"
    Then wait for webpage to load
    Examples:
      | username  | password | browser | ClasificationType | TIN      | SuspendTaxType   | StartDate  | EndDate    | validation                                                              |
      | tripsuser | Passw0rd | FireFox | Individual        | P0023123 | SUSPEND TAX TYPE | 09/04/2022 | 09/04/2025 | Personal Income tax should be the LAST tax type to be Suspended/Dormant |

  @SUC:02-05 @UAT_TCS-01.25.7
  Scenario: UAT_TCS 01.25.7 To verify the Process of Supervisor rejecting the Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "P0020582"
    And Click on search
    Then Click table column "//td[contains(text(),'PAYE')]"
    And Enter suspension start date as todays date
    And Enter Suspension End Date as "03/09/2021"
    And Select outcome reason for suspension
    And Click suspend
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "Invalid documents"
    And Enter Outcome Reason for Taxpayer accounting
    Then Click save CRM
    Then Status should be "Rejected"