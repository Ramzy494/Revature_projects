window.onload = function(){
    console.log("window has loaded!");

    document.getElementById("tickTable").addEventListener('click',getFoods)
    getFoods();
};


function getFoods(){
    let xmlhttp = new XMLHttpRequest();

    console.log("made it to getTickets()");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let foodObject = JSON.parse(xmlhttp.responseText);
             console.log(foodObject);

             DOMManipulation(foodObject);
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:8080/PROJECT1/Client/GetAllTickets');
    xmlhttp.send();
};
function DOMManipulation(foodObject){
    console.log(foodObject.length);
    for(let i = 0; i < foodObject.length; i++){
    console.log("I'm in the loop", i);
    let newDiv = document.createElement("h3");
    console.log(foodObject[i]);
    newDiv.setAttribute("class", "login-container");
   let divText = document.createTextNode(foodObject[i].REIM_ID+ foodObject[i].REIMB_AMOUNT+foodObject[i].REIMB_SUBMITTED+ foodObject[i].REIMB_RESOLVED+ foodObject[i].REIMB_DESCRIPTION+foodObject[i].REIMB_AUTHOR+foodObject[i].REIMB_RESOLVER, foodObject[i].REIMB_STATUS_ID,foodObject[i].REIMB_TYPE_ID);
    //let divText2 = document.createTextNode("Food Name: " + foodObject[i].foodName);
    newDiv.appendChild(divText);
    let currentElement = document.getElementById("tempSpace");
    currentElement.appendChild(newDiv);
    
    }
}
