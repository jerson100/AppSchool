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

import constantes.AppColegio;
import dao.Aul_CursoDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import interfaces.IAul_Curso;
import interfaces.ICrud;
import models.Aul_Ciclo;
import models.Aul_Curso;
import models.Aul_Seccion;
import models.Sesion;

@WebServlet(name="/ControladorMisNotas",urlPatterns= {"/zonaEstudiante/misNotas"})
public class ControladorMisNotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Curso> daoCurso;
	private ICrud<Aul_Ciclo> daoCiclo;
    
	public ControladorMisNotas() {
        super();
        
    }

    @SuppressWarnings("unchecked")
	@Override
    public void init() throws ServletException {
    	daoCurso = DaoManager.getDaoManager(EDaoManager.AULCURSO);
		daoCiclo = DaoManager.getDaoManager(EDaoManager.AULCICLO);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Sesion us_session = (Sesion)session.getAttribute("usuarioSesion");
		
		// tenemos que verificar que el usuario es docente, eso lo haremos con un filter
		
		if(us_session != null) { 
			
			if (us_session.getIdPerfil() == AppColegio.TIPO_ALUMNO) {
				
				List<Aul_Curso> cursos = null;
				List<Aul_Ciclo> ciclos = null;
				
				try {
					cursos = ((IAul_Curso)daoCurso).all(us_session.getIdPerfil(), us_session.getIdCuenta(), 0);
					ciclos = daoCiclo.all();
					System.out.println(cursos);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String grado = us_session.getGrado();
				
				String tutor = us_session.getTutor();
				
				request.setAttribute("grado", grado);
				request.setAttribute("tutor", tutor);
				request.setAttribute("cursos", cursos);
				request.setAttribute("ciclos", ciclos);
				
				request.getRequestDispatcher("../WEB-INF/pages/zonaEstudiante/misNotas.jsp").forward(request, response);
				
			}else {
				response.sendError(403);
			}
					
		}else {
			
			response.sendError(401);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
