package pruebas;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testCaseCambiarContrasena {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void ContrasenaYCambioCorrecto() throws Exception {
		//Iniciar sesion
		driver.get("https://mantenimientoequipo2.herokuapp.com/");
	    driver.findElement(By.name("txtUsuarioEmail")).click();
	    driver.findElement(By.name("txtUsuarioEmail")).clear();
	    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("lydia.prado@alu.uclm.es");
	    driver.findElement(By.name("txtUsuarioPassword")).click();
	    driver.findElement(By.name("txtUsuarioPassword")).clear();
	    driver.findElement(By.name("txtUsuarioPassword")).sendKeys("MiContrasena1");
	    driver.findElement(By.name("acceso")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Salir'])[1]/preceding::strong[1]")).click();
	    //Cambiar contrasena por una con todos los requisitos
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena1");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("MiContrasena2");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por una con un espacio en blanco
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys(" ");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys(" ");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por una con 8 espacios en blanco
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("        ");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("        ");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por 8 mayusculas
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("ASDFGHJK");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("ASDFGHJK");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por 8 minusculas
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("asdfghjk");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("asdfghjk");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por 8 numeros
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("12345678");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("12345678");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por un numero, una minuscula y una mayuscula
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("1aA");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("1aA");
	    driver.findElement(By.name("Aceptar")).click();
	    //Introducir contrasena actual incorrecta y una nueva contrasena valida
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("incorrecta");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("Lydia098");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("Lydia098");
	    driver.findElement(By.name("Aceptar")).click();
	    //Cambiar contrasena por una ya ustilizada anteriormente
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("Lydia1996");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("Lydia1996");
	    driver.findElement(By.name("Aceptar")).click();
	    //Introducir contrasena nueva que no coincide
	    driver.findElement(By.name("contrasenaActual")).click();
	    driver.findElement(By.name("contrasenaActual")).clear();
	    driver.findElement(By.name("contrasenaActual")).sendKeys("MiContrasena2");
	    driver.findElement(By.id("contrasenaNueva")).click();
	    driver.findElement(By.id("contrasenaNueva")).clear();
	    driver.findElement(By.id("contrasenaNueva")).sendKeys("Lydia111");
	    driver.findElement(By.id("contrasenaNueva2")).click();
	    driver.findElement(By.id("contrasenaNueva2")).clear();
	    driver.findElement(By.id("contrasenaNueva2")).sendKeys("Lydia112");
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
