Feature: [SUC:02-06] Re-activate Tax Type	Individual-Reactivate Tax TypeIndividual

  Scenario Outline:  UAT_TCS 01.29.1	To verify the Process of Reactivating a Tax type
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
      | username  | password | browser | ClasificationType | TIN      | EDD        | Reason                             | ARN                                           |
      | tripsuser | Passw0rd | chrome  | Individual        | Q0014070 | 06/04/2029 | Taxable Turnover exceeds threshold | Processing Completed - Reference Number - ARN |