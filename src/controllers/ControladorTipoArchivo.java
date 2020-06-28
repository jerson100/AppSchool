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
import javax.servlet.http.Part;

import com.google.gson.Gson;

import constantes.AppColegio;
import dao.Glo_TipoArchivoDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import models.Glo_TipoArchivo;
import models.Sesion;
import utilidades.JeVlidate;

@WebServlet(name="/ControladorTipoArchivo",urlPatterns= {"/tipoArchivo"})
@MultipartConfig
public class ControladorTipoArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Gson JSON;
	private Glo_TipoArchivoDao dao;
	
    public ControladorTipoArchivo() {
        super();
    }

    	@Override
    	public void init() throws ServletException {
    		this.JSON = new Gson();
    		this.dao = (Glo_TipoArchivoDao)DaoManager.getDaoManager(EDaoManager.GLOTIPOARCHIVO);
    	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		if(us != null) {
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(us.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || 
			   us.getIdPerfil() == AppColegio.TIPO_DOCENTE ||
			   us.getIdPerfil() == AppColegio.TIPO_ALUMNO) {
				
				if(action != null) {
					
					switch(action) {
						case "agregarTipoArchivo":
							break;
						case "actualizarTipoArchivo":
							break;
						case "validar":
							validarArchivo(request,response);
							break;
						default:
								response.sendError(404);
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

	private void validarArchivo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("Application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String mensaje;
		Part file = request.getPart("file");
		
		try {
			
			Glo_TipoArchivo tipoA = new Glo_TipoArchivo();
			System.out.println(file.getName());
			System.out.println(file.getSubmittedFileName());
			System.out.println(file.getSize());
			System.out.println(file.getContentType());
			System.out.println("extensión: "+JeVlidate.obtenerExtension(file.getSubmittedFileName()));
			
			tipoA.setExtension(JeVlidate.obtenerExtension(file.getSubmittedFileName()));
			tipoA.setTamagnoMax(file.getSize());
			System.out.println(dao);
			mensaje = dao.validarExtension(tipoA);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			mensaje  = "El extensión no es válida";
			
		}
		
		map.put("estado", mensaje.isEmpty());
		map.put("mensaje", mensaje);
		
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(map));
		}
		
	}

}
