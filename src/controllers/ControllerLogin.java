package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.SesionDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotFound;
import interfaces.ICrud;
import models.Sesion;

@MultipartConfig
@WebServlet(name="/ControllerLogin",urlPatterns= {"/login"})
public class ControllerLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Sesion> sesionDao;
	private static Gson JSON;
	 
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		
		this.sesionDao = DaoManager.getDaoManager(EDaoManager.SESIONDAO);
		
		JSON = new Gson();
		
	}
	
    public ControllerLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("usuarioSesion") != null) {
			
			response.sendRedirect("inicio");
			
		}else {
			
			request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if(action != null) {
			
			switch (action) {
			
				case "cerrarSesion":
					
					cerrarSession(req,resp);
					
					break;
	
				case "acceder":
					
					acceder(req,resp);
					
					break;
					
				default:
					
					resp.sendError(404,"Estás navegando por lugares que no debes :-)");
					
			}
			
		}else {
			
			resp.sendError(404,"Estás navegando por lugares que no debes :-)");
			
		}
		
	}

	private void cerrarSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json;charset=UTF-8");
		
		Map<String,Object> map = new HashMap<>();
		
		boolean status = false;
		
		String url = "";
		
		if(req.getSession().getAttribute("usuarioSesion")==null) {
			
			resp.sendError(404,"Usted no puede cerrar sesión");
			
		}else {
			
			req.getSession().removeAttribute("usuarioSesion");
			
			/*resp.setHeader("Cache-Control","no-store");*/
			
			status = true;
			
			resp.setStatus(200);
			
			url = "login";
			
		}
		
		map.put("estado", status);
		
		map.put("url",url);
		
		try(Writer w = resp.getWriter()){
			
			w.write(JSON.toJson(map));
			
		}
		
	}

	private void acceder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json;charset=UTF-8");
		
		String usuario = req.getParameter("username");
		String contrasena = req.getParameter("password");
		
		String mensaje = "";
		boolean estado = false;
		Sesion sesion = null;
		try {
			
			sesion = ((SesionDao)sesionDao).acceder(usuario, contrasena);
			
			if(sesion != null) {
				
				HttpSession objSesion = req.getSession();
			
				objSesion.setAttribute("usuarioSesion", sesion);
				
				estado = true;
				
				mensaje = "Bienvenido: " + sesion.getNombres();
				
			}
			
		} catch (NotFound e) {
			
			mensaje = e.getMessage();
			
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("estado",estado);
		
		map.put("mensaje",mensaje);
		
		map.put("idPerfil",sesion!=null?sesion.getIdPerfil():0);
		
		try( Writer w = resp.getWriter() ){
			
			w.write(JSON.toJson(map));
			
		}
		
	}
	
}
