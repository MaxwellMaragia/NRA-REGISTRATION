Feature: Print Registration Reports

  Background:
    Given User navigates to the login page
    When Enters the username "tripsuser" and password "Passw0rd"
    Then User should be logged in
    Then Click reporting > reports

  @UAT_TCS-01.27.1
  Scenario: UAT_TCS 01.27.1-To Verify the Process of printing Taxpayer Reports - Taxpayer Registrations Summary of Transactions
    Then Select report to print "Taxpayer Registrations Summary of Transactions"
    Then Select report file type "PDF"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.2
  Scenario: UAT_TCS 01.27.2-To Verify the Process of printing Taxpayer Reports - De-Registered Taxpayer List
    Then Select report to print "De-Registered Taxpayer List"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select business sector "All"
    Then Select district "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "De-Registered Taxpayer List.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "De-Registered Taxpayer List.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"


  @UAT_TCS-01.27.3
  Scenario: UAT_TCS 01.27.3-To Verify the Process of printing Taxpayer Reports - Registered Taxpayer List
    Then Select report to print "Registered Taxpayer List"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select business sector "All"
    Then Select district "All"
    Then Select source "Trips"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registered Taxpayer List.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registered Taxpayer List.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.4
  Scenario: UAT_TCS 01.27.4-To Verify the Process of printing Taxpayer Reports - Registrations Awaiting Approval Report
    Then Select report to print "Registrations Awaiting Approval Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select reason "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registrations Awaiting Approval Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registrations Awaiting Approval Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.5
  Scenario: UAT_TCS 01.27.5-To Verify the Process of printing Taxpayer Reports - Taxpayer Profile Amendment Report
    Then Select report to print "Taxpayer Profile Amendment Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select taxpayer category "Individual"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Profile Amendment Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Profile Amendment Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.6
  Scenario: UAT_TCS 01.27.6-To Verify the Process of printing Taxpayer Reports - Transferred Taxpayer List
    Then Select report to print "Transferred Taxpayers List"
    Then Select report file type "PDF"
    Then Select tax office from "Balaka"
    Then Select tax office to "Blantyre MTO"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Transferred Taxpayers List.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Transferred Taxpayers List.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.7
  Scenario: UAT_TCS 01.27.7-To Verify the Process of printing Taxpayer Reports - De-Registration Awaiting Approval Report
    Then Select report to print "De-Registrations Awaiting Approval Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select reason "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "De-Registrations Awaiting Approval Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "De-Registrations Awaiting Approval Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @UAT_TCS-01.27.8
  Scenario: UAT_TCS 01.27.8-To Verify the Process of printing  Taxpayer Reports - Registrations Awaiting Processing Report
    Then Select report to print "Registrations Awaiting Processing Report"
    Then Select report file type "PDF"
    Then Select tax office "All"
    Then Select reason "All"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registrations Awaiting Processing Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Registrations Awaiting Processing Report.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"


  @UAT_TCS-01.27.9
  Scenario: UAT_TCS 01.27.9-To Verify the Process of printing Taxpayer Reports - Validation Error
    Then Select report to print "Registrations Awaiting Processing Report"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify error message "Tax Office: Validation Error: Value is required."

  @UAT_TCS-01.27.10
  Scenario: UAT_TCS 01.27.10-To Verify the Process of printing Taxpayer Reports - Report is abandoned
    Then Select report to print "Registrations Awaiting Processing Report"
    Then Click Cancel "frmReportDetails:btnCancel"
    Then Verify report page is abandoned

  @UAT_TCS-01.27.11
  Scenario: UAT_TCS 01.27.11-To Verify the Process of printing Taxpayer Reports - Save Softcopy Report
    Then Select report to print "Taxpayer Registrations Summary of Transactions"
    Then Select report file type "PDF"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
    Then Select report file type "EXCEL"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Taxpayer Registrations Summary of Transactions.xls" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"
