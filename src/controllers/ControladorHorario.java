package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constantes.AppColegio;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import interfaces.ICrud;
import models.Aul_Horario;
import models.Sesion;

@WebServlet(name="/ControladorHorario",urlPatterns= {"/horario"})
public class ControladorHorario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Horario> horarioDao;
       
    public ControladorHorario() {
        super();
    }

    @SuppressWarnings("unchecked")
	@Override
    public void init() throws ServletException {
    	this.horarioDao = DaoManager.getDaoManager(EDaoManager.AULHORARIO);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Object sesion = request.getSession().getAttribute("usuarioSesion");
		
		if(sesion != null) {
			
			Sesion user = (Sesion)sesion;
			
			if(user.getIdPerfil() != AppColegio.TIPO_ADMINISTRADOR) {
				
				List<Aul_Horario> list = null;
				
				boolean estado = false;
				
				try {
					
					list = horarioDao.all(user.getIdCuenta());
					
					Map<Integer, List<Aul_Horario>> map = new HashMap<Integer, List<Aul_Horario>>();
				
					map.put(1, new ArrayList<>());
					map.put(2, new ArrayList<>());
					map.put(3, new ArrayList<>());
					map.put(4, new ArrayList<>());
					map.put(5, new ArrayList<>());
					
					java.util.ListIterator<Aul_Horario> it = list.listIterator();
					
					while(it.hasNext()) {
						
						Aul_Horario aux = it.next();
						
						List<Aul_Horario> lis = map.get(aux.getIdSemana());
					
						lis.add(aux);
							
					}
					
					estado = true;
					
					request.setAttribute("horario", map);
					
				} catch (NotAll e) {
				}
				
				request.setAttribute("estado", estado);
				
				request.getRequestDispatcher("WEB-INF/pages/horario.jsp").forward(request, response);
				
			}else {
				
				response.sendRedirect("login");
				
			}
			
		}else {
			
			response.sendRedirect("login");
			
		}
		
	}
	
}
