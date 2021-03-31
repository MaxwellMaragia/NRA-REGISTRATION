Feature: [SUC:01-01] Submit Registration Application	Individual - Register Taxpayer

   #!!!!!!NAMES ARE APPENDED WITH RANDOM DIGITS ,SO NO NEED TO MODIFY
  #Change email download paths
  @SUC:01-01 @UAT_TCS-01.01.4 @NRA @NRA-INDV @combine
  Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully with mandatory fields
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max        | 0 |
      | Last Name         | SecondName | 1 |
      | CategoryValue     | Employee   | 2 |
      | Title Value       | MR         | 3 |
      | Gender            | M          | 4 |
      | MothersMaidenName | Wambui     | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
      | Assigned Office    | BOMTO                          | 5 |
    Then Select residence permit identification with number "321362"
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 1000j188             | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhbak1262            | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | account128100      | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersey                                                              | 29 8  |
      | ProvisionValue           | Bombali                                                                 | 30 9  |
      | ReogonValue              | North                                                                   | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | margiewambui11@gmail.com                                                | 35 14 |
    And enters attachment details "National ID"  with number "00000003" and path "C:\id_doc.png"
    And enters attachment details "Passport"  with number "00106" and path "C:\id_doc.png"
    Then Click On Individual Page Submit Button
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
    And Download the Attachment "C:\Users\barnaby.kamau\Downloads\id_doc.png"
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check <Approve>
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"
    And Clicks on Taxpayer name CRM
    And refresh page
    Then switch to frame0
    Then Taxpayer Tin is displayed
    Examples:
      | DOB      | DOI        | IED        | ESD        | Approve    | Read     |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | First Name | Approved |

  @[SUC:01-01] @UAT_TCS-01.01.3 @combine
  Scenario Outline: UAT_TCS 01.01.3	To verify the process of checking Validation error in entered data
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | maxw     | 0 |
      | Last Name         | Nick6    | 1 |
      | CategoryValue     | Employee | 2 |
      | Title Value       | MR       | 3 |
      | Gender            | M        | 4 |
      | MothersMaidenName | hjjhg1   | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 8767t                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | o8olih1              | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | oijlou1            | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersey                                                              | 29 8  |
      | ProvisionValue           | Bombali                                                                 | 30 9  |
      | ReogonValue              | North                                                                   | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | margiewambui11@gmail.com                                                | 35 14 |
    Then Click On Individual Page Save Button
    And Verify the Validation Error "<Validate>"

    Examples:
      | DOB      | DOI        | IED        | ESD        | Validate                                            |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Address should have at least one primary indicator. |

  @[SUC:01-01] @UAT_TCS-02.01.2 @combine
  Scenario Outline: UAT_TCS 02.01.20-UAT_TCS 02.01.4-UAT_TCS 02.01.5: To verify the Process of Rejecting Application - Invalid Identification Details
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max        | 0 |
      | Last Name         | SecondName | 1 |
      | CategoryValue     | Employee   | 2 |
      | Title Value       | MR         | 3 |
      | Gender            | M          | 4 |
      | MothersMaidenName | Wambui     | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
      | Assigned Office    | BOMTO                          | 5 |
    Then Select residence permit identification with number "321362"
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 1000j188             | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhbak1262            | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | account128100      | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersey                                                              | 29 8  |
      | ProvisionValue           | Bombali                                                                 | 30 9  |
      | ReogonValue              | North                                                                   | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | margiewambui11@gmail.com                                                | 35 14 |
    And enters attachment details "National ID"  with number "00000003" and path "C:\id_doc.png"
    And enters attachment details "Passport"  with number "00106" and path "C:\id_doc.png"
    Then Click On Individual Page Submit Button
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
    And Download the Attachment "C:\Users\barnaby.kamau\Downloads"
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check <Approve>
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    And Select Reject outcome dropdown value to Approve"<Approve>"
    Then Enter Outcome Notes "Invalid documentation"
    Then Enter Outcome Reason
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           | Approve    | Read     |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN | First Name | Rejected |


  @[SUC:01-01] @UAT_TCS-01.01.4 @NRA  @NRA-INDV @NRA-INDV-PROP @combine
  Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully (Sole Proprietor)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | TestName        | 0 |
      | Last Name         | Bana            | 1 |
      | CategoryValue     | Sole Proprietor | 2 |
      | Title Value       | MR              | 3 |
      | Gender            | M               | 4 |
      | MothersMaidenName | Maria           | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0000n                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Residence Permit   | 0 |
      | Identification num  | accoac             | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersey                                                              | 29 8  |
      | ProvisionValue           | Bombali                                                                 | 30 9  |
      | ReogonValue              | North                                                                   | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | v-barnaby.kamau@microsoft.com                                                   | 35 14 |
    And enters attachment details "Business Registration Certificate"  with number "00000007" and path "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    And Enter Sole Proprietor Additional Details
      | Trading Name        | Trader1              | 0 |
      | Invested Capital    | 2000                 | 1 |
      | Existing Capital    | 2000                 | 2 |
      | Existing Capital    | 4000                 | 3 |
      | Nature of Business  | kiosk                | 4 |
      | AccountYearEndDay   | 01                   | 5 |
      | AccountYearEndMonth | July                 | 6 |
      | Address Type        | Local Postal Address | 7 |
      | Region              | East                 | 8 |
      | District            | Kono                 | 9 |
    And enters attachment details "Passport"  with number "00000007" and path "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\Users\barnaby.kamau\Downloads"
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check <Approve>
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    Then switch to frame
    And Verify the String "<Read>"
    And Clicks on Taxpayer name CRM
    And refresh page
    Then switch to frame0
    Then Taxpayer Tin is displayed

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           | Approve    | Read     |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN | First Name | Approved |




  @UAT_TCS-01.02.2
  Scenario Outline: UAT_TCS 01.02.2-To verify the process of unsuccessful Registration for organization due to incomplete mandatory fields
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then Click on registration > register taxpayer > register organization
    Then Select category : organization "<Category>"
    Then Enter Organization name "<name>"
    Then Select Account end day "<endDay>"
    Then Select Account end month "<endMonth>"
    Then Enter source of capital "<sourceOfCapital>"
    Then Select place of incorporation "<Nationality>"
    Then Select business sector
    Then Select reason for tin application : organization "<reasonForApplication>"
#        And Click attachments tab : organization
#        Then Click add "OrganisationSummaryDetails:organisationAccordion:attachmentTableHandler:AddAttachment"
#        Then Switch to frame
#        Then Select document type "<documentType>"
#        Then Enter document number "<idNumber>"
#        Then Browse for attachment "<path>"
#        Then Click ok: xpath "//*[@id='AttachmentDetails:Ok']"
    Then Click address tab : organization
    Then Click add "OrganisationSummaryDetails:organisationAccordion:addressTableHandler:AddAddress"
    Then Switch to frame
    Then Select address type "<addressType>"
    Then Uncheck primary indicator checkbox
    Then Then enter town "<town>"
    Then Select region "<region>" and district "<district>"
    Then Click ok: xpath "//*[@id='AddressDetails:addOk']"
    Then Click save "OrganisationSummaryDetails:Save"
    Then Verify error message "Following necessary attachments should be supplied"
    Examples:
      | Category     | name     | Nationality | reasonForApplication | idNumber | documentType            | path            | endDay | endMonth | sourceOfCapital | addressType          | town     | region         | district |
      | Club Farmers | CODEI v1 | MALAWI      | Am an employer       | 32355247 | Letter Of Authorization | C:\\ronaldo.jpg | 04     | June     | sales           | Local Postal Address | Lilongwe | Central Region | Lilongwe |

    