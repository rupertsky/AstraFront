package co.edu.unbosque.frontTecno;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.edu.unbosque.frontTecno.UsuarioJSON;
import co.edu.unbosque.frontTecno.Usuarios;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void validarCedula(String id, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{		
		try {
			ArrayList<Usuarios> listac = UsuarioJSON.getJSON();
			for(Usuarios usuarios: listac) {				
				if(usuarios.getCedula_usuario().equals(id)) {
					request.setAttribute("usuarioSeleccionado1", usuarios);					
				}
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
			
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
						ServletException, IOException {
		
		String menu=request.getParameter("menu");		
		String accion=request.getParameter("accion");
		
		switch (menu) {
			case "New_usuarios":
				if(accion.equals("Agregar")) {
					String id=request.getParameter("txtcedula");
				
					this.validarCedula(id, request, response);
					System.out.println("Usuario en uso");
					request.getRequestDispatcher("/New_usuarios.jsp").forward(request, response);
					
				} else if (accion.equals("Agregar")) {
					Usuarios usuario = new Usuarios();
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.postJSON(usuario);
						PrintWriter write = response.getWriter();
						
						if (respuesta==200) {							
							request.getRequestDispatcher("/Inicio.jsp").forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case "Principal":
				request.getRequestDispatcher("/Home.jsp").forward(request, response);
			break;
			
			case "Usuarios":
				if (accion.equals("Listar")) {
					try {
						ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
						request.setAttribute("listaUsuarios", lista);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Agregar")) {
					Usuarios usuario = new Usuarios();
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.postJSON(usuario);
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Actualizar")) {
					Usuarios usuario = new Usuarios();
					usuario.set_id(request.getParameter("txtid"));
					usuario.setCedula_usuario(request.getParameter("txtcedula"));
					usuario.setNombre_usuario(request.getParameter("txtnombre"));
					usuario.setEmail_usuario(request.getParameter("txtemail"));
					usuario.setUsuario(request.getParameter("txtusuario"));
					usuario.setPassword(request.getParameter("txtpassword"));
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.putJSON(usuario, usuario.get_id());
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
						write.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Cargar")) {
					String id= request.getParameter("id");
					try {
						ArrayList<Usuarios> lista1 = UsuarioJSON.getJSON();
						for (Usuarios usuarios:lista1){
							if (usuarios.getCedula_usuario().equals(id)) {
								request.setAttribute("usuarioSeleccionado", usuarios);
								request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
								.forward(request, response);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(accion.equals("Eliminar")) {
					String id= request.getParameter("id");
					int respuesta=0;
					try {
						respuesta = UsuarioJSON.deleteJSON(id);
						PrintWriter write = response.getWriter();
						if (respuesta==200) {
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar")
							.forward(request, response);
						} else {
							write.println("Error: " + respuesta);
						}
					} catch (Exception e) {
							e.printStackTrace();
					}					
				}		
					request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
				break;
				
				case "Clientes":
					if (accion.equals("Listar")) {
						try {
							ArrayList<Clientes> lista = ClienteJSON.getJSON();
							request.setAttribute("listaClientes", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Clientes cliente = new Clientes();
						cliente.setCedula_cliente(request.getParameter("txtcedula"));
						cliente.setNombre_cliente(request.getParameter("txtnombre"));
						cliente.setEmail_cliente(request.getParameter("txtemail"));
						cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
						cliente.setTelefono_cliente(request.getParameter("txttelefono"));
						
						int respuesta=0;
						try {
							respuesta = ClienteJSON.postJSON(cliente);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Clientes cliente = new Clientes();
						cliente.set_id(request.getParameter("txtid"));
						cliente.setCedula_cliente(request.getParameter("txtcedula"));
						cliente.setNombre_cliente(request.getParameter("txtnombre"));
						cliente.setEmail_cliente(request.getParameter("txtemail"));
						cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
						cliente.setTelefono_cliente(request.getParameter("txttelefono"));
						
						int respuesta=0;
						try {
							respuesta = ClienteJSON.putJSON(cliente, cliente.get_id());
							PrintWriter write = response.getWriter();						
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Clientes> lista1 = ClienteJSON.getJSON();
							for (Clientes clientes:lista1){
								if (clientes.getCedula_cliente().equals(id)) {
									request.setAttribute("clienteSeleccionado", clientes);
									request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
									.forward(request, response);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
						String id= request.getParameter("id");
						int respuesta=0;
						try {
							respuesta = ClienteJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								PrintWriter out = response.getWriter();
									
								request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}		
					request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
				break;
				
				case "Proveedores":
					request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
				break;
				
				case "Productos":
					if (accion.equals("Listar")) {
						try {
							ArrayList<Productos> lista = ProductoJSON.getJSON();
							request.setAttribute("listaProductos", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Productos producto = new Productos();
						producto.setCodigo_producto(request.getParameter("txtcodigo"));
						producto.setNombre_producto(request.getParameter("txtnombre"));
						producto.setNit_proveedor(request.getParameter("txtnit"));
						producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecioproducto")));
						producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
						producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecioventa")));
						System.out.println(producto.getCodigo_producto());
						System.out.println(producto.getNombre_producto());
						System.out.println(producto.getNit_proveedor());
						System.out.println(producto.getPrecio_compra());
						System.out.println(producto.getIva_compra());
						System.out.println(producto.getPrecio_venta());
						int respuesta=0;
						try {
							respuesta = ProductoJSON.postJSON(producto);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								System.out.println("No esta agregando");
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							System.out.println("causa un error");
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Productos producto = new Productos();
						producto.set_id(request.getParameter("txtid"));
						producto.setCodigo_producto(request.getParameter("txtcodigo"));
						producto.setNombre_producto(request.getParameter("txtnombre"));
						producto.setNit_proveedor(request.getParameter("txtnit"));
						producto.setPrecio_compra(Double.parseDouble(request.getParameter("txtprecioproducto")));
						producto.setIva_compra(Double.parseDouble(request.getParameter("txtiva")));
						producto.setPrecio_venta(Double.parseDouble(request.getParameter("txtprecioventa")));
						int respuesta=0;
						try {
							respuesta = ProductoJSON.putJSON(producto, producto.get_id());
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Productos> lista1 = ProductoJSON.getJSON();
							for (Productos productos:lista1){
								if (productos.getCodigo_producto().equals(id)) {
									request.setAttribute("productoSeleccionado", productos);
									request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
									.forward(request, response);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
						String id= request.getParameter("id");
						int respuesta=0;
						try {
							respuesta = ProductoJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								System.out.println("Se elimino");
								request.getRequestDispatcher("Controlador?menu=Productos&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}
						
					request.getRequestDispatcher("/Productos.jsp").forward(request, response);
				break;
				
				case "Ventas":
					request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
				break;
				
				case "Reportes":
					request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
				break;
				

		}
	}
}

