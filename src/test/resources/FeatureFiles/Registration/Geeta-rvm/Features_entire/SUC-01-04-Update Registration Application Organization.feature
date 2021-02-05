Feature: SUC:01-04-Update Registration Application Organization

  @UAT_TCS-01.04.2
  Scenario: UAT_TCS 01.04.2-To verify the process of checking validation error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Click on registration > register taxpayer > register organization
    Then Select category : organization "<Category>"
    Then Enter Organization name "<name>"
    Then Select Account end day "<endDay>"
    Then Select Account end month "<endMonth>"
    Then Enter source of capital "<sourceOfCapital>"
    Then Select place of incorporation "<Nationality>"
    Then Select reason for tin application : organization "<reasonForApplication>"
    Then Click add "OrganisationSummaryDetails:organisationAccordion:businessDetailsHandler:AddBusinessSD"
    Then Switch to frame
    Then Select business sector
    Then Click primary indicator checkbox "BusinessSectorDetails:PrimaryIndicator"
    Then Click ok: xpath "//*[@id='BusinessSectorDetails:OK']"
    Then Click address tab : organization
    Then Click add "OrganisationSummaryDetails:organisationAccordion:addressTableHandler:AddAddress"
    Then Switch to frame
    Then Select address type "<addressType>"
    Then Then enter town "<town>"
    Then Select region "<region>" and district "<district>"
    Then Click ok: xpath "//*[@id='AddressDetails:addOk']"
    And Click directors tab
    Then Click add "OrganisationSummaryDetails:organisationAccordion:directorsTableHandler:AddDirectors"
    Then Switch to frame
    Then Click find "DirectorsDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Click search : id "SearchForm:j_idt21"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
    Then Click continue "SearchForm:j_id16"
    Then Switch to frame
    Then Enter director start date "<directorStartDate>"
    Then Click ok: xpath "//*[@id='DirectorsDetails:rdOk']"
    Then Click save "OrganisationSummaryDetails:Save"


  Scenario: UAT_TCS 01.04.2-To verify the process of checking validation error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    When I Fill the Organization Taxpayer Registration form
    And I enter valid data on the pages of Organization
      | CategoryValue          | Co-operative Society (Other) | 0  |
      | Organization Name      | A&C                          | 1  |
      | RGD Number             | kuji                         | 2  |
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
      | ContactDetails         | v-bakam@microsoft.com        | 18 |
      | EndYearMonth           | February                     | 19 |
      | EndYeadDay             | 01                           | 20 |

    And enters director "P0017167" and "startDate"
    Then Click On Organization Page Submit Button
    Then Verify error message "Following necessary attachments should be supplied"
