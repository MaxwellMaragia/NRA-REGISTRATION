Feature: SUC:02-05 Suspend/Dormant Tax Type

  @SUC:02-05 @UAT_TCS-01.25.1 @UAT_TCS-01.25.6 @SuspReact
  Scenario Outline: UAT_TCS 01.25.1 To verify the Process of Paper Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "Suspend Tax Type"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "N0000020354"
    And Click on search
    Then Click table column TaxDetails "Personal Income Tax"
    And Enter suspension start date as todays date
    And Enter Suspension End Date as "03/09/2021"
    And Select outcome reason for suspension
    And Click suspend
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
      | Read     |Approve|
      | Approved |Tax Type To Suspend|

  @SUC:02-05 @UAT_TCS-01.25.2
  Scenario: UAT_TCS 01.25.2 To verify the Process of suspending Tax Type whose status is Suspended
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "N0000020354"
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
    And Enter TIN number "N0000020354"
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
    And Enter TIN number "N0000020354"
    And Click on search
    Then Click table column TaxDetails "Personal Income Tax"
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

# ********************************DOESTN'T WORK IN NRA,PIT CAN BE SUSPENDED WHEN OTHER TAXTYPES PRESENT ****************************
#  @SUC:02-05 @UAT_TCS-01.25.5
#  Scenario:  UAT_TCS 01.25.5 To verify the Process of Suspending Personal Income Tax when other Taxes are active
#    Given User navigates to the login page
#    When Enters the username "tripsuser" and password "Passw0rd"
#    Then User should be logged in
#    And  Click on regisration link
#    And  Goto Manage taxpayer
#    And Goto Suspend TaxType "Suspend Tax Type"
#    And Select Taxpayer Classification Type "Individual"
#    And Enter TIN number "N0000019925"
#    And Click on search
#    Then Click table column TaxDetails "Personal Income Tax"
#    And Enter suspension start date as todays date
#    And Enter Suspension End Date as "03/09/2021"
#    And Select outcome reason for suspension
#    And Click suspend
#    Then Verify error message "Personal Income tax should be the LAST tax type to be Suspended/Dormant"


  @SUC:02-05 @UAT_TCS-01.25.7
  Scenario Outline: UAT_TCS 01.25.7 To verify the Process of Supervisor rejecting the Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Suspend TaxType "SUSPEND TAX TYPE"
    And Select Taxpayer Classification Type "Individual"
    And Enter TIN number "N0000020354"
    And Click on search
    Then Click table column TaxDetails "Personal Income Tax"
    And Enter suspension start date as todays date
    And Enter Suspension End Date as "03/09/2021"
    And Select outcome reason for suspension
    And Click suspend
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
    Then clicks Decline from the dropdown <Reject>
    Then Enter Outcome Notes <Notes>
    And Enter Outcome Reason for Taxpayer accounting
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Read     | Reject              | Notes                 |
      | Rejected | Tax Type To Suspend | Error in data capture |