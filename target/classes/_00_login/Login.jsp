<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/Login.css"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.onload = function() {
		var login = document.getElementById('login');

		// 		ip_email.onblur = function(){
		// 			if (!email) {
		// 				email_error.innerHTML = "Email欄位不可以空白喔!(js)";
		// 				psw_error.innerHTML = "";
		// 				return;
		// 			}

		// 		}

		// 		ip_psw.onblur = function(){
		// 			if (!psw) {
		// 				psw_error.innerHTML = "密碼欄位不能空白喔!(js)";
		// 				email_error.innerHTML = "";
		// 				return;
		// 			}
		// 		}

		login.onclick = function() {
			var ip_email = document.getElementById('email');
			var ip_psw = document.getElementById('psw');
			var email = document.getElementById('email').value;
			var psw = document.getElementById('psw').value;
			var email_error = document.getElementById('email_error');
			var psw_error = document.getElementById('psw_error');
			var check_error = document.getElementById('check_error');
			var content = document.getElementById('content');
			/*===================== 前端處理 =====================*/
			// 			
			// 				
			/*===================== 後端處理 =====================*/
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "Login.do", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.send("email=" + email + "&psw=" + psw);

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					console.log(xhr.responseText);
					var result = JSON.parse(xhr.responseText);

					if (result.EmailEmptyError) {
						email_error.innerHTML = result.EmailEmptyError;
						psw_error.innerHTML = "";
						check_error.innerHTML = "";
						console.log('EmailEmpty check');
					} else if (result.PasswordEmptyError) {
						psw_error.innerHTML = result.PasswordEmptyError;
						email_error.innerHTML = "";
						check_error.innerHTML = "";
						console.log('PswEmpty check');
					} else if (result.LoginError) {
						check_error.innerHTML = result.LoginError;
						psw_error.innerHTML = "";
						email_error.innerHTML = "";
						console.log('LoginError check');
					} else if (result.success) {/*======== 成功，換字 ========*/
						console.log('content.innerHTML check');
						window.location = result.success;

					}
				}
			}
		}
	}
</script>
<title>登入</title>
</head>
<body>
	<nav id="navMenu"
		class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	<c:if test="${! empty sessionScope.timeOut }">
		<c:set var="msg"> ${sessionScope.timeOut}</c:set>
	</c:if>
	<div class="row clearFloat" id="content">

		<!--        <div class="col-md-8"></div>-->
		<div class="col-md-8 col-xs-12 right-box center-box">
			<div class="box clearFloat">
				<p class="sm-title text-center">登入</p>

				<input type="text" name="email" id="email" class="input-yellow"
					placeholder="請輸入email" aria-describedby="basic-addon1"
					value="${sessionScope.email}"><br>
				<div id="email_error" class="tx-error"></div>

				<input type="password" name="psw" id="psw" class="input-yellow"
					placeholder="請輸入密碼" aria-describedby="basic-addon1"
					value="${sessionScope.psw}"><br>
				<div id="psw_error" class="tx-error"></div>

				<input type="checkbox" name="rememberMe" value="true"


					<c:if test='${sessionScope.rememberMe==true}'>
					checked='checked'</c:if>>


					<span class="tx-gray">記得我</span><br>

				<div id="check_error" class="tx-error"></div>
				<div class="btnyellow" name="login" id="login">登入</div>

				<a class="font-gray" href="forgot_psw.jsp" target="_blank"
					style='border-right: 1px solid #95989A'>忘記帳號密碼嗎？</a>
				<a class="font-gray" href="../_01_register/register.jsp">還不是會員嗎？快去註冊</a><br>
				<a onclick="assistant()">小幫手</a>
			</div>
		</div>
	</div>
	<div class="bg-full">
		<img src="../images/nano_nastasia-324812.jpg">
	</div>

	<script type="text/javascript" src="../js/nav.js"></script>
	<script type="text/javascript">
		function assistant(){
			document.getElementById("email").value = 'kingfafa8520@gmail.com';
			document.getElementById("psw").value = 'abc1234';
			}
	</script>

</body>
</html>