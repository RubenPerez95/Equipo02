package pruebas;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.uclm.equipo02.Auxiliar.Utilidades;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
public class testModEmpl{
	
	
	
	
	
	
	@Before
	public void precondiciones() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		WebDriver  driver = new ChromeDriver();
		
	    driver.get("https://mantenimientoequipo2.herokuapp.com/");
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("PassRafa88");
		
		driver.findElement(By.name("acceso")).click();
		Thread.sleep(1000);
	}
	
}