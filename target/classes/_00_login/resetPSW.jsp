<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.css">
        <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="../css/normalize.css">

        <!-- 自己的 -->
        <link rel="stylesheet" type="text/css" href="../css/share.css">
        <link rel="stylesheet" type="text/css" href="../css/forgot.css">
        <link rel="stylesheet" type="text/css" href="../css/nav.css">
        <!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>重設密碼</title>
    </head>

    <body>

<!--
        <div id="content">
            <h2>重新設定密碼</h2>
            <p>密碼長度不可少於6位數，超過12位數，必須是英文數字組合。</p>
            <input type="password" name="psw01" id="psw01" placeholder="新密碼">
            <div id="empty1"></div><br>
            
            <input type="password" name="psw02" id="psw02" placeholder="重複輸入密碼">
            <div id="empty2"></div><br> <br>

            <div id="notEqual"></div>
            <br> <input type="submit" name="resetpsw" id="resetpsw">
        </div>
        
-->
        
        <div class="center-box">
        <div class="col-md-4 col-xs-12 box color-bg-darkBox">
            <p class="sm-title text-center">重新設定密碼</p>
            <p>密碼長度不可少於6位數，超過12位數，必須是英文數字組合。</p>
            
            <input type="password" name="psw01" id="psw01" placeholder="新密碼" class="input-yellow"><span id="empty1" class="tx-error"></span><br>
            
            <input type="password" name="psw02" id="psw02" placeholder="重複輸入密碼" class="input-yellow"><span id="er_Empty" class="tx-error"></span><br>
            <span id="empty2" class="tx-error"></span>
            <div id="notEqual"></div>
            <div id="resetpsw" class="btnyellow" name="resetpsw">重設密碼</div>
            <!-- 		<div class="g-recaptcha" data-sitekey="6LdUIzEUAAAAAPXvXcLFu2_J7qUGubQujeVdAL2i"></div> -->
        </div>
    </div>
        <div class="bg-full">
        <img src="../images/alexey-ruban-363198.jpg">
    </div>

        <script type="text/javascript">
            window.onload = function() {
                var resetpsw = document.getElementById("resetpsw");
                var psw01 = document.getElementById("psw01").value;
                var psw02 = document.getElementById("psw02").value;
                var ip_psw01 = document.getElementById("psw01");
                var ip_psw02 = document.getElementById("psw02");

                var resetpsw = document.getElementById("resetpsw");
                var empty1 = document.getElementById("empty1");
                var empty2 = document.getElementById("empty2");
                var notEqual = document.getElementById("notEqual");
                var content = document.getElementById("content");

                ip_psw01.onblur = function() {
                    if (psw01.length > 12 || psw01.length < 6) {
                        notEqual.innerHTML = "密碼長度不可少於6位數，超過12位數。";
                        return false;
                    }
                    return;
                }
                ip_psw02.onblur = function() {
                    if (psw02.length > 12 || psw02.length < 6) {
                        notEqual.innerHTML = "密碼長度不可少於6位數，超過12位數。";
                        return false;
                    }
                    return;
                }

                resetpsw.onclick = function() {
                    var psw01 = document.getElementById("psw01").value;
                    var psw02 = document.getElementById("psw02").value;
                    var resetpsw = document.getElementById("resetpsw");
                    var empty1 = document.getElementById("empty1");
                    var empty2 = document.getElementById("empty2");
                    var notEqual = document.getElementById("notEqual");
                    var content = document.getElementById("content");
                    /*===================== 前端處理 =====================*/
                    console.log(psw01);
                    console.log(psw02);
                    /*===================== 後端處理 =====================*/
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "ResetPswServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.send("psw01=" + psw01 + "&psw02=" + psw02);

                    xhr.onreadystatechange = function() {
                        if (xhr.readyState == 4 && xhr.status == 200) {
                            var result = JSON.parse(xhr.responseText);

                            if (result.Psw01EmptyError) {
                                empty1.innerHTML = result.Psw01EmptyError;
                                empty2.innerHTML = "";
                                notEqual.innerHTML = "";
                                console.log('Empty check');
                            } else if (result.Psw02EmptyError) {
                                empty1.innerHTML = "";
                                empty2.innerHTML = result.Psw02EmptyError;
                                notEqual.innerHTML = "";
                                console.log('Empty check');
                            } else if (result.notEqualError) {
                                empty1.innerHTML = "";
                                empty2.innerHTML = "";
                                notEqual.innerHTML = result.notEqualError;
                                console.log('Empty check');
                            } else if (result.UpdateSuccess) {
                                content.innerHTML = result.UpdateSuccess;
                            }
                        }
                    }
                }
            }

        </script>
    </body>

    </html>
