package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class Prueba
 */
@WebServlet(name="ControladorPrueba",urlPatterns={"/prueba"})
public class Prueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Prueba() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRequestDispatcher("WEB-INF/pages/prueba.jsp").forward(request,response);
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("v"));
		System.out.println("got the request");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        while(s!=null){
            System.out.println("read line is " + s);
            Gson json = new Gson();
            P pp = json.fromJson(s, P.class);
            System.out.println(pp);
            s = br.readLine();
            
        }
        br.close();
	}

	class P{
		
		String name;
		int id;
		List<Integer> nums;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public List<Integer> getNums() {
			return nums;
		}
		public void setNums(List<Integer> nums) {
			this.nums = nums;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "P [name=" + name + ", id=" + id + ", nums=" + nums + "]";
		}
		
	}
	
}
