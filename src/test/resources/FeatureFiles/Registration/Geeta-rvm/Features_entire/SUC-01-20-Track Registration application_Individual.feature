Feature: [SUC:01-20] Track Registration application_Individual

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in

  @SUC:01-20
  Scenario: UAT_TCS-03.05.1-To verify the process of verifying all the fields and buttons in the Track Registration Application screen & UAT_TCS-03.05.2-To verify the process of Tracking Registration application
    Then Click on registration > register taxpayer > process registration application
    Then Enter registration application reference number as "ARN/00018025/2019"
    Then Click search : id "SearchForm:j_idt42"
    Then Click view
    Then Verify reference number, applicant name, application status and tpin fields

  @SUC:01-20
  Scenario: UAT_TCS-03.05.3-To verify the process of not finding an application number
    Then Click on registration > register taxpayer > process registration application
    Then Enter registration application reference number as "ARN/18025/2019"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table