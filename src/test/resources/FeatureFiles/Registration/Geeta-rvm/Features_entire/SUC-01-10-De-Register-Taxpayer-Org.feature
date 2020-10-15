Feature: [SUC:01-10] De-Register Taxpayer	Organisation - Deregister Taxpayer

  Scenario Outline: UAT_TCS 01.16.1	To verify the process of Deregistering Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And Click on regisration link
    And Goto Manage taxpayer
    And Goto DeRegister
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And CLick on taxtype in grid
    And Select EDD "<EDD>"
    And Select de register Reason "<Reason>"
    Then Click on DeRegister button
    And Verify the ARN number "<ARN>"
    Then wait for webpage to load
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then clicks Approve from the dropdown "Tax Type To De-Register"
    Then Click on Save button
    And Verify the String "Approved"
    Examples:
      | ClasificationType | TIN      | EDD        | Reason                  | ARN                                           |
      | Organisation      | V0028462 | 16/04/2029 | No longer liable to tax | Processing Completed - Reference Number - ARN |


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
      | ClasificationType | TIN      |
      | Organisation      | V0026884 |
    



