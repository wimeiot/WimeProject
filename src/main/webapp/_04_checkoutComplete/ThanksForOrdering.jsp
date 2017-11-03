<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../css/normalize.css">

    <!-- 自己的 -->
    <link rel="stylesheet" type="text/css" href="../css/share.css">
    <link rel="stylesheet" type="text/css" href="../css/CheckUp.css">
    <link rel="stylesheet" type="text/css" href="../css/nav.css">
    <title>Document</title>
</head>
<body>
   <!--navbar-->
        <!-- 引入共同的頁首 -->
        <nav id="navMenu" class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
        <!--section-->
        <div class="section">
            <!--title-shopping-->
            <div class="title-shopping">
                <div class="title-container">
                   <div class="title-img">
                       <img src="../images/shopping-cart.png">
                   </div>
                    <div class="title-gray title"><p>購物車></p></div>
                    <div class="title-gray title"><p>確認結帳資料</p></div>
                    <div class="title-black title"><p>>結帳</p></div>
                </div><!--/.title-containe-->
                <hr>
            </div>
        <!--ariticle-->
           <div class="ariticle">
               <div class="ariticle-img">
                   <img src="../images/finished.png">
                   <div class="title-finshed">
                       <p class="sm-title">結帳成功!</p>
                   </div>
               </div>
               <div class="ariticle-btn">
               <fORM action="<c:url value='/_06_query/queryOrder.jsp' />" method="POST">
                   <input type="submit" value="檢視訂單" class="btn-yellow">
                   </fORM>
               </div>
           </div>
            </div><!--/.section-->
        
        
    <!-- nav.js -->
    <script type="text/javascript" src="../js/nav.js"></script>
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>