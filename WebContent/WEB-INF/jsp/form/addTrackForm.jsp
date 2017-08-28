<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Add Track</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link rel="SHORTCUT ICON" href="/web/image/other/MusicChartIcon.png" type="image/x-icon">
	</head>

	<body>
		<c:import url="../title/editTitle.jsp"/>
		<hr color="FFD700">

		<form method="POST" name="addTrackForm" action="/web/edit">
			<input type=hidden name="action" value="TRACK">
			<table class="form">
				<tr>
					<td></td>
					<td>Enter the data of the track</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<b class="error">${error}</b>
					</td>
				</tr>
				<tr>
					<td class="name">Name</td>
					<td><input type="text" name="Name" size="70" maxlength="70"></td>
				</tr>
				<tr>
					<td class="name">Image</td>
					<td><input type="text" name="Image" size="70" maxlength="255"></td>
				</tr>
				<tr>
					<td class="name">URL</td>
					<td><input type="text" name="URL" size="70" maxlength="255"></td>
				</tr>
			</table>

			<input class="agree" type="submit" value="Add">
			<c:import url="../other/buttons.jsp"/>
		</form>
	</body>
</html>