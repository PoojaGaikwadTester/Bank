package Pack;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Excel {
	
	static WebDriver driver;
	private	static String baseUrl;

	public static void setup() {
		System.setProperty("webdriver.chrome.driver", DayUtil.CHROME_PATH);
		driver = new ChromeDriver();
		baseUrl=DayUtil.BASE_URL;
		driver.manage().timeouts().implicitlyWait(DayUtil.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "V4/");
	}

	
	public static void main(String[] args) throws Exception {
		setup();
		String excelFilePath = ".\\XLData\\Demo.xlsx";
		File src = new File(excelFilePath);
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		//System.out.println(sh.getSheetName());// print name of sheet
		//System.out.println(sh.getRow(0).getCell(0).getStringCellValue());// print value present in
																			// row-cell(column)-cellvalue
		///System.out.println(sh.getPhysicalNumberOfRows() + "=== " + sh.getLastRowNum());// physicalnoofrows prints total
																						// rows which hv data

		//int rows = sh.getLastRowNum() - sh.getFirstRowNum() + 1;
		//int cols = sh.getRow(0).getLastCellNum();

		int rows = sh.getLastRowNum();
		int cols = sh.getRow(0).getLastCellNum();

		
		for (int i = 0; i <= rows; i++) {
			
			XSSFRow celldata=sh.getRow(i);
			
				String user=celldata.getCell(0).getStringCellValue();
				String password=celldata.getCell(1).getStringCellValue();
				driver.findElement(By.name("uid")).clear();
				driver.findElement(By.name("uid")).sendKeys(user);

				driver.findElement(By.name("password")).clear();
				driver.findElement(By.name("password")).sendKeys(password);

				//driver.findElement(By.name("btnLogin")).click();
			System.out.println(i+user+password);

		}
		driver.close();

	}

}
