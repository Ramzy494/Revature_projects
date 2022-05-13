
import {PostDisplayService} from '../js/post-display-service.js';



window.onload= function(){
document.getElementById('submitPost').addEventListener('click',CreatePost);
//document.getElementById('reply').addEventListener('click', CreateComment);
}
function CreatePost(){
    let xmlhttp = new XMLHttpRequest();
    let post= {
        "postMessage":document.getElementById("postMessage").value,
        "numOfLikes":0
    };

console.log(post.postMessage);
    console.log("made it to Create Post");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200")

    readallPost();

           
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:9099/AddPost');
    xmlhttp.setRequestHeader('content-type','application/json')
    xmlhttp.send(JSON.stringify(post));

     
    

};

function CreateComment(){
    let xmlhttp = new XMLHttpRequest();
    let Comment= {
        "commentMessage":document.getElementById("commentMessage").value
     
    };


    console.log("made it to Create message");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
           

             //DOMManipulation(Object); Frederick stuff 
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:9099/AddComment');
    xmlhttp.setRequestHeader('content-type','application/json')
    xmlhttp.send(JSON.stringify(Comment));
}

function readallPost(){


let xmlhttp = new XMLHttpRequest();
    let post= {
        "postMessage":document.getElementById("postMessage").value,
        "numOfLikes":0
    };


    console.log("made it to Create Post");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200")
            console.log(xmlhttp.responseText)
            let post1=JSON.parse(xmlhttp.responseText);
            
            let postService = new PostDisplayService();
        postService.posts = ourObjectFromJSON;
        postService.displayPosts();

    

           
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:9099/ReadPost');
    xmlhttp.send();

     
    

};