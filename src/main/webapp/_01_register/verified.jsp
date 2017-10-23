<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">

<c:if test="${empty param.verifiedResultNo}">
	<meta http-equiv="refresh" content="5; url=../index.jsp">
</c:if>
<title>驗證</title>

<script language="JavaScript">
	//先拿到目前的時間
	startday = new Date();
	clockStart = startday.getTime();

	function initStopwatch() {
		var myTime = new Date();
		var timeNow = myTime.getTime();
		var timeDiff = timeNow - clockStart; //現在的時間和起始的時間相減得到已經過的時間
		this.diffSecs = timeDiff
		/1000; // 除以1000就是秒
		return (this.diffSecs); //將差異秒數回傳給getSecs函數
	}
	function getSecs() {
		var mySecs = initStopwatch();
		var mySecs1 = "" + mySecs; //將數字變成文字
		mySecs1 = 5 - eval(mySecs1.substring(0, mySecs1.indexOf(".")))+ "秒後...頁面自動跳轉"; //算出倒數的時間
		document.form1.timespent.value = mySecs1; //將倒數時間顯示在頁面空隔中
		window.setTimeout('getSecs()', 1000); //每1秒重新執行一次
	}
</script>
</head>
<style = text/css>
input {
border: none;
text-align: center;
}

div {
	margin-top: 100px;
}

img {
	width: 200px;
}
</style>
<body onLoad="window.setTimeout('getSecs()',1)">
	<center>
		<div>
			<form name=form1>
				<c:if test="${!empty param.VerifiedResultOK}">
					<img src="../images/yes1.png">
				</c:if>
				<c:if test="${!empty param.VerifiedResultNO}">
					<img src="../images/no1.png">
				</c:if>
				<h1>${param.VerifiedResultOK}</h1>
				<h1>${param.VerifiedResultNO}</h1>
				<h4>
					<input name=timespent>
				</h4>
			</form>
		</div>
	</center>

	<c:if test="${param.type == 'reverified'}">
${param.message}
</c:if>

</body>
</html>