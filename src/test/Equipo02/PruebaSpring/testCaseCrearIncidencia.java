package Equipo02.PruebaSpring;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class testCaseCrearIncidencia {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:/Users/Lydia Prado Ibáñez/Desktop/chromedriver_win32/chromedriver.exe"); 
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCrearIncidencia() throws Exception {
    driver.get("https://mantenimientoequipo2.herokuapp.com/login");
    driver.findElement(By.name("crearIncidencia")).click();
    //Funcionamiento normal de crear una incidencia
    driver.findElement(By.name("listaTiposIncidencia")).click();
    new Select(driver.findElement(By.name("listaTiposIncidencia"))).selectByVisibleText("Baja médica");
    driver.findElement(By.name("listaTiposIncidencia")).click();
    driver.findElement(By.name("textoIncidencia")).click();
    driver.findElement(By.name("textoIncidencia")).clear();
    driver.findElement(By.name("textoIncidencia")).sendKeys("Tengo gripe y fiebre");
    driver.findElement(By.name("Aceptar")).click();
    //Funcionamiento cuando intentas añadir una incidencia vacía
    driver.findElement(By.name("crearIncidencia")).click();
    driver.findElement(By.name("Aceptar")).click();
    driver.findElement(By.name("textoIncidencia")).clear();
    //prueba de que se permiten introducir incidencias solo con espacios
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
