var xhr = new XMLHttpRequest();
xhr.open('get', '/WimeProject/nav.jsp', true);
xhr.onreadystatechange = function () {
//	loginText = document.getElementById("lgc").innerHTML;
	login = Cookies.get("login");
	if(login = "ok"){
//		loginText = "登出";
//		alert(login);
	}
    if (this.readyState !== 4 && this.status !== 200) {
//    	loginText = document.getElementById("lgc").innerHTML;
//    	alert(loginText);
        return;
    }
    document.getElementById('navMenu').innerHTML = this.responseText;    
};
xhr.send();
