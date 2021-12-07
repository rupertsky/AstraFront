<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" 
rel="stylesheet">
<link rel="stylesheet" href="css/reportes.css">
<meta charset="ISO-8859-1">
<title>Reporte Consolidado</title>
</head>
<body>
	
	<div class="col-md-7 seccion2">
		<div class="card mt-2">
			<div class="card-body">
				<div class="form-group">
					<h4 class="card-title m-2" style="color: white;">Reporte de Total de ventas por Ciudad</h4>					
				</div>
				<table class="table">
						<thead class="thead-dark">
						<tr>
							<th scope="col">Ciudad</th>
							<th scope="col">Valor Total Ventas</th>
						</tr>
						</thead>
						<tbody>
								<tr>
									<td>Bogota</td>
									<td>${totalventabogota}</td>									
								</tr>
								<tr>
									<td>Cali</td>
									<td>${totalventacali}</td>									
								</tr>
								<tr>
									<td>Medellin</td>
									<td>${totalventamedellin}</td>									
								</tr>
								<tr>
									<td>Total Ventas</td>
									<td>${totalventageneral}</td>									
								</tr>	
						</tbody>
							
					
				</table>
			</div>
		</div>
	</div>
</body>
</html>