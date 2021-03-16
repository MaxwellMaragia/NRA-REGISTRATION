Feature: [SUC:01-21] Find Registration Application

  @SUC:01-21
  Scenario: UAT_TCS 01.11.1-UAT_TCS 01.11.2-To verify the process of Successful Registration application search
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Register Taxpayer
    And clicks Process Registration Application
    And enters the ref number "ARN/00004841/2018"
    And clicks Find Registration Application search button
    Then Searched application is displayed

  @SUC:01-21
  Scenario: UAT_TCS 01.12.3-To verify the process of unsuccessful Registration application search
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Register Taxpayer
    And clicks Process Registration Application
    And enters the ref number "ARN/00000000/2018"
    And clicks Find Registration Application search button
    Then No records found is displayed

  @SUC:01-21
  Scenario: UAT_TCS 01.12.4-To verify the process of modifying a Registration application search
    And enters the ref number "ARN/00004841/2018"
    And clicks Find Registration Application search button
    Then Searched application is displayed

  @SUC:01-21
  Scenario: UAT_TCS 01.12.5-To verify the process of wild card search of Registration Application
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Given navigate to Registration>>Register Taxpayer
    And clicks Process Registration Application
    And enters the ref number "A%"
    And clicks Find Registration Application search button
    Then all applications are displayed
    And enters the ref number "%A"
    And clicks Find Registration Application search button
    Then No records found is displayed
    And enters the ref number "%"
    And clicks Find Registration Application search button
    Then all applications are displayed