/**
 * Sends AJAX requests and returns given this.posts.
 * @author darkm (Frederick)
 * @implements post-display-service
 */
import { PostDisplayService } from './post-display-service.js';


export class PostGetService {
    constructor() { this.posts = [];}

    // display all the this.posts I am given in whatever container has ID "postContainer"
    displayPosts(ourObjectFromJSON) {
        let postService = new PostDisplayService();
        postService.posts = ourObjectFromJSON;
        postService.displayPosts();
        
    }

    likePost(id) {
        let postService = new PostDisplayService();
        postService.likePostById(id);
    }

    /**
    * Request: An integer of the current page
    * Response: An array of Page objects (should be length 5)
    */
    getPostsByPage(currentPage) {
        let respObj = JSON.parse(
            '[{"postId":1,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"postImage":"","accountId":4,"username":"Agent 47","accountImage":null},{"postId":2,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":4,"postImage":"","accountId":5,"username":"Gust Man","accountImage":null},{"postId":3,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"postImage":null,"accountId":4,"username":"Agent 47","accountImage":null},{"postId":4,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":3,"postImage":"","accountId":4,"username":"Agent 47","accountImage":""},{"postId":5,"postMessage":"Super fighting robot. MEGA MAN!","numOfLikes":2,"postImage":"","accountId":4,"username":"Agent 47","accountImage":""}]');
        this.displayPosts(respObj);

         let xhttp = new XMLHttpRequest();

         xhttp.onreadystatechange = function () {
         console.log("readyState is changing: ", xhttp.readyState);
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                  console.log(xhttp.responseText);
                let respObj = JSON.parse(xhttp.responseText);
                 console.log("This is our response from getPostsByPage: " + respObj)
                 displayPosts(respObj);
             }
         }

         xhttp.open('POST', `http://domain:port/getPostByPage`);
         xhttp.send(currentPage);
    }

    /**
     * Get one post, update the number of likes by 1, and
     * refresh the likes amount in JavaScript as well.
     * Request pass: id
     * Response: Post with renewed like count + 1
     * Called when a post is liked.
     */
     likePostById(id) {
        this.likePost(id);
    }

}
