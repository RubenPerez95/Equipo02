package test;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class testCaseCrearIncidencia {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.katalon.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testUntitledTestCase() throws Exception {
		selenium.open("https://mantenimientoequipo2.herokuapp.com/login");
		selenium.click("name=crearIncidencia");
		selenium.click("name=listaTiposIncidencia");
		selenium.select("name=listaTiposIncidencia", "label=Error de fichaje");
		selenium.click("name=listaTiposIncidencia");
		selenium.click("name=textoIncidencia");
		selenium.type("name=textoIncidencia", "Hay un error en mi fichaje del día 3 de Diciembre de 2018");
		selenium.click("name=Aceptar");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
