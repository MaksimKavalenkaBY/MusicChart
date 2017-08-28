<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Registration</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link rel="SHORTCUT ICON" href="/web/image/other/MusicChartIcon.png" type="image/x-icon">
	</head>

	<body>
		<form method="POST" name="registrationForm" action="/web/edit">
			<input type=hidden name="action" value="USER">

			<table class="form">
				<tr>
					<td></td>
					<td>Enter login and password</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<b class="error">${error}</b>
					</td>
				</tr>
				<tr>
					<td class="name">Login</td>
					<td><input type="text" name="Login" size="16" maxlength="16"></td>
				</tr>
				<tr>
					<td class="name">Password</td>
					<td><input type="password" name="Password" size="16" maxlength="16"></td>
				</tr>
				<tr>
					<td class="name">Repeat password</td>
					<td><input type="password" name="checkPassword" size="16" maxlength="16"></td>
				</tr>
			</table>

			<input class="agree" type="submit" value="Registration">
			<c:import url="../other/buttons.jsp"/>
		</form>
	</body>
</html>