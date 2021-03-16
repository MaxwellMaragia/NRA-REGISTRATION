Feature: [SUC:01-07] Amend Taxpayer	Individual - Amend Taxpayer

  @SUC:01-07- @UAT_TCS-01.03.1 @UAT_TCS-01.07.1
  Scenario Outline: UAT_TCS 01.07.1	To verify the process of Amend Taxpayer Data
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "1000007200"
    Then Click search : id "SearchForm:j_idt40"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then approve transaction
    Then Click save CRM
    Then Status should be "<Status>"
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000036110"
    Then Click search : id "SearchForm:j_idt40"
    Then Check if changes reflect "<firstName>"
    Examples:
      | firstName | lastName     | amendmentReason | Status   | SuccessMessage                          |
      | Max       | AmendTestOne | Change of Name  | Approved | Processing Completed - Reference Number |

  @SUC:01-07 @UAT_TCS-01.07.2
  Scenario Outline: UAT_TCS 01.07.2-To verify the process of amendment Request received from RGD (sole proprietors)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033537"
    Then Click search : id "SearchForm:j_idt40"
    Then Select reason for amendment "<amendmentReason>"
    Then Click Sole proprietor additional details tab
    Then Click table column "//*[@id='RegisterIndividual:individualAccordion:tradingNameTableHandler_data']/tr/td[2]"
    Then Click edit "RegisterIndividual:individualAccordion:tradingNameTableHandler:EditTradingNameDetails"
    Then Switch to frame
    Then Change RGD number to "32344560"
    Then Click ok: xpath "//*[@id='TradingNameDetails:OK']"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Wait for text "Amendment Change Type" to load in frame "WebResource_RegistrationApplicationAngular"
    Then approve transaction
    Then Click save CRM
    Then Status should be "<Status>"
    Examples:
      | amendmentReason | Status   | SuccessMessage                          |
      | Change of Name  | Approved | Processing Completed - Reference Number |

  @SUC:01-07 @UAT_TCS-01.07.3
  Scenario: UAT_TCS 01.07.3-To verify the process of amendment request received from Taxpayer Portal
    Given User navigates to the Portal login page
    And Enters the Portal username "maxportalamend" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Then user navigates to my account
    Then user clicks edit button to edit account details
    Then user modifies second name to "PortalAmendTwo"
    Then user clicks save button after amend name
    Then Verify portal save success message "Record submitted successfully and your request is waiting for approval."


  @SUC:01-07 @UAT_TCS-01.07.4
  Scenario: UAT_TCS 01.07.4-To verify the process of Taxpayer not found for Amendment
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0028182"
    Then Click search : id "SearchForm:j_idt40"
    Then Verify no data is found in table

  @SUC:01-07 @UAT_TCS-01.07.5
  Scenario Outline: UAT_TCS 01.07.5-To verify the process of validation error during amendment
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033464"
    Then Click search : id "SearchForm:j_idt40"
    Then Select category as "<Category>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify error message "Occupation/Business Interests: Turnover - Value is required"
    Examples:
      | Category      | amendmentReason |
      | Self-employed | Change of Name  |

  @SUC:01-07 @UAT_TCS-01.07.6
  Scenario: UAT_TCS 01.07.6-To verify the process of Abandoning amendment
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033537"
    Then Click search : id "SearchForm:j_idt40"
    Then Click Cancel "RegisterIndividual:cancelIndividual"
    Then Verify abandon process "http://34.241.245.79:8080/trips-ui/faces/login/Welcome.xhtml"


  @SUC:01-07 @UAT_TCS-01.07.7
  Scenario: UAT_TCS 01.07.7-To verify the process of Duplicate check during Amendment
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033464"
    Then Click search : id "SearchForm:j_idt40"
    Then Enter first name "Max" and last name "Pain"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "Processing Completed - Reference Number"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Wait for duplicate check "Validate Duplicates" to load in frame "WebResource_RegistrationApplicationAngular"
    Then switch to frame
    Then Verify duplicate check returns duplicates
    Then Delete case

  @SUC:01-07 @UAT_TCS-01.07.8
  Scenario Outline: UAT_TCS 01.07.8-To verify the process of checking duplicates [Organization initially registered in RGD]
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033464"
    Then Click search : id "SearchForm:j_idt40"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "<SuccessMessage>"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Click next stage button
    Then switch to frame
    Then Wait for text "Amendment Change Type" to load in frame "WebResource_RegistrationApplicationAngular"
    Then switch to frame
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "<Notes>"
    And Enter Outcome Reason for Taxpayer accounting
    Then Click save CRM
    Then Status should be "Rejected"
    Examples:
      | firstName | lastName | amendmentReason | SuccessMessage                          | Notes      |
      | John      | Kamau    | Change of Name  | Processing Completed - Reference Number | Duplicates |

  @SUC:01-07 @UAT_TCS-01.07.10
  Scenario Outline: UAT_TCS 01.07.10-To verify the process of Amending when taxpayer status is 'Pending Approval'
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000036099"
    Then Click search : id "SearchForm:j_idt40"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify error message "Taxpayer has an existing application which is pending approval, hence the request cannot be processed at this time."
    Examples:
      | firstName | lastName | amendmentReason |
      | John      | Kamau    | Change of Name  |

  @SUC:01-07 @UAT_TCS-01.07.11
  Scenario: UAT_TCS 01.07.11-To verify the process of Amending when a taxpayer status is 'De-Registered'
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N000002063X"
    Then Click search : id "SearchForm:j_idt40"
    Then Verify no data is found in table

  @SUC:01-07 @UAT_TCS-01.07.12
  Scenario: UAT_TCS 01.07.12-To verify the process of Amending when taxpayer and other relationships contains same TIN number
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033537"
    Then Click search : id "SearchForm:j_idt40"
    Then Click relationships tab
    Then Click add "RegisterIndividual:individualAccordion:relationshipHandler:AddRelationshipDetails"
    Then Switch to frame
    Then Select relationship type
    Then Click find "RelationshipDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for relationship with same tin "N0000033537"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='RelationshipDetails:Ok']"
    Then Verify error message "The selected taxpayer cannot be added to itself as any other relationship."


  @SUC:01-07 @UAT_TCS-01.07.13
  Scenario: UAT_TCS 01.07.13-To verify the process of Transferring Property
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033537"
    Then Click search : id "SearchForm:j_idt40"
    Then Select reason for amendment "Change of Property Details"
    Then Click properties tab
    Then Obtain property id of first property item in list
    Then Click table column "//*[@id='RegisterIndividual:individualAccordion:propertyTableHandler_data']/tr[1]/td[2]"
    Then Click transfer
    Then Switch to frame
    Then Click find "TransferPropertyDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for taxpayer to transfer property to "N0000033464"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='TransferPropertyDetails:Ok']"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "N0000033464"
    Then Click search : id "SearchForm:j_idt40"
    Then Click properties tab
    Then Validate transfer of property by checking property id

  
    
    

