package controllers;

import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.microsoft.azure.storage.StorageException;

import constantes.AppColegio;
import dao.Aul_TrabajoDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import exceptions.NotCreated;
import exceptions.NotDeleted;
import interfaces.ICrud;
import models.Aul_Trabajo;
import models.Sesion;
import utilidades.Cifrado;
import utilidades.JeVlidate;
import utilidades.Storage;

@MultipartConfig
@WebServlet(name="/ControladorTrabajo",urlPatterns= {"/trabajo"})
public class ControladorTrabajo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Trabajo> dao;
	private Gson JSON;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		dao = DaoManager.getDaoManager(EDaoManager.AULTRABAJO);
		JSON = new Gson();
	}
	
    public ControladorTrabajo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Sesion sesion = (Sesion) request.getSession().getAttribute("usuarioSesion");
		
		String mensaje = "";
		boolean estado = false;
		
		if(sesion != null) {
			
			response.setContentType("Application/json;charset=utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			List<Aul_Trabajo> listTrabajos = null;
			try {
				int idSecCur = Integer.parseInt(request.getParameter("idSecCurPro"));
				int idCuenta = sesion.getIdCuenta();				
				listTrabajos = ((Aul_TrabajoDao)dao).all(idSecCur,idCuenta);
				estado = true;
				mensaje = "ok!";
			} catch (NotAll e) {
				mensaje = e.getMessage();
			} catch(Exception e) {e.printStackTrace();
				mensaje = "No se pudo llevar a cabo la operación";
			}
			map.put("mensaje", mensaje);
			map.put("estado",estado);
			map.put("trabajos",listTrabajos);
			
			try(Writer w = response.getWriter()){
				w.write(JSON.toJson(map));
			}
			
		}else {
			
			response.sendError(401);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Sesion sesion = (Sesion) request.getSession().getAttribute("usuarioSesion");
		
		if(sesion != null) {
			
			String action = request.getParameter("action");
		
			if(action != null) {
				
				switch(action) {
				
					case "agregarTrabajo":
						
						agregarTrabajo(sesion, request,response);
						
						break;
						
					case "actualizarTrabajo":
						
						actualizarTrabajo(sesion, request,response);
						
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

	private void actualizarTrabajo(Sesion sesion, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Map<String, Object> map = new HashMap<String, Object>();
		String mensaje = "";
		boolean estado = false;
		
		try {
			
			response.setContentType("Application/json;charset=UTF-8");
			
			Part file = request.getPart("file");
			int idTrabajo = Integer.parseInt(request.getParameter("idTrabajo"));
			int idSeCur= Integer.parseInt(request.getParameter("idSecCurPro"));
			String codTrabajo = request.getParameter("codTrabajo");
			String descTrabajo = request.getParameter("descTrabajo");
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			String nombreArchivoAnterior = request.getParameter("nombreArchivoAnterior");
			
			String extensionArchivo = JeVlidate.obtenerExtension(nombreArchivoAnterior);

			String rutaArchivo = request.getParameter("rutaArchivo");
			String isFlagLimite = request.getParameter("isFlagLimite");
			String diasLimite = request.getParameter("diasLimite");
			String replicarsolo = request.getParameter("replicarsolo"),
				   replicartodos = request.getParameter("replicartodos"),
				   nuevoNombreArchivo = request.getParameter("nuevoNombreArchivo");
			
			Aul_Trabajo obj = new Aul_Trabajo();
			
				boolean estadoU = false;
				System.out.println(file.getSubmittedFileName().isEmpty());
				if(!file.getSubmittedFileName().isEmpty()) {
					try {
						
						String newPath = this.generarNombreArchivo(file.getSubmittedFileName(), new Date().getTime(), JeVlidate.generarNumeroAleatorio(50, 99999999), JeVlidate.obtenerExtension(file.getSubmittedFileName()));
						
						//insertamos el nuevo
						Storage.addAndUpdateBlob(request, "PROFESORES", newPath, file.getContentType(), "2020", file.getInputStream(), file.getSize());
						
						if(!rutaArchivo.isEmpty() && (replicartodos!=null && replicartodos.equals("on"))) {
							try {
								//eliminamos el anterior
								Storage.deleteBlob(request, rutaArchivo.replaceAll("2020/", ""), "2020");
								nombreArchivoAnterior = file.getSubmittedFileName();
								extensionArchivo = JeVlidate.obtenerExtension(file.getSubmittedFileName());
								rutaArchivo = "2020/PROFESORES/"+newPath;
								estadoU = true;
							}catch(Exception e) {
								mensaje = "No se pudo actualizar";
							}
						}else {
							nombreArchivoAnterior = file.getSubmittedFileName();
							extensionArchivo = JeVlidate.obtenerExtension(file.getSubmittedFileName());
							rutaArchivo = "2020/PROFESORES/"+newPath;
							estadoU = true;
						}
						
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
					estadoU = true;
				}
								
				if(estadoU) {
					
					obj.setIdTrabajo(idTrabajo);
					obj.setIdSecCur(idSeCur);
					obj.setCodTrabajo(codTrabajo);
					obj.setDescTrabajo(descTrabajo);
					obj.setFechaIni(fechaIni);
					obj.setFechaFin(fechaFin);
					obj.setRutaArchivo(rutaArchivo);
					obj.setExtensionArchivo(extensionArchivo);
					obj.setNombreArchivo(nombreArchivoAnterior);
					obj.setIdUsuario(sesion.getIdUsuario()); 
					obj.setFlagLimite(isFlagLimite!=null && isFlagLimite.equals("on"));
					obj.setDiasLimite(diasLimite!=null && !diasLimite.isEmpty()?Short.parseShort(diasLimite):0);
					obj.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
					obj.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
					
					try {
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
			
		}
	}

	private void agregarTrabajo(Sesion sesion, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String mensaje = "";
		boolean estado = false;
		
		try {
			
			response.setContentType("Application/json;charset=UTF-8");
			
			Part file = request.getPart("file");
			int idSeCur= Integer.parseInt(request.getParameter("idSecCurPro"));
			String descTrabajo = request.getParameter("descTrabajo");
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			String extensionArchivo = "";
			String nombreArchivo = "";
			String nombreArchivoGenerado = "";
			String rutaArchivo = "";
			String isFlagLimite = request.getParameter("isFlagLimite");
			String diasLimite = request.getParameter("diasLimite");
			String replicarsolo = request.getParameter("replicarsolo"),
				   replicartodos = request.getParameter("replicartodos");
			
			Aul_Trabajo obj = new Aul_Trabajo();
			
			try {
				
				if(file != null && !file.getSubmittedFileName().isEmpty()) {
					
					extensionArchivo = JeVlidate.obtenerExtension(file.getSubmittedFileName());
					nombreArchivo = file.getSubmittedFileName();
					nombreArchivoGenerado = generarNombreArchivo(nombreArchivo,new Date().getTime(),JeVlidate.generarNumeroAleatorio(50, 9999999),extensionArchivo);
					rutaArchivo = "2020/PROFESORES/"+nombreArchivoGenerado;
					
					Storage.addAndUpdateBlob(request, "PROFESORES", nombreArchivoGenerado, file.getContentType(), "2020", file.getInputStream(), file.getSize());
						
				}
				
				obj.setIdSecCur(idSeCur);
				obj.setDescTrabajo(descTrabajo);
				obj.setFechaIni(fechaIni);
				obj.setFechaFin(fechaFin);
				obj.setRutaArchivo(rutaArchivo);
				obj.setExtensionArchivo(extensionArchivo);
				obj.setNombreArchivo(nombreArchivo);
				obj.setIdUsuario(sesion.getIdUsuario()); 
				obj.setFlagLimite(isFlagLimite!=null && isFlagLimite.equals("on"));
				obj.setDiasLimite(diasLimite!=null && !diasLimite.trim().equals("")?Short.parseShort(diasLimite):0);
				obj.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
				obj.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));

				dao.create(obj);
				
				mensaje = "Trabajo creado satisfactoriamente";
				
				estado = true;
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				mensaje = "No se pudo crear el trabajo";
				
				response.setStatus(404);
				
			}
			
		}catch(Exception e) {
			
			response.setStatus(404);
			
		}
		
		map.put("mensaje", mensaje);
		map.put("estado",estado);
		
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(map));
		}
		
	}
	
	private String generarNombreArchivo(String nombreArchivo, long time, int generarNumeroAleatorio, String extension) {
		return Cifrado.cifrar(nombreArchivo+time+generarNumeroAleatorio) + extension;
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
					
					int idTrabajo = Integer.parseInt(request.getParameter("idTrabajo"));
					
					String rutaArchivo = request.getParameter("rutaArchivo");
					
					String replicarsolo = request.getParameter("replicarsolo");
					
					String replicartodos = request.getParameter("replicartodos");
					
					String message = "";
					
					boolean estado = false;
					
					Aul_Trabajo co = new Aul_Trabajo();
					
					co.setIdTrabajo(idTrabajo);
					
					co.setIdUsuario(usuarioSesion.getIdUsuario());
					
					co.setReplicar_solo(replicarsolo!=null && replicarsolo.equals("on"));
					
					co.setReplicar_todos(replicartodos!=null && replicartodos.equals("on"));
					
					boolean state = true;
					
					if(!rutaArchivo.isEmpty()) {
						int index = rutaArchivo.indexOf("/");
						String container = rutaArchivo.substring(0,index);
						String path = rutaArchivo.substring(index + 1);
						try {
							if(co.isReplicar_todos()) {
								Storage.deleteBlob(request, path, container);
								state = true;
							}
						}catch(Exception e) {
							message  = "No se pudo eliminar";
							state = false;
						}
					}
					if(state) {
						try {
							dao.delete(co);
							estado = true;
							message = "Trabajo eliminado satisfactoriamente";
						}catch (NotDeleted e) {
							message = "No se pudo eliminar el trabajo";
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
