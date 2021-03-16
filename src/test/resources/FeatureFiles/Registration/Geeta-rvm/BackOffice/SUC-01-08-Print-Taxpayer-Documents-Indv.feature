Feature: [SUC:01-08] Print Taxpayer Documents

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.1-To verify the process of printing Taxpayer TPIN certificate
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Taxpayer TIN Certificate"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Taxpayer TIN Certificate - ARN000046062018.PDF |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.2-To verify the process of printing Tax Type certificate
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "V0017205"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Tax Type Certificate"
    Then Report Reprint page is displayed with name "Registration" and TIN "V0017205"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Tax Type Certificate - ARN000184602019.PDF |

   @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.3-To verify the process of printing Taxpayer Amendment notification
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Taxpayer Registration Amendment Notification"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Taxpayer Registration Amendment Notification - ARN000046062018.PDF |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.4-To verify the process of printing Tax Type Amendment notification for Suspend Tax Type
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "C0000020109"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Tax Type Registration Amendment Notification"
    Then Report Reprint page is displayed with name "Registration" and TIN "C0000020109"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Tax Type Registration Amendment Notification |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.5- To verify the process of printing Tax Type Amendment notification for Reactivate Tax Type
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Tax Type Reactivation Application"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Tax Type Registration Amendment Notification |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.6- To verify the process of printing Tax Type Amendment notification for De-Register Tax Type
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Taxpayer Registration Amendment Notification"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Taxpayer Registration Amendment Notification |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.7- To verify the process of printing Tax Type Amendment notification for Re-Register Tax Type
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Taxpayer Registration Amendment Notification"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Taxpayer Registration Amendment Notification - ARN000046062018.PDF |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.1-To verify the process of printing Taxpayer Amendment notification for Transfer Taxpayer
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "P0014866"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Taxpayer TIN Certificate"
    Then Report Reprint page is displayed with name "Registration" and TIN "P0014866"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Taxpayer TIN Certificate - ARN000046062018.PDF |

  @[SUC:01-08]
  Scenario Outline: UAT_TCS 01.09.9-To verify the process of printing Rejected notifications
    Given navigate to Reporting>>Report Reprint
    When user enters module name "Registration" and TIN "C0017744"
    And clicks Find Reports search Button
    Then available reports table is displayed
    When clicks on report name "Tax Type Rejection Notification"
    Then Report Reprint page is displayed with name "Registration" and TIN "C0017744"
    When clicks download Reports Reprint button
    Then Report download should be generate <donwloadPath> and <filename>

    Examples:
      | donwloadPath               | filename                                     |
      | C:\Users\barnaby.kamau\Downloads | Tax Type Rejection Notification - ARN000183452019.PDF |