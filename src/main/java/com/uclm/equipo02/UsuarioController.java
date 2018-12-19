package com.uclm.equipo02;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.uclm.equipo02.Auxiliar.Utilidades;
import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

@Controller
public class UsuarioController {
	private final String alert = "alerta";
	private final String gestionPwd = "gestionPwd";
	private final String usuario_conect = "usuarioConectado";

	UsuarioDaoImplement userDao = new UsuarioDaoImplement();

	@RequestMapping(value = "/modificarPwd", method = RequestMethod.POST)
	public String modificarPwd(HttpServletRequest request, Model model) throws Exception {
		Usuario usuarioLigero;
		try {
			sesionServidor(request);
			usuarioLigero = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "login";
		}
		String emailActual = usuarioLigero.getEmail();
		
		String pwdActual = request.getParameter("contrasenaActual");
		String pwdNueva = request.getParameter("contrasenaNueva");
		String pwdNueva2 = request.getParameter("contrasenaNueva2");
		String nombre = userDao.devolverUser(usuarioLigero);
	
		Usuario usuario = userDao.selectNombre(nombre);
		usuario.setEmail(emailActual);
		usuario.setPassword(pwdActual);
		
		
		
		if(!userDao.login(usuario)) {
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			model.addAttribute(alert, "Password actual incorrecta");
			return gestionPwd;
		}
		if(Utilidades.comprobarPwd(usuario.getDni(), pwdActual, pwdNueva)==false){
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			model.addAttribute("alertaPWDRepe","Password anteriormente utilizada");
			return gestionPwd;
		}
		if (usuario == null || !(pwdNueva.equals(pwdNueva2))) {
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			model.addAttribute(alert, "Datos incorrectos");
			return gestionPwd;
		}
		
		try {
	
		} catch (Exception e) {
			model.addAttribute(alert, e.getMessage());
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			return gestionPwd;
		}
		
		if(!Utilidades.seguridadPassword(pwdNueva)) {
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			model.addAttribute("alertaPWDinsegura","Password poco segura (minimo 8 caracteres, con numeros y letras)");
			return gestionPwd;
		}else {
			usuario.setPassword(pwdNueva);
			userDao.updatePwd(usuario);
			HttpSession session = request.getSession();
			request.setAttribute("nombreUser", usuario.getNombre());
			request.setAttribute("mailUser", usuario.getEmail());
			session.setAttribute("alertaCambio", "La contrase&ntilde;a ha sido cambiada satisfactoriamente");
			return gestionPwd;
		}
	}
	
	
	
	@RequestMapping(value = "/REfichajesUser", method = RequestMethod.GET)
	public ModelAndView REfichajesUser(HttpServletRequest request,Model model) {
		String returned="";
		Usuario usuario;
		try {
			sesionServidor(request);
			usuario = usuarioDeSesion(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("login");
		}
		if(usuario.getRol().equalsIgnoreCase("Empleado")) {
			returned="fichajes";
		}else if(usuario.getRol().equalsIgnoreCase("administrador")){
			returned="interfazAdministrador";

		}else if(usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
			returned="interfazGestor";
		}
		
		return new ModelAndView(returned);
	}
	
	//Codigo mantenimiento
	public void sesionServidor(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UsuarioDaoImplement daoAux = new UsuarioDaoImplement();
		String sessionKey = (String) session.getAttribute("sessionKey");
		LocalTime hora = LocalTime.now();
		int minutos = (int) ChronoUnit.MINUTES.between((Temporal) session.getAttribute("hora"), hora);
		if (!daoAux.existeSessionKey(sessionKey) || minutos > 10) {
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
