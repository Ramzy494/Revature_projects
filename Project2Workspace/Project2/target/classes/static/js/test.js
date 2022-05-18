/**
 * Testing for input of image upload
 */

import {ImageUploadService} from './image-upload-service.js';

window.onload = function(event) {
    document.getElementById("testPost").addEventListener('click', testUploadImagePost);
    document.getElementById("testAccount").addEventListener('click', testUploadImageAccount);
}

function testUploadImagePost() {
    let imageUpload = new ImageUploadService(1);
    imageUpload.uploadImagePost();
}

function testUploadImageAccount() {
    let imageUpload = new ImageUploadService(1);
    imageUpload.uploadImageAccount();
}