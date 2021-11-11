<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<link rel="icon" href="img/minilogo.ico" type="image/x-icon">
<link rel="stylesheet" href="./css/login.css">
<title>Login</title>
</head>
<body>
	<div class="container col-lg-4 bcenter">
        <!--Inicio Login-->
        <div class="card-body">
            <form class="form-sign" method="get" action="./Servlet" id="formulario">

                <div class="form-group text-center">
                    <img src="img/logocortado.png" class="icon" alt="Astra" width="180">
                </div>

                <div class="form-group mb-lg-3">               	                 
                  	<input type="text" id="usuario" name="txtusuario" class="form form-control text-light mt-3" placeholder="Usuario">
                  	
                </div>

                <div class=" form-group mb-lg-3">                  
                  	<input type="password" id="password" name="txtpassword" class="form form-control text-light mt-3" placeholder="Contraseña">
                                  
                </div>                
                <button type="submit" class="btn btn-primary m-2" style="color: black; font-weight: bold;" name="accion" value="Ingresar">Ingresar</button>
              </form>              
              <div id="dato_invalido"></div>
        </div>
        <!--Fin Login-->
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="js/validarLogin.js"></script>
</body>
</html>