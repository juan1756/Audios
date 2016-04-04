<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	debugger
	function sendTest() {
		var form = $("#test");
		var formData = '{"input1":"' + $("#input1").val() + '",' +
							'"input2":"' + $("#input2").is(':checked') + '",' +
							'"input3":"' + $("#input3").is(':checked') + '",' +
							'"select1":"' + $("#select1").val() + '"' +
							'}';
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : form.attr('action'),
			//data : '{"input1": "hola", "input2": "true", "input3": "false", "input4": "holi"}',
			data: formData,
			success : function(data) {
				alert(data.input1 + " " + data.input2 + " " + data.input3 + " " + data.select1);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
	}
</script>
<div>
	<form id="test" action="<c:url value='/testPost' />">
		<input id="input1" type="text" value="hola"/>
		<input id="input2" type="checkbox"/>
		<input id="input3" type="radio"/>
		<select id="select1">
			<option value="1">
				holi
			</option>
		</select>
		<input type="button" value="Enviar" onclick="sendTest();"/>
	</form>
</div>
</html>