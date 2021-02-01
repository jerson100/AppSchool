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
import models.Aul_SeccionAlumno;
import models.Sesion;

@WebServlet(name="/ControladorSeccionAlumno", urlPatterns= {"/alumnos"})
public class ControladorSeccionAlumno extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ICrud<Aul_SeccionAlumno> dao;
    private static Gson JSON;
    
    @SuppressWarnings("unchecked")
	public ControladorSeccionAlumno() {
        super();
        JSON =  new Gson();
        dao  =  DaoManager.getDaoManager(EDaoManager.AULSECCIONALUMNO);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Sesion us_session = (Sesion)session.getAttribute("usuarioSesion");
		
		// tenemos que verificar que el usuario es docente, eso lo haremos con un filter
		
		if(us_session != null) { 
			
			if (us_session.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || 	
					us_session.getIdPerfil() == AppColegio.TIPO_DOCENTE)  {
				
				response.setContentType("Application/json;charset=utf-8");
		
				String idSecGraNiv = request.getParameter("idSecGraNiv");
				
				Map<String,Object> map = new HashMap<>();
				
				String message = "";
				
				boolean status = false;
				
				List<Aul_SeccionAlumno> list = null;

				if(idSecGraNiv != null) {
					
					try {
						
						list = dao.all(Integer.parseInt(idSecGraNiv));
						
						status = true;
						
						message = "ok!";
						
					}catch(NotAll e) {
						
						message = "No se encontraron alumnos";
						
					}catch(Exception e) {
						
						message = "Ocurrió un error en el servidor";
						response.setStatus(500);
						
					}
					
				}else {
					
					message = "El idSecGraNiv es requerido";
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
