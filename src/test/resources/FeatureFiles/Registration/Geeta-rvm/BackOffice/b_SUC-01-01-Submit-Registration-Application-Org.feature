Feature: [SUC:01-01] Submit Registration Application	Organisation - Register Taxpayer

#  @UAT_TCS-01.02.2
#  Scenario Outline: UAT_TCS 01.02.2 To verify the process of Unsuccessful Registration for Organisation due to incomplete mandatory fields
#    Given User navigates to the login page
#    When Enters the username "tripsuser" and password "Passw0rd"
#    Then User should be logged in
#    When I Fill the Organization Taxpayer Registration form
#    And I enter valid data on the pages of Organization
#      | CategoryValue          | Co-operative Society (Other) | 0     |
#      | Organization Name      | CODEI ENTREPRISE             | 1     |
#      | RGD Number             | kuii                         | 2     |
#      | DOE                    | 12092018                     | 3     |
#      | DOC                    | 12092020                     | 4     |
#      | Source of Capital      | Home Loan                    | 5     |
#      | Place Of Incorporation | ALBANIA                      | 6     |
#      | ReasonForTin Value     | Exporting goods              | 7     |
#      | Business Sector Value  | 0112 - Growing of rice       | 8     |
#      | Address Submodule      | Addresses                    | 9     |
#      | AddressValue           | Local Postal Address         | 10    |
#      | SName                  | United States                | 11    |
#      | City                   | United States                | 12    |
#      | ProvisionValue         | Falaba                      | 30 9  |
#      | ReogonValue            | North             | 31 10 |
#      | Contact Method         | Contact Methods              | 15    |
#      | Purpose Value          | Business                     | 16    |
#      | ContactTypeValue       | Email                        | 17    |
#      | ContactDetails         | v-bakam@microsoft.com        | 18    |
#      | EndYearMonth           | February                     | 19    |
#      | EndYeadDay             | 01                           | 20    |
#    And Enter Attachment Tab details
#      | Attachments                 | Attachments                       | 19 0 |
#      | Attachment Date             | 21082016                          | 20 1 |
#      | Attachment Pasport          | Business Registration Certificate | 21 2 |
#      | Reference number            | ug                                | 22 3 |
#      | File upload                 | C:\id_doc.png                     | 23 4 |
#      | Attachments                 | Doccument                         | 24 5 |
#      | Attachments                 | Certificate of Incorporation      | 25 6 |
#      | Attachments                 | Letter Of Authorization           | 26 7 |
#      | Attachment Reference number | 78v                               | 27 8 |
#      | Attachment Reference number | 98u                               | 28 9 |
#    And enters director "P0017167" and "startDate"
#    Then Click On Organization Page Submit Button
#    Then wait for webpage to load
#
#
#    Examples:
#      | username  | password |
#      | tripsuser | Passw0rd |

  @UAT_TCS-01.02.3
  Scenario Outline: UAT_TCS 01.02.3	To verify the process of Validation error in entered data
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Organization Taxpayer Registration form
    And I enter valid data on the pages of Organization without primary indicator
      | CategoryValue          | Co-operative Society (Other) | 0  |
      | Organization Name      | gfhngv                       | 1  |
      | RGD Number             | kjtyht                       | 2  |
      | DOE                    | 12092018                     | 3  |
      | DOC                    | 12092020                     | 4  |
      | Source of Capital      | Home Loan                    | 5  |
      | Place Of Incorporation | ALBANIA                      | 6  |
      | ReasonForTin Value     | Exporting goods              | 7  |
      | Business Sector Value  | 0112 - Growing of rice       | 8  |
      | Address Submodule      | Addresses                    | 9  |
      | AddressValue           | Local Postal Address         | 10 |
      | SName                  | United States                | 11 |
      | City                   | United States                | 12 |
      | ProvisionValue         | Falaba                       | 13 |
      | ReogonValue            | North                        | 14 |
      | Contact Method         | Contact Methods              | 15 |
      | Purpose Value          | Business                     | 16 |
      | ContactTypeValue       | Email                        | 17 |
      | ContactDetails         | v-bakam@microsoft.com        | 18 |
      | EndYearMonth           | February                     | 19 |
      | EndYeadDay             | 01                           | 20 |
    And Enter Attachment Tab details
      | Attachments                 | Attachments                       | 19 0 |
      | Attachment Date             | 21082016                          | 20 1 |
      | Attachment Pasport          | Business Registration Certificate | 21 2 |
      | Reference number            | ug                                | 22 3 |
      | File upload                 | C:\id_doc.png                     | 23 4 |
      | Attachments                 | Doccument                         | 24 5 |
      | Attachments                 | Certificate of Incorporation      | 25 6 |
      | Attachments                 | Letter Of Authorization           | 26 7 |
      | Attachment Reference number | 7t                                | 27 8 |
      | Attachment Reference number | 9u                                | 28 9 |
    And enters director "P0017167" and "startDate"
    Then Click On Organization Page Save Button
    And Verify the Validation Error for organization "<Validate>"
    Examples:
      | Validate                                           |
      | Address should have at least one primary indicator |

  @[SUC:01-01]-- @UAT_TCS-01.02.4
  Scenario Outline: UAT_TCS 02.02.1-UAT_TCS 01.02.4	 To verify the process of Registering an Organisation successfully with mandatory fields
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
      | CategoryValue          | Co-operative Society (Other) | 0  |
      | Organization Name      | CODEI FIVE                   | 1  |
      | RGD Number             | 80907060                     | 2  |
      | DOE                    | 12092018                     | 3  |
      | DOC                    | 12092020                     | 4  |
      | Source of Capital      | Home Loan                    | 5  |
      | Place Of Incorporation | ALBANIA                      | 6  |
      | ReasonForTin Value     | Exporting goods              | 7  |
      | Business Sector Value  | 0112 - Growing of rice       | 8  |
      | Address Submodule      | Addresses                    | 9  |
      | AddressValue           | Local Postal Address         | 10 |
      | SName                  | United States                | 11 |
      | City                   | United States                | 12 |
      | ProvisionValue         | Koinadugu                    | 13 |
      | ReogonValue            | North                        | 14 |
      | Contact Method         | Contact Methods              | 15 |
      | Purpose Value          | Business                     | 16 |
      | ContactTypeValue       | Email                        | 17 |
      | ContactDetails         | margiewambui11@gmail.com     | 18 |
      | EndYearMonth           | February                     | 19 |
      | EndYeadDay             | 01                           | 20 |
    And Enter Attachment Tab details
      | Attachments                 | Attachments                        | 19 0 |
      | Attachment Date             | 21082016                           | 20 1 |
      | Attachment Pasport          | Certificate of Incorporation       | 21 2 |
      | Reference number            | uh                                 | 22 3 |
      | File upload                 | C:\id_doc.png                      | 23 4 |
      | Attachments                 | Doccument                          | 24 5 |
      | Attachments                 | Certificate of Incorporation       | 25 6 |
      | Attachments                 | Approval Letter from Line Ministry | 26 7 |
      | Attachment Reference number | 78c                                | 27 8 |
      | Attachment Reference number | 98c                                | 28 9 |
    And enters director "P0017167" and "startDate"
    Then Click On Organization Page Submit Button
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number - ARN"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\Users\v-maxmar\Downloads"
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then wait for duplicate check
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"
    Examples:
      | Approve    | Read     |
      | First Name | Approved |


  @[SUC:01-01]
  Scenario Outline: UAT_TCS 02.02.1	To verify the process of Approving Organisation Registration
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\users\Maxwell Maragia\downloads"
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then wait for duplicate check
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"
    Examples:
      | Approve    | Read     |
      | First Name | Approved |
