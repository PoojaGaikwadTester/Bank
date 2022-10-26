package Pack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrintExcelValues {

	static WebDriver driver;
	private	static String baseUrl;

	public static void setup() {
		System.setProperty("webdriver.chrome.driver", DayUtil.CHROME_PATH);
		driver = new ChromeDriver();
		baseUrl=DayUtil.BASE_URL;
		driver.manage().timeouts().implicitlyWait(DayUtil.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "V4/");
	}

	
	public static void main(String[] args) throws IOException {
		String excelFilePath = ".\\XLData\\Demo.xlsx";
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// Using For Loop

		int rows = sheet.getLastRowNum();
//int f1=sheet.getFirstRowNum();
//System.out.println(f1);
		int cols = sheet.getRow(1).getLastCellNum();
//System.out.println(rows);
//System.out.println(cols);

		for (int r = 0; r <= rows; r++) {
			XSSFRow row = sheet.getRow(r);

			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.getCell(c);

				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					//driver.findElement(By.name("uid")).sendKeys(cell.getStringCellValue());//inputting data

					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					//driver.findElement(By.name("uid")).sendKeys(cell.getNumericCellValue());//inputting data

					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					//driver.findElement(By.name("uid")).sendKeys(cell.getBooleanCellValue());//inputting data

					break;
				default:
					break;

				}
				System.out.print(" | ");

			}
			System.out.println();
		}
	}

}
