let currentContent = 'content1'; 

// Function to show and hide content sections
function switchContent(showId, hideId) {
    document.getElementById(showId).style.display = 'flex';
    document.getElementById(hideId).style.display = 'none';
    currentContent = showId; 
    localStorage.setItem('currentForm', showId); // Save current form in localStorage
}

window.addEventListener('load', function() {
    const savedForm = localStorage.getItem('currentForm');
    if (savedForm) {
        // Hide all contents first
        document.querySelectorAll('.content').forEach((content) => {
            content.style.display = 'none';
        });
        // Show the saved form
        document.getElementById(savedForm).style.display = 'flex';
        currentContent = savedForm; // Update the current content state
    }
});

// Form1 submission
document.getElementById('form1').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();

    if (username === "" || email === "") {
        document.getElementById('message-1').style.display = "block";
        document.getElementById('message-1').innerHTML = "Please fill out the fields";
    }
    else if(username.length <= 6){
        document.getElementById('message-1').style.display = "block";
        document.getElementById('message-1').innerHTML = "Username should be greater than 6 character";    
    }
    else {
        $.ajax({
            url: 'register/handleform1',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ username: username, email: email }),
            success: function(response) {
                if (response.status === "success") {
                    console.log(response);
                    switchContent('content2', 'content1');
                }
                else {
                    document.getElementById('message-1').style.display = "block";
                    document.getElementById('message-1').innerHTML = "Sorry!! But you can't proceed further";
                }
            },
            error: function(xhr, status, error) {
                
                if(error === "emailExists"){
                    document.getElementById('message-1').style.display = "block";
                    document.getElementById('message-1').innerHTML = "Email Already Exist!! try to Login";
                    console.log("ajax error", status, error);
                }
                else{
                    document.getElementById('message-1').style.display = "block";
                    document.getElementById('message-1').innerHTML = "An error occurred while processing your request";
                    console.log("ajax error", status, error);
                }
            }
        });
    }
});

// Form2 submission
document.getElementById('form2').addEventListener('submit', function(event) {
    event.preventDefault();
    const password = document.getElementById('pass').value.trim();
    const confirmPassword = document.getElementById('cPass').value.trim();

    if (password === "" || confirmPassword === "") {
        document.getElementById('message-2').style.display = "block";
        document.getElementById('message-2').innerHTML = "Fields are empty";
    }
    else if(password.length <= 6){
        document.getElementById('message-2').style.display = "block";
        document.getElementById('message-2').innerHTML = "Password should be greater than 6 character";    
    }
    else if (password !== confirmPassword) {
        document.getElementById('message-2').style.display = "block";
        document.getElementById('message-2').innerHTML = "Passwords are different! Password must be ideal";
    } else {
        document.getElementById('message-2').style.display = "block";
        document.getElementById('message-2').innerHTML = "OTP is sent to your email";
        document.getElementById('message-2').style.borderColor = "green";
        document.getElementById('message-2').style.color = "green";

        $.ajax({
            url: 'register/handleform2',
            type: 'POST',
            data: $.param({ password: password }),
            success: function(response) {
                console.log(response);
                if (response.status === "success") {
                    console.log(response);
                    switchContent('content3', 'content2'); // Switch to form3
                } else {
                    document.getElementById('message-2').style.display = "block";
                    document.getElementById('message-2').innerHTML = "Sorry!! But you can't proceed further";
                }
            },
            error: function(xhr, status, error) {
                document.getElementById('message-2').style.display = "block";
                document.getElementById('message-2').innerHTML = "An error occurred while processing your request";
                console.log("ajax error", status, error);
            }
        });
    }
});

// Form3 submission
document.getElementById('form3').addEventListener('submit', function(event) {
    event.preventDefault();
    const otp = document.querySelector('#form3 input[type="text"]').value.trim();
    if (otp === "") {
        document.getElementById('message-3').style.display = "block";
        document.getElementById('message-3').innerHTML = "OTP Field is empty";
    } else {
        $.ajax({
            url: "register/checkOTP",
            data: $.param({ otp: otp }), 
            type: 'POST',
            success: function(response) {
                if (response.status === "success") {
                    window.location.href = '/learnledger';
                } 
                else if(response.status === "otpNotMatched"){
                    document.getElementById('message-3').style.display = "block";
                    document.getElementById('message-3').innerHTML = "OTP not matched !! You can't proceed further";
                }
                else{
                    document.getElementById('message-3').style.display = "block";
                    document.getElementById('message-3').innerHTML = "Sorry!! Getting some error while processing your request";
                    
                }
            },
            error: function(xhr, status, error) {
                document.getElementById('message-3').style.display = "block";
                document.getElementById('message-3').innerHTML = "An error occurred while processing your request";
                console.log("ajax error", status, error);
            }
        });
    }
});

// Back icon functionality
document.getElementById('back-icon').addEventListener('click', function(event) {
    event.preventDefault();
    if (currentContent === 'content3') {
        switchContent('content2', 'content3'); // Go back from form3 to form2
    } else if (currentContent === 'content2') {
        switchContent('content1', 'content2'); // Go back from form2 to form1
    } else if (currentContent === 'content1') {
        window.location.href = '/learnledger'; // Replace with the actual URL
    }
});