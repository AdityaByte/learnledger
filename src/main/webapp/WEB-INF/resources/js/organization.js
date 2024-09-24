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
