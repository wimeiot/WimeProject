<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function cancelOrder() {
		if (confirm("確定取消此份訂單 ? ")) {
			// 接收此資料的Servlet會使用 finalDecision 參數的值
			document.forms[0].finalDecision.value = "CANCEL";
			return true;
		} else {
			return false;
		}
	}
	function reconfirmOrder() {
		if (confirm("確定送出此份訂單 ? ")) {
			// 接收此資料的Servlet會使用 finalDecision 參數的值
			document.forms[0].finalDecision.value = "ORDER";
			return true;
		} else {
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/OrderConfirm.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<title>Document</title>
<jsp:useBean id="today" class="java.util.Date" scope="session" />
</head>

<body>
	<fORM action="<c:url value='ProcessOrder.do' />" method="POST">
		<!--navbar-->
		<!-- 引入共同的頁首 -->
		<nav id="navMenu"
			class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
		<!--section-->
		<div class="section">
			<!--title-shopping-->
			<div class="title-shopping">
				<div class="title-container">
					<div class="title-img">
						<img src="../images/shopping-cart.png">
					</div>
					<div class="title-gray title">
						<p>購物車></p>
					</div>
					<div class="title-black title">
						<p>確認結帳資料></p>
					</div>
					<div class="title-gray title">
						<p>結帳</p>
					</div>
				</div>
				<!--/.title-containe-->
				<hr>
			</div>
			<!--article-->
			<div class="ariticle-container">
				<div class="article-left">
					<div class="block-all">
						<div class="block-name-date">
							<div class="buyer-name">
								<p class="title-gray">會員姓名:</p>
								<p class="title-black">${LoginOK.name}</p>
							</div>
							<div class="order-date">
								<p class="title-gray">訂單日期:</p>
								<p class="title-black">
									<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss" />
								</p>
							</div>
						</div>
						<!--/.block-name-date-->
						<div class="buyer-address">
							<p class="title-gray">會員地址:</p>
							<p class="title-black">${LoginOK.address}</p>
						</div>
						<div class="receiver-name">
							<p class="title-gray">收貨姓名:</p>
							<input type="text" name="ReceiverName"
								value="${param.ReceiverName}" class="input-yellow">
							<div>${err.errorReceiverName}</div>
						</div>
						<div class="receiver-pnone">
							<p class="title-gray">聯絡電話:</p>
							<input type="text" name="ReceiverPhone"
								value="${param.ReceiverPhone}" class="input-yellow">
							<div>${err.errorReceiverPhone}</div>
						</div>
						<div class="receiver-address">
							<p class="title-gray">收貨地址:</p>
							<input type="text" name="ReceiverAddr"
								value="${param.ReceiverAddr}" class="input-yellow">
							<div>${err.errorReceiverAddr}</div>
						</div>
					</div>
					<!--/.block-all-->
				</div>
				<!--/.article-left-->
				<div class="article-right-bgc">
					<c:forEach varStatus="vs" var="anEntry"
						items="${ShoppingCart.content}">
						<div class="article-right">
							<div class="product">
								<div class="product-container">
									<div class="product-name sm-title">${anEntry.value.name}</div>
									<div class="unit-price sm-title">$${anEntry.value.price }</div>
									<div class="qty">
										<p class="sm-title title-gray">x</p><br>
										<!--                            <input type="text" value="2" class="input-yellow"-->
										<!--                            style="width:50px;text-align:center;">-->
										<p class="sm-title">${anEntry.value.qty}</p>
										<p class="sm-title title-gray">=</p>
									</div>
									<div class="total-price sm-title">$${anEntry.value.price * anEntry.value.qty}</div>

								</div>
								<!--/.product-container-->
							</div>
							<!--/.product-->
							<hr>
						</div>
						<!--/.article-right-->
					</c:forEach>
					
				</div>
				<!--/.article-right-bgc-->
			</div>
			<!--/.ariticle-container-->
		</div>
		<!--/.section-->

		<!--footer-->
		<div class="footer">
			<div class="fotter-container">
				<div class="pervious-step">
					<!--                     <Input type="button" class="btn-yellow" value="<<上一步，購物車" onclick=<c:url value='ShowCartContent.jsp' />/>     -->
					<a href="<c:url value='ShowCartContent.jsp' />" type="button"
						class="btn-yellow"> 上一步，購物車 </a>
				</div>
				<div class="final-price">
					<p></p>
				</div>
				<div class="final-price sm-title">
					<p style="font-size: 18px;">金額總計:</p>
					<p>$${ShoppingCart.subtotal + VAT }</p>
				</div>
				<div class="next-price">
					<Input type="SUBMIT" class="btn-yellow" value="下一步，結帳>>"
						onclick="return reconfirmOrder();" />
				</div>
			</div>
			<!--/.fotter-container-->
		</div>
		<!--/.footer-->
	</fORM>
	<!-- nav.js -->
	<script type="text/javascript" src="../js/nav.js"></script>
	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>