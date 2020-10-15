Feature: [SUC:02-06]	[SUC:02-06] Re-activate Tax Type	Organisation- Reactivate Tax Type

  Scenario Outline:  UAT_TCS 01.30.1	To verify the Process of Reactivating a Tax type
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
    Then wait for webpage to load

    Examples:
      | URL                | username  | password | browser | ClasificationType | TIN      | EDD        | Reason                             | ARN                                           |
      | NRA_BackOffice_URL | tripsuser | Passw0rd | chrome  | Organisation      | V0020228 | 06/04/2029 | Taxable Turnover exceeds threshold | Processing Completed - Reference Number - ARN |

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

  Scenario Outline: UAT_TCS 01.18.1	To verify the Process of Finding a Taxpayer by TPIN
    Given User navigates to the login page
    When Enter the username "<username>" and password "<password>"
    Then click on login
    And  Click on regisration link
    And  Goto Manage taxpayer
    And  Goto Find taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then wait for webpage to load

    Examples:
      | username  | password | browser | ClasificationType | TIN      |
      | tripsuser | Passw0rd | FireFox | Organisation      | V0020228 |
    