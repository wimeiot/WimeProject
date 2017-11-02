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
<link rel="stylesheet" type="text/css" href="../css/queryOrder.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/js.cookie.js"></script>
<c:if test="${empty LoginOK}">
	<c:redirect url="/_00_login/Login.jsp" />
</c:if>

<jsp:useBean id="updateBeans" class="_06_query.model.Update_DAO"
	scope="page" />
<jsp:useBean id="orderBeans" class="_03_ShoppingCart.model.Order_DAO"
	scope="page" />
<title>訂單列表</title>
</head>
<body background="../images/queryOrder_BG.jpg">
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

	<!-- 	<div class="right-box-center"> -->
	<div class="right-box">
		<!-- 	<span class="sm-title text-center orderTable"> 歷史訂購紀錄 </span> -->
		<table class="sm-title text-center orderTable">
			<tr>
				<th colspan="4" class="orderTitle">歷史訂購紀錄</th>
			</tr>

			<tr>
				<th class="orderText">訂單編號</th>
				<th class="orderText">訂購日期</th>
				<th class="orderText">總金額</th>
				<th class="orderText">送貨地址</th>
			</tr>
			<c:forEach var="anOrderBean" varStatus="stat"
				items="${orderBeans.allOrders}">

				<c:if test="${anOrderBean.cmId==LoginOK.id}">

					<TR class="orderStatus">
						<TD width="86" align="center"><a
							href='<c:url value='orderDetail.do?Id=${LoginOK.id}&ordId=${anOrderBean.ordId}' />'
							class="ordno"> ${anOrderBean.ordId} </a></TD>
						<TD width="100" class="orderDate">${anOrderBean.ordDateText}</TD>
						<TD width="80" class="orderTotal">${anOrderBean.totalPrice}</TD>
						<TD width="400" class="orderDest">${anOrderBean.receiverAddress}</TD>
						<!-- 						<TD width="100"><a href='' class="ordno">001</a></TD>
						<TD width="100">2017/10/18</TD>
						<TD width="100">$5566</TD>
						<TD width="400" class="address">全家就是你家</TD> -->
					</TR>
				</c:if>



			</c:forEach>
		</TABLE>
	</div>
	<!-- 	</div> -->

	<!-- 	<div class="bg-full"> -->
	<!-- 		<img src="../images/queryOrder_BG.jpg"> -->
	<!-- 	</div> -->
	<script type="text/javascript" src="../js/nav.js"></script>
</body>
</html>