package Pack;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
//made use in day6.java


public class TakeSSonfailure1 {

	WebDriver driver;
	
	public void CaptureSS(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) {
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File source=ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/"+result.getName() + ".png"));
			System.out.println(result.getName()+" method is failed and ss captured");
		}
	}
}
