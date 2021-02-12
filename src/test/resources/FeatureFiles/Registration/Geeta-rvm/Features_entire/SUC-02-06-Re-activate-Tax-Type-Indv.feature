Feature: [SUC:02-06] Re-activate Tax Type-Individual

  @UAT_TCS-01.29.1-- @SuspReact
  Scenario Outline:  UAT_TCS 01.29.1-To verify the Process of Reactivating a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Reactive Taxtype
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And CLick on Reactivate taxtype in grid
    And Select Reactivate EDD "<EDD>"
    And Select Reactivate Taxtype Reason "<Reason>"
    Then Click on Reactivate button
    And  Verify the ARN number "<ARN>"
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
      | Read     | ClasificationType | Approve                 | TIN         | EDD        | Reason                             | ARN                                           |
      | Approved | Individual        | Tax Type to Re-Activate | N0000020354 | 06/04/2022 | Taxable Turnover exceeds threshold | Processing Completed - Reference Number - ARN |

  @UAT_TCS-01.29.2
  Scenario Outline:  UAT_TCS 01.29.2-UAT_TCS 01.29.3-UAT_TCS 01.29.6-To verify the Process of Reactivating a Tax type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Reactive Taxtype
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And CLick on Reactivate taxtype in grid
    And Select Reactivate EDD "<EDD>"
    And Select Reactivate Taxtype Reason "<Reason>"
    Then Click on Reactivate button
    And  Verify the ARN number "<ARN>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then Wait for text "Tax Type to Re-Activate" to load in frame "WebResource_RegistrationApplicationAngular"
    Then Approve taxtype deregistration from dropdown
    Then Click save CRM
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Read     | ClasificationType | TIN         | EDD        | Reason                             | ARN                                           |
      | Approved | Individual        | N0000020354 | 06/04/2029 | Taxable Turnover exceeds threshold | Processing Completed - Reference Number - ARN |

  @UAT_TCS-01.29.4
  Scenario Outline:  UAT_TCS 01.29.4-To verify the Process of checking Validation Errors
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Reactive Taxtype
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then Verify no data is found in table

    Examples:
      | ClasificationType | TIN         |
      | Individual        | N0000020354 |

  @UAT_TCS-01.29.5
  Scenario Outline:  UAT_TCS 01.29.5-To verify the Process of Supervisor rejecting the Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Reactive Taxtype
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And CLick on Reactivate taxtype in grid
    And Select Reactivate EDD "<EDD>"
    And Select Reactivate Taxtype Reason "<Reason>"
    Then Click on Reactivate button
    And  Verify the ARN number "<ARN>"
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
      | Read     | ClasificationType | TIN         | EDD        | Reason                             | ARN                                           | Reject                  | Notes                 |
      | Rejected | Individual        | N0000020354 | 06/04/2029 | Taxable Turnover exceeds threshold | Processing Completed - Reference Number - ARN | Tax Type to Re-Activate | Error in data capture |