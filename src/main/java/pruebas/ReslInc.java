package pruebas;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReslInc {

	WebDriver  driver;
	
	@Before
	public void precondiciones() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("https://mantenimientoequipo2.herokuapp.com/");
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("PassRafa88");
		
		driver.findElement(By.name("acceso")).click();
		
		
		Thread.sleep(1000);
	}
    @Test
    public void ResolverIncidenciaEnBlanco() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
    	driver.findElement(By.name("Resolver")).click();
    	 
    }
    
    @Test
    public void ResolverIncidenciaEnBlancoMenosElComentario() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
    	driver.findElement(By.name("textoGestor")).sendKeys("1234");
    	
    	driver.findElement(By.name("Resolver")).click();
    	try {
    	driver.findElement(By.name("Resolver"));
    }catch(Exception e){
    	fail("Excepcion al resolver una incidencia en blanco");
    	
    }
    }
    @Test
    public void ResolverIncidenciaEnBlancoYComentarioConEspacios() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
	driver.findElement(By.name("textoGestor")).sendKeys(" ");
    	driver.findElement(By.name("Resolver")).click();
    	try {
        	driver.findElement(By.name("Resolver"));
        }catch(Exception e){
        	fail("Excepcion al resolver una incidencia en blanco");
        	
        }
    	 
    }
    @Test
    public void ResolverIncidenciaRellenaYComentarioConEspacios() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
    	
    	driver.findElement(By.name("seleccionar")).click();
	driver.findElement(By.name("textoGestor")).sendKeys(" ");
    	driver.findElement(By.name("Resolver")).click();
    	
    	 
    }
    
    @Test
    public void ResolverIncidenciaRellenaYComentarioConMensaje() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
    	
    	driver.findElement(By.name("seleccionar")).click();
	driver.findElement(By.name("textoGestor")).sendKeys("12345");
    	driver.findElement(By.name("Resolver")).click();
    	
    	 
    }
    
    
}
