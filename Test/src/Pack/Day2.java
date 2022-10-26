package Pack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Day2 {

	static WebDriver driver;
	private	static String baseUrl;

	public static void setup() {
		System.setProperty("webdriver.chrome.driver", DayUtil.CHROME_PATH);
		driver = new ChromeDriver();
		baseUrl=DayUtil.BASE_URL;
		driver.manage().timeouts().implicitlyWait(DayUtil.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "V4/");
	}

	public static void main(String[] args) {
		setup();
		
		driver.findElement(By.name("uid")).sendKeys(DayUtil.username);//inputting data
		driver.findElement(By.name("password")).sendKeys(DayUtil.password);
		
		driver.findElement(By.name("btnLogin")).click();
		String actualtitle = driver.getTitle();//getting text of title of dashboard
		System.out.println("Title of Homepage : " + actualtitle);
		if (actualtitle.trim().contains(DayUtil.dashboardtitle)) {   //comparing both titles
			System.out.println("Verify Login: Login Successful");
		} else {
			System.out.println("Verify Login: Login Failed");
		}
		driver.close();
	}

}
