Feature: [SUC:01-12] Submit Portal Application

# @SUC:01-12
  Scenario:UAT_TCS 03.03.1 To Verify the process of failed login in to the Applicant Regitration Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "mamaa@mailinator.com" and password "Passw0rd@123"
    Then message is displayed "Login Error:"

 #@SUC:01-12
  Scenario:UAT_TCS 03.03.3 To Verify the process of  Save Error Validation in Applicant Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama6@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks Personal Details
    And user enters Applicant Individual details
      | FirstName          |                       |
      | LastName           |                       |
      | DOB                | 01/01/1990            |
      | POB                | Lilongwe              |
      | category           | Diplomat              |
      | title              | DR                    |
      | Gender             | M                     |
      | Marital            | Married               |
      | Nationality        | malawi                |
      | Tax Office         | Balaka                |
      | Reason Application | Am a company director |
      | country            | Kenya                 |
    Then save as draft button is not enabled
    And click next button
    Then validation message is displayed "This field is required"


  #@SUC:01-12
  Scenario:UAT_TCS 03.03.4 To Verify the process of Saving Application in Applicant Portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama6@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks Personal Details
    And user enters Applicant Individual details
      | FirstName          | Max                   |
      | LastName           | Mende                 |
      | DOB                | 01/01/1990            |
      | POB                | Lilongwe              |
      | category           | Diplomat              |
      | title              | DR                    |
      | Gender             | M                     |
      | Marital            | Married               |
      | Nationality        | malawi                |
      | Tax Office         | Balaka                |
      | Reason Application | Am a company director |
      | country            | Kenya                 |
    And clicks save as draft
    Then success message and exit confirmation dislayed



  #@SUC:01-12
  Scenario Outline:UAT_TCS 03.03.2-UAT_TCS 03.03.5 To Verify the process of Updating Application Details in Applicant portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama5@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks contacts
    And clicks new
    And inputs contact purpose as <purpose> and contact detail as <detail>
    And clicks update contact button
    Then contact successfully saved
    Given user clicks next for more details
    And clicks address new button
    And inputs Addres type<address> and house number <number> and street <street> and town <town>
    And clicks update address button
    Given user clicks next for more details
    And clicks identification new button
    And inputs identification type as <identification> and identification number <number>
    And inputs issue date <issue> and expiry date <expiry>
    And clicks update identification button
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Examples:
      | purpose  | detail                | identification | number   | issue      | expiry     | address                | number | street | town     |
      | Personal | v-bakam@microsoft.com | NID Card       | 32165499 | 01/01/1998 | 01/01/2030 | Foreign Postal Address | 22     | Lilo   | Lilongwe |

   #@SUC:01-12
  Scenario Outline:UAT_TCS 03.03.6 To Verify the process of Submitting Application Details in Applicant portal
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama6@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks contacts
    And clicks new
    And inputs contact purpose as <purpose> and contact detail as <detail>
    And clicks update contact button
    Then contact successfully saved
    Given user clicks next for more details
    And clicks address new button
    And inputs Addres type<address> and house number <number> and street <street> and town <town>
    And clicks update address button
    Given user clicks next for more details
    And clicks identification new button
    And inputs identification type as <identification> and identification number <number>
    And inputs issue date <issue> and expiry date <expiry>
    And clicks update identification button
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Examples:
      | purpose  | detail                | identification | number   | issue      | expiry     | address              | number | street | town     |
      | Personal | v-bakam@microsoft.com | Passport       | 32165499 | 01/01/1998 | 01/01/2030 | Local Postal Address | 22     | Lilo   | Lilongwe |


#  @SUC:01-12
  Scenario Outline:UAT_TCS 03.03.7 To Verify the process of Updating Application Details in Applicant portal [Sole proprietor]
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama5@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks Personal Details
    And user enters Applicant Individual details
      | FirstName          | Max                   |
      | LastName           | Mende                 |
      | DOB                | 01/01/1990            |
      | POB                | Lilongwe              |
      | category           | Sole proprietor       |
      | title              | Dr                    |
      | Gender             | M                     |
      | Marital            | Married               |
      | Nationality        | malawi                |
      | Tax Office         | Balaka                |
      | Reason Application | Am a company director |
      | country            | Kenya                 |
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Given user clicks contacts
    And clicks new
    And inputs contact purpose as <purpose> and contact detail as <detail>
    And clicks update contact button
    Then contact successfully saved
    Given user clicks next for more details
    And clicks address new button
    And inputs Addres type<address> and house number <number> and street <street> and town <town>
    And clicks update address button
    Given user clicks next for more details
    And clicks identification new button
    And inputs identification type as <identification> and identification number <number>
    And inputs issue date <issue> and expiry date <expiry>
    And clicks update identification button
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Examples:
      | purpose  | detail                | identification | number   | issue      | expiry     | address              | number | street | town     |
      | Personal | v-bakam@microsoft.com | NID Card       | 32165499 | 01/01/1998 | 01/01/2030 | Local Postal Address | 22     | Lilo   | Lilongwe |

#  @SUC:01-12
  Scenario Outline:UAT_TCS 03.03.8 To Verify the process of Updating Application Details in Applicant portal [Sole proprietor]
    Given User navigates to the Portal login page
    When User clicks login as Applicant
    And enters Portal email "bama5@mailinator.com" and password "Passw0rd@123"
    Then successfully logged in to appplicant portal

    Given user clicks Personal Details
    And user enters Applicant Individual details
      | FirstName          | Max                   |
      | LastName           | Mende                 |
      | DOB                | 01/01/1990            |
      | POB                | Lilongwe              |
      | category           | Employee              |
      | title              | Dr                    |
      | Gender             | M                     |
      | Marital            | Married               |
      | Nationality        | malawi                |
      | Tax Office         | Balaka                |
      | Reason Application | Am a company director |
      | country            | Kenya                 |
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Given user clicks contacts
    And clicks new
    And inputs contact purpose as <purpose> and contact detail as <detail>
    And clicks update contact button
    Then contact successfully saved
    Given user clicks next for more details
    And clicks address new button
    And inputs Addres type<address> and house number <number> and street <street> and town <town>
    And clicks update address button
    Given user clicks next for more details
    And clicks identification new button
    And inputs identification type as <identification> and identification number <number>
    And inputs issue date <issue> and expiry date <expiry>
    And clicks update identification button
    And clicks save as draft
    Then success message and exit confirmation dislayed

    Examples:
      | purpose  | detail                | identification | number   | issue      | expiry     | address              | number | street | town     |
      | Personal | v-bakam@microsoft.com | NID Card       | 32165499 | 01/01/1998 | 01/01/2030 | Local Postal Address | 22     | Lilo   | Lilongwe |

