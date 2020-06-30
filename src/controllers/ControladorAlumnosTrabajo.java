package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;

import constantes.AppColegio;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import interfaces.ICrud;
import models.Aul_Trabajo;
import models.Aul_TrabajosAlumno;
import models.Sesion;
import utilidades.JeVlidate;
import utilidades.Storage;

@WebServlet(name="/ControladorAlumnosTrabajo",urlPatterns= {"/alumnosTrabajo"})
@MultipartConfig
public class ControladorAlumnosTrabajo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson JSON;
	private ICrud<Aul_TrabajosAlumno> dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		this.JSON = new Gson();
		this.dao = DaoManager.getDaoManager(EDaoManager.AULTRABAJOSALUMNO);
	}
	
    public ControladorAlumnosTrabajo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Sesion sesion = (Sesion) request.getSession().getAttribute("usuarioSesion");
		
		String mensaje = "";
		boolean estado = false;
		Map<String, Object> map = new HashMap<String, Object>();
		
		String idTrabajo = request.getParameter("idTrabajo");
		
		if(sesion != null) {
			
			if(idTrabajo != null) {
				
				List<Aul_TrabajosAlumno> list = null;
				
				response.setContentType("Application/json;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				
				try {
					
					list = dao.all(Integer.parseInt(idTrabajo));
					
					estado = true;
					
					mensaje = "OK!";
					
				}catch (NotAll e) {
					
					e.printStackTrace();
					
					mensaje = e.getMessage();
					
				}
				
				map.put("estado", estado);
				map.put("mensaje", mensaje);
				map.put("list",list);
				
				try(Writer w = response.getWriter()){
					
					w.write(JSON.toJson(map));
					
				}
							
			}else {
				
				response.sendError(404);
				
			}
			
			
		}else {
			
			response.sendError(401);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		if(us != null) {
			
			if(action != null) {
					
				switch(action) {
					case "subirArchivo":
						subirArchivo(us, request, response);
						break;
					case "actualizarNota":
						actualizarNota(us,request,response);
						break;
					default:
						response.sendError(404);
				}
					
			}else {
					
				response.sendError(404);
					
			}
						
		}else {
			
			response.sendError(401);
			
		}
	}

	private void actualizarNota(Sesion sesion, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			
			if(sesion.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR ||
			   sesion.getIdPerfil() == AppColegio.TIPO_DOCENTE	) {
					
				request.setCharacterEncoding("UTF-8");
				
				response.setContentType("Application/json;charset=UTF-8");
				
				int idTraAlu = Integer.parseInt(request.getParameter("idTraAlu"));
				
				int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
				
				int idTrabajo = Integer.parseInt(request.getParameter("idTrabajo"));
				
				String notaTrabajo = request.getParameter("notaTrabajo");
				
				String comentario = request.getParameter("comentario");
				
				String mensaje = "";
				
				boolean estado = false;
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				Aul_TrabajosAlumno al = new Aul_TrabajosAlumno();
				
				al.setIdCuenta(idCuenta);
				al.setIdTraAlu(idTraAlu);
				al.setIdTrabajo(idTrabajo);
				al.setNotaTrabajo(notaTrabajo);
				al.setComentario(comentario);
				al.setIdUsuario(sesion.getIdUsuario());
				
				try {
					dao.update(al);	
					estado = true;
					mensaje = "Actualización Exitosa";
				}catch(Exception e) {
					e.printStackTrace();
					mensaje = "No se pudo llevar a cabo la actualización";
				}
				
				map.put("mensaje", mensaje);
				map.put("estado",estado);
					
				try(Writer w = response.getWriter()){
					w.write(JSON.toJson(map));
				}
			}else {
				response.sendError(403);
			}
			
		}catch(Exception e) {
			response.sendError(404);
			e.printStackTrace();
		}
	}

	private void subirArchivo(Sesion sesion,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if(sesion.getIdPerfil() == AppColegio.TIPO_ALUMNO) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			String mensaje = "";
			boolean estado = false;

			try {
				
				response.setContentType("Application/json;charset=UTF-8");
				
				Part file = request.getPart("file");
				int idTraAlu = request.getParameter("idTraAlu") == null ? 0 : Integer.parseInt(request.getParameter("idTraAlu")),
					idTrabajo= Integer.parseInt(request.getParameter("idTrabajo"));		
				
				String nombreArchivoAnterior = request.getParameter("nombreArchivoAnterior"),
				       extensionArchivo = JeVlidate.obtenerExtension(nombreArchivoAnterior),
					   rutaArchivo = request.getParameter("rutaArchivo"),
					   nuevoNombreArchivo = request.getParameter("nuevoNombreArchivo");
				
				Aul_TrabajosAlumno obj = new Aul_TrabajosAlumno();
				
				boolean estadoU = false;
				
				if(!file.getSubmittedFileName().isEmpty()) {
					try {
							
						String newPath = JeVlidate.generarNombreArchivo(file.getSubmittedFileName(), new Date().getTime(), JeVlidate.generarNumeroAleatorio(50, 99999999), JeVlidate.obtenerExtension(file.getSubmittedFileName()));
							
						//insertamos el nuevo
						Storage.addAndUpdateBlob(request, "ALUMNOS", newPath, file.getContentType(), "2020", file.getInputStream(), file.getSize());
							
						if(!rutaArchivo.isEmpty()) {
							try {
								//eliminamos el anterior
								Storage.deleteBlob(request, rutaArchivo.replaceAll("2020/", ""), "2020");
							}catch(Exception e) {}
						}
						nombreArchivoAnterior = file.getSubmittedFileName();
						extensionArchivo = JeVlidate.obtenerExtension(file.getSubmittedFileName());
						rutaArchivo = "2020/ALUMNOS/"+newPath;
						estadoU = true;
					}catch(Exception e) {
						mensaje = "No se pudo agregar el nuevo archivo";
					}
				}else if(!nombreArchivoAnterior.equals(nuevoNombreArchivo)) {
					//eliminamos el anterior
					try {
						Storage.deleteBlob(request, rutaArchivo.replaceAll("2020/", ""), "2020");
						nombreArchivoAnterior = "";
						extensionArchivo = "";
						rutaArchivo = "";
						estadoU = true;
					}catch(Exception e) {
						mensaje = "No se pudo actualizar";
					}
				}else {
					estadoU = false;
					estado = true;
					mensaje = "Trabajo actualizado satisfactoriamente";
				}
									
				if(estadoU) {
					try {
						obj.setIdTraAlu(idTraAlu);
						obj.setIdCuenta(sesion.getIdCuenta());
						obj.setIdTrabajo(idTrabajo);
						obj.setRutaArchivo(rutaArchivo);
						obj.setExtensionArchivo(extensionArchivo);
						obj.setNombreArchivo(nombreArchivoAnterior);
						obj.setIdUsuario(sesion.getIdUsuario());
						dao.create(obj);
						mensaje = "Trabajo actualizado satisfactoriamente";
						estado = true;
					} catch (NotCreated e) {
						e.printStackTrace();
					} 	
				}	

				map.put("mensaje", mensaje);
				map.put("estado",estado);
					
				try(Writer w = response.getWriter()){
					w.write(JSON.toJson(map));
				}
					
			}catch(Exception e) {
				response.sendError(404);
				e.printStackTrace();
			}
		}else {
			response.sendError(403);
		}
		
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object sesion = request.getSession().getAttribute("usuarioSesion");
		
		try {
			
			if(sesion != null) {
				
				Sesion usuarioSesion = (Sesion)sesion;
				
				if(usuarioSesion.getIdPerfil() == AppColegio.TIPO_ALUMNO) {
					
					request.setCharacterEncoding("UTF-8");
					
					response.setContentType("Application/json;charset=UTF-8");
					
					int idTraAlu = Integer.parseInt(request.getParameter("idTraAlu"));
					
					String rutaArchivo = request.getParameter("rutaArchivo");
					
					String nota = request.getParameter("nota");
					
					String message = "";
					
					boolean estado = false;

					if(nota != null && !nota.isEmpty()) {
						
						message = "No puede eliminar ya que el profesor ya asignó una nota";
						
						estado = false;
						
					}else {
						
						Aul_TrabajosAlumno co = new Aul_TrabajosAlumno();
						
						if(!rutaArchivo.isEmpty()) {
							int index = rutaArchivo.indexOf("/");
							String container = rutaArchivo.substring(0,index);
							String path = rutaArchivo.substring(index + 1);
							try {
								Storage.deleteBlob(request, path, container);
							}catch(Exception e) {}
						}
						
						try {
							co.setIdTraAlu(idTraAlu);
							co.setIdUsuario(usuarioSesion.getIdUsuario());
							dao.delete(co);
							estado = true;
							message = "Su trabajo se eliminó";
						}catch (NotDeleted e) {
							e.printStackTrace();
							message = "No se pudo eliminar su trabajo";
						}
																		
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
			e.printStackTrace();
			response.sendError(404);
		}
	}
}
