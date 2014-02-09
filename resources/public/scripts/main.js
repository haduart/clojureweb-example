
var bigImage = false;

function status_message(status) {
    if (status == 0) {
        return "not cached";
    } else if (status == 1) {
        return "fully cached and operational offline";
    } else if (status == 2) {
        return "checking for changes to the cache manifest...";
    } else if (status == 3) {
        return "downloading changed resources...";
    } else if (status == 4) {
        return "ready to switch to updated offline version";
    }
    return "";
}

function changeImage() {
    if (bigImage) {
        document.getElementById("alphabet").src = "http://storage0.dms.mpinteractiv.ro/media/2/84/2025/10384001/4/earthlights.jpg";
        bigImage = false;
    }  else {
        document.getElementById("alphabet").src = "http://mag.bent.com/files/2012/04/Atomium-under-the-sun-cc-Amanito.jpg";
        bigImage = true;
    }
}

window.setInterval(function () {
    var status = window.applicationCache.status;
    document.getElementById('status').innerHTML = status + " [" + status_message(status) + "]";

}, 1000);

window.applicationCache.addEventListener("updateready", function() {
    window.applicationCache.swapCache();
}, false);
