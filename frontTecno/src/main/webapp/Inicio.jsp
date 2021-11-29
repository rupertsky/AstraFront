<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<link rel="icon" href="img/minilogo.ico" type="image/x-icon">
<link rel="stylesheet" href="css/login.css">
<title>Login</title>
</head>
<body>
	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
       <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
         <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 
         9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 
         11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
       </symbol>        
       <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
         <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 
         1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 
         0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 
         0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
       </symbol>
     </svg>
	<div class="container col-lg-4">
        <!--Inicio Login-->
        <div class="card-body">
            <form class="form-sign" method="get" action="./Servlet">
                <div class="form-group text-center">
                    <img src="img/logocortado.png" class="icon" alt="Astra" width="180">
                </div>
                <div>
                    <label for="usuario" class="form-label mt-3 fw-bold">Usuario</label>                                        
                     <input type="text" class="form form-control text-light" id="usuario" name="txtusuario" required>
                </div>
                <div>
                    <label for="password" class="form-label mt-3 fw-bold">Password</label>
                    <input type="password" class="form form-control text-light"  name="txtpassword" id="password" required>
                 </div>                
                    <button type="submit" class="btn btn-primary m-2 fw-bold" style="color: black;" 
                    name="accion" value="Ingresar">Ingresar</button>
                    <a class="btn btn-primary fw-bold" href="New_usuarios.jsp">Registrarse</a>
              </form>
              
        </div>
        <!--Fin Login-->
     
        
        <div style="color:=red;">
				 ${mensaje}
	    </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
    crossorigin="anonymous"></script>
</body>
</html>