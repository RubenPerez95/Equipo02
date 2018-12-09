package pruebas;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class testCaseCrearIncidencia {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testIncidencias() throws Exception {
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
		driver.findElement(By.name("txtUsuarioEmail")).click();
		driver.findElement(By.name("txtUsuarioEmail")).clear();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("lydia.prado@alu.uclm.es");
		driver.findElement(By.name("txtUsuarioPassword")).click();
		driver.findElement(By.name("txtUsuarioPassword")).clear();
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Lydia1234");
		driver.findElement(By.id("formlogin")).submit();
		//Ejemplo de funcionamiento correcto
		driver.findElement(By.name("crearIncidencia")).click();
		driver.findElement(By.name("listaTiposIncidencia")).click();
		new Select(driver.findElement(By.name("listaTiposIncidencia"))).selectByVisibleText("Vacaciones");
		driver.findElement(By.name("listaTiposIncidencia")).click();
		driver.findElement(By.name("textoIncidencia")).click();
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys("Me marcho de vacaciones las semana que viene");
		driver.findElement(By.name("Aceptar")).click();
		//Ejemplo de intentar meter una incidencia vacía
		driver.findElement(By.name("crearIncidencia")).click();
		driver.findElement(By.name("Aceptar")).click();
		//Ejemplo de intentar meter una incidencia con espacios y que funciona
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys("");
		driver.findElement(By.name("Aceptar")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
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
