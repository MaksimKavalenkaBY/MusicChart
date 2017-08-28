<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Track Body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript" src="/web/js/swfobject.js"></script>
		<script type="text/javascript" src="/web/js/player.js"></script>
		<script type="text/javascript" src="/web/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="/web/js/scroll.js"></script>
		<script>
			$(document).ready(function() {
				$('.likebutton').click(function() {
					var id = $(this).attr('data-id');
					var trackName = $(this).attr('data-track');

					$.ajax({
						type: "POST",
						url: "edit?Name="+trackName+"&action=USER_TRACK",
						dataType: "html",
						success: function(response) {
							$("#rating"+id).html(response);
				        }
					});
				});
			});
		</script>
	</head>

	<body>
		<form method="GET" action="/web/music.chart">
			<input type="hidden" name="action" value="${action}">
			<input type="hidden" name="info" value="${info}">
			Page: <input type="number" name="page" size="2" maxlength="2">
		</form>

		<div class="head">
			${data}
		</div>

		<c:choose>
			<c:when test="${empty Track}">
				<b class="error">
					Unfortunately, data are not found :(
				</b>
			</c:when>

			<c:otherwise>
				<table class="body">
					<tr>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>

					<c:forEach var="track" items="${Track}" varStatus="counter">
						<td class="window">
							<div class="head">
								<c:if test="${not empty user.login}">
									<div id="button-like">
										<span class="likebutton" data-id="${counter.count}" data-track="${track.name}">
											<img id="add" src="/web/image/other/add.png" style="background-color:#FFFFFF" width="100%">
										</span>
									</div>
								</c:if>

								<div id="scroll${counter.count}" class="scroll">
									${track.name}
								</div>
								<iframe onLoad="setScroll(${counter.count})" style="display: none"></iframe>

								<div id="rating${counter.count}" class="rating">
									${track.rating}
								</div>
							</div>

							<div id="track${counter.count}Icon" class="trackIcon" onclick="GetPlayer(${counter.count},'${track.name}','${track.url}')">
    							<img src="${track.image}" width="100%">
							</div>
						</td>

						<c:if test="${counter.count % 5 == 0}">
							<tr>
						</c:if>
     				</c:forEach>

				</table>

				<div id="track" class="footer">
					<div id="trackPlayer"></div>
				</div>
			</c:otherwise>
		</c:choose>
	</body>
</html>