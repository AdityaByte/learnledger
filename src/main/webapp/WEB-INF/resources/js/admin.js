function showContent(contentNumber) {
    document.querySelectorAll('.content, .content1, .content2 , .content3').forEach(content => {
        content.style.display = 'none';
    });
    document.getElementById(`content${contentNumber}`).style.display = 'block';
}

const buttons = document.querySelectorAll('.button-user-more');
buttons.forEach((button) => {
    button.addEventListener('click', function() {
        const bottomDiv = this.parentElement.nextElementSibling;

        if (this.innerHTML === "More") {
            bottomDiv.style.display = "flex";
            this.innerHTML = "Less";
        } else {
            bottomDiv.style.display = "none";
            this.innerHTML = "More";
        }
    });
});

showContent(1);

const documentForm = document.getElementById("documentForm");

document.addEventListener('submit' , function(event){
    event.preventDefault();
    const formData = new FormData(documentForm);
    $.ajax({
        url: "document",
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response){
            if(response.status == "success"){
                alert("Data saved successfully");
            }
            else{
                alert("Some error occured while saving data");
            }
        },
        error: function(error , xhr , status){
            console.log("error = " , error);
            console.log("xhr = " , xhr);
            console.log("status = " , status);
        }
    });
});