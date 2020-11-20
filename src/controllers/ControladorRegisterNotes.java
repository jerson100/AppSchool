package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import constantes.AppColegio;
import dao.Aul_RegistroNotasDao;
import dao.manager.DaoManager;
import enumerados.EDaoManager;
import exceptions.NotCreated;
import interfaces.ICrud;
import models.Aul_RegistroNotas;
import models.Sesion;

@WebServlet(name = "/ControladorRegisterNotes", urlPatterns = { "/alumno/notas" })
public class ControladorRegisterNotes extends HttpServlet {
	private ICrud<Aul_RegistroNotas> dao;
	private static final long serialVersionUID = 1L;
	private Gson JSON;

	@SuppressWarnings("unchecked")
	public ControladorRegisterNotes() {
		super();
		this.dao = DaoManager.getDaoManager(EDaoManager.AULREGISTRONOTAS);
		this.JSON = new Gson();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Sesion us = (Sesion) request.getSession().getAttribute("usuarioSesion");
		String action = request.getParameter("action");
		Map<String, Object> result = new HashMap<String, Object>();
		if (action != null) {
			if (us != null) {
				//if (us.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || us.getIdPerfil() == AppColegio.TIPO_DOCENTE) {
					switch (action) {
					case "listar":
						listar(request, response);
						return;
					case "listarMapNotes":
						listarMapNotes(us, request, response);
						return;
					default:
						response.setStatus(403);
						result.put("msg", "Usted no puede ver este recurso");
						return;
					}
				/*} else {
					response.setStatus(403);
					result.put("msg", "Usted no puede ver este recurso");
				}*/
			} else {
				response.setStatus(401);
				result.put("msg", "Inicie sesión");
			}
		} else {
			response.setStatus(400);
			result.put("msg", "action is required");
		}
		try (Writer w = response.getWriter()) {
			w.write(JSON.toJson(result));
		}
	}

	private void listarMapNotes(Sesion us, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int idCiclo = Integer.parseInt(request.getParameter("idCiclo"));
			int idSecCur = Integer.parseInt(request.getParameter("idSecCurPro"));
			Aul_RegistroNotasDao registroDao = (Aul_RegistroNotasDao) dao;
			try {
				Map<String, List<Aul_RegistroNotas>> list =  
						us.getIdPerfil() == AppColegio.TIPO_ALUMNO 
						? 
							registroDao.allNotasStudent(idCiclo, us.getIdCuenta())
						: 
							registroDao.all(idCiclo, idSecCur);
				result.put("data", list);
				//System.out.println(list);
			} catch (SQLException e) {
				// e.printStackTrace();
				response.setStatus(500);
				result.put("msg", "Ocurrió un error en el servidor, comuníquese con el administrador");
			}
		} catch (Exception e) {
			response.setStatus(400);
			result.put("msg", "El idCiclo y el idSecCur deben ser números enteros");
		}
		try (Writer w = response.getWriter()) {
			w.write(JSON.toJson(result));
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int idPeriodoNotas = Integer.parseInt(request.getParameter("idPeriodoNotas"));
			int idSecCurPro = Integer.parseInt(request.getParameter("idSecCurPro"));
			Aul_RegistroNotasDao registroDao = (Aul_RegistroNotasDao) dao;
			try {
				List<Aul_RegistroNotas> list = registroDao.allList(idSecCurPro, idPeriodoNotas);
				result.put("data", list);
			} catch (SQLException e) {
				// e.printStackTrace();
				response.setStatus(500);
				result.put("msg", "Ocurrió un error en el servidor, comuníquese con el administrador");
			}
		} catch (Exception e) {
			response.setStatus(400);
			result.put("msg", "El idPeriodoNotas y el idSecCurPro deben ser números enteros");
		}
		try (Writer w = response.getWriter()) {
			w.write(JSON.toJson(result));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Sesion us = (Sesion) request.getSession().getAttribute("usuarioSesion");
		
		if(us != null) {
			if (us.getIdPerfil() == AppColegio.TIPO_ADMINISTRADOR || us.getIdPerfil() == AppColegio.TIPO_DOCENTE) {
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String json = "";
				if (br != null) json = br.readLine();
				Aul_RegistroNotas[] obj = JSON.fromJson(json, Aul_RegistroNotas[].class);
				/*for(Aul_RegistroNotas a : obj) {
					System.out.println(a);
				}*/
				HashMap<String, Object> resp = new HashMap<String, Object>();
				try {
					((Aul_RegistroNotasDao)dao).create(obj, us.getIdUsuario());
					response.setStatus(200);
					resp.put("message","Se registró satisfactoriamente");
					try(Writer w = response.getWriter()){
						w.write(JSON.toJson(resp));
					}
				} catch (NotCreated e) {
					resp.put("message","No se pudo agregar las notas, inténtelo nuevamente");
					response.setStatus(400);
					try(Writer w = response.getWriter()){
						w.write(JSON.toJson(resp));
					}
				} catch (SQLException e) {
					response.setStatus(500);
				}
			}else {
				response.setStatus(403);
			}
		}else {
			response.setStatus(401);
		}
		
		/*System.out.println(obj);*/
		//JsonObject wholedata = new JsonObject(json);
	}

}
