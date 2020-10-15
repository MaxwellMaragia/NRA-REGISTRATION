Feature: SUC:02-01 Register Tax Type	Individual - Register Tax Type

  @SUC:02-01 @UAT_TCS-01.19.2
  Scenario Outline: UAT_TCS 01.19.2	To Verify the Process of Registering a Tax Type
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I enter valid data on the TaxType Individual page <taxtype>
      | TaxpayerClassificationT | Individual    | 0 |
      | TaxPayer_TIN            | P0020644      | 1 |
      | Turnover                | 300000        | 2 |
      | EDR                     | 16/09/2016    | 3 |
      | Wait for Record         | Record Added  | 4 |
      | Attachment              | C:\id_doc.png | 5 |
      | Doctype                 | National ID   | 6 |
      | AttachNumber            | 12345         | 7 |
    Then TaxType ARN number will generate
      | ARN number | Processing Completed - Reference Number - ARN | 0 |
    Then wait for webpage to load
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
      | taxtype              | Approve  | Read     |
      | Withholding Tax(WHT) | Tax Type | Approved |


 
    
    
   
   
    