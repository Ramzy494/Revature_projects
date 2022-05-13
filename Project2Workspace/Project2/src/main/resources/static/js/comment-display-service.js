/**
 * Handles displaying comments for a comment
 * 
 */

import { CommentGetService } from "./comment-get-service.js";

export class CommentDisplayService {
    constructor() {
        this.comments = [];
        this.commentService = new CommentGetService();
    }

    displayComments(postId) {
        let ourCommentContainer = document.getElementById("commentsContainer");
        ourCommentContainer.innerHTML = "";
        let newComment = document.createElement("li");
        let newCommentCard = document.createElement("div");
        let newCommentImgHead = document.createElement("img");
        let newCommentUsername = document.createElement("h5");
        let newCommentProfilePic = document.createElement("img"); // image element within h5
        let newCommentBody = document.createElement("div"); // div that is card body
        let newCommentBodyText = document.createElement("p"); // <p> that is text content
        let newCommentLikes = document.createElement("button");
        let commentHasImage = false;
        let ourPostReply = document.getElementById("postReply");
        ourPostReply.addEventListener('click', function () { this.commentService.replyToPost(postId); });

        // for each value...
        if ((this.comments) && !(this.comments.length === 0)) {
            Object.values(this.comments).forEach(commentObj => {
                console.log(commentObj);

                // take that object and dynamically make a card per comment object
                if (commentObj.commentId) {
                    if (commentObj.commentImage) {
                        commentHasImage = true;
                    } else {
                        commentHasImage = false;
                    }
                    initCommentElements();
                    addCommentContent(commentObj);
                    appendChildren();
                }
            });
        } else {
            ourCommentContainer.innerText = "No comments to display.";
        }

        /**
         * Before we make any element content, we need to initialize them.
         * @author darkm (Frederick)
         */
        function initCommentElements() {
            newComment = document.createElement("li");
            newCommentCard = document.createElement("div");
            if (commentHasImage) {
                newCommentImgHead = document.createElement("img");
            }
            newCommentUsername = document.createElement("h5");
            newCommentProfilePic = document.createElement("img"); // image element within h5
            newCommentBody = document.createElement("div"); // div that is card body
            newCommentBodyText = document.createElement("p"); // <p> that is text content
            newCommentLikes = document.createElement("button");

            // initialize the attribute values
            newComment.className = "list-group-item align-items-center text-center";
            newCommentCard.className = "card justify-content-center mb-2 mt-2 text-center container w-80";
            if (commentHasImage) {
                newCommentImgHead.className = "card-img-top";
                newCommentImgHead.alt = "Card image cap OPTIONAL";
            }
            newCommentUsername.className = "card-title";
            newCommentProfilePic.alt = "[Profile Picture]";
            newCommentProfilePic.width = "32";
            newCommentProfilePic.height = "32";
            newCommentBody.class = "card-body";
            newCommentBodyText.class = "card-text";
            newCommentLikes.className = "btn btn-primary float-end";
            newCommentLikes.type = "button";
        }

        /**
         * Add the content itself
         * @author darkm (Frederick)
         * @param {*} commentObj 
         */
        function addCommentContent(commentObj) {
            // {"commentId":1,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"commentImage":"","accountId":4,"username":"Agent 47","accountImage":null}

            // first set the IDs
            newComment.id = "commentContainer" + commentObj.commentId;
            newCommentCard.id = "open" + commentObj.commentId;
            if (commentHasImage) {
                newCommentImgHead.id = "commentImg" + commentObj.commentId;
            }
            newCommentProfilePic.id = "commentProfileImg" + commentObj.accountId;
            newCommentUsername.id = "commentUsername" + commentObj.commentId;
            newCommentBody.id = "commentBody" + commentObj.commentId;
            newCommentBodyText.id = "commentText" + commentObj.commentId;
            newCommentLikes.id = "commentLike" + commentObj.commentId;

            // Now add the content itself
            if (commentHasImage) {
                newCommentImgHead.id = "commentImg" + commentObj.commentImage;
            }
            if(commentObj.accountImage)
                newCommentProfilePic.src = commentObj.accountImage;
            newCommentUsername.appendChild(newCommentProfilePic);
            newCommentUsername.insertAdjacentText('beforeend', commentObj.username);
            newCommentBodyText.innerText = commentObj.commentMessage;
            newCommentLikes.innerText = "Like " + commentObj.numOfLikes;
            newCommentLikes.addEventListener('click', function () { new CommentDisplayService().likeCommentById(commentObj.commentId); });
        }

        function appendChildren() {
            // <!-- This is a comment example -->
            // <li id="commentContainer + whateverTheCommentIdIs" class="list-group-item align-items-center text-center">
            //     <div class="card d-flex mx-auto w-50 mb-2 mt-2" id="open + whateverTheCommentIdIs">
            //         <img class="card-img-top"
            //             src="https://i.pinimg.com/originals/7d/e5/03/7de503da9f70df23a54dd5f6dedeb6bd.jpg"
            //             alt="Card image cap OPTIONAL" id="commentImg + whateverTheCommentIdIs">
            //         <h5 class="card-title" id="commentUsername + whateverTheUserIdIs"><img src="" alt="[Profile Picture]"
            //                 width="32" height="32">Aang</h5>
            //         <div class="card-body" id="body + whateverTheCommentIdIs">
            //             <p class="card-text" id="commentText + whateverTheCommentIdIs">
            //                 Yip yip!</p>
            //             <button id="commentLike + whateverTheCommentIdIs" type="button" class="btn btn-primary float-end">Like
            //                 0</button>
            //         </div>
            //     </div>
            // </li>
            // <!-- END EXAMPLE POST -->
            if (commentHasImage) {
                newCommentCard.appendChild(newCommentImgHead);
            }
            newCommentCard.appendChild(newCommentUsername);
            newCommentBody.appendChild(newCommentBodyText);
            newCommentBody.appendChild(newCommentLikes);
            newCommentCard.appendChild(newCommentBody);
            newComment.appendChild(newCommentCard);
            ourCommentContainer.appendChild(newComment);
        }
    }


    likeCommentById(id) {
        let comment = JSON.parse(`{"commentId":1,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":3,"commentImage":"","accountId":4,"username":"Agent 47","accountImage":null}`);
        this.likeComment(comment);

         let xhttp = new XMLHttpRequest();

         xhttp.onreadystatechange = function () {
              console.log("readyState is changing: ", xhttp.readyState);
             if (xhttp.readyState == 4 && xhttp.status == 200) {
                  console.log(xhttp.responseText);
                 this.comments = JSON.parse(xhttp.responseText);
                 console.log("This is our response from likeCommentByPage: " + respObj)
                 likeComment();
             }
         }

         xhttp.open('POST', `http://domain:port/likeCommentById`);
         xhttp.send(id);
    }

    likeComment(comment) {
        // for each value...There should only be one
        console.log(comment);

        // take that object and update the contents (as in just the like amount)
        if (comment.commentId) {
            let newCommentLikes = document.getElementById("commentLike" + comment.commentId);
            newCommentLikes.innerText = "Like " + comment.numOfLikes;
        }
    }
}

