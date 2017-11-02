<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<link rel="stylesheet" type="text/css" href="../css/query.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/js.cookie.js"></script>
<c:if test="${empty LoginOK}">
	<c:redirect url="/_00_login/Login.jsp" />
</c:if>

<jsp:useBean id="updateBeans" class="_06_query.model.Update_DAO"
	scope="page" />
<title>訂單列表</title>
</head>
<body background="../images/nano_nastasia-324812.jpg">

	<c:set var="funcName" value="ORD" scope="session" />

	<nav id="navMenu"
		class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>

	<div class="left-box">
		<div class="left-text-describe">
			<a href="/WimeProject/_06_query/query.jsp">會員資料查詢及修改</a>
		</div>
		<div class="left-text-describe">
			<a href="/WimeProject/_06_query/queryOrder.jsp">歷史訂單查詢</a>
		</div>
	</div>

	<div class="right-box-center">
		<div class="right-box">
			<div>
				<div>

					<form method="POST" action="<c:url value='/_06_query/update.do' />"
						id="/_06_query/update.do">
						<div class="query-zone">
							<p class="sm-title text-center">${LoginOK.name}的會員資料</p>
							<div class="updateText">${ErrorMsgKey.sucessSave}${ErrorMsgKey.failSave}</div>

							<c:set value="${LoginOK.mail}" target="${updateBeans}"
								property="mail" />
							<c:forEach var="aBean" items="${updateBeans.memberData}">

								<label class="text-describe">使用者代碼：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorMemberId}</font>
								<div class="query-text">${aBean.id}</div>

								<label class="text-describe">使用者姓名：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorName}</font>
								<input type="text" name="name" class="input-yellow"
									value="${aBean.name}">

								<c:set value="${aBean.mail}" target="${updateBeans}"
									property="mail" />
								<label class="text-describe">使用者郵件：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorEmail}</font>
								<div class="query-text">${aBean.mail}</div>


								<label class="text-describe">使用者地址：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorAddress}</font>
								<input type="text" class="input-yellow" name="address"
									value="${aBean.address}">



								<label class="text-describe">使用者電話：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorTel}</font>
								<input type="text" name="phone" class="input-yellow"
									value="${aBean.phone}">

								<label class="text-describe">使用者手機：</label>
								<font color="red" size="-1">${ErrorMsgKey.errorMob}</font>
								<input type="text" name="mobile" class="input-yellow"
									value="${aBean.mobile}">

							</c:forEach>


							<br>

							<div id="btnArea" align="center">
								<input class="btnyellow" type="submit" name="submit" id="submit"
									value="確認修改" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

<!-- 	<div class="bg-full">
		<img src="../images/nano_nastasia-324812.jpg">
	</div> -->
	<script type="text/javascript" src="../js/nav.js"></script>
</body>
</html>