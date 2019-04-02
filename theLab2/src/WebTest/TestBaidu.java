package WebTest;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.*;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class TestBaidu {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  String driverPath = System.getProperty("user.dir") + "/src/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	  baseUrl = "http://121.193.130.195:8800/login";
	  driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
  }

  @Test
  public void testBaidu() throws Exception {
	  driver.get(baseUrl);
	   
	  InputStream f=new FileInputStream("E:\\java proj\\theLab2\\testlist.xlsx");
	XSSFWorkbook wb = new XSSFWorkbook(f);
    XSSFSheet sheet = wb.getSheetAt(0);
    for(int row=2; row<sheet.getLastRowNum();row++){
    	XSSFRow xssfrow=sheet.getRow(row);
    	XSSFCell cell = xssfrow.getCell(1);
    	cell.setCellType(CellType.STRING);
   	 System.out.println(cell.getStringCellValue());
    	driver.findElement(By.name("id")).clear();
        driver.findElement(By.name("id")).sendKeys(cell.toString());
        driver.findElement(By.name("password")).clear();
       
        driver.findElement(By.name("password")).sendKeys(cell.getStringCellValue().substring(4, 10));
        driver.findElement(By.id("btn_login")).sendKeys(Keys.ENTER);
        assertEquals(xssfrow.getCell(2).toString(), driver.findElement(By.id("student-name")).getText());
        assertEquals(xssfrow.getCell(3).toString(), driver.findElement(By.id("student-git")).getText());
        driver.findElement(By.id("btn_logout")).click();
        driver.findElement(By.id("btn_return")).click();
    }
//    driver.findElement(By.name("id")).clear();
//    driver.findElement(By.name("id")).sendKeys("3016218110");
//    driver.findElement(By.name("password")).clear();
//   
//    driver.findElement(By.name("password")).sendKeys("218110");
//    driver.findElement(By.id("btn_login")).sendKeys(Keys.ENTER);
//    assertEquals("³ÂÉ­ÁÖ", driver.findElement(By.id("student-name")).getText());
//    
   
  wb.close();
  }
  

  @After
  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

