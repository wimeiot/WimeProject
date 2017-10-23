<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
	response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/ShowCartContent.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=DEL&productID=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
	
	}
}
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='UpdateItem.do?cmd=MOD&productID=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物清單</title>
</head>
<body>
<%-- 	<c:set var="funcName" value="CHE" scope="session" /> --%>
	<!-- 引入共同的頁首 -->
	<nav id="navMenu"
		class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	<div class="section">
		<!--title-shopping-->
		<div class="title-shopping">
			<div class="title-container">
				<div class="title-img">
					<img src="../images/shopping-cart.png">
				</div>
				<div class="title-black title">
					<p>購物車></p>
				</div>
				<div class="title-gray title">
					<p>確認結帳資料>結帳</p>
				</div>
			</div>
			<!--/.title-containe-->
			<hr>
		</div>




		<c:choose>
			<c:when test="${ShoppingCart.subtotal > 0}">
				<c:set var="subtotalMessage" value="金額小計:${ShoppingCart.subtotal} 元" />
				<c:set var="subtotal" value="${ShoppingCart.subtotal}" />
			</c:when>
			<c:otherwise>
				<c:set var="subtotalMessage" value="金額小計:  0 元" />
				<c:set var="subtotal" value="0" />
			</c:otherwise>
		</c:choose>


		<p />





		<c:forEach varStatus="vs" var="anEntry"
			items="${ShoppingCart.content}">
			<div class="article">
				<div class="product">
					<div class="product-container">
						<div class="product-name sm-title"><p>${anEntry.value.name}</p></div>
						<div class="unit-price sm-title"><p>
							<fmt:formatNumber value="${anEntry.value.price}"
								pattern="#,###" />
							元
						</p></div>
						<div class="qty">

							<p class="sm-title">x</p>
							<Input id="newQty${vs.index}" class="input-yellow"
								style="width: 50px; text-align: center" name="newQty"
								type="text"
								value="<fmt:formatNumber value="${anEntry.value.qty}" />"
								name="qty" onkeypress="return isNumberKey(event)" />
							<p class="sm-title">=</p>
						</div>
						<div class="total-price sm-title">
							<fmt:formatNumber
								value="${anEntry.value.price * anEntry.value.qty}"
								pattern="#,###,###" />
							元
						</div>
						<div class="button-container">
							<div class="update-btn">
								<Input type="button" class="btn-yellow" name="update" value="修改"
									onClick="modify(${anEntry.key}, ${anEntry.value.qty}, ${vs.index})">
							</div>
							<div class="delete-btn">
								<Input type="button" class="btn-yellow" name="delete" value="刪除"
									onClick="confirmDelete(${anEntry.key})">
							</div>
						</div>
					</div>
					<!--/.product-container-->
				</div>
				<!--/.product-->
				<hr>
			</div>
			<!--/.article-->
		</c:forEach>
	</div>
	<!--/.section-->


	<div class="footer">
		<div class="fotter-container">
			<div class="pervious-step">
				<%-- 			<A href="<c:url value='../_03_listBooks/DisplayPageProducts?pageNo=${param.pageNo}' />">上一步， 繼續購物</A> --%>
				<Input type="button" class="btn-yellow"
					onclick="self.location.href='../_02_listProducts/DisplayPageProducts?pageNo=${param.pageNo}'"
					value="<<上一步， 繼續購物"/>
			</div>
			<div class="final-price">
				<p></p>
			</div>
			<div class="final-price sm-title">
				<p style="font-size: 18px;">金額總計:</p>
				<p>
					<fmt:formatNumber value="${subtotal}" pattern="#,###,###" />
					元
				</p>
			</div>
			<div class="next-price">
				<Input type="button" class="btn-yellow"
					onClick=" self.location.href='checkout.do'" value="下一步，確認結帳資料>>" />
			</div>
		</div>
		<!--/.fotter-container-->
	</div>
	<!--/.footer-->
<%-- 	<center> --%>
<!-- 		<TABLE> -->

<!-- 			<TR height='80'> -->
<!-- 				<TD> -->
<!-- 					<TABLE border='1'> -->
<!-- 						<TR> -->
<!-- 							<TD width="260" align='center'><A -->
<%-- 								href="<c:url value='../_03_listBooks/DisplayPageProducts?pageNo=${param.pageNo}' />">繼續購物</A> --%>
<!-- 							</TD> -->
<!-- 							<TD width="260" align='center'><A -->
<%-- 								href="<c:url value='checkout.do' />" --%>
<%-- 								onClick="return Checkout(${subtotal});">再次確認</A></TD> --%>
<!-- 							<TD width="260" align='center'><A -->
<%-- 								href="<c:url value='abort.do' />" onClick="return Abort();">放棄購物</A> --%>
<!-- 							</TD> -->
<!-- 						</TR> -->
<!-- 					</TABLE> -->
<!-- 				</TD> -->
<!-- 			</TR> -->
<!-- 		</TABLE> -->
<%-- 	</center> --%>

	<form>
		<input type="hidden" name="a" />
	</form>
	<!-- nav.js -->
	<script type="text/javascript" src="../js/nav.js"></script>
	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</body>
</html>