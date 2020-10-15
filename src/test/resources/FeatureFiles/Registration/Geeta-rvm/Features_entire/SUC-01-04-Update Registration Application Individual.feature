Feature: SUC:01-04_Update Registration Application

  @UAT_TCS-01.03.2
  Scenario Outline: UAT_TCS-01.03.2-To verify the process of checking validation error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Click on registration > register taxpayer > register individual
    Then Select category as "<Category>"
    Then Select title as "<title>"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select gender "<Gender>"
    Then Select marital status "<MaritalStatus>"
    Then Enter date of birth "<dob>"
    Then Select nationality "<Nationality>"
    Then Select country of residence "<Nationality>"
    Then Enter place of birth "<placeOfBirth>"
    Then Select reason for tin application "<reasonForApplication>"
    And Click occupation - business interest tab
    Then Select occupation status "<OccupationStatus>"
    Then Select main category "<mainCategory>"
    Then Select Precise category "<preciseCategory>"
    And Click on identification tab
    Then Click add "RegisterIndividual:individualAccordion:identificationTableHandler:AddIdentification"
    Then Switch to frame
    Then Select identification "<identification>"
    Then Enter identification number "<idNumber>"
    Then Enter date of issue "<dateOfIssue>"
    Then Enter E-permit number "<eNumber>" and E-permit type
    Then Select country of issue "Kenya"
    Then Enter expiry date "<expiryDate>"
    Then Click ok: xpath "//*[@id='Identification:Ok']"
    Then Click employment details tab
    Then Click add "RegisterIndividual:individualAccordion:employmentTableHandler:AddEmployment"
    Then Switch to frame
    Then Enter employers name "<employersName>" and employment start date "<startDate>"
    Then Click ok: xpath "//*[@id='EmploymentDetails:Ok']"
    Then Click address tab
    Then Click add "RegisterIndividual:individualAccordion:addressTableHandler:AddAddress"
    Then Switch to frame
    Then Select address type "<addressType>"
    Then Then enter town "<town>"
    Then Select region "<region>" and district "<district>"
    Then Click ok: xpath "//*[@id='AddressDetails:addOk']"
    Then Click Contact methods tab
    Then Click add "RegisterIndividual:individualAccordion:contactDetailsHandler:AddContacts"
    Then Switch to frame
    Then Select purpose
    Then Enter contact details "<email>"
    Then Click ok: xpath "//*[@id='ContactDetails:Ok']"
    Then Click save "RegisterIndividual:Save"
    Then Verify error message " cannot be empty."
    Examples:
      | Category | title | firstName | lastName | Gender | MaritalStatus | dob        | Nationality | placeOfBirth | OccupationStatus | mainCategory  | preciseCategory              | reasonForApplication | identification | idNumber | dateOfIssue | expiryDate | documentType | path            | addressType          | town     | region         | district | email                                | employersName | startDate  | eNumber |
      | Employee | MR    | Maxwell   | Maragia  | M      | Single        | 25/09/1996 | Malawi      | Lilongwe     | Employed         | SALES WORKERS | Sales Supervisors and Buyers | Am an employee       | Passport       | 32355247 | 31/07/2014  | 31/07/2021 | National ID  | C:\\ronaldo.jpg | Local Postal Address | Lilongwe | Central Region | Lilongwe | v-maxwell.maragia@technobrainltd.com | TBL           | 24/05/2020 | 1234    |
