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

/**
 * Servlet implementation class ControladorMantenimientoNoticias
 */

//zonaAdmin/mantenimientoNoticias
@WebServlet(name="/ControladorMantenimientoNoticias",urlPatterns= {"/mantenimientoNoticias"})
public class ControladorMantenimientoNoticias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorMantenimientoNoticias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Sesion us_session = (Sesion)session.getAttribute("usuarioSesion");
		
		// tenemos que verificar que el usuario es docente, eso lo haremos con un filter
		
		if(us_session != null) { 
			
			if (us_session.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR) {
			
				request.getRequestDispatcher("/WEB-INF/pages/zonaAdmin/news.jsp").forward(request, response);
				
			}else {
			
				response.sendError(403, "No tiene privilegios para poder acceder a este módulo");
				
			}
			
		}else {
			
			response.sendRedirect("login");
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
