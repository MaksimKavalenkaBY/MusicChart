<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Delete Track-Actor</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link rel="SHORTCUT ICON" href="/web/image/other/MusicChartIcon.png" type="image/x-icon">
	</head>

	<body>
		<c:import url="../title/editTitle.jsp"/>
		<hr color="FFD700">

		<form method="POST" name="deleteTrackActorForm" action="/web/edit">
			<input type=hidden name="action" value="DELETE_TRACK_ACTOR">
			<table class="form">
				<tr>
					<td></td>
					<td>Choose the track and its actors you want delete</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<b class="error">${error}</b>
					</td>
				</tr>
				<tr>
					<td class="name">Track</td>
					<td>
						<select name="Name">
							<option disabled>Choose the track</option>
							<c:forEach var="track" items="${Track}">
								<option value="${track.name}">${track.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">Actor</td>
					<td>
						<c:forEach var="actor" items="${Actor}">
							<input type=checkbox name="${actor.name}">
							${actor.name}<br>
						</c:forEach>
					</td>
				</tr>
			</table>

			<input class="agree" type="submit" value="Delete">
			<c:import url="../other/buttons.jsp"/>
		</form>
	</body>
</html>