let xhttp = new XMLHttpRequest();



window.onload = function(){

document.getElementById("email").addEventListener('keyup', submit);
document.getElementById("firstName").addEventListener('keyup', submit);
document.getElementById("lastName").addEventListener('keyup', submit);
document.getElementById("username").addEventListener('keyup', submit);
document.getElementById("password").addEventListener('keyup', submit);
document.getElementById("passwordTwo").addEventListener('keyup', submit);

submit();

}

/* Will lock the submit button until all fields are full and password fields match */
function submit(){
    document.getElementById("createAccountButton").disabled = true;

    if(document.getElementById("email").value.length != 0 && 
        document.getElementById("firstName").value.length != 0 &&
        document.getElementById("lastName").value.length != 0 &&
        document.getElementById("username").value.length != 0 &&
        document.getElementById("password").value.length != 0 &&
        document.getElementById("passwordTwo").value.length != 0){
            console.log("inside first if")
            if(document.getElementById("password").value==document.getElementById("passwordTwo").value){
                console.log("inside 2nd if")
               buttonEnabler();
            }
        // submit();
    }

}




function buttonEnabler(){
    document.getElementById("createAccountButton").disabled = false;
}
