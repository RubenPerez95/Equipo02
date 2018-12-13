package pruebas;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Pattern;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.support.ui.Select;

class LoginSelenium {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseUrl = "https://www.katalon.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	@Test
	public void loginCorrecto() throws Exception {
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
		driver.findElement(By.name("txtUsuarioEmail")).click();
		driver.findElement(By.name("txtUsuarioEmail")).clear();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("intimeequipo02ad@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).clear();
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("admin1234");
		driver.findElement(By.id("formlogin")).submit();

	}

	@Test
	public void passwordIncorrecto() throws Exception {
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
		driver.findElement(By.name("txtUsuarioEmail")).click();
		driver.findElement(By.name("txtUsuarioEmail")).clear();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("intimeequipo02ad@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).clear();
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("adsfadsgfdc");
		driver.findElement(By.id("formlogin")).submit();

	}
	
	@Test
	public void emailIncorrecto() throws Exception {
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
		driver.findElement(By.name("txtUsuarioEmail")).click();
		driver.findElement(By.name("txtUsuarioEmail")).clear();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("intimeequipo02ad@hotmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).clear();
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("admin1234");
		driver.findElement(By.id("formlogin")).submit();

	}
	
	@Test
	public void loginVacio() throws Exception {
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
		driver.findElement(By.name("txtUsuarioEmail")).click();
		driver.findElement(By.name("txtUsuarioEmail")).clear();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("");
		driver.findElement(By.name("txtUsuarioPassword")).clear();
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("");
		driver.findElement(By.id("formlogin")).submit();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String closeAlertAndGetItsText() {
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