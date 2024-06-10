package stepsDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class HooksClass {

    @After
    public void after(Scenario scenario){
        if(scenario.isFailed()){
            byte [] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }
        else{
            Driver.quit();
        }
    }
}
