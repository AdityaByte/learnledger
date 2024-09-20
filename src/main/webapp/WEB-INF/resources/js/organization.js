document.getElementById('orgForm').addEventListener('submit' , function(event){
    event.preventDefault();
    let formData = new FormData(this);
    $.ajax({
        url: "/org/form",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            console.log("sucess - " , response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("error" , errorThrown);
        }
    });
});