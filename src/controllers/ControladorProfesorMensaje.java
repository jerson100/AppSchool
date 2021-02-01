package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import constantes.AppColegio;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import interfaces.ICrud;
import models.Aul_ProfesorMensaje;
import models.Sesion;

@WebServlet(name="/ControladorProfesorMensaje", urlPatterns = "/profesorMensaje")
public class ControladorProfesorMensaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ICrud<Aul_ProfesorMensaje> dao;
    private static Gson JSON;

    @SuppressWarnings("unchecked")
	public ControladorProfesorMensaje() {
        super();
        JSON =  new Gson();
        dao  =  DaoManager.getDaoManager(EDaoManager.AULPROFESORMENSAJE);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Sesion us_session = (Sesion)session.getAttribute("usuarioSesion");
		
		// tenemos que verificar que el usuario es docente, eso lo haremos con un filter
		
		if(us_session != null) { 
			
			if (us_session.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || 	
					us_session.getIdPerfil() == AppColegio.TIPO_DOCENTE ||
					us_session.getIdPerfil() == AppColegio.TIPO_ALUMNO)  {
				
				response.setContentType("Application/json;charset=utf-8");
				
				String idSecCur = request.getParameter("idSecCur");
				
				Map<String, Object> map = new HashMap<>();
				
				String message = "";
				
				boolean status = false;
				
				List<Aul_ProfesorMensaje> list = null;

				if(idSecCur != null) {
					
					try {
						
						list = dao.all(Integer.parseInt(idSecCur));
						
						status = true;
						
						message = "ok!";
						
					}catch(NotAll e) {
						
						message = "No se encontraron mensajes";
						
					}catch(Exception e) {
						
						message = "Ocurrió un error en el servidor";
						response.setStatus(500);
						
					}
					
				}else {
					
					message = "El idSecCur es requerido";
					response.setStatus(400);
					
				}
				
				map.put("mensaje", message);
				
				map.put("estado", status);
				
				map.put("alumnos",list);
				
				try(Writer w = response.getWriter()){
					
					w.write(JSON.toJson(map));
					
				}
				
			}else {
			
				response.sendError(403);
			
			}
	
		}else {
			
			response.sendError(401);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
