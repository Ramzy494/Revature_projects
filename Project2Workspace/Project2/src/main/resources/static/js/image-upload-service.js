/**
 * Handles the AJAX requests for getting or uploading an image. (Profile pictures and posts).
 * Fetches the image automatically when constructed.
 */

// <input id="uploadImage" type="file" name="image" accept="image/png, image/jpeg" />

export class ImageUploadService {
    image; // our image value.
    id; // our ID (for both a post or an account)

    /**
     * @param {Image} image what image am I uploading?
     * @param {number} id what is the ID of the DB item I am updating?
     */
    constructor(id) {
        this.image = null;
        // console.log("Here is our image: " + this.image);
        this.id = id;
    }

    /**
     * Given an image to send, and the ID of the post it is attached to.
     * POST MUST EXIST FIRST.
     * @author darkm (Frederick)
     */
    uploadImagePost() {
        this.image = document.getElementById('uploadImagePost').files[0];
        if(this.image) {
            this.uploadImagePostAjax();
        } else {
            console.log("Oi! I have no image to upload for this post!");
        }
    }

    /**
     * Given an image to send, and the ID of the account it is attached to.
     * ACCOUNT MUST EXIST FIRST.
     * @author darkm (Frederick)
     */
    uploadImageAccount() {
        this.image = document.getElementById('uploadImageAccount').files[0];
        if(this.image) {
            this.uploadImageAccountAjax();
        } else {
            console.log("Oi! I have no image to upload for this account!");
        }
    }

    /**
     * Send an AJAX request to upload a post image.
     * @author darkm (Frederick)
     */
    uploadImagePostAjax() {
        let formData = new FormData();
        formData.append("image", this.image);
        formData.append("id", this.id);

        let xhttp = new XMLHttpRequest();
        
        xhttp.onreadystatechange = function () {

            if(xhttp.readyState == 4 && xhttp.status == 200) {
                console.log(xhttp.responseText);
            }

        }

        xhttp.open('POST', 'http://54.86.185.172:9099/uploadImagePost');
        xhttp.send(formData);
    }

    /**
     * Send an AJAX request to upload an account image.
     * @author darkm (Frederick)
     */
    uploadImageAccountAjax() {
        let formData = new FormData();
        formData.append("image", this.image);
        formData.append("id", this.id);

        let xhttp = new XMLHttpRequest();
        
        xhttp.onreadystatechange = function () {

            if(xhttp.readyState == 4 && xhttp.status == 200) {
                console.log(xhttp.responseText);
            }

        }

        xhttp.open('POST', 'http://54.86.185.172:9099/uploadImageUser');
        xhttp.send(formData);
    }

}

