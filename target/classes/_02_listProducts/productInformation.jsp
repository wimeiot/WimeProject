<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet"
	href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/listbooks.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">

<script></script>
<title>商品介紹</title>
</head>
<body>
	<!-- 引入共同的頁首 -->
<nav id="navMenu" class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	

	<div class="scroll">
		<div class="just-all">
			
			<!--產品圖片，介紹圖片，QRcode-->
			<div class="all-img">
			<div class="pervious-step">
				<%-- 			<A href="<c:url value='../_03_listBooks/DisplayPageProducts?pageNo=${param.pageNo}' />">上一步， 繼續購物</A> --%>
				<Input type="button" class="btn-yellow"
					onclick="self.location.href='../_02_listProducts/DisplayPageProducts?pageNo=${param.pageNo}'"
					value="<<上一步， 繼續購物"/>
			</div>
				<div class="bg-img">
					<div class="bigImg">
						<img
							src='${pageContext.servletContext.contextPath}/_00_init/getImage?id=${param.id}&type=product'>
					</div>
					<div class="qrCodeIcon">
						<div class="qrCodeIconAnd">
							<img src="../images/QRcode_image.svg(1).png" /> <img
								src="../images/and1.png" />
						</div>
						<div class="qrCodeIconIos">
							<img src="../images/QRcode_image.svg.png" /> <img
								src="../images/ios.png" />
						</div>
					</div>
				</div>
				<div class="col-md-8 col-xs-12 right-box center-box">
					<div class="box clearFloat">
						<FORM action="<c:url value='BuyProduct.do' />" method="POST">

							<!--點圖片進入購物車 -->
							<a href="<c:url value='/_03_ShoppingCart/ShowCartContent.jsp' />">
								<div class="container">
									<c:if test="${not empty ShoppingCart.itemNumber }">
										<div class="qty">
											<p>${ShoppingCart.itemNumber}</p>
										</div>
									</c:if>

									<div class="screen">

										<img src="../images/shopping-cart.png" width="45">

									</div>

								</div>
							</a>

							<div style="width: 450px">
								<font class="title">${param.name}</font>
							</div>
							<br>
							<div>
								<font class="title">網路價$${param.price}</font>
							</div>
							<div class="checkQty">
								<select name='qty' class="checkSelect">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select>
								<!--                這些隱藏欄位都會送到後端 -->
								<Input type='hidden' name='productID' value='${param.id}' />
								<P />
								<Input type='hidden' name='name' value='${param.name}' />
								<P />
								<Input type='hidden' name='desc' value='${param.desc}' />
								<P />
								<Input type='hidden' name='number' value='${param.number}' />
								<P />
								<Input type='hidden' name='price' value='${param.price}' />
								<P />
								<Input type='hidden' name='pageNo' value='${param.pageNo}' />
								<P />
								<Input type='hidden' name='a' value='${param.a}'>
								<P />
								<Input type='submit' value='加入購物車' class='btnyellow' />
							</div>
							<hr>

							<div class='serviceIcon'>
								<div>
									<img src="../images/furbo-freeshipping.png" width="70">
									<p>7天鑑賞期</p>
								</div>
								<div>
									<img src="../images/furbo-moneyback.png" width="70">
									<p>免運費</p>
								</div>
								<div>
									<img src="../images/furbo-warranty.png" width="70">
									<p>1年保固</p>
								</div>
							</div>
							<hr>
							<div>注意事項：</div>
							<ol style="color: #888888;">
								<li>家中需有無線網路，建議上傳速度至少1 Mbps。</li>
								<li>Furbo 目前支援iOS 和 Android系統，智慧型手機型號需為iOS 8 / Android
									4.3以上。</li>
								<li>Furbo不含電池，使用時需要隨時插電。</li>
								<li>目前只寄送至台灣本島。</li>
								<li>本網站只提供信用卡付款。</li>
							</ol>

						</FORM>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>



		</div>

		<div class="intr-img1">
			<div class="intr-float"></div>
			<img src="../images/paul-273388.jpg" />
			<div class="gradiant"></div>
			<div class="intr-font">
				<font style="font-size: 40px; font-weight: bold;">趣味遠端互動<br>
					把握喵喵生命的每一刻
				</font><br> <font style="font-size: 30px; padding-left: 10px;">透過獸醫與寵物訓練師建議，<br>
				</font> <font style="font-size: 30px; padding-left: 10px;">
					把握喵喵生命的每一刻 </font>
			</div>
		</div>
		<div class="intr-img2">
			<div class="intr-text1">
				<font style="font-size: 30px;"> 寵物訓練師:
					「當狗狗獨自在家時，遠端互動能有效安撫和訓練狗狗!」 </font>
			</div>
			<div class="intr-text2">
				<font style="font-size: 40px; font-weight: bold;"> 吠叫通知
					掌握突發狀況 </font><br> <br> <font style="font-size: 25px;">
					Furbo
					能即時偵測狗叫聲，並透過手機推播馬上通知你！不論狗寶貝為什麼凹嗚～你都能隨時連回家中檢查，還能依照狗狗個性，自訂偵測敏感度，出門在外也能隨時掌握突發狀況。
				</font>
			</div>
			<img src="../images/product.png" />
			<div style="clear: both;"></div>
			<div class="bottom-text">
				<font style="font-size: 35px; font-weight: bold;">
					即使不在毛孩身旁，也能隨時寵愛他! </font>
			</div>

		</div>


	</div>




	<script type="text/javascript" src="../js/nav.js"></script>
</body>
</html>