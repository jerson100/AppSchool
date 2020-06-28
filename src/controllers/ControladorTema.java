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

import com.google.gson.Gson;

import constantes.AppColegio;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import exceptions.NotUpdated;
import interfaces.ICrud;
import models.Aul_Contenido;
import models.Sesion;

@MultipartConfig
@WebServlet(name="/ControladorTema",urlPatterns={"/tema"})
public class ControladorTema extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Contenido> daoContenido;
	private Gson JSON;
	
    public ControladorTema() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void init() throws ServletException {
    	daoContenido = DaoManager.getDaoManager(EDaoManager.AULCONTENIDO);
    	JSON = new Gson();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			                                 throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		Object sesion = request.getSession().getAttribute("usuarioSesion");
		
		if(sesion != null) {
			
			if(action!=null && !action.equals("")) {
				
				switch(action) {
				
					case "agregarContenido":
						
						agregarContenido(sesion,request,response);
						
						break;
						
					case "actualizarContenido":
						
						actualizarContenido(sesion,request,response);
						
						break;
						
					default:
						
						response.sendError(404);
						
						break;
				
				}
				
			}else {
				
				response.sendError(404);
				
			}
			
		}else {
			
			response.sendError(401);
			
		}
		
	}
	
	private void actualizarContenido(Object s, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		Sesion sesion = (Sesion)s;
		
		if(sesion != null) {
			
			if(sesion.getIdPerfil() == AppColegio.TIPO_DOCENTE ||
			   sesion.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR) {
				
				try {
				
					request.setCharacterEncoding("UTF-8");
					
					response.setContentType("Application/json;charset=UTF-8");
					
					int idContenido = Integer.parseInt(request.getParameter("idContenido"));
					
					String descContenido = request.getParameter("descContenido"),
						   link = request.getParameter("link");
					
					int idSecCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
					
					String replicarsolo = request.getParameter("replicarsolo"),
						   replicartodos = request.getParameter("replicartodos"),
						   message = "";
					
					String codContenido = request.getParameter("codContenido");
				
					boolean estado = false;
					
					Aul_Contenido co = new Aul_Contenido();
					
					co.setIdContenido(idContenido);
					
					co.setDescContenido(descContenido);
					
					co.setLink(link);
					
					co.setIdUsuario(sesion.getIdUsuario());
					
					co.setIdSecCurPro(idSecCurPro);
					
					co.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
					
					co.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
					
					co.setCodContenido(codContenido);
					
					try {
						
						daoContenido.update(co);
						
						estado = true;
						
						message = "Contenido actualizado satisfactoriamente";
						
					}catch (NotUpdated e) {
						
						message = e.getMessage();
						
					}
					
					Map<String, Object> map = new HashMap<>();
					
					map.put("mensaje", message);
					
					map.put("estado", estado);
					
					try(Writer w = response.getWriter()){
						
						w.write(JSON.toJson(map));
						
					}
					
				}catch (Exception e) {
					
					response.sendError(404);
					
				}
				
			}else {
				
				response.sendError(403);
				
			}
			
		}else {
			
			response.sendError(401);
			
		}
		
	}

	private void agregarContenido(Object sesion, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("Application/json;charset=UTF-8");
		
		try {
		
			int idSecCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
			
			String descContenido = request.getParameter("descContenido"),
				   link = request.getParameter("link"),
				   message = "";
			
			String replicarsolo = request.getParameter("replicarsolo"),
				   replicartodos = request.getParameter("replicartodos");
			
			Aul_Contenido co = new Aul_Contenido();
			
			boolean estado = false;
			
			co.setIdContenido(0);
			co.setDescContenido(descContenido);
			co.setLink(link);
			co.setIdUsuario(((Sesion)sesion).getIdUsuario());
			co.setIdSecCurPro(idSecCurPro);
			co.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
			co.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
			
			try {
				
				daoContenido.create(co);
				
				estado = true;
				
				message = "Contenido creado satisfactoriamente";
				
			}catch (NotCreated e) {
				
				message = e.getMessage();
				
			}
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("mensaje", message);
			
			map.put("estado", estado);
			
			try(Writer w = response.getWriter()){
				
				w.write(JSON.toJson(map));
				
			}
			
		}catch(Exception e) {
			
			response.sendError(404);
			
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Object sesion = request.getSession().getAttribute("usuarioSesion");
	
		try {
			
			if(sesion != null) {
				
				Sesion usuarioSesion = (Sesion)sesion;
				
				if(usuarioSesion.getIdPerfil() == AppColegio.TIPO_DOCENTE ||
				   usuarioSesion.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR) {
					
					request.setCharacterEncoding("UTF-8");
					
					response.setContentType("Application/json;charset=UTF-8");
					
					int idContenido = Integer.parseInt(request.getParameter("idContenido"));
					
					String replicarsolo = request.getParameter("replicarsolo");
					
					String replicartodos = request.getParameter("replicartodos");
					
					String message = "";
					
					boolean estado = false;
					
					Aul_Contenido co = new Aul_Contenido();
					
					co.setIdContenido(idContenido);
					
					co.setIdUsuario(usuarioSesion.getIdUsuario());
					
					co.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
					
					co.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
					
					try {
						
						daoContenido.delete(co);
						
						estado = true;
						
						message = "Contenido eliminado satisfactoriamente";
						
					}catch (NotDeleted e) {
						
						message = e.getMessage();
						
					}
					
					Map<String, Object> map = new HashMap<>();
					
					map.put("mensaje", message);
					
					map.put("estado", estado);
					
					try(Writer w = response.getWriter()){
						
						w.write(JSON.toJson(map));
						
					}
					
				}else {
					
					response.sendError(403);
					
				}
				
			}else {
				
				response.sendError(401);
				
			}
			
		}catch(Exception e) {
			
			response.sendError(404);
			
		}
		
	}

}
