<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Tags</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link rel="SHORTCUT ICON" href="/web/image/other/MusicChartIcon.png" type="image/x-icon">
	</head>

	<body>
		<table class="main">
			<tr class="title">
				<td>
					<c:import url="../title/mainTitle.jsp"/>
				</td>
			</tr>
			<tr class="body">
				<td>
					<c:import url="../body/tagBody.jsp"/>
				</td>
			</tr>
			<tr class="title">
				<td>
					<c:import url="../title/contactsTitle.jsp"/>
				</td>
			</tr>
		</table>
	</body>
</html>