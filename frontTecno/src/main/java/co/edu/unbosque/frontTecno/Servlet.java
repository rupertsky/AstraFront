package co.edu.unbosque.frontTecno;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.frontTecno.UsuarioJSON;
import co.edu.unbosque.frontTecno.Usuarios;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void validarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		String usua = request.getParameter("txtusuario");
    		String pass = request.getParameter("txtpassword");
    		
    		ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
    		request.setAttribute("lista", lista);    		
    		int respuesta =0;
    		
    		for (Usuarios usuario:lista){
    			if (usuario.getUsuario().equals(usua) && usuario.getPassword().equals(pass)) {
    				request.setAttribute("usuario", usuario);
    				request.getRequestDispatcher("/Principal.jsp").forward(request, response);			    
    				respuesta =1;
			}					
		}
			
		if (respuesta==0) {
			request.getRequestDispatcher("/Inicio.jsp").forward(request, response);
			System.out.println("No se encontraron datos");
		}
			
	} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
	}
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     String accion = request.getParameter("accion");
			
			
	      if (accion.equals("Ingresar")) {
		    this.validarUsuarios(request, response);		
		
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
