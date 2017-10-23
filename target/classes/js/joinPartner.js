function overlayOn() {
    $('#overlay').fadeIn();
}

function overlayOff() {
    $('#overlay').fadeOut();
}

var submit = document.getElementById("btn-submit");
var errorMSG = $(".errorMSG");

function check() {
    var identity = document.getElementById("identity").value; //1.身分
    var companyName = document.getElementById("companyName").value //2.公司名稱
    var deitoria = document.getElementById("deitoria").value; //3.統編
    var capital = document.getElementById("capital").value; //4.資本額
    var principle = document.getElementById("principle").value; //5.負責人
    var sexaul = document.getElementById("sexaul").value; //6.負責人性別
    var contecter = document.getElementById("contecter").value; //7.聯絡人
    var phone = document.getElementById("phone").value; //8.連絡電話
    var email = document.getElementById("email").value; //9.公司簡介
    var message = document.getElementById("message").value; //訊息
    var errorMSG = document.getElementById('errorMSG');
    var content = document.getElementById('content-box');

    var catBg = document.getElementById("cat-bg");

    catBg.style.display = "block";

    var xhr = new XMLHttpRequest();
    xhr.open("post", "AboutUs_Servlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(
        "identity=" + identity +
        "&companyName=" + companyName +
        "&deitoria=" + deitoria +
        "&capital=" + capital +
        "&principle=" + principle +
        "&sexaul=" + sexaul +
        "&contecter=" + contecter +
        "&phone=" + phone +
        "&email=" + email +
        "&message=" + message
    );
    xhr.onreadystatechange = function () {
        var errorMSG = document.getElementById('errorMSG');
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = JSON.parse(xhr.responseText);

            catBg.style.display = "none";

            //=============1.公司身分欄位不可以空白喔! check=============
            if (result.identityEmptyError) {
                errorMSG.innerHTML = result.identityEmptyError;
                console.log("公司身分欄位不可以空白喔! check");
            }

            //=============2.公司名稱不可以空白喔! check=============
            if (result.companyNameEmptyError) {
                errorMSG.innerHTML = result.companyNameEmptyError;
                console.log("公司名稱不可以空白喔! check");
            }

            //=============3.公司統編不可以空白喔! check=============
            if (result.deitoriaEmptyError) {
                errorMSG.innerHTML = result.deitoriaEmptyError;
                console.log("公司統編不可以空白喔! check");
            }

            //=============4.公司資本額不可以空白喔!=============
            if (result.capitalEmptyError) {
                errorMSG.innerHTML = result.capitalEmptyError;
                console.log("公司資本額不可以空白喔!");
            }

            //=============5.公司負責人不可以空白喔! check=============
            if (result.principleEmptyError) {
                errorMSG.innerHTML = "公司負責人不可以空白喔! check";
                console.log("公司負責人不可以空白喔! check");
            }

            //=============6.負責人性別不可以空白喔! check=============
            if (result.sexaulEmptyError) {
                errorMSG.innerHTML = result.sexaulEmptyError;
                console.log("負責人性別不可以空白喔! check");
            }

            //=============7.公司聯絡人不可以空白喔! check=============
            if (result.contecterEmptyError) {
                errorMSG.innerHTML = result.contecterEmptyError;
                console.log("公司聯絡人不可以空白喔! check");
            }

            //=============8.聯絡電話不可以空白喔! check=============
            if (result.phoneEmptyError) {
                errorMSG.innerHTML = result.phoneEmptyError;
                console.log("聯絡電話不可以空白喔! check");
            }

            //=============9.聯絡信箱不可以空白喔! check=============
            if (result.emailEmptyError) {
                errorMSG.innerHTML = result.emailEmptyError;
                console.log("聯絡信箱不可以空白喔! check");
            }

            //=============10.寄件成功，寄信!!=============
            if (result.success) {
                content.innerHTML = result.success;
                console.log("寄件成功，寄信!!");
            }

        } //end if
    } //end xhr.onreadystatechange
} //end $("#btn-submit").click
