
function logIn(){
    console.log("log in button  is is working");
    
    let currentURL = window.location.href;
    let url = new URL(currentURL);
    url.pathname = url.pathname + "login";
    window.location.href = url.href;
}

function logOut(){
    let currentURL = window.location.href;
    let url = new URL(currentURL);
    url.pathname = url.pathname + "logout";
    window.location.href = url.href;
}

function accountDetail(){
    console.log("working button account");
    let currentURL = window.location.href;
    let url = new URL(currentURL);
    url.pathname = url.pathname + "account";
    window.location.href = url.href;
}

function Register(){
    console.log("working button account");
    let currentURL = window.location.href;
    let url = new URL(currentURL);
    url.pathname = url.pathname + "register";
    window.location.href = url.href;    
}

function getStarted(){
    window.location.href = "/login";
}