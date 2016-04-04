<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html>
<script type="text/javascript">
	debugger
	/**
	 * Id del pedido seleccionado 
	 */
	var pedidoId = null;
	
	/**
	 * Persist del pedido
	 */
	function commit(){
		if($("#cliente").val() == "" || $("#descripcion").val().trim() == ""){
			alert("Cliente o Descripcion no pueden estar vacíos!");
			return;
		}
		$.ajax({
			type : $("#formPedidos").attr('method'),
			url : $("#formPedidos").attr('action') + '?id=' + pedidoId,
			data : $("#formPedidos").serialize(),
			success : function(data) {
				if(data != ""){
					alert(data);
				} else {
					getPedidos();
				}
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:"
						+ er);
			}
		});
	}
	/*
	$("#formPedidos").ajaxSubmit({
		url : this.attr('action') + '?id=' + pedidoId,
		type : this.attr('method')
	});
	*/

	function cancelEdit() {
		$("#cliente").val("");
		$("#descripcion").val("");
		$("#abonado").prop('checked', false);
		$("#montoAbonado").val("");
		$("#entregado").prop('checked', false);
		pedidoId = null;
		$("#ok").val("Agregar");
	}
	function fillFields(id, cliente, descripcion, abonado, montoAbonado, entregado) {
		$("#cliente").val(cliente);
		$("#descripcion").val(descripcion);
		$("#abonado").prop('checked', abonado);
		$("#montoAbonado").val(montoAbonado);
		$("#entregado").prop('checked', entregado);
		pedidoId = id;
		$("#ok").val("Modificar");
	}
	function search() {
		var params = '?';
		var cliente = $("#cliente").val();
		var descripcion = $("#descripcion").val().trim();
		//var abonado = $("#abonado").is(':checked');
		//var montoAbonado = $("#montoAbonado").val();
		//var entregado = $("#entregado").is(':checked');

		if (cliente != "") {
			params += 'cliente=' + cliente + '&';
		}
		if (descripcion != "") {
			params += 'descripcion=' + descripcion + '&';
		}
		/*
		if (abonado != "") {
			params += 'abonado=' + abonado + '&';
		}
		if (montoAbonado != "") {
			params += 'montoAbonado=' + montoAbonado + '&';
		}
		if (entregado != "") {
			params += 'entregado=' + entregado;
		}
		*/

		url = '<c:url value="/pedidos/getPedidos" />';
		setContentBody(url + params, null);
	}
	function cancelPedido(id){
		$.ajax({
			type : 'POST',
			url : '<c:url value="/pedidos/removePedido" />'  + '?id=' + id,
			success : function(data) {
				if(data != ""){
					alert(data);
				} else {
					getPedidos();
				}
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:"
						+ er);
			}
		});
	}
</script>
<div>
	<div class="abm">
		<div>
			<strong>PEDIDOS!!!</strong>
		</div>
		<br>
		<form id="formPedidos" action="<c:url value='/pedidos/persist' />" method="POST">
			<table>
				<tr>
					<td><label>Cliente</label></td>
					<td><input id="cliente" name="cliente" type="text" /></td>
				</tr>
				<tr>
					<td><label>Descripcion</label></td>
					<td>
						<input id="descripcion" name="descripcion" type="text"/>
					</td>
				</tr>
				<tr>
					<td><label>Abonado</label></td>
					<td><input id="abonado" name="abonado" type="checkbox" /></td>
				</tr>
				<tr>
					<td><label>Monto abonado</label></td>
					<td><input id="montoAbonado" name="montoAbonado" type="text" /></td>
				</tr>
				<tr>
					<td><label>Entregado</label></td>
					<td><input id="entregado" name="entregado" type="checkbox" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="button" id="ok" value="Agregar" onclick="commit();"/>
						<input type="button" id="cancel" value="Borrar" onclick="cancelEdit();" />
						<input type="button" id="find" value="Buscar" onclick="search();" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="displaytag">
		<display:table id="currentPedido" length="10" name="${pedidosList}">
			<display:column property="cliente" title="Cliente" />
			<display:column property="descripcion" title="Descripcion" />
			<display:column title="Abonado" >
				<c:if test="${currentPedido.abonado}">
					<c:out value="Si" />
				</c:if>
			</display:column>
			<c:choose>
				<c:when test="${currentPedido.abonado}">
					<display:column property="montoAbonado" title="Monto abonado" />
				</c:when>
				<c:otherwise>
					<display:column title="Monto abonado" >-</display:column>
				</c:otherwise>
			</c:choose>
			<display:column title="Entregado" >
				<c:if test="${currentPedido.entregado}">
					<c:out value="Si" />
				</c:if>
			</display:column>
			<display:column title="Acciones">
				<img src='<c:url value="/resources/images/linealIcons/edit3.png"/>' onclick="fillFields('${currentPedido.id}', '${currentPedido.cliente}', '${currentPedido.descripcion}', ${currentPedido.abonado}, '${currentPedido.montoAbonado}', ${currentPedido.entregado});"/>
				<img src='<c:url value="/resources/images/linealIcons/cancel1.png"/>' onclick="if (confirm('Realmente desea eliminarlo?')){cancelPedido('${currentPedido.id}');}"/>
			</display:column>
			<display:setProperty name="basic.empty.showtable" value="true"></display:setProperty>
		</display:table>
	</div>
</div>