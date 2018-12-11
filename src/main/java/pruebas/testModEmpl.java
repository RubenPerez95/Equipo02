package pruebas;


import static org.junit.Assert.assertTrue;

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
	
	
	
	
	WebDriver  driver;
	
	@Before
	public void precondiciones() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		  driver = new ChromeDriver();
		
	    driver.get("https://mantenimientoequipo2.herokuapp.com/");
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("PassRafa88");
		
		driver.findElement(By.name("acceso")).click();
		
		
		Thread.sleep(1000);
	}
	@Test
	public void testModificarUsuarioEnBlanco() {
		

		driver.findElement(By.name("modificarUsuario")).click();
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("listaRoles")).sendKeys("Gestor de incidencias");
		driver.findElement(By.name("Aceptar")).click();
		
		assertTrue(UserExiste());
		
	}
	
	@Test
	public void testModificarUsuarioExistente() {
		UsuarioDaoImplement us =new UsuarioDaoImplement();
		Usuario user =new Usuario();
		user.setDni("12345678T");
		
		driver.findElement(By.name("modificarUsuario")).click();
		driver.findElement(By.name("txtDniBusqueda")).sendKeys("12345678T");
		driver.findElement(By.name("Buscar")).click();
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("listaRoles")).sendKeys("Gestor de incidencias");
		driver.findElement(By.name("Aceptar")).click();
		
		assertTrue(us.devolverMail(user).equals("rafapx2@gmail.com")&&us.devolverRol(user).equals("Gestor de incidencias"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean UserExiste() {
		UsuarioDaoImplement us =new UsuarioDaoImplement();
		boolean estado=true;
		List<Usuario> usuarios=us.selectAll();
		int i=0;
		while(i<usuarios.size()) { 
			if(usuarios.get(i).getNombre().equals(""))estado=false;
		i++;
		}
		return estado;
		
		
	}
	
}