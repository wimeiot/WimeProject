<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
	function printHelp() {
		var email = document.getElementById("email").value = "wime5687183@gmail.com";
		var name = document.getElementById("name").value = "蔣希娜";
		//    var phone = document.getElementById("tel").value = "0980709606 or 0209090908"; 
		var ordno = document.getElementById("ordno").value = "1";
		var description = document.getElementById("description").value = "你好，我的貓跳台壞了 QQ 請問可以幫忙修理嗎？";

	}
</script>
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/respond.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">

<title>客戶服務</title>
</head>
<body>


	<!-- 引入共同的頁首 -->
	<%-- <jsp:include page="/fragment/top.jsp" /> --%>
	<nav id="navMenu"
		class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	<%-- 	<c:if test="${! empty sessionScope.timeOut }"> --%>
	<%-- 		<c:set var="msg"> ${sessionScope.timeOut}</c:set> --%>
	<%-- 	</c:if> --%>
	<div class="row clearFloat">

		<!--        <div class="col-md-8"></div>-->
		<div class="col-md-8 col-xs-12 right-box center-box">
			<div class="box clearFloat">
				<!-- 				<p class="sm-title text-center">客戶服務</p> -->

				<form class="emailbox center-box"
					action="<c:url value='respond.do' />" method="POST">

					<div class="emailbox">
						<ul>
							<h4>請說明您的問題</h4>
							<!--                   <li class="problems" ></li> -->
							<div class="form-group">
								<label for="email">Email信箱：</label> <input type="email"
									class="form-control" name="email" value="${param.mailId}"
									id="email" size="260">&nbsp;&nbsp;(必填)(我們將以此信箱回覆給您。) <small
									class="form-text text-muted">${MsgMap.errorEmail}
									${MsgMap.errorEmailFormat}</small>
							</div>

							<div class="form-group">
								<label for="name">姓名：</label> <input type="text"
									class="form-control" name="name" value="${param.name}"
									id="name" size="260">&nbsp;&nbsp; <small
									class="form-text text-muted">${MsgMap.errorName}</small>
							</div>

							<div class="form-group">
								<label for="ordno">訂單編號：</label> <input type="text"
									class="form-control" name="ordno" value="${param.ordno}"
									id="ordno" size="260">&nbsp;&nbsp;(若您欲詢問的問題與訂單有關，請您儘可能偍供。)
								<small class="form-text text-muted">${MsgMap.errorOrdno}</small>
							</div>
							<div class="form-group">
								<span class="email">問題類型：</span> <select name="question"
									id="question">
									<option value="進度查詢" selected>進度查詢</option>
									<option value="退換貨">退換貨</option>
									<option value="其他">其他</option>
								</select>
								<!-- (請簡述說明您反應的問題，200字以內。) -->
							</div>
							<div class="form-group">
								<span class="email">問題說明：</span>(請簡述說明您反應的問題，200字以內。)<span
									id="alert_wd"></span>
							</div>

						</ul>

						<div class="mailIcon">
							<textarea id="description" name="description" cols="75" rows="7"
								class="textareainner"></textarea>
							<BR> <input type="hidden" name="message"
								value="${param.message}" id="message"> <small
								class="form-text text-muted">${MsgMap.errorMessage}</small>
						</div>

						<div>
							<br> <input class="btn" type="reset" value="重新填寫"
								name="again"> <input class="btn" type="submit"
								value="正確送出" name="send">
						</div>

						<div class="fixSubmit">
							<a name="submit" class="btn btn-primary"
								id="help" onclick="printHelp()">小幫手</a>
						</div>

						<br> <font color='red'>${MsgMap.errorIDDup}</font> <a
							href="https://drive.google.com/open?id=0B89IcPcdO7OBSGVacEdQR2RBQ0k">
							相關下載 </a>

					</div>

				</form>

			</div>

		</div>
	</div>
	<div class="bg-full">
		<img src="../images/00001.jpg">
	</div>
	<script type="text/javascript" src="../js/nav.js"></script>
	<script src="../js/js.cookie.js"></script>
	<script>
		window.onload = function() {
			login = Cookies.get('login');
			if (login = "ok") {
				document.getElementById("lgc").innerHTML = "登出";
			}
		}
	</script>

</body>
</html>