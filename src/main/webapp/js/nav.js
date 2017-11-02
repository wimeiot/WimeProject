var xhr = new XMLHttpRequest();
xhr.open('get', '/WimeProject/nav.jsp', true);
xhr.onreadystatechange = function () {
	login = Cookies.get("login");
	if(login = "ok"){
		alert(login);
	}
//	alert(123);
    if (this.readyState !== 4 && this.status !== 200) {
        return;
    }
    document.getElementById('navMenu').innerHTML = this.responseText;
};
xhr.send();
