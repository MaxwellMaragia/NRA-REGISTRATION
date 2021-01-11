Feature: [SUC:01-01] Submit Registration Application	Individual - Register Taxpayer

  @SUC:01-01 @UAT_TCS-01.01.1
  Scenario Outline: UAT_TCS 01.01.1	To verify all the fields and buttons in the Individual Summary Details screen
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max              | 0 |
      | Last Name         | CreditAdjustment | 1 |
      | CategoryValue     | Employee         | 2 |
      | Title Value       | MR               | 3 |
      | Gender            | M                | 4 |
      | MothersMaidenName | Wambui           | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 43534472             | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb1                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | uioj7233             | 1 |
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
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | margiewambui11@gmail.com                                                | 35 14 |
#    And enters attachment details "National ID"  with number "5000670120" and path "C:\id_doc.png"
    And enters attachment details "Passport"  with number "9870000004503" and path "C:\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
#    Then wait for webpage to load
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    #Then switch to frame
    When enters reference number in search results
    Then switch to frame
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    #  Change names and atttachment numbers after each run
    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           | Approve    | Read     |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN | First Name | Approved |

 #@[SUC:01-01]
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
    And Enter Employee details for Primary indicattor "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | barnaby.kamau@technobraingroup.com                                      | 35 14 |
    And enters attachment details "National ID"  with number "00000001" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    And enters attachment details "Passport"  with number "00000002" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    Then Click On Individual Page Save Button
    And Verify the Validation Error "<Validate>"
    Then wait for webpage to load

    Examples:
      | DOB      | DOI        | IED        | ESD        | Validate                                            |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Address should have at least one primary indicator. |

  @[SUC:01-01] @UAT_TCS-01.01.4
  Scenario Outline: UAT_TCS 01.01.4: To verify the process of Registering an individual successfully with mandatory fields
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Yes      | 0 |
      | Last Name         | Bana     | 1 |
      | CategoryValue     | Employee | 2 |
      | Title Value       | MR       | 3 |
      | Gender            | M        | 4 |
      | MothersMaidenName | hjuytgh  | 5 |
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
      | Identification Type | Driving Licence    | 0 |
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
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | barnaby.kamau@technobraingroup.com                                      | 35 14 |

#  And enters attachment details "National ID"  with number "00000003" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    And enters attachment details "Passport"  with number "00000007" and path "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load

#  Change names and atttachment numbers after each run

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |


  Scenario Outline: Individual - [SUC:01-02] Approve Taxpayer [SUC:01-02] Approve Taxpayer (UAT_TCS 02.01.1)To verify the process of Approving Taxpayer Registration
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
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Approval outcome dropdown value to Approve <Approve>
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Approve    | Read     |
      | First Name | Approved |

#  @[SUC:01-01]
  Scenario Outline: UAT_TCS 02.01.2: To verify the Process of Rejecting Application - Invalid Identification Details
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max        | 0 |
      | Last Name         | Dissaprove | 1 |
      | CategoryValue     | Employee   | 2 |
      | Title Value       | MR         | 3 |
      | Gender            | M          | 4 |
      | MothersMaidenName | hjuytgh    | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0000o                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | accpac             | 1 |
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
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | maxwell.maragia@technobraingroup.com                                    | 35 14 |
#  And enters attachment details "National ID"  with number "00000003" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    And enters attachment details "Passport"  with number "00000008" and path "C:\Users\v-bakam\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load

#  Change names and atttachment numbers after each run

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |

#  @[SUC:01-01]
  Scenario Outline: To verify the Process of Rejecting Application - Invalid Identification Details
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    When enters reference number in search results
    Then switch to frame
    And Pick registration case
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Invalid Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Reject outcome dropdown value to Approve"<Approve>"
    Then Enter Outcome Notes "Invalid documentation"
    Then Enter Outcome Reason
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Approve    | Read     |
      | First Name | Rejected |

  Scenario Outline: UAT_TCS 02.01.3: To verify the process of checking validation error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max        | 0 |
      | Last Name         | Dissaprove | 1 |
      | CategoryValue     | Employee   | 2 |
      | Title Value       | MR         | 3 |
      | Gender            | M          | 4 |
      | MothersMaidenName | hjuytgh    | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0000o                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | accpac             | 1 |
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
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | maxwell.maragia@technobraingroup.com                                    | 35 14 |
#  And enters attachment details "National ID"  with number "00000003" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    And enters attachment details "Passport"  with number "00000008" and path "C:\Users\v-bakam\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load

#  Change names and atttachment numbers after each run

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |

#  @[SUC:01-01]
  Scenario Outline: To verify the process of checking validation error
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    When enters reference number in search results
    Then switch to frame
    And Pick registration case
#    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Reject outcome dropdown value to Approve"<Approve>"
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Approve    | Read        |
      | First Name | In progress |

  Scenario Outline: UAT_TCS 02.01.4: To verify the process of Registration Rejected
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max        | 0 |
      | Last Name         | Dissaprove | 1 |
      | CategoryValue     | Employee   | 2 |
      | Title Value       | MR         | 3 |
      | Gender            | M          | 4 |
      | MothersMaidenName | hjuytgh    | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 0000o                | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhb                  | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |
    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | accpac             | 1 |
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
      | Mname                    | New jersy                                                               | 29 8  |
      | ProvisionValue           | Karonga                                                                 | 30 9  |
      | ReogonValue              | Northern Region                                                         | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | maxwell.maragia@technobraingroup.com                                    | 35 14 |
#  And enters attachment details "National ID"  with number "00000003" and path "C:\Users\v-bakam\Downloads\id_doc.png"
    And enters attachment details "Passport"  with number "00000008" and path "C:\Users\v-bakam\Desktop\id_doc.png"
    Then Click On Individual Page Submit Button
    And  Verify the ARN number "<ARN>"
    Then wait for webpage to load

#  Change names and atttachment numbers after each run

    Examples:
      | DOB      | DOI        | IED        | ESD        | ARN                                           |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 | Processing Completed - Reference Number - ARN |

#  @[SUC:01-01]
  Scenario Outline: To verify the process of Registration Rejected
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    When enters reference number in search results
    Then switch to frame
    And Pick registration case
#    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Reject outcome dropdown value to Approve"<Approve>"
    Then Enter Outcome Notes "Invalid documentation"
    Then Enter Outcome Reason
    Then Click on Save button
    And Verify the String "<Read>"

    Examples:
      | Approve    | Read        |
      | First Name | In progress |

#  @[SUC:01-01]
  Scenario Outline: UAT_TCS 02.01.5-To verify the process of Return for Revision [Application made in trips+]
    Given Open CRM URL Module
    And Close Popup Window
    And Click start search
    When enters reference number in search results
    Then switch to frame
    And Pick registration case
#    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment
    Then switch to frame
    Then Select Identification Outcome dropdown value for Individual Taxpayer Approval
    And Click on NextStage button
    Then switch to frame
    Then wait for duplicate check
    And Click on NextStage button
    Then switch to frame
    And Select Reject outcome dropdown value to Approve"<Approve>"
    Then Enter Outcome Notes "Invalid documentation"
    Then Enter Outcome Reason
    Then Click on Save button
    And Verify the String "<Read>"




    Examples:
      | Approve    | Read        |
      | First Name | In progress |

#  @UAT_TCS-01.02.1
#  Scenario Outline: UAT_TCS 01.02.1-To verify the process of unsuccessful Registration for organization due to incomplete mandatory fields
#    Given Browser is launched and trips URL loaded in address bar
#    And User logged in as revenue officer
#      | tripsuser | Passw0rd |
#    Then Click on registration > register taxpayer > register organization
#    Then Select category : organization "<Category>"
#    Then Enter Organization name "<name>"
#    Then Select Account end day "<endDay>"
#    Then Select Account end month "<endMonth>"
#    Then Enter source of capital "<sourceOfCapital>"
#    Then Select place of incorporation "<Nationality>"
#    Then Select reason for tin application : organization "<reasonForApplication>"
#    And Click attachments tab : organization
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:attachmentTableHandler:AddAttachment"
#    Then Switch to frame
#    Then Select document type "<documentType>"
#    Then Enter document number "<idNumber>"
#    Then Browse for attachment "<path>"
#    Then Click ok: xpath "//*[@id='AttachmentDetails:Ok']"
#    Then Click save "OrganisationSummaryDetails:Save"
#    Then Verify error message "Address should have at least one primary indicator"
#    Examples:
#      | Category     | name     | Nationality | reasonForApplication | idNumber | documentType            | path            | endDay | endMonth | sourceOfCapital |
#      | Club Farmers | CODEI v1 | MALAWI      | Am an employer       | 32355247 | Letter Of Authorization | C:\\ronaldo.jpg | 04     | June     | sales           |
#
#  @UAT_TCS-01.02.2
#  Scenario Outline: UAT_TCS 01.02.2-To verify the process of unsuccessful Registration for organization due to incomplete mandatory fields
#    Given Browser is launched and trips URL loaded in address bar
#    And User logged in as revenue officer
#      | tripsuser | Passw0rd |
#    Then Click on registration > register taxpayer > register organization
#    Then Select category : organization "<Category>"
#    Then Enter Organization name "<name>"
#    Then Select Account end day "<endDay>"
#    Then Select Account end month "<endMonth>"
#    Then Enter source of capital "<sourceOfCapital>"
#    Then Select place of incorporation "<Nationality>"
#    Then Select business sector
#    Then Select reason for tin application : organization "<reasonForApplication>"
#    #    And Click attachments tab : organization
#    #    Then Click add "OrganisationSummaryDetails:organisationAccordion:attachmentTableHandler:AddAttachment"
#    #    Then Switch to frame
#    #    Then Select document type "<documentType>"
#    #    Then Enter document number "<idNumber>"
#    #    Then Browse for attachment "<path>"
#    #    Then Click ok: xpath "//*[@id='AttachmentDetails:Ok']"
#    Then Click address tab : organization
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:addressTableHandler:AddAddress"
#    Then Switch to frame
#    Then Select address type "<addressType>"
#    Then Uncheck primary indicator checkbox
#    Then Then enter town "<town>"
#    Then Select region "<region>" and district "<district>"
#    Then Click ok: xpath "//*[@id='AddressDetails:addOk']"
#    Then Click save "OrganisationSummaryDetails:Save"
#    Then Verify error message "Following necessary attachments should be supplied"
#    Examples:
#      | Category     | name     | Nationality | reasonForApplication | idNumber | documentType            | path            | endDay | endMonth | sourceOfCapital | addressType          | town     | region         | district |
#      | Club Farmers | CODEI v1 | MALAWI      | Am an employer       | 32355247 | Letter Of Authorization | C:\\ronaldo.jpg | 04     | June     | sales           | Local Postal Address | Lilongwe | Central Region | Lilongwe |
#
#
#  @UAT_TCS-01.02.3
#  Scenario Outline: UAT_TCS 01.02.3-To verify the process of unsuccessful Registration for organization due to incomplete mandatory fields
#    Given Browser is launched and trips URL loaded in address bar
#    And User logged in as revenue officer
#      | tripsuser | Passw0rd |
#    Then Click on registration > register taxpayer > register organization
#    Then Select category : organization "<Category>"
#    Then Enter Organization name "<name>"
#    Then Select Account end day "<endDay>"
#    Then Select Account end month "<endMonth>"
#    Then Enter source of capital "<sourceOfCapital>"
#    Then Select place of incorporation "<Nationality>"
#    Then Select reason for tin application : organization "<reasonForApplication>"
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:businessDetailsHandler:AddBusinessSD"
#    Then Switch to frame
#    Then Select business sector
#    Then Click primary indicator checkbox "BusinessSectorDetails:PrimaryIndicator"
#    And Click attachments tab : organization
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:attachmentTableHandler:AddAttachment"
#    Then Switch to frame
#    Then Select document type "<documentType>"
#    Then Enter document number "<idNumber>"
#    Then Browse for attachment "<path>"
#    Then Click ok: xpath "//*[@id='AttachmentDetails:Ok']"
#    Then Click address tab : organization
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:addressTableHandler:AddAddress"
#    Then Switch to frame
#    Then Select address type "<addressType>"
#    Then Uncheck primary indicator checkbox
#    Then Then enter town "<town>"
#    Then Select region "<region>" and district "<district>"
#    Then Click ok: xpath "//*[@id='AddressDetails:addOk']"
#    And Click directors tab
#    Then Click add "OrganisationSummaryDetails:organisationAccordion:directorsTableHandler:AddDirectors"
#    Then Switch to frame
#    Then Click find "DirectorsDetails:FindTin"
#    Then Switch to default
#    Then Switch to frame 2
#    Then Click search : id "SearchForm:j_idt21"
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
#    Then Click continue "SearchForm:j_id16"
#    Then Switch to frame
#    Then Enter director start date "<directorStartDate>"
#    Then Click save "OrganisationSummaryDetails:Save"
#    Then Verify error message "Address should have at least one primary indicator"
#    Examples:
#      | Category     | name     | Nationality | reasonForApplication | idNumber | documentType            | path            | endDay | endMonth | sourceOfCapital | addressType          | town     | region         | district | directorStartDate |
#      | Club Farmers | CODEI v1 | MALAWI      | Am an employer       | 32355247 | Letter Of Authorization | C:\\ronaldo.jpg | 04     | June     | sales           | Local Postal Address | Lilongwe | Central Region | Lilongwe | 11/07/2019        |
#
#
#
    