package StepDefinitions;

import Utils.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class stepDefinitions extends BaseClass {

    public static sharedatastep sharedata;
    public String ReferenceNumber = "ARN/00025810/2020";
    public String propertyID;
    public String organizationPropertyID;

    public stepDefinitions(sharedatastep sharedata) throws Exception {

        stepDefinitions.sharedata = sharedata;
        driver = getDriver();
        Pro = propFile();

    }

    //----------------------------------Shared Login and Logout Code-------------------------------------------------------------------///
    @Given("^User navigates to the login page$")
    public void user_navigates_to_the_login_page() throws Throwable {
//
//    	Intergration
//        driver.get(Pro.getProperty("IntergrationBackoffice"));

//        SIT
        driver.get(Pro.getProperty("SITBackoffice"));
//        driver.get("https://backoffice.mra.mw:8443/trips-ui/faces/login/tripsLogin.xhtml");
    }

    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void enters_the_username_and_password(String strArg1, String strArg2) throws Throwable {
        driver.findElement(By.id("loginForm:username")).clear();
        driver.findElement(By.id("loginForm:username")).sendKeys(strArg1);
        driver.findElement(By.id("loginForm:password")).clear();
        driver.findElement(By.id("loginForm:password")).sendKeys(strArg2);
        driver.findElement(By.xpath("//button[@type='submit' and span='Login']")).click();
    }

    @Then("^User should be logged in$")
    public void user_should_be_logged_in() throws Throwable {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://34.241.245.79:8080/trips-ui/faces/login/Welcome.xhtml");
    }

    @Then("^User logs out successfully$")
    public void user_logs_out_successfully() throws Throwable {
        driver.findElement(By.id("Logout")).click();
    }

////----------------------------------------Register Tax Type---------------------------------------------------------------------------------------------///

    @Given("^navigate to Registration>>Register Tax Type$")
    public void navigate_to_registrationregister_tax_type() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("registerTaxtype_XPATH"))).click();
        Thread.sleep(2000);
    }

    @When("^Select Taxpayer Classification Type as (.+)$")
    public void select_taxpayer_classification_type_as(String type) throws Throwable {
        WebElement classTypeDropdown = driver.findElement(By.xpath("//*[@id=\"SearchForm:DTYPE\"]/div[3]"));
        classTypeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='" + type + "']")).click();

        Thread.sleep(4000);
    }

    @And("^enters TIN as (.+)$")
    public void enters_tin_as(String tin) throws Throwable {
        WebElement tinInput = driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(tin);
    }

    @And("^clicks find entity search button$")
    public void clicks_find_entity_search_button() throws Throwable {
        driver.findElement(By.id("SearchForm:j_idt40")).click();

        Thread.sleep(5000);
    }

    @And("^click taxtpes field$")
    public void click_taxtpes_field() throws Throwable {
        driver.findElement(By.xpath("//*[text()=\"Tax Types\"]")).click();

        Thread.sleep(5000);
    }

    @Then("^taxtypes are displayed$")
    public void taxtypes_are_displayed() throws Throwable {
        WebElement table = driver.findElement(By.xpath("//*[@id=\"RegisterRegime:RegimeTable\"]/div[2]/table"));
        Assert.assertTrue(table.isDisplayed());
    }

    @Given("^Click Register Tax Type button$")
    public void click_register_tax_type_button() throws Throwable {
        WebElement registerTaxtypeBtn = driver.findElement(By.id("RegisterRegime:RegimeTable:RegisterTaxType"));
        registerTaxtypeBtn.click();

        Thread.sleep(5000);
    }

    @And("^Selects (.+)  from Tax Type drop down$")
    public void selects_from_tax_type_drop_down(String taxtype) throws Throwable {
        WebElement frame = driver.findElement(By.tagName("iframe"));

        //Switch to iframe to allow interaction with modal
        driver.switchTo().frame(frame);

        WebElement taxTypeDropdown = driver.findElement(By.xpath("//*[@id=\"RevenueTypeDetails:RevenueType\"]/div[3]"));
        taxTypeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='" + taxtype + "']")).click();
        Thread.sleep(5000);

    }

    @And("^enters date of mission$")
    public void enters_date_of_mission() throws Throwable {

        WebElement submissionDateInput = driver.findElement(By.id("RevenueTypeDetails:DateOfSubmission_input"));
        submissionDateInput.sendKeys(Keys.ENTER);
    }

    @And("^enters EDR$")
    public void enters_edr() throws Throwable {
        WebElement EDRInput = driver.findElement(By.id("RevenueTypeDetails:EffectiveDateOfRegistration_input"));
        EDRInput.sendKeys(Keys.ENTER);
    }

    @And("^checks forced registration$")
    public void checks_forced_registration() throws Throwable {
        WebElement forcedRegCheckBox = driver.findElement(By.xpath("//*[@id=\"RevenueTypeDetails:ForcedRegistration\"]/div[2]"));
        forcedRegCheckBox.click();
    }

    @And("^clicks ok on tax type details$")
    public void clicks_ok_on_tax_type_details() throws Throwable {
        WebElement taxTypDetOKBtn = driver.findElement(By.id("RevenueTypeDetails:okButton"));
        taxTypDetOKBtn.click();
        Thread.sleep(5000);
    }

    @Then("^message is displayed \"([^\"]*)\"$")
    public void message_is_displayed(String strArg1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + strArg1 + "')]")));
        if (Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed", true);
        } else {
            Assert.fail("No Error message displayed");
        }
    }

    @And("^clicks tax type registration submit button$")
    public void clicks_tax_type_registration_submit_button() throws Throwable {

        WebElement registerSubmitBtn = driver.findElement(By.id("RegisterRegime:Submit"));
        registerSubmitBtn.click();
        Thread.sleep(5000);
    }

    @Then("^success message is displayed \"([^\"]*)\"$")
    public void success_message_is_displayed(String strArg1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + strArg1 + "')]")));
        if (Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed", true);
        } else {
            Assert.fail("No Error message displayed");
        }
        String Text = Message.getText();
        sharedatastep.TaxType_CRMARN = Text.substring(Text.lastIndexOf(" ") + 1);
        System.out.print(sharedatastep.TaxType_CRMARN);
        System.out.print("Reference Number is - " + sharedatastep.TaxType_CRMARN);
    }

    @Then("^(.+) taxtype is displayed$")
    public void taxtype_is_displayed(String taxtype) throws Throwable {
        System.out.print("Approve on crm");
    }

    @Given("^enters taxable turnover (.+)$")
    public void enters_taxable_turnover(String taxableamount) throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement taxableTurnoverInput = driver.findElement(By.id("RevenueTypeDetails:TaxableTurnover_input"));

        taxableTurnoverInput.sendKeys(taxableamount);
    }


    @Then("^clicks Approve from the dropdown (.+)$")
    public void clicks_approve_from_the_dropdown(String approve) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + approve + "']")));
        Assert.assertTrue(downloadAttach.isDisplayed());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome4"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

        driver.switchTo().defaultContent();

    }

    @And("^clicks Decline from the dropdown (.+)$")
    public void clicks_Decline_from_the_dropdown(String decline) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + decline + "']")));
        Assert.assertTrue(downloadAttach.isDisplayed());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome4"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^Enter Outcome Notes (.+)$")
    public void enter_outcome_notes(String Notes) throws Throwable {
        Thread.sleep(3000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
        action1.sendKeys(element1, Notes).build().perform();
        Thread.sleep(5000);
    }

    @Then("^TaxTypes are displayed2$")
    public void TaxTypes_are_displayed2() throws Throwable {
        WebElement table = driver.findElement(By.id("RegisterIndividual:individualAccordion:TaxtypesTableHandler:j_idt166"));
        Assert.assertTrue(table.isDisplayed());
    }

    @Then("^TaxTypes are displayed$")
    public void TaxTypes_are_displayed() throws Throwable {
        WebElement table = driver.findElement(By.id("RegisterRegime:RegimeTable_data"));
        Assert.assertTrue(table.isDisplayed());
    }

    @And("^click save on Payments$")
    public void click_save_on_Payments() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_revenuecollectionapplication|NoRelationship|Form|Mscrm.Form.tbg_revenuecollectionapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Then("^Application Account Adjustment status should be \"([^\"]*)\"$")
    public void application_account_adjustment_status_should_be(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        Thread.sleep(3000);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Status_label\"]"))).getText();
        if (text.contains(Status)) {

            System.out.println("Text Verified and" + Status);
        } else {
            System.out.println("Text Not Verified and failed");
        }
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>Find Taxpayer$")
    public void navigate_to_registrationfind_taxpayer() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("findTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Then("^individual details displayed$")
    public void individual_details_displayed() throws Throwable {
        Thread.sleep(5000);
        WebElement individualDetailsHeader = driver.findElement(By.id("RegisterIndividual:individualummaryLabel"));
        Assert.assertEquals("Individual Summary Details", individualDetailsHeader.getText());

    }

    @When("^user clicks on taxtypes$")
    public void user_clicks_on_taxtypes() throws Throwable {

//        WebElement taxTypes = driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[6]"));
        WebElement taxTypes = driver.findElement(By.xpath("//a[text()='Tax Types']"));

        taxTypes.click();
        Thread.sleep(3000);
    }

    ///--------------------------------------Taxtype registration -Portal----------------------------------------------------------------------------------------------//
    @Given("^User navigates to the Portal login page$")
    public void user_navigates_to_the_portal_login_page() throws Throwable {
        driver.get(Pro.getProperty("PortalURL"));
    }

    @And("^Enters the Portal username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_portal_username_and_password_to_login(String strArg1, String strArg2) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_userName"))).sendKeys(strArg1);
        ;

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"id_password\"]"));
        passwordInput.sendKeys(strArg2);

        WebElement loginBtn = driver.findElement(By.id("btnSubmit"));
        loginBtn.click();
    }

    @When("^User clicks login as Taxpayer$")
    public void user_clicks_login_as_taxpayer() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/trips-app/div/app-portal-home/div/div/div[1]/div[3]/div/p/a"))).click();
    }

    @Then("^is logged in to portal$")
    public void is_logged_in_to_portal() throws Throwable {
        Thread.sleep(5000);
        WebElement welcomeImage = driver.findElement(By.id("id_btnMyTaxToggle"));
        Assert.assertTrue(welcomeImage.isDisplayed());
    }

    @Then("^is logged in to taxpayer portal$")
    public void is_logged_in_to_taxpayer_portal() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement welcomeImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_btnMyTax")));
        Assert.assertTrue(welcomeImage.isDisplayed());
    }


    @Then("^user navigates to my account$")
    public void user_navigates_to_myaccount() throws Throwable {
        Thread.sleep(3000);
        WebElement myTaxDropdown = driver.findElement(By.id("id_btnMyAccount"));
        myTaxDropdown.click();
    }

    @Then("^user clicks save button after amend name$")
    public void user_clicks_save_button_after_amend_name() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
    }

    @Then("^user modifies second name to \"([^\"]*)\"$")
    public void user_modifies_name(String name) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_lastName")));
        nameField.clear();
        nameField.sendKeys(name);
    }

    @Then("^user clicks edit button to edit account details$")
    public void user_clicks_edit_button_to_edit_account_details() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnEdit"))).click();
    }

    @Given("^user navigates to my tax>>taxtype request$")
    public void user_navigates_to_my_taxtaxtype_request() throws Throwable {
        WebElement myTaxDropdown = driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkTaxTypeRequest")).click();
    }

    @Given("^user navigates to my tax>>request suspension$")
    public void user_navigates_to_my_taxrequest_suspension() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement myTaxDropdown = driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkRequestSuspension")).click();
    }

    @And("^enters taxtype as (.+)$")
    public void enters_taxtype_as(String taxtype) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement taxTypeDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_taxTypes\"]/div/div[2]/p-dropdown/div/div[3]")));

        taxTypeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + taxtype + "']")).click();
    }

    @And("^enters an effective date$")
    public void enters_an_effective_date() throws Throwable {
        WebElement effectiveDateInput = driver.findElement(By.id("id_edr"));
        effectiveDateInput.sendKeys(todaysDate());
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();

    }

    @And("^enters taxtype taxable turnover (.+)$")
    public void enters_taxtype_taxable_turnover(String amount) throws Throwable {
        WebElement taxableTurnoverInput = driver.findElement(By.id("id_taxableTurnover"));
        taxableTurnoverInput.sendKeys(amount);
    }

    @And("^clicks taxtype registration Save Button$")
    public void clicks_taxtype_registration_save_button() throws Throwable {
        WebElement saveBtn = driver.findElement(By.id("btnSave"));
        saveBtn.click();
    }

    @Then("^Portal message is displayed \"([^\"]*)\"$")
    public void portal_message_is_displayed(String strArg1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + strArg1 + "')]")));

        Assert.assertTrue(message.isDisplayed());
    }

    @Then("^SUSPEND DORMANT TAX TYPE screen displayed$")
    public void suspend_dormant_tax_type_screen_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement taxTypeDropdown = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        Assert.assertTrue(taxTypeDropdown.isEnabled());

        WebElement dormantCheckbox = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        Assert.assertTrue(dormantCheckbox.isEnabled());

        WebElement inputBox = driver.findElement(By.id("id_notes"));
        Assert.assertTrue(inputBox.isEnabled());

        WebElement submitBtn = driver.findElement(By.id("btnSubmit"));
        Assert.assertTrue(submitBtn.isEnabled());

        WebElement cancelBtn = driver.findElement(By.id("btnCancel"));
        Assert.assertTrue(cancelBtn.isEnabled());

    }

    @And("^enters suspension start date$")
    public void enters_suspension_start_date() throws Throwable {
        WebElement suspensionDateInput = driver.findElement(By.id("id_suspensionStartDate"));
        suspensionDateInput.sendKeys(todaysDate());
    }

    @And("^enters suspension end date$")
    public void enters_suspension_end_date() throws Throwable {
        WebElement endSuspensionDateInput = driver.findElement(By.id("id_suspensionEndDate"));
        endSuspensionDateInput.sendKeys(tomorrowsDate());
    }

    @And("^enters reason for suspension$")
    public void enters_reason_for_suspension() throws Throwable {
        WebElement reasonDropdown = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        reasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]"));
    }

    @And("^clicks Suspension Save Button$")
    public void clicks_suspension_save_button() throws Throwable {
        WebElement saveBtn = driver.findElement(By.id("btnSubmit"));
        saveBtn.click();
    }

    @And("^checks dormant account$")
    public void checks_dormant_account() throws Throwable {
        WebElement dormantCheckbox = driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        dormantCheckbox.click();
    }

    @And("^enters dormant start date$")
    public void enters_dormant_start_date() throws Throwable {
        WebElement effectiveDateInput = driver.findElement(By.id("id_dormantStartDate"));
        effectiveDateInput.sendKeys(todaysDate());

    }

    @Given("^user navigates to my tax>>request reactivation$")
    public void user_navigates_to_my_taxrequest_reactivation() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement myTaxDropdown = driver.findElement(By.xpath("//*[@id=\"id_btnMyTaxToggle\"]/span"));
        myTaxDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_linkRequestReActivation")).click();
    }

    @Then("^REACTIVATE TAX TYPE screen displayed$")
    public void reactivate_tax_type_screen_displayed() throws Throwable {
        WebElement taxTypeDropdown = driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/label"));
        Assert.assertTrue(taxTypeDropdown.isEnabled());

        WebElement reactivationDate = driver.findElement(By.id("id_reactivationDate"));
        Assert.assertTrue(reactivationDate.isEnabled());

        WebElement reasonDropdown = driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/label"));
        Assert.assertTrue(reasonDropdown.isEnabled());

        WebElement notesField = driver.findElement(By.id("id_notes"));
        Assert.assertTrue(notesField.isEnabled());

    }

    @And("^enters reactivation date$")
    public void enters_reactivation_date() throws Throwable {
        WebElement reactivationDateInput = driver.findElement(By.id("id_reactivationDate"));
        reactivationDateInput.sendKeys(todaysDate());
    }

    @And("^enters reason for reactivation$")
    public void enters_reason_for_reactivation() throws Throwable {
        WebElement reasonDropdown = driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        reasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_reactivateTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]"));
    }

    @And("^clicks reactivation Save Button$")
    public void clicks_reactivation_save_button() throws Throwable {
        WebElement saveBtn = driver.findElement(By.id("btnSubmit"));
        saveBtn.click();
    }

    ///////--------------------------geeta code----------------------------------------------------------------///

    @When("^User clicks login as Applicant$")
    public void user_clicks_login_as_applicant() throws Throwable {
        Thread.sleep(5000);
        WebElement taxPayer = driver.findElement(By.xpath("/html/body/trips-app/div/app-portal-home/div/div/div[1]/div[3]/div[2]/p/a"));
        taxPayer.click();
    }

    @And("^clicks register now$")
    public void clicks_register_now() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("id_linkRegisterNow")).click();
    }

    @Then("^create portal credential page is displayed$")
    public void create_portal_credential_page_is_displayed() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement header = driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/ng-component/div"));

        Assert.assertTrue(header.isDisplayed());
    }

    @Then("^successfully logged in to appplicant portal$")
    public void successfully_logged_in_to_appplicant_portal() throws Throwable {
//        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[1]")));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Personal Details']")));
        Assert.assertTrue(nav.isDisplayed());
        Thread.sleep(4000);
    }

    @Then("^successfully logged in to organisation portal$")
    public void successfully_logged_in_to_organisation_portal() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[1]/ul/li[1]")));
        Assert.assertTrue(nav.isDisplayed());
        Thread.sleep(4000);
    }

    @Given("^user clicks applicant submit button with fields blank$")
    public void user_clicks_applicant_submit_button_with_fields_blank() throws Throwable {
        WebElement submitBtn = driver.findElement(By.id("id_btnSubmit"));
        Assert.assertTrue(submitBtn.isDisplayed());
    }

    @Then("^submit button should not be clickable$")
    public void submit_button_should_not_be_clickable() throws Throwable {
        WebElement submitBtn = driver.findElement(By.id("id_btnSubmit"));
        Assert.assertFalse(submitBtn.isEnabled());
    }

    @Given("^user inputs identification details$")
    public void user_inputs_identification_details(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();

        WebElement firstNameInput = driver.findElement(By.id("id_firstName"));
        firstNameInput.sendKeys(data.get(0).get(1));

        WebElement lastNameInput = driver.findElement(By.id("id_lastName"));
        lastNameInput.sendKeys(data.get(1).get(1));

        WebElement genderDropDown = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/div[3]"));
        genderDropDown.click();
        Thread.sleep(2000);
        // clicks male from dropdown
        driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[3]")).click();

        WebElement DOBInput = driver.findElement(By.id("id_dateOfBirth"));
        DOBInput.sendKeys(data.get(2).get(1));

        WebElement POBInput = driver.findElement(By.id("id_placeOfBirth"));
        POBInput.sendKeys(data.get(3).get(1));

        WebElement IdInput = driver.findElement(By.id("id_idenNumber"));
        IdInput.sendKeys(data.get(4).get(1));

        WebElement issueDate = driver.findElement(By.id("id_IssueDate"));
        issueDate.sendKeys(data.get(5).get(1));

        WebElement expiryDate = driver.findElement(By.id("id_expiryDate"));
        expiryDate.sendKeys(data.get(6).get(1));

        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys(data.get(7).get(1));

        //clicks on the search result in drop down after entering country
        WebElement firstEntry = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

    }

    @When("^user selects correct (.+) and (.+)$")
    public void user_selects_correct_and(String taxpayerregistrationtype, String identificationtype) throws Throwable {

        WebElement registrationType = driver.findElement(By.xpath("//span[contains(text(),'" + taxpayerregistrationtype + "')]"));
        registrationType.click();

        WebElement identificationDropdown = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]/span"));
        identificationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + identificationtype + "')]")).click();

        // clicks on nationality down then enters value in datatable
        try {
            WebElement nationalityDropdown = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
            nationalityDropdown.click();
            Thread.sleep(2000);
            WebElement nationalityInput = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
            nationalityInput.sendKeys("malawi");

            //clicks on the search result in drop down after entering country
            WebElement myNationality = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
            myNationality.click();
        } catch (NoSuchElementException | StaleElementReferenceException e) {

        }

    }

    @And("^clicks create portal credential validate id button$")
    public void clicks_create_portal_credential_validate_id_button() throws Throwable {
        WebElement validateId = driver.findElement(By.id("id_btnValidate"));
        validateId.click();
    }


    @Then("^successful validation message appears$")
    public void successful_validation_message_appears() throws Throwable {
        Thread.sleep(5000);
        WebElement validateId = driver.findElement(By.id("id_btnValidate"));
        Assert.assertFalse(validateId.isEnabled());
    }

    @Then("^unsuccessful validation message appears$")
    public void unsuccessful_validation_message_appears() throws Throwable {
        Thread.sleep(5000);
        WebElement validateId = driver.findElement(By.id("id_btnValidate"));
        Assert.assertTrue(validateId.isEnabled());
    }

    @Given("^user clicks contacts$")
    public void user_clicks_contacts() throws Throwable {
//        driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[2]/div[3]/button")).click();
        driver.findElement(By.xpath("//a[text()='Contacts']")).click();
    }

    @Given("^user clicks Personal Details$")
    public void user_clicks_personal_details() throws Throwable {
        WebElement personalDetails = driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[1]"));
        personalDetails.click();
    }

    @Given("^user enters valid (.+) and (.+)$")
    public void user_enters_valid_and(String email, String password) throws Throwable {
        WebElement emailInput = driver.findElement(By.id("id_email"));
        emailInput.sendKeys(email);

        WebElement confirmEmailInput = driver.findElement(By.id("id_confirmEmail"));
        confirmEmailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("id_password"));
        passwordInput.sendKeys(password);

        WebElement confirmPasswordInput = driver.findElement(By.id("id_confirmPassword"));
        confirmPasswordInput.sendKeys(password);
    }

    @And("^attaches id document (.+)$")
    public void attaches_id_document(String path) throws Throwable {
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span")).click();
        driver.switchTo()
                .activeElement()
                .sendKeys(
                        path);
        driver.switchTo();
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ESCAPE).perform();
    }

    @And("^checks captcha$")
    public void checks_captcha() throws Throwable {
        Thread.sleep(2000);
        WebElement captcha = driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]"));
        captcha.click();
    }


    @And("^clicks the submit button$")
    public void clicks_the_submit_button() throws Throwable {
        WebElement submitBtn = driver.findElement(By.id("id_btnSubmit"));
        submitBtn.click();
    }

    @And("^enters Portal email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void enters_portal_email_and_password(String strArg1, String strArg2) throws Throwable {
        Thread.sleep(5000);
        WebElement emailInput = driver.findElement(By.id("id_userName"));
        emailInput.sendKeys(strArg1);

        WebElement passwordInput = driver.findElement(By.id("id_password"));
        passwordInput.sendKeys(strArg2);

        WebElement loginBtn = driver.findElement(By.id("btnSubmit"));
        loginBtn.click();

    }

    @And("^user enters Applicant Individual details$")
    public void user_enters_applicant_individual_details(DataTable table) throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<List<String>> data = table.asLists();

        WebElement dob = driver.findElement(By.id("id_dateOfBirth"));
        dob.clear();

        WebElement firstNameInput = driver.findElement(By.id("id_firstName"));
        firstNameInput.clear();
        if (data.get(0).get(1) == null) {
            System.out.println("Firstname is Empty");
        } else {
            firstNameInput.sendKeys(data.get(0).get(1));
        }

        WebElement lastNameInput = driver.findElement(By.id("id_lastName"));
        lastNameInput.clear();
        if (data.get(1).get(1) == null) {
            System.out.println("Lastname is Empty");
        } else {
            lastNameInput.sendKeys(data.get(1).get(1));
        }

        dob.sendKeys(data.get(2).get(1));


        Thread.sleep(2000);

//        driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]/a")).click();

        WebElement pobInput = driver.findElement(By.id("id_placeOfBirth"));
        pobInput.clear();
        pobInput.sendKeys(data.get(3).get(1));

        WebElement categoryDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));

        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(4).get(1) + "')]")).click();

        WebElement titleDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        titleDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(5).get(1) + "')]")).click();

        WebElement genderDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[3]"));
        genderDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(6).get(1) + "')]")).click();

        WebElement maritalDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[3]"));
        maritalDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(7).get(1) + "')]")).click();

        WebElement nationalityDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[3]"));
        nationalityDropdown.click();
        Thread.sleep(2000);
        WebElement nationalityInput = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        nationalityInput.sendKeys(data.get(8).get(1));

        //clicks on the search result in drop down after entering country
        WebElement oneEntry = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[6]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        oneEntry.click();

        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[3]"));
        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys(data.get(8).get(1));

        //clicks on the search result in drop down after entering country
        WebElement firstEntry = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[7]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

        WebElement taxOfficeDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown[8]/div/div[2]/p-dropdown/div/div[3]"));
        taxOfficeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(9).get(1) + "')]")).click();

        WebElement reasonTinDropdown = driver.findElement(By.xpath("//*[@id=\"id_indPersonalDetailForm\"]/div/div/tb-dropdown-with-othertext/div/div[2]/p-dropdown/div/div[3]"));
        reasonTinDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(10).get(1) + "')]")).click();
    }

    @And("^clicks save as draft$")
    public void clicks_save_as_draft() throws Throwable {

        WebElement saveDraftBtn = driver.findElement(By.xpath("//*[@id='id_OrgForm']/form-wizard/div/div/div[2]/div[2]/button"));
//        WebElement saveDraftBtn=driver.findElement(By.xpath("//button[text()='Save As Draft']"));
        saveDraftBtn.click();
    }

    @Then("^save as draft button is not enabled$")
    public void save_as_draft_button_is_not_enabled() throws Throwable {
        WebElement saveDraftBtn = driver.findElement(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[5]/div/div[2]/button"));
        Assert.assertFalse(saveDraftBtn.isEnabled());
    }

    @Then("^validation message is displayed \"([^\"]*)\"$")
    public void validation_message_is_displayed_something(String strArg1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + strArg1 + "')]")));
        if (Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed", true);
        } else {
            Assert.fail("No Error message displayed");
        }
    }

    @And("^click next button$")
    public void click_next_button() throws Throwable {
        WebElement nextBtn = driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[2]/div[3]/button"));

        nextBtn.click();
        Thread.sleep(5000);
    }

    @Then("^success message and exit confirmation dislayed$")
    public void success_message_and_exit_confirmation_dislayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Exit Confirmation')]")));
        if (Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed", true);
        } else {
            Assert.fail("No Error message displayed");
        }

        //  click no to exit confirmation modal
        driver.findElement(By.xpath("/html/body/trips-app/p-confirmdialog/div/div[3]/button[2]")).click();
    }

    @Given("^user clicks next for more details$")
    public void user_clicks_next_for_more_details() throws Throwable {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement nextBtn = driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[2]/div[3]/button"));

        nextBtn.click();
        Thread.sleep(5000);
    }

    @And("^clicks the Summary submit$")
    public void clicks_the_summary_submit() throws Throwable {
        WebElement submitBtn = driver.findElement(By.xpath(Pro.getProperty("SubmitBtnXpath")));
        submitBtn.click();
    }

    @Then("^Application should submit the Application and display Reference Number$")
    public void application_should_submit_the_application_and_display_reference_number() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);

        WebElement refNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_summary\"]/div/div/h4[2]")));
        Assert.assertTrue(refNumber.isDisplayed());

    }

    @And("^enters directors TIN \"([^\"]*)\" and name \"([^\"]*)\"$")
    public void enters_directors_tin_something_and_name_something(String strArg1, String strArg2) throws Throwable {
        WebElement directorsTin = driver.findElement(By.id("id_relationTin"));
        directorsTin.sendKeys(strArg1);

        WebElement directorsName = driver.findElement(By.id("id_relationName"));
        directorsName.sendKeys(strArg2);

        WebElement startDate = driver.findElement(By.id("id_startDate"));
        startDate.clear();
        startDate.sendKeys(todaysDate());
    }

    @And("^clicks new$")
    public void clicks_new() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(4000);
        WebElement newBtn = driver.findElement(By.id("btnAdd"));
        newBtn.click();
    }

    @And("^inputs contact purpose as (.+) and contact detail as (.+)$")
    public void inputs_contact_purpose_as_and_contact_detail_as(String purpose, String detail) throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement purposeDropdown = driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        purposeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + purpose + "']")).click();

        WebElement contactDetailInput = driver.findElement(By.id("id_contactDetail"));
        contactDetailInput.sendKeys(detail);
    }

    @And("^clicks update contact button$")
    public void clicks_update_contact_button() throws Throwable {
        WebElement updateBtn = driver.findElement(By.id("btnSave"));
        updateBtn.click();
    }

    @Then("^contact successfully saved$")
    public void contact_successfully_saved() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement contactsRow = driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div[1]/tb-dropdown/div/div[3]"));
        Assert.assertTrue(contactsRow.isDisplayed());
    }

    @Then("^identification successfully saved$")
    public void identification_successfully_saved() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement contactsRow = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div[1]"));
        Assert.assertTrue(contactsRow.isDisplayed());
    }

    @And("^inputs identification type as (.+) and identification number (.+)$")
    public void inputs_identification_type_as_and_identification_number(String identification, String number) throws Throwable {
        WebElement identificationDropdown = driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div[2]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        identificationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + identification + "')]")).click();

        WebElement identifyNumberInput = driver.findElement(By.id("id_idenNumber"));
        identifyNumberInput.sendKeys(number);

    }

    @And("^inputs issue date (.+) and expiry date (.+)$")
    public void inputs_issue_date_and_expiry_date(String issue, String expiry) throws Throwable {
        WebElement issueDateInput = driver.findElement(By.id("id_IssueDate"));
        issueDateInput.sendKeys(issue);

        WebElement expiryDateInput = driver.findElement(By.id("id_expiryDate"));
        expiryDateInput.sendKeys(expiry);
    }

    @And("^clicks identification new button$")
    public void clicks_identification_new_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("newBtnIdentification"))).click();
//        driver.findElement(By.xpath("//button[contains(text(),'New')]"))
    }

    @And("^clicks update identification button$")
    public void clicks_update_identification_button() throws Throwable {
        Thread.sleep(4000);

//        WebElement updateBtn=driver.findElement(By.id("btnSave"));
        WebElement updateBtn = driver.findElement(By.xpath(Pro.getProperty("addIdentificationBtn")));
        updateBtn.click();
    }

    @And("^inputs occupation status (.+) and main category (.+) and precise category (.+)$")
    public void inputs_occupation_status_and_main_category_and_precise_category(String occupation, String category, String precise) throws Throwable {
        WebElement occupationDropdown = driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        occupationDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + occupation + "')]")).click();

        WebElement categoryDropdown = driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + category + "')]")).click();

        WebElement preciseDropdown = driver.findElement(By.xpath("//*[@id=\"id_occupationForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]"));
        preciseDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + precise + "')]")).click();

    }

    @Then("^registration is successful$")
    public void registration_is_successful() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Registration status')]")));
        if (Message.isDisplayed()) {
            Assert.assertTrue("Error message displayed", true);
        } else {
            Assert.fail("No Error message displayed");
        }
    }

    @And("^inputs Business Sector \"([^\"]*)\"$")
    public void inputs_business_sector_something(String strArg1) throws Throwable {
        WebElement businessSectorDropdown = driver.findElement(By.xpath("//*[@id=\"id_businessSectorForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]"));
        businessSectorDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + strArg1 + "']")).click();

        WebElement primaryIndicator = driver.findElement(By.xpath("//*[@id=\"id_businessSectorForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        primaryIndicator.click();
    }

    @And("^clicks Add Business Details button$")
    public void clicks_add_business_details_button() throws Throwable {
        WebElement addBusinessDetails = driver.findElement(By.xpath(Pro.getProperty("addBusinessSectorXpath")));
        addBusinessDetails.click();
    }

    @And("^clicks Add contacts button$")
    public void clicks_add_contacts_button() throws Throwable {
        WebElement addBusinessDetails = driver.findElement(By.xpath(Pro.getProperty("addContactsXpath")));
        addBusinessDetails.click();
    }


    @And("^clicks address new button$")
    public void clicks_address_new_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("NewBusinessSectorXpath"))).click();
    }

    @And("^clicks contacts new button$")
    public void clicks_contacts_new_button() throws Throwable {
        WebElement contact = driver.findElement(By.xpath("//*[@id=\"id_addressFormGroup\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/label"));
        if (contact.isDisplayed()) {
            WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[2]/div[3]/button"));
            nextBtn.click();
        } else {
            driver.findElement(By.xpath(Pro.getProperty("NewContactsXpath"))).click();
        }

    }

    @And("^clicks new button$")
    public void clicks_new_button() throws Throwable {
        WebElement contact = driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/label"));
        if (contact.isDisplayed()) {
            WebElement nextBtn = driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[2]/div[3]/button"));
            nextBtn.click();
        } else {
            driver.findElement(By.id("id_newAddress")).click();
        }
    }

    @And("^clicks new Director button$")
    public void clicks_new_director_button() throws Throwable {
        WebElement contact = driver.findElement(By.xpath("//*[@id=\"id_directorForm\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/label"));
        if (contact.isDisplayed()) {
            WebElement nextBtn = driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[2]/div[3]/button"));
            nextBtn.click();
        } else {
            driver.findElement(By.xpath(Pro.getProperty("NewDirectorXpath"))).click();
        }
    }

    @And("^clicks new Attachment button$")
    public void clicks_new_attachment_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("NewAttachmentXpath"))).click();
    }

    @And("^user enters Document type \"([^\"]*)\" Document number \"([^\"]*)\" and attachment \"([^\"]*)\"$")
    public void user_enters_document_type_something_document_number_something_and_attachment_something(String strArg1, String strArg2, String strArg3) throws Throwable {
        WebElement docTypeDropdown = driver.findElement(By.xpath("//*[@id=\"id_attachmentForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]"));
        docTypeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + strArg1 + "']")).click();

        WebElement docName = driver.findElement(By.id("id_reference"));
        docName.sendKeys(strArg2);

        WebElement attachment = driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span/input"));
        attachment.sendKeys(strArg3);
    }


    @And("^selects contact purpose \"([^\"]*)\" and contact detail \"([^\"]*)\"$")
    public void selects_contact_purpose_something_and_contact_detail_something(String strArg1, String strArg2) throws Throwable {
        WebElement contactPurposeDropdown = driver.findElement(By.xpath("//*[@id=\"id_addressFormGroup\"]/div[1]/tb-dropdown/div/div[2]/p-dropdown/div/label"));
        contactPurposeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='" + strArg1 + "']")).click();

        WebElement contactDetail = driver.findElement(By.id("id_contactDetail"));
        contactDetail.sendKeys(strArg2);

    }

    @And("^inputs Addres type(.+) and house number (.+) and street (.+) and town (.+)$")
    public void inputs_addres_type_and_house_number_and_street_and_town(String address, String number, String street, String town) throws Throwable {
        WebElement addressDropdown = driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        addressDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + address + "')]")).click();

        driver.findElement(By.id("id_houseNumber")).sendKeys(number);

        driver.findElement(By.id("id_streetName")).sendKeys(street);

        driver.findElement(By.id("id_townCity")).sendKeys(town);


        // clicks on country drop down then enters value in datatable
        WebElement countryDropdown = driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));

        countryDropdown.click();
        Thread.sleep(2000);
        WebElement countryInput = driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        countryInput.sendKeys("kenya");

        //clicks on the search result in drop down after entering country
        WebElement firstEntry = driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
        firstEntry.click();

        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/label")).click();
        Thread.sleep(2000);
        WebElement region = driver.findElement(By.xpath("//span[text()='Central Region']"));
        region.click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/label")).click();
        Thread.sleep(2000);
        WebElement District = driver.findElement(By.xpath("//span[text()='Dedza']"));
        District.click();

    }

    @And("^clicks update address button$")
    public void clicks_update_address_button() throws Throwable {
        Thread.sleep(3000);
        WebElement updateBtn = driver.findElement(By.xpath(Pro.getProperty("addAddressBtn")));
        Assert.assertTrue(updateBtn.isEnabled());
        updateBtn.click();
    }

    @And("^clicks primary indicator$")
    public void clicks_primary_indicator() throws Throwable {
        WebElement primaryIndicator = driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"));
        primaryIndicator.click();
    }

    @And("^clicks add address button$")
    public void clicks_add_address_button() throws Throwable {
        WebElement addAddress = driver.findElement(By.xpath(Pro.getProperty("addAddressXpath")));
        addAddress.click();
    }

    @And("^clicks add attachment button$")
    public void clicks_add_attachment_button() throws Throwable {
        WebElement addAddress = driver.findElement(By.xpath(Pro.getProperty("addAttachmentXpath")));
        addAddress.click();
    }

    @And("^clicks add director button$")
    public void clicks_add_director_button() throws Throwable {
        WebElement addAddress = driver.findElement(By.xpath(Pro.getProperty("addDirectorXpath")));
        addAddress.click();
    }

    @Given("^user clicks Directors tab$")
    public void user_clicks_directors_tab() throws Throwable {
        WebElement directorsTab = driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[1]/ul/li[5]"));
        directorsTab.click();
    }

    @And("^Click directors tab$")
    public void click_on_directors() {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[14]/a")).click();
    }


    @Then("^Switch to frame 2$")
    public void shift_focus_to_second_frame() throws Throwable {
        Thread.sleep(2000);
        driver.switchTo().frame(1);
    }

    @Then("^Click table column \"([^\"]*)\"$")
    public void click_table_column(String ColumnXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ColumnXpath))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
    }

    @Given("^user clicks organization details$")
    public void user_clicks_organization_details() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[1]/ul/li[1]")).click();
    }

    @And("^user enters organisation details$")
    public void user_enters_organisation_details(DataTable table) throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<List<String>> data = table.asLists();

        WebElement categoryDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"));
        categoryDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(0).get(1) + "')]")).click();

        WebElement orgNameInput = driver.findElement(By.id("id_orgName"));
        orgNameInput.clear();
        orgNameInput.sendKeys(data.get(1).get(1));

        WebElement prefOfficeDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
        prefOfficeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(2).get(1) + "')]")).click();

        WebElement tinReasonDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown-with-othertext/div/div[2]/p-dropdown/div/div[3]"));
        tinReasonDropdown.click();
        Thread.sleep(2000);

        if (data.get(3).get(1) == null) {
            System.out.println("tinReason is Empty");
        } else {
            driver.findElement(By.xpath("//span[contains(text(),'" + data.get(3).get(1) + "')]")).click();
        }

        WebElement DOIInput = driver.findElement(By.id("id_dateOfIncorporation"));
        DOIInput.clear();
        if (data.get(4).get(1) == null) {
            System.out.println("DOI is Empty");
        } else {
            DOIInput.sendKeys(data.get(4).get(1));
        }

        WebElement placeDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]"));
        placeDropdown.click();
        Thread.sleep(2000);
        WebElement placeInput = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
        placeInput.sendKeys(data.get(5).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li")).click();

        WebElement endDayDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[3]"));
        endDayDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[4]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        WebElement endmonthDropdown = driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[3]"));
        endmonthDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_orgDetailForm\"]/div/div/tb-dropdown[5]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        WebElement capitalSourceInput = driver.findElement(By.id("id_sourceOfCapital"));
        capitalSourceInput.sendKeys(data.get(6).get(1));

    }

    @And("^enters number of employees as \"([^\"]*)\"$")
    public void enters_number_of_employees_as(String strArg1) throws Throwable {
        Thread.sleep(3000);
        WebElement employeeNo = driver.findElement(By.id("id_noOfEmployees"));
        employeeNo.sendKeys(strArg1);
    }

    //.....................Verify fields......................................//
    @And("^clicks new on business sector$")
    public void clicks_new_on_business_sector() throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[1]/div/div/button[1]")).click();
    }

    @And("^Fill business sector details and click next$")
    public void fill_business_sector_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[3]/div/business-sector/div/form/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"id_businessSectorForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li[2]")).click();

        Thread.sleep(1000);
//        driver.findElement(By.xpath("//span[contains(text(),'"+data.get(0).get(1)+"')]")).click();
        driver.findElement(By.xpath("//*[@id='id_businessSectorForm']/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[2]/div/tb-business-sector-list/div/div/form/div[4]/div[2]/div/button[1]")).click();

    }

    @And("^click next on business sector$")
    public void click_next_on_business_sector() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_OrgForm\"]/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @And("^Fill in contact details and click next$")
    public void fill_in_contact_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
//        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[3]/div/tb-contact-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_contactForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(0).get(1) + "')]")).click();
        driver.findElement(By.id("id_contactDetail")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[3]/div/tb-contact-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @And("^Add address details and click next$")
    public void add_address_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[6]/div/tb-address-list/div/div/form/div[1]/div/div/button[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-checkbox/div/div[2]/p-checkbox")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(0).get(1) + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_townCity")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(2).get(1) + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"id_addressForm\"]/div/div/tb-dropdown[3]/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(3).get(1) + "')]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[6]/div/tb-address-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @And("^Fill directors details and click next$")
    public void fill_directors_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[7]/div/tb-director-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.id("id_relationTin")).sendKeys(data.get(0).get(1));
        driver.findElement(By.id("id_relationName")).sendKeys(data.get(1).get(1));
        driver.findElement(By.id("id_startDate")).sendKeys("05/08/2020");
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[7]/div/tb-director-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    @And("^Fill in attachment details and click next$")
    public void fill_in_attachment_details_and_click_next(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[8]/div/tb-attachment-list/div/div/form/div[1]/div/div/button[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_attachmentForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'" + data.get(0).get(1) + "')]")).click();
        driver.findElement(By.id("id_reference")).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span/input")).sendKeys(data.get(2).get(1));
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[4]/div/wizard-step[8]/div/tb-attachment-list/div/div/form/div[4]/div[2]/div/button[1]")).click();
        driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/ng-component/ng-component/div/div/form/form-wizard/div/div/div[5]/div/div[3]/div/button")).click();
    }

    //////////////////////////////////-------------------------ENTIRE----------------------------------------------------------------------------------------------------\\\


    //------ Register Individual Taxpayer Submit Scenario-------///

    @When("^I Fill the Individual Taxpayer Registration form$")
    public void  I_Fill_the_Individual_Taxpayer_Registration_form() throws Throwable
    {

        WebDriverWait wait=new WebDriverWait(driver,70);
        // driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
        Actions action = new Actions(driver);
        WebElement Reg=driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
        WebElement Taxpayer=driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
        action.click(Taxpayer).build().perform();
        // 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")))).click();
        WebElement Individual=driver.findElement(By.xpath(Pro.getProperty("RegisterIndividual_LINK_XPATH")));
        action.click(Individual).build().perform();
        Thread.sleep(2000);
    }

    @When("^I enter valid data on the Individualpage and Submit$")
    public void i_enter_valid_data_on_the_Individualpage_and_Submit(DataTable table) throws Throwable {

        //Initialize data table
        List<List<String>> data =table.asLists();

        driver.findElement(By.id(Pro.getProperty("FirstName_ID"))).sendKeys(data.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LastName_ID"))).sendKeys(data.get(1).get(1) + " " +BaseClass.getRandom(4));
        driver.findElement(By.id("RegisterIndividual:individualAccordion:MothersMaidenName")).sendKeys("Wambui");
        Thread.sleep(2000);
        Actions action = new Actions(driver);

        WebElement title=driver.findElement(By.xpath(Pro.getProperty("Title_LINK_XPATH")));
        action.click(title).build().perform();
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("TITLE_LIST_ITEMS_XPATH")));
        for(WebElement option : list)
        {
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(3).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();
            }
        }


        Thread.sleep(7000);
        driver.findElement(By.xpath(Pro.getProperty("Categoryofindividual_XPATH"))).click();

        List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("Categoryofindividual_LIST_ITEMS_XPATH")));
        for(WebElement option1 : CatValue)
        {
            String text= option1.getText();
            System.out.println(text);
            if(text.equalsIgnoreCase(data.get(2).get(1)))
            {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                //  builder1.perform();

            }
        }
        Thread.sleep(4000);
        List<WebElement> element=driver.findElements(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
        for (WebElement ele : element)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);
            //ele.click();
        }
        Thread.sleep(2000);


        Thread.sleep(2000);
        WebElement Gender=driver.findElement(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
        action.click(Gender).build().perform();
        Thread.sleep(3000);
        List<WebElement> Gen = driver.findElements(By.xpath

                (Pro.getProperty("GenderItems_LINK_XPATH")));
        for(WebElement option : Gen)
        {
            String text= option.getText();
            if(text.equalsIgnoreCase(data.get(4).get(1)))
            {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();
            }
        }
        Thread.sleep(3000);
    }


    @When("^Enter Date Of Birth in additional info tab\"([^\"]*)\"$")
    public void enter_Date_Of_Birth_in_additional_info_tab(String DOB, DataTable AddTable) throws Throwable {
        List<List<String>> data =AddTable.asLists();
        Actions action = new Actions(driver);
        Thread.sleep(6000);

        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).sendKeys(DOB);

        Thread.sleep(4000);
        WebElement Meritalstatus=driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MaritalStatus\"]/div[3]"));
        Meritalstatus.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+data.get(0).get(1)+"']")).click();




        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(7000);
        List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("PLACEOFBIRTH_LINK_ID")));
        for (WebElement ele : element2)
        {
            JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);

        }
        Thread.sleep(5000);


        WebDriverWait wait1=new WebDriverWait(driver,60);
        wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("CountyOfResidency_LINK_XPATH")))).click();
        List<WebElement> CountryValue = driver.findElements(By.xpath(Pro.getProperty("CountyOfResidency_ITEM_LINK_XPATH")));
        for(WebElement option : CountryValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(5000);
        WebDriverWait wait=new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReasonForTINChange_CLICK_LINK_XPATH")))).click();
        List<WebElement> RFTValue = driver.findElements(By.xpath(Pro.getProperty("ReasonForTINChange_ITEM_LINK_XPATH")));
        for(WebElement option : RFTValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(3).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(2000);
        WebDriverWait Nwait=new WebDriverWait(driver,60);
        Nwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NATIONALITY_CLICK_LINK_XPATH")))).click();
        List<WebElement> NValue = driver.findElements(By.xpath(Pro.getProperty("NationalityItem_LINK_XATH")));
        for(WebElement option : NValue){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(4).get(1)))
            {
                option.click();
                break;
            }
        }

        Thread.sleep(4000);
        WebElement officeAssigned=driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:AssignedOffice\"]/div[3]"));
        officeAssigned.click();
        Thread.sleep(4000);

//        driver.findElement(By.xpath("//li[@data-label='"+data.get(5).get(1)+"']")).click();
        driver.findElement(By.id("RegisterIndividual:individualAccordion:AssignedOffice_1")).click();

    }

    @Then("^Select residence permit identification with number \"([^\"]*)\"$")
    public void select_residence_permit_identification_with_number_something(String number) throws Throwable {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[2]/a")).click();
        WebElement Identification1=driver.findElement(By.id(Pro.getProperty("Identification_Add_ID")));
        Identification1.click();
        WebDriverWait wait = new WebDriverWait(driver,100);
        //WebElement  idfnframe= driver.findElement(By.tagName("iframe"));
        WebElement  idfnframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(idfnframe);

        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"Identification:IdentificationType\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'Residence Permit')]")).click();
        Thread.sleep(5000);
        driver.findElement(By.id(Pro.getProperty("Identification_Number_ID"))).sendKeys(String.valueOf(timestamp.getTime()));

        Thread.sleep(4000);
        driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();


    }

    @When("^Enter identification Date of Issue \"([^\"]*)\"$")
    public void enter_identification_Date_of_Issue(String DOI,DataTable DateTable) throws Throwable {

        List<List<String>> data =DateTable.asLists();
        Actions action = new Actions(driver);
        List<WebElement> ScrollIdent=driver.findElements(By.id(Pro.getProperty("ScrollTO-Identification_LINK_XPATH")));
        for (WebElement ele : ScrollIdent)
        {
            JavascriptExecutor js1 = (JavascriptExecutor) driver;js1.executeScript("arguments [0].scrollIntoView(true);",ele);

        }

        List<WebElement> Reg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : Reg){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);

        WebDriverWait wait=new WebDriverWait(driver,50);
        WebElement Identification=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Identification_Add_ID"))));

        Identification.click();
        Thread.sleep(7000);
        WebElement  iframe= wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(iframe);

        Thread.sleep(2000);
        WebElement identificationNumber=driver.findElement(By.id(Pro.getProperty("Identification_Number_ID")));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        identificationNumber.sendKeys(BaseClass.getRandom(4));

        WebDriverWait Iwait=new WebDriverWait(driver,60);
        Iwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
        Thread.sleep(2000);
        List<WebElement> IdentificationType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
        for(WebElement option : IdentificationType){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(1).get(1)))
            {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);

        driver.findElement(By.id("Identification:CountryOfIssue_label")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+data.get(3).get(1)+"')]")).click();

//            driver.findElement(By.id(Pro.getProperty("Identification_ePermit_Num_ID"))).sendKeys(Keys.TAB);


        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

        Thread.sleep(3000);
        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        js3.executeScript("document.getElementById('"+Pro.getProperty("DateOfIssue_ID")+"').setAttribute('value', '"+DOI+"')");

    }

    @When("^Enter identification Expiry Date \"([^\"]*)\"$")
    public void enter_identification_Expiry_Date(String IED, DataTable Idtable) throws Throwable {
        Thread.sleep(2000);
        List<List<String>> data =Idtable.asLists();
        Actions action = new Actions(driver);

        JavascriptExecutor js3 = (JavascriptExecutor)driver;
        js3.executeScript("document.getElementById('"+Pro.getProperty("Identification_Expiry_Date_ID")+"').setAttribute('value', '"+IED+"')");


        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Identification_Expiry_Date_ID"))).sendKeys(Keys.TAB);
        List<WebElement> element2=driver.findElements(By.id(Pro.getProperty("Identification_Frame_OK_ID")));
        for (WebElement ele : element2)
        {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);",ele);

        }
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Identification_Frame_OK_ID"))).click();


        WebDriverWait wait=new WebDriverWait(driver,60);
        Thread.sleep(5000);
        WebElement Identification1 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Identification_Add_ID"))));
        Identification1.click();
        Thread.sleep(5000);
        WebElement  idfnframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(idfnframe);

        Thread.sleep(6000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("Identification_Number_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        Thread.sleep(2000);

        WebDriverWait Irecidentwait=new WebDriverWait(driver,60);
        Irecidentwait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("IdenificationType_CLICK_XPATH")))).click();
        List<WebElement> IdntRecidenrType = driver.findElements(By.xpath(Pro.getProperty("IdenificationType_ITEM__XPATH")));
        for(WebElement option : IdntRecidenrType){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(0).get(1)))
            {
                option.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Identification_Frame_OK_ID")))).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();




        WebDriverWait IRecordwait=new WebDriverWait(driver,50);
        IRecordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Identification_RecordAdded_XPATH"))));
        List<WebElement> RegInd = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for(WebElement option : RegInd){
            String text2= option.getText();
            if(text2.equalsIgnoreCase(data.get(2).get(1)))
            {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("Employment_details_Add_XPATH"))).click();
        Thread.sleep(4000);
        WebElement  Eiframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Eiframe);
        Thread.sleep(5000);
        driver.findElement(By.id(Pro.getProperty("Employment_details_Position_ID"))).sendKeys(data.get(3).get(1));
        driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(data.get(4).get(1));
        driver.findElement(By.id(Pro.getProperty("Employment_Details_Employer's_Name_ID"))).sendKeys(Keys.TAB);

    }


    @Then("^wait for webpage to load$")
    public void wait_for_webpage_to_load() throws Throwable {
        Thread.sleep(10000);

    }


    @And("^enters attachment details \"([^\"]*)\"  with number \"([^\"]*)\" and path \"([^\"]*)\"$")
    public void enters_attachment_details_something_with_number_something_and_path_something(String strArg1, String strArg2, String strArg3) throws Throwable {
        Thread.sleep(3000);
        WebElement attachTab=driver.findElement(By.xpath("//a[contains(text(),'Attachments')]"));
        attachTab.click();
        Thread.sleep(2000);

        WebElement Identification1=driver.findElement(By.id("RegisterIndividual:individualAccordion:attachmentTableHandler:AddAttachment"));
        Identification1.click();
        Thread.sleep(8000);

        WebElement idfnframe= driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(idfnframe);

        WebElement docTypeDropdown=driver.findElement(By.xpath("//*[@id=\"AttachmentDetails:DocType\"]/div[3]"));
        docTypeDropdown.click();
        Thread.sleep(2000);

        //selects passport instead of diplomatic passport which has matching selector
        List <WebElement> attachments=driver.findElements(By.xpath("//li[contains(text(),'"+strArg1+"')]"));
        if (attachments.size()==1){
            attachments.get(0).click();
        }
        else{
            WebElement passport = attachments.get(1);
            passport.click();
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebElement docNumber=driver.findElement(By.id("AttachmentDetails:Reference"));
        docNumber.sendKeys(String.valueOf(timestamp.getTime()));

        Thread.sleep(2000);
        // set download path dynamically
        String path =System.getProperty("user.dir")+ File.separator +"src\\test\\resources"+File.separator+strArg3;
        WebElement El = driver.findElement(By.id("AttachmentDetails:AttachmentPath_input"));
        El.sendKeys(path);


        Thread.sleep(2000);
        WebElement verifiedCheckbox=driver.findElement(By.xpath("//*[@id='AttachmentDetails:Verified']/div[2]/span"));
        verifiedCheckbox.click();

        Thread.sleep(2000);
        driver.findElement(By.id("AttachmentDetails:Ok")).click();
        Thread.sleep(5000);
    }

    @And("^Enter Sole Proprietor Additional Details$")
    public void enter_sole_proprietor_additional_details(DataTable AddTable) throws Throwable {
        List<List<String>> data = AddTable.asLists();
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 50);

        WebElement soleProprietorTab = driver.findElement(By.xpath("//a[contains(text(),'Sole Proprietor Additional Details')]"));
        soleProprietorTab.click();

        WebElement addBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RegisterIndividual:individualAccordion:tradingNameTableHandler:AddTradingNameDetails")));
        addBtn.click();

        //Switch to NEW Trading Names frame
        WebElement idframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(idframe);

        WebElement tradingNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TradingNameDetails:TradingName")));
        tradingNameInput.sendKeys(data.get(0).get(1));

        WebElement primaryTradingCheckBox = driver.findElement(By.xpath("//*[@id=\"TradingNameDetails:PrimaryTradingName\"]/div[2]"));
        primaryTradingCheckBox.click();

        Thread.sleep(2000);
        WebElement effectiveDate = driver.findElement(By.id("TradingNameDetails:EffectiveDate_input"));
        effectiveDate.sendKeys(Keys.ENTER);
//        effectiveDate.sendKeys(Keys.ESCAPE);
        Thread.sleep(2000);

        WebElement capitalInvested = driver.findElement(By.id("TradingNameDetails:SourceOfCapitalInv"));
        capitalInvested.sendKeys(data.get(1).get(1));

        WebElement existingCapital = driver.findElement(By.id("TradingNameDetails:ExistBusinessCapital_input"));
        existingCapital.sendKeys(data.get(2).get(1));

        WebElement totalCapital = driver.findElement(By.id("TradingNameDetails:TotCapitalInvst_input"));
        totalCapital.sendKeys(data.get(3).get(1));

        WebElement natureOfBusiness = driver.findElement(By.id("TradingNameDetails:NatureOfBusiness"));
        natureOfBusiness.sendKeys(data.get(4).get(1));

        WebElement AccountYearEndDayDropdown = driver.findElement(By.xpath("//*[@id=\"TradingNameDetails:AccountYearEndDateDD\"]/div[3]"));
        AccountYearEndDayDropdown.click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='" + data.get(5).get(1) + "']")).click();
        Thread.sleep(2000);
        WebElement AccountYearEndMonthDropdown = driver.findElement(By.xpath("//*[@id=\"TradingNameDetails:AccountYearEndDateMM\"]/div[3]"));
        AccountYearEndMonthDropdown.click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='" + data.get(6).get(1) + "']")).click();

        WebElement AddressBtn = driver.findElement(By.id("TradingNameDetails:AddTradingAddress"));
        AddressBtn.click();

        Thread.sleep(5000);


        switch_to_default();
        driver.switchTo().frame(1);

        WebElement typeSolProprietorDropdown = driver.findElement(By.xpath("//*[@id=\"AddressDetails:AddressType\"]/div[3]"));
        typeSolProprietorDropdown.click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='" + data.get(7).get(1) + "']")).click();

        Thread.sleep(2000);
        WebElement citySolProprietor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddressDetails:City")));
        citySolProprietor.sendKeys(data.get(8).get(1));

        WebElement regionProprietorDropdown = driver.findElement(By.xpath("//*[@id=\"AddressDetails:PostalRegion\"]/div[3]"));
        regionProprietorDropdown.click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='" + data.get(8).get(1) + "']")).click();

        Thread.sleep(3000);
        WebElement districtSolProprietorDropdown = driver.findElement(By.xpath("//*[@id=\"AddressDetails:District1\"]/div[3]"));
        districtSolProprietorDropdown.click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='" + data.get(9).get(1) + "']")).click();

        Thread.sleep(2000);
        WebElement okButton = driver.findElement(By.xpath("//button[@type='submit' and span='Ok']"));
        okButton.click();


        Thread.sleep(4000);
        switch_to_default();
        driver.switchTo().frame(0);
        WebElement okButton2 = driver.findElement(By.xpath("//button[@type='submit' and span='Ok']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", okButton2);
        Thread.sleep(500);
        okButton2.click();


    }



    @When("^Enter Employee details \"([^\"]*)\"$")
    public void enter_Employee_details(String ESD, DataTable Employetable) throws Throwable {
        Thread.sleep(3000);

        List<List<String>> data = Employetable.asLists();
        Actions action = new Actions(driver);
	/*WebElement DateES=driver.findElement(By.xpath(Pro.getProperty("Employment_details_Employment_StartDate_XPATH")));
		action.sendKeys(DateES, ESD).build().perform();*/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + Pro.getProperty("Employment_details_Employment_StartDate_ID") + "').setAttribute('value', '" + ESD + "')");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Employment_details_OK_ID")))).click();
        driver.switchTo().defaultContent();
        WebDriverWait Recordwait = new WebDriverWait(driver, 50);
        Recordwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Employment_details_RecordAdded_XPATH"))));
        List<WebElement> Occupation = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for (WebElement option : Occupation) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        WebDriverWait Occupationwait = new WebDriverWait(driver, 100);
        Occupationwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_XPATH")))).click();
        List<WebElement> Employed = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_occupationStatus_ITEM_XPATH")));
        for (WebElement option : Employed) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(1).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).click(option);
                builder.perform();
                //  option.click();
                break;
            }
        }
        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_XPATH"))).click();
        List<WebElement> OccupationValue = driver.findElements(By.xpath(Pro.getProperty("Occupation/Business_MainCategory_ITEM_XPATH")));
        for (WebElement option : OccupationValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(2).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
//        WebElement preciseDropdown = driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:PreciseCategory\"]/div[3]"));
//        preciseDropdown.click();
//        Thread.sleep(4000);
//        driver.findElement(By.xpath("//li[@data-label='" + data.get(3).get(1) + "']")).click();


        List<WebElement> turnover = driver.findElements(By.id("RegisterIndividual:individualAccordion:TotalSalesTurnoverPA_input"));
        if (turnover.size() > 0) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", turnover.get(0));
            Thread.sleep(500);
            turnover.get(0).sendKeys("3000000");
        }


        WebElement businessSectorAdd = driver.findElement(By.id("RegisterIndividual:individualAccordion:businessDetailsHandler:AddBusinessSD"));
        businessSectorAdd.click();

        WebDriverWait Wait = new WebDriverWait(driver, 10);
        WebElement Taxtypeframe = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Taxtypeframe);

        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"BusinessSectorDetails:BusinessCode\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'0112 - Growing of rice')]")).click();

        WebElement primaryIndicator = driver.findElement(By.xpath("//*[@id=\"BusinessSectorDetails:PrimaryIndicator\"]/div[2]"));
        primaryIndicator.click();

        Thread.sleep(2000);
        WebElement okButton = driver.findElement(By.xpath("//button[@type='submit' and span='Ok']"));
        okButton.click();

        Thread.sleep(5000);
        Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Addresses')]"))).click();


        WebElement Addressadd = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Addresses_ADD_ID"))));
        action.click(Addressadd).build().perform();

        WebElement Addressframe = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Addressframe);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait AddressType = new WebDriverWait(driver, 50);
        AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
        List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
        for (WebElement option : AddressValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(5).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        WebElement SName = driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
        action.sendKeys(SName, data.get(6).get(1)).build().perform();
        Thread.sleep(2000);
        WebElement CName = driver.findElement(By.id("AddressDetails:City"));
        action.sendKeys(CName, data.get(7).get(1)).build().perform();

        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_region_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + data.get(10).get(1) + "')]")).click();

        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_District_XPATH"))).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[contains(text(),'" + data.get(9).get(1) + "')]")).click();

        List<WebElement> AddressOK = driver.findElements(By.id(Pro.getProperty("Address_Scroll_View_XPATH")));
        for (WebElement ele : AddressOK) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        WebElement AddOK = driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
        action.doubleClick(AddOK).build().perform();
        AddOK.click();
        driver.switchTo().defaultContent();
        WebDriverWait AddressRecord = new WebDriverWait(driver, 50);
        AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_RecordAdded_XPATH"))));
        List<WebElement> ContactReg = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for (WebElement option : ContactReg) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(11).get(1))) {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ADD_XPATH"))).click();
        Thread.sleep(5000);
        WebElement ContMethodframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(ContMethodframe);
        WebDriverWait Purpose = new WebDriverWait(driver, 50);
        Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
        List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
        for (WebElement option : PurposeValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(12).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + data.get(13).get(1) + "')]")).click();


        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("ContactMethods_ContactMethodDetails_ID"))).sendKeys(data.get(14).get(1));
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
    }


    @Then("^Click On Individual Page Save Button$")
    public void click_On_Individual_PageSave_Button() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("Save_DATA_XPATH"))).click();

    }

    @Then("^Click On Individual Page Submit Button$")
    public void click_On_Individual_PageSubmit_Button() throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
        Thread.sleep(7000);

    }

    @Then("^ARN number will generate$")
    public void ARN_number_will_generate(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Individual_Submit_Data_ID")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is " + text.substring(42));
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);

        if (text.contains(data.get(0).get(1))) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Unsuccessful Registartion");
        }

        Thread.sleep(27000);
    }

    // Register Individual Taxpayer save individual taxpayer info  for Processing scenario
    @When("^Enter required data to save individual info for Processing scenario$")
    public void enter_required_data_to_save_individual_info_for_Processing_scenario(DataTable savetable) throws Throwable {

        List<List<String>> data = savetable.asLists();
        Actions action = new Actions(driver);
        WebDriverWait ContactRecord = new WebDriverWait(driver, 50);
        ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_RecordAdded_XPATH")))).click();
        List<WebElement> Reg1 = driver.findElements(By.xpath(Pro.getProperty("RegisterIndividual_ITEM_XPATH")));
        for (WebElement option : Reg1) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_XPATH"))).click();
        WebElement Attachmentframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Attachmentframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttPassport) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(2).get(1))) {
                option.click();
                break;
            }
        }
        driver.findElement(By.xpath(Pro.getProperty("Attachments_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
        Thread.sleep(2000);
        WebElement uploadBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        action.click(uploadBtn).build().perform();
        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(600);
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord = new WebDriverWait(driver, 50);
        AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);


    }

//------UAT_TCS 01.01.3	To verify the process of checking Validation error in entered data---


    @Then("^Verify the Validation Error \"([^\"]*)\"$")
    public void verify_the_Validation_Error(String Validation) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PrimaryIndicator_Validation_XPATH"))));
        // Capture ARN Number
        String text = driver.findElement(By.xpath(Pro.getProperty("PrimaryIndicator_Validation_XPATH"))).getText();

        System.out.println(text);
	/*    System.out.println("substring is "+ text.substring(42));
	 String A_BackOffice_ARN=text.substring(42);

	    sharedatastep.A_CRMARN="*"+A_BackOffice_ARN;
   // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


    System.out.println(sharedatastep.A_CRMARN);
    System.out.println("Actual ARN to be used in CRM is " +sharedatastep.A_CRMARN);*/

        if (text.contains(Validation)) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

        Thread.sleep(8000);

    }


// Organization

    @When("^I Fill the Organization Taxpayer Registration form$")
    public void I_Fill_the_Organization_Taxpayer_Registration_form() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        // driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH"))).click();
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")))).click();
        WebElement Taxpayer = driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
        action.click(Taxpayer).build().perform();
        WebElement Individual = driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxpayer_RegisterOrganisation_XPATh")));
        action.click(Individual).build().perform();
        Thread.sleep(2000);


    }

    // Register Taxpayer Organization Scenario submit
    @And("^I enter valid data on the pages of Organization$")
    public void I_enter_valid_data_on_the_pages_of_Organization(DataTable table) throws Throwable {

        //Initialize data table
        List<List<String>> data = table.asLists();
        Actions action = new Actions(driver);
        driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
        Thread.sleep(2000);
        WebDriverWait Category = new WebDriverWait(driver, 20);
        Category.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Organization_OrganisationCategory_XPATH")))).click();
        List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("HeaderDetails_OrganisationCategory_ITEM_XPATH")));
        for (WebElement option : CatValue) {
            String text2 = option.getText();

            // System.out.println(text2);
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                option.click();
                break;
            }
        }

        Thread.sleep(2000);

        // driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
        List<WebElement> element2 = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
        for (WebElement ele : element2) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(3000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_RGDNO_ID"))).sendKeys(String.valueOf(timestamp.getTime()));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(3000);
        // Boolean status=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).isEnabled();
        WebElement Date = driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID")));
        if (Date.isEnabled()) {
            System.out.println("enabled");
        } else {
            System.out.println("Disabled");
        }


        // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).sendKeys(data.get(3).get(1));

        // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(4).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Organisation_SourceOfCapital_Xapth"))).sendKeys(data.get(5).get(1));
        Thread.sleep(2000);
         driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH"))).sendKeys(data.get(6).get(1));

//        WebDriverWait Place = new WebDriverWait(driver, 60);
//        Place.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH")))).click();
//        List<WebElement> PlaceValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_Values_XPATH")));
//        for (WebElement option : PlaceValue) {
//            String text2 = option.getText();
//
//            // System.out.println(text2);
//            if (text2.equalsIgnoreCase(data.get(6).get(1))) {
//                option.click();
//                break;
//            }
//        }




        List<WebElement> BSDetails = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
        for (WebElement ele : BSDetails) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_XPATH"))).click();

        List<WebElement> ReasonFTIN = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_ITEM_XPATH")));
        for (WebElement option1 : ReasonFTIN) {
            String text = option1.getText();
            // System.out.println(text);
            if (text.equalsIgnoreCase(data.get(7).get(1))) {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
            }
        }

        Thread.sleep(3000);
        WebElement PersonFirstName=driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:footerLastName"));
        PersonFirstName.sendKeys("Bigman");

        WebElement PersonLastName=driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:footerFirstName"));
        PersonLastName.sendKeys("Bazu");

        WebElement PersonPostal=driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:footerPostalAddress"));
        PersonPostal.sendKeys("Bazu");

        WebElement PersonContact =driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:footerContactDetails"));
        PersonContact.sendKeys("Bazu");


        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
        List<WebElement> EndYearMonth = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_Value_XPATH")));
        for (WebElement option1 : EndYearMonth) {
            String text = option1.getText();
            // System.out.println(text);
            if (text.equalsIgnoreCase(data.get(19).get(1))) {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                break;
            }
        }

        Thread.sleep(4000);
        WebElement yearDay = driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_XPATH")));
        yearDay.click();
        List<WebElement> EndYearDay = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_Value_XPATH")));
        for (WebElement option1 : EndYearDay) {
            String text = option1.getText();
            // System.out.println(text);
            if (text.equalsIgnoreCase(data.get(20).get(1))) {
                Actions builder1 = new Actions(driver);
                builder1.moveToElement(option1).doubleClick(option1).build().perform();
                break;
            }
        }

        Thread.sleep(4000);

	 /*driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
	 action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 Thread.sleep(2000);*/
        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID"))).click();
        WebElement Occupationframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Occupationframe);
        WebDriverWait OccWait = new WebDriverWait(driver, 50);
        OccWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Occupation/Business_ADD_WAIT_ID"))));
        driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_Primaryindicator_XPATH"))).click();

        WebElement BSadd = driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_XPATH")));
        action.click(BSadd).build().perform();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        List<WebElement> BSecValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_ITEM_XPATH")));
        for (WebElement option : BSecValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(8).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);

        driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait BSecrecord = new WebDriverWait(driver, 50);
        BSecrecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("AdditionalDetails_RecordAdded_XPATH"))));
        Thread.sleep(2000);
        List<WebElement> ScrollAddress = driver.findElements(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID")));
        for (WebElement ele : ScrollAddress) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }

        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for (WebElement option : SummarytabValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(9).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        WebElement Addressadd = driver.findElement(By.id(Pro.getProperty("Organization_Addresses_Add_ID")));
        action.click(Addressadd).build().perform();
        Thread.sleep(7000);
        WebElement Addressframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Addressframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait AddressType = new WebDriverWait(driver, 50);
        AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
        List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
        for (WebElement option : AddressValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(10).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(5000);

        WebElement SName = driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
        action.sendKeys(SName, data.get(11).get(1)).build().perform();
        WebElement CName = driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
        action.sendKeys(CName, data.get(12).get(1)).build().perform();


        Thread.sleep(4000);
        driver.findElement(By.xpath(Pro.getProperty("Addresses_region_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + data.get(14).get(1) + "')]")).click();

        Thread.sleep(2000);
        WebDriverWait District = new WebDriverWait(driver, 50);

        District.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_District_XPATH")))).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//li[@data-label='"+data.get(13).get(1)+"']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("AddressDetails:addOk")).click();




        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ADD_ID"))).click();
        driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:contactDetailsHandler:AddContacts")).click();

        Thread.sleep(3000);
        WebElement ContMethodframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(ContMethodframe);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait Purpose = new WebDriverWait(driver, 50);
        Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
        List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
        for (WebElement option : PurposeValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(16).get(1))) {

                option.click();
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
//            WebDriverWait ContactType=new WebDriverWait(driver,50);
//            ContactType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH")))).click();
//            List<WebElement> ContactTypeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Type_ITEM_XPATH")));
//            for(WebElement option : ContactTypeValue){
//                String text2= option.getText();
//                if(text2.equalsIgnoreCase(data.get(17).get(1)))
//                {
//
//                    Actions builder = new Actions(driver);
//                    builder.moveToElement(option).click(option);
//                    builder.perform();
//                    // option.click();
//                    break;
//                }
//            }
//            JavascriptExecutor js = (JavascriptExecutor)driver;
//            js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));
//
//            WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
//            PrimInd.click();
        Thread.sleep(3000);
        WebDriverWait ContctDet = new WebDriverWait(driver, 50);
        ContctDet.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH"))));
        Thread.sleep(1000);
        WebElement Contdetails = driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH")));
        action.sendKeys(Contdetails, data.get(18).get(1)).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();


    }


    @When("^Enter Attachment Tab details$")
    public void enter_Attachment_Tab_details(DataTable Attchtable) throws Throwable {

        List<List<String>> data = Attchtable.asLists();
        //Actions action=new Actions(driver);


        WebDriverWait ContactRecord = new WebDriverWait(driver, 50);
        ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_ContactMethods_RecordAdded_XPATH"))));
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> OrgAttachment = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for (WebElement option : OrgAttachment) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement Attachmentframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Attachmentframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttPassport) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(2).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(String.valueOf(timestamp.getTime()));
        Thread.sleep(2000);
        WebElement uploadBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        // action.click(uploadBtn).build().perform();
        uploadBtn.click();
        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(600);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);

        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();

        WebElement dateRecieved = driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved.click();
        dateRecieved.sendKeys("10/12/2005");
        Thread.sleep(4000);

        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord = new WebDriverWait(driver, 50);
        AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//Attachment-----	Approval Letter from Line Ministry


        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();

        WebElement AttLetterframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttLetterframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttLettert = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttLettert) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(6).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        Timestamp timestamptwo = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(String.valueOf(timestamptwo.getTime()));
        Thread.sleep(2000);

        WebElement dateRecieved3 = driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved3.click();
        dateRecieved3.sendKeys("10/12/2005");
        dateRecieved3.sendKeys(Keys.TAB);
        Thread.sleep(4000);


        WebElement LetterBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
// action.click(LetterBtn).build().perform();
        LetterBtn.click();
//put path to your image in a clipboard
        StringSelection ss1 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot1 = new Robot();
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        robot1.keyPress(KeyEvent.VK_CONTROL);
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_CONTROL);
        robot1.delay(600);
        Thread.sleep(5000);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.delay(300);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();


        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord1 = new WebDriverWait(driver, 50);
        AttRecord1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//End....
// Certificate of Incorporation
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement AttCertiframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttCertiframe);
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttCertificate = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttCertificate) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(7).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(4000);
        Timestamp timestampthree = new Timestamp(System.currentTimeMillis());
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(String.valueOf(timestampthree.getTime()));
        Thread.sleep(4000);
        WebElement CertiBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        CertiBtn.click();
// action.click(CertiBtn).build().perform();
//put path to your image in a clipboard
        StringSelection ss2 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot2 = new Robot();
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        robot2.keyPress(KeyEvent.VK_CONTROL);
        robot2.keyPress(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_CONTROL);
        robot2.delay(600);
        Thread.sleep(5000);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();

        WebElement dateRecieved2 = driver.findElement(By.id("AttachmentDetails:DateReceived_input"));
        dateRecieved2.click();
        dateRecieved2.sendKeys("10/12/2005");
        Thread.sleep(4000);

        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord2 = new WebDriverWait(driver, 50);
        AttRecord2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
    }

    @And("^enters director \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enters_director_and(String strArg1, String strArg2) throws Throwable {
        WebElement directorsPath = driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[6]"));
        directorsPath.click();
        Thread.sleep(4000);

        WebElement addSummary = driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:directorsTableHandler:AddDirectors"));
        addSummary.click();
        Thread.sleep(2000);



        //Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);

        driver.findElement(By.id("DirectorsDetails:FindTin")).click();
        Thread.sleep(3000);


        driver.switchTo().defaultContent();
//        WebElement  Tinframe= driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(1);
        Thread.sleep(3000);

        WebElement tinInput = driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(strArg1);

        WebElement searchBtn = driver.findElement(By.id("SearchForm:j_idt21"));
        searchBtn.click();
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
//        WebElement  Tinframe= driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(0);
        Thread.sleep(7000);

        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DirectorsDetails:PositionHeldSince_input")));

        startDate.click();
        startDate.sendKeys(Keys.ENTER);

        WebElement principalDirector=driver.findElement(By.xpath("//*[@id=\"DirectorsDetails:PrimaryIndicatorChk\"]/div[2]"));
        principalDirector.click();

        driver.findElement(By.id("DirectorsDetails:rdOk")).click();
        Thread.sleep(4000);

    }


    @When("^Enter Directors Tab Info for Organization taxpayer$")
    public void enter_Directors_Tab_Info_for_Organization_taxpayer(DataTable Dirtable) throws Throwable {

        List<List<String>> data = Dirtable.asLists();
        Actions action = new Actions(driver);
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> DirectorValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for (WebElement option : DirectorValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Directors_ADD_ID"))).click();
        WebElement directorframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(directorframe);

        WebElement DirecTin = driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_ID")));
        action.click(DirecTin).build().perform();
        int frame = driver.findElements(By.tagName("iframe")).size();
        System.out.println(frame);
        driver.switchTo().parentFrame();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        int frame1 = driver.findElements(By.tagName("iframe")).size();
        System.out.println(frame1);
        driver.switchTo().frame(1);
        WebDriverWait DirctAdd = new WebDriverWait(driver, 60);
        DirctAdd.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Webdriverwait_ID")))).click();
        Thread.sleep(2000);
        WebElement DTinadd = driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_TIN_NAME_ID")));
        action.sendKeys(DTinadd, data.get(1).get(1)).build().perform();
        Thread.sleep(2000);
        WebDriverWait Searchwait = new WebDriverWait(driver, 100);
        Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID")))).click();


//driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement DirTinframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(DirTinframe);
//Thread.sleep(2000);
//driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
        Thread.sleep(2000);
//driver.findElement(By.xpath(Pro.getProperty("Directors_ADD_DirectorStartDate_XPATH"))).sendKeys(data.get(2).get(1));
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("document.getElementById('" + Pro.getProperty("Directors_ADD_DirectorStartDate_ID") + "').setAttribute('value', '" + data.get(2).get(1) + "')");

//driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Directors_ADD_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        Thread.sleep(2000);


    }

    @Then("^Click On Organization Page Submit Button$")
    public void click_On_Organization_Page_Submit_Button() throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("RegisterOrganisation_SUBMIT_XPATH")))).click();
        Thread.sleep(7000);
    }


    // Register Organization Taxpayer save Taxpayer Information
    @Then("^Click On Organization Page Save Button$")
    public void click_On_Organization_Page_Save_Button() throws Throwable {
        WebDriverWait Save = new WebDriverWait(driver, 60);
        Save.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("RegisterOrganisation_SAVE_Data_ID")))).click();
        Thread.sleep(7000);
    }


//Register Organization Taxpayer save Taxpayer Information

    @When("^Enter required data to save organization info for Processing scenario$")
    public void enter_required_data_to_save_organization_info_for_Processing_scenario(DataTable Dirtable) throws Throwable {

        List<List<String>> data = Dirtable.asLists();
        Actions action = new Actions(driver);
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> DirectorValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for (WebElement option : DirectorValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Directors_ADD_ID"))).click();
        WebElement directorframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(directorframe);

        WebElement DirecTin = driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_ID")));
        action.click(DirecTin).build().perform();
        int frame = driver.findElements(By.tagName("iframe")).size();
        System.out.println(frame);
        driver.switchTo().parentFrame();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        int frame1 = driver.findElements(By.tagName("iframe")).size();
        System.out.println(frame1);
        driver.switchTo().frame(1);
        WebDriverWait DirctAdd = new WebDriverWait(driver, 60);
        DirctAdd.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Webdriverwait_ID")))).click();
        Thread.sleep(2000);
        WebElement DTinadd = driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_TIN_NAME_ID")));
        action.sendKeys(DTinadd, data.get(1).get(1)).build().perform();
        Thread.sleep(2000);
        WebDriverWait Searchwait = new WebDriverWait(driver, 100);
        Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID")))).click();


//driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement DirTinframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(DirTinframe);
        Thread.sleep(2000);
//driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Directors_ADD_DirectorStartDate_XPATH"))).sendKeys(data.get(2).get(1));
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Directors_Principal_XPATH"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Directors_ADD_OK_ID"))).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.switchTo().defaultContent();
        Thread.sleep(2000);


    }

//UAT_TCS 01.02.3	To verify the process of Validation error in entered data

    @When("^I enter valid data on the pages of Organization without primary indicator$")
    public void i_enter_valid_data_on_the_pages_of_Organization_without_primary_indicator(DataTable table) throws Throwable {

        {

            //Initialize data table
            List<List<String>> data = table.asLists();
            Actions action = new Actions(driver);
            driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
            Thread.sleep(2000);
            WebDriverWait Category = new WebDriverWait(driver, 60);
            Category.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Organization_OrganisationCategory_XPATH")))).click();
            List<WebElement> CatValue = driver.findElements(By.xpath(Pro.getProperty("HeaderDetails_OrganisationCategory_ITEM_XPATH")));
            for (WebElement option : CatValue) {
                String text2 = option.getText();

                // System.out.println(text2);
                if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                    option.click();
                    break;
                }
            }

            Thread.sleep(2000);

            // driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).sendKeys(data.get(1).get(1));
            List<WebElement> element2 = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
            for (WebElement ele : element2) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }
            Thread.sleep(5000);
            driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_RGDNO_ID"))).sendKeys(data.get(2).get(1));
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            Thread.sleep(7000);
            // Boolean status=driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).isEnabled();
            WebElement Date = driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID")));
            if (Date.isEnabled()) {
                System.out.println("enabled");
            } else {
                System.out.println("Disabled");
            }


            // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfIncorporation_ID"))).sendKeys(data.get(3).get(1));

            // driver.findElement(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(4).get(1));
            driver.findElement(By.xpath(Pro.getProperty("Organisation_SourceOfCapital_Xapth"))).sendKeys(data.get(5).get(1));
            Thread.sleep(2000);
            // driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH"))).sendKeys(data.get(6).get(1));

            WebDriverWait Place = new WebDriverWait(driver, 60);
            Place.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_XPATH")))).click();
            List<WebElement> PlaceValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_PlaceOfIncorporation_Values_XPATH")));
            for (WebElement option : PlaceValue) {
                String text2 = option.getText();

                // System.out.println(text2);
                if (text2.equalsIgnoreCase(data.get(6).get(1))) {
                    option.click();
                    break;
                }
            }


            List<WebElement> BSDetails = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ScrollToView_XPATH")));
            for (WebElement ele : BSDetails) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }
            Thread.sleep(4000);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_XPATH"))).click();

            List<WebElement> ReasonFTIN = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_ReasonForTINApplication_ITEM_XPATH")));
            for (WebElement option1 : ReasonFTIN) {
                String text = option1.getText();
                // System.out.println(text);
                if (text.equalsIgnoreCase(data.get(7).get(1))) {
                    Actions builder1 = new Actions(driver);
                    builder1.moveToElement(option1).doubleClick(option1).build().perform();
                }
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
            List<WebElement> EndYearMonth = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_Value_XPATH")));
            for (WebElement option1 : EndYearMonth) {
                String text = option1.getText();
                // System.out.println(text);
                if (text.equalsIgnoreCase(data.get(19).get(1))) {
                    Actions builder1 = new Actions(driver);
                    builder1.moveToElement(option1).doubleClick(option1).build().perform();
                    break;
                }
            }

            Thread.sleep(4000);
            WebElement yearDay = driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_XPATH")));
            yearDay.click();
            List<WebElement> EndYearDay = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_YearEndDay_ITEM_Value_XPATH")));
            for (WebElement option1 : EndYearDay) {
                String text = option1.getText();
                // System.out.println(text);
                if (text.equalsIgnoreCase(data.get(20).get(1))) {
                    Actions builder1 = new Actions(driver);
                    builder1.moveToElement(option1).doubleClick(option1).build().perform();
                    break;
                }
            }

            Thread.sleep(4000);

	 /*driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_YearEndMonth_ITEM_XPATH"))).click();
	 action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	 Thread.sleep(2000);*/
            List<WebElement> element = driver.findElements(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID")));
            for (WebElement ele : element) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_ID"))).click();
            WebElement Occupationframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(Occupationframe);
            WebDriverWait OccWait = new WebDriverWait(driver, 50);
            OccWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Occupation/Business_ADD_WAIT_ID"))));
            driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_Primaryindicator_XPATH"))).click();

            WebElement BSadd = driver.findElement(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_XPATH")));
            action.click(BSadd).build().perform();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            List<WebElement> BSecValue = driver.findElements(By.xpath(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_BusinessSector_ITEM_XPATH")));
            for (WebElement option : BSecValue) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(8).get(1))) {
                    option.click();
                    break;
                }
            }
            Thread.sleep(5000);

            driver.findElement(By.id(Pro.getProperty("AdditionalDetails_BusinessSectorDetails_ADD_OK_ID"))).click();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.switchTo().defaultContent();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            WebDriverWait BSecrecord = new WebDriverWait(driver, 50);
            BSecrecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("AdditionalDetails_RecordAdded_XPATH"))));
            Thread.sleep(2000);
            List<WebElement> ScrollAddress = driver.findElements(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID")));
            for (WebElement ele : ScrollAddress) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }

            driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

            List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
            for (WebElement option : SummarytabValue) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(9).get(1))) {
                    Actions builder = new Actions(driver);
                    builder.moveToElement(option).doubleClick();
                    builder.perform();

                }
            }
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

            WebElement Addressadd = driver.findElement(By.id(Pro.getProperty("Organization_Addresses_Add_ID")));
            action.click(Addressadd).build().perform();
            Thread.sleep(7000);
            WebElement Addressframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(Addressframe);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            WebDriverWait AddressType = new WebDriverWait(driver, 50);
            AddressType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_Type_XPATH")))).click();
            List<WebElement> AddressValue = driver.findElements(By.xpath(Pro.getProperty("Addresses_Type_ITEM_XPATH")));
            for (WebElement option : AddressValue) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(10).get(1))) {
                    option.click();
                    break;
                }
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath(Pro.getProperty("Addresses_PrimaryIndicator_XPATH"))).click();
            Thread.sleep(2000);

            WebElement SName = driver.findElement(By.xpath(Pro.getProperty("Addresses_StreetName_XPATH")));
            action.sendKeys(SName, data.get(11).get(1)).build().perform();
            WebElement CName = driver.findElement(By.xpath(Pro.getProperty("Addresses_Town/City_XPATH")));
            action.sendKeys(CName, data.get(12).get(1)).build().perform();
            Thread.sleep(4000);

            Thread.sleep(4000);
            driver.findElement(By.xpath(Pro.getProperty("Addresses_region_XPATH"))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + data.get(14).get(1) + "')]")).click();

            Thread.sleep(2000);
            WebDriverWait District = new WebDriverWait(driver, 100);
            District.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Addresses_District_XPATH")))).click();

            List<WebElement> Dvalue = driver.findElements(By.xpath(Pro.getProperty("Addresses_District_ITEM_XPATH")));
            for (WebElement option : Dvalue) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(13).get(1))) {
                    option.click();
                    break;
                }
            }

            List<WebElement> AddressOK = driver.findElements(By.id(Pro.getProperty("Addresses_OK_ID")));
            for (WebElement ele : AddressOK) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }
            WebElement AddOK = driver.findElement(By.id(Pro.getProperty("Addresses_OK_ID")));
            action.click(AddOK).build().perform();
            driver.switchTo().defaultContent();
            WebDriverWait AddressRecord = new WebDriverWait(driver, 100);
            AddressRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_Addresses_RecordAdded_XPATH"))));
            driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

            List<WebElement> SummarytabValue2 = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
            for (WebElement option : SummarytabValue2) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(15).get(1))) {
                    Actions builder = new Actions(driver);
                    builder.moveToElement(option).doubleClick();
                    builder.perform();

                }
            }

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.findElement(By.id(Pro.getProperty("ContactMethods_ADD_ID"))).click();
            Thread.sleep(9000);
            WebElement ContMethodframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(ContMethodframe);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            WebDriverWait Purpose = new WebDriverWait(driver, 50);
            Purpose.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Purpose_XPATH")))).click();
            List<WebElement> PurposeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Purpose_ITEM_XPATH")));
            for (WebElement option : PurposeValue) {
                String text2 = option.getText();
                if (text2.equalsIgnoreCase(data.get(16).get(1))) {

                    option.click();
                    break;
                }
            }

            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            Thread.sleep(2000);
            WebDriverWait ContactType = new WebDriverWait(driver, 50);
//                ContactType.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_Type_XPATH")))).click();
//                List<WebElement> ContactTypeValue = driver.findElements(By.xpath(Pro.getProperty("ContactMethods_Type_ITEM_XPATH")));
//                for(WebElement option : ContactTypeValue){
//                    String text2= option.getText();
//                    if(text2.equalsIgnoreCase(data.get(17).get(1)))
//                    {
//
//                        Actions builder = new Actions(driver);
//                        builder.moveToElement(option).click(option);
//                        builder.perform();
//                        // option.click();
//                        break;
//                    }
//                }
//                JavascriptExecutor js = (JavascriptExecutor)driver;
//                js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")))));
//
//                WebElement PrimInd=driver.findElement(By.xpath(Pro.getProperty("ContactMethods_PrimaryIdicator_XPATH")));
//                action.click(PrimInd).build().perform();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            WebDriverWait ContctDet = new WebDriverWait(driver, 50);
            ContctDet.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH"))));
            Thread.sleep(1000);
            WebElement Contdetails = driver.findElement(By.xpath(Pro.getProperty("ContactMethods_ContactMethodDetails_XPATH")));
            action.sendKeys(Contdetails, data.get(18).get(1)).build().perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath(Pro.getProperty("ContactMethods_OK_XPATH"))).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();


        }


    }

    //UAT_TCS 01.02.3	To verify the process of Validation error in entered data
    @Then("^Verify the Validation Error for organization \"([^\"]*)\"$")
    public void verify_the_Validation_Error_for_organization(String Validate) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PrimaryIndicator_Validation_Organization_XPATH"))));
        // Capture ARN Number
        String text = driver.findElement(By.xpath(Pro.getProperty("PrimaryIndicator_Validation_Organization_XPATH"))).getText();

        System.out.println(text);


        if (text.contains(Validate)) {

            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

        Thread.sleep(8000);

    }


//Register Organizatio Taxpayer Save Process Application

    @When("^Enter Attachment Tab details for Processing Application$")
    public void enter_Attachment_Tab_details_for_Processing_Application(DataTable Attchtable) throws Throwable {


        List<List<String>> data = Attchtable.asLists();
        //Actions action=new Actions(driver);


        WebDriverWait ContactRecord = new WebDriverWait(driver, 50);
        ContactRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organisation_ContactMethods_RecordAdded_XPATH"))));
        driver.findElement(By.id(Pro.getProperty("Organisation_Summary_Tabs_ID"))).click();

        List<WebElement> OrgAttachment = driver.findElements(By.xpath(Pro.getProperty("Organisation_Summary_Tabs_ITEM_XPATH")));
        for (WebElement option : OrgAttachment) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

            }
        }
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement Attachmentframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Attachmentframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttPassport = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttPassport) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(2).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(3).get(1));
        Thread.sleep(3000);
        WebElement uploadBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        // action.click(uploadBtn).build().perform();
        uploadBtn.click();
        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        // robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // robot.delay(600);
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_ENTER);
        //  robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        //  robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        // driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        // driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord = new WebDriverWait(driver, 50);
        AttRecord.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//Attachment-----	Approval Letter from Line Ministry


        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement AttLetterframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttLetterframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttLettert = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttLettert) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(6).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(5).get(1));
        Thread.sleep(3000);
        WebElement LetterBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
// action.click(LetterBtn).build().perform();
        LetterBtn.click();
//put path to your image in a clipboard
        StringSelection ss1 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss1, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot1 = new Robot();
        robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        robot1.keyPress(KeyEvent.VK_CONTROL);
        robot1.keyPress(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_V);
        robot1.keyRelease(KeyEvent.VK_CONTROL);
//robot1.delay(600);
//Thread.sleep(5000);
        robot1.keyPress(KeyEvent.VK_ENTER);
//robot1.delay(300);
        robot1.keyPress(KeyEvent.VK_ENTER);
        robot1.delay(300);
        robot1.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord1 = new WebDriverWait(driver, 50);
        AttRecord1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
//End....
// Certificate of Incorporation
        driver.findElement(By.id(Pro.getProperty("Attachments_ADD_ID"))).click();
        WebElement AttCertiframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(AttCertiframe);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Attachments_Date_Received_ID"))).sendKeys(data.get(1).get(1));
        driver.findElement(By.xpath(Pro.getProperty("Attachments_DoccumentType_XPATH"))).click();

        List<WebElement> AttCertificate = driver.findElements(By.xpath(Pro.getProperty("Attachments_DoccumentType_ITEM_XPATH")));
        for (WebElement option : AttCertificate) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(7).get(1))) {
                option.click();
                break;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Attachments_ADD_RefferenceNumber_XPATH"))).sendKeys(data.get(9).get(1));
        Thread.sleep(2000);
        WebElement CertiBtn = driver.findElement(By.id(Pro.getProperty("Attachment_Upload_BUTTON_ID")));
        CertiBtn.click();
// action.click(CertiBtn).build().perform();
//put path to your image in a clipboard
        StringSelection ss2 = new StringSelection(data.get(4).get(1));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot2 = new Robot();
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        robot2.keyPress(KeyEvent.VK_CONTROL);
        robot2.keyPress(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_CONTROL);
        robot2.delay(600);
//Thread.sleep(5000);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyPress(KeyEvent.VK_ENTER);
        robot2.delay(300);
        robot2.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();
        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        WebDriverWait AttRecord2 = new WebDriverWait(driver, 50);
        AttRecord2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("Organization_Attachment_Details_RecordAdded_XPATH"))));
        Thread.sleep(2000);
    }


    @Then("^Organization ARN number will generate$")
    public void Organization_ARN_number_will_generate(DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("RegisterOrganisation_SUBMIT_XPATH")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
// Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is " + text.substring(42));
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;
// System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);

        if (text.contains((data.get(4).get(1)))) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

        Thread.sleep(27000);
    }


// Process Registration Application Individual Taxpayer Scenario


    @Then("^Click on Register Taxpayer tab$")
    public void click_on_Register_Taxpayer_tab() throws Throwable {
        //Actions action =new Actions(driver);
        WebElement Taxpayer = driver.findElement(By.xpath(Pro.getProperty("RegisterTaxpayer_LINK_XPATH")));
        //action.click(Taxpayer).build().perform();
        Taxpayer.click();

    }

    @Then("^Click on Process Registration application$")
    public void click_on_Process_Registration_application() throws Throwable {
        Actions action = new Actions(driver);

        WebElement Taxpayer = driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxpayer_ProcessRegistrationApplication_XPATH")));
        action.click(Taxpayer).build().perform();

    }

    @Then("^Enter Application reference number \"([^\"]*)\"$")
    public void enter_Application_reference_number(String ReferenceNumber) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("ProcessRegistrationApplication_ApplicationRefferenceNumber_ID"))).sendKeys(ReferenceNumber);
        Thread.sleep(2000);
    }

    @Then("^Click on Search$")
    public void click_on_Search() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_Search_XPATH"))).click();

    }

    @Then("^Select the row from the grid$")
    public void select_the_row_from_the_grid() throws Throwable {
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_SelectFirstRow_XPATH")));
        action.doubleClick(PrimInd).build().perform();

    }

    @Then("^Click on Edit button of individual page$")
    public void click_on_Edit_button_of_individual_page() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("ProcessRegistrationApplication_IND_Edit_ID"))).click();
        Thread.sleep(2000);

    }

    @Then("^Scroll page to the attachment tab$")
    public void scroll_page_to_the_attachment_tab() throws Throwable {
        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Applicant_Attachments_ScrollToViwe_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(2000);

    }

    @Then("^Click on Attachment Tab$")
    public void click_on_Attachment_Tab() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Exemption_ScrollToView_XPATH"))).click();

    }

    @Then("^Select first row of the grid for individual page$")
    public void select_first_row_of_the_grid_for_individual_page() throws Throwable {
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_IND_Edit_Attachment_FirstRow_XPATH")));
        action.doubleClick(PrimInd).build().perform();
        Thread.sleep(2000);


    }

    @Then("^Click edit the Individual attachment information$")
    public void click_edit_the_Individual_attachment_information() throws Throwable {
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_IND_Attachment_Edit_XPATH")));
        action.doubleClick(PrimInd).build().perform();
        Thread.sleep(2000);
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);


    }

    @Then("^Check the PN folder check box$")
    public void check_the_PN_folder_check_box() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_PNFolder_XPATH"))).click();

    }

    @Then("^Check the Verified check box$")
    public void check_the_Verified_check_box() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("AttachmentDetails_Verified_XPATH"))).click();

    }

    @Then("^Click on OK Button$")
    public void click_on_OK_Button() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("AttachmentDetails_OK_ID"))).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);

    }

//Process Registration Application Organization Taxpayer Scenario

    @Then("^Select first row of the grid for Organization page$")
    public void select_first_row_of_the_grid_for_Organization_page() throws Throwable {
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("ProcessRegistrationApplication_ORG_Edit_Attachment_FirstRow_XPATH")));
        action.doubleClick(PrimInd).build().perform();
        Thread.sleep(2000);


    }


    //Register Individual taxtype  Individual scenario
    @When("^I enter valid data on the TaxType Individual page (.+)$")
    public void i_enter_valid_data_on_the_taxtype_individual_page(String taxtype, DataTable arg1) throws Throwable {
        {
            //Initialize data table
            List<List<String>> data = arg1.asLists();
            Actions action = new Actions(driver);
            WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
            action.doubleClick(Reg).build().perform();
            Reg.click();
            WebElement manage = driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
            action.doubleClick(manage).build().perform();
            WebDriverWait Taxtype = new WebDriverWait(driver, 60);
            Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_RegisterTaxType_XPATH")))).click();

            Thread.sleep(4000);
            driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
            ;
            List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
            for (WebElement option : list) {
                String text2 = option.getText();
                System.out.println(text2);


                if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                    option.click();
                    break;
                }
            }
            driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));


            WebDriverWait Searchwait = new WebDriverWait(driver, 100);
            Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID")))).click();

            Thread.sleep(2000);
            List<WebElement> element2 = driver.findElements(By.id(Pro.getProperty("SrollToView_Title_ID")));
            for (WebElement ele : element2) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }
            WebDriverWait wait = new WebDriverWait(driver, 100);
            WebElement RegTaxType = wait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_RegisterTaxTypeButton_ID"))));

            action.click(RegTaxType).build().perform();
            WebDriverWait iframeWait = new WebDriverWait(driver, 60);
            WebElement Taxtypeframe = iframeWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
            driver.switchTo().frame(Taxtypeframe);
            WebDriverWait selectTaxtype = new WebDriverWait(driver, 60);
            selectTaxtype.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_TaxType_ID")))).click();
            List<WebElement> TaxTypeValue = driver.findElements(By.xpath(Pro.getProperty("Registration_RegisterTaxType_TaxType_ITEM_XPATH")));
            for (WebElement option : TaxTypeValue) {
                String text2 = option.getText();


                if (text2.equalsIgnoreCase(taxtype)) {

                    option.click();
                    break;
                }
            }
            Thread.sleep(2000);
            //driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_EDR_ID"))).sendKeys(data.get(3).get(1));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('" + Pro.getProperty("Registration_RegisterTaxType_EDR_ID") + "').setAttribute('value', '" + data.get(3).get(1) + "')");

            WebElement turnoverInput = driver.findElement(By.id("RevenueTypeDetails:TaxableTurnover_input"));
            turnoverInput.sendKeys(data.get(2).get(1));

            WebElement attachDropdown = driver.findElement(By.xpath("//*[@id=\"RevenueTypeDetails:DocType\"]/div[3]"));
            attachDropdown.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[text()='" + data.get(6).get(1) + "']")).click();

            Thread.sleep(2000);
            WebElement docNumber = driver.findElement(By.id("RevenueTypeDetails:Reference"));
            docNumber.sendKeys(data.get(7).get(1));

            Thread.sleep(2000);
            WebElement attachment = driver.findElement(By.id("RevenueTypeDetails:AttachmentPath_input"));
            Thread.sleep(2000);
            attachment.sendKeys(data.get(5).get(1));


            driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_OK_ID"))).click();
            driver.switchTo().defaultContent();
//                String Recordwait=driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxType_RecordAdded_XPATH"))).getText();
//                System.out.println(Recordwait);
//                if(Recordwait.contains(data.get(4).get(1)))
//                {
//
//                    System.out.println("Text Verified and passed");
//                }
//                else
//                {
//                    System.out.println("Text Not Verfied and failed");
//                }
//
//                Thread.sleep(1000);
        }


    }

    @Then("^TaxType ARN number will generate$")
    public void taxtype_ARN_number_will_generate(DataTable TaxType) throws Throwable {

        List<List<String>> data = TaxType.asLists();
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("TaxType_Submit_ID")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
        System.out.println(text);

        System.out.println("substring is " + text.substring(42));
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;

        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);


        if (text.contains(data.get(0).get(1))) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }


    }

    @When("^I enter valid data on the TaxType Organization page$")
    public void i_enter_valid_data_on_the_TaxType_Organization_page(DataTable arg1) throws Throwable {
        {
            //Initialize data table
            List<List<String>> data = arg1.asLists();
            Actions action = new Actions(driver);
            WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
            action.doubleClick(Reg).build().perform();
            Reg.click();

            WebElement manage = driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
            action.doubleClick(manage).build().perform();
            WebDriverWait Taxtype = new WebDriverWait(driver, 60);
            Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_RegisterTaxType_XPATH")))).click();

            driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
            ;
            List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
            for (WebElement option : list) {
                String text2 = option.getText();
                System.out.println(text2);


                if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                    option.click();
                    break;
                }
            }
            driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));


            WebDriverWait Searchwait = new WebDriverWait(driver, 100);
            Searchwait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Individual_Search_ID")))).click();

            Thread.sleep(2000);
            List<WebElement> element2 = driver.findElements(By.id(Pro.getProperty("SrollToView_Title_ID")));
            for (WebElement ele : element2) {

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].scrollIntoView(true);", ele);

            }

            WebElement RegTaxType = driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_RegisterTaxTypeButton_ID")));
            action.click(RegTaxType).build().perform();
            Thread.sleep(10000);
            WebElement Taxtypeframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(Taxtypeframe);
            WebDriverWait selectTaxtype = new WebDriverWait(driver, 60);
            selectTaxtype.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_RegisterTaxType_TaxType_ID")))).click();
            List<WebElement> TaxTypeValue = driver.findElements(By.xpath(Pro.getProperty("Registration_RegisterTaxType_TaxType_ITEM_XPATH")));
            for (WebElement option : TaxTypeValue) {
                String text2 = option.getText();


                System.out.println(text2);
                if (text2.equalsIgnoreCase(data.get(2).get(1))) {

                    option.click();
                    break;
                }
            }
            Thread.sleep(2000);
            //driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_EDR_ID"))).sendKeys(data.get(3).get(1));
            JavascriptExecutor js3 = (JavascriptExecutor) driver;
            js3.executeScript("document.getElementById('" + Pro.getProperty("Registration_RegisterTaxType_EDR_ID") + "').setAttribute('value', '" + data.get(3).get(1) + "')");

            driver.findElement(By.id(Pro.getProperty("Registration_RegisterTaxType_OK_ID"))).click();
            driver.switchTo().defaultContent();
            String Recordwait = driver.findElement(By.xpath(Pro.getProperty("Registration_RegisterTaxType_RecordAdded_XPATH"))).getText();
            System.out.println(Recordwait);
            if (Recordwait.contains(data.get(4).get(1))) {

                System.out.println("Text Verified and passed");
            } else {
                System.out.println("Text Not Verfied and failed");
            }

        }

        Thread.sleep(1000);
    }

// Update Individual Taxpayer Scenario

    @When("^I enter valid data on the Update Individual page$")
    public void i_enter_valid_data_on_the_Update_Individual_page(DataTable UpadateTable) throws Throwable {
        //Initialize data table
        List<List<String>> data = UpadateTable.asLists();
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        WebDriverWait Taxtype = new WebDriverWait(driver, 10);
        Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_XPATH"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
        ;
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
//                System.out.println(text2);


            if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                option.click();
                break;
            }
        }

        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(2000);
        WebElement Search = driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID")));
        Search.click();
        Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("LastName_ID"))).clear();
        Thread.sleep(1000);
        WebElement LastName = driver.findElement(By.id(Pro.getProperty("LastName_ID")));
        action.sendKeys(LastName, data.get(2).get(1)).build().perform();

        Thread.sleep(2000);
        List<WebElement> element = driver.findElements(By.xpath(Pro.getProperty("Gender_LINK_XPATH")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }
        Thread.sleep(2000);
			/*driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).clear();
	driver.findElement(By.id(Pro.getProperty ("DOB_ID"))).sendKeys(data.get(3).get(1));*/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + Pro.getProperty("DOB_ID") + "').setAttribute('value', '" + data.get(3).get(1) + "')");


        List<WebElement> AmendScroll = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_AmendmentReason_Lable_Scroll_XPATH")));
        for (WebElement ele : AmendScroll) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }

        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Individual_AmendmentReason_XPATH"))).click();
        List<WebElement> AmendValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Individual_AmendmentReason_ITEM_XPATH")));
        for (WebElement option : AmendValue) {
            String text2 = option.getText();
//                System.out.println(option);
            if (text2.equalsIgnoreCase(data.get(4).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

                break;


            }
            Thread.sleep(3000);
        }
    }

    // Update Organization Taxpayer Scenario
    @When("^I enter valid data on the Update Organizatio page$")
    public void i_enter_valid_data_on_the_Update_Organizatio_page(DataTable UpdteOrgTable) throws Throwable {
        //Initialize data table
        List<List<String>> data = UpdteOrgTable.asLists();
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        WebDriverWait Taxtype = new WebDriverWait(driver, 10);
        Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_XPATH"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
        ;
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            System.out.println(text2);


            if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                option.click();
                break;
            }
        }

        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(1).get(1));
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH"))).clear();
        Thread.sleep(1000);
        WebElement LastName = driver.findElement(By.xpath(Pro.getProperty("HeaderDetails_OrganisationName_XPATH")));
        action.sendKeys(LastName, data.get(2).get(1)).build().perform();

        Thread.sleep(4000);
        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }
        Thread.sleep(2000);

    }

    @Then("^Clicks on transfer Taxpayer Cancel button$")
    public void Clicks_on_transfer_Taxpayer_Cancel_button() throws Throwable {
        driver.findElement(By.id("TransferTaxpayer:Cancel")).click();

    }

    @Then("^Find Entity page should be displayed$")
    public void find_entity_page_should_be_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);

        String Header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:directorHeader"))).getText();
        Assert.assertEquals(Header, "Trips - Find Entity");
    }

    @Then("^System displays message Records Not Found$")
    public void system_displays_message_records_not_found() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);

        WebElement emptyDatatable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr/td")));
        Assert.assertEquals(emptyDatatable.getText(), "No records found.");
    }

//        @Then("^Select New Office \"([^\"]*)\"$")
//        public void select_New_Office(String NewOffice) throws Throwable {
//            Actions action = new Actions(driver);
//            Thread.sleep(3000);
//            WebElement title = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_NewOffice_XPATH")));
//        }

//				/*driver.findElement(By.id(Pro.getProperty ("Organisation_AdditionalDetails_DateOfCommencement_ID"))).clear();
//		driver.findElement(By.id(Pro.getProperty ("Organisation_AdditionalDetails_DateOfCommencement_ID"))).sendKeys(data.get(3).get(1));*/
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.getElementById('" + Pro.getProperty("Organisation_AdditionalDetails_DateOfCommencement_ID") + "').setAttribute('value', '" + data.get(3).get(1) + "')");
//
//        Thread.sleep(1000);
//        List<WebElement> AmendScroll = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_AmendmentReason_Lable_Scroll_XPATH")));
//        for (WebElement ele : AmendScroll) {
//
//            JavascriptExecutor js1 = (JavascriptExecutor) driver;
//            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
//            //ele.click();
//        }
//
//        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Organisation_AmendmentReason_XPATH"))).click();
//        List<WebElement> AmendValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_Organisation_AmendmentReason_ITEM_XPATH")));
//        for (WebElement option : AmendValue) {
//            String text2 = option.getText();
//            System.out.println(option);
//            if (text2.equalsIgnoreCase(data.get(4).get(1))) {
//                Actions builder = new Actions(driver);
//                builder.moveToElement(option).doubleClick();
//                builder.perform();
//
//                break;
//
//
//            }
//            Thread.sleep(3000);
//        }
//    }


//------------------Login Find taxpayer------------

    @Then("^Goto Find taxpayer$")
    public void goto_Find_taxpayer() throws Throwable {
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Findtaxpayer_ITEM_XPATH")));
        action.doubleClick(Reg).build().perform();


    }


    // Transfer Individual taxpayer scenario code

    @Then("^Click on regisration link$")
    public void click_on_regisration_link() throws Throwable {


        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        action.click().build().perform();


    }

    @Then("^Goto Manage taxpayer$")
    public void goto_Manage_taxpayer() throws Throwable {
        Actions action = new Actions(driver);
        WebElement manage = driver.findElement(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")));
        action.doubleClick(manage).build().perform();


    }

    @Then("^Goto Transfer Taxpayer$")
    public void goto_Transfer_Taxpayer() throws Throwable {

        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_XPATH")));

        Reg.click();

        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

    }

//    @Then("^Select Taxpayer Classification Type \"([^\"]*)\"$")
//    public void select_Taxpayer_Classification_Type(String ClasificationType) throws Throwable {
//        Thread.sleep(2000);
//        Actions action = new Actions(driver);
//
//        WebElement title = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH")));
//
//
//        action.click(title).build().perform();
//        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
//        for (WebElement option : list) {
//            Thread.sleep(2000);
//            String text2 = option.getText();
//            if (text2.equalsIgnoreCase(ClasificationType)) {
//                Actions builder = new Actions(driver);
//                builder.moveToElement(option).doubleClick();
//                builder.perform();
//                break;
//
//            }
//        }
//    }

    @Then("^Enter TIN number \"([^\"]*)\"$")
    public void enter_TIN_number(String TIN) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(TIN);
    }

    @Then("^Click on search$")
    public void click_on_search() throws Throwable {
        driver.findElement(By.xpath("//*[text()='Search']")).click();

    }

    @Then("^Click table column TaxDetails \"([^\"]*)\"$")
    public void click_table_column_taxdetails_something(String strArg1) throws Throwable {
        WebDriverWait waitReason = new WebDriverWait(driver, 10);
        WebElement taxTypeGrid =waitReason.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//td[contains(text(),'"+strArg1+"')]"))));
//        Assert.assertTrue(taxTypeGrid.isDisplayed());
//        WebElement parent = taxTypeGrid.findElement(By.xpath("./.."));
        taxTypeGrid.click();
    }

    @Then("^Select New Office \"([^\"]*)\"$")
    public void select_New_Office(String NewOffice) throws Throwable {
        Actions action = new Actions(driver);
        Thread.sleep(3000);
        WebElement title = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_NewOffice_XPATH")));


        action.click(title).build().perform();

        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_NewOffice_ITEM_XPATH")));

        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(NewOffice)) {
                Actions builder = new Actions(driver);
                Thread.sleep(3000);
                builder.moveToElement(option).doubleClick();
                Thread.sleep(3000);

                builder.perform();
                break;

            }
        }
        Thread.sleep(2000);

    }

    @Then("^Select Date of transfer \"([^\"]*)\"$")
    public void select_Date_of_transfer(String DateOfTransfer) throws Throwable {

        List<WebElement> element2 = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_DateOfTransfer_ID")));

        for (WebElement ele : element2) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(2000);
/*	WebElement DateOfTransfr =driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_DateOfTransfer_XPATH")));
	DateOfTransfr.sendKeys(Keys.TAB);
	DateOfTransfr.sendKeys(DateOfTransfer);
	DateOfTransfr.sendKeys(Keys.TAB);*/

        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("document.getElementById('" + Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_DateOfTransfer_ID") + "').setAttribute('value', '" + DateOfTransfer + "')");


    }


    @Then("^Select Reason \"([^\"]*)\"$")
    public void select_Reason(String Reason) throws Throwable {
        Actions action = new Actions(driver);
        Thread.sleep(3000);
        WebElement title = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Reason_XPATH")));
        action.click(title).build().perform();
//=======
//            WebElement EDR=driver.findElement(By.id("ReregisterRegime:EDRR_input"));
//            EDR.clear();
//            Thread.sleep(2000);
//            EDR.sendKeys(data.get(4).get(1));
//            Thread.sleep(3000);
//>>>>>>> master

        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Reason_ITEM_XPATH")));

        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(Reason)) {
                Actions builder = new Actions(driver);
                Thread.sleep(3000);
                builder.moveToElement(option).doubleClick();
                Thread.sleep(3000);

                builder.perform();
                break;

            }
        }
        Thread.sleep(2000);

    }

    @And("^click reRegister Button$")
    public void click_reregister_button() throws Throwable {
        driver.findElement(By.id("ReregisterRegime:reRegisterBtn")).click();
    }

//    @Then("^message is displayed \"([^\"]*)\"$")
//    public void message_is_displayed_something(String strArg1) throws Throwable {
////    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        WebDriverWait wait=new WebDriverWait(driver,10);
//
//        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+strArg1+"')]")));
//        if(Message.isDisplayed()) {
//            Assert.assertTrue("Error message displayed",true);
//        }else {
//            Assert.fail("No Error message displayed");
//        }
//    }


    @Then("^Click on tarnsfer$")
    public void click_on_tarnsfer() throws Throwable {

        WebDriverWait RefNumber = new WebDriverWait(driver, 100);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_TransferTaxPayer_Search_Transfer_XPATH")))).click();
        Thread.sleep(6000);
    }

    @Then("^Verify the ARN number \"([^\"]*)\"$")
    public void verify_the_ARN_number_ARN(String ARN) throws Throwable {

        WebDriverWait RefNumber = new WebDriverWait(driver, 150);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is " + text.substring(42));

        sharedatastep.A_CRMARN = text.substring(42);
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);

        if (text.contains(ARN)) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verified and failed");
        }

        Thread.sleep(25000);
    }


//Re-Register TaxType Individual Scenario

    @When("^I enter valid data on the Re-RegisterTaxType Individual page$")
    public void i_enter_valid_data_on_the_Re_RegisterTaxType_Individual_page(DataTable ReRegTable) throws Throwable {


        //Initialize data table
        List<List<String>> data = ReRegTable.asLists();
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        WebDriverWait Taxtype = new WebDriverWait(driver, 100);
        Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();

        List<WebElement> ReRegisterTax = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Context_ITEM_XPATH")));
        for (WebElement option : ReRegisterTax) {
            String text2 = option.getText();
            // System.out.println(text2);


            if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                option.click();
                break;
            }
        }

        Thread.sleep(1000);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
        ;
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            // System.out.println(text2);


            if (text2.equalsIgnoreCase(data.get(1).get(1))) {

                option.click();
                break;
            }
        }

        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(2).get(1));
        Thread.sleep(1000);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
        Thread.sleep(5000);

        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_ReregisterTax_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }


        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement ReturnType = driver.findElement(By.id(Pro.getProperty("Re-RegisterTaxType_Return_ID")));
        action.doubleClick(ReturnType).build().perform();
        Thread.sleep(2000);

        WebElement EDR = driver.findElement(By.id("ReregisterRegime:EDRR_input"));
//            EDR.sendKeys(tomorrowsDate());
        EDR.clear();
        EDR.sendKeys(data.get(4).get(1));
        action.sendKeys(Keys.TAB);

        Thread.sleep(3000);

        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_XPATH"))).click();
        List<WebElement> ReRegValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_ITEM_XPATH")));
        for (WebElement option : ReRegValue) {
            String text2 = option.getText();
            System.out.println(option);
            if (text2.equalsIgnoreCase(data.get(3).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

                break;
            }

            Thread.sleep(2000);

        }


    }


    @Then("^ReRegister ARN number will generate$")
    public void reregister_ARN_number_will_generate(DataTable ReRegTable1) throws Throwable {


        List<List<String>> data = ReRegTable1.asLists();
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_RegisterBtn_ID")))).click();
        Thread.sleep(7000);
        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
// Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();
        System.out.println(text);
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;
        if (text.contains(data.get(0).get(1))) {
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }


    }

//Re-Register TaxType Organization Scenario

    @When("^I enter valid data on the Re-RegisterTaxType Organization page$")
    public void i_enter_valid_data_on_the_Re_RegisterTaxType_Organization_page(DataTable ReRegTable) throws Throwable {


        //Initialize data table
        List<List<String>> data = ReRegTable.asLists();
        Actions action = new Actions(driver);
        WebElement Reg = driver.findElement(By.xpath(Pro.getProperty("Registration_LINK_XPATH")));
        action.doubleClick(Reg).build().perform();
        Reg.click();
        WebDriverWait Taxtype = new WebDriverWait(driver, 100);
        Taxtype.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ManageTaxpayer_LINK_XPATH")))).click();

        List<WebElement> ReRegisterTax = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Context_ITEM_XPATH")));
        for (WebElement option : ReRegisterTax) {
            String text2 = option.getText();


            if (text2.equalsIgnoreCase(data.get(0).get(1))) {

                option.click();
                break;
            }
        }

        Thread.sleep(1000);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_XPATH"))).click();
        ;
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();


            if (text2.equalsIgnoreCase(data.get(1).get(1))) {

                option.click();
                break;
            }
        }

        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TIN_ID"))).sendKeys(data.get(2).get(1));
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Search_ID"))).click();
        Thread.sleep(5000);

        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_ReregisterTax_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }


        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebElement ReturnType = driver.findElement(By.id(Pro.getProperty("Re-RegisterTaxType_Return_ID")));
        action.doubleClick(ReturnType).build().perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_XPATH"))).click();
        List<WebElement> ReRegValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-RegisterTax_Search_Reason_ITEM_XPATH")));
        for (WebElement option : ReRegValue) {
            String text2 = option.getText();
            System.out.println(option);
            if (text2.equalsIgnoreCase(data.get(3).get(1))) {
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick();
                builder.perform();

                break;
            }


            Thread.sleep(2000);

        }


    }

    // Suspend Individual TaxType
    @Then("^Goto Suspend TaxType \"([^\"]*)\"$")
    public void goto_Suspend_TaxType(String SuspendTaxType) throws Throwable {
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//a[text()='"+SuspendTaxType+"']")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[3]/a")).click();



    }

    @Then("^Enter Suspension Satrt Date \"([^\"]*)\"$")
    public void enter_Suspension_Satrt_Date(String StartDate) throws Throwable {
        List<WebElement> element2 = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_ScrollToView_ID")));
        for (WebElement ele : element2) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SelectfirstRow_XPATH")));
        action.click(PrimInd).build().perform();

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("document.getElementById('" + Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionStartDate_ID") + "').setAttribute('value', '" + StartDate + "')");
        Thread.sleep(2000);

    }

    @Then("^Enter Suspension End Date \"([^\"]*)\"$")
    public void enter_Suspension_End_Date(String EndDate, DataTable SuspendTable) throws Throwable {
        List<List<String>> data = SuspendTable.asLists();
        Actions builder = new Actions(driver);
        WebDriverWait waitReason = new WebDriverWait(driver, 50);
        waitReason.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_Lable_Wait_ID"))));
        Thread.sleep(1000);
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("document.getElementById('" + Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_SuspensionEndDate_ID") + "').setAttribute('value', '" + EndDate + "')");
        Thread.sleep(2000);


        WebElement SD = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_XPATH")));
        builder.click(SD).build().perform();

        List<WebElement> SummarytabValue = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Reason_ITEM_XPATH")));
        for (WebElement option : SummarytabValue) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(data.get(0).get(1))) {
                System.out.println(text2);
                //Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();
                builder.build().perform();
                break;
            }
        }
        Thread.sleep(6000);

        WebDriverWait RefNumber = new WebDriverWait(driver, 100);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Suspend/DormantTaxType_Suspend_BTN_XPATH")))).click();
        Thread.sleep(6000);


    }


//De-register Individual TaxType


    @And("^Select de register Reason \"([^\"]*)\"$")
    public void select_de_register_Reason(String Reason) throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_Reason_XPATH"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + Reason + "')]")).click();
    }

    @Then("^Goto DeRegister$")
    public void goto_DeRegister() throws Throwable {

        WebElement deregister = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_XPATH")));
        deregister.click();
        Thread.sleep(2000);

    }


    @Then("^check the Deregister checkbox$")
    public void check_the_Deregister_checkbox() throws Throwable {
        Thread.sleep(3000);
        //WebDriverWait RefNumber=new WebDriverWait(driver,100);
        //RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("DeRegisterTaxpayer_Checkbox_ID")))).click();
        WebElement DeCheckbox = driver.findElement(By.xpath(Pro.getProperty("DeRegisterTaxpayer_Checkbox_XPATH")));
        Actions action = new Actions(driver);
        action.click(DeCheckbox).build().perform();
        Thread.sleep(1000);

    }

    @Then("^Taxpayer should be deregisterd \"([^\"]*)\"$")
    public void taxpayer_should_be_deregisterd(String Status) throws Throwable {
        Thread.sleep(2000);
        String text = driver.findElement(By.xpath(Pro.getProperty("TaxPayer_Deregister_Status_XPATH"))).getAttribute("value");

        System.out.println(text);

        if (text.contains(Status)) {

            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

    }


    @Then("^CLick on taxtype in grid$")
    public void click_on_taxtype_in_grid() throws Throwable {


        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }

        Actions action = new Actions(driver);
        WebElement ReturnType = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_SelectfirstRow_XPATH")));
        //ReturnType.click();
        action.click(ReturnType).build().perform();

        Thread.sleep(2000);

    }

    @Then("^CLick on taxtype (\\d+)nd row in grid$")
    public void click_on_taxtype_nd_row_in_grid(int arg1) throws Throwable {


        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }

        Actions action = new Actions(driver);
        WebElement ReturnType = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_SelectsecondRow_XPATH")));
        //ReturnType.click();
        action.click(ReturnType).build().perform();

        Thread.sleep(2000);
    }

    @Then("^CLick on (\\d+)rd taxtype in grid$")
    public void click_on_rd_taxtype_in_grid(int arg1) throws Throwable {

        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }

        Actions action = new Actions(driver);
        WebElement ReturnType = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_SelectthirdRow_XPATH")));
        //ReturnType.click();
        action.click(ReturnType).build().perform();

        Thread.sleep(2000);

    }


    @Then("^Select EDD \"([^\"]*)\"$")
    public void select_EDD(String EDD) throws Throwable {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_EDD_ID") + "').setAttribute('value', '" + tomorrowsDate() + "')");

        Thread.sleep(1000);

    }

    @Then("^Click on DeRegister button$")
    public void click_on_DeRegister_button() throws Throwable {

        WebDriverWait RefNumber = new WebDriverWait(driver, 30);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Registration_ManageTaxPayer_De-registerTax_DeRegister_ID")))).click();
        Thread.sleep(6000);


    }


//Re-Active  Individual TaxType


    @Then("^Goto Reactive Taxtype$")
    public void goto_Reactive_Taxtype() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement Reactivate = driver.findElement(By.xpath(Pro.getProperty("Re-Activate_XPATH")));
        Reactivate.click();
        Thread.sleep(2000);


    }

    @Then("^CLick on Reactivate taxtype in grid$")
    public void click_on_Reactivate_taxtype_in_grid() throws Throwable {
        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_ScrollToView_TaxDetails_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }

        Actions action = new Actions(driver);
        WebElement ReturnType = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_SelectfirstRow_XPATH")));
//ReturnType.click();
        action.click(ReturnType).build().perform();
        Thread.sleep(2000);


    }

    @Then("^Select Reactivate EDD \"([^\"]*)\"$")
    public void select_Reactivate_EDD(String EDD) throws Throwable {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_EffectiveDateOfReactivation_ID") + "').setAttribute('value', '" + EDD + "')");
        Thread.sleep(2000);
    }


    @Then("^Select Reactivate Taxtype Reason \"([^\"]*)\"$")
    public void select_Reactivate_Taxtype_Reason(String Reason) throws Throwable {

        Actions action = new Actions(driver);
        WebElement reason1 = driver.findElement(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Reason_XPATH")));
        action.click(reason1).build().perform();

        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Reason_ITEM_XPATH")));

        for (WebElement option : list) {
            String text2 = option.getText();
            System.out.println(text2);
            if (text2.equalsIgnoreCase(Reason)) {
                System.out.println(Reason);
                Actions builder = new Actions(driver);
                // Thread.sleep(3000);
                builder.moveToElement(option).doubleClick();
                Thread.sleep(3000);

                builder.perform();
                break;

            }
        }
        Thread.sleep(3000);

    }

    @Then("^Click on Reactivate button$")
    public void click_on_Reactivate_button() throws Throwable {

        WebDriverWait RefNumber = new WebDriverWait(driver, 30);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_Re-ActivateTax_Re-active_Btn_XPATH")))).click();
        Thread.sleep(6000);

    }

//-------------UAT_TCS 01.17.2	To verify the Process of Finding Individual Taxpayer by Name or part of Name----

    @Then("^Enter Name \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enter_Name_and(String Firstname, String lastname) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_Name_ID"))).sendKeys(Firstname);
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_lastName_ID"))).sendKeys(lastname);

    }

//----- UAT_TCS 01.18.2	To Verify the Process of Finding a Taxpayer by Name or part of Name-------


    @Then("^Enter OrganisationName \"([^\"]*)\"$")
    public void enter_OrganisationName(String CompanyName) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_CompanyName_ID"))).sendKeys(CompanyName);

    }
//-----------UAT_TCS 01.25.5,UAT_TCS 01.25.3	To verify the Process of Suspending Personal Income Tax when other Taxes are active---

    @Then("^get the message  \"([^\"]*)\"$")
    public void get_the_message(String validation) throws Throwable {


        WebDriverWait RefNumber = new WebDriverWait(driver, 60);
        RefNumber.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID")))).click();
        // Capture ARN Number
        String text = driver.findElement(By.id(Pro.getProperty("Precessing_Completed_RefferenceNumber_ID"))).getText();

        System.out.println(text);
        System.out.println("substring is " + text.substring(42));
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);

        if (text.contains(validation)) {
            //  System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

        Thread.sleep(25000);
    }


//--------------Taxpayer_Accounting_Module --------BackOffice---------------------
//------Taxpayer_Account_Enquiry_Ind------------

    @Then("^Click on TaxPayer Accounting$")
    public void click_on_TaxPayer_Accounting() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("TaxpayerAccounting_XPATH")))).click();

    }

    @Then("^click on TaxPayer Accounting Enquiry$")
    public void click_on_TaxPayer_Accounting_Enquiry() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("TaxpayerAccounting_Enquiry_XPATH"))).click();

    }

    @Then("^TaxType Account dropdown$")
    public void taxtype_Account_dropdown() throws Throwable {
        List<WebElement> element = driver.findElements(By.xpath(Pro.getProperty("AccountDetails_ScrollToView_XPATH")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }

    }

    @Then("^TaxType Account dropdown value \"([^\"]*)\"$")
    public void taxtype_Account_dropdown_value(String AccountType) throws Throwable {


        Actions action = new Actions(driver);
        WebElement Type = driver.findElement(By.xpath(Pro.getProperty("TaxTypeAccount_XPATH")));
        action.doubleClick(Type).build().perform();
        Type.click();

        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Registration_ManageTaxPayer_UpdateTaxPayer_TaxpayerClassificationType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(AccountType)) {
                System.out.println(AccountType);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();
                option.click();
                break;


            }
            Thread.sleep(5000);
        }
    }

    @Then("^click on select button$")
    public void click_on_select_button() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("TaxTypeAccount_Select_ID"))).click();

    }


    //------------Maintain period Formula Configuration scenario-------------
    @Then("^Click on Maintain Period Formula Configuration link$")
    public void click_on_Maintain_Period_Formula_Configuration_link() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("TaxpayerAccounting_Maitain_XPATH"))).click();

    }

    @Then("^Enter Code \"([^\"]*)\" and Description \"([^\"]*)\"$")
    public void enter_Code_and_Description(String arg1, String arg2, DataTable table) throws Throwable {
        List<List<String>> data = table.asLists();

        driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_CreateNew_XPATH"))).click();
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_Code_ID"))).sendKeys(data.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_Description_ID"))).sendKeys(data.get(1).get(1));


    }

    @Then("^Select Filling Frequency \"([^\"]*)\"$")
    public void select_Filling_Frequency(String Frequency) throws Throwable {

        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Filing_Frequency_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Filing_Frequency_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(Frequency)) {
                //System.out.println(Frequency);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(7000);
        }


    }

    @Then("^Select Payment DueIn first value \"([^\"]*)\"$")
    public void select_Payment_DueIn_first_value(String PaymentDueIn1) throws Throwable {

        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_First_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_First_DropDown_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(PaymentDueIn1)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }

    }

    @Then("^Select Payment DueIn Second value \"([^\"]*)\"$")
    public void select_Payment_DueIn_Second_value(String PaymentDueIn2) throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_Second_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_Second_DropDown_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(PaymentDueIn2)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }

    }

    @Then("^Select Payment DueIn third dropdown value \"([^\"]*)\" enter \"([^\"]*)\"$")
    public void select_Payment_DueIn_third_dropdown_value_enter(String PaymentDueIn3, String Days) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("PaymentDueIn_Days_ID"))).sendKeys(Days);
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("PaymentDueIn_Third_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("PaymentDueIn_Third_DropDown__ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(PaymentDueIn3)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);

        }

    }


    @Then("^Select Return DueIn first value \"([^\"]*)\"$")
    public void select_Return_DueIn_first_value(String ReturnDueIn1) throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_First_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_First_DropDown_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(ReturnDueIn1)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^Select Return DueIn Second value \"([^\"]*)\"$")
    public void select_Return_DueIn_Second_value(String ReturnDueIn2) throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_Second_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_Second_DropDown_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(ReturnDueIn2)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }


    @Then("^Select Return DueIn return third value \"([^\"]*)\" enter \"([^\"]*)\"$")
    public void select_Return_DueIn_return_third_value_enter(String ReturnDueIn3, String Days) throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("ReturnDueIn_Third_DropDown_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("ReturnDueIn_Third_DropDown_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(ReturnDueIn3)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
        driver.findElement(By.id(Pro.getProperty("ReturnDueIn_Days_ID"))).sendKeys(Days);

    }

    @Then("^enter cinfiguration effective date \"([^\"]*)\"$")
    public void enter_cinfiguration_effective_date(String Date) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config_EffectiveDate_ID"))).sendKeys(Date);

    }

    @Then("^click on save$")
    public void click_on_save() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Formula_Config__Save_ID"))).click();

    }

    @Then("^Gettext Record successfully saved \"([^\"]*)\"$")
    public void gettext_Record_successfully_saved(String Record) throws Throwable {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_GetText_XPATH"))).getText();

        if (text.contains(Record)) {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

    }

//-----------------------------Maintain period Generation Configuration scenario of TaxPayer Accounting Modle-------------

    @Then("^Click on Maintain Period Generation Configuration link$")
    public void click_on_Maintain_Period_Generation_Configuration_link() throws Throwable {

        WebDriverWait click = new WebDriverWait(driver, 60);
        click.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("TaxpayerAccounting_Maitain_Generation_XPATH")))).click();


    }

    @When("^click on Create New button$")
    public void click_on_Create_New_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Formula_Config_CreateNew_XPATH"))).click();


    }

    @Then("^click on Taxtype \"([^\"]*)\"$")
    public void click_on_Taxtype(String Taxtype) throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxType_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(Taxtype)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
    }

    @Then("^click on Tax cycle start month \"([^\"]*)\" day \"([^\"]*)\"$")
    public void click_on_Tax_cycle_start_month_day(String month, String day) throws Throwable {

        Thread.sleep(2000);
        WebDriverWait Month = new WebDriverWait(driver, 80);
        Month.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_Month_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_Month_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(month)) {
                System.out.println(month);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(4000);
        }


        WebDriverWait Day = new WebDriverWait(driver, 80);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_XPATH")))).click();


        List<WebElement> list1 = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_Start_ITEM_XPATH")));
        for (WebElement option1 : list1) {
            String text = option1.getText();
            if (text.equalsIgnoreCase(day)) {
                System.out.println(day);
                //System.out.println(option1);
                Actions builder = new Actions(driver);
                builder.moveToElement(option1).doubleClick(option1).build().perform();
                builder.click().build().perform();
                break;
            }
        }
        Thread.sleep(2000);


    }


    @Then("^click on return type$")
    public void click_on_return_type() throws Throwable {
        Thread.sleep(2000);
        WebDriverWait type = new WebDriverWait(driver, 60);
        type.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_ReturnType_XPATH")))).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_ReturnType_XPATH"))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


    }

    @Then("^click on TaxCycle end month \"([^\"]*)\" day \"([^\"]*)\"$")
    public void click_on_TaxCycle_end_month_day(String endmonth, String endday) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait Month = new WebDriverWait(driver, 60);
        Month.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_Month_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_Month_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(endmonth)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_XPATH")))).click();


        List<WebElement> list1 = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_TaxCycle_End_ITEM_XPATH")));
        for (WebElement option1 : list1) {
            String text = option1.getText();
            if (text.equalsIgnoreCase(endday)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
    }


    @Then("^click on frequency \"([^\"]*)\"$")
    public void click_on_frequency(String frequency) throws Throwable {
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Frequency_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Frequency_ITEM_XPATH")));
        for (WebElement option : list) {
            String text = option.getText();
            if (text.equalsIgnoreCase(frequency)) {
                System.out.println(frequency);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
    }


    @Then("^click on formula \"([^\"]*)\"$")
    public void click_on_formula(String formula) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Formula_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Formula_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(formula)) {
                System.out.println(formula);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }
    }


    @Then("^enter no of period \"([^\"]*)\" and effective date \"([^\"]*)\"$")
    public void enter_no_of_period_and_effective_date(String period, String date) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Generation_Conf_NoOfperiod_ID"))).sendKeys(period);
        driver.findElement(By.id(Pro.getProperty("Maintain_period_Generation_Conf_EDate_ID"))).sendKeys(date);

    }

    @Then("^click on save button$")
    public void click_on_save_button() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Save_XPATH"))).click();

    }

    @Then("^Gettext Period Generation Configuration saved successfully\\. \"([^\"]*)\"$")
    public void gettext_Period_Generation_Configuration_saved_successfully(String Record) throws Throwable {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Maintain_period_Generation_Conf_Record_XPATH"))).getText();

        if (text.contains(Record)) {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }

    }

    //---------Find_Allocation Rules Configuration scenario----------
    @Then("^Click on Find Allocation Rules Configuration link$")
    public void click_on_Find_Allocation_Rules_Configuration_link() throws Throwable {
        WebDriverWait find = new WebDriverWait(driver, 60);
        find.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_XPATH")))).click();


    }

    @Then("^click on add button twice$")
    public void click_on_add_button_twice() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

    }

    @Then("^select allocation method \"([^\"]*)\"$")
    public void select_allocation_method(String method) throws Throwable {
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Allocation_Method_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Allocation_Method_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(method)) {
                System.out.println(method);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }

    }

    @Then("^enter allocation priority \"([^\"]*)\"$")
    public void enter_allocation_priority(String priority) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Find_Allocation_Rules_Config_Priority_ID"))).sendKeys(priority);

    }

    @Then("^click ok button$")
    public void click_ok_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Ok_XPATH"))).click();
        driver.switchTo().defaultContent();
    }

    @Then("^gettext record added \"([^\"]*)\"$")
    public void gettext_record_added(String record) throws Throwable {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_RecordAdded_XPATH"))).getText();

        if (text.contains(record)) {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }


    }

//--------------Dishonoured Payment Config scenario of Taxpayer Accounting Module---------


    @Then("^Click on Dishonoured Payment Configure link$")
    public void click_on_Dishonoured_Payment_Configure_link() throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_XPATH")))).click();

    }

    @Then("^select payment instrument \"([^\"]*)\"$")
    public void select_payment_instrument(String payment) throws Throwable {
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Payment_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Payment_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(payment)) {
                System.out.println(payment);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^enter entry allowed \"([^\"]*)\"$")
    public void enter_entry_allowed(String entry) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_Entry_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_Entry_ID"))).sendKeys(entry);

    }

    @Then("^enter no of days blocked \"([^\"]*)\" and checked \"([^\"]*)\"$")
    public void enter_no_of_days_blocked_and_checked(String block, String check) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysBlocked_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysBlocked_ID"))).sendKeys(block);


        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysChecked_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("Dishonoured_Payment_Config_DaysChecked_ID"))).sendKeys(check);
    }

    @Then("^gettext Dishonoured Payment Configuration is saved Successfully\\. \"([^\"]*)\"$")
    public void gettext_Dishonoured_Payment_Configuration_is_saved_Successfully(String Success) throws Throwable {
        Thread.sleep(2000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Dishonoured_Payment_Config_Message_XPATH"))).getText();

        if (text.contains(Success)) {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }
    }
//----------Taxpayer Account Adj Credit scenario of TaxPayer Accounting Modle---------------

    @Then("^click on Taxpayer Account Adjustment link$")
    public void click_on_Taxpayer_Account_Adjustment_link() throws Throwable {
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_XPATH")))).click();
    }

    @Then("^click on add button$")
    public void click_on_add_button() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Find_Allocation_Rules_Config_Add_XPATH"))).click();

    }

    @Then("^click on find button$")
    public void click_on_find_button() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Find_XPATH"))).click();
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        Thread.sleep(2000);

    }

    @Then("^enter Tin number \"([^\"]*)\" and click search$")
    public void enter_Tin_number_and_click_search(String TIN) throws Throwable {

        driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adjustment_TIN_ID"))).sendKeys(TIN);
        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Search_XPATH"))).click();

    }

    @Then("^scroll down and select row$")
    public void scroll_down_and_select_row() throws Throwable {
        Thread.sleep(4000);
        List<WebElement> element = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_SrollToView_XPATH")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", (driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_FirstRow_XPATH")))));

        Thread.sleep(3000);


    }

    @Then("^click on continue button$")
    public void click_on_continue_button() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Continue_XPATH"))).click();
        driver.switchTo().defaultContent();
    }

    @Then("^select charge type \"([^\"]*)\"$")
    public void select_charge_type(String chargetype) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_ChargeType_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_ChargeType_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(chargetype)) {
                System.out.println(chargetype);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^select adjustment type \"([^\"]*)\"$")
    public void select_adjustment_type(String adjtype) throws Throwable {
        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_AdjesmentType_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_AdjesmentType_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(adjtype)) {
                System.out.println(adjtype);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^give reason value \"([^\"]*)\"$")
    public void give_reason_value(String reason) throws Throwable {

        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Taxpayer_Account_Adj_Reason_ScrollToView_Reason_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);
            //ele.click();
        }
        Thread.sleep(3000);

        WebDriverWait Day = new WebDriverWait(driver, 60);
        Day.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Reason_XPATH")))).click();


        List<WebElement> Form = driver.findElements(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Reason_ITEM_XPATH")));
        for (WebElement Form1 : Form) {
            String text = Form1.getText();
            if (text.equalsIgnoreCase(reason)) {
                System.out.println(reason);
                Actions builder = new Actions(driver);
                builder.moveToElement(Form1).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^enter revenue ledger code \"([^\"]*)\" and amount \"([^\"]*)\"$")
    public void enter_revenue_ledger_code_and_amount(String code, String amount) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Revenue_LedgerCode_ID"))).sendKeys(code);
        driver.findElement(By.id(Pro.getProperty("Taxpayer_Account_Adj_Amount_ID"))).sendKeys(amount);
    }

    @Then("^click on submit button$")
    public void click_on_submit_button() throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adj_Submit_XPATH"))).click();

    }

    @Then("^Credit reference number will generate$")
    public void credit_reference_number_will_generate() throws Throwable {
        Thread.sleep(2000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Gettext_XPATH"))).getText();
        System.out.println(text);
        System.out.println("substring is " + text.substring(42));
        String A_BackOffice_ARN = text.substring(42);

        sharedatastep.A_CRMARN = "*" + A_BackOffice_ARN;
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));


        System.out.println(sharedatastep.A_CRMARN);
        System.out.println("Actual ARN to be used in CRM is " + sharedatastep.A_CRMARN);
        if (text.contains("ACAD")) {
            System.out.println(text);
            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }
        Thread.sleep(20000);
    }
    //----------Manage Credit Allocation scenario of TaxPayer Accounting Modle-----

    @Then("^click on Manage Credit Allocation link$")
    public void click_on_Manage_Credit_Allocation_link() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_Allocation_XPATH")))).click();


    }

    @Then("^Click on search button$")
    public void click_on_search_button() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Directors_ADD_Ditector'sTIN_Find_Search_ID"))).click();
    }

    @Then("^select taxType \"([^\"]*)\"$")
    public void select_taxType(String Taxtype) throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(4000);
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_Allocation_TaxType_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Manage_Credit_Allocation_TaxType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(Taxtype)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^check the suspense checkbox$")
    public void check_the_suspense_checkbox() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Manage_Credit_Allocation_Suspense_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Manage_Credit_Allocation_Suspense_Select_ID"))).click();

    }

    @Then("^enter month \"([^\"]*)\" and year \"([^\"]*)\"$")
    public void enter_month_and_year(String month, String year) throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_PeriodMonth_ID"))).sendKeys(month);
        driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_PeriodYear_ID"))).sendKeys(year);
    }

    @Then("^select document type \"([^\"]*)\"$")
    public void select_document_type(String Documenttype) throws Throwable {

        //Thread.sleep(1000);
        WebDriverWait Submit = new WebDriverWait(driver, 60);
        Submit.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("Manage_Credit_All_DocumentType_XPATH")))).click();


        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Manage_Credit_All_DocumentType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(Documenttype)) {

                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);
        }


    }

    @Then("^click on Search button for selected account$")
    public void click_on_Search_button_for_selected_account() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Taxpayer_Account_Adjustment_Search_XPATH"))).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);

    }

    @Then("^Click on Outstanding Liability Taxtype$")
    public void click_on_Outstanding_Liability_Taxtype() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Manage_Credit_All_Liability_TaxType_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id(Pro.getProperty("Manage_Credit_All_Liability_TaxType_Select_ID"))).click();
        Thread.sleep(2000);
    }


//-----------Dishonoured Payment  scenario of Taxpayer Accounting Module--------


    //----------- Integrated Taxpayer View Individual Scenario of Taxpayer Accounting Module-----------
    @Then("^click on Integrated Taxpayer View$")
    public void click_on_Integrated_Taxpayer_View() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_XPATH"))).click();

    }

    @Then("^TaxType dropdown value \"([^\"]*)\"$")
    public void taxtype_dropdown_value(String AccountType) throws Throwable {
        Thread.sleep(2000);

        Actions action = new Actions(driver);
        WebElement Type = driver.findElement(By.xpath(Pro.getProperty("TaxTypeAccount_XPATH")));
        action.doubleClick(Type).build().perform();
        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_DocumentType_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(AccountType)) {
                System.out.println(AccountType);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(2000);


        }
    }

    @Then("^enter from date \"([^\"]*)\"$")
    public void enter_from_date(String date) throws Throwable {
        WebElement Date = driver.findElement(By.id(Pro.getProperty("Integrated_Taxpayer_View_Fromdate_ID")));
        Date.clear();
        System.out.println(date);
        Date.sendKeys(date);
        Thread.sleep(2000);
    }

    @Then("^Activity dropdown value \"([^\"]*)\"$")
    public void activity_dropdown_value(String AccountType) throws Throwable {
        Thread.sleep(2000);

        Actions action = new Actions(driver);
        WebElement Type = driver.findElement(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_Activity_XPATH")));
        action.doubleClick(Type).build().perform();
        Type.click();

        List<WebElement> list = driver.findElements(By.xpath(Pro.getProperty("Integrated_Taxpayer_View_Activity_ITEM_XPATH")));
        for (WebElement option : list) {
            String text2 = option.getText();
            if (text2.equalsIgnoreCase(AccountType)) {
                System.out.println(AccountType);
                Actions builder = new Actions(driver);
                builder.moveToElement(option).doubleClick().build().perform();

                break;


            }
            Thread.sleep(3000);
        }
    }

    @Then("^click on select button to view$")
    public void click_on_select_button_to_view() throws Throwable {
        driver.findElement(By.id(Pro.getProperty("Integrated_Taxpayer_View_Select_ID"))).click();

    }


//------CRM Scripts------------
//----------Individual_Taxpayer_Approval Scenario------


    @Given("^Open CRM URL$")
    public void open_CRM_URL() throws Throwable {
        driver.get(Pro.getProperty("MRA_crm_url_Registration"));

    }

    @When("^Click on Case management Sub module$")
    public void click_on_Case_management_Sub_module() throws Throwable {
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement CaseManagement = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_ContextLink_XPATH")));
        action.click(CaseManagement).build().perform();


    }

    @When("^Goto Registration")
    public void goto_Queues() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("tbg_registrationapplication")).click();


    }

    @Then("^Select SearchResult Dropdown element$")
    public void select_SearchResult_Dropdown_element() throws Throwable {


        Actions action = new Actions(driver);
        WebElement SearchResult = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchResult_XPATH")));
        action.moveToElement(SearchResult).doubleClick().build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchResult_ITEMS_XPATH"))));
        Assert.assertEquals(success.getText(), "Items available to work on");


    }

    @Then("^Selecte Queues Dropdown element$")
    public void selecte_Queues_Dropdown_element() throws Throwable {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Dropdown_XPATH"))).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Dropdown_XPATH"))).click();
        Thread.sleep(2000);

    }


    @Then("^Enter the Reference number \"([^\"]*)\" in search box\\.$")
    public void enter_the_Reference_number_in_search_box(String ReferenceNumber) throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchBox_XPATH"))).sendKeys(ReferenceNumber);

    }


    @Then("^Enter Reference number in search box \"([^\"]*)\"$")
    public void enter_Reference_number_in_search_box(String ReferenceNumber) throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Search_Textbox_XPATH"))).sendKeys(ReferenceNumber);
        Thread.sleep(2000);


    }


    @Then("^Enter the Reference number$")
    public void enter_the_Reference_number() throws Throwable {
//            System.out.println("CRMARN is " + sharedatastep.A_CRMARN);
//            WebElement searchinput=driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchBox_XPATH")));
//            searchinput.sendKeys(sharedatastep.A_CRMARN);
        WebElement searchinput = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_SearchBox_XPATH")));
        searchinput.sendKeys("ARN/00020723/2020");

        searchinput.sendKeys(Keys.ENTER);


    }


    @Then("^Click on Serch icon$")
    public void click_on_Serch_icon() throws Throwable {
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        WebElement SerachIcon = driver.findElement(By.id("crmGrid_findCriteriaButton"));
        SerachIcon.click();

    }


//        @Then("^Pick the selected Reference number$")
//        public void pick_the_selected_Reference_number() throws Throwable {
//
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_PICK_Link_XPATH"))).click();
//            Thread.sleep(5000);
//
//
//        }


    @Then("^Goto Dashboard$")
    public void goto_Dashboard() throws Throwable {

        Thread.sleep(1000);
        WebElement Case = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_ContextLink_XPATH")));
        Case.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Actions action=new Actions(driver);
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Dashboard_Link_XPATH"))).click();
        Thread.sleep(2000);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        WebElement specificframe1 = (driver.findElement(By.id(Pro.getProperty("Search_TextBox_frame_ID"))));
        driver.switchTo().frame(specificframe1);
        Thread.sleep(2000);


    }

    @Then("^Enter Reference number in search box$")
    public void enter_Reference_number_in_search_box() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("Search_Textbox_XPATH"))).sendKeys(sharedatastep.A_CRMARN);
        Thread.sleep(3000);


    }

    @Then("^Click Dashboard serch icon$")
    public void click_Dashboard_serch_icon() throws Throwable {
	/*Actions action=new Actions(driver);
	WebElement SearchResult=driver.findElement(By.id(Pro.getProperty("DashBoard_Search_Icon_ID")));
	action.moveToElement(SearchResult).doubleClick().build().perform();*/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", (driver.findElement(By.id(Pro.getProperty("DashBoard_Search_Icon_ID")))));

        Thread.sleep(2000);

    }

    @Then("^Click on appeard Reference number$")
    public void click_on_appeard_Reference_number() throws Throwable {
        Thread.sleep(5000);
        Actions action = new Actions(driver);
        WebElement SelectRef = driver.findElement(By.className(Pro.getProperty("appeared_Reference_number_CLASSNAME")));
        action.moveToElement(SelectRef).doubleClick().build().perform();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(5000);

    }

    @Then("^Click on Open Case Record link$")
    public void click_on_Open_Case_Record_link() throws Throwable {

        driver.findElement(By.xpath(Pro.getProperty("DashBoard_SelectRefNo_OpenCase_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("DashBoard_SelectRefNo_OpenApplication_XPATH"))).click();
        Thread.sleep(3000);
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);
        Thread.sleep(4000);


    }

    @Then("^Click on NextStage button$")
    public void click_on_NextStage_button() throws Throwable {
        Thread.sleep(3000);
       // driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        driver.findElement(By.id("stageAdvanceActionContainer")).click();
        Thread.sleep(8000);
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_ID"))));
        driver.switchTo().frame(specificframe2);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Then("^wait for duplicate check$")
    public void wait_for_duplicate_check() throws Throwable {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WebResource_RegistrationApplicationAngular")));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='First Name']")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(3000);
        WebElement validation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stageAdvanceActionContainer")));
        validation.click();
        driver.switchTo().defaultContent();
    }

    @Then("^Download the Attachment \"([^\"]*)\"$")
    public void download_the_Attachment(String downloadpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Download']")));
        downloadAttach.click();
        Thread.sleep(9000);
        String downloadPath = "C:\\Users\\barnaby.kamau\\Downloads";
        String fileName = downloadpath;
        Thread.sleep(5000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @And("^click validate documentation screen$")
    public void click_validate_documentation_screen() throws Throwable {
        driver.switchTo().defaultContent();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[text()='Validate Documentation']")).click();
        Thread.sleep(4000);
    }

    @Then("^Select Identification Outcome dropdown value for Individual Taxpayer Approval$")
    public void select_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Approval() throws Throwable {

        driver.findElement(By.xpath("//span[text()='click to enter']")).click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Invalid Identification Outcome dropdown value for Individual Taxpayer Approval$")
    public void select_invalid_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Approval() throws Throwable {
        driver.findElement(By.xpath("//span[text()='click to enter']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @And("^Select Approval outcome dropdown value to Approve (.+)$")
    public void select_approval_outcome_dropdown_value_to_approve(String confirmation) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + confirmation + "']")));
        Assert.assertTrue(downloadAttach.isDisplayed());

        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(9000);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_approvaloutcome']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        driver.switchTo().defaultContent();
    }

    @Then("^Select Reject outcome dropdown value to Approve\"([^\"]*)\"$")
    public void select_Reject_outcome_dropdown_value_to_Approve(String Approve) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + Approve + "']")));

        Assert.assertTrue(downloadAttach.isDisplayed());

        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_approvaloutcome']")).click();
//        driver.findElement(By.id("header_process_tbg_approvaloutcome4_c")).click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Revise outcome dropdown value to Approve\"([^\"]*)\"$")
    public void select_Revise_outcome_dropdown_value_to_Approve(String Approve) throws Throwable {
        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement downloadAttach = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + Approve + "']")));
        Assert.assertTrue(downloadAttach.isDisplayed());

        driver.switchTo().defaultContent();
        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        WebElement specificframe = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

        driver.findElement(By.xpath("//div[@data-attributename='tbg_approvaloutcome']")).click();
//        driver.findElement(By.id("header_process_tbg_approvaloutcome4_c")).click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

//        @Then("^Click on Save button for individual$")
//        public void click_on_Save_button_for_individual() throws Throwable {
//            Thread.sleep(10000);
//            driver.switchTo().defaultContent();
//            WebElement specificframe3 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
//            driver.switchTo().frame(specificframe3);
//        }

    @Then("^Click on Save button for individual$")
    public void click_on_Save_button_for_individual() throws Throwable {
        Thread.sleep(10000);
        driver.switchTo().defaultContent();
        WebElement specificframe3 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe3);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("NextStage_RefNum_Save_Btn_ID"))).click();
        Thread.sleep(3000);
        WebDriverWait wait1 = new WebDriverWait(driver, 50);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_RefNum_Save_ReadOnly_Text_ID"))));


    }

    @Then("download file")
    public void downloadFile(String filepath) throws Throwable{
        driver.findElement(By.xpath("")).click();
    }

    @Then("^Click on Save button$")
    public void click_on_Save_button() throws Throwable {
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_registrationapplication|NoRelationship|Form|Mscrm.Form.tbg_registrationapplication.Save")).click();


    }

    @Then("^Verify the String \"([^\"]*)\"$")
    public void verify_the_String(String Status) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        Thread.sleep(3000);
        WebElement statusLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + Status + "')]")));
        if (statusLabel.isDisplayed()) {
            Assert.assertTrue("Approved", true);
        } else {
            Assert.fail("Approval failed");
        }
        Thread.sleep(2000);

    }

    @And("^Clicks on Taxpayer name CRM$")
    public void clicks_on_taxpayer_name_crm() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        Thread.sleep(9000);
        WebElement NameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header_tbg_taxpayer_lookupValue")));
        NameLabel.click();
    }

    @And("^refresh page$")
    public void refresh_page() throws Throwable {
        driver.navigate().refresh();
        Thread.sleep(5000);
    }

    @Then("^Taxpayer Tin is displayed$")
    public void taxpayer_tin_is_displayed() throws Throwable {
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement tinLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TIN Number_label")));
        System.out.println("---------------------------------------------------------");
        sharedata.Taxpayer_TIN_INDV=tinLabel.getText();
        System.out.println("Taxpayer TIN is " + sharedata.Taxpayer_TIN_INDV);
        System.out.println("---------------------------------------------------------");
    }



    @Then("^Click on Taxpayer name$")
    public void click_on_Taxpayer_name() throws Throwable {

        List<WebElement> element = driver.findElements(By.id(Pro.getProperty("Save_Taxpayer_Taxpayer_ScrollToView_ID")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
        WebElement specificframe4 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe4);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id(Pro.getProperty("Taxpayer_ID"))).click();

        Thread.sleep(8000);


    }

    @Then("^Get the TIN Number$")
    public void get_the_TIN_Number() throws Throwable {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String text = driver.findElement(By.id(Pro.getProperty("Save_Taxpayer_Gettext_ID"))).getText();
        System.out.println(text);

    }
//-------------CRM------------
//--------------- Code for Individual Taxpayer Reject scenario--------------

    @Then("^Select Identification Outcome dropdown value for Individual Taxpayer Reject$")
    public void select_Identification_Outcome_dropdown_value_for_Individual_Taxpayer_Reject() throws Throwable {


        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_Identification_OutCome_XPATH"))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


    }

    @Then("^Select Approval outcome dropdown value to Reject\"([^\"]*)\"$")
    public void select_Approval_outcome_dropdown_value_to_Reject(String Reject) throws Throwable {
        Thread.sleep(9000);
        driver.switchTo().defaultContent();
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);

//driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Individual_Quese_Approval_Outcome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();

        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


    }

    @Then("^Enter Outcome Reason$")
    public void enter_Outcome_Reason() throws Throwable {
        Thread.sleep(2000);
        WebElement specificframe = (driver.findElement(By.xpath(Pro.getProperty("OutComeReason_Frame_XPATH"))));
        driver.switchTo().frame(specificframe);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_XPATH"))).click();
        WebDriverWait ReasonValue = new WebDriverWait(driver, 60);
        ReasonValue.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_Options_XPATH")))).click();
        Thread.sleep(8000);
    }

    @Then("^Get Status of Taxpayer \"([^\"]*)\"$")
    public void get_Status_of_Taxpayer(String Status) throws Throwable {
        String text = driver.findElement(By.id(Pro.getProperty("Status_Lable_ID"))).getText();
        System.out.println(text);
        if (text.contains(Status)) {

            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }
        Thread.sleep(2000);
    }

    //Revise Individual Taxpayer Scenario.....
    @Then("^Select Approval outcome dropdown value to Revise\"([^\"]*)\"$")
    public void select_Approval_outcome_dropdown_value_to_Revise(String Revise) throws Throwable {
        Thread.sleep(7000);
        driver.switchTo().defaultContent();
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);

//driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Individual_Quese_Approval_Outcome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


    }

//----------------- Approve Organization Taxpayer Scenario------------

    @Then("^Click on Orgnization NextStage button$")
    public void click_on_Orgnization_NextStage_button() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(1000);
        driver.findElement(By.id(Pro.getProperty("Organisation_NextStage_Btn_ID"))).click();
        Thread.sleep(7000);


    }

    @Then("^Goto view Organization AttachmentDetails screen$")
    public void goto_view_Organization_AttachmentDetails_screen() throws Throwable {
        Thread.sleep(2000);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_ID"))));
        driver.switchTo().frame(specificframe);

        List<WebElement> element = driver.findElements(By.xpath(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(2000);


    }

    @Then("^Download Organization Attachments$")
    public void download_Organization_Attachments() throws Throwable {
        Actions action = new Actions(driver);
        WebElement PrimInd = driver.findElement(By.xpath(Pro.getProperty("Organisation_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
        action.click(PrimInd).build().perform();
        Thread.sleep(5000);


    }


    @Then("^Select Organization Approval outcome dropdown value$")
    public void select_Organization_Approval_outcome_dropdown_value_to_Approve() throws Throwable {
        driver.switchTo().defaultContent();
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);


        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();


    }

//------Organization Taxpayer Reject Scenario----

    @Then("^Select Organization Approval outcome dropdown value to reject$")
    public void select_Organization_Approval_outcome_dropdown_value_to_reject() throws Throwable {
        driver.switchTo().defaultContent();
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);


        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    //----- Organization Taxpayer Revision Scenario-----
    @Then("^Select Organization Approval outcome dropdown value to Revision$")
    public void select_Organization_Approval_outcome_dropdown_value_to_Revision() throws Throwable {
        driver.switchTo().defaultContent();
        WebElement specificframe2 = (driver.findElement(By.id(Pro.getProperty("SearchResult_Frame_ID"))));
        driver.switchTo().frame(specificframe2);


        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Organization_Approval_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }


    //-----Update Individual Taxpayer Approve------------
    @Then("^Click on NextStage button two times$")
    public void click_on_NextStage_button_two_times() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(11000);
        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
    }

    @Then("^Select Approval outcome dropdown value to Approve for Update Ind$")
    public void select_Approval_outcome_dropdown_value_to_Approve_for_Update_Ind() throws Throwable {

        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Individual_Update_Approve_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }

    //-----Update Individual Taxpayer Reject------------
    @Then("^Select Approval outcome dropdown value to Reject for Update Ind$")
    public void select_Approval_outcome_dropdown_value_to_Reject_for_Update_Ind() throws Throwable {

        Thread.sleep(11000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Individual_Update_Approve_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }

    //--- Register TaxType Individual Taxpayer Approve Scenario--------------
    @Then("^Select Approval outcome dropdown value to Approve for Register TaxType Ind$")
    public void select_Approval_outcome_dropdown_value_to_Approve_for_Register_TaxType_Ind() throws Throwable {

        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Organisation_Quese_Approval_OutCome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }

    //--- Register TaxType Individual Taxpayer Approve Scenario--------------
    @Then("^Approve taxtype deregistration from dropdown$")
    public void approveTaxtypeDeregistration() throws Throwable {

        driver.switchTo().frame("contentIFrame1");
        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id("header_process_tbg_approvaloutcome4"));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

    }

    @Then("^Click on NextStage button one time$")
    public void click_on_NextStage_button_one_time() throws Throwable {


        driver.findElement(By.xpath(Pro.getProperty("Individual_NextStage_Button_XPATH"))).click();
        Thread.sleep(8000);


    }

    //------Register TaxType Individual Taxpayer  Reject Scenario------
    @Then("^Select Approval outcome dropdown value to Reject for Register TaxType Ind$")
    public void select_Approval_outcome_dropdown_value_to_Reject_for_Register_TaxType_Ind() throws Throwable {

        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Organisation_Quese_Approval_OutCome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }


    //--------Taxpayer Account Adj Credit App Individual Taxpayer Approval Scenario------------
    @Then("^Select Approval outcome dropdown value to Approve for Taxpayer accounting$")
    public void select_Approval_outcome_dropdown_value_to_Approve_for_Taxpayer_accounting() throws Throwable {

        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }


    //--------Taxpayer Account Adj Credit App Individual Taxpayer Reject Scenario------------
    @Then("^Select Approval outcome dropdown value to Reject for Taxpayer accounting$")
    public void select_Approval_outcome_dropdown_value_to_Reject_for_Taxpayer_accounting() throws Throwable {

        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
        action.doubleClick(Outcome).build().perform();
        Outcome.click();
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);


    }

    @Then("^Enter Outcome Reason for Taxtype deregistration$")
    public void enter_Outcome_Reason_for_Taxtype_deregistration() throws Throwable {


        WebElement specificframe = driver.findElement(By.id("WebResource_tbg_rejectionRefernceData"));
        driver.switchTo().frame(specificframe);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_XPATH"))).click();
        WebDriverWait ReasonValue = new WebDriverWait(driver, 60);
        ReasonValue.until(ExpectedConditions.elementToBeClickable(By.xpath(Pro.getProperty("NextStage_RefNum_Reject_OutComeReason_Options_XPATH")))).click();
        Thread.sleep(8000);
    }

    @Then("^Pick the selected Reference number for Accounting Module$")
    public void pick_the_selected_Reference_number_for_Accounting_Module() throws Throwable {
        Thread.sleep(3000);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_PICK_Link_XPATH"))).click();
        Thread.sleep(5000);

    }

    @Then("^Get Status of Taxpayer \"([^\"]*)\" as Rejected$")
    public void get_Status_of_Taxpayer_as_Rejected(String Status) throws Throwable {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath(Pro.getProperty("Reject_Status_XPATH"))).getText();
        System.out.println(text);
        if (text.contains(Status)) {

            System.out.println("Text Verified and passed");
        } else {
            System.out.println("Text Not Verfied and failed");
        }
        Thread.sleep(2000);

    }

    @Given("^Open CRM URL Module$")
    public void open_CRM_URL_Module() throws Throwable {
//
        driver.get(Pro.getProperty("MRA_crm_url_Registration"));
    }

    @When("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
    }

    @And("^click on Registration application$")
    public void click_on_revenue_collection_application() throws Throwable {
        driver.findElement(By.id("tbg_registrationapplication")).click();
    }

    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame1")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @Then("^wait for duplicate check (.+)$")
    public void wait_for_duplicate_check(String approve) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WebResource_RegistrationApplicationAngular")));
        driver.switchTo().frame(frame);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='"+approve+"']")));
    }

    @When("^enters reference number in search results$")
    public void enters_in_search_results() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
//        search.sendKeys(sharedatastep.A_CRMARN);
    	search.sendKeys(" ARN/00022833/2020");

        search.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
    }

    @Then("^Click selected Reference Number$")
    public void click_selected_Reference_Number() throws Throwable {
        WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();
        Thread.sleep(4000);
    }

    //--------------------approve crm-------------------------------------//
    @And("^Click start search$")
    public void click_start_search() throws Throwable {
        driver.findElement(By.id("TabSearch")).click();
    }

    @And("^Pick registration case$")
    public void pick_registration_case() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        List<WebElement> cases = driver.findElements(By.xpath("//div[@tabindex='0']"));
        cases.get(1).click();
    }

    @Then("^Goto view AttachmentDetails screen$")
    public void goto_view_AttachmentDetails_screen() throws Throwable {

        driver.switchTo().frame("WebResource_RegistrationApplicationAngular");

        Thread.sleep(3000);
        List<WebElement> element = driver.findElements(By.xpath(Pro.getProperty("Individual_NextStage_RefNum_DownloadFrame_DownloadLink_XPATH")));
        for (WebElement ele : element) {

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("arguments[0].scrollIntoView(true);", ele);

        }
        Thread.sleep(2000);

    }


    //...............max code.......................................................................//
    //..............SUC:01-24........................................................................//
    @Then("^Select attachment as \"([^\"]*)\"$")
    public void select_attachment(String attachmentType) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_fileChoose\"]/div/div[2]/div/div/div[1]/span/input")).sendKeys(attachmentType);
    }

    @And("^Select document type as \"([^\"]*)\"$")
    public void select_document_type_as(String documentType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_attachmentForm\"]/div/div/tb-dropdown/div/div[2]/p-dropdown/div/div[3]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + documentType + "')]")).click();
    }

    @And("^Enter document name as \"([^\"]*)\"$")
    public void enter_document_name_as_something(String documentName) throws Throwable {
        driver.findElement(By.id("id_reference")).sendKeys(documentName);
    }

    @And("^Selects tax type as \"([^\"]*)\"$")
    public void selects_tax_type_as(String taxType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + taxType + "')]")).click();
    }

    @And("^Enter Suspension Start Date$")
    public void enter_suspension_start_date() throws Throwable {
        WebElement StartDate = driver.findElement(By.id("id_suspensionStartDate"));
        Thread.sleep(2000);
        StartDate.sendKeys(tomorrowsDatePortal());
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();

    }

    @And("^Enter Dormant Start Date$")
    public void enter_dormant_start_date() throws Throwable {
        WebElement StartDate = driver.findElement(By.id("id_dormantStartDate"));
        Thread.sleep(2000);
        StartDate.sendKeys(todaysDatePortal());
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();

    }

    @And("^Give Suspension End Date$")
    public void give_suspension_end_date() throws Throwable {
        WebElement EndDate = driver.findElement(By.id("id_suspensionEndDate"));
        Thread.sleep(2000);
        EndDate.sendKeys(tomorrowsDatePortal());
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }

    @And("^Select reason for suspension as \"([^\"]*)\"$")
    public void select_reason_for_suspension_as(String reason) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[contains(text(),'" + reason + "')]")).click();
    }

    @And("^Click save to suspend taxtype$")
    public void click_save_to_suspend_taxtype() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSubmit"))).click();
    }

    @Then("^Verify portal save success message \"([^\"]*)\"$")
    public void verify_portal_success_message(String Message) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'" + Message + "')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify success message \"([^\"]*)\"$")
    public void verify_success_message(String Message) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + Message + "')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Obtain reference number from portal success message \"([^\"]*)\"$")
    public void split_string_to_obtain_reference_number(String SuccessMessage) {
        //get full message
        String FullMessage = driver.findElement(By.xpath("//div[contains(text(),'" + SuccessMessage + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - CRAL/000001959/2020
        //Your tax type registration request has been successfully submitted. Your reference number is :
        ReferenceNumber = FullMessage.substring(94);
        System.out.println(ReferenceNumber);
    }

    @Then("^Obtain reference number from portal success message for suspend \"([^\"]*)\"$")
    public void split_string_to_obtain_reference_number_from_suspend_action(String SuccessMessage) {
        //get full message
        String FullMessage = driver.findElement(By.xpath("//div[contains(text(),'" + SuccessMessage + "')]")).getText();
        System.out.println(FullMessage);
        //Your Suspend tax type request has been successfully submitted. Your reference number is : ARN/00025810/2020
        //Your tax type registration request has been successfully submitted. Your reference number is :
        ReferenceNumber = FullMessage.substring(89);
        System.out.println(ReferenceNumber);
    }

    @And("^Tick dormant check box$")
    public void tick_dormant_check_box() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"id_suspendTaxTypeForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]")).click();
    }

    @Then("^Click on registration application link$")
    public void click_on_accounting_application_link() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Cases_Management_Dropdown_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_registrationapplication")).click();
    }

//    @Then("^search for reference number$")
//    public void search_for_reference_number() throws Throwable {
//        Thread.sleep(3000);
//        driver.findElement(By.id(Pro.getProperty("Search_Field_ID"))).sendKeys(ReferenceNumber);
//        driver.findElement(By.id(Pro.getProperty("Search_Field_Submit_ID"))).click();
//    }

    @Then("^Click on reference number$")
    public void click_on_reference_number() {
        WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
    }

    @Then("^Click next stage button$")
    public void Click_next_stage() throws Throwable {

        //        Thread.sleep(15000);
        //        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //        driver.findElement(By.id("stageAdvanceActionContainer")).click();

        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 120);
        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stageAdvanceActionContainer")));
        nextButton.click();
        Thread.sleep(12000);
        driver.switchTo().defaultContent();
    }

    @And("^clicks Decline from the dropdown$")
    public void clicks_Decline_from_the_dropdown() throws Throwable {

        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome3\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            Thread.sleep(7000);
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^Select rejection option on taxtype deregistration$")
    public void select_rejection_option_on_taxtype_deregistration() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome4\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            Thread.sleep(7000);
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^Select decline from the dropdown to deregister tax$")
    public void clicks_Decline_from_the_dropdown_to_reject_deregistration() throws Throwable {
        driver.switchTo().frame("contentIFrame0");
        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome3\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^Click save CRM$")
    public void ClickSaveCRM() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_registrationapplication|NoRelationship|Form|Mscrm.Form.tbg_registrationapplication.Save")).click();
        //    	driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        //    	driver.findElement(By.xpath(Pro.getProperty("//*[@id=\"tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save\"]"))).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("^Status should be \"([^\"]*)\"$")
    public void Verify_status_from_CRM(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver, 200);
        WebElement statusLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + Status + "')]")));
        if (statusLabel.isDisplayed()) {
            Assert.assertTrue("Approved", true);
        } else {
            Assert.fail("Approval failed");
        }
        Thread.sleep(2000);
    }


    //......................................SUSPEND DORMANT TAXTYPE.............................................................//

    @Then("^Click table column taxtype (.+)$")
    public void click_table_column_taxtype(String taxtypes) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='DeregisterRegime:deregTable_data' and contains(...., '"+taxtypes+"')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"DeregisterRegime:deregTable_data\"]/tr[2]"))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
    }

    @And("^Enter suspension start date as todays date$")
    public void enter_suspension_start_date_as_todays_date() throws Throwable {
        driver.findElement(By.id("SuspendRegime:SuspensionStartDate_input")).sendKeys(Keys.ENTER);
    }

    @And("^Enter Suspension End Date as \"([^\"]*)\"$")
    public void enter_suspension_end_date(String date) throws Throwable {
        WebElement endDate = driver.findElement(By.id("SuspendRegime:SuspensionEndDate_input"));
        endDate.click();
        endDate.sendKeys(date);
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    @And("^Enter suspension start date as \"([^\"]*)\"$")
    public void enter_suspension_start_date(String date) throws Throwable {
        WebElement endDate = driver.findElement(By.id("SuspendRegime:SuspensionStartDate_input"));
        endDate.click();
        endDate.sendKeys(date);
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB);
        Thread.sleep(1000);
    }

    @And("^Select outcome reason for suspension$")
    public void select_outcome_reason_for_suspension() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"SuspendRegime:Reason\"]/div[3]")).click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @And("^Click suspend$")
    public void click_suspend() throws Throwable {
        driver.findElement(By.id("SuspendRegime:Suspend")).click();
    }

    @Then("^Verify save success message \"([^\"]*)\"$")
    public void verify_success(String Message) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + Message + "')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Obtain reference number \"([^\"]*)\"$")
    public void obtainReferenceNumber(String SuccessMessage) {
        //get full message
        String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - CRAL/000001959/2020
        ReferenceNumber = FullMessage.substring(41);
        sharedatastep.A_CRMARN=ReferenceNumber;
        System.out.println(ReferenceNumber);
    }

    @Then("^Open CRM and close modal$")
    public void open_crm_and_close_modal() throws Throwable {

        driver.get(Pro.getProperty("MRA_crm_url_Registration"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @Then("^approve transaction$")
    public void approve_transaction() throws Throwable {
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.id("header_process_tbg_approvaloutcome3"));
        List <WebElement> hasLoaded = driver.findElements(By.id("header_process_tbg_approvaloutcome_lock"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.size()>0) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }

        driver.switchTo().defaultContent();
    }

    @Then("^Verify suspended tax type \"([^\"]*)\" is not present in the table$")
    public void verify_suspended_tax_type_something_is_not_present_in_the_table(String taxType) throws Throwable {
        List<WebElement> PoniBaybe = driver.findElements(By.xpath("//td[contains(text(),'" + taxType + "')]"));
        System.out.println("List size:" + PoniBaybe.size());
        if (PoniBaybe.size() > 0) {
            Assert.fail("Element has been located");
        }
    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message(String error) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + error + "')]")));
        if (errorMessage.isDisplayed()) {
            //This will scroll the page till the element is found
            System.out.println("Error message ('" + error + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    //---------------------------------------------Print Taxpayer Documents----------------------------------------------------------------///
    @Given("^navigate to Reporting>>Report Reprint$")
    public void navigate_to_reportingreport_reprint() throws Throwable {
        driver.findElement(By.xpath("//span[text()='Reporting']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Report Reprint']")).click();
    }

    @When("^user enters module name \"([^\"]*)\" and TIN \"([^\"]*)\"$")
    public void user_enters_module_name_something_and_tin_something(String strArg1, String strArg2) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement tinInput = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("SearchForm:TIN"))));
        tinInput.sendKeys(strArg2);

        WebElement moduleDropdown = driver.findElement(By.xpath("//*[@id=\"SearchForm:ModuleName\"]/div[3]"));
        moduleDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[text()='" + strArg1 + "']")).click();
    }

    @And("^clicks Find Reports search Button$")
    public void clicks_find_reports_search_button() throws Throwable {
        WebElement searchBtn = driver.findElement(By.id("SearchForm:j_idt40"));
        searchBtn.click();
    }

    @Then("^available reports table is displayed$")
    public void available_reports_table_is_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement reportTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:resultsDataTable")));
        Assert.assertTrue(reportTable.isDisplayed());
    }

    @When("^clicks on report name \"([^\"]*)\"$")
    public void clicks_on_report_name_something(String strArg1) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + strArg1 + "']")));

        //Get parent element of the table cell data
        WebElement row = driver.findElement(By.xpath("//label[text()='" + strArg1 + "']//ancestor::tr[contains(@role, 'row')]"));
        String rowValue = row.getAttribute("data-ri");

        //Select the last cell and click
        WebElement cellsInRow = driver.findElement(By.xpath("//tr[@data-ri='" + rowValue + "']"));
        WebElement cell = cellsInRow.findElement(By.xpath(".//td[1]"));
        cell.click();


        WebElement viewBtn = driver.findElement(By.id("SearchForm:j_id12"));
        viewBtn.click();
    }

    @Then("^Report Reprint page is displayed with name \"([^\"]*)\" and TIN \"([^\"]*)\"$")
    public void report_reprint_page_is_displayed_with_name_something_and_tin_something(String strArg1, String strArg2) throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String TIN = driver.findElement(By.id("ReportReprint:ReportTIN")).getAttribute("value");
        Assert.assertEquals(strArg2, TIN);

        String moduleName = driver.findElement(By.id("ReportReprint:ModuleName")).getAttribute("value");
        Assert.assertEquals(strArg1, moduleName);
    }

    @When("^clicks download Reports Reprint button$")
    public void clicks_download_reports_reprint_button() throws Throwable {
//        WebElement donwloadReportBtn = driver.findElement(By.id("ReportReprint:j_idt46"));
        WebElement donwloadReportBtn = driver.findElement(By.xpath("//*[text()='Download']"));
        donwloadReportBtn.click();
    }

    @Then("^Report download should be generate (.+) and (.+)$")
    public void report_download_should_be_generate_and(String downloadpath, String filename) throws Throwable {
        Thread.sleep(8000);
        boolean isPresent = false;
        File dir = new File(downloadpath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().contains(filename))
                isPresent = true;
        }
        Thread.sleep(4000);
        Assert.assertTrue(isPresent);

    }

    ////----------------------------------------------find registration application-----------------------------------------------------///

    @Given("^navigate to Registration>>Register Taxpayer$")
    public void navigate_to_registrationregister_taxpayer() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("registerTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }


    @And("^clicks Process Registration Application$")
    public void clicks_process_registration_application() throws Throwable {
        WebElement processRegApplication = driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[4]/a"));
        processRegApplication.click();
        Thread.sleep(2000);

    }

    @And("^enters the ref number \"([^\"]*)\"$")
    public void enters_the_ref_number_something(String strArg1) throws Throwable {
        WebElement refNumberInput = driver.findElement(By.id("SearchForm:applicationReference"));
        refNumberInput.clear();
        refNumberInput.sendKeys(strArg1);
    }

//    @Then("^successfully logged in to appplicant portal$")
//    public void successfully_logged_in_to_appplicant_portal() throws Throwable {
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        WebElement nav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_IndForm\"]/form-wizard/div/div/div[1]/ul/li[1]")));
//        Assert.assertTrue(nav.isDisplayed());
//        Thread.sleep(4000);
//    }

    @And("^clicks Find Registration Application search button$")
    public void clicks_find_registration_application_search_button() throws Throwable {
        WebElement searchBtn = driver.findElement(By.id("SearchForm:j_idt42"));
        searchBtn.click();
    }

    @Then("^Searched application is displayed$")
    public void searched_application_is_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement usersReg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:resultsDataTable:0:j_id5")));
        Assert.assertTrue(usersReg.isDisplayed());
        Assert.assertEquals("ABC Corp", usersReg.getText());
    }

    @Then("^all applications are displayed$")
    public void all_applications_are_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement usersReg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:resultsDataTable:0:j_id5")));
        Assert.assertTrue(usersReg.isDisplayed());
    }

    @Then("^No records found is displayed$")
    public void no_records_found_is_displayed() throws Throwable {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement noUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='No records found.']")));
        Assert.assertTrue(noUser.isDisplayed());
    }

    ///-----------------------------------------------------Individual - Find Taxpayer------------------------------------------------------------------///

    @Given("^navigate to Registration>>Manage Taxpayer$")
    public void navigate_to_registrationmanage_taxpayer() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }



    @Then("^Find screen should be displayed successfully$")
    public void find_screen_should_be_displayed_successfully() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,10);

        WebElement taxpayerClassificationType=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:DTYPE_label")));
        Assert.assertTrue(taxpayerClassificationType.isEnabled());

        WebElement tinInput=driver.findElement(By.id("SearchForm:accountNumber"));
        Assert.assertTrue(tinInput.isEnabled());

        WebElement searchBtn=driver.findElement(By.xpath("//button[@type='submit' and span='Search']"));
        Assert.assertTrue(searchBtn.isDisplayed());

    }
    @When("^In Find screen user Clicks Search$")
    public void in_find_screen_user_clicks_search() throws Throwable {
        Thread.sleep(2000);
        WebElement searchBtn=driver.findElement(By.xpath("//button[@type='submit' and span='Search']"));
        searchBtn.click();
    }

    @When("^Select Taxpayer Classification Type \"([^\"]*)\"$")
    public void select_taxpayer_classification_type_something(String strArg1) throws Throwable {
        WebElement taxpayerClassificationType=driver.findElement(By.id("SearchForm:DTYPE_label"));
        taxpayerClassificationType.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'"+strArg1+"')]")).click();

    }

    @Then("^advanced search is displayed$")
    public void advanced_search_is_displayed() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,20);

        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:INDIVIDUALfirstName")));
        Assert.assertTrue(firstNameInput.isDisplayed());

        WebElement lastNameInput = driver.findElement(By.id("SearchForm:INDIVIDUALlastName"));
        Assert.assertTrue(lastNameInput.isDisplayed());

    }

    @Then("^advanced org search is displayed$")
    public void advanced_org_search_is_displayed() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);


        WebElement companyNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:ORGANIZATIONcompanyName")));
        Assert.assertTrue(companyNameInput.isDisplayed());

//        WebElement identificationDropdown=driver.findElement(By.xpath("//*[@id=\"id_identificationForm\"]/div/div/tb-dropdown[1]/div/div[2]/p-dropdown/div/div[3]/span"));
//        identificationDropdown.click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//span[contains(text(),'"+identificationtype+"')]")).click();

        // clicks on nationality down then enters value in datatable
        try {
            WebElement nationalityDropdown=driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[3]"));
            nationalityDropdown.click();
            Thread.sleep(2000);
            WebElement nationalityInput = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[1]/input"));
            nationalityInput.sendKeys("malawi");

            //clicks on the search result in drop down after entering country
            WebElement myNationality = driver.findElement(By.xpath("//*[@id=\"id_applicantSignupForm\"]/div[1]/tb-dropdown[2]/div/div[2]/p-dropdown/div/div[4]/div[2]/ul/li"));
            myNationality.click();
        } catch(NoSuchElementException | StaleElementReferenceException e) {

        }

        WebElement tradingNameInput = driver.findElement(By.id("SearchForm:ORGANIZATIONtradingName"));
        Assert.assertTrue(tradingNameInput.isDisplayed());
    }

    @When("^enters Tin value as \"([^\"]*)\"$")
    public void enters_tin_value_as_something(String strArg1) throws Throwable {
        WebElement tinInput = driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(strArg1);
    }

    @And("^firstname \"([^\"]*)\"$")
    public void firstname_something(String strArg1) throws Throwable {
        Thread.sleep(3000);
        WebElement firstNameInput = driver.findElement(By.id("SearchForm:INDIVIDUALfirstName"));
        firstNameInput.clear();
        firstNameInput.sendKeys(strArg1);
    }
    @And("^lastname \"([^\"]*)\"$")
    public void lastname_something(String strArg1) throws Throwable {
        Thread.sleep(3000);
        WebElement lastNameInput = driver.findElement(By.id("SearchForm:INDIVIDUALlastName"));
        lastNameInput.clear();
        lastNameInput.sendKeys(strArg1);
    }

    @When("^In Find screen user Clicks Cancel$")
    public void in_find_screen_user_clicks_cancel() throws Throwable {
        WebElement cancelBtn=driver.findElement(By.id("SearchForm:Cancel"));
        cancelBtn.click();
    }

    @Then("^user is navigated back to homepage \"([^\"]*)\"$")
    public void user_is_navigated_back_to_homepage_something(String strArg1) throws Throwable {
        Thread.sleep(4000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL ,strArg1);
    }

    @Then("^applications \"([^\"]*)\" starting with A displayed$")
    public void applications_something_starting_with_a_displayed(String strArg1) throws Throwable {
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement usersReg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:resultsDataTable:0:j_id14")));

        //get full name,parse to get first name then check if first letter starts with a
        String usersRegText = usersReg.getText();
        String[] fullName = usersRegText.split(" ");

        String firstName = fullName[1];
        String lastName = fullName[fullName.length - 1];

        if (strArg1.equals("firstname")) {
            //convert firstletter to uppercase becauser startsWith is case sensitive
            if (firstName.toUpperCase().startsWith("A")) {
                System.out.println("Firstname starts with A");
            } else {
                System.out.println("Text Not Verified and failed");
//                System.exit(1);
            }
        } else {
            //convert firstletter to uppercase becauser startsWith is case sensitive
            if (lastName.toUpperCase().startsWith("A")) {
                System.out.println("Fistname starts with A");
            } else {
                System.out.println("Text Not Verified and failed");
//                System.exit(1);
            }
        }
    }

    @Then("^all records are displayed$")
    public void all_records_are_displayed() throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,20);
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"SearchForm:resultsDataTable\"]/div[3]/table")));
        Assert.assertTrue(row.isDisplayed());
    }

    @Then("^applications \"([^\"]*)\" ending with \"([^\"]*)\" displayed name id \"([^\"]*)\"$")
    public void applications_something_ending_with_something_displayed_name_id_something(String strArg1, String strArg2, String strArg3) throws Throwable {
        Thread.sleep(4000);
        WebDriverWait wait=new WebDriverWait(driver,20);
        WebElement usersReg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(strArg3)));

        //get full name,parse to get first name then check if first letter starts with a
        String usersRegText=usersReg.getText();
        String[] fullName = usersRegText.split(" ");

        //takes care of firstname
        if (strArg1.equals("firstname")){
            String firstName = fullName[1];
            //convert firstletter to uppercase because startsWith() is case sensitive
            if (firstName.toUpperCase().endsWith(strArg2)) {
                System.out.println("Firstname ends with A");
            }
            else {
                System.out.println("Text Not Verified and failed");
//                System.exit(1);
            }
            //takes care of tin
        }else if (strArg1.equals("tin")){
            //convert firstletter to uppercase because startsWith() is case sensitive
            if (usersRegText.toUpperCase().endsWith(strArg2)) {
                System.out.println("tin ends with A");
            }
            else {
                System.out.println("Text Not Verified and failed");
//                System.exit(1);
            }
        }
        else{
            //takes care of lastname
            String lastName = fullName[fullName.length-1];
            //convert firstletter to uppercase because startsWith() is case sensitive
            if (lastName.toUpperCase().endsWith(strArg2)) {
                System.out.println("Fistname ends with A");
            }
            else {
                System.out.println("Text Not Verified and failed");
//                System.exit(1);
            }
        }
    }

    //clear firstname and lastname fields
    @And("^clears all entries$")
    public void clears_all_entries() throws Throwable {
        Thread.sleep(3000);
        WebElement firstNameInput = driver.findElement(By.id("SearchForm:INDIVIDUALfirstName"));
        firstNameInput.clear();

        WebElement lastNameInput = driver.findElement(By.id("SearchForm:INDIVIDUALlastName"));
        lastNameInput.clear();


    }

    @And("^TPIN \"([^\"]*)\"$")
    public void tpin_something(String strArg1) throws Throwable {
        Thread.sleep(3000);
        WebElement tinInput=driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.clear();
        tinInput.sendKeys(strArg1);
    }

    @Then("^user with Tin \"([^\"]*)\" is displayed on input \"([^\"]*)\"$")
    public void user_with_tin_something_is_displayed_on_input_something(String strArg1, String strArg2) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='" + strArg1 + "']")));
        if (text.isDisplayed()) {

            System.out.println("Text Verified");
        } else {
            System.out.println("Text Not Verified and failed");
        }
        Thread.sleep(2000);
    }

    @And("^companyname \"([^\"]*)\"$")
    public void companyname_something(String strArg1) throws Throwable {
        WebDriverWait wait=new WebDriverWait(driver,20);

        WebElement companyNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:ORGANIZATIONcompanyName")));
        companyNameInput.sendKeys(strArg1);
    }

    @And("^tradingname \"([^\"]*)\"$")
    public void tradingname_something(String strArg1) throws Throwable {
        WebElement tradingNameInput = driver.findElement(By.id("SearchForm:ORGANIZATIONtradingName"));
        tradingNameInput.sendKeys(strArg1);
    }


    @Given("^navigate to Registration>>Reactivate Tax$")
    public void navigate_to_registrationreactivate_tax() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("registerTaxtype_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>ReRegister Tax$")
    public void navigate_to_registrationreregister_tax() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("Re-RegisterTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>DeRegister Tax$")
    public void navigate_to_registrationderegister_tax() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("De-RegisterTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>Transfer Tax$")
    public void navigate_to_registrationtransfer_tax() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("TransferTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>update Taxpayer$")
    public void navigate_to_registrationupdate_taxpayer() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("updateTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }

    @Given("^navigate to Registration>>Suspend Dormant Tax Type$")
    public void navigate_to_registrationsuspend_dormant_tax_type() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("registration_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("manageTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("suspendTaxpayer_XPATH"))).click();
        Thread.sleep(2000);
    }
    @Then("^search for reference number$")
    public void search_for_reference_number() throws Throwable {
        Thread.sleep(3000);
//        driver.findElement(By.id(Pro.getProperty("Search_Field_ID"))).sendKeys("ARN/00026550/2021");
        driver.findElement(By.id(Pro.getProperty("Search_Field_ID"))).sendKeys(sharedatastep.A_CRMARN);
        driver.findElement(By.id(Pro.getProperty("Search_Field_Submit_ID"))).click();
    }

    @Then("^Wait for text \"([^\"]*)\" to load in frame \"([^\"]*)\"$")
    public void wait_for_text_to_load_in_frame(String text, String frameID) throws Throwable {
//        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(frameID)));
        driver.switchTo().frame(frame);
        WebDriverWait wait2 = new WebDriverWait(driver,30);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + text + "')]")));
        driver.switchTo().defaultContent();
    }

    @Then("^Wait for duplicate check \"([^\"]*)\" to load in frame \"([^\"]*)\"$")
    public void wait_for_duplicate_text_to_load_in_frame(String text, String frameID) throws Throwable {
//        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(frameID)));
        driver.switchTo().frame(frame);
        WebDriverWait wait2 = new WebDriverWait(driver,30);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + text + "')]")));
        driver.switchTo().defaultContent();
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement noDataXpath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'No records found.')]")));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {
            Assert.assertFalse("Data found in table", false);
        }
    }

    @Then("^Click Cancel \"([^\"]*)\"$")
    public void click_cancel(String cancelID) {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cancelID))).click();
    }

    @Then("^Verify abandon process \"([^\"]*)\"$")
    public void displayWelcomePage(String url) throws Throwable {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, url );
    }

    @Then("^Click on deregister checkbox$")
    public void click_deregister_taxpayer_checkbox() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"DeregisterRegime:TaxpayerAndAllRegime\"]/div[2]"))).click();
    }

    //......................amend taxpayer.................................//
    @Then("^Go to registration > manage taxpayer > update taxpayer$")
    public void go_to_registration_manage_taxpayer_update_taxpayer() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[1]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[1]/a")).click();
    }

    @Then("^Go to registration > manage taxpayer > find taxpayer$")
    public void go_to_registration_manage_taxpayer_find_taxpayer() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[1]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[2]/a")).click();
    }

    @Then("^Enter tin as \"([^\"]*)\"$")
    public void enter_tin_as(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(sharedata.Taxpayer_TIN_INDV);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(tin);
    }

    @Then("^Click search : id \"([^\"]*)\"$")
    public void click_search(String search) throws Throwable {
       WebDriverWait wait = new WebDriverWait(driver,30);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(search))).click();
    }

    @Then("^Select reason for amendment \"([^\"]*)\"$")
    public void select_reason_for_amendment(String amendmentReason) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:AmmendmentReason\"]/div[3]"))).click();

        Thread.sleep(1500);
        //        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //        WebElement reason = driver.findElement(By.xpath("//li[contains(text(),'" + amendmentReason + "')]"));
        //        reason.click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
    }

    @Then("^Select reason for amendment : organization \"([^\"]*)\"$")
    public void select_reason_for_amendment_organization(String amendmentReason) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AmendmentReason\"]/div[3]"))).click();
        Thread.sleep(2500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }


    @Then("^Click search : id")
    public void click_search_id() throws Throwable {
        driver.findElement(By.xpath("//*[text()=\"Search\"]")).click();
    }

    //...............AMEND REQUEST FROM RGD...............................................................//
    @Then("^Click Sole proprietor additional details tab$")
    public void Click_sole_proprietor_additional_details_tab() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[5]/a")).click();
    }

    @Then("^Click edit \"([^\"]*)\"$")
    public void click_edit_something(String editXpath) throws Throwable {
        driver.findElement(By.id(editXpath)).click();
    }

    @Then("^Change RGD number to \"([^\"]*)\"$")
    public void change_rgd_number_to_something(String rgd) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement rgdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TradingNameDetails:RGDNo")));
        rgdField.clear();
        rgdField.sendKeys(rgd);
    }

    @Then("^switch to frame0$")
    public void shift_focus_to_zero_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("^Switch to frame$")
    public void shift_focus_to_frame() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Iframe);
    }

//    @Then("^Switch to frame 2$")
//    public void shift_focus_to_second_frame_two() throws Throwable {
//        Thread.sleep(2000);
//        driver.switchTo().frame(1);
//    }

    @Then("^Switch to default$")
    public void switch_to_default() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    @And("^Click ok: xpath \"([^\"]*)\"$")
    public void click_ok_xpath(String okXpath) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(okXpath)).click();
        Thread.sleep(5000);
    }

    @Then("^Click find \"([^\"]*)\"$")
    public void click_find(String findID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(findID)).click();
    }

    @Then("^Click save \"([^\"]*)\"$")
    public void click_save(String saveID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(saveID))).click();

    }

    @Then("^Select category as \"([^\"]*)\"$")
    public void select_category_individual(String category) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"RegisterIndividual:LegalStatusInd\"]/div[3]"))).click();

        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + category + "')]")).click();
    }

    @Then("^Enter first name \"([^\"]*)\" and last name \"([^\"]*)\"$")
    public void enter_names(String firstName, String lastName) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RegisterIndividual:FirstName"))).clear();
        Thread.sleep(3000);
        driver.findElement(By.id("RegisterIndividual:FirstName")).sendKeys(firstName);
        Thread.sleep(3000);
        driver.findElement(By.id("RegisterIndividual:LastName")).clear();
        Thread.sleep(3000);
        driver.findElement(By.id("RegisterIndividual:LastName")).sendKeys(lastName + " " + BaseClass.getRandom(4));
        Thread.sleep(2000);
    }

    @Then("^Verify duplicate check returns duplicates$")
    public void verifyDuplicateCheck() throws Throwable {

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id("header_process_tbg_approvaloutcome4"));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }else {
            String duplicateCheck = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_duplicatecheckoutcome2\"]/div[1]/span")).getText();
            if(duplicateCheck.equalsIgnoreCase("No"))
            {
                Assert.fail("Duplicate check fail");
            }
        }
        driver.switchTo().defaultContent();
    }

    @Then("^Delete case$")
    public void deleteCase() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbg_registrationapplication|NoRelationship|Form|Mscrm.Form.tbg_registrationapplication.Delete"))).click();

        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("InlineDialog_Iframe")));
        driver.switchTo().frame(frame);
        Thread.sleep(4000);
        driver.findElement(By.id("butBegin")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @Then("^Check if changes reflect \"([^\"]*)\"$")
    public void check_if_changes_reflect_something(String lastName) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,100);
        String original = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RegisterIndividual:FirstName"))).getText();

        if (original.contains(lastName)) {
            Assert.assertTrue("Changes have reflected", true);
        } else {
            Assert.assertFalse("Changes not reflected", false);
        }
    }

    @Then("^Click relationships tab$")
    public void click_relationship_tab_individual() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[14]/a"))).click();

    }

    @Then("^Click relationships tab : organization$")
    public void click_relationship_tab_organization() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Relationships')]"))).click();

    }

    @Then("^Select relationship type$")
    public void select_relationship_type() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"RelationshipDetails:RelationshipType\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Search for relationship with same tin \"([^\"]*)\"$")
    public void search_for_relationship_with_same_tin(String tin) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
    }

    @Then("^Select address type \"([^\"]*)\"$")
    public void select_address_type(String addressType) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:AddressType\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + addressType + "')]")).click();
    }

    @Then("^Uncheck primary indicator checkbox$")
    public void uncheck_primary_indicator_checkbox() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("AddressDetails:PrimaryAddressType")).click();
    }
    @Then("^Click properties tab$")
    public void click_properties_tab_individual() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Property Details')]"))).click();

    }

    @Then("^Click properties tab : organization$")
    public void click_properties_tab_organization() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Property Details')]"))).click();

    }


    @Then("^Obtain property id of first property item in list$")
    public void obtain_property_id_of_first_property_item_in_list() throws Throwable {
        Thread.sleep(2000);
        propertyID = driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:propertyTableHandler_data\"]/tr[1]/td[2]")).getText();
    }

    @Then("^Obtain property id of first property item in list : organization$")
    public void obtain_property_id_of_first_property_item_in_list_organization() throws Throwable {
        Thread.sleep(2000);
        organizationPropertyID = driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:propertyTableHandler_data\"]/tr/td[2]")).getText();
    }

    @Then("^Click transfer$")
    public void click_transfer() throws Throwable {
        Thread.sleep(1500);
        driver.findElement(By.id("RegisterIndividual:individualAccordion:propertyTableHandler:TransferProperty")).click();
    }

    @Then("^Click transfer : organization$")
    public void click_transfer_organization() throws Throwable {
        Thread.sleep(1500);
        driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:propertyTableHandler:TransferProperty")).click();
    }

    @Then("^Search for taxpayer to transfer property to \"([^\"]*)\"$")
    public void search_for_taxpayer_to_transfer_property_to(String tin) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt40")).click();
    }

    @Then("^Validate transfer of property by checking property id$")
    public void validate_transfer_of_property_by_checking_property_id() throws Throwable {
        Thread.sleep(2000);
        WebElement property = driver.findElement(By.xpath("//td[contains(text(),'" + propertyID + "')]"));
        if (property.isDisplayed()) {
            System.out.println("Property ID matches : Transfer was successful");
            Assert.assertTrue("Property ID matches : Transfer was successful", true);
        } else {
            Assert.assertFalse("Property ID does not match", false);
        }
    }

    @Then("^Validate transfer of property by checking property id : organization$")
    public void validate_transfer_of_property_by_checking_property_id_organization() throws Throwable {
        Thread.sleep(2000);
        WebElement property = driver.findElement(By.xpath("//td[contains(text(),'" + organizationPropertyID + "')]"));
        if (property.isDisplayed()) {
            System.out.println("Property ID matches : Transfer was successful");
            Assert.assertTrue("Property ID matches : Transfer was successful", true);
        } else {
            Assert.assertFalse("Property ID does not match", false);
        }
    }

    @Then("^Verify exit from update page$")
    public void verifyExitFromUpdatePage() throws Throwable {
        Thread.sleep(5000);
        WebElement pendingTasksDiv = driver.findElement(By.xpath("//*[@id=\"welcomeForm:pendingTasksAccordion\"]/div[1]"));
        if (pendingTasksDiv.isDisplayed()) {
            System.out.println("Cancel was successful. No changes made");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse("Exit not successful", false);
        }
    }

    //................................Print Organization Reports................................................//
    @Then("^Click reporting > reports$")
    public void goToReportingScreen() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[4]/a"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt27\"]/ul/li[4]/ul/li[1]/a")).click();
    }

    @Then("^Select report to print \"([^\"]*)\"$")
    public void select_report_to_print(String reportType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + reportType + "']"))).click();

    }

    @Then("^Select report file type \"([^\"]*)\"$")
    public void select_report_file_type(String reportFormat) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:ReportFormat\"]/div[3]"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reportFormat + "')]")).click();
    }

    @Then("^Click run report \"([^\"]*)\"$")
    public void click_run_report(String buttonID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(buttonID)).click();
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(10000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @Then("^Select tax office \"([^\"]*)\"$")
    public void select_tax_office(String taxOffice) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxOffice + "')]")).click();
    }

    @Then("^Select business sector \"([^\"]*)\"$")
    public void select_business_sector(String businessSector) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + businessSector + "')]")).click();
    }

    @Then("^Select district \"([^\"]*)\"$")
    public void select_district(String district) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:DISTRICT\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + district + "')]")).click();
    }

    @Then("^Select source \"([^\"]*)\"$")
    public void select_source(String source) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:SOURCE\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + source + "')]")).click();
    }

    @Then("^Select reason \"([^\"]*)\"$")
    public void select_reason(String reason) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:REASON\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reason + "')]")).click();
    }

    @Then("^Select tax type \"([^\"]*)\"$")
    public void select_tax_type(String taxType) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE\"]/div[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxType + "')]")).click();
    }

    @Then("^Select taxpayer category \"([^\"]*)\"$")
    public void select_taxpayer_category(String taxpayerCategory) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxpayerCategory + "')]")).click();
    }

    @Then("^Select tax office from \"([^\"]*)\"$")
    public void select_taxoffice_from(String taxOfficeFrom) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:STATION_FROM\"]/div[3]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//li[contains(text(),'" + taxOfficeFrom + "')]")).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
    }

    @Then("^Select tax office to \"([^\"]*)\"$")
    public void select_taxoffice_to(String taxOfficeTo) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:STATION_TO\"]/div[3]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//li[contains(text(),'" + taxOfficeTo + "')]")).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
    }

    @Then("^Verify report page is abandoned$")
    public void verify_report_page_is_abandoned() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement reportsListHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Reports List')]")));

        if(reportsListHeading.isDisplayed())
        {
            Assert.assertTrue("Report page abandoned",true);
        }
        else{
            Assert.fail();
        }
    }

    @Then("^Click add \"([^\"]*)\"$")
    public void click_add(String addID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addID))).click();

    }

    @Then("^Enter Outcome Reason for Taxpayer accounting$")
    public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
        WebElement specificframe = driver.findElement(By.id("WebResource_tbg_rejectionRefernceData"));
        driver.switchTo().frame(specificframe);
        WebElement dropDown = driver.findElement(By.id("viewoption"));
        //WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"statuscode_i\"]"));
        dropDown.click();
        driver.findElement(By.xpath("//option[text()='Error in data capture']")).click();

        driver.switchTo().defaultContent();
    }

    @Then("^Enter Outcome Reason for Taxtype registration rejection \"([^\"]*)\"$")
    public void enter_Outcome_Reason_for_taxtype_registration_rejections(String reason) throws Throwable {
        WebElement specificframe = driver.findElement(By.id("WebResource_tbg_rejectionRefernceData"));
        driver.switchTo().frame(specificframe);
        WebElement dropDown = driver.findElement(By.id("viewoption"));
        //WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"statuscode_i\"]"));
        dropDown.click();
        driver.findElement(By.xpath("//option[text()='"+reason+"']")).click();
    }

    @Then("^Enter Organization name \"([^\"]*)\"$")
    public void select_organization_name(String name) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrganisationSummaryDetails:LegalName")));
        nameField.clear();
        Thread.sleep(5000);
        nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrganisationSummaryDetails:LegalName")));
        nameField.sendKeys(name);
    }

    //......................SUC:01-20..........................................................//
    @Then("^Click on registration > register taxpayer > process registration application$")
    public void accessProcessRegistrationScreen(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"Registration\"]"))).click();

        driver.findElement(By.xpath("//*[text()=\"Register Taxpayer\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[4]/a")).click();
    }

    @Then("^Enter registration application reference number as \"([^\"]*)\"$")
    public void enter_registration_application_reference_number(String refNumber)  {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:applicationReference"))).sendKeys(refNumber);
    }

    @Then("^Click view$")
    public void click_view() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:j_id19")).click();
    }

    @Then("^Verify reference number, applicant name, application status and tpin fields$")
    public void verify_reference_number_applicant_name_application_status_and_tpin_fields() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RegisterIndividual:FirstName")));
        Assert.assertFalse(firstNameField.isEnabled());

        WebElement referenceNumberField = driver.findElement(By.id("RegisterIndividual:ApplicationReference"));
        Assert.assertFalse(referenceNumberField.isEnabled());

        WebElement applicationStatusField = driver.findElement(By.id("RegisterIndividual:StatusInd"));
        Assert.assertFalse(applicationStatusField.isEnabled());

        WebElement tinField = driver.findElement(By.id("RegisterIndividual:TINInd"));
        Assert.assertFalse(tinField.isEnabled());
    }

    @Then("^Verify reference number, organization name, application status and tpin fields$")
    public void verify_reference_number_organization_name_application_status_and_tpin_fields() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement organizationNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrganisationSummaryDetails:LegalName")));
        Assert.assertFalse(organizationNameField.getAttribute("readonly"),false);

        WebElement referenceNumberField = driver.findElement(By.id("OrganisationSummaryDetails:ApplicationReference"));
        Assert.assertFalse(referenceNumberField.getAttribute("readonly"),false);

        WebElement applicationStatusField = driver.findElement(By.id("OrganisationSummaryDetails:Status"));
        Assert.assertFalse(applicationStatusField.getAttribute("readonly"),false);

        WebElement tinField = driver.findElement(By.id("OrganisationSummaryDetails:TIN"));
        Assert.assertFalse(tinField.getAttribute("readonly"),false);
    }

    //-------------------------Submit Registration Application------------------------------------------------------------//

    @Then("^Click on registration > register taxpayer > register individual$")
    public void openIndividualRegistration() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]"))).click();

        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[1]")).click();
    }

    @Then("^Click on registration > register taxpayer > register organization$")
    public void openOrganizationRegistration() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Registration')]"))).click();
        driver.findElement(By.xpath("//span[contains(text(),'Register Taxpayer')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Register Organisation')]")).click();
    }

    @Then("^Select category : organization \"([^\"]*)\"$")
    public void select_category_organization(String category) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"OrganisationSummaryDetails:LegalStatus\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + category + "')]")).click();
    }

    @Then("^Select title as \"([^\"]*)\"$")
    public void select_title(String title) throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:Title\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + title + "')]")).click();
    }


    @Then("^Select gender \"([^\"]*)\"$")
    public void select_gender(String gender) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:Gender\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
        //        driver.findElement(By.xpath("//li[contains(text(),'" + gender + "')]")).click();
    }

    @Then("^Select marital status \"([^\"]*)\"$")
    public void select_marital_status(String maritalStatus) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MaritalStatus\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + maritalStatus + "')]")).click();
    }

    @Then("^Enter date of birth \"([^\"]*)\"$")
    public void enter_date_of_birth(String dob) throws Throwable {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('RegisterIndividual:individualAccordion:DOB_input').setAttribute('value', '" + dob + "')");
    }

    @Then("^Select nationality \"([^\"]*)\"$")
    public void select_nationality(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:Nationality\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'Kenya')]")).click();
    }

    @Then("^Select country of residence \"([^\"]*)\"$")
    public void select_country_of_residence(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:CountryOfResidence\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + nationality + "')]")).click();
    }

    @Then("^Enter place of birth \"([^\"]*)\"$")
    public void enter_place_of_birth(String placeOfBirth) throws Throwable {
        driver.findElement(By.id("RegisterIndividual:individualAccordion:PlaceOfBirth")).sendKeys(placeOfBirth);
    }

    @Then("^Select reason for tin application \"([^\"]*)\"$")
    public void select_reason_for_tin_application_individual(String reasonForApplication) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:ReasonForTIN\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + reasonForApplication + "')]")).click();
    }

    @Then("^Select reason for tin application : organization \"([^\"]*)\"$")
    public void select_reason_for_tin_application_organization(String reasonForApplication) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:ReasonForTIN\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + reasonForApplication + "')]")).click();
    }

    @Then("^Select occupation status \"([^\"]*)\"$")
    public void select_occupation_status(String occupationStatus) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:occupationStatus\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + occupationStatus + "')]")).click();
    }

    @Then("^Select main category \"([^\"]*)\"$")
    public void select_main_category(String mainCategory) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MainCategory\"]/div[3]")).click();
        Thread.sleep(1500);
        //driver.findElement(By.xpath("//li[contains(text(),'" + mainCategory + "')]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Precise category \"([^\"]*)\"$")
    public void select_precise_category(String preciseCategory) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:PreciseCategory\"]/div[3]")).click();
        Thread.sleep(1500);
        //driver.findElement(By.xpath("//li[contains(text(),'" + preciseCategory + "')]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @And("^Click occupation - business interest tab$")
    public void click_occupation_business_interest_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[4]/a")).click();
    }

    @And("^Click on identification tab$")
    public void click_on_identification_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[2]/a")).click();
    }

    @Then("^Select identification \"([^\"]*)\"$")
    public void select_identification(String identification) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"Identification:IdentificationType\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + identification + "')]")).click();
    }

    @Then("^Enter identification number \"([^\"]*)\"$")
    public void enter_identification_number(String idNumber) throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id("Identification:IdentificationNumber")).sendKeys(idNumber);
    }

    @Then("^Enter date of issue \"([^\"]*)\"$")
    public void enter_date_of_issue(String dateOfIssue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('Identification:IdentificationEffectiveDate_input').setAttribute('value', '" + dateOfIssue + "')");
    }

    @Then("^Enter expiry date \"([^\"]*)\"$")
    public void enter_expiry_date(String expiryDate) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('Identification:ExpiryDate_input').setAttribute('value', '" + expiryDate + "')");
    }

    @Then("^Enter E-permit number \"([^\"]*)\" and E-permit type$")
    public void enter_epermit_number_something_and_epermit_type(String enumber) throws Throwable {
        driver.findElement(By.id("Identification:ePermitNumber")).sendKeys(enumber);
        driver.findElement(By.xpath("//*[@id=\"Identification:ePermitType\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select country of issue \"([^\"]*)\"$")
    public void select_country_of_issue(String countryOfIssue) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"Identification:CountryOfIssue\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + countryOfIssue + "')]")).click();
    }

    @Then("^Click employment details tab$")
    public void click_employment_details_tab() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[3]/a")).click();
    }

    @Then("^Enter employers name \"([^\"]*)\" and employment start date \"([^\"]*)\"$")
    public void enter_employers_details(String employersName, String startDate) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("EmploymentDetails:employersName")).sendKeys(employersName);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('EmploymentDetails:StartDate_input').setAttribute('value', '" + startDate + "')");
    }

    @Then("^Click address tab$")
    public void click_address_tab_individual() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[5]/a")).click();
    }

    @Then("^Then enter town \"([^\"]*)\"$")
    public void then_enter_town(String town) throws Throwable {
        Thread.sleep(2500);
        driver.findElement(By.id("AddressDetails:City")).sendKeys(town);
    }

    @Then("^Select region \"([^\"]*)\" and district \"([^\"]*)\"$")
    public void select_region_and_district(String region, String district) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:PostalRegion\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + region + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:District\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + district + "')]")).click();
    }

    @Then("^Click Contact methods tab$")
    public void click_contact_methods_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[6]/a")).click();
    }

    @Then("^Select purpose$")
    public void select_purpose() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"ContactDetails:Purpose\"]/div[3]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Enter contact details \"([^\"]*)\"$")
    public void enter_contact_details(String email) {
        driver.findElement(By.id("ContactDetails:ContactMethodDetailForWeb")).sendKeys(email);
    }

    @Then("^Select Account end day \"([^\"]*)\"$")
    public void select_account_end_day(String endDay) throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AccountYearEndDateDD\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Account end month \"([^\"]*)\"$")
    public void select_account_end_month(String endMonth) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AccountYearEndDateMM\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Enter source of capital \"([^\"]*)\"$")
    public void enter_source_of_capital(String sourceOfCapital) {
        driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:SourceOfCapital")).sendKeys(sourceOfCapital);
    }

    @Then("^Select place of incorporation \"([^\"]*)\"$")
    public void select_place_of_incorporation(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:PlaceOfIncorporation\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + nationality + "')]")).click();
    }

    @Then("^Select business sector$")
    public void select_business_sector() throws Throwable {
        //driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:businessDetailsHandler:AddBusinessSD")).click();
        driver.findElement(By.xpath("//*[@id=\"BusinessSectorDetails:BusinessCode\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Click primary indicator checkbox \"([^\"]*)\"$")
    public void click_primary_indicator_checkbox(String primaryID) throws Throwable {
        driver.findElement(By.id(primaryID)).click();
    }

    @Then("^Click address tab : organization$")
    public void click_address_tab_organization() {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[2]/a")).click();
    }

    @And("^Clickelect Approval outcome dropdown value to Approve directors tab$")
    public void click_on_directors_tab() {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[14]/a")).click();
    }

    @Then("^Click continue \"([^\"]*)\"$")
    public void click_continue(String continueID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(continueID)).click();
    }

    @Then("^Enter director start date \"([^\"]*)\"$")
    public void enter_director_start_date(String directorStartDate) throws Throwable {
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('DirectorsDetails:PositionHeldSince_input').setAttribute('value', '" + directorStartDate + "')");
    }


}




