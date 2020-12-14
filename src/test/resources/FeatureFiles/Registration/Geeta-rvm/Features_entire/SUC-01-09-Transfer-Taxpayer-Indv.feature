Feature: [SUC:01-09] Transfer Taxpayer	Individual - Transfer Taxpayer

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.1	To verify the process of Transferring a Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And Select New Office "<NewOffice>"
    And Select Date of transfer "<DateOfTransfer>"
    And Select Reason "<Reason>"
    Then Click on tarnsfer
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load


    Examples:

      | username  | password | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                                   | ARN                                           |
      | tripsuser | Passw0rd | Individual        | N0000024813 | Bo Tax Office (STO) | 06/04/2025     | The individual taxpayer location changed | Processing Completed - Reference Number - ARN |

  #@SUC:01-09
  Scenario Outline:  Trnsfer TaxPayer Individual Taxpayer Approve Scenario
    Given Open CRM URL Module
    And Close Popup Window
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | Read     | Approve            |
      | Approved | Current Tax Office |

  #@SUC:01-09
  Scenario Outline: UAT_TCS 01.11.2(UAT_TCS 01.17.1) To verify the process of Successful Registration application search,
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
      | username  | password | browser | ClasificationType | TIN      |
      | tripsuser | Passw0rd | FireFox | Individual        |  P0017167|

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.2 To verify the process of Taxpayer not found for Transfer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then System displays message Records Not Found

    Examples:
      | username  | password | ClasificationType | TIN      |
      | tripsuser | Passw0rd | Individual        | N0000024813 |

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.3 To verify the process of checking Validation error during Transfer Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And Select Date of transfer "<DateOfTransfer>"
    And Select Reason "<Reason>"
    Then Click on tarnsfer
    Then message is displayed "New Office: Validation Error: Value is required."



    Examples:
      | username  | password | ClasificationType | TIN      | DateOfTransfer | Reason                                   |
      | tripsuser | Passw0rd | Individual        | N0000019925 | 06/04/2025     | The individual taxpayer location changed |


  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.4 To verify the process of Abandoning Transfer of Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And Select New Office "<NewOffice>"
    And Select Date of transfer "<DateOfTransfer>"
    And Select Reason "<Reason>"
    And Clicks on transfer Taxpayer Cancel button
    Then Find Entity page should be displayed

    Examples:
      | username  | password | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                                   |
      | tripsuser | Passw0rd | Individual        | N0000019925 | Bo Tax Office (STO) | 06/04/2025     | The individual taxpayer location changed |

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.5 To verify the process of Rejecting Transfer of a Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    And Select New Office "<NewOffice>"
    And Select Date of transfer "<DateOfTransfer>"
    And Select Reason "<Reason>"
    Then Click on tarnsfer
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load


    Examples:
      | username  | password | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                                   | ARN                                           |
      | tripsuser | Passw0rd | Individual        | N0000019925 | Bo Tax Office (STO) | 06/04/2025     | The individual taxpayer location changed | Processing Completed - Reference Number - ARN |

  #@SUC:01-09
  Scenario Outline:  Trnsfer TaxPayer Individual Taxpayer reject Scenario
    Given Open CRM URL Module
    And Close Popup Window
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
      | Read     | Reject             | Notes                 |
      | Rejected | Current Tax Office | Invalid Documentation |

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.13.6 To verify the process of Transferring a De-registered Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    And  Click on regisration link
    And  Goto Manage taxpayer
    And Goto Transfer Taxpayer
    And Select Taxpayer Classification Type "<ClasificationType>"
    And Enter TIN number "<TIN>"
    And Click on search
    Then System displays message Records Not Found

    Examples:
      | username  | password | ClasificationType | TIN      |
      | tripsuser | Passw0rd | Individual        | P0016006 |

      
    
    
    
     