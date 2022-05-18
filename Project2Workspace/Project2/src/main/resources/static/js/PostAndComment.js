
import { PostDisplayService } from './post-display-service.js';
import { CommentDisplayService } from "./comment-display-service.js";
import { PostGetService } from "./post-get-service.js";
import { ImageUploadService } from './image-upload-service.js';




window.onload = function () {
    document.getElementById('submitPost').addEventListener('click', CreatePost);
    document.getElementById('SubmitUpdate').addEventListener('click', editProfile);
    GetUser();
    checkBending();
document.getElementById('submitPost').addEventListener('click',CreatePost);
document.getElementById('SubmitUpdate').addEventListener('click',editProfile);
document.getElementById('timeline').addEventListener('click',colorbuttonsTimeline);
document.getElementById('mypost').addEventListener('click',colorbuttonsPost);
document.getElementById('mypost').addEventListener('click',DisplayPostByUser);
GetUser();
checkBending();
    document.getElementById("waterButton").addEventListener('click', setWater)
    document.getElementById("earthButton").addEventListener('click', setEarth)
    document.getElementById("airButton").addEventListener('click', setAir)
    document.getElementById("fireButton").addEventListener('click', setFire)


readallPost();



}
function CreatePost() {
    let xmlhttp = new XMLHttpRequest();

    let post = {
        "postMessage": document.getElementById("postMessage").value,
        "numOfLikes": 0
    };

    console.log(post.postMessage);
    console.log("made it to Create Post");

    xmlhttp.onreadystatechange = function () {
        console.log("inside on ready state changefunction")

        if (xmlhttp.readyState == 4 && xmlhttp.status == 201) {
            console.log("state is 4 status 200")
            // let respPost = JSON.parse(xmlhttp.responseText);
            // let imageUpload = new ImageUploadService(respPost.postId);
            // imageUpload.uploadImagePost();
            clearPostInput();
            readallPost();
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/AddPost');
    xmlhttp.setRequestHeader('content-type', 'application/json')
    xmlhttp.send(JSON.stringify(post));
};

// function CreateComment(){
//     let xmlhttp = new XMLHttpRequest();
//     let Comment= {
//         "commentMessage":document.getElementById("commentMessage").value

//     };


//     console.log("made it to Create message");

//     xmlhttp.onreadystatechange = function (){
//         console.log("inside on ready state changefunction")

//         if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
//             console.log("state is 4 status 200");

//             displayComments()


//              //DOMManipulation(Object); Frederick stuff 


//         };
//     };

//     xmlhttp.open('POST', 'http://localhost:9099/AddComment');
//     xmlhttp.setRequestHeader('content-type','application/json')
//     xmlhttp.send(JSON.stringify(Comment));
// }

function readallPost() {

    let xmlhttp = new XMLHttpRequest();
    //console.log("made it to read Post");

    xmlhttp.onreadystatechange = function () {
        //console.log("inside on ready state changefunction")

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //console.log("state is 4 status 200")
            //console.log(xmlhttp.responseText)
            let post1 = JSON.parse(xmlhttp.responseText);

            let postdisplayService = new PostDisplayService();
            postdisplayService.posts = post1;
            postdisplayService.displayPosts();
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/ReadPost');
    xmlhttp.send();
};

function editProfile() {

    console.log(" In edit button");
    let fName = document.getElementById("Firstname").value;
    let lName = document.getElementById("Lastname").value;


    let updateProfile = {
        "firstName": fName,
        "lastName": lName,

    }
    console.log(updateProfile);

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {

            let updatedUser = JSON.parse(xhttp.responseText);
            console.log(updatedUser);
            dOMManipulationDisplayProfile(updatedUser);

        }
    }


    xhttp.open('POST', 'http://localhost:9099/updateProfile');

    xhttp.setRequestHeader("content-type", "application/json");
    xhttp.send(JSON.stringify(updateProfile));
}

function dOMManipulationEditprofile(ourResponseObject) {
    console.log(ourResponseObject);

    document.getElementById("user name").innerText = (ourResponseObject.firstName + " " + ourResponseObject.lastName);


}

function GetUser() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        console.log("inside on ready state changefunction")

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log(xmlhttp.responseText);
            dOMManipulationDisplayProfile(JSON.parse(xmlhttp.responseText));
            //console.log("state is 4 status 200")

        }

    }
    xmlhttp.open('GET', 'http://localhost:9099/ReadUser');
    xmlhttp.send();
}


function dOMManipulationDisplayProfile(User) {

    document.getElementById("u-name").innerText = (User.firstName + " " + User.lastName);

}

function clearPostInput() {
    document.getElementById("postMessage").value = "";
}




// Below is functionality for bending styles


function checkBending() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let currentUser = JSON.parse(xmlhttp.responseText);

            if (currentUser.bendingType != null) {
                document.getElementById("waterButton").style.display = 'none';
                document.getElementById("earthButton").style.display = 'none';
                document.getElementById("airButton").style.display = 'none';
                document.getElementById("fireButton").style.display = 'none';

                console.log(currentUser.bendingType);
                let bendingStyle = currentUser.bendingType;
                document.getElementById("intro-line").innerText = bendingStyle;
            };


        };
    };

    xmlhttp.open('GET', 'http://localhost:9099/ReadUser');
    xmlhttp.send();
}

function setWater() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("intro-line").innerText = "Water Bending";

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addwaterbending');
    xmlhttp.send();
}

function setEarth() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("intro-line").innerText = "Earth Bending";

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addearthbending');
    xmlhttp.send();
}

function setAir() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("intro-line").innerText = "Air Bending";

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addairbending');
    xmlhttp.send();
}

function setFire() {
    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("intro-line").innerText = "Fire Bending";

            checkBending()
        };
    };

    xmlhttp.open('POST', 'http://localhost:9099/addfirebending');
    xmlhttp.send();
}


function findUser() {
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {

        console.log("inside on ready state changefunction")

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log(xmlhttp.responseText);
            DOMManipulation(JSON.parse(xmlhttp.responseText));



        }

    }
    xmlhttp.open('GET', 'http://localhost:9099/SearchUser');
    xmlhttp.send();
}

function DOMManipulation(Find) {
    document.getElementById("Search").innerText(Find.firstName);
    document.getElementById("Search").innerText(Find.lastName);

}

function colorbuttonsTimeline(){
    document.getElementById("mypost").setAttribute("class","td")
    document.getElementById("timeline").setAttribute('class',"td active");
    readallPost();
}
function colorbuttonsPost(){
    document.getElementById("timeline").setAttribute("class","td")
    document.getElementById("mypost").setAttribute('class',"td active");
   
}
function DisplayPostByUser(){
    let xmlhttp = new XMLHttpRequest();
    console.log("made it to read Post");

    xmlhttp.onreadystatechange = function () {
        console.log("inside on ready state changefunction")

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log("state is 4 status 200")
            console.log(xmlhttp.responseText)
            let post1 = JSON.parse(xmlhttp.responseText);
            displayPosts();


}
xmlhttp.open('POST', 'http://localhost:9099/GetPostByCurrentUser');
    xmlhttp.send();
}
}

