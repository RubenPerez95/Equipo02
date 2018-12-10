package pruebas;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
public class testModEmpl{
	WebDriver driver;
	
	@Test
	public void prueba() throws Throwable {
		
	}
	@Before
	public void abre_Chrome_y_se_establece_conexion_con_la_direccion() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
	    driver.get("https://mantenimientoequipo2.herokuapp.com/");
		Thread.sleep(1000);
	}
	
	
	@Test
	public void pulsa_el_boton_para_redirigir() throws Throwable {
		WebElement btnRedirigir;
	}
}