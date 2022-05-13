window.onload = function(){
    console.log("window has loaded!");
    // // createTable();
    getFoods();
    // // DOMManipulation();
    // // createTable();
};


function getFoods(){
    let xmlhttp = new XMLHttpRequest();

    // action="/INTRAINING_EXERCISE/home/foods/get"


    console.log("made it to getFoods()")
    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let foodObject = JSON.parse(xmlhttp.responseText);
             console.log(foodObject);
            DOMManipulation(foodObject);
        };
    };
    // /INTRAINING_EXERCISE/home/foods/get
    xmlhttp.open('GET', 'http://localhost:8080/InClassTrain/UserInput/Retrieve');
    xmlhttp.send();
};
function DOMManipulation(foodObject){
    console.log(foodObject.length);
    for(let i = 0; i < foodObject.length; i++){
    console.log("I'm in the loop", i);
    let newDiv = document.createElement("h3");
    console.log(foodObject[i]);
    newDiv.setAttribute("class", "login-container");
    let divText = document.createTextNode("Food ID: " + foodObject[i].foodId + "Food Name: " + foodObject[i].foodName);
    //let divText2 = document.createTextNode("Food Name: " + foodObject[i].foodName);
    newDiv.appendChild(divText);
    let currentElement = document.getElementById("tempSpace");
    currentElement.appendChild(newDiv);
    
    }
}
