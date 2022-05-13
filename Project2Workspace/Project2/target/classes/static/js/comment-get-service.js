/**
 * Handles getting the comments.
 * @author darkm (Fred)
 */

import { CommentDisplayService } from "./comment-display-service.js";

export class CommentGetService {
    constructor() { this.comments = []; }

    // display all the this.comments I am given in whatever container has ID "commentContainer"
    displayComments(postId, ourObjectFromJSON) {
        let commentService = new CommentDisplayService();
        commentService.comments = ourObjectFromJSON;
        commentService.displayComments(postId);
    }

    likeComment(id) {
        let commentService = new CommentDisplayService();
        commentService.likeCommentById(id);
    }

    /**
     * Insert a comment through AJAX.
     * Return all of this posts' comments and put them in the modal.
     * @param {number} postId 
     * @param {string} message 
     */
    insertComment(postId, message) {
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
             console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                 console.log(xhttp.responseText);
                let respObj = JSON.parse(xhttp.responseText);
                console.log("This is our response from insertComment: " + respObj)
                displayComments(respObj);
            }
        }

        // Data will be ObjectMapped into a CommentModel
        let data = {
            commentMessage:message,
            postId:postId
        }

        xhttp.open('comment', `http://domain:port/insertComment`);
        xhttp.send(data);
    }

    /**
    * Request: An integer of the current page
    * Response: An array of Page objects (should be length 5)
    */
    getCommentsByPost(postId) {
        let respObj = JSON.parse(
            '[{"commentId":1,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"commentImage":"","accountId":4,"username":"Agent 47","accountImage":null},{"commentId":2,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":4,"commentImage":"","accountId":5,"username":"Gust Man","accountImage":null},{"commentId":3,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"commentImage":null,"accountId":4,"username":"Agent 47","accountImage":null},{"commentId":4,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":3,"commentImage":"","accountId":4,"username":"Agent 47","accountImage":""},{"commentId":5,"commentMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"commentImage":"","accountId":4,"username":"Agent 47","accountImage":""}]');
        this.displayComments(postId, respObj);

         let xhttp = new XMLHttpRequest();

         xhttp.onreadystatechange = function () {
              console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                  console.log(xhttp.responseText);
                 let respObj = JSON.parse(xhttp.responseText);
                 console.log("This is our response from getCommentsByPage: " + respObj)
                 displayComments(respObj);
             }
         }

         xhttp.open('comment', `http://domain:port/getCommentsByPost`);
         xhttp.send(postId);
    }

    /**
     * Get one comment, update the number of likes by 1, and
     * refresh the likes amount in JavaScript as well.
     * Request pass: id
     * Response: comment with renewed like count + 1
     * Called when a comment is liked.
     */
    likeCommentById(id) {
        this.likeComment(id);
    }

    /**
     * Reply to a post (insert comment)
     */
    replyToPost(id) {
        this.insertComment(id, document.getElementById("commentInput").value);
        this.displayComments(id);
    }
}


