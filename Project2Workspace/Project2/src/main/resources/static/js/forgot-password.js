let xhttp = new XMLHttpRequest();



window.onload = function(){

document.getElementById("email").addEventListener('keyup', submit);

submit();

}

/* Will lock the submit button until all fields are full and password fields match */
function submit(){
    document.getElementById("createAccountButton").disabled = true;

    if(document.getElementById("email").value.length != 0){
           let input = document.getElementById("email").value
           let symbol = "@"
            if(input.includes(symbol)){
                
               buttonEnabler();
            }
    }

}




function buttonEnabler(){
    document.getElementById("createAccountButton").disabled = false;
}