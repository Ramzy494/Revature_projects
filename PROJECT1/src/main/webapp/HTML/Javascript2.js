
window.onload= function(){
    getTickets();
	document.getElementById("Approved").addEventListener('click',getApproved);
	document.getElementById("Denied").addEventListener('click',getDenied);
	document.getElementById("Pending").addEventListener('click',getPending);
	document.getElementById("GetAllTickets").addEventListener('click',getTickets);
	document.getElementById("Update").addEventListener('click',updateTicket);
}

function getTickets(){
    let xmlhttp = new XMLHttpRequest();


    console.log("made it to getTickets");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let TicketObject = JSON.parse(xmlhttp.responseText);
             console.log(TicketObject);

             DOMManipulation(TicketObject);
           
        };
    };
    
    xmlhttp.open('GET', 'http://localhost:8080/PROJECT1/Client/GetAllTickets');
    xmlhttp.send();
};

function getPending(){
    let xmlhttp = new XMLHttpRequest();
    console.log("show pending tickets");

    xmlhttp.onreadystatechange = function(){
        console.log("inside on ready state changefunction");

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let pendTicks = JSON.parse(xmlhttp.responseText);
            console.log(pendTicks);
            DOMManipulation(pendTicks);
        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/PROJECT1/Client/showPendingTicks');
    xmlhttp.send();
}

function getApproved (){
    let xmlhttp = new XMLHttpRequest();
    console.log("show Approved tickets");

    xmlhttp.onreadystatechange = function(){
        console.log("inside on ready state changefunction");

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let pendTicks = JSON.parse(xmlhttp.responseText);
            console.log(pendTicks);
            DOMManipulation(pendTicks);
        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/PROJECT1/Client/showApprovedTicks');
    xmlhttp.send();
}

function getDenied (){
    let xmlhttp = new XMLHttpRequest();
    console.log("show Denied tickets");

    xmlhttp.onreadystatechange = function(){
        console.log("inside on ready state changefunction");

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let pendTicks = JSON.parse(xmlhttp.responseText);
            console.log(pendTicks);
            DOMManipulation(pendTicks);
        }
    }
    xmlhttp.open('GET', 'http://localhost:8080/PROJECT1/Client/showDeniedTicks');
    xmlhttp.send();
}


function updateTicket() {
    console.log("update ticket");

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){
		console.log("inside on ready state changefunction");
    }

    xhttp.open('POST', 'http://localhost:8080/PROJECT1/Client/updateTicket')
    xhttp.setRequestHeader("content-type", "application/json");

    let updatedTicket = {
        "reim_ID": document.getElementById("ID").value,
        "reimb_STATUS_ID": document.getElementById("TypeID").value
    }
    xhttp.send(JSON.stringify(updatedTicket));
}



function DOMManipulation(targetedList){
	let Select = document.querySelector("#tempSpace");

    while(Select.firstChild){

        Select.removeChild(Select.firstChild);
    }
for (let i = 0; i < targetedList.length; i++) {

		/////////creating the elements 
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");

		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		let newTD8 = document.createElement("td");

		//populate them 
		newTH.setAttribute("scope", "row");
		let myTextH = document.createTextNode(targetedList[i].reim_ID);
		let myTextTD1 = document.createTextNode(targetedList[i].reimb_AMOUNT);
		let myTextTD2 = document.createTextNode(targetedList[i].reimb_SUBMITTED);
		let myTextTD3 = document.createTextNode(targetedList[i].reimb_RESOLVED);
		let myTextTD4 = document.createTextNode(targetedList[i].reimb_DESCRIPTION);
		let myTextTD5 = document.createTextNode(targetedList[i].reimb_AUTHOR);
		let myTextTD6 = document.createTextNode(targetedList[i].reimb_RESOLVER);
		let myTextTD7 = document.createTextNode(targetedList[i].reimb_STATUS_ID);
		let myTextTD8 = document.createTextNode(targetedList[i].reimb_TYPE_ID);

		// append 
		newTH.appendChild(myTextH);
		newTD1.appendChild(myTextTD1);
		newTD2.appendChild(myTextTD2);
		newTD3.appendChild(myTextTD3);
		newTD4.appendChild(myTextTD4);
		newTD5.appendChild(myTextTD5);
		newTD6.appendChild(myTextTD6);
		newTD7.appendChild(myTextTD7);
		newTD8.appendChild(myTextTD8);

		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);
		newTR.appendChild(newTD8);


		let newSelection = document.querySelector("#tempSpace");
		newSelection.appendChild(newTR);
		console.log("domManip2");
	}
}