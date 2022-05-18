window.onload = () => {
    document.getElementById("searchButton").addEventListener("click", loadResults);
}

function loadResults() {
    let xmlhttp = new XMLHttpRequest();

    let criteria = document.getElementById("searchInput").value;
    clearSearchInput();

    xmlhttp.onreadystatechange = () => {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let results = JSON.parse(xmlhttp.responseText);
            displaySearchResults(results);
        }
    }

    xmlhttp.open("POST", `http://localhost:9099/SearchUser`);
    xmlhttp.setRequestHeader('content-type', 'application/json');
    xmlhttp.send(JSON.stringify(criteria));
}

function displaySearchResults(results) {
    let url = "http://localhost:9099/profile/";
    for(let result in results) {
        newAnchor = document.createElement("a");
        newAnchor.setAttribute("href", (url + result.username));
        newAnchorText = document.createTextNode(result.username);
        newAnchor.appendChild(newAnchorText);
        document.getElementById("searchBody").appendChild(newAnchor);
    }
}

function clearSearchInput() {
    document.getElementById("searchInput").value = "";
}