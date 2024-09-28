function showContent(contentNumber) {
    document.querySelectorAll('.content, .content1, .content3').forEach(content => {
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