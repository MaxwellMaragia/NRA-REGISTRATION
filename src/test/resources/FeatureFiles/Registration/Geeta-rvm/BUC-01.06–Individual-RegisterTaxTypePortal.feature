Feature: SUC:01-24 Taxpayer Portal Registration (Individual)

  #  fresh taxpayer with PAYE and INCOME TAX already registered and portal credentials setup

  @SUC:01-24 @UAT_TCS-04.05.2
  Scenario Outline: UAT_TCS 04.05.2 To Verify the Process of Registering a Tax Type in the Taxpayer Portal
    Given User navigates to the Portal login page
    And Enters the Portal username "taxtypeportalthree" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>taxtype request
    And enters taxtype as <TaxType>
    And enters an effective date
    And clicks taxtype registration Save Button
    Then Portal message is displayed "Your tax type registration request has been successfully submitted"
    Then Obtain reference number from portal success message "Your tax type registration request has been successfully submitted"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\users\Maxwell Maragia\downloads"
    Then switch to frame
    Then clicks Approve from the dropdown "<Approve>
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | TaxType           | Read     | Approve  |
      | Rental Income Tax | Approved | Tax Type |

  @SUC:01-24 @UAT_TCS-04.05.4
  Scenario Outline: UAT_TCS 04.05.4-To Verify the Process of Income Tax Suspension with other tax types still active
    Given User navigates to the Portal login page
    And Enters the Portal username "taxtypeportalthree" and password "Codei@maseno2020" to login
    Then is logged in to taxpayer portal
    Given user navigates to my tax>>request suspension
    And Selects tax type as "Personal Income Tax"
    And Enter Suspension Start Date
    And Give Suspension End Date
    And Select reason for suspension as "No longer liable to tax type"
    And Click save to suspend taxtype
    Then Verify success message "Your Suspend tax type request has been successfully submitted. Your reference number is"
    Then Obtain reference number from portal success message for suspend "Your tax type registration request has been successfully submitted"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame0
    Then search for reference number
    Then Click on reference number
    Then switch to frame
    And Click on NextStage button
    Then switch to frame
    Then Goto view AttachmentDetails screen
    And Download the Attachment "C:\users\Maxwell Maragia\downloads"
    Then switch to frame
    Then clicks Approve from the dropdown "<Approve>"
    Then Click on Save button
    And Verify the String "<Read>"
    Examples:
      | Read     | Approve  |
      | Approved | Tax Type |

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








