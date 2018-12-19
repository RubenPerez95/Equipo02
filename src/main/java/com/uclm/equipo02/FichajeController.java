package com.uclm.equipo02;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Fichaje;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.DAOFichaje;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

import org.bson.Document;
import org.bson.types.ObjectId;

@Controller
public class FichajeController {

	DAOFichaje fichajedao = new DAOFichaje();


	private final String usuario_conect = "token";
	private final String errorMessageAbrir = "errorMessageAbrir";
	private final String errorMessageCerrar = "errorMessageCerrar";
	private final String fichajes = "fichajes";
	private final String interfazAdministrador="interfazAdministrador";
	private final String alertaFichaje="alertaFichaje";
	private final String interfazGestor="interfazGestor";


	@RequestMapping(value = "abrirFichajeGeneral", method = RequestMethod.POST)
	public String abrirFichajeGeneral(HttpServletRequest request, Model model) throws Exception {
		String returned="";
		String hora,fecha;
		ObjectId id;

		Usuario usuario;
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "login";
		}

		hora=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();



		Fichaje fichaje = new Fichaje(usuario.getDni(), fecha, hora,null,true);

		if(!fichajedao.validezAbierto(fichaje)) {
			model.addAttribute(errorMessageAbrir, "No puedes abrir otro fichaje, necesitas cerrar tu fichaje actual");
			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}

		}else {
			id=fichajedao.abrirFichaje(fichaje);

			Fichaje fichajeabierto = new Fichaje(id,usuario.getDni(), fecha, hora,null,true);

			model.addAttribute("seleccionadoFichaje",fichajeabierto);

			model.addAttribute(alertaFichaje,"Ha abierto un fichaje correctamente");

			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}
		}
		return returned;
	} 

	@RequestMapping(value = "cerrarFichajeGeneral", method = RequestMethod.POST)
	public String cerrarFichajeGeneral(HttpServletRequest request, Model model) throws Exception {
		Usuario usuario;
		String returned="";
		String fecha, horaentrada, idAbiertoString;
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "login";
		}
		fecha=(java.time.LocalDate.now()).toString();
		horaentrada=fichajedao.getHoraEntrada(usuario.getDni(),fecha);


		idAbiertoString = request.getParameter("idFichajeAbierto");
		ObjectId idAbierto=new ObjectId(idAbiertoString);

		String horaactual;
		horaactual=fichajedao.getCurrentTimeUsingCalendar();
		fecha=(java.time.LocalDate.now()).toString();

		Fichaje fichaje = new Fichaje(idAbierto,usuario.getDni(), fecha,horaentrada,horaactual,false);

		if(fichajedao.validezCerrado(fichaje)) {
			fichajedao.cerrarFichaje(usuario, fichaje);
			model.addAttribute(alertaFichaje,"Ha cerrado un fichaje correctamente");
			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}

		}else {
			model.addAttribute(errorMessageCerrar, "No puedes cerrar ning&uacuten fichaje, necesitas fichar para cerrar un fichaje");
			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}
		}
		return returned;

	} 

	@RequestMapping(value = "consultaFichajesFechaGeneral", method = RequestMethod.GET)
	public String consultaFichajesFechaGeneral(HttpServletRequest request, Model model) {
		Usuario usuario;
		String returned="";
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "login";
		}
		String dni = usuario.getDni();
		String fecha1= request.getParameter("fecha1");
		String fecha2= request.getParameter("fecha2");

		if(!DAOFichaje.existeFichajesPeriodo(dni, fecha1,fecha2)) {
			model.addAttribute("nullFecha","No existe ning&uacuten fichaje en ese periodo de fechas");

			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}
		}else {
			List<Document> listaFichajesFecha =DAOFichaje.listarFichajesPeriodo(dni, fecha1,fecha2);
			model.addAttribute("listafichajes", listaFichajesFecha);

			if(usuario.getRol().equalsIgnoreCase("Empleado")) {
				returned=fichajes;
			}else if(usuario.getRol().equalsIgnoreCase("administrador")){
				returned=interfazAdministrador;

			}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				returned=interfazGestor;
			}
		}
		return returned;
	}

	/***Redireccion a gestionPwd***/


	@RequestMapping(value = "/gestionPwd", method = RequestMethod.GET)
	public ModelAndView gestionPwd(HttpServletRequest request) {
		Usuario usuario;
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("login");
		}
		request.setAttribute("nombreUser", usuario.getNombre());
		request.setAttribute("mailUser", usuario.getEmail());
		return new ModelAndView("gestionPwd");
	}

	@RequestMapping(value = "REfichajes", method = RequestMethod.GET)
	public ModelAndView REfichajes() {
		return new ModelAndView("fichajes");
	}

	@RequestMapping(value = "/modificarIncidencia", method = RequestMethod.GET)
	public ModelAndView modificarIncidencia(HttpServletRequest request) {
		Usuario usuario;
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("login");
		}
		request.setAttribute("nombreUser", usuario.getNombre());
		request.setAttribute("dniUser", usuario.getDni());
		return new ModelAndView("modificarIncidencia");
	}

	//Codigo mantenimiento
	public void sesionServidor(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UsuarioDaoImplement daoAux = new UsuarioDaoImplement();
		String sessionKey = (String) session.getAttribute("sessionKey");
		LocalTime hora = LocalTime.now();
		int minutos = (int) ChronoUnit.MINUTES.between((Temporal) session.getAttribute("hora"), hora);
		if (!daoAux.existeSessionKey(sessionKey) || minutos > 1) {
			session.invalidate();
		}
		session.setAttribute("hora", hora);
	}

	public Usuario usuarioDeSesion(HttpServletRequest request) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsuarioDaoImplement daoAux = new UsuarioDaoImplement();
		Usuario usuarioSesion = daoAux.usuarioDeSesion((String) session.getAttribute("sessionKey"));

		return usuarioSesion;
	}
}
