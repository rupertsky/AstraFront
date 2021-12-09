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

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//********************Variables Generales**************************************************
	double subtotal, totalapagar=0;
	double precio=0, iva=0, acusubtotal=0, subtotaliva = 0, totalventas=0, totaltotal=0;
	int cantidad=0, item = 0;
	long numfac = 0;
	String descripcion, cedulaCliente, codProducto, ciudad, fecha;
	
	Usuarios usuarios1 = new Usuarios();
	List <Detalle_Venta> listaVentas = new ArrayList<>();
	List <Ventas> listaTotal = new ArrayList<>();
	Detalle_Venta detalle_venta = new Detalle_Venta();	
	Consolidado consolidado = new Consolidado();
	//*****************************************************************************************
			
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//******************Metodo para validar la cedula del Usuario*******************************
	public boolean validarCedula(String id, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{		
		try {
			ArrayList<Usuarios> listac = UsuarioJSON.getJSON();
			for(Usuarios usuarios: listac) {
				
				if(usuarios.getCedula_usuario().equals(id)) {
					return true;										
				}
			}
			} catch (Exception e) {			
				e.printStackTrace();
			}
		return false;
		}
	
	//******************Metodo para buscar la cedula del Cliente*******************************
	public void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Clientes> listac = ClienteJSON.getJSON();
			for (Clientes clientes: listac) {
				if(clientes.getCedula_cliente().equals(id)) {
					request.setAttribute("clienteSeleccionado", clientes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//*****************************************************************************************
	//******************Metodo para buscar el codigo del producto*******************************	
	public void buscarProducto(String id, HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			ArrayList<Productos> listap = ProductoJSON.getJSON();
			for (Productos productos: listap) {
				if(productos.getCodigo_producto().equals(id)) {
						request.setAttribute("productoSeleccionado", productos);						
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	//*****************************************************************************************
	//******************Metodo para grabar Detalle Venta*******************************	
	public void grabarDetalle_Ventas (Long numFact, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		for(int i = 0; i<listaVentas.size(); i++) {
			detalle_venta = new Detalle_Venta();
			
			detalle_venta.setCodigo_venta(numFact);
			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
			detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
			detalle_venta.setValor_iva(listaVentas.get(i).getValor_iva());
			
			int respuesta1 = 0;
			
			try {
				respuesta1 = Detalle_VentaJSON.postJSON(detalle_venta);
				PrintWriter write1 = response.getWriter();
				if(respuesta1 == 200) {
					System.out.println("Registro grabado en Detalle Ventas: " + i);
					request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
				}else {
					write1.println("Error Detalle Ventas: " + respuesta1);
				}
				write1.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
	}
	//*****************************************************************************************
	
	//**************M�todo para generar el consecutivo de la factura de ventas*****************
	public Long generarConsecutivo() {
        long aux=0;
        try {
            ArrayList<Ventas> listav = VentaJSON.getJSON();
            for(Ventas venta:listav) {
                if(venta.getCodigo_venta()>aux) {
                    aux=venta.getCodigo_venta();
                }
            }
            return (aux+1);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return (aux+1);
    }
	
	//*****************************************************************************************
	
	//**************************Mostrar Fecha y Ciudad*************************************
	public void mostrarFechaCiudad (HttpServletRequest request, HttpServletResponse response) throws
						ServletException, IOException {
		ciudad = request.getParameter("ciudad");
		fecha = request.getParameter("fecha");
		
		request.setAttribute("ciudadSeleccionada", ciudad);
		request.setAttribute("fechaSeleccionada", fecha);
	}
	//*************************************************************************************
	
	//*********************** Grabar Consolidado ********************************************
	public void grabarConsolidado (HttpServletRequest request, HttpServletResponse response) throws
						ServletException, IOException {
		int respuesta = 0;
		try {
			respuesta = ConsolidadoJSON.postJSON(consolidado);
			PrintWriter write = response.getWriter();
			if(respuesta == 200) {
				System.out.println("Registro grabado en consolidacion");
				request.getRequestDispatcher("Controlador?menu=Ventas&accion=default").forward(request, response);
				
			} else {
				write.println("Error de Consolidacion" + respuesta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//***************************************************************************************
	
	//*********************** Buscar Ventas por Ciudad ***************************************
	public double mostrarTotalVentasCiudad(String ciudad) {
	
		double acTotalVentas=0;
		System.out.println(ciudad);
		try {
			ArrayList<Consolidado> listac = ConsolidadoJSON.getJSON();
			for(Consolidado consolidacion: listac) {
				System.out.println(consolidacion.getCiudad_venta());
				System.out.println(consolidacion.getTotal_ventas());
				if(consolidacion.getCiudad_venta().equals(ciudad)) {
					acTotalVentas = acTotalVentas + consolidacion.getTotal_ventas();
				}
			}
			return acTotalVentas;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acTotalVentas;
		
	}
	//***************************************************************************************

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
						ServletException, IOException {	
		
		String menu=request.getParameter("menu");		
		String accion=request.getParameter("accion");	
		
		//************Cedula del Usuario Activo *****************
		String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		usuarios1.setCedula_usuario(cedula_usuario_activo);
		request.setAttribute("usuarioSeleccionado1", usuarios1);
		//*******************************************************
		
		switch (menu) {
			case "New_usuarios":
				if(accion.equals("Agregar")) {
					String id=request.getParameter("txtcedula");
					if(!validarCedula(id, request, response)) {
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
								request.setAttribute("mensaje", "Usuario Creado Exitosamente");
								System.out.println("Usuario creado exitosamente");
								request.getRequestDispatcher("/Inicio.jsp").forward(request, response);								
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						System.out.println("Usuario existe");
						request.setAttribute("mensaje", "Usuario Existe");
						request.getRequestDispatcher("/New_usuarios.jsp").forward(request, response);
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
					if (accion.equals("Listar")) {
						try {
							ArrayList<Proveedores> lista = ProveedoresJSON.getJSON();
							request.setAttribute("listaProveedores", lista);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Agregar")) {
						Proveedores proveedor = new Proveedores();
						proveedor.setNitproveedor(request.getParameter("txtnit"));
						proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
						proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
						proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
						proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
						
						int respuesta=0;
						try {
							respuesta = ProveedoresJSON.postJSON(proveedor);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(accion.equals("Actualizar")) {
						Proveedores proveedor = new Proveedores();
						proveedor.set_id(request.getParameter("txtid"));
						proveedor.setNitproveedor(request.getParameter("txtnit"));
						proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
						proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
						proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
						proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));					
						int respuesta=0;
						try {
							respuesta = ProveedoresJSON.putJSON(proveedor, proveedor.get_id());
							PrintWriter write = response.getWriter();						
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
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
							ArrayList<Proveedores> lista1 = ProveedoresJSON.getJSON();
							for (Proveedores proveedores:lista1){
								if (proveedores.getNitproveedor().equals(id)) {
									request.setAttribute("proveedorSeleccionado", proveedores);
									request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
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
							respuesta = ProveedoresJSON.deleteJSON(id);
							PrintWriter write = response.getWriter();
							if (respuesta==200) {
								PrintWriter out = response.getWriter();
									
								request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar")
								.forward(request, response);
							} else {
								write.println("Error: " + respuesta);
							}
						} catch (Exception e) {
								e.printStackTrace();
						}					
					}
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
					
					//**********Enviar al formulario de ventas la c�dula del usuario y numero de factura**************
					request.setAttribute("usuarioSeleccionado1", usuarios1);
					request.setAttribute("numerofactura", numfac);
					
					//****************************************************************************
					
					if(accion.equals("BuscarCliente")) {
						String id = request.getParameter("cedulacliente");
						this.buscarCliente(id, request, response);
						this.mostrarFechaCiudad(request, response);
						
					}else if(accion.equals("BuscarProducto")){
						String id1 = request.getParameter("cedulacliente");												
						String id2 = request.getParameter("codigoproducto");
						this.buscarCliente(id1,request, response);
						this.buscarProducto(id2, request, response);
						this.mostrarFechaCiudad(request, response);
						request.setAttribute("listaventas", listaVentas);
						
					}else if(accion.equals("AgregarProducto")){
						String id = request.getParameter("cedulacliente");
						this.buscarCliente(id,request, response);
						this.mostrarFechaCiudad(request, response);
						
						detalle_venta = new Detalle_Venta();
					
						item++;
						totalapagar=0;
						totalventas=0;
						
						codProducto=request.getParameter("codigoproducto");
						descripcion=request.getParameter("nombreproducto");
						precio=Double.parseDouble(request.getParameter("precioproducto"));
						cantidad=Integer.parseInt(request.getParameter("cantidadproducto"));
						iva= Double.parseDouble(request.getParameter("ivaproducto"));
						
						subtotal = precio*cantidad;
						iva = (subtotal*iva)/100;
						
						detalle_venta.setCodigo_detalle_venta(item);
						detalle_venta.setCodigo_producto(codProducto);
						detalle_venta.setNombre_producto(descripcion);
						detalle_venta.setCantidad_producto(cantidad);
						detalle_venta.setPrecio_producto(precio);
						detalle_venta.setCodigo_venta(numfac);
						detalle_venta.setValor_iva(iva);
						detalle_venta.setValor_venta(subtotal);
						listaVentas.add(detalle_venta);
						
						for (int i=0; i<listaVentas.size(); i++) {
							acusubtotal+=listaVentas.get(i).getValor_venta();
							subtotaliva+=listaVentas.get(i).getValor_iva();
						}
						totalapagar=acusubtotal+subtotaliva;
						detalle_venta.setValor_total(totalapagar);
						//primer parametro es como lo vamos a llamar desde el jsp / segundo atributo lo que se envia y como lo recibe el formulario 
						request.setAttribute("listaVentas", listaVentas);
						request.setAttribute("totalsubtotal", acusubtotal);
						request.setAttribute("totaliva", subtotaliva);
						request.setAttribute("totalapagar", totalapagar);
						
					
					}else if(accion.equals("GenerarVenta")) {
						cedulaCliente=request.getParameter("cedulacliente");
						String numFact = request.getParameter("numerofactura");
						
						Ventas ventas= new Ventas();
						
						ventas.setCodigo_venta(Long.parseLong(numFact));
						ventas.setCedula_cliente(cedulaCliente);
						ventas.setCedula_usuario(usuarios1.getCedula_usuario());
						ventas.setValor_venta(acusubtotal);
						ventas.setIva_venta(subtotaliva);
						ventas.setTotal_venta(totalapagar);
						ventas.setFecha_venta(fecha);
						ventas.setCiudad_venta(ciudad);
						
						consolidado.setCiudad_venta(ciudad);
						consolidado.setFecha_venta(fecha);
						consolidado.setTotal_ventas(totalapagar);
						
						int respuesta=0;
						try {
							respuesta = VentaJSON.postJSON(ventas);
							PrintWriter write = response.getWriter();
							if(respuesta==200) {
								this.grabarConsolidado(request, response);
								this.grabarDetalle_Ventas(ventas.getCodigo_venta(), request, response);
								System.out.println("Grabaci�n exitosa" + respuesta);
							}else {
								write.println("Error Ventas:" + respuesta);
							}
							write.close();
							} catch (Exception e) {
							e.printStackTrace();
						}
					listaVentas.clear();
					item=0;
					totalapagar=0;
					acusubtotal=0;
					subtotaliva=0;
					
						
					}else {
						// ********* Muestro por primera vez el numero de la factura *****************
						numfac = this.generarConsecutivo();
						request.setAttribute("numerofactura", numfac);
					}				
					request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
				break;
				
				case "Reportes":
					int opcion = 0;
					if(accion.equals("ReporteUsuarios")) {
						try {
							ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
							opcion=1;
							request.setAttribute("listaUsuarios", lista);
							request.setAttribute("opcion", opcion);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} else if (accion.equals("ReporteClientes")) {
						try {
							ArrayList<Clientes> lista = ClienteJSON.getJSON();
							opcion=2;
							request.setAttribute("listaClientes", lista);
							request.setAttribute("opcion", opcion);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (accion.equals("ReporteVentas")) {
						double acutotalv = 0;
						try {
							ArrayList<Ventas> lista = VentaJSON.getJSON();							
							opcion=3;
							for(Ventas venta: lista) {
								acutotalv += venta.getTotal_venta();
							}
							System.out.println(acutotalv);
							request.setAttribute("totaltotal", acutotalv);	
							request.setAttribute("listaVentas", lista);							
							request.setAttribute("opcion", opcion);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					} 
					request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
				break;
				
				case "Consolidado":
					
					double totalventascali=0, totalventasbogota=0, totalventasmedellin=0, totalventasgeneral=0;
						totalventascali= mostrarTotalVentasCiudad("Cali");
						totalventasbogota = mostrarTotalVentasCiudad("Bogota");
						totalventasmedellin = mostrarTotalVentasCiudad("Medellin");
						
						totalventasgeneral = totalventascali + totalventasbogota + totalventasmedellin;
						
						request.setAttribute("totalventacali", totalventascali);
						request.setAttribute("totalventabogota", totalventasbogota);
						request.setAttribute("totalventamedellin", totalventasmedellin);
						request.setAttribute("totalventageneral", totalventasgeneral);
						
						request.getRequestDispatcher("/ReporteConsolidado.jsp").forward(request, response);
					break;

				
		}
	}
}

