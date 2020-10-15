Feature: SUC:02-01 Register Tax Type	Organisation-Register Tax Type

  @SUC:02-01 @UAT_TCS-01.20.2
  Scenario Outline: UAT_TCS 01.20.2	To verify the process of Registering Tax Type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the TaxType Individual page <taxtype>
      | TaxpayerClassificationT | Organisation            | 0 |
      | TaxPayer_TIN            | P0020644                | 1 |
      | Turnover                | 20000                   | 2 |
      | EDR                     | 16/09/2016              | 3 |
      | Wait for Record         | Record Added            | 4 |
      | Attachment              | C:\id_doc.png          | 5 |
      | Doctype                 | Letter Of Authorization | 6 |
      | AttachNumber            | 12345                   | 7 |
    Then TaxType ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then wait for webpage to load
    Examples:
      | taxtype         |
      | Domestic Excise |
#      | Domestic VAT |

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
    
    
   
   
    