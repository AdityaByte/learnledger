const backButton = document.getElementById('backButton');

if (backButton) {
    backButton.addEventListener('click', function (event) {
        event.preventDefault();
        window.history.back();
    });
}

const orgForm = document.getElementById('orgForm');

document.addEventListener("DOMContentLoaded" , function(){
    
    orgForm.addEventListener('submit' , (event) => {
        event.preventDefault();
        let formData = new FormData(orgForm);
        $.ajax({
            url: 'form',
            method: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response){
                console.log("Full response : " , response);
                console.log("Reponse Status : ",response.status);
                
                if(response.status === "success"){
                    const popup = document.getElementById("popup");
                    const closePopup = document.getElementById("closePopup");
                    const msg = document.getElementById('otp-message');

                    msg.style.display = "block";
                    msg.innerHTML = "Proccessing request! Sending otp";
                    popup.style.display = "flex";
                    
                    const otpForm = document.getElementById('otpForm')
                    
                    otpForm.addEventListener('submit' , function(event){
                        event.preventDefault();
                        let otp = document.getElementById('otp').value;
                        if(otp !== ""){
                            $.ajax({
                                url: "validateOTP",
                                type: 'POST',
                                contentType: 'application/json',
                                data: JSON.stringify({otp : otp}),
                                success: function(response){
                                    if(response.status === "success"){
                                        msg.style.display = "block";
                                        msg.innerHTML = "OTP is Valid !!";
                                        alert("Your email is -> " + response.email + " and password is -> " + response.password);
                                        window.location.href = window.location.origin + "/learnledger/";

                                    }
                                    else if(response.status === "failure"){
                                        msg.style.display = "block";
                                        msg.innerHTML = "OTP is Invalid !!";
                                    }
                                },
                                error: function(error){
                                    console.log("error -> " , error);
                                }
                            });
                        }
                    });

                    closePopup.addEventListener("click", function() {
                        popup.style.display = "none";
                    });

                    window.addEventListener("click", function(event) {
                        if (event.target === popup) {
                            popup.style.display = "none";
                        }
                    });
                }
            },
            error: function(error){
                console.log("Error : " , error);
            }  
        });
    });
    
});

function displayFileName1(event) {
    const input = event.target;
    const fileName = input.files[0] ? input.files[0].name : "No file selected";
    
    document.getElementById("file-name1").textContent = fileName;
}

function displayFileName2(event) {
    const input = event.target;
    const fileName = input.files[0] ? input.files[0].name : "No file selected";
    
    document.getElementById("file-name2").textContent = fileName;
}


