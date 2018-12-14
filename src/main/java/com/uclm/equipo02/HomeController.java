package com.uclm.equipo02;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uclm.equipo02.modelo.Usuario;
import com.uclm.equipo02.persistencia.UsuarioDaoImplement;

@Controller

public class HomeController {


	private final String usuario_login = "login";
	private final String usuario_conect = "usuarioConectado";
	private final String alert = "alerta";
	private final String usuarioServ = "usuario";
	private final String name = "nombre";
	private final String password = "pwd";
	private final String email = "email";
	private final String rol = "rol";
	private final String dni = "dni";

	private final String welcome = "welcome";



	UsuarioDaoImplement userDao = new UsuarioDaoImplement();


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String iniciarSesion(HttpServletRequest request, Model model) throws Exception {
		String email = request.getParameter("txtUsuarioEmail");
		String password = request.getParameter("txtUsuarioPassword");
		String sessionHex;
		if (email.equals("") || password.equals("")) {
			model.addAttribute(alert, "Por favor rellene los campos");
			return usuario_login;
		}
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setPassword(password);
		try {
			String nombre =  userDao.devolverUser(usuario);
			usuario.setNombre(nombre);
			String dni = userDao.devolverDni(usuario);
			usuario.setDni(dni);
		}catch(Exception e) {

		}
		sessionHex = sesionServidor(request);
		/*if (userDao.login(usuario) && request.getSession().getAttribute(usuario_conect) == null){*/
		if(userDao.login(usuario) && sessionHex != null){
			usuario.setRol(userDao.devolverRol(usuario));

			if(usuario.getRol().equalsIgnoreCase("empleado")) {
				request.getSession().setAttribute("sessionkey", sessionHex);
				request.setAttribute("nombreUser", usuario);
				request.setAttribute("mailUser", email);
				request.setAttribute("dniUser", dni);
				return "fichajes";
			}else if (usuario.getRol().equalsIgnoreCase("administrador")){
				request.getSession().setAttribute("sessionkey", sessionHex);
				request.setAttribute("nombreUser", usuario.getNombre());
				request.setAttribute("mailUser", email);
				request.setAttribute("dniUser", dni);
				return "interfazAdministrador";
			}else if (usuario.getRol().equalsIgnoreCase("Gestor de incidencias")){
				request.getSession().setAttribute("sessionkey", sessionHex);
				request.setAttribute("nombreUser", usuario.getNombre());
				request.setAttribute("mailUser", email);
				request.setAttribute("dniUser", dni);
				return "interfazGestor";
			}

		}else{
			model.addAttribute(alert, "Usuario y/o clave incorrectos");
			return usuario_login;
		}
		return usuario_login;
	}

	//Código nuevo
	protected String sesionServidor(HttpServletRequest request/*, HttpServletResponse response*/) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String sessionKey = (String) session.getAttribute("sessionkey");
	    String remoteAddr = request.getRemoteAddr();
	    int remotePort = request.getRemotePort();
	    String sha256Hex = DigestUtils.sha256Hex(remoteAddr + remotePort);
	    /*if (sessionKey == null || sessionKey.isEmpty()) {
	        session.setAttribute("sessionkey", sha256Hex);
	        // save mapping to memory to track which user attempted
	       // Application.userSessionMap.put(sha256Hex, remoteAddr + remotePort);
	    } else */if (!sha256Hex.equals(sessionKey)) {
	        session.invalidate();
//	        response.getWriter().append(Application.userSessionMap.get(sessionKey));
//	        response.getWriter().append(" attempted to hijack session id ").append(request.getRequestedSessionId()); 
//	        response.getWriter().append("of user ").append(Application.userSessionMap.get(sha256Hex));
	        return null;
	    }
	    //response.getWriter().append("Valid Session\n");
	    return sha256Hex;
	}
	
	
	
	public ModelAndView cambiarVista(String nombreVista) {
		ModelAndView vista = new ModelAndView(nombreVista);
		return vista;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView cerrarSesion(HttpServletRequest request) throws Exception {
		HttpSession sesion = request.getSession();

		System.out.println("Sesion antes de invalidar: " + sesion);
		sesion.invalidate();
		System.out.println("Invalidamos la sesion: " + sesion);

		return cambiarVista(usuario_login);
	}
}
