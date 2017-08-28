<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<link rel="SHORTCUT ICON" href="/web/image/other/MusicChartIcon.png" type="image/x-icon">
	</head>

	<body>
		<form method="POST" name="loginForm" action="/web/login">
			<input type=hidden name="action" value="LOGIN">

			<table class="form">
				<tr>
					<td></td>
					<td>Enter your login and password</td>
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
			</table>

			<input class="agree" type="submit" value="Login">
			<c:import url="../other/buttons.jsp"/>
		</form>
	</body>
</html>