Feature: SUC:02-01 Register Tax Type-Indv

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"

  @SUC:02-01 @UAT_TCS-01.20.2 @Taxtype
  Scenario Outline: UAT_TCS 01.19.1-UAT_TCS 01.19.2-To Verify the Process of Registering a Tax Type and approve CRM
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
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
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
      | Type       | TIN         | Tax type        | Approve  | Read     |
      | Individual | N0000036455 | Capital Gains Tax | Tax Type | Approved |

  @UAT_TCS-01.19.3
  Scenario Outline: UAT_TCS 01.19.3-To Verify the Process of Registering a Tax Type which has multiple return type and all Exclusive amd approve
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
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
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
      | Type       | TIN         | Tax type            | Tax type2    | Approve  | Read     |
      | Individual | N0000036250 | Personal Income Tax | Domestic VAT | Tax Type | Approved |


  @UAT_TCS-01.19.4
  Scenario Outline: UAT_TCS 01.19.4-To Verify the Process of Registering a Tax Type which has multiple Return Types and all inclusive and approve
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
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
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
      | Type       | TIN         | Tax type        | Approve  | Read     |
      | Individual | N0000036099 | Pay As You Earn | Tax Type | Approved |

  @UAT_TCS-01.19.5
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
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
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
      | Type       | TIN         | Tax type        | Approve  | Read     |
      | Individual | N0000036099 | Pay As You Earn | Tax Type | Approved |

  @UAT_TCS-01.19.5.5
  Scenario Outline: Find taxType
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed2

    Examples:
      | Type       | TIN         |
      | Individual | N0000036099 |


  @UAT_TCS-01.19.6
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
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
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
      | Type       | TIN         | Tax type          | Read     | Reject   | Notes                 |
      | Individual | N0000036099 | Capital Gains Tax | Rejected | Tax Type | Error in data capture |


  @UAT_TCS-01.19.7
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
#    Then message is displayed "Taxable Turnover: Validation Error"
#    Given enters taxable turnover <taxable Amount>
#    And clicks ok on tax type details
    #Taxable turnover is not a mandatory field for NRA taxtypes
    Then message is displayed "Record Added"

    Examples:
      | Type       | TIN         | Tax type          | Tax type2         | taxable Amount |
      | Individual | N0000036099 | Capital Gains Tax | Rental Income Tax | 10000          |

  @UAT_TCS-01.19.8
  Scenario Outline: Find taxType
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed2

    Examples:
      | Type       | TIN         |
      | Individual | N0000036099 |

  @UAT_TCS-01.19.9
  Scenario Outline: UAT_TCS 01.19.8-To verify the process of displaying Registered Tax Types for TaxPayer from another Tax Office
    Given navigate to Registration>>Find Taxpayer
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then individual details displayed
    When user clicks on taxtypes
    Then TaxTypes are displayed2

    Examples:
      | Type       | TIN         |
      | Individual | N0000036099 |





