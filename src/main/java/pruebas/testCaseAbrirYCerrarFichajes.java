package pruebas;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testCaseAbrirYCerrarFichajes {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

  @Test
  public void testAbrirYcerrarFichaje() throws Exception {
    driver.get("https://mantenimientoequipo2.herokuapp.com/");
    driver.findElement(By.name("txtUsuarioEmail")).click();
    driver.findElement(By.name("txtUsuarioEmail")).clear();
    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("ruben.perez7@alu.uclm.es");
    driver.findElement(By.name("txtUsuarioPassword")).clear();
    driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Ruben123");
    driver.findElement(By.id("formlogin")).submit();
    driver.findElement(By.id("btnAbrir")).click();
    driver.findElement(By.id("btnCerrar")).click();
  }
  
  @Test
  public void testComprobarFichajeAbierto() throws Exception {
    driver.get("https://mantenimientoequipo2.herokuapp.com/logout?");
    driver.findElement(By.name("txtUsuarioEmail")).click();
    driver.findElement(By.name("txtUsuarioEmail")).clear();
    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("ruben.perez7@alu.uclm.es");
    driver.findElement(By.name("txtUsuarioPassword")).clear();
    driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Ruben123");
    driver.findElement(By.id("formlogin")).submit();
    driver.findElement(By.id("btnAbrir")).click();
    driver.findElement(By.id("btnAbrir")).click();
    driver.findElement(By.id("btnCerrar")).click();
  }
  
  @Test
  public void testComprobarFichajeCerrado() throws Exception {
    driver.get("https://mantenimientoequipo2.herokuapp.com/logout?");
    driver.findElement(By.name("txtUsuarioEmail")).click();
    driver.findElement(By.name("txtUsuarioEmail")).clear();
    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("ruben.perez7@alu.uclm.es");
    driver.findElement(By.name("txtUsuarioPassword")).clear();
    driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Ruben123");
    driver.findElement(By.id("formlogin")).submit();
    driver.findElement(By.id("btnAbrir")).click();
    driver.findElement(By.id("btnCerrar")).click();
    driver.findElement(By.id("btnCerrar")).click();
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
