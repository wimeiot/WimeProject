<%@ page buffer="16kb" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript --><!-- Bootstrap CSS -->
<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/normalize.css">

<!-- 自己的 -->
<link rel="stylesheet" type="text/css" href="../css/share.css">
<link rel="stylesheet" type="text/css" href="../css/nav.css">
<link rel="stylesheet" type="text/css" href="../css/register.css">
<script src="../js/register.js"></script>

<title>加入會員</title>
</head>

<body>
	<!-- 引入共同的頁首 -->
<%-- 	<jsp:include page="/fragment/top.jsp" /> --%>
<nav id="navMenu" 
	class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
	<div class="row clearFloat">
<!-- overFlow --- Making a div vertically scrollable using CSS -->
	<div class="col-md-8 col-xs-12 right-box center-box" 
				style="overflow-y:scroll">
			<div class="box clearFloat">
				<p class="sm-title text-center">註冊</p><br>
						
						<input type="email" name="email" id="email" class="input-yellow" 
							placeholder="請輸入email" aria-describedby="basic-addon1" 
							value="${param.mailId}">
							<a href='#' id='accountlink'style='font-size: 15px;'>檢查email</a>
							<div id="resultE" class="tx-error" style="height:30px;">${MsgMap.errorEmail}
							${MsgMap.errorEmailFormat}</div> 
							

						<input type="password" name="password1" id="password1" class="input-yellow" 
							placeholder="密碼" aria-describedby="basic-addon1" 
							maxlength="14"
							value="${param.password}"> 
							<div id="resultP1" class="tx-error" style="height:30px;">${MsgMap.errorPassword1}
							${MsgMap.errorPasswordEmpty1}</div>
					
						<input type="password" name="password2" id="password2" class="input-yellow"
							placeholder="密碼確認" aria-describedby="basic-addon1" 
							maxlength="14"
							 value="${param.password}">
							 <div id="resultP2" class="tx-error" style="height:30px;">${MsgMap.errorPassword2}
							${MsgMap.errorPasswordEmpty2}</div>
					
						<input type="text" name="name" id="name" class="input-yellow"
							placeholder="姓名" aria-describedby="basic-addon1" 
							maxlength="10"
							  value="${param.name}">
							<div id="resultN" class="tx-error" style="height:30px;">${MsgMap.errorName}</div>
							
						<div class="form-group">
						<input type="radio" name="gender" id="inlineRadio1"
						 value="M"><font size="4" color="#fff">男</font> 
						<input type="radio" name="gender" id="inlineRadio2"
						value="F"><font size="4" color="#fff">女</font></div>
							<div id="resultG" class="tx-error" style="height:30px;">${MsgMap.errorGender}</div>
						
						<input type="text" name="birthday" id="birthday" 
							onfocus="this.type='date'" onblur="this.type='text'"
							placeholder="生日" min="1890-01-01" max="2017-09-08"
							aria-describedby="basic-addon1"
							value="${param.birthday}" class="input-yellow">
							<div id="resultB"
							class="tx-error" style="height:30px;">${MsgMap.errorBirthday}</div>
					
						<input type="text" name="phone" id="phone" class="input-yellow" 
							placeholder="室內電話" aria-describedby="basic-addon1"
							maxlength="10"
							value="${param.phone}">
							<div id="resultPH"
							class="tx-error" style="height:30px;">${MsgMap.errorPhone}</div>
					
						<input type="text" name="mobile" id="mobile" class="input-yellow"
							placeholder="手機" aria-describedby="basic-addon1"
							maxlength="10"
							value="${param.mobile}">
							<div id="resultMO"
							class="tx-error" style="height:30px;">${MsgMap.errorMobile}</div>
					
						<input type="text" name="address" id="address" class="input-yellow" 
							placeholder="地址" aria-describedby="basic-addon1"
							maxlength="25"
							value="${param.address}">
							<div id="resultA"
							class="tx-error" style="height:30px;">${MsgMap.errorAddress}</div>
					<input type="checkbox" name="hide" id="hide" Style="border: none"
					value="check" onclick="hidepassword()"><font size="4" color="#fff">顯示/隱藏密碼</font>

					<div class="submitGroup">
					
						<button type="reset" name="reset" class="btnyellow"
							id="reset" onclick="reset()" style="width:85px;">reset</button>
						 	
						<button type="submit" name="submit" class="btnyellow"
							id="submit" style="width:200px;margin-left:10px;">送出</button>
						
						<font id="errorIDDup" color='red'>${MsgMap.errorIDDup}</font>
					</div>
				<a name="submit" class="font-gray" id="help" onclick="printHelps()"
				target="_blank" style='border-right: 1px solid #95989A'>小幫手</a>
				<p class="font-gray">已經是會員嗎？請
					<a style="color:#FFDA44;" href="../_00_login/Login.jsp">登入</a>
				</p>
				
				</div>
				</div>
				</div>
			<div class="bg-img">
		<img src="../images/dariusz-sankowski-46480.jpg">
	</div>
	<script type="text/javascript" src="../js/nav.js"></script>

</body>

</html>