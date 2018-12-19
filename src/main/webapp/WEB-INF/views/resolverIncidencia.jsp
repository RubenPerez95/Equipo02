
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
.modificacion-container h2{
	padding: 0% 0% 0% 5%;
}
.busqueda-container {
    margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.busqueda-container h2{
	padding: 0% 0% 0% 5%;
}
.gestion-form {
	padding: 0% 5% 0% 5%;
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
      
			<div class="nav-item pull-left col-md-1 fixed-top" style="position: relative; top: 8px">
				<form action="REfichajesUser" method="GET"> <button class="btn" type="submit"><strong><span class="glyphicon glyphicon-sort"></span> Fichajes</strong></button></form>
			</div>
			<div class="col-md-1 pull-right fixed-top" style="position: relative; top: 8px">
				<form action="logout" method="GET"> <button class="btn btn-danger" type="submit"><strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong></button></form>
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
					<form action="seleccionarIncidencia" method="get">
					<input name="idI" type="hidden" value="${incidencia._id}"/>
						<a id="incidenciaA" class="list-group-item list-group-item-action ">Nombre: ${incidencia.nombreUsuario} Dni: ${incidencia.dniUsuario} Categoria: ${incidencia.categoria} Fecha: ${incidencia.fechaCreacion}</a>
						<button type="submit" name="seleccionar">Seleccionar Incidencia</button>
					</form>
				</c:forEach>
			</div>

		</div>
	</div>





	<div class="container modificacion-container col-md-6">
		
			<h2>
				<span class="glyphicon glyphicon-pencil"></span> Resoluci&oacute;n Incidencia
			</h2>
			<label>Email de usuario actual: ${EmailUsuario}</label>
		<form id="resolverIncidencia" method=get data-toggle="validator">
			
			<div class="form-group">
    				<h3>DNI del usuario</h3>
						<input id="DNIUsuario" type="text" disabled class="form-control" placeholder="DNI del Usuario" value="${seleccionadaInci.getDniUsuario()} " />
					</div>
			<div class="form-group">
				<h3>Nombre del usuario</h3>
				<input name="txtNombre" type="text" disabled class="form-control" placeholder="Nombre del usuario" value="${seleccionadaInci.getNombreUsuario()}" /> 
			</div>
			
			<div class="form-group">
				<h3>Categoria</h3>
				<input name="txtCategoria" type="text" disabled class="form-control" placeholder="Categoria" value="${seleccionadaInci.getCategoria()}" />

			</div>
			<div class="form-group">
						   <h3>Descripción de la incidencia: </h3>
                           <textarea disabled class=textoIncidencia name=textoIncidencia rows="5" cols="90"> ${seleccionadaInci.getDescripcion()}</textarea>
			</div>
			
			<div class="form-group">
						   <h3>Comentario de la incidencia: </h3>
                           <textarea class=textoIncidencia name=textoGestor data-error="Campo necesario" rows="5" cols="90" required></textarea>
                           <div class="help-block with-errors"></div>
			</div>
			<input name="idSeleccionada" type="hidden" value="${seleccionadaInci.get_id()}"/>
			<div class="botones-form text-right" style="margin: auto;">
				<input type="submit" class="btnModUsuario input-lg" name="Resolver" formaction="resolverIncidencia"
					value="Resolver Incidencia"  />
					<input type="submit" class="btnModUsuario input-lg" name="Resolver" formaction="denegarIncidencia"
					value="Denegar Incidencia" />
	
			</div>
		</form>

	</div>

</body>
<script type="text/javascript">

</script>




</html>