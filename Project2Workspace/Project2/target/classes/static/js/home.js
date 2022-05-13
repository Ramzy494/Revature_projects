/**
 * 
 * @author darkm (Frederick)
 */

import { PostGetService } from './post-get-service.js';

let postGetService = new PostGetService();
let currentPage = 1;
let pageRangeStart = 1;
let pageCount = 1;
let paginationShow = 3;
let postsPerPage = 5;

// Onload we need to fetch the latest 5 posts
window.onload = function (event) {
    generatePagination();
    postGetService.getPostsByPage();
}

/**
 * Set the event listeners for pagination
 */
function generatePagination() {
    let paginationPrev = document.getElementById("paginationPrev");
    let paginationOne = document.getElementById("paginationOne");
    let paginationPlusOne = document.getElementById("paginationPlusOne");
    let paginationPlusTwo = document.getElementById("paginationPlusTwo");
    let paginationNext = document.getElementById("paginationNext");

    paginationPrev.addEventListener('click', paginationPrevFunc);
    paginationOne.addEventListener('click', function () { changePagination(parseInt(paginationOne.firstElementChild.textContent)); });
    paginationPlusOne.addEventListener('click', function () { changePagination(parseInt(paginationPlusOne.firstElementChild.textContent)); });
    paginationPlusTwo.addEventListener('click', function () { changePagination(parseInt(paginationPlusTwo.firstElementChild.textContent)); });
    paginationNext.addEventListener('click', paginationNextFunc);
    getPostCount();
}

/**
 * Request: None
 * Response: An integer.
 */
function getPostCount() {
    let respObj = "20";
    pageCount = respObj / postsPerPage;
    refreshPagination();
    // let xhttp = new XMLHttpRequest();

    // xhttp.onreadystatechange = function () {
    //     // console.log("readyState is changing: ", xhttp.readyState);
    //     if (xhttp.readyState == 4 && xhttp.status == 200) {
    //         // console.log(xhttp.responseText);
    //         let respObj = JSON.parse(xhttp.responseText);
    //         console.log("This is our response from fetchPostCount: " + respObj)
    //         pageCount = respObj / postsPerPage;
    //         refreshPagination();
    //     }
    // }

    // xhttp.open('POST', `http://domain:port/getPostCount`);
    // xhttp.send();
}

/**
 * Set the pagination innerText (what numbers they display)
 */
function refreshPagination() {
    let paginationPrev = document.getElementById("paginationPrev");
    let paginationOne = document.getElementById("paginationOne");
    let paginationPlusOne = document.getElementById("paginationPlusOne");
    let paginationPlusTwo = document.getElementById("paginationPlusTwo");
    let paginationNext = document.getElementById("paginationNext");

    paginationOne.firstElementChild.textContent = pageRangeStart.toString();
    paginationPlusOne.firstElementChild.textContent = (pageRangeStart + 1).toString();
    paginationPlusTwo.firstElementChild.textContent = (pageRangeStart + 2).toString();


    function prevNextEnable() {
        paginationPrev.className = "page-item";
        paginationNext.className = "page-item";
    }
    function prevDisable() {
        paginationPrev.className = "page-item disabled";
        paginationNext.className = "page-item";
    }
    function nextDisable() {
        paginationNext.className = "page-item disabled";
        paginationPrev.className = "page-item";
    }
    switch (true) {
        case (currentPage === 1):
            prevDisable();
            break;
        case (currentPage >= pageCount):
            nextDisable();
            break;
        default:
            prevNextEnable();
    }

    // Find active number
    paginationOne.className = "page-item";
    paginationPlusOne.className = "page-item";
    paginationPlusTwo.className = "page-item";
    if (paginationOne.firstElementChild.textContent == currentPage.toString()) {
        paginationOne.className = "page-item page-item active";
    } else if (paginationPlusOne.firstElementChild.textContent == currentPage.toString()) {
        paginationPlusOne.className = "page-item page-item active";
    } else if (paginationPlusTwo.firstElementChild.textContent == currentPage.toString()) {
        paginationPlusTwo.className = "page-item page-item active";
    }

    // finally check if the pagination numbers are greater than page count
    if (parseInt(paginationPlusOne.firstElementChild.textContent) > pageCount) {
        paginationPlusOne.className = "page-item disabled";
    }
    if (parseInt(paginationPlusTwo.firstElementChild.textContent) > pageCount) {
        paginationPlusTwo.className = "page-item disabled";
    }
}

function changePagination(num) {
    currentPage = num;
    if (currentPage <= 0) {
        currentPage = 1;
    } else if (currentPage > pageCount) {
        currentPage = pageCount;
    } else {
        postGetService.getPostsByPage(currentPage);
        getPostCount();
    }
}

function paginationPrevFunc() {
    currentPage--;
    pageRangeStart--;
    if (pageRangeStart <= 0) {
        pageRangeStart = 1;
    }
    if (currentPage <= 0) {
        currentPage++;
    } else {
        postGetService.getPostsByPage(currentPage);
        getPostCount();
    }
}

function paginationNextFunc() {
    currentPage++;
    pageRangeStart++;
    if (pageRangeStart + paginationShow - 1 > pageCount) {
        pageRangeStart--;
    }
    if (currentPage > pageCount) {
        currentPage--;
    } else {
        postGetService.getPostsByPage(currentPage);
        getPostCount();
    }
}

