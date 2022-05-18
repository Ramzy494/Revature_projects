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
        if (commentService.comments) {
            commentService.displayComments(postId);
        }
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
            if (xhttp.readyState == 4 && xhttp.status == 201) {
                console.log(xhttp.responseText);
                let respObj = JSON.parse(xhttp.responseText);
                console.log("This is our response from insertComment: " + respObj);
                new CommentGetService().getCommentsByPost(postId);
            }
        }

        // Data will be ObjectMapped into a CommentModel
        let data = {
            commentMessage: message,
            post: {
                postId: postId
            }
        }

        xhttp.open('POST', `http://54.86.185.172:9099/insertComment`);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(data));
    }

    /**
    * Request: An integer of the current page
    * Response: An array of Page objects (should be length 5)
    */
    getCommentsByPost(id) {
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                console.log(xhttp.responseText);
                let respObj = JSON.parse(xhttp.responseText);
                console.log("This is our response from getCommentsByPost: " + respObj)
                let commentService = new CommentDisplayService();
                commentService.comments = respObj;
                commentService.displayComments(id);
            }
        }
        xhttp.open('GET', `http://54.86.185.172:9099/getCommentsByPost/${id}`);
        xhttp.send();
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
        if (document.getElementById("commentInput").value) {
            document.getElementById('commentInput').style.borderColor = "none";
            document.getElementById('commentInput').placeholder = ""
            this.insertComment(id, document.getElementById("commentInput").value);
        } else {
            document.getElementById('commentInput').style.borderColor = "red";
            document.getElementById('commentInput').placeholder = "Please input a comment."
            console.log("No comment text.");
        }
    }
}

