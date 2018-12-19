%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" http-equiv="Content-Type"
  content="text/html,width=device-width, initial-scale=1, shrink-to-fit=no ">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script 
  src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<c:set var="incidencia" value="${incidencia}" scope="request" />



<style>
.modificacion-container {
	margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.modificacion-container h2 {
	padding: 0% 0% 0% 5%;
}

.busqueda-container {
	margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.busqueda-container h2 {
	padding: 0% 0% 0% 5%;
}

.gestion-form {
	padding: 0% 5% 0% 5%;
}

textarea { 
	resize: none;
}

.gestion-form h3 {
	text-align: left;
	color: #333;
	text-align: left;
	font-size: 17px;
}

.botones-form {
	padding: 5% 0% 5% 0%;
}

.botones-form .btnModUsuario {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}
</style>


</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>

			<div class="nav-item pull-left col-md-1 fixed-top"
				style="position: relative; top: 8px">
				<form action="REfichajesUser" method="GET">
					<button class="btn" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="col-md-1 pull-right fixed-top"
				style="position: relative; top: 8px">
				<form action="logout" method="GET">
					<button class="btn btn-danger" type="submit">
						<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
					</button>
				</form>
			</div>
		</div>
	</nav>

	<div class="container busqueda-container col-md-4">
		<div class="row">
			<h2>
				<span class="glyphicon glyphicon-search"></span> Lista de
				Incidencias
			</h2>
			<span style="color: red"><em>${nullIncidencia}</em></span>

			<div class="list-group">
				<c:forEach items="${listaIncidencias}" var="incidencia">
					<form action="seleccionarIncidenciaUsuario" method="get">
						<input name="idI" type="hidden" value="${incidencia._id}" /> <a
							id="incidenciaA" class="list-group-item list-group-item-action ">Nombre:
							${incidencia.nombreUsuario} Dni: ${incidencia.dniUsuario}
							Categoria: ${incidencia.categoria} Fecha:
							${incidencia.fechaCreacion}</a>
						<button type="submit" name="seleccionar">Seleccionar
							Incidencia</button>
					</form>
				</c:forEach>
			</div>

		</div>
	</div>





	<div class="container modificacion-container col-md-6">

		<h2>
			<span class="glyphicon glyphicon-pencil"></span> Modificaci&oacute;n
			Incidencia
		</h2>
		<form id="modificarIncidenciaUser"  data-toggle="validator" method=get>

			<div class="form-group">
				<h3>DNI del usuario</h3>
				<input id="DNIUsuario" type="text" disabled class="form-control"
					placeholder="DNI del Usuario"
					value="${seleccionadaInci.getDniUsuario()}" />
			</div>
			<div class="form-group">
				<h3>Nombre del usuario</h3>
				<input name="txtNombre" type="text" disabled class="form-control"
					placeholder="Nombre del usuario"
					value="${seleccionadaInci.getNombreUsuario()}" />
			</div>

			<div class="tipoIncidencia-form">
				<h3>Tipo de incidencia:</h3>
				<select class="listaDesplegable" name="listaTiposIncidencia">
					<option value="Vacaciones">Vacaciones</option>
					<option value="Baja">Baja médica</option>
					<option value="Ausencia">Justificación de ausencia</option>
					<option value="Error">Error de fichaje</option>
				</select> <label>La categor&iacutea actual es:
					${seleccionadaInci.getCategoria()}</label>
			</div>
			<div class="form-group">
				<h3>Fecha de Incidencia</h3>
				<input name="txtFecha" type="text" class="form-control"
					placeholder="yyyy-MM-dd" data-error="Formato de fecha err&oacute;neo" pattern="([0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" required /> 
					<div class="help-block with-errors"></div>
					<label>La fecha actual de la incidencia es: ${seleccionadaInci.getFechaCreacion()}</label>
			</div>
			<div class="form-group">
				<h3>Descripción de la incidencia:</h3>
				<textarea class=textoIncidencia data-error="Campo necesario"  name="textoIncidencia" rows="5"
					cols="90" required> ${seleccionadaInci.getDescripcion()}</textarea>
					<div class="help-block with-errors"></div>
			</div>
      <span
  				style="color: red"><em>${alerta}</em></span>
			<input name="idSeleccionada" type="hidden"
				value="${seleccionadaInci.get_id()}" />
			<div class="botones-form text-right" style="margin: auto;">
				<input type="submit" class="btnModUsuario input-lg" name="Resolver"
					formaction="modificarIncidenciaUser" value="Aceptar" /> 
			</div>
		</form>

	</div>

</body>
<script type="text/javascript">
	
</script>




</html>