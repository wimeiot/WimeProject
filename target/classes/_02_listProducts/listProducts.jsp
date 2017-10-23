<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="paBean" class="_02_listProducts.model.Product_Bean" />
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
<link rel="stylesheet" type="text/css" href="../css/listBooksAll.css">
<!-- <link rel="stylesheet" type="text/css" href="../css/nav.css"> -->



<title>顯示商品資訊</title>
</head>

<body>
<nav id="navMenu" class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	<!-- 下列敘述設定變數funcName的值為SHO，top.jsp 會用到此變數 -->
<%-- 		<c:set var="funcName" value="SHO" scope="session" /> --%>
	<!-- 引入共同的頁首 -->
	<%-- 	<jsp:include page="/fragment/top.jsp" /> --%>

	<!--section-->
	<div class="section">
		<!--top-img-->
		<div class="top-img">
			<img src="../images/wime_item_pic.png">
			<!--cart-img-->
			<div class="cart-img">
				<!--點圖片進入購物車 -->
				<!--<a href="<c:url value='/_04_ShoppingCart/ShowCartContent.jsp' />">-->
				<a href="<c:url value='/_03_ShoppingCart/ShowCartContent.jsp' />">
					<div class="cart-container">
						<div class="cart-screen">
							<img src="../images/shopping-cart.png">
						</div>
						<!--<c:if test="${not empty ShoppingCart.itemNumber}">-->
						<div class="cart-qty">
							<c:if test="${not empty ShoppingCart.itemNumber }">
								<div class="qty">
									<p>${ShoppingCart.itemNumber}</p>
								</div>
							</c:if>
						</div>
						<!--</c:if>-->
					</div>
				</a>
			</div>
			<!--/.cart-img-->
		</div>
		<!--/.top-img-->
		
			<c:forEach varStatus="stVar" var="ProductBean"
				items="${products_DPP}">
				<!--article-->
				<div class="article">
					<div class="article-inner">

						<div class="product-img">
							<a href="../_02_listProducts/productInformation.jsp?id=${ProductBean.productID}&name=${ProductBean.name}&price=${Product_Bean.price}&a=a">
								<img	
								src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${ProductBean.productID}&type=product'>
							</a>
						</div>
						<div class="product-content">
							<div class="content-container">
								<div class="product-name">
									<p>
										<a
											href="../_02_listProducts/productInformation.jsp?id=${ProductBean.productID}&name=${ProductBean.name}
                    &price=${ProductBean.price}&a=a">商品名稱：${ProductBean.name}</a>
									</p>
								</div>
								<div class="product-desc">
									Furbo是一台智慧寵物攝影機，讓你能隨時觀看、和毛孩說話，還能丟零食遠端互動！即使拔麻不在家，毛孩還是能透過Furbo感受你滿滿的愛！
								</div>

							</div>
							<!--/.content-container-->
						</div>
						<!--/.product-content-->
						<div class="product-add">
							<div class="add-container">
								<div class="price-container">
									<div class="unit-price">
										<p>網路價</p>
										<font>$${ProductBean.price}</font>
									</div>
								</div>
								
							</div>
							<!--/.add-container-->
						</div>
						<!--/.product-add-->

					</div>
					<!--/.article-inner-->
					<hr>
				</div>
				<!--/.article-->
			</c:forEach>
		</div>
	<!--/.section-->
		<table border="1">
			<tr>
				<td width='76'><c:if test="${pageNo > 1}">
						<div id="pfirst">
							<a href="<c:url value='DisplayPageProducts?pageNo=1' />">第一頁</a>&nbsp;&nbsp;&nbsp;
						</div>
					</c:if></td>
				<td width='76'><c:if test="${pageNo > 1}">
						<div id="pprev">
							<a href="<c:url value='DisplayPageProducts?pageNo=${pageNo-1}' />">上一頁</a>&nbsp;&nbsp;&nbsp;
						</div>
					</c:if></td>
				<td width='76'><c:if test="${pageNo != totalPages}">
						<div id="pnext">
							<a href="<c:url value='DisplayPageProducts?pageNo=${pageNo+1}' />">下一頁</a>&nbsp;&nbsp;&nbsp;
						</div>
					</c:if></td>
				<td width='76'><c:if test="${pageNo != totalPages}">
						<div id="plast">
							<a
								href="<c:url value='DisplayPageProducts?pageNo=${totalPages}' />">最末頁</a>&nbsp;&nbsp;&nbsp;
						</div>
					</c:if></td>
				<td width='176' align="center">第${pageNo}頁 / 共${totalPages}頁</td>
			</tr>
		</table>

	<!-- nav.js -->
	<script type="text/javascript" src="../js/nav.js"></script>
	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
