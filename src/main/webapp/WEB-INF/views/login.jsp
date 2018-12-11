<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script 
  src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<title>Login | InTime</title>

<style>
.login-form {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.login-container h2 {
	
	background-color: #337ab7;
	color: #333;
}
</style>

</head>
<body>
	<center>
<img src="http://i65.tinypic.com/s5ybty.png" style="width:%;">
   <br/>
  	</div>
  </center>

	<div class="container login-container col-md-4 col-md-offset-4 ">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-pencil "></span> Login
			</h2>

			<div class="login-form">



				
				<form action="login" method="post" id="formlogin" data-toggle="validator">
					<div class="form-group">
						<input name="txtUsuarioEmail" type="email" class="form-control" placeholder="E-mail *" data-error="Direcci&oacute;n de correo inv&aacute;lida" value="" required/>
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<input name="txtUsuarioPassword" type="password" class="form-control" placeholder="Contrase&ntilde;a *" value="" />
					</div>
					<div class="form-inline text-center" style="margin: auto;">
						<div class="form-group">
							<input name="acceso" type="submit" class="btnSubmit" value="Acceso" />
							<span style="color:red"><em>${alerta}</em></span>
						</div>
					</div>
				</form>
			</div>
			
		</div>

	</div>

</body>
</html>
