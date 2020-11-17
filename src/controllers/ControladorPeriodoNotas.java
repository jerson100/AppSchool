package controllers;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import constantes.AppColegio;
import dao.Aul_PeriodoNotasDao;
import dao.Aul_RegistroNotasDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import interfaces.IAul_PeriodoNotas;
import interfaces.ICrud;
import models.Aul_Periodo;
import models.Aul_PeriodoNotas;
import models.Aul_RegistroNotas;
import models.Sesion;

/**
 * Servlet implementation class ControladorPeriodoNotas
 */
@WebServlet(name="/ControladorPeriodoNotas",urlPatterns= {"/periodoNotas"})
public class ControladorPeriodoNotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ICrud<Aul_PeriodoNotas> dao;
	private Gson JSON;
       
    @SuppressWarnings("unchecked")
	public ControladorPeriodoNotas() {
        super();
        this.dao = DaoManager.getDaoManager(EDaoManager.AULPERIODONOTAS);
        this.JSON = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sesion us = (Sesion)request.getSession().getAttribute("usuarioSesion");
		Map<String, Object> result = new HashMap<String, Object>();
		if(us != null) {
			if(us.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || 
					   us.getIdPerfil() == AppColegio.TIPO_DOCENTE) {
				try {
					int idCiclo = Integer.parseInt(request.getParameter("idCiclo"));
					int idSecCur = Integer.parseInt(request.getParameter("idSecCurPro"));
					IAul_PeriodoNotas periodoNotasDao = (IAul_PeriodoNotas)dao;
					try {
						Map<String, List<Aul_PeriodoNotas>> list = periodoNotasDao.all(idCiclo, idSecCur);
						result.put("data", list);
					}catch(SQLException e) {
						e.printStackTrace();
						response.setStatus(500);
						result.put("msg", "Ocurrió un error en el servidor, comuníquese con el administrador");
					}				
				}catch(Exception e) {
					response.setStatus(400);
					result.put("msg", "El idCiclo y el idSecCur deben ser números enteros");
				}
			}else {
				response.setStatus(403);
				result.put("msg", "Usted no puede ver este recurso");
			}
		}else {
			response.setStatus(401);
			result.put("msg", "Inicie sesión");
		}
		try(Writer w = response.getWriter()){
			w.write(JSON.toJson(result));
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
