<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="news" class="_08_news.model.News_DAO" scope="page" />

<head>
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
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<link rel="stylesheet" type="text/css" href="../css/newsList.css">

<meta charset="UTF-8">
<title>喵新聞</title>
</head>

<body>
	<!--nav-->
	<nav id="navMenu"
		class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>

	<!--===============banner===============-->
	<div id="carousel-example-generic" class="carousel slide banner"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<div class="banner-img-box">
					<a href="news1.html"><img src="../images/article1_1.jpg"></a>

					<div class="banner-title">貓咪的左右撇子原因是？你不知道的8個貓咪小知識</div>
				</div>
			</div>
			<div class="item">
				<div class="banner-img-box">
					<a href="news2.html"><img src="../images/article2_1.jpg"></a>

					<div class="banner-title">《貓後腳寫真集》繼貓手手之後又一特寫喵部位的毛呼呼寫真集❤</div>
				</div>
			</div>
			<div class="item">
				<div class="banner-img-box">
					<a href="#"><img src="../images/article3_1.jpg"></a>

					<div class="banner-title">《Qoobo貓尾抱枕》尾巴會動帶給你前所未有的療癒</div>
				</div>
			</div>
			............
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div class="yellow-box"></div>


	<!--===============content-box===============-->
	<div class="content-box">
		<!--left-box-->
		<div class="left-box">
			<c:forEach items="${news.select_All}" var="newsContent"
				varStatus="status">
				<div class="article-box">
					<div class="imgOL">
						<a href="#"><img src="${newsContent.picloc}" alt=""></a>
					</div>
					<div class="article-content">
						<div class="article-title">${newsContent.title }</div>
						<div class="article">${newsContent.article}</div>
						<div class="article-info">${newsContent.arDate}
							${newsContent.creater}</div>
						<div class="article-btn-area">
							<a href="${newsContent.articleLOC }" class="read-more"> read
								more>> </a>
						</div>
					</div>
				</div>

			</c:forEach>



			<div class="pages">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>

		</div>
		<!-- end left-box -->

		<!--right-box-->
		<div class="right-box">
			<a href="https://shop.maoup.com.tw/" target="_blank"><div
					class="imgAD">
					<img src="../images/11407557143627492953.gif" alt="">
				</div></a>

		</div>
		<!-- end right-box -->

	</div>
	<!-- end content-box -->



	<!--===============footer===============-->
	<div class="footer">
		<div class="footer-content">
			<div class="footer-text">即使不在毛孩身邊，也能隨時寵愛牠!</div>
			<a href="#"> 立刻購買 </a>
		</div>

	</div>


	<script type="text/javascript" src="../js/nav.js"></script>
	<script src="../js/newsList.js"></script>
	<script src="../js/jquery-3.2.1.min.js"></script>
</body>

</html>
