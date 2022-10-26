package Pack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class Day1 {

	WebDriver driver;
	String username = "mngr439198"; //login credentials
	String password = "gagUtej";
	String dashboardtitle = "Guru99 Bank"; //title displayed by webpage

	@Test
	public void verifyLogin() {
		driver.findElement(By.name("uid")).sendKeys(username);//inputting data
		driver.findElement(By.name("password")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		String actualtitle = driver.findElement(By.className("barone")).getText();//getting text of title of dashboard
		System.out.println(actualtitle);
		if (actualtitle.trim().equalsIgnoreCase(dashboardtitle)) {   //comparing both titles
			System.out.println("Login Successful");
		} else {
			System.out.println("Login Failed");
		}

	}

	@BeforeClass
	public void setup() {   
		System.setProperty("webdriver.chrome.driver", "G:\\Software\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.demo.guru99.com/V4/");
	}

	@AfterClass
	public void end() {
		driver.close();
	}

}
