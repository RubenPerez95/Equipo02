package pruebas;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import com.uclm.equipo02.modelo.Usuario;

public class DarAltaUnEmpl {
	@Test
	public void testCrearUser() {
		Usuario user =new Usuario("carlos","1234","ejemplo@hotmail.com","1234","12345678T");

		assertTrue(user.getNombre().equals("carlos")&&user.getPassword().equals("1234")&&user.getEmail().equals("ejemplo@hotmail.com")
				&&user.getRol().equals("1234")&&user.getDni().equals("12345678T"));
		
	}
}
