
window.onload = function(){
    checkBending();
    document.getElementById("waterButton").addEventListener('click',setWater)
    document.getElementById("earthButton").addEventListener('click',setEarth)
    document.getElementById("airButton").addEventListener('click',setAir)
    document.getElementById("fireButton").addEventListener('click',setFire)
}


function checkBending(){
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log(xmlhttp.responseText)
            let currentUser = JSON.parse(xmlhttp.responseText);

            if(currentUser.bendingType!=null){
                document.getElementById("waterButton").style.display='none';
                document.getElementById("earthButton").style.display='none';
                document.getElementById("airButton").style.display='none';
                document.getElementById("fireButton").style.display='none';
            };
           
        };
    };

    xmlhttp.open('GET', 'http://localhost:9099/ReadUser');
    xmlhttp.send();
}

function setWater(){
    let xmlhttp = new XMLHttpRequest();
    let bendingType = "Water Bending";

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            bendingType = document.getElementById("intro-line").innerText;

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addbending');
    xmlhttp.send(bendingType);
}

function setEarth(){
    let xmlhttp = new XMLHttpRequest();
    let bendingType = "Earth Bending";

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            bendingType = document.getElementById("intro-line").innerText;

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addbending');
    xmlhttp.send(bendingType);
}

function setAir(){
    let xmlhttp = new XMLHttpRequest();
    let bendingType = "Air Bending";

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
           bendingType = document.getElementById("intro-line").innerText;

           checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addbending');
    xmlhttp.send(bendingType);
}

function setFire(){
    let xmlhttp = new XMLHttpRequest();
    let bendingType = "Fire Bending";

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            bendingType = document.getElementById("intro-line").innerText;

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addbending');
    xmlhttp.send(bendingType);
}