package controllers;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
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

import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotAll;
import interfaces.ICrud;
import models.Aul_Ciclo;
import models.Sesion;

@WebServlet(name="/ControladorCiclo", urlPatterns={"/aulCiclo"})
public class ControladorCiclo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ICrud<Aul_Ciclo> dao;
	private static Gson JSON;
       
    @SuppressWarnings("unchecked")
	public ControladorCiclo() {
        super();
        this.dao = DaoManager.getDaoManager(EDaoManager.AULCICLO);	
		JSON = new Gson();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		if(us!=null) {
			Map<String, Object> rspt = new HashMap<String, Object>();
			try{
				List<Aul_Ciclo> ciclos = dao.all();
				rspt.put("data", ciclos);
				try(Writer w = response.getWriter()){
					w.write(JSON.toJson(ciclos));
				}
			}catch(NotAll e) {
				rspt.put("data", new ArrayList<Aul_Ciclo>());
			}catch(Exception e) {
				response.setStatus(500);
			}
		}else {
			response.setStatus(401);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
