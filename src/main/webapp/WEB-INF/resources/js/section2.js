//document.addEventListener('DOMContentLoaded' , function(){
//   let carousel = document.getElementById('carousel');
//    if(carousel){
//
//        let currentIndex = 0;
//        const carousel = document.getElementById('carousel');
//        const cards = carousel.children;
//        let cardsPerPage = calculateCardsPerPage();
//        const totalItems = cards.length;
//        let autoScrollInterval;
//
//        function calculateCardsPerPage() {
//            const containerWidth = document.querySelector('.carousel-container').offsetWidth;
//            const cardWidth = cards[0].offsetWidth + 16; // card width + margin-right
//            return Math.floor(containerWidth / cardWidth);
//        }
//
//        function updateCarousel() {
//            const cardWidth = cards[0].offsetWidth + 16; // card width + margin-right
//            const offset = currentIndex * cardWidth;
//            carousel.style.transform = `translateX(-${offset}px)`;
//
//            document.getElementById('prevBtn').disabled = currentIndex === 0;
//            document.getElementById('nextBtn').disabled = currentIndex >= totalItems - cardsPerPage;
//        }
//
//        function nextPage() {
//            console.log("next page");
//            if (currentIndex < totalItems - cardsPerPage) {
//                currentIndex++;
//            } else {
//                currentIndex = 0; // Reset to the first card after reaching the end
//            }
//            updateCarousel();
//        }
//
//        function prevPage() {
//            console.log("previous page");
//            if (currentIndex > 0) {
//                currentIndex--;
//            } else {
//                currentIndex = totalItems - cardsPerPage; // Go to the last card set if at the start
//            }
//            updateCarousel();
//        }
//
//        function startAutoScroll() {
//            autoScrollInterval = setInterval(nextPage, 2000); // Adjust time (in ms) as needed
//        }
//
//        function stopAutoScroll() {
//            clearInterval(autoScrollInterval);
//        }
//
//        // Recalculate cardsPerPage on window resize
//        window.addEventListener('resize', () => {
//            cardsPerPage = calculateCardsPerPage();
//            updateCarousel();
//        });
//
//        // Initial update and start auto-scroll
//        updateCarousel();
//        startAutoScroll();
//
//        // Pause auto-scroll on hover and resume on mouse leave
//        carousel.addEventListener('mouseover', stopAutoScroll);
//        carousel.addEventListener('mouseleave', startAutoScroll);
//
//    }
//});

document.addEventListener('DOMContentLoaded', function() {
    let carousel = document.getElementById('carousel');
    if (carousel) {
        let currentIndex = 0;
        const cards = carousel.children;
        let cardsPerPage = calculateCardsPerPage();
        const totalItems = cards.length;
        let autoScrollInterval;

        function calculateCardsPerPage() {
            const containerWidth = document.querySelector('.carousel-container').offsetWidth;
            const cardWidth = cards.length ? cards[0].offsetWidth + 16 : 0; // card width + margin-right
            return Math.floor(containerWidth / cardWidth);
        }

        function updateCarousel() {
            const cardWidth = cards.length ? cards[0].offsetWidth + 16 : 0; // card width + margin-right
            const offset = currentIndex * cardWidth;
            carousel.style.transform = `translateX(-${offset}px)`;

            document.getElementById('prevBtn').disabled = currentIndex === 0;
            document.getElementById('nextBtn').disabled = currentIndex >= totalItems - cardsPerPage;
        }

        function nextPage() {
            console.log("next page");
            if (currentIndex < totalItems - cardsPerPage) {
                currentIndex++;
            } else {
                currentIndex = 0; // Reset to the first card after reaching the end
            }
            updateCarousel();
        }

        function prevPage() {
            console.log("previous page");
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                currentIndex = totalItems - cardsPerPage; // Go to the last card set if at the start
            }
            updateCarousel();
        }

        function startAutoScroll() {
            autoScrollInterval = setInterval(nextPage, 2000); // Adjust time (in ms) as needed
        }

        function stopAutoScroll() {
            clearInterval(autoScrollInterval);
        }

        // Recalculate cardsPerPage on window resize
        window.addEventListener('resize', () => {
            cardsPerPage = calculateCardsPerPage();
            updateCarousel();
        });

        // Initial update and start auto-scroll
        updateCarousel();
        startAutoScroll();

        // Pause auto-scroll on hover and resume on mouse leave
        carousel.addEventListener('mouseover', stopAutoScroll);
        carousel.addEventListener('mouseleave', startAutoScroll);

        // Add event listeners to buttons
        document.getElementById('prevBtn').addEventListener('click', prevPage);
        document.getElementById('nextBtn').addEventListener('click', nextPage);
    }
});

