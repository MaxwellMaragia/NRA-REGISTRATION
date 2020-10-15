Feature: [SUC:01-19] Find Taxpayer-Indv

  #@SUC:01-19
  Scenario: UAT_TCS 01.17.3 To verify the Process of checking Validation Error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Find Taxpayer
    Then Find screen should be displayed successfully
    When In Find screen user Clicks Search
    Then message is displayed "At least one field is required"

  #@SUC:01-19
  Scenario: UAT_TCS 01.17.4 To verify the Process of Not Finding a Taxpayer
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Find Taxpayer
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "00000000"
    And firstname "Max"
    And lastname "Mende"
    When In Find screen user Clicks Search
    Then No records found is displayed

  #@SUC:01-19
  Scenario: UAT_TCS 01.17.5 To verify the Process of Abandoning Search
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Find Taxpayer
    Then Find screen should be displayed successfully
    When In Find screen user Clicks Cancel
    Then user is navigated back to homepage "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml"

  #@SUC:01-19
  Scenario: UAT_TCS 01.17.6 To verify the Process of using Wild Search in Finding Taxpayer Details
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Find Taxpayer
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    And firstname "%"
    When In Find screen user Clicks Search
    Then all records are displayed
    And firstname "A%"
    When In Find screen user Clicks Search
    Then applications "firstname" starting with A displayed
    And firstname "A%"
    And lastname "A%"
    When In Find screen user Clicks Search
    Then applications "firstname" starting with A displayed
    Then applications "lastname" starting with A displayed
    And firstname "%A"
    And lastname "A%"
    When In Find screen user Clicks Search
    Then applications "firstname" ending with "A" displayed name id "SearchForm:resultsDataTable:0:j_id14"
    Then applications "lastname" starting with A displayed
    And clears all entries
    And TPIN "%35"
    When In Find screen user Clicks Search
    Then applications "tin" ending with "35" displayed name id "SearchForm:resultsDataTable:0:j_id18"

  #@SUC:01-19
  Scenario: UAT_TCS 01.17.7 To verify the Process of Finding Taxpayer from other functionality (use Case)
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>update Taxpayer
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0021518"
    When In Find screen user Clicks Search
    Then user with Tin "P0021518" is displayed on input "RegisterIndividual:TINInd"
    Given navigate to Registration>>Suspend Dormant Tax Type
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0021518"
    When In Find screen user Clicks Search
    Then user with Tin "P0021518" is displayed on input "SuspendRegime:TINInd"
    Given navigate to Registration>>Reactivate Tax
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0021518"
    When In Find screen user Clicks Search
    Then user with Tin "P0021518" is displayed on input "RegisterRegime:TINInd"
    Given navigate to Registration>>DeRegister Tax
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0021518"
    When In Find screen user Clicks Search
    Then user with Tin "P0021518" is displayed on input "DeregisterRegime:TINInd"
    Given navigate to Registration>>ReRegister Tax
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0019361"
    When In Find screen user Clicks Search
    Then user with Tin "P0019361" is displayed on input "ReregisterRegime:TINInd"
    Given navigate to Registration>>Transfer Tax
    Then Find screen should be displayed successfully
    When Select Taxpayer Classification Type "Individual"
    Then advanced search is displayed
    When enters Tin value as "P0021518"
    When In Find screen user Clicks Search
    Then user with Tin "P0021518" is displayed on input "TransferTaxpayer:TINInd"



