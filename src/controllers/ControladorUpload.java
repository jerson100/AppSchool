package controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microsoft.azure.storage.StorageException;

import models.Sesion;
import utilidades.Storage;

@WebServlet(name="/ControladorUpload",urlPatterns= {"/upload"})
public class ControladorUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControladorUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		Sesion us = ((Sesion)sesion.getAttribute("usuarioSesion"));
		
		if(us != null) {
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			System.out.println(name);
			int index = name.indexOf("/");		
			String container = name.substring(0,index);
			String path = name.substring(index+1);
			try {
				Storage.download(response, request, path, container, title);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
				//response.sendError(404);
			} catch (StorageException e) {
				e.printStackTrace();
				//response.sendError(404);
			} catch (URISyntaxException e) {
				e.printStackTrace();
				//response.sendError(404);
			} catch (IOException e) {
				e.printStackTrace();
				//response.sendError(404);
			}
		}else {
			response.sendRedirect("");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
