<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Actor Body</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="/web/css/style.css"/>
		<script type="text/javascript" src="/web/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="/web/js/scroll.js"></script>
		<script type="text/javascript">
			function setActor(actor) {
				document.actorForm.info.value = actor;
				document.actorForm.submit();
			}
		</script>
		<script>
			$(document).ready(function() {
				$('.likebutton').click(function() {
					var id = $(this).attr('data-id');
					var actorName = $(this).attr('data-actor');

					$.ajax({
						type: "POST",
						url: "edit?Name="+actorName+"&action=USER_ACTOR",
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
		<form method="GET" name="actorForm" action="/web/music.chart">
			<input type="hidden" name="action" value="TRACK_ACTOR">
			<input type="hidden" name="info" value="no">
		</form>
	
		<div class="head">
			${data}
		</div>

		<c:choose>
			<c:when test="${empty Actor}">
				<b class="error">
					Unfortunately, data are not found :(
				</b>
			</c:when>

			<c:otherwise>
				<table class="body">
					<tr>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>

					<c:forEach var="actor" items="${Actor}" varStatus="counter">
						<td class="window">
							<div class="head">
								<c:if test="${not empty user.login}">
									<div id="button-like">
										<span class="likebutton" data-id="${counter.count}" data-actor="${actor.name}">
											<img src="/web/image/other/add.png" style="background-color:#FFFFFF" width="100%">
										</span>
									</div>
								</c:if>

								<div id="scroll${counter.count}" class="scroll">
									${actor.name}
								</div>
								<iframe onLoad="setScroll(${counter.count})" style="display: none"></iframe>

								<div id="rating${counter.count}" class="rating">
									${actor.rating}
								</div>
							</div>

							<a class="title" href="JavaScript:setActor('${actor.name}')">
								<img src="${actor.image}" width="100%">
							</a>
						</td>

						<c:if test="${counter.count % 5 == 0}">
							<tr>
						</c:if>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>