package pruebas;


import java.util.List;

import org.bson.Document;

import com.uclm.equipo02.persistencia.DAOIncidencia;

public class principal {
public static void main(String[]args) {
	DAOIncidencia inc=new DAOIncidencia(); 
	List<Document> lista=inc.getIncidencias("12345678T");
	int i=0;
	String cadena="";
	while(i<lista.size()) {
		cadena=lista.get(i).getString("dniUsuario");
		i++;
	}
	System.out.println(cadena);

}
}
