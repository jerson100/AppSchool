package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import exceptions.NotDeleted;
import interfaces.ICrud;
import models.Aul_Clases;
import models.Sesion;

@MultipartConfig
@WebServlet(name="/ControladorClases",urlPatterns= {"/clases"})
public class ControladorClases extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private ICrud<Aul_Clases> aulClasesDao;
	private static Gson JSON;
	 
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		
		this.aulClasesDao = DaoManager.getDaoManager(EDaoManager.AULCLASES);
		
		JSON = new Gson();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		response.setContentType("Application/json;charset=UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String msg = "";
		
		boolean estado = false;
		
		List<Aul_Clases> clases = null;
		
		if(us==null) {
		
			response.setStatus(401);
			
			msg = "Usted no puede ver este recurso";
			
		}else {
			
			try {
				
				int idSecCur = Integer.parseInt(request.getParameter("idSecCurPro"));
			
				try {
					
					clases = aulClasesDao.all(idSecCur);
					
					msg = "OK";
					
					estado = true;
					
				} catch (NotAll e) {
	
					msg = "Este contenido no tiene asigno clases en vivo";
					
				}
				
			}catch(Exception ee) {
				
				msg = "No se pudo llevar a cabo la acción";
				
			}
			
		}
		
		map.put("mensaje", msg);
		
		map.put("estado", estado);
		
		map.put("clases", clases);
		
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(map)); 
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		System.out.println(action);
		if(us != null) {
			
			if(us.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || 
			   us.getIdPerfil() == AppColegio.TIPO_DOCENTE) {
				
				if(action != null) {
					
					switch(action) {
						case "agregarClase":
							agregarClase(us,request,response);
							break;
						case "actualizarClase":
							actualizarClase(us,request,response);
							break;
					}
					
				}else {
					
					response.sendError(404);
					
				}
				
			}else {
				
				response.sendError(403);
				
			}
						
		}else {
			
			response.sendError(401);
			
		}
	}

	private void actualizarClase(Sesion user,HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("Application/json;charset=UTF-8");
		
		int idClass = Integer.parseInt(request.getParameter("idClass"));
		String codClass = request.getParameter("codClase");
		int idSeCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
		String descClase = request.getParameter("descClase");
		String link = request.getParameter("link");
		String fechaClase = request.getParameter("fechaClase");
		String horaClase = request.getParameter("horaClase");
		String replicarsolo = request.getParameter("replicarsolo"),
			   replicartodos = request.getParameter("replicartodos");
		
		String mensaje = "";
		boolean estado = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Aul_Clases obj = new Aul_Clases();
		
		try {
			
			obj.setIdClass(idClass);
			obj.setCodClase(codClass);
			obj.setIdSecCur(idSeCurPro);
			obj.setDescClase(descClase);
			obj.setLink(link);
			obj.setIdUsuario(user.getIdUsuario());
			obj.setFechaClase(fechaClase);
			obj.setHoraClase(horaClase);
			obj.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
			obj.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
			
			aulClasesDao.update(obj);
			
			mensaje = "Clase actualizada satisfactoriamente";
			
			estado = true;
			
		} catch (Exception e) {
			
			mensaje = "No se pudo actualizar la clase";
			
			response.setStatus(404);
			
		}
		
		map.put("mensaje", mensaje);
		map.put("estado",estado);
		
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(map));
		}
	}

	private void agregarClase(Sesion user,HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("Application/json;charset=UTF-8");
		
		int idSeCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
		String descClase = request.getParameter("descClase");
		String link = request.getParameter("link");
		String fechaClase = request.getParameter("fechaClase");
		String horaClase = request.getParameter("horaClase");
		String replicarsolo = request.getParameter("replicarsolo"),
			   replicartodos = request.getParameter("replicartodos");
		
		String mensaje = "";
		boolean estado = false;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Aul_Clases obj = new Aul_Clases();
		
		try {
			
			obj.setIdSecCur(idSeCurPro);
			obj.setDescClase(descClase);
			obj.setLink(link);
			obj.setFechaClase(fechaClase);
			obj.setHoraClase(horaClase);
			obj.setIdUsuario(user.getIdUsuario());
			obj.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
			obj.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
			
			aulClasesDao.create(obj);
			
			mensaje = "Clase creada satisfactoriamente";
			
			estado = true;
			
		} catch (Exception e) {
			
			mensaje = "No se pudo crear la clase";
			
			response.setStatus(404);
			
		}
		
		map.put("mensaje", mensaje);
		map.put("estado",estado);
		
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(map));
		}
		
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
					
					int idClass = Integer.parseInt(request.getParameter("idClass"));
					
					String replicarsolo = request.getParameter("replicarsolo");
					
					String replicartodos = request.getParameter("replicartodos");
					
					String message = "";
					
					boolean estado = false;
					
					Aul_Clases co = new Aul_Clases();
					
					co.setIdClass(idClass);
					
					co.setIdUsuario(usuarioSesion.getIdUsuario());
					
					co.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
					
					co.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
					
					try {
						
						aulClasesDao.delete(co);
						
						estado = true;
						
						message = "Clase eliminado satisfactoriamente";
						
					}catch (NotDeleted e) {
						
						message = "No se pudo eliminar la clase";
						
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
