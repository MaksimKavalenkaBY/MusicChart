<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Buttons</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<form>
			<input class="clear" type="reset" value="Clear">
			<input class="cancel" type="button" value="Cancel" onClick="JavaScript:document.cancelForm.submit()">
		</form>
		<form method="POST" name="cancelForm" action="/web/music.chart"></form>
	</body>
</html>