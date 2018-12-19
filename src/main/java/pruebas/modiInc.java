package pruebas;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.uclm.equipo02.persistencia.DAOIncidencia;

public class modiInc {

WebDriver  driver;
	
	@Before
	public void precondiciones() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://localhost:8090/equipo02/");
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Contrasena1");
		
		driver.findElement(By.name("acceso")).click();
		
		
		Thread.sleep(1000);
	}
	@Test
	public void testModificarIncidenciaConTodosSusCampos() {
		
       String cadena1,cadena2,cadena3;
        cadena1="Vacaciones";
        cadena2="2018-01-01";
        cadena3="prueba123";
		driver.findElement(By.name("modificarIncidencia")).click();
        driver.findElement(By.name("seleccionar")).click();
        driver.findElement(By.name("listaTiposIncidencia")).sendKeys(cadena1);
		driver.findElement(By.name("txtFecha")).sendKeys(cadena2);
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys(cadena3);
		driver.findElement(By.name("Resolver")).click();
		
		assertTrue(estaEnBD(cadena1,cadena2,cadena3));
		
	}
	@Test
	public void testModificarIncidenciaConEspaciosEnBlanco() {
		
       String cadena1,cadena2,cadena3;
        cadena1="Vacaciones";
        cadena2=" ";
        cadena3=" ";
		driver.findElement(By.name("modificarIncidencia")).click();
        driver.findElement(By.name("seleccionar")).click();
        driver.findElement(By.name("listaTiposIncidencia")).sendKeys(cadena1);
		driver.findElement(By.name("txtFecha")).sendKeys(cadena2);
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys(cadena3);
		driver.findElement(By.name("Resolver")).click();
		
		assertTrue(!estaEnBD(cadena1,cadena2,cadena3));
		
	}
	@Test
	public void testModificarIncidenciaConUnEspacioEnBlanco() {
		
       String cadena1,cadena2,cadena3;
        cadena1="Vacaciones";
        cadena2="  ";
        cadena3="";
		driver.findElement(By.name("modificarIncidencia")).click();
        driver.findElement(By.name("seleccionar")).click();
        driver.findElement(By.name("listaTiposIncidencia")).sendKeys(cadena1);
		driver.findElement(By.name("txtFecha")).sendKeys(cadena2);
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys(cadena3);
		driver.findElement(By.name("Resolver")).click();
		
		assertTrue(!estaEnBD(cadena1,cadena2,cadena3));
		
	}
	@Test
	public void testModificarIncidenciaMayor255Carac() {
		
       String cadena1,cadena2,cadena3;
        cadena1="Vacaciones";
        cadena2="h";
        cadena3="111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		driver.findElement(By.name("modificarIncidencia")).click();
        driver.findElement(By.name("seleccionar")).click();
        driver.findElement(By.name("listaTiposIncidencia")).sendKeys(cadena1);
		driver.findElement(By.name("txtFecha")).sendKeys(cadena2);
		driver.findElement(By.name("textoIncidencia")).clear();
		driver.findElement(By.name("textoIncidencia")).sendKeys(cadena3);
		driver.findElement(By.name("Resolver")).click();
		
		assertTrue(estaEnBD(cadena1,cadena2,cadena3));
		
	}
	private boolean estaEnBD(String cadena1, String cadena2, String cadena3) {
		DAOIncidencia inc=new DAOIncidencia(); 
		List<Document> lista=inc.getIncidencias("88888888Q");
		int i=0;
		String cadena="";
		boolean result=false;
		while(i<lista.size()) {
			
			if((lista.get(i).getString("categoria").equals(cadena1))&&
					(lista.get(i).getString("fechaCreacion").equals(cadena2))&&
					(lista.get(i).getString("descripcion").equals(cadena3)))result=true;
		
			i++;
		}
		return result;
	}
}
