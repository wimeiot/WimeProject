<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/Login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.onload = function() {
		var login = document.getElementById('login');
		
		var ip_email = document.getElementById('email');
		var ip_psw = document.getElementById('psw');
		var email = document.getElementById('email').value;
		var psw = document.getElementById('psw').value;
		var email_error = document.getElementById('email_error');
		var psw_error = document.getElementById('psw_error');
		var check_error = document.getElementById('check_error');
		
		ip_email.onblur = function(){
			if (!email) {
				email_error.innerHTML = "Email欄位不可以空白喔!(js)";
				psw_error.innerHTML = "";
				return;
			}
			//因為你沒有擋住請求 所以email_error.innerHTML(js)被蓋掉了 			
		}
				 
		ip_psw.onblur = function(){
			if (!psw) {
				psw_error.innerHTML = "密碼欄位不能空白喔!(js)";
				email_error.innerHTML = "";
				return;
			}
		}

		
		login.onclick = function() {
			
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
					} else {/*======== 成功，換字 ========*/
						console.log('content.innerHTML check');
						content.innerHTML = result.success;

					}
				}
			}
		}
	}
</script>
<title>Login</title>
</head>
<body>
	<div id="bg_black">
		<div id="bg_box">
			<%-- 	<c:set var="funcName" vlaue="LOG" scop="session" /> --%>
			<c:set var="msg" value="登入" />
			<c:if test="${! empty sessionScope.timeOut }">
				<c:set var="msg"> ${sessionScope.timeOut}</c:set>
			</c:if>

			<div id="content">
				<h2>${msg}</h2>
				<input type="text" name="email" id="email" placeholder="註冊email"
					value="${sessionScope.email}"><br>
				<div id="email_error"></div>
				<br> <input type="password" name="psw" id="psw" placeholder="密碼"
					value="${sessionScope.psw}"><br>
				<div id="psw_error"></div>

				<br> <input type="checkbox" name="rememberMe" value="true"
					<c:if test='${sessionScope.rememberMe==true}'>
					checked='checked'
					</c:if>>記得我
				<br>


				<div id="check_error"></div>
				<input type="submit" name="login" id="login" value="登入"><br>
				<a href="forgot_psw.jsp" target="_blank">忘記帳號密碼嗎?</a><br> <a
					href="http://localhost:8080/wimeProject3Ajax/_01_register/register.jsp">註冊</a><br>
			</div>
		</div>
	</div>

</body>
</html>