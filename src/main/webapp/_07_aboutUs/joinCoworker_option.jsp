<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <!DOCTYPE html>
        <html lang="en">
        <jsp:useBean id="coworkerBean" class="_07_aboutUs.model.JoinCoworker_DAO" scope="page" />

        <head>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script src="../js/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
            <!-- Latest compiled JavaScript -->
            <script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
            <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" type="text/css" href="../css/normalize.css">

            <!-- 自己的 -->
            <link rel="stylesheet" type="text/css" href="../css/share.css">
            <link rel="stylesheet" type="text/css" href="../css/joinPartner.css">
            <link rel="stylesheet" type="text/css" href="../css/joinCoworker.css">
            <link rel="stylesheet" type="text/css" href="../css/nav.css">
            <link rel="stylesheet" type="text/css" href="../css/cat.css">
            <meta charset="UTF-8">
            <title>職缺列表</title>
        </head>

        <body>
            <nav id="navMenu" class="navbar navbar-inverse navbar-static-top nav-fixed-top"></nav>
            <!--    joinCorworker overlay     -->
            <div id="overlay">
                <div class="full">

                    <div class="big-box" id="inputArea">
                        <!--loading page-->
                        <div id="cat-bg">
                            <div class="loading-cat">
                                <div class="body"></div>
                                <div class="head">
                                    <div class="face"></div>
                                </div>
                                <div class="foot">
                                    <div class="tummy-end"></div>
                                    <div class="bottom"></div>
                                    <div class="legs left"></div>
                                    <div class="legs right"></div>
                                </div>
                                <div class="hands left"></div>
                                <div class="hands right"></div>
                            </div>
                            <div class="loading-text">處理中...</div>
                        </div>


                        <!--填寫基本資料-->
                        <div class="form-title">填寫基本資料</div>
                        <input type="text" placeholder="姓名" name="name" id="name" class="inputyellow"><br>
						<input type="radio" name="male" value="sexual" class="radio-style" id="sexual">男
                        <input type="radio" name="female" value="sexual" class="radio-style" id="sexual">女<br>
						<input type="text" placeholder="生日" name="birthday" id="birthday" class="inputyellow"><br>
                        <input type="file" id="intro" name="intro" class="btnyellow btn-forLightBox" value="履歷上傳"><br>
                        <textarea type="text" placeholder="留言板..." name="message" id="message" class="textarea-style"></textarea>
                        <br>
                        <div id="errorMSG"></div>
                        <div class="gether">
                            <br>
                            <div id="btn-cancel" class="btnyellow btn-forLightBox" name="btn-cancel" onclick="overlayOff()">取消</div>
                            <div id="btn-submit" class="btnyellow btn-forLightBox" name="btn-submit" onclick="submit()">送出資料</div>
                        </div>
                        <a onclick="assistant()">小幫手</a>
                    </div>
                </div>
            </div>

            <!--    joinCorworker option     -->
            <div class="box">
                <p class="sub-title">職缺列表</p>
                <div class="styled-select slate">
                    <select id="select" onchange="refresh()">
				<option value="" disabled selected>請選擇職缺名稱</option>
				<c:forEach items="${coworkerBean.selectAll}" var="coworker"
					varStatus="status">
					<option class="option-style" value="${coworker.value.result}">
        				${coworker.key}
    				</option>
				</c:forEach>
			</select>
                </div>
                <div class="contentBox">
                    <lebel>所需條件</lebel>
                    <span id="content"></span><br>
                    <lebel>上班時間</lebel>
                    <span id="time"></span><br>
                    <lebel>上班地點</lebel>
                    <span id="locate"></span><br>
                    <lebel>職務內容</lebel>
                    <span id="describe"></span><br>
                    <lebel>薪資</lebel>
                    <span id="salary"></span><br>
                    <lebel>福利</lebel>
                    <span id="welfare"></span><br>
                    <div id="btn_go" name="btn_go" class="btnyellow" onclick="overlayOn()">我要應徵</div>
                </div>
            </div>
            <div class="bg-full">
                <img src="../images/index.jpg">
            </div>

            <script type="text/javascript" src="../js/joinPartner.js"></script>
            <script type="text/javascript" src="../js/nav.js"></script>
            <script>
            function assistant(){
    			document.getElementById("name").value = '許碧璽';
    			document.getElementById("birthday").value = '1980/10/12';
    			document.getElementById("message").value = '您好!我是許碧璽，對於本職缺非常有興趣，再煩請過目我的履歷，謝謝!';
    			}
                function refresh() {
                    var strkey = $('#select').val();
                    var values = strkey.split(',');
                    $("#name").text(values[0]);
                    $("#content").text(values[1]);
                    $("#time").text(values[2]);
                    $("#locate").text(values[3]);
                    $("#describe").text(values[4]);
                    $("#salary").text(values[5]);
                    $("#welfare").text(values[6]);
                }

                function submit() {
                    var name = document.getElementById("name").value;
                    var sexual = document.getElementById("sexual").value;
                    var birthday = document.getElementById("birthday").value;
                    var message = document.getElementById("message").value;
                    var inputArea = document.getElementById("inputArea");
                    var catBg = document.getElementById("cat-bg");
                    
                    catBg.style.display="block";

                    var xhr = new XMLHttpRequest();
                    xhr.open("post", "JoinCoworker_Servlet", true);
                    xhr.setRequestHeader("Content-Type",
                        "application/x-www-form-urlencoded");
                    xhr.send("name=" + name + "&sexual=" + sexual + "&birthday=" +
                        birthday + "&message=" + message);
                    xhr.onreadystatechange = function() {
                        
                        var errorMSG = document.getElementById('errorMSG');
                        if (xhr.readyState == 4 && xhr.status == 200) {
                            var result = JSON.parse(xhr.responseText);
                            
                            catBg.style.display="none";
                            
                            if (result.nameEmptyError) {
                                errorMSG.innerHTML = result.nameEmptyError;
                                console.log("姓名不可以空白喔!");
                            }
                            if (result.sexualEmptyError) {
                                errorMSG.innerHTML = result.sexualEmptyError;
                                console.log("性別不可以空白喔!");
                            }
                            if (result.birthdayEmptyError) {
                                errorMSG.innerHTML = result.birthdayEmptyError;
                                console.log("生日不可以空白喔!");
                            }
                            if (result.success) {
                                inputArea.innerHTML = result.success;
                                console.log("成功!");
                            }

                        }
                    }
                }

            </script>
        </body>

        </html>
