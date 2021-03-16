Feature: SUC:02-01 Register Tax Type-Org

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in

  @UAT_TCS-01.19.2-org @Taxtype-
  Scenario Outline: UAT_TCS 01.20.1-UAT_TCS 01.19.2-To Verify the Process of Registering a Tax Type
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
      | Type         | TIN        | Tax type                      | Approve  | Read     |
      | Organisation | 1000008207 | Withholding Tax(5.5% & 10.5%) | Tax Type | Approved |


  @UAT_TCS-01.20.3-org
  Scenario Outline: UAT_TCS 01.20.3-To Verify the Process of Registering a Tax Type which has multiple return type and all Exclusive
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
      | Type         | TIN         | Tax type          | Tax type2          | Approve  | Read     |
      | Organisation | N0000036099 | Capital Gains Tax | Company Income Tax | Tax Type | Approved |

  @UAT_TCS-01.20.4-org
  Scenario Outline: UAT_TCS 01.20.4-To Verify the Process of Registering a Tax Type which has multiple Return Types and all inclusive
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
      | Type         | TIN         | Tax type                      | Approve  | Read     |
      | Organisation | N0000036099 | Withholding Tax(5.5% & 10.5%) | Tax Type | Approved |

  @UAT_TCS-01.20.5-org
  Scenario Outline: UAT_TCS 01.20.5-To Verify the Process of authorisation within Tax Office
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
      | Type         | TIN         | Tax type           | Approve  | Read     |
      | Organisation | N0000036099 | Foreign Travel Tax | Tax Type | Approved |

  @UAT_TCS-01.19.6-org
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
      | Type       | TIN         | Tax type               | Read     | Reject   | Notes                 |
      | Individual | N0000036099 | Goods and Services Tax | Rejected | Tax Type | Error in data capture |

  @UAT_TCS-01.20.7-org
  Scenario Outline: UAT_TCS 01.20.7-To Verify the Process of checking Validation Error during Tax Type Registration
    Given navigate to Registration>>Register Tax Type
    When Select Taxpayer Classification Type as <Type>
    And enters TIN as <TIN>
    And clicks find entity search button
    Then taxtypes are displayed
    Given Click Register Tax Type button
    And Selects <Tax type>  from Tax Type drop down
    And enters date of mission
    And checks forced registration
    And clicks ok on tax type details
    Then message is displayed "EDR: Validation Error"
    And enters EDR
    And clicks ok on tax type details
    Then message is displayed "Record Added"

    Examples:
      | Type         | TIN      | Tax type     |
      | Organisation | V0024037 | Domestic VAT |

  @UAT_TCS-01.20.8-org
  Scenario Outline: UAT_TCS 01.20.8-To verify the process of displaying Registered Tax Types for TaxPayer from another Tax Office
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
