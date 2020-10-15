Feature: [SUC:01-09] Transfer Taxpayer	Organisation- Transfer Taxpayer

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.1	To verify the process of Transferring a Organisation
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
      | username  | password | browser | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                        | ARN                                           |
      | tripsuser | Passw0rd | FireFox | Organisation      | V0028159 | Blantyre MTO | 06/04/2025     | Segmentation criteria applied | Processing Completed - Reference Number - ARN |

  Scenario Outline:  Trnsfer TaxPayer Individual Taxpayer Approve Scenario
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
      | Read     | Approve            |
      | Approved | Current Tax Office |


#  @SUC:01-09
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
      | tripsuser | Passw0rd | FireFox | Organisation      | P0019610 |

#  @SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.2 To verify the process of Organisation not found for Transfer
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
      | tripsuser | Passw0rd | Organisation      | P0016006 |

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.3 To verify the process of checking Validation error during Transfer Organisation
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
      | username  | password | ClasificationType | TIN      | DateOfTransfer | Reason                                     | ARN                                           |
      | tripsuser | Passw0rd | Organisation      | P0020466 | 06/04/2025     | The Organisation taxpayer location changed | Processing Completed - Reference Number - ARN |


  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.4 To verify the process of Abandoning Transfer of Organisation
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
      | username  | password | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                                     |
      | tripsuser | Passw0rd | Organisation      | P0020466 | Blantyre MTO | 06/04/2025     | The Organisation taxpayer location changed |

  #@SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.5 To verify the process of Rejecting Transfer of a Organisation
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
      | username  | password | ClasificationType | TIN      | NewOffice    | DateOfTransfer | Reason                                     | ARN                                           |
      | tripsuser | Passw0rd | Organisation      | P0018004 | Blantyre MTO | 06/04/2025     | The Organisation taxpayer location changed | Processing Completed - Reference Number - ARN |

  @SUC:01-09
  Scenario Outline:  Trnsfer TaxPayer Organisation Taxpayer reject Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Select Reject outcome dropdown value to Approve"<Reject>"
    Then Enter Outcome Notes <Notes>
    And Enter Outcome Reason for Taxpayer accounting
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Read     | Reject             | Notes                 |
      | Rejected | Current Tax Office | Invalid Documentation |

    #  @SUC:01-09
  Scenario Outline:  UAT_TCS 01.14.6 To verify the process of Transferring a De-registered Organisation
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
      | tripsuser | Passw0rd | Organisation      | P0016006 |

