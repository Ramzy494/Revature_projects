/**
 * In charge of storing a this.posts array and displaying cards in
 * the postsContainer element ID.
 * @author darkm (Frederick)
 */

import { CommentGetService } from './comment-get-service.js';


export class PostDisplayService {
    constructor() {
        this.posts = [];
        this.commentService = new CommentGetService();
    }

    displayPosts() {
        let ourPostContainer = document.getElementById("postContainer");

        ourPostContainer.innerHTML = "";
        let newPost = document.createElement("li");
        let newPostCard = document.createElement("div");
        let newPostImgHead = document.createElement("img");
        let newPostUsername = document.createElement("h5");
        let newPostProfilePic = document.createElement("img"); // image element within h5
        let newPostBody = document.createElement("div"); // div that is card body
        let newPostBodyText = document.createElement("p"); // <p> that is text content
        let newPostReply = document.createElement("button");
        let newPostLikes = document.createElement("button");
        let postHasImage = false;
    

        // for each value...
        if ((this.posts) && !(this.posts.length === 0)) {
            Object.values(this.posts).forEach(postObj => {
                //console.log(postObj);

                // take that object and dynamically make a card per post object
                if (postObj.postId) {
                    if (postObj.postImage) {
                        postHasImage = true;
                    } else {
                        postHasImage = false;
                    }
                    initPostElements();
                    addPostContent(postObj);
                    appendChildren();
                }
            });
        } else {
            ourPostContainer.innerText = "No posts to display.";
        }

        /**
         * Before we make any element content, we need to initialize them.
         * @author darkm (Frederick)
         */
        function initPostElements() {
            newPost = document.createElement("li");
            newPostCard = document.createElement("div");
            if (postHasImage) {
                newPostImgHead = document.createElement("img");
            }
            newPostUsername = document.createElement("h5");
            newPostProfilePic = document.createElement("img"); // image element within h5
            newPostBody = document.createElement("div"); // div that is card body
            newPostBodyText = document.createElement("p"); // <p> that is text content
            newPostReply = document.createElement("button");
            newPostLikes = document.createElement("button");

            // initialize the attribute values
            newPost.className = "list-group-item align-items-center text-center";
            newPostCard.className = "card d-flex mx-auto mb-2 mt-2";
            if (postHasImage) {
                newPostImgHead.className = "card-img-top";
                newPostImgHead.alt = "Card image cap OPTIONAL";
            }
            newPostUsername.className = "card-title";
            newPostProfilePic.alt = "[Profile Picture]";
            newPostProfilePic.width = "32";
            newPostProfilePic.height = "32";
            newPostBody.class = "card-body";
            newPostBodyText.class = "card-text";
            newPostReply.className = "btn btn-primary float-start";
            newPostReply.setAttribute("data-bs-toggle", "modal");
            newPostReply.setAttribute("data-bs-target", "#commentsModal");
            newPostLikes.className = "btn btn-primary float-end";
            newPostLikes.type = "button";
        }

        /**
         * Add the content itself
         * @author darkm (Frederick)
         * @param {*} postObj 
         */
        function addPostContent(postObj) {
            // {"postId":1,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"postImage":"","accountId":4,"username":"Agent 47","accountImage":null}

            // first set the IDs
            newPost.id = "postContainer" + postObj.postId;
            newPostCard.id = "open" + postObj.postId;
            if (postHasImage) {
                newPostImgHead.id = "postImg" + postObj.postId;
            }
            newPostProfilePic.id = "postProfileImg" + postObj.accountId;
            newPostUsername.id = "postUsername" + postObj.postId;
            newPostBody.id = "postBody" + postObj.postId;
            newPostBodyText.id = "postText" + postObj.postId;
            newPostLikes.id = "postLike" + postObj.postId;

            // Now add the content itself
            if (postHasImage) {
                newPostImgHead.src = "/s3img/" + postObj.postImage;
            }
            if (postObj.accountImage) {
                newPostProfilePic.src = "/s3img/" + postObj.accountImage;
            } else {
                newPostProfilePic.src = "../img/default-pfp.jpg";
            }
            newPostUsername.appendChild(newPostProfilePic);
            newPostUsername.insertAdjacentText('beforeend', postObj.username);
            newPostBodyText.innerText = postObj.postMessage;
            newPostReply.addEventListener('click', function () { new CommentGetService().getCommentsByPost(postObj.postId); });
            newPostReply.innerText = "Reply";
            newPostLikes.innerText = "Like " + postObj.numOfLikes;
            newPostLikes.addEventListener('click', function () { new PostDisplayService().likePostById(postObj.postId); });
        }

        function appendChildren() {
            if (postHasImage) {
                newPostCard.appendChild(newPostImgHead);
            }
            newPostCard.appendChild(newPostUsername);
            newPostBody.appendChild(newPostBodyText);
            newPostBody.appendChild(newPostReply);
            newPostBody.appendChild(newPostLikes);
            newPostCard.appendChild(newPostBody);
            newPost.appendChild(newPostCard);
            ourPostContainer.appendChild(newPost);
        }
    }

    /**
     * Passes an ID.
     * Response is a single post.
     * @param {number} id 
     */
    likePostById(id) {
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                console.log(xhttp.responseText);
                let post = JSON.parse(xhttp.responseText);
                console.log("This is our response from likePostById: " + post)
                new PostDisplayService().likePost(post);
            }
        }

        xhttp.open('POST', `http://54.86.185.172:9099/likePostById/${id}`);
        xhttp.send();
    }

    likePost(post) {
        // for each value...There should only be one
        console.log(post);

        // take that object and update the contents (as in just the like amount)
        if (post.postId) {
            let newPostLikes = document.getElementById("postLike" + post.postId);
            newPostLikes.innerText = "Like " + post.numOfLikes;
        }
    }

}