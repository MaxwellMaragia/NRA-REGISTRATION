Feature: [SUC:02-11] Taxpayer Portal Registration

  Background:
    Given User navigates to the Portal login page
    When User clicks login as Taxpayer
    And Enters the Portal username "Bakamsystems" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal

  #@SUC:01-24
  Scenario Outline: UAT_TCS 04.05.2 To Verify the Process of Registering a Tax Type in the Taxpayer Portal
    Given user navigates to my tax>>taxtype request
    And enters taxtype as <TaxType>
    And enters an effective date
    And enters taxtype taxable turnover <amount>
    And clicks taxtype registration Save Button
    Then Portal message is displayed "Your tax type registration request has been successfully submitted"
    Examples:
      | TaxType            | amount   |
      | Company Income Tax | 10000000 |