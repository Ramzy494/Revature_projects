let xhttp = new XMLHttpRequest();



window.onload = function(){
console.log("is this working")
document.getElementById("email").addEventListener('keyup', submit);
document.getElementById("tempPassword").addEventListener('keyup', submit);
document.getElementById("password").addEventListener('keyup', submit);
document.getElementById("passwordTwo").addEventListener('keyup', submit);

submit();

}

/* Will lock the submit button until all fields are full and password fields match */
function submit(){
    document.getElementById("createAccountButton").disabled = true;

    if(document.getElementById("email").value.length != 0 && 
        document.getElementById("tempPassword").value.length != 0 &&
        document.getElementById("password").value.length != 0 &&
        document.getElementById("passwordTwo").value.length != 0 ){
            
            if(document.getElementById("password").value==document.getElementById("passwordTwo").value){
                
               buttonEnabler();
            }
    }

}




function buttonEnabler(){
    document.getElementById("createAccountButton").disabled = false;
}