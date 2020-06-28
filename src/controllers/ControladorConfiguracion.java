package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constantes.AppColegio;
import models.Sesion;

@WebServlet(name="/ControladorConfiguracion",urlPatterns={"/mantenimiento"})
public class ControladorConfiguracion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ControladorConfiguracion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"Usted no tiene permisos para acceder a esta url.");
		
		/*
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		if( us == null) {
			
			response.sendRedirect("login");
			
		}else {
			
			if(AppColegio.TIPO_ADMINISTRADOR == us.getIdPerfil()) {
				
				request.getRequestDispatcher("WEB-INF/pages/mantenimiento.jsp").forward(request, response);
				
			}else {
				
				response.sendError(HttpServletResponse.SC_FORBIDDEN, 
					"Usted no tiene los privilegios suficientes para esta página");
				
			}
			
		}*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
