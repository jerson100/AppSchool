package controllers;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.manager.DaoManager;
import enumerados.EDaoManager;
import interfaces.ICrud;
import models.Aul_SeccionGradoNivel;
import models.Sesion;

@WebServlet(name="/ControladorAula",urlPatterns= {"/aula"})
public class ControladorAula extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_SeccionGradoNivel> seccionGNDao;
	private static final Gson JSON = new Gson();
       
	@SuppressWarnings({"unchecked" })
	@Override
	public void init() throws ServletException {
		this.seccionGNDao =  DaoManager.getDaoManager(EDaoManager.AULSECCIONGRADONIVEL);
	}
	
    public ControladorAula() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sesion sesion = (Sesion) request.getSession().getAttribute("usuarioSession");
		
		if(sesion != null) {
			
			response.setContentType("Application/json;charset=utf-8");
			
			boolean status = false;
			
			String message = "";
			
			List<Aul_SeccionGradoNivel> list = null;
			
			try {
				
				list = seccionGNDao.all();
				
				message = "ok";
				
				status = true;
				
			} catch (Exception e) {
				
				message = e.getMessage();
				
			}
			
			Map<String,Object> map = new HashMap<>();
			
			map.put("mensaje", message);
			
			map.put("estado", status);
			
			map.put("aulas",list);
			
			try(Writer w = response.getWriter()){
				
				w.write(JSON.toJson(map));
				
			}
			
		}else {
			
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
