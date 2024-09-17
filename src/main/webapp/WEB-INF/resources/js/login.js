const backButton = document.getElementById('backButton');

if (backButton) {
    backButton.addEventListener('click', function (event) {
        event.preventDefault();
        window.history.back();
    });
}

document.getElementById('loginForm').addEventListener('submit' , function(event){
    event.preventDefault();
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let typeofUser = document.getElementById('typeofUser').value;
    
    let messageDiv = document.getElementById('message-div');
    
    if(email === "" || password === ""){
        messageDiv.style.display = "block";
        messageDiv.innerHTML = "Fields are empty! Please fill out the fields";
    }
    else{
        messageDiv.style.display = "block";
        messageDiv.innerHTML = "Checking Email and password";
        $.ajax({
            url: "login",
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email : email, 
                password : password, 
                typeofUser : typeofUser
            }),
            success: function(response){
                if(response.status === "success"){
                    window.location.href = "/learnledger/home";
                }
                else if(response.status === "failure"){
                    messageDiv.style.display = "block";
                    messageDiv.innerHTML = "Password is incorrect !!";    
                }
                else{
                    messageDiv.style.display = "block";
                    messageDiv.innerHTML = "Sorry!! We can't proceed further";
                }
            },
            error: function(xhr, status, error){
                console.log(" i am in the error section");
                messageDiv.style.display = "block";
//                messageDiv.innerHTML = error + " " + status + " " + xhr;
                messageDiv.innerHTML= xhr;
            }
        });
    }
    
});