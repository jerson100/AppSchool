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
import models.Glo_TipoSexo;

@WebServlet(name="/ControladorSexo",urlPatterns= {"/sexo"})
public class ControladorSexo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static ICrud<Glo_TipoSexo> daoSexo;
    private static Gson JSON;
	
    public ControladorSexo() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void init() throws ServletException {
    	ControladorSexo.daoSexo = DaoManager.getDaoManager(EDaoManager.GLOTIPOSEXO);
    	JSON = new Gson();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("Application/json;charset=utf-8");
		
		boolean status = false;
		
		String message = "";
		
		List<Glo_TipoSexo> list = null;
		
		try {
			
			list = daoSexo.all();
			
			message = "ok";
			
			status = true;
			
		} catch (Exception e) {
			
			message = e.getMessage();
			
		}
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("mensaje", message);
		
		map.put("estado", status);
		
		map.put("sexos",list);
		
		try(Writer w = response.getWriter()){
			
			w.write(JSON.toJson(map));
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
