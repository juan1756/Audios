<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>
		<script type="text/javascript">
			var last = '';
			var interval = setInterval(function(){
				$.ajax({
					url : '<c:url value="/test/" />',
		            success: function (response) {
		            	if(last != response){
			            	$("#content").html($("#content").html() + '<br>' +  response);
		            	}
		            	last = response;
		            },
		        });
			}, 1000);
		</script>
		<title>HOLA MUNDO</title>
	</head>
	<body>
		<div id="content">
			${message}
		</div>
	</body>
</html>