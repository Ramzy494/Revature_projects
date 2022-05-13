window.onload= function(){
    getAllTickets();
	document.getElementById("AddingTicket").addEventListener('click',AddTickets);
}

function getAllTickets(){
    let xmlhttp = new XMLHttpRequest();


    console.log("made it to getTickets");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let Object = JSON.parse(xmlhttp.responseText);
             console.log(Object);

             DOMManipulation(Object);
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:8080/PROJECT1/Client/ShowEmployeeticket');
    xmlhttp.send();
};

function AddTickets(){
    let xmlhttp = new XMLHttpRequest();


    console.log("made it to AddTickets");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
            let Object = JSON.parse(xmlhttp.responseText);
             console.log(Object);

             DOMManipulation(Object);
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:8080/PROJECT1/Client/AddingTicket');
    xmlhttp.send(JSON.stringify(AddTicket));
};



function DOMManipulation(Ticket){
for (let i = 0; i < Ticket.length; i++) {

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
		let myTextH = document.createTextNode(Ticket[i].reim_ID);
		let myTextTD1 = document.createTextNode(Ticket[i].reimb_AMOUNT);
		let myTextTD2 = document.createTextNode(Ticket[i].reimb_SUBMITTED);
		let myTextTD3 = document.createTextNode(Ticket[i].reimb_RESOLVED);
		let myTextTD4 = document.createTextNode(Ticket[i].reimb_DESCRIPTION);
		let myTextTD5 = document.createTextNode(Ticket[i].reimb_AUTHOR);
		let myTextTD6 = document.createTextNode(Ticket[i].reimb_RESOLVER);
		let myTextTD7 = document.createTextNode(Ticket[i].reimb_STATUS_ID);
		let myTextTD8 = document.createTextNode(Ticket[i].reimb_TYPE_ID);

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


		let newSelection = document.querySelector("#Table");
		newSelection.appendChild(newTR);
		console.log("domManip2");
	}

}

	let AddTicket = {
        "reimb_AMOUNT": document.getElementById("Amount").value,
        "reimb_DESCRIPTION": document.getElementById("description").value,
		"reimb_TYPE_ID": document.getElementById("TypeID").value
    };