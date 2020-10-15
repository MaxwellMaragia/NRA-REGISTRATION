Feature: SUC:01-04-Update Registration Application Organization

  @UAT_TCS-01.04.2
  Scenario Outline: UAT_TCS 01.04.2-To verify the process of checking validation error
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
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
    Then Verify error message "Following necessary attachments should be supplied"
    Examples:
      | Category     | name     | Nationality | reasonForApplication |  endDay | endMonth | sourceOfCapital | addressType          | town     | region         | district | directorStartDate |
      | Club Farmers | CODEI v1 | MALAWI      | Am an employer       |  04     | June     | sales           | Local Postal Address | Lilongwe | Central Region | Lilongwe | 11/07/2019        |
