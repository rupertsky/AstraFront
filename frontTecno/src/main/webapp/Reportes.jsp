<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" 
crossorigin="anonymous">
<link rel="stylesheet" href="css/reportes.css">
<meta charset="ISO-8859-1">
<title>Reportes / Informes</title>
</head>
<body>
	<div class="row">
		<div class="col-md-7 seccion1">
			<h2 class="card-title" style="color: white;">Reportes</h2>
			<form method="get" accion="Controlador">
				<div class="card mt-4">					
					<div class="card-body">
						<div class="form-group">
							<h4 class="card-title" style="color: white;">Seleccione el Tipo de Reporte</h4>							
						</div>
						<input type="hidden" name="menu" value="Reportes">
						<div class="form-group d-flex">
							<div class="col-sm-5 d-flex">
								<input type="submit" name="accion" value="ReporteUsuarios" class="btn btn-primary m-2">
								<input type="submit" name="accion" value="ReporteClientes" class="btn btn-primary m-2">
								<input type="submit" name="accion" value="ReporteVentas" class="btn btn-primary m-2">
								<input type="submit" name="accion" value="ReporteDetalleVenta" class="btn btn-primary m-2 mr-2">
							</div>
						</div>
					</div>				
				</div>				
			</form>		
		</div>	
	</div>
	<div class="col-md-7 seccion2">
		<div class="card mt-2">
			<div class="card-body">
				<div class="form-group">
					<h4 class="card-title m-2" style="color: white;">Detalle del Reporte</h4>					
				</div>
				<table class="table">
				
					<c:if test="${opcion==1}">
						<h5 class="m-2" style="color: white;" >Usuarios</h5>
						<thead class="thead-dark">
						<tr>
							<th scope="col">Cédula</th>
							<th scope="col">Nombre</th>
							<th scope="col">Email</th>
							<th scope="col">Usuario</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaUsuarios}">
								<tr>
									<td>${lista.getCedula_usuario()}</td>
									<td>${lista.getNombre_usuario()}</td>
									<td>${lista.getEmail_usuario()}</td>									
									<td>${lista.getUsuario()}</td>
									<td></td>
								</tr>
							</c:forEach>				
						</tbody>
					</c:if>
					
					<c:if test="${opcion==2}">
						<h5 class="m-2" style="color: white;" >Clientes</h5>
						<thead class="thead-dark">
						<tr>
							<th scope="col">Cédula</th>
							<th scope="col">Nombre</th>
							<th scope="col">Email</th>
							<th scope="col">Dirección</th>
							<th scope="col">Telefono</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaClientes}">
								<tr>
									<td>${lista.getCedula_cliente()}</td>
									<td>${lista.getNombre_cliente()}</td>
									<td>${lista.getEmail_cliente()}</td>									
									<td>${lista.getDireccion_cliente()}</td>
									<td>${lista.getTelefono_cliente()}</td>
									<td></td>
								</tr>
							</c:forEach>				
						</tbody>
					</c:if>
					<c:if test="${opcion==3}">	
					<h5 class="m-2" style="color: white;" >Ventas</h5>				
						<thead class="thead-dark">
						<tr>
							<th scope="col">Código Ventas</th>
							<th scope="col">Cédula Cliente</th>
							<th scope="col">Cédula Usuario</th>
							<th scope="col">Valor Ventas</th>
							<th scope="col">Valor Iva</th>
							<th scope="col">Valor Total</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaVentas}">
								<tr>
									<td>${lista.getCodigo_venta()}</td>
									<td>${lista.getCedula_cliente()}</td>
									<td>${lista.getCedula_usuario()}</td>	
									<td>${lista.getValor_venta()}</td>								
									<td>${lista.getIva_venta()}</td>
									<td>${lista.getValor_venta()}</td>
									<td></td>
								</tr>
							</c:forEach>				
						</tbody>
					</c:if>
					<c:if test="${opcion==4}">	
					<h5 class="m-2" style="color: white;" >Detalle Ventas</h5>				
						<thead class="thead-dark">
						<tr>
							<th scope="col">Código Venta</th>
							<th scope="col">Código Producto</th>
							<th scope="col">Cantidad Producto</th>
							<th scope="col">Valor Venta</th>
							<th scope="col">Valor Iva</th>
							<th scope="col">Valor Total</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaDetalleVenta}">
								<tr>
									<td>${lista.getCodigo_venta()}</td>
									<td>${lista.getCodigo_producto()}</td>
									<td>${lista.getCantidad_producto()}</td>	
									<td>${lista.getValor_venta()}</td>								
									<td>${lista.getValor_iva()}</td>
									<td>${lista.getValor_total()}</td>
									<td></td>
								</tr>
							</c:forEach>				
						</tbody>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
</html>