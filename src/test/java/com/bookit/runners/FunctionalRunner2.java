package com.bookit.runners;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.bookit.utils.FileReaderManager;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin={"pretty:target/cucumber-pretty.txt",
				"html:target/html-report",
				"json:target/cucumbe2.json",
				"junit:target/junit/junit-report.xml",
//				"usage:target/cucumber-usage.json",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/Report.html"
			},
		tags = {"~@ignore",},   
        features= {
        		"src/test/resources/features/Schedule.feature"  		
        },
        glue= {"com/bookit/stepdefinitions","com/bookit/suitesetupandteardown"},
//        dryRun=true,
        monochrome = true
		)
public class FunctionalRunner2 {
	
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
	}
}
