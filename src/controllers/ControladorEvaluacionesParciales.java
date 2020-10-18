package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.manager.DaoManager;
import enumerados.EDaoManager;
import interfaces.ICrud;
import models.Aul_Seccion;
import models.Sesion;

@WebServlet(name="/ControladorEvaluacionesParciales",urlPatterns= {"/zonaDocente/registrarNotas"})
public class ControladorEvaluacionesParciales extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Seccion> dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		dao = DaoManager.getDaoManager(EDaoManager.AULSECCION);
	}
    
    public ControladorEvaluacionesParciales() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Aul_Seccion> secciones = null;
		
		HttpSession session = request.getSession();
		
		Sesion us_session = (Sesion)session.getAttribute("usuarioSesion");
		
		// tenemos que verificar que el usuario es docente, eso lo haremos con un filter
		
		if(us_session != null) { 
			
			try {
				
				secciones = dao.all(us_session.getIdCuenta());
				System.out.println(secciones);
			} catch (Exception e) {
				
				secciones = new ArrayList<Aul_Seccion>();
				
			}
			
			request.setAttribute("secciones", secciones);
			
			request.getRequestDispatcher("../WEB-INF/pages/zonaDocente/registrarEvaluaciones.jsp").forward(request, response);
			
		}else {
			
			response.sendError(401);
			
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
