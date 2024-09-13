const backButton = document.getElementById('backButton');

if (backButton) {
    backButton.addEventListener('click', function (event) {
        event.preventDefault();
        window.history.back();
    });
}

