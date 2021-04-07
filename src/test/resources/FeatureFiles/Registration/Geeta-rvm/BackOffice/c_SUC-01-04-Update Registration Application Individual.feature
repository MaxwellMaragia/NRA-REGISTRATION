Feature: SUC:01-04_Update Registration Application

  @UAT_TCS-01.03.2 @UAT_TCS-01.04.2 @SUC:01-04- @Reports @combine--
  Scenario Outline: UAT_TCS-01.03.2-To verify the process of checking validation error
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    When I Fill the Individual Taxpayer Registration form
    And I enter valid data on the Individualpage and Submit
      | First Name        | Max      | 0 |
      | Last Name         | One      | 1 |
      | CategoryValue     | Employee | 2 |
      | Title Value       | MR       | 3 |
      | Gender            | M        | 4 |
      | MothersMaidenName | Wambui   | 5 |
    And Enter Date Of Birth in additional info tab"<DOB>"
      | Marital Status     | Married                        | 0 |
      | Place Of Birth     | Mumbai                         | 1 |
      | Country Value      | Albania                        | 2 |
      | ReasonForTin Value | A Contractor or Sub-contractor | 3 |
      | Nationality Value  | Albania                        | 4 |

    Then Select residence permit identification with number "211257"
    And Enter identification Date of Issue "<DOI>"
      | Identification      | Identification       | 0 |
      | Identification Type | Passport             | 1 |
      | Identification num  | 1000h182             | 2 |
      | Country of Issue    | Albania              | 3 |
      | epermit num         | jhbak1256              | 4 |
      | epermit type        | Asylum Seeker Permit | 5 |

    And Enter identification Expiry Date "<IED>"
      | Identification Type | Driving Licence    | 0 |
      | Identification num  | account12894       | 1 |
      | Register Ind        | Employment Details | 2 |
      | Employment Position | Senior Executive   | 3 |
      | Employer's Name     | SiddharthReddy     | 4 |
    And Enter Employee details "<ESD>"
      | Occupation               | Occupation/Business Interests                                           | 0     |
      | Occupation status        | Employed                                                                | 1     |
      | Occupation main category | AGRICULTURE, ANIMAL HUSBANDARY, FORESTRY WORKERS, FISHERMEN AND HUNTERS | 2     |
      | Occupation precise value | Agriculture and Animal Husbandry Workers                                | 3     |
      | Address Submodule        | Addresses                                                               | 4     |
      | AddressValue             | Local Postal Address                                                    | 26 5  |
      | SName                    | United States                                                           | 27 6  |
      | City                     | United States                                                           | 28 7  |
      | Mname                    | New jersey                                                              | 29 8  |
      | ProvisionValue           | Bombali                                                                 | 30 9  |
      | ReogonValue              | North                                                                   | 31 10 |
      | Contact Method           | Contact Methods                                                         | 32 11 |
      | Purpose Value            | Personal                                                                | 33 12 |
      | ContactTypeValue         | Email                                                                   | 34 13 |
      | ContactDetails           | margiewambui11@gmail.com                                                | 35 14 |
    Then Click On Individual Page Submit Button
    Then Verify error message " cannot be empty."
    Examples:
      | DOB      | DOI        | IED        | ESD        |
      | 26091989 | 11/04/2010 | 11/04/2022 | 11/02/2000 |
