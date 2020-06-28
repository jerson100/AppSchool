package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="/ControladorInicio",urlPatterns={"/inicio"})
public class ControladorInicio extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public ControladorInicio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		if(sesion.getAttribute("usuarioSesion") == null) {
			
			response.sendRedirect("login");
			
		}else {
			
			request.getRequestDispatcher("WEB-INF/pages/inicio.jsp").forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
