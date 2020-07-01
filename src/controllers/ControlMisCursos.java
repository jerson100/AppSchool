package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
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

import dao.Aul_CursoDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import interfaces.ICrud;
import models.Aul_Contenido;
import models.Aul_Curso;
import models.Aul_Seccion;
import models.Sesion;

@WebServlet(name="/ControlMisCursos",urlPatterns={"/cursos"})
public class ControlMisCursos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Gson JSON;
	private static ICrud<Aul_Curso> aulCursoDao;
	private static ICrud<Aul_Contenido> aulcontenidoDao;
	private static ICrud<Aul_Seccion> aulSeccionDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		JSON = new Gson();
		aulCursoDao = DaoManager.getDaoManager(EDaoManager.AULCURSO);
		aulcontenidoDao = DaoManager.getDaoManager(EDaoManager.AULCONTENIDO);
		aulSeccionDao = DaoManager.getDaoManager(EDaoManager.AULSECCION);
	}
       
    public ControlMisCursos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		String action = request.getParameter("action");
				
		if(action==null) {
					
			if(us == null) {
						
				response.sendRedirect("login");
						
			}else {
				
				mostrarMisSecciones(us,request,response);
					
			}
				
		}else {
			
			if(us == null) {
				
				response.sendError(401);
				
			}else {
				
				switch (action) {
				
					case "listar":
							listar(request,response);
								break;
					case "getCursos":
							getCursos(us,request,response);
								break;
					case "agregarCurso":
							agregarCurso(us,request,response);
							break;
						default:
							response.sendError(404,"El recurso no está disponible");
							break;
				}	
				
			}
					
		}
			
	}

	private void agregarCurso(Sesion us, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("Application/json;charset=utf-8");
	}

	private void getCursos(Sesion us,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("Application/json;charset=UTF-8");
		
		int idSecGraNiv = Integer.parseInt(request.getParameter("idSecGraNiv"));
		
		List<Aul_Curso> listaCurso = null;
		
		String message = "";
		
		boolean estado = false;
		
		try {
			
			listaCurso = ((Aul_CursoDao)aulCursoDao).all(us.getIdTipoCuenta(), us.getIdCuenta(), idSecGraNiv);
			
			message = "ok!";
			
			estado = true;
			
		} catch (NotAll e) {
			
			message = e.getMessage();
			
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("mensaje", message);
		
		map.put("estado", estado);
		
		map.put("cursos", listaCurso);
		
		try(Writer w = response.getWriter()){
			
			w.write(JSON.toJson(map));
			
		}
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int idSecCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
		
		response.setContentType("Application/json;charset=UTF-8");
		
		List<Aul_Contenido> listaContenido;
		
		String mensaje = "";
		
		boolean estado = false;
		
		try {
			
			listaContenido = aulcontenidoDao.all(idSecCurPro);
			
			estado = true;
			
			mensaje = "Ok";
			
		} catch (NotAll e) {
			
			listaContenido = new ArrayList<>();
			
			mensaje = e.getMessage();
			
		}
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("mensaje", mensaje);
		
		map.put("estado", estado);
		
		map.put("contenidos", listaContenido);
		
		try(Writer w = response.getWriter()){
			
			w.write(JSON.toJson(map));
			
		}
		
	}

	private void mostrarMisSecciones(Sesion us,HttpServletRequest request, HttpServletResponse response) 
																throws ServletException, IOException {
		
			String message = "";
			
			List<Aul_Curso> listaCursos = null;
			
			List<Aul_Seccion> listaSeccion = null;
			
			try {
				
				listaSeccion = aulSeccionDao.all(us.getIdCuenta());
				
				message = "ok!";
				
			} catch (NotAll e) {
				
				message = e.getMessage();
				
			}
			
			try {
				
				listaCursos = ((Aul_CursoDao)aulCursoDao).all(us.getIdPerfil(), us.getIdCuenta(), 0);
				
			} catch (NotAll e) {
				
				message = e.getMessage();
				
			}
			
			request.setAttribute("listaSeccion", listaSeccion);
			
			request.setAttribute("listaCursos", listaCursos);
			
			request.setAttribute("mensaje", message);
			
			request.setAttribute("tipoPerfil", us.getIdPerfil());
			
			String grado = us.getGrado();
			
			String tutor = us.getTutor();
			
			request.setAttribute("grado", grado);
			
			request.setAttribute("tutor", tutor);
			
			request.getRequestDispatcher("WEB-INF/pages/cursos.jsp").forward(request, response);
		
		
		
	}

}
