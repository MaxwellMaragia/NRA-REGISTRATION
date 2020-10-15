package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import Utils.BaseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks extends BaseClass {

	public Scenario scenario = null;

	@Before()
	public void before(Scenario scenario) throws Throwable {
		this.scenario = scenario;
	}

	@After(order=1)
	public void AfterSelenium()
	{

		//driver.close();
	}

	@After(order=2)
	public void AftersaveScreenshot(Scenario scenario) {

		File destPath;

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss");
		Date curDate = new Date(); String strDate = sdf.format(curDate);
		File screenshot_with_scenario_name = (((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));

		if(scenario.isFailed())
		{
			destPath=new File("./test-output/Screenshots/Failed/" + scenario.getName()+ strDate + ".png");
		}
		else{
			destPath=new File("./test-output/Screenshots/Passed/" + scenario.getName()+ strDate + ".png");
		}

		try {
			FileUtils.copyFile(screenshot_with_scenario_name,destPath);
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
