Feature: SUC:02-01 Register Tax Type-Indv

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in

#@SUC:02-01
  Scenario Outline: UAT_TCS 01.19.1-UAT_TCS 01.19.2-To Verify the Process of Registering a Tax Type
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    And clicks tax type registration submit button
    Then success message is displayed "Processing completed"
#Given navigate to Registration>>Register Tax Type
#When Select Taxpayer Classification Type as <Type>
#And enters TIN as <TIN>
#And clicks find entity search button
#Then <Tax type> taxtype is displayed

    Examples:
      | Type       | TIN      | Tax type             |
      | Individual | P0020797 | Withholding Tax(WHT) |

  @SUC:02-01 @UAT_TCS-01.20.2
  Scenario Outline:  Register TaxType Individual Taxpayer Approve Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve  | Read     |
      | Tax Type | Approved |

    #@SUC:02-01
  Scenario Outline: UAT_TCS 01.19.3-To Verify the Process of Registering a Tax Type which has multiple return type and all Exclusive
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    Given Click Register Tax Type button
    And Selects <Tax type2>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    And clicks tax type registration submit button
    Then success message is displayed "Processing completed"
#Given navigate to Registration>>Register Tax Type
#When Select Taxpayer Classification Type as <Type>
#And enters TIN as <TIN>
#And clicks find entity search button
#Then <Tax type> taxtype is displayed

    Examples:
      | Type       | TIN      | Tax type            | Tax type2    |
      | Individual | 20000129 | Personal Income Tax | Domestic VAT |

  Scenario Outline: Taxpayer Approve Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve  | Read     |
      | Tax Type | Approved |

    #@SUC:02-01
  Scenario Outline: UAT_TCS 01.19.4-To Verify the Process of Registering a Tax Type which has multiple Return Types and all inclusive
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    And clicks tax type registration submit button
    Then success message is displayed "Processing completed"
#Given navigate to Registration>>Register Tax Type
#When Select Taxpayer Classification Type as <Type>
#And enters TIN as <TIN>
#And clicks find entity search button
#Then <Tax type> taxtype is displayed

    Examples:
      | Type       | TIN      | Tax type |
      | Individual | 20000129 | PAYE     |

  Scenario Outline: Taxpayer Approve Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve  | Read     |
      | Tax Type | Approved |

  Scenario Outline: UAT_TCS 01.19.5-To Verify the Process of authorisation within Tax Office
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    And clicks tax type registration submit button
    Then success message is displayed "Processing completed"
#Given navigate to Registration>>Register Tax Type
#When Select Taxpayer Classification Type as <Type>
#And enters TIN as <TIN>
#And clicks find entity search button
#Then <Tax type> taxtype is displayed

    Examples:
      | Type       | TIN      | Tax type |
      | Individual | 20000129 | PAYE     |

  Scenario Outline: Taxpayer Approve Scenario
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then clicks Approve from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve  | Read     |
      | Tax Type | Approved |

  Scenario Outline: Find taxType
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed

    Examples:
      | Type       | TIN      |
      | Individual | 20000129 |


  #@SUC:02-01
  Scenario Outline: UAT_TCS 01.19.6-To Verify the Process of a Supervisor rejecting the application
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And enters EDR
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    And clicks tax type registration submit button
    Then success message is displayed "Processing completed"
#Given navigate to Registration>>Register Tax Type
#When Select Taxpayer Classification Type as <Type>
#And enters TIN as <TIN>
#And clicks find entity search button
#Then <Tax type> taxtype is displayed

    Examples:
      | Type       | TIN      | Tax type |
      | Individual | 20000129 | PAYE     |

  Scenario Outline: Reject Cash Till
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    Then switch to frame
    When enters reference number in search results
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then clicks Decline from the dropdown <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Approve  | Read     |
      | Tax Type | Rejected |

  @SUC:02-01
  Scenario Outline: UAT_TCS 01.19.7-To Verify the Process of checking Validation Error during Tax Type Registration
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then TaxTypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "EDR: Validation Error"
    And enters EDR
    And clicks ok on tax type details
    Then message is displayed "Record Added"
    Given Click Register Tax Type button
    And Selects <Tax type2>  from Tax Type drop down
    And enters date of mission
    And checks forced registration
    And enters EDR
    And clicks ok on tax type details
    Then message is displayed "Taxable Turnover: Validation Error"
    Given enters taxable turnover <taxable Amount>
    And clicks ok on tax type details
    Then message is displayed "Record Added"
#  And clicks tax type registration submit button
#  Then success message is displayed "Processing completed"

    Examples:
      | Type       | TIN      | Tax type            | Tax type2    | taxable Amount |
      | Individual | P0019361 | Personal Income Tax | Domestic VAT | 10000          |

  Scenario Outline: Find taxType
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed

    Examples:
      | Type       | TIN      |
      | Individual | P0019361 |

  Scenario Outline: UAT_TCS 01.19.8-To verify the process of displaying Registered Tax Types for TaxPayer from another Tax Office
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed

    Examples:
      | Type       | TIN      |
      | Individual | P0019361 |

    
    
   
   
    