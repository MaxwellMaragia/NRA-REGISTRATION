Feature: SUC:01-24 Taxpayer Portal Registration (Individual)

  #  fresh taxpayer with PAYE and INCOME TAX already registered and portal credentials setup

  @SUC:01-24 @UAT_TCS-04.05.2
  Scenario Outline: UAT_TCS 04.05.2 To Verify the Process of Registering a Tax Type in the Taxpayer Portal
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "JuliusCeaser" and password "Password@123" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>taxtype request
    And enters taxtype as <TaxType>
    And enters an effective date
    And enters taxtype taxable turnover <amount>
    And enters number of employees as "1"
    And Select document type as "Business Registration Certificate"
    And Enter document name as "BRC123"
    Then Select attachment as "C:\ronaldo.png"
    And clicks taxtype registration Save Button
    Then Portal message is displayed "Your tax type registration request has been successfully submitted"
    Then Obtain reference number from portal success message "Your tax type registration request has been successfully submitted. Your reference number is"
    Given Open CRM URL Module
    And Close Popup Window
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Click next stage button
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "Invalid data"
    And Enter Outcome Reason for Taxpayer accounting
    Then Click save CRM
    Then Status should be "Rejected"
    Examples:
      | TaxType         | amount   |
      | Pay As You Earn | 10000000 |

  @SUC:01-24 @UAT_TCS-04.05.4
  Scenario: UAT_TCS 04.05.4-To Verify the Process of Income Tax Suspension with other tax types still active
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "princesswinnie" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>request suspension
    And Selects tax type as "Personal Income Tax"
    And Enter Suspension Start Date
    And Give Suspension End Date
    And Select reason for suspension as "No longer liable to tax type"
    And Click save to suspend taxtype
    Then Verify success message ""

  @SUC:01-24 @UAT_TCS-04.05.5
  Scenario: UAT_TCS 04.05.5-To Verify the Process of Suspending a Tax Type in the Taxpayer  Portal
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "princesswinnie" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>request suspension
    And Selects tax type as "Non Resident Tax(NRT)"
    And Enter Suspension Start Date
    And Give Suspension End Date
    And Select reason for suspension as "No longer liable to tax type"
    And Click save to suspend taxtype
    Then Verify success message ""

  @SUC:01-24 @UAT_TCS-04.05.6
  Scenario: UAT_TCS 04.05.6-To Verify the Process of making a Tax Type dormant in the Taxpayer  Portal
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "princesswinnie" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>request suspension
    And Selects tax type as "Non Resident Tax(NRT)"
    And Tick dormant check box
    And Enter Dormant Start Date
    And Select reason for suspension as "No longer liable to tax type"
    And Click save to suspend taxtype
    Then Verify success message ""








