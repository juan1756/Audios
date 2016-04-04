<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function getMensajes() {
		setContentBody("<c:url value='/audio/mensajes' />", null);
	}
</script>
<div>
	<nav>
		<ul>
			<li><a href="javascript:void(0)" onclick="getMensajes();">Archivos
					de audio</a></li>
			<li><a href="javascript:void(0) " onclick="">Archivos de
					video</a></li>
			<li><a href="javascript:void(0) " onclick="">Archivos
					miscel&aacute;neos</a></li>
		</ul>
	</nav>
</div>
</html>