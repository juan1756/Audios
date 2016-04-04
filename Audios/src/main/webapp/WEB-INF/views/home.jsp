<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	
	<link rel="shortcut icon" href="<c:url value="/resources/favicon/favicon.ico" />" />
	<link rel="stylesheet" href='<c:url value="/resources/css/displaytag.css"/>' type="text/css"/>
	
	<title>Audios 1.0</title>

	<script src="resources/js/jquery-2.1.1.min.js"></script>
	<script src="resources/js/skel.min.js">
	    {
	      prefix: "resources/css/style",
	      resetCSS: true,
	      boxModel: "border",
	      grid: { gutters: 30 },
	      breakpoints: {
	        wide: { range: "1200-", containers: 1140, grid: { gutters: 50 } },
	        narrow: { range: "481-1199", containers: 960 },
	        mobile: { range: "-480", containers: "fluid", lockViewport: true, grid: { collapse: true } }
	      }
	    }
	</script>
	<script type="text/javascript">
		function setContentBody(url, onComplete){
			$("#dinamicBody").load(url, onComplete);
		}
		function getAudio(){
			setContentBody("<c:url value='/audio/menu' />", null);
		}
		function getTest(){
			setContentBody("<c:url value='/test' />", null);
		}
		function getPedidos(){
			setContentBody("<c:url value='/pedidos' />", null);
		}
	</script>

</head>
<body>
	<div class="container">
		<!-- Header -->
		<div id="header" class="row">
			<div class="1u">
				<h1>Audios</h1>
			</div>
			<nav id="nav" class="8u">
				<ul>
					<li><a href="javascript:void(0)">Inicio</a></li>
					<li><a href="javascript:void(0)" onclick="">Operadores</a></li>
					<li><a href="javascript:void(0)" onclick="getPedidos();">Pedidos</a></li>
					<!-- 
					<li><a href="javascript:void(0)" onclick="getAudio();">Audio</a></li>
					 -->
				</ul>
			</nav>
		</div>
		<!-- Hero -->
		<div class="row">
				<!-- 
			<div class="3u">
				<nav>
					<div class="listContainer">
						<ul>
							<li><a href="javascript:void(0)">Inicio</a></li>
							<li><a href="javascript:void(0)" onclick="">Operadores</a></li>
							<li><a href="javascript:void(0)" onclick="getPedidos();">Pedidos</a></li>
							<li><a href="javascript:void(0)" onclick="getAudio();">Audio</a></li>
						</ul>
					</div>
				</nav>
			</div>
				 -->
			<div id="dinamicBody" class="12u"></div>
		</div>
		<!-- Footer -->
		<div id="footer">© Audios. All rights reserved.</div>
	</div>
</body>
</html>