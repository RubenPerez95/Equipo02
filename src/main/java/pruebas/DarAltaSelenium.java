package pruebas;


import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DarAltaSelenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void AltaUsuarioFormatoErroneo() throws Exception {
    driver.get("https://mantenimientoequipo2.herokuapp.com/interfazCrearUsuario?crearUsuario=Crear+usuario");
    driver.findElement(By.name("txtUsuarioEmail")).click();
    driver.findElement(By.name("txtUsuarioEmail")).clear();
    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("ana");
    driver.findElement(By.name("txtUsuarioNombre")).click();
    driver.findElement(By.name("txtUsuarioNombre")).clear();
    driver.findElement(By.name("txtUsuarioNombre")).sendKeys("Ana Perez Lucas");
    driver.findElement(By.name("txtDni")).click();
    driver.findElement(By.name("txtDni")).clear();
    driver.findElement(By.name("txtDni")).sendKeys("70593055");
    driver.findElement(By.name("txtDni")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Formato del DNI inválido'])[1]/following::div[1]")).click();
    driver.findElement(By.name("Aceptar")).click();
    driver.findElement(By.name("Aceptar")).click();
    
  }
  
  @Test
  public void AltaUsuarioCorrecto() throws Exception {
	driver.get("https://mantenimientoequipo2.herokuapp.com/interfazCrearUsuario?crearUsuario=Crear+usuario");
    driver.findElement(By.name("txtUsuarioEmail")).click();
    driver.findElement(By.name("txtUsuarioEmail")).clear();
    driver.findElement(By.name("txtUsuarioEmail")).sendKeys("anaprezlucas@gmail.com");
    driver.findElement(By.name("txtUsuarioNombre")).click();
    driver.findElement(By.name("Aceptar")).click();
    driver.findElement(By.name("txtDni")).click();
    driver.findElement(By.name("txtDni")).clear();
    driver.findElement(By.name("txtDni")).sendKeys("70593055");
    driver.findElement(By.name("Aceptar")).click();
    driver.findElement(By.name("listaRoles")).click();
    driver.findElement(By.name("Aceptar")).click();
  }

  @AfterClass
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
