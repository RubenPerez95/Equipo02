package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.uclm.equipo02.IncidenciaController;
import com.uclm.equipo02.modelo.Incidencia;
import com.uclm.equipo02.persistencia.DAOIncidencia;

public class ReslInc {

	WebDriver  driver;
	
	@Before
	public void precondiciones() throws Throwable {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		  driver = new ChromeDriver();
	
	    driver.get("http://localhost:8090/equipo02/");
		Thread.sleep(1000);
		
		driver.findElement(By.name("txtUsuarioEmail")).sendKeys("rafapx2@gmail.com");
		driver.findElement(By.name("txtUsuarioPassword")).sendKeys("Contrasena1");
		
		driver.findElement(By.name("acceso")).click();
		
		
		Thread.sleep(1000);
	}
	
	
    @Test
    public void ResolverIncidenciaEnBlanco() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
    	driver.findElement(By.name("Resolver")).click();

		try {
			driver.findElement(By.xpath("//form[1]"));
							
		}
catch(NoSuchElementException excp){
	fail("salta excepcion");
	
};
assertTrue(estaEnBD());
    }
    
    
    
	
	


	@Test
    public void ResolverIncidenciaEnBlancoMenosElComentario() 
    {
    	IncidenciaController inc=new IncidenciaController();
    	driver.findElement(By.name("resolverIncidencia")).click();
    	driver.findElement(By.name("textoGestor")).sendKeys("1234");
    	driver.findElement(By.name("Resolver")).click();
    	
    	
		
    			try {
    				driver.findElement(By.xpath("//form[1]"));
    								
    			}
    	catch(NoSuchElementException excp){
    		fail("salta excepcion");
    		
    	};
    	
    	
    
    	assertTrue(estaEnBD());
   
    }
   
		
	
	@Test
    public void ResolverIncidenciaEnBlancoYComentarioConEspacios() 
    {
    	driver.findElement(By.name("resolverIncidencia")).click();
	driver.findElement(By.name("textoGestor")).sendKeys(" ");
    	driver.findElement(By.name("Resolver")).click();

		try {
			driver.findElement(By.xpath("//form[1]"));
							
		}
catch(NoSuchElementException excp){
	fail("salta excepcion");
	
};
assertTrue(estaEnBD());	 
    }
    
	@Test
    public void ResolverIncidenciaRellenaYComentarioConEspacios() 
    {
    	DAOIncidencia inc =new DAOIncidencia();
    	List<Document> lista=inc.getIncs();
    	driver.findElement(By.name("resolverIncidencia")).click();
    	
    	driver.findElement(By.name("seleccionar")).click();
	driver.findElement(By.name("textoGestor")).sendKeys(" ");
    	driver.findElement(By.name("Resolver")).click();
    	boolean result=ComprSiSeModInc(lista," ");
    	assertTrue(!result);
    }
    
    
	
    
    
	private boolean comprobarSiSeModInc(List<Document> lista,String comen) {
		boolean result=false;
		int i=0;
		String estado="";
		String comentario="";
		while(i<lista.size()) {
			estado=lista.get(i).getString("estado");
			comentario=lista.get(i).getString("comentarioGestor");
			System.out.println(estado+" "+comentario);
			if(estado.equals("Resuelta")&&comentario.equals(comen)) {
				result=true;
			}
			i++;
		}
		return result;
	}
    
	private boolean ComprSiSeModInc(List<Document> lista, String comen) {
		
    	boolean result=false;
		int i=0;
		String estado="";
		String comentario="";
		while(i<lista.size()) {
			estado=lista.get(i).getString("estado");
			comentario=lista.get(i).getString("comentarioGestor");
			if(estado.equals("Resuelta")&&comentario.equals(comen)) {
				result=true;
			}
			i++;
		}
		return result;
	}
	@Test
    public void ResolverIncidenciaRellenaYComentarioConMensaje() throws InterruptedException 
    {
    	DAOIncidencia inc =new DAOIncidencia();
    	driver.findElement(By.name("resolverIncidencia")).click();
    	driver.findElement(By.name("seleccionar")).click();
    	int aleatorio1= (int) (Math.random() * 1000000) + 1;
    	int aleatorio2= (int) (Math.random() * 1000000) + 1;
    	String al1=aleatorio1+"";
    	String al2=aleatorio2+"";
	    driver.findElement(By.name("textoGestor")).sendKeys(al1+al2);
    	driver.findElement(By.name("Resolver")).click();
    	Thread.sleep(1000);
    	List<Document> lista=inc.getIncs();
       boolean result= comprobarSiSeModInc(lista,al1+al2);
    	assertTrue(result);
    	 
    }
	private boolean estaEnBD() {
		DAOIncidencia inc=new DAOIncidencia(); 
		List<Document> lista=inc.getIncidencias("12345678T");
		int i=0;
		String cadena="";
		boolean result=true;
		while(i<lista.size()) {
			
			if(lista.get(i).getString("dniUsuario").equals(""))result=false;
			
		}
		return result;
	}

	}
