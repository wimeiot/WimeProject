window.onload = function() {
		alink = document.getElementById("accountlink");
		
		alink.onclick = function() {

			var smallresultE = document.getElementById("resultE");
			var email = document.getElementById("email").value;
			if (!email) {
				smallresultE.innerHTML = "<font color='green' size='-2'>請輸入帳號</font>";
				return;
			}
			var xhr = new XMLHttpRequest();
			xhr.open("POST", "CheckUserIdServlet", true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.send("email=" + email);
			var message = "";
			xhr.onreadystatechange = function() {
				// 伺服器請求完成
				if (xhr.readyState == 4 && xhr.status == 200) {
					result = JSON.parse(xhr.response);
					var color = "";
					 if (result.errorEmailFormat) {
						message = result.errorEmailFormat;
						color = 'red';
					}else if (result.emailId.length == 0) {
							message = "帳號可用";
							color = 'green';
					} else if (result.emailId.startsWith("Error")) {
						message = result.emailId;
						color = 'red';
					} else {
						message = "帳號重複，請重新輸入";
						color = 'red';
					}
					smallresultE.innerHTML = "<font color='" + color + "' size='-1'>"
							+ message + "</font>";
				}
			}
		}

		var submit = document.getElementById("submit");
		submit.onclick = function() {
			//讀取欄位資料
			var email = document.getElementById("email").value;
			var password1 = document.getElementById("password1").value;
			var password2 = document.getElementById("password2").value;
			var name = document.getElementById("name").value;
			var birthday = document.getElementById("birthday").value;
			var phone = document.getElementById("phone").value;
			var mobile = document.getElementById("mobile").value;
			var address = document.getElementById("address").value;
			//判斷radio check
			var gender1 = document.getElementById("inlineRadio1");
			var gender2 = document.getElementById("inlineRadio2");
			var gender;
			if (gender1.checked == true) {
				gender1.value = "M";
				// 		 			console.log(gender1.value);
				gender = gender1;
			} else if (gender2.checked == true) {
				gender2.value = "F";
				// 		 			console.log(gender2.value);
				gender = gender2;
			} else {
				gender1.value = "";
				gender2.value = "";
				gender = gender1;
			}

			var hasError = false; //設定判斷有無錯誤的旗標
			var small0 = document.getElementById('resultE');
			var small1 = document.getElementById('resultP1');
			var small2 = document.getElementById('resultP2');
			var small3 = document.getElementById('resultN');
			var small4 = document.getElementById('resultG');
			var small5 = document.getElementById('resultB');
			var small6 = document.getElementById('resultPH');
			var small7 = document.getElementById('resultMO');
			var small8 = document.getElementById('resultA');
			var small9 = document.getElementById('errorIDDup');
			//
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "register.do", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("email=" + email + "&password1=" + password1 +
					 "&password2=" + password2 + "&name=" + name + "&gender="
					+ gender.value + "&birthday=" + birthday + "&phone="
					+ phone + "&mobile=" + mobile + "&address=" + address);

			xhr1.onreadystatechange = function() {
				//伺服器請求完成
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.response);
					
					if(result.succesID){
						//後端驗證ok跳到已傳信頁面
						document.location.href = 'submitMail.jsp?submitResultOK= 驗證信已寄出~';
					} else if (result.errorcheckID){
						small9.innerHTML = result.errorcheckID;
						hasError = true;
						return;
					} else{
						
					}
					//0_mail帳號
					if (result.errorEmail) {
						small0.innerHTML = result.errorEmail;
						hasError = true;
					} else if (result.errorEmailFormat) {
						small0.innerHTML = result.errorEmailFormat;
						hasError = true;
					} else {
						small0.innerHTML = "";
					}
					//1_密碼1
					if (result.errorPassword1) {
						small1.innerHTML = result.errorPassword1;
						hasError = true;
					} else if (result.errorPasswordEmpty1) {
						small1.innerHTML = result.errorPasswordEmpty1;
						hasError = true;
					} else {
						small1.innerHTML = "";
					}
					//2_密碼2
					if (result.errorPassword2) {
						small2.innerHTML = result.errorPassword2;
						hasError = true;
					} else if (result.errorPasswordEmpty2) {
						small2.innerHTML = result.errorPasswordEmpty2;
						hasError = true;
					} else {
						small2.innerHTML = "";
					}
					//3_姓名
					if (result.errorName) {
						small3.innerHTML = result.errorName;
						hasError = true;
					} else {
						small3.innerHTML = "";
					}
					//4_姓別
					if (result.errorGender) {
						small4.innerHTML = result.errorGender;
						hasError = true;
					} else {
						small4.innerHTML = "";
					}
					//5_生日
					if (result.errorBirthday) {
						small5.innerHTML = result.errorBirthday;
						hasError = true;
					} else {
						small5.innerHTML = "";
					}
					//6_室話
					if (result.errorPhone) {
						small6.innerHTML = result.errorPhone;
						hasError = true;
					} else {
						small6.innerHTML = "";

					}
					//7_手機
					if (result.errorMobile) {
						small7.innerHTML = result.errorMobile;
						hasError = true;
					} else {
						small7.innerHTML = "";

					}
					//8_住址
					 if (result.errorAddress) {
						small8.innerHTML = result.errorAddress;
						hasError = true;
					} else {
						small8.innerHTML = "";
						
					}//帳號相同
					 if (result.errorIDDup) {
						    small9.innerHTML = result.errorIDDup;
							hasError = true;
						} else {
							small9.innerHTML = "";
						}
					if (hasError){
			 		    return false;
			 		    console.log('false');
					 }else{


				}
			}

			}						
		}
		}

	function printHelps() {
		var email = document.getElementById("email").value = "zero0989108353@gmail.com";
		var password1 = document.getElementById("password1").value = "Java0062017";
		var password2 = document.getElementById("password2").value = "Java0062017";
		var name = document.getElementById("name").value = "小熊測試";
		var inlineRadio1 = document.getElementById("inlineRadio1").checked = true;
		var birthday = document.getElementById("birthday").value = "2017-09-08";
		var phone = document.getElementById("phone").value = "0980709606";
		var mobile = document.getElementById("mobile").value = "0209090908";
		var address = document.getElementById("address").value = "106台北市大安區新生南路一段1號";

	}
	function reset() {
		var inlineRadio1 = document.getElementById("inlineRadio1").checked = false;
		var inlineRadio2 = document.getElementById("inlineRadio2").checked = false;
		var email = document.getElementById("email").value = "";
		var password1 = document.getElementById("password1").value = "";
		var password2 = document.getElementById("password2").value = "";
		var name = document.getElementById("name").value = "";
		var birthday = document.getElementById("birthday").value = "";
		var phone = document.getElementById("phone").value = "";
		var mobile = document.getElementById("mobile").value = "";
		var address = document.getElementById("address").value = "";
		
		var small0 = document.getElementById('resultE').innerHTML = "";
		var small1 = document.getElementById('resultP1').innerHTML = "";
		var small2 = document.getElementById('resultP2').innerHTML = "";
		var small3 = document.getElementById('resultN').innerHTML = "";
		var small4 = document.getElementById('resultG').innerHTML = "";
		var small5 = document.getElementById('resultB').innerHTML = "";
		var small6 = document.getElementById('resultPH').innerHTML = "";
		var small7 = document.getElementById('resultMO').innerHTML = "";
		var small8 = document.getElementById('resultA').innerHTML = "";

	}
	function hidepassword() {
		var hide = document.getElementById("hide");

		if (hide.checked == true) {
			var password1 = document.getElementById("password1").type = "text";
			var password2 = document.getElementById("password2").type = "text";

		} else {
			var password1 = document.getElementById("password1").type = "password";
			var password2 = document.getElementById("password2").type = "password";
		}

	}