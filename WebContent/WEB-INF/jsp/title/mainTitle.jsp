<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Head Menu</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
	</head>

	<body>
		<b class="error">${error}</b>

		<table class="main">
			<tr>
				<td align="left" width="33%">
					<section class="border">
						<input type="button" class="button" value="Tracks" onClick="javascript:window.location='music.chart?action=TRACK'">
						<input type="button" class="button" value="Actors" onClick="javascript:window.location='music.chart?action=ACTOR'">
						<input type="button" class="button" value="Tags" onClick="javascript:window.location='music.chart?action=TAG'">

						<c:if test="${not empty user.login}">
							<br><br>
							<input type="button" class="button" value="My tracks" onClick="javascript:window.location='music.chart?action=USER_TRACK'">
							<input type="button" class="button" value="My actors" onClick="javascript:window.location='music.chart?action=USER_ACTOR'">
							<input type="button" class="button" value="My tags" onClick="javascript:window.location='music.chart?action=USER_TAG'">
						</c:if>
					</section>
				</td>

				<td align="center" width="34%">
					<c:if test="${not empty user.login}">
						<p class="login">
							Hello, <b>${user.login}</b>!
						</p>
					</c:if>
				</td>

				<td align="right" width="33%">
					<c:choose>
						<c:when test="${empty user.login}">
							<section class="border">
								<input type="button" class="button" value="Login" onClick="javascript:window.location='login'">
								<input type="button" class="button" value="Registration" onClick="javascript:window.location='edit?action=USER'">
							</section>
						</c:when>

						<c:otherwise>
							<form method="POST" name="logoutForm" action="/web/logout">
								<section class="border">
									<button type="submit">Logout</button>
								</section>
							</form>

							<c:if test="${user.role eq 'admin'}">
								<section class="border">
									<input type="button" class="button" value="Edit" onClick="javascript:window.location='edit?action=TRACK'">
								</section>
							</c:if>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</body>
</html>