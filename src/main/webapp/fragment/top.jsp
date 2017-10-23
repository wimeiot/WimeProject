<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style = text/css>
#fix{
margin-top: 10px;
}
</style>
<body>
<center>
	<table class='menuInner' id="fix" BORDER='1' BGCOLOR='#F7FE2E'>
		<tr>
			<td class='menuInner'>
				<div class='menu'>
				<a href=" <c:url value='/index.jsp'/> " style="text-decoration:none">
				首頁
				</a>
				</div> 
			</td>
			<td class='menuInner'>
				<div class='menu'>
				<a href="" style="text-decoration:none">
				產品介紹
				</a>
				</div> 
			</td>
		
			<td class='menuInner'>
				<div class='menu'>
				<a href="" style="text-decoration:none">
				關於公司
				</a>
				</div> 
			</td>
		
			<td class='menuInner'>
				<div class='menu'>
				<a href="<c:url value='/_05_respond/respond.jsp'/>" style="text-decoration:none">
				客戶服務
				</a>
				</div> 
			</td>
			
			<td class='menuInner'>
				<div class='menu'>
				<a href=" <c:url value='/_00_login/Login.jsp'/> " style="text-decoration:none">
				登入
				</a>
				</div> 
			</td>
			
			<td class='menuInner'>
				<div class='menu'>
				<a href=" <c:url value='/_06_query/query.jsp'/> " style="text-decoration:none">
				查詢及修改個人資料
				</a>
				</div> 
			</td>
		
			<td class='menuInner'>
				<div class='menu'>
				<a href=" <c:url value='/_01_register/register.jsp'/> " style="text-decoration:none">
				註冊
				</a>
				</div> 
			</td>
		</tr>
		
	</table>
</center>
</body>
</html>