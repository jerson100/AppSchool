package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Sesion;

@WebServlet(name="/ControladorMantenimientoUsuario",urlPatterns = {"/mantenimientoUsuario"})
public class ControladorMantenimientoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Gson JSON;   
	
    @Override
    	public void init() throws ServletException {
    		JSON = new Gson();
    	}
	
    public ControladorMantenimientoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("WEB-INF/pages/mantenimientoUsuario.jsp").forward(request, response);
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "Aún estamos trabajando en ello.");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		Object ses = request.getSession().getAttribute("usuarioSesion");
		
		String action = request.getParameter("action");
		
		if(action != null) {
			
			if(ses != null) {
				
				Sesion session = (Sesion)ses;
				
				if(session.getIdPerfil() == 3) {
					
					switch (action) {
						case "agregarAlumno":
							agregarActualizar(session,request,response);
							break;
						default:
							break;
						}
					
				}else {
					
					response.sendError(403);
					
				}
				
			}else {
				
				response.sendError(401);
				
			}
			
		}else {
			
			response.sendError(404);
			
		}
		
		
		
		
	}

	private void agregarActualizar(Sesion session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("Application/json;charset=utf-8");
		
		try {
		
			String dni = request.getParameter("dni");
			String nombres = request.getParameter("nombres");
			String apellidoP = request.getParameter("apellidoP");
			String apellidoM = request.getParameter("apellidoM");
			int seccion = Integer.parseInt(request.getParameter("seccion"));
			int sexo = Integer.parseInt(request.getParameter("sexo"));
			@SuppressWarnings("unused")
			String inhabilitar = request.getParameter("inhabilitar");
			String us = request.getParameter("us");
			String pass = request.getParameter("pass");
			
			if(dni != null && nombres != null && apellidoP != null 
					&& apellidoM != null && seccion != 0 
					&& sexo != 0 && us != null && pass != null) {
				
				String message = "";
				boolean status = false;
				
				//enviamos la data a la bd
				
				Map<String, Object> map = new HashMap<>();
				map.put("estado",status);
				map.put("mensaje",message);
				
				try(Writer w  = response.getWriter()){
					
					w.write(JSON.toJson(map));
					
				}
				
			}else {
				
				response.sendError(404);
				
			}
			
		}catch(Exception e) {
			response.sendError(404);
		}
		
	}

}
