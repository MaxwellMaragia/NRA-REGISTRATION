Feature: Print Registration Reports TaxType
  
  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Click reporting > reports
    
  @UAT_TCS-01.32.1 @Reports
  Scenario: UAT_TCS 01.32.1-To Verify the Process of printing Tax Type Reports - Reactivated Tax Type List
    Then Select report to print "Reactivated Tax Type List"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Reactivated Tax Type List.pdf" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Reactivated Tax Type List.xls" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"

  @UAT_TCS-01.32.2 @Reports
  Scenario: UAT_TCS 01.32.2-To Verify the Process of printing Tax Type Reports - Tax Type Registration Summary of Transactions
    Then Select report to print "Tax Type Registration Summary Of Transactions"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select tax type "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Tax Type Registration Summary Of Transactions.pdf" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Tax Type Registration Summary Of Transactions.xls" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"

  @UAT_TCS-01.32.3 @Reports
  Scenario: UAT_TCS 01.32.3-To Verify the Process of printing Tax Type Reports- Suspended Tax Type List
    Then Select report to print "Suspended Tax Type List"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select tax type "PAYE"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Suspended Tax Type List.pdf" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Suspended Tax Type List.xls" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"


  @UAT_TCS-01.32.4 @Reports
  Scenario: UAT_TCS 01.32.4-To Verify the Process of printing Tax Type Reports- Tax Type Registration Report
    Then Select report to print "TaxTypeRegistrationReport"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select taxpayer category "Individual"
    Then Select tax type "PAYE"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "TaxTypeRegistrationReport.pdf" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "TaxTypeRegistrationReport.xls" has been downloaded in downloads directory "C:\Users\barnaby.kamau\Downloads"

  @UAT_TCS-01.32.5 @Reports
  Scenario: UAT_TCS 01.32.5-To Verify the Process of printing Tax Type Reports -  Validation Error
    Then Select report to print "TaxTypeRegistrationReport"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify error message "Tax Office: Validation Error: Value is required."

  @UAT_TCS-01.32.6 @Reports
  Scenario: UAT_TCS 01.32.6-To Verify the Process of printing Taxpayer Reports - Report is abandoned
    Then Select report to print "TaxTypeRegistrationReport"
    Then Click Cancel "frmReportDetails:btnCancel"
    Then Verify report page is abandoned