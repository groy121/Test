window.onload = function (e) {
    //once everything is loaded hide the preloader and display the content
    hideDisplay('.preloader');
    showDisplay('#main-wrapper');
    var userElem = getElement('.dropdown-toggle');
    var userElemToggle = false;
    userElem.addEventListener('click', function (e) {
        userElemToggle = toggleElem('.dropdown-menu', userElemToggle);
    });
    showMainBodyElements('dashboard');
    var dashboard = getElement('#dashboard');
    var create = getElement('#create');
    var update = getElement('#update');
    var view = getElement('#view');
    var del = getElement('#delete');

    dashboard.addEventListener('click', function (e) {
        showMainBodyElements('dashboard');
    });

    create.addEventListener('click', function (e) {
        showMainBodyElements('create');
    });

    update.addEventListener('click', function (e) {
        showMainBodyElements('update');
    });

    view.addEventListener('click', function (e) {
        showMainBodyElements('view');
    });

    del.addEventListener('click', function (e) {
        showMainBodyElements('delete');
    });

    var createElem = getElement('#createForm');
    createElem.addEventListener('submit',function(e){
        e.preventDefault();
        console.log(this.type.value);
        console.log(this.name.value);
        console.log(this.revision.value);
        console.log(this.policy.value);
        console.log(this.vault.value);
        
    });
}

getElement = function (selector) {
    var doc = document.querySelector(selector);
    return doc;
}

toggleElem = function (selectorForToggle, toggle) {
    if (toggle) {
        hideDisplay(selectorForToggle);
    } else {
        showDisplay(selectorForToggle);
    }
    return !toggle;
}

showDisplay = function (selector) {
    var doc = document.querySelector(selector);
    if (doc) {
        doc.style.display = "block";
    }
}

hideDisplay = function (selector) {
    var doc = document.querySelector(selector);
    if (doc) {
        doc.style.display = "none";
    }
}

showMainBodyElements = function (elemSelector) {
    var dashboard = getElement('.dashboard');
    var create = getElement('.create');
    var update = getElement('.update');
    var view = getElement('.view');
    var del = getElement('.delete');
    if (elemSelector === 'dashboard') {
        dashboard.style.display = "block";
        getElement('#opened').innerHTML = "Dashboard";
        //setFileContentToElement('./dashboard.html', dashboard);
    } else {
        dashboard.style.display = "none";
    }
    if (elemSelector === 'create') {
        create.style.display = "block";
        getElement('#opened').innerHTML = "Create";
        //setFileContentToElement('./create.html', create);
    } else {
        create.style.display = "none";
    }
    if (elemSelector === 'update') {
        update.style.display = "block";
        getElement('#opened').innerHTML = "Update";
        //setFileContentToElement('./update.html', update);
    } else {
        update.style.display = "none";
    }
    if (elemSelector === 'view') {
        view.style.display = "block";
        getElement('#opened').innerHTML = "View";
        //setFileContentToElement('./view.html', view);
    } else {
        view.style.display = "none";
    }
    if (elemSelector === 'delete') {
        del.style.display = "block";
        getElement('#opened').innerHTML = "Delete";
        //setFileContentToElement('./delete.html', del);
    } else {
        del.style.display = "none";
    }
}

// setFileContentToElement = function (url, element) {
//     var client = new XMLHttpRequest();
//     client.open('GET', url);
//     client.onreadystatechange = function () {
//         element.innerHTML = client.responseText;
//     }
//     client.send();
// }

// init = function() {
//     showMainBodyElements('create');
//     showMainBodyElements('update');
//     showMainBodyElements('view');
//     showMainBodyElements('delete');
//     showMainBodyElements('dashboard');
// }


