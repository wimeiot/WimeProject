var xhr = new XMLHttpRequest();
xhr.open('get', '/WimeProject/nav.html', true);
xhr.onreadystatechange = function () {
    if (this.readyState !== 4 && this.status !== 200) {
        return;
    }
    document.getElementById('navMenu').innerHTML = this.responseText;
};
xhr.send();
