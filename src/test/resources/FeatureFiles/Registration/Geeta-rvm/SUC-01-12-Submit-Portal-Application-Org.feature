Feature: [SUC:01-12] Submit Portal Application(Organisation)

  #  @SUC:01-12
  Scenario:UAT_TCS 03.04.1 To Verify the process of failed login in to the Applicant Regitration Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "mamaa@mailinator.com" and password "Passw0rd@123"
    Then message is displayed "Login Error:"

        #  @SUC:01-12
  Scenario:UAT_TCS 03.04.3 To Verify the process of  Save Error Validation in Applicant Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "mamaa@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks organization details
    And user enters organisation details
      | category         | Association |
      | OrgName          | MaxMende    |
      | Preferred Office | Balaka      |
      | TINreason        |             |
      | DOI              |             |
      | place            | malawi      |
      | capital source   | Research    |
    Given user clicks next for more details
    Then validation message is displayed "This field is required"

    #  @SUC:01-12
  Scenario: UAT_TCS 03.04.4 To Verify the process of Saving Application in Applicant Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bab00@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to organisation portal

  @SUC:01-12
  Scenario: UAT_TCS 03.04.2 To Verify all Fields in Update Portal Application Screens
    Given user clicks organization details
    And user enters organisation details
      | category         | Association    |
      | OrgName          | MaxMende       |
      | Preferred Office | Balaka         |
      | TINreason        | Am an employer |
      | DOI              | 01/01/2007     |
      | place            | malawi         |
      | capital source   | Research       |
    And clicks save as draft
    Then success message and exit confirmation dislayed

  @SUC:01-12
  Scenario Outline: UAT_TCS 03.03.2-UAT_TCS 03.04.5 To Verify the process of Updating Application Details in Applicant portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bab000@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to organisation portal

    Given user clicks organization details
    And user enters organisation details
      | category         | Association    |
      | OrgName          | MaxMende       |
      | Preferred Office | Balaka         |
      | TINreason        | Am an employer |
      | DOI              | 01/01/2007     |
      | place            | malawi         |
      | capital source   | Research       |
    Given user clicks next for more details
    And clicks new on business sector
    And Fill business sector details and click next
      | BusinessSector | 011 - Growing of non-perennial crops |
    And click next on business sector
    And Fill in contact details and click next
      | ContactPurpose | Business                |
      | ContactDetail  | baze@codeisystems.co.ke |
    And Add address details and click next
      | AddressType | Local Business Address |
      | Town        | Lilongwe               |
      | Region      | Central Region         |
      | District    | Lilongwe               |
    And Fill directors details and click next
      | DirectorsTin  | P0020448 |
      | DirectorsName | Baze     |
    And Fill in attachment details and click next
      | DocumentType | Constitution                          |
      | DocNumber    | 32355247                              |
      | Attachment   | C:\Users\v-bakam\Downloads\id_doc.png |

    Given user clicks next for more details
    And clicks address new button
    And inputs Business Sector "0112 - Growing of rice"
    And clicks Add Business Details button
    Given user clicks next for more details
    And clicks contacts new button
    And selects contact purpose "Business" and contact detail "v-bakam@microsoft.com"
    And clicks Add contacts button
    Given user clicks next for more details
    And clicks new button
    And inputs Addres type<address> and house number <number> and street <street> and town <town>
    And clicks primary indicator
    And clicks add address button
    And clicks save as draft
    Then success message and exit confirmation dislayed
    Given user clicks next for more details
    And clicks new Director button
    And enters directors TIN "P0020500" and name "MR.Director"
    And clicks add director button
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Examples:
      | address                | number   | town     | street |
      | Local Business Address | 32165499 | Lilongwe | Elm    |

#   @SUC:01-12
  Scenario: UAT_TCS 03.04.6 To Verify the process of Submitting Application Details in Applicant portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bab000@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to organisation portal
    Given user clicks Directors tab
    Given user clicks next for more details
    And clicks new Attachment button
    And user enters Document type "Constitution" Document number "12345" and attachment "C:\Users\barnaby.kamau\Desktop\id_doc.png"
    And clicks add attachment button
    Given user clicks next for more details
    And clicks the Summary submit
    Then Application should submit the Application and display Reference Number
