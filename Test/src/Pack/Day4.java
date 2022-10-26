package Pack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day4 {
	
	static WebDriver driver;
	private	static String baseUrl;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", DayUtil.CHROME_PATH);
		driver = new ChromeDriver();
		baseUrl=DayUtil.BASE_URL;
		driver.manage().timeouts().implicitlyWait(DayUtil.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "V4/");
	}

	@Test(dataProvider = "data")
	public void loginTest(String user, String pwd, String exp) {
		
		WebElement txtEmail = driver.findElement(By.xpath("//input[@name='uid']"));
		txtEmail.clear();
		txtEmail.sendKeys(user);

		WebElement txtPassward = driver.findElement(By.xpath("//input[@name='password']"));
		txtPassward.clear();
		txtPassward.sendKeys(pwd);

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		try {
			Alert alert = driver.switchTo().alert();
			String actualBoxTitle = alert.getText();
			String exepectedBoxTitle = "User or Password is not valid";
			if (actualBoxTitle.contains(exepectedBoxTitle)) {
				System.out.print("Username or Password are invalid");
			} else {
				System.out.print("Username or Password are valid");
			}
			alert.accept();

		} catch (NoAlertPresentException Ex) {

		}

		String exp_url = "https://www.demo.guru99.com/V4/manager/Managerhomepage.php";
		String act_url = driver.getCurrentUrl();

		if (exp.equals("Valid")) {
			if (exp_url.equals(act_url)) // Dashboard / nopCommerce administration
			{
				//verify manager id
			String mgrGetid	=driver.findElement(By.xpath("//tr[@class='heading3']")).getText();
			if(mgrGetid.contains(user)) {
					System.out.println("For User : "+ user +"id is : " +mgrGetid);
				}

				
				driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();
				driver.switchTo().alert().accept();

				Assert.assertTrue(true);

			} else {
				System.out.println("This is not valid login");
				Assert.assertTrue(false);
			}
		}

		else if (exp.equals("InValid"))

		{

			if (exp_url.equals(act_url)) {
				driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}

		}
	}

	@DataProvider(name = "data")
	public String[][] getData() throws IOException {
		/*
		 * String loginData[][]= { {"admin@yourstore.com","adm","Invalid"},
		 * {"adm@yourstore.com","admin","Invalid"},
		 * {"adm@yourstore.com","adm","Invalid"},
		 * {"admin@yourstore.com","admin","Valid"} };
		 */
		// get the data from excel
		String path = ".\\XLData\\Demo.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++)

		{
			for (int j = 0; j < totalcols; j++) {
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}

		return loginData;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}

}
