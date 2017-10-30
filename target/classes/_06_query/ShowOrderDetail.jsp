<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty LoginOK}">
	<c:redirect url="/_00_login/login.jsp" />
</c:if>
<title>個人訂單明細</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}
</style>
</head>
<body background="../images/queryOrder_BG.jpg" style="color: white;">

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

	<div class="right-box">

		<div class="backButton">
			<a href="<c:url value='queryOrder.jsp' />"> ＜ 回上一頁 </a>
		</div>
		<div class="detailTable">
			<div class="orderTitle">商品訂購明細</div>
			<table class="sm-title text-center orderDetailTable">
				<div class="orderDetailLine">
					<div>出貨地址：${OrderBean.receiverAddress}&nbsp;&nbsp;&nbsp;</div>
					<div>訂購日期：${OrderBean.ordDate}&nbsp;&nbsp;&nbsp;</div>
					<div>訂單編號：${OrderBean.ordId}</div>
				</div>
				<div class="orderDetailLineDetail">
					<div id="pdNumber">商品編號</div>
					<div id="pdName">商品資訊</div>
					<div id="pdUnitPrice">購買數量</div>
					<div id="pdAmount">單價</div>
					<div id="paTotal">總價</div>
				</div>
				<c:set var="subtotal" value="0" />
				<c:forEach var="aBean" varStatus="stat" items="${OrderBean.items}">

					<div class="orderData">
						<div class="productID">${aBean.productID}</div>
						<div class="productName">${aBean.productName}</div>
						<div class="amount">${aBean.amount}</div>
						<div class="unitPrice">${aBean.unitPrice}</div>
						<div class="total">${ aBean.unitPrice*aBean.amount }</div>

					</div>

				</c:forEach>
				<div class="totalAmount">
					總金額
					<fmt:formatNumber value="${OrderBean.totalPrice}"
						pattern="#,###,###" />
					元
				</div>

			</TABLE>

		</div>
	</div>
	<script type="text/javascript" src="../js/nav.js"></script>
</body>
</html>



