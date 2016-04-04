<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<script type="text/javascript">
	debugger
	/**
	 * Id del audio seleccionado 
	 */
	var audioId = null;
	
	/**
	 * Persist del audio
	 */
	$("#formMensaje").submit(
		function(ev) {
			var formJsonData = 
				'{"id":' + audioId + ',' + 
				'"orador":"' + $("#orador").val() + '",' +
				'"titulo":"' + $("#titulo").val() + '",' +
				'"tema":"' + $("#tema").val() + '",' +
				'"lugar":"'+ $("#lugar").val() + '"' + 
				'}';
	
			var formData = JSON.parse(formJsonData);
			
			// Validaciones!
			if(formData.orador == ""){
				alert("No se puede cargar un audio sin Autor!");
				return false;
			}
			
			// Request AJAX
			$.ajax({
				type : $("#formMensaje").attr('method'),
				contentType : "application/json",
				url : $("#formMensaje").attr('action'),
				data : formJsonData,
				success : function(data) {
					if(data != null){
						alert(data);
					}
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: " + status + " er:"
							+ er);
				}
			});
			ev.preventDefault();
			return false;
		}
	);

	function cancelEdit() {
		$("#orador").val("");
		$("#titulo").val("");
		$("#tema").val("");
		$("#lugar").val("");
		audioId = null;
		$("#ok").val("Agregar");
	}
	function fillFields(id, orador, titulo, tema, lugar) {
		$("#orador").val(orador);
		$("#titulo").val(titulo);
		$("#tema").val(tema);
		$("#lugar").val(lugar);
		audioId = id;
		$("#ok").val("Modificar");
	}
	function search(){
		var params = '?';
		var orador = $("#orador").val();
		var titulo = $("#titulo").val();
		var tema = $("#tema").val();
		var lugar = $("#lugar").val();
		
		if(orador != ""){
			params += 'orador=' + orador + '&';
		}
		if(titulo != ""){
			params += 'titulo=' + titulo + '&';
		}
		if(tema != ""){
			params += 'tema=' + tema + '&';
		}
		if(lugar != ""){
			params += 'lugar=' + lugar;
		}
		
		url = '<c:url value="/audio/mensajes" />';
		setContentBody(url + params, null);
	}
</script>
<div>
	<div class="abm">
		<div>
			<strong>Mensajes!!!!</strong>
		</div>
		<form id="formMensaje" action="<c:url value='/audio/persist' />" method="POST">
			<table>
				<tr>
					<td><label>T&iacute;tulo</label></td>
					<td><input id="titulo" type="text" /></td>
				</tr>
				<tr>
					<td><label>Orador</label></td>
					<td><input id="orador" type="text" /></td>
				</tr>
				<tr>
					<td><label>Tema</label></td>
					<td><input id="tema" type="text" /></td>
				</tr>
				<tr>
					<td><label>Lugar</label></td>
					<td><input id="lugar" type="text" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="submit" id="ok" value="Agregar" />
						<input type="button" id="cancel" value="Borrar" onclick="cancelEdit();" />
						<input type="button" id="find" value="Buscar" onclick="search();" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="displaytag">
		<display:table id="currentMensaje" length="10" name="${mensajesList}">
			<display:column property="titulo" title="Nombre" />
			<display:column property="orador" title="Orador" />
			<display:column property="tema" title="Tema" />
			<display:column property="idReunion" title="Tipo de reunion" />
			<display:column property="lugar" title="Lugar" />
			<display:column title="Acciones">
			</display:column>
			<display:setProperty name="basic.empty.showtable" value="true"></display:setProperty>
		</display:table>
	</div>
</div>