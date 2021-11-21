document.addEventListener('DOMContentLoaded', () => {
	const elementosCarousel = document.querySelectorAll('.carousel');
    M.Carousel.init(elementosCarousel, {
    	duration: 300,
    	shift: 5,
    	padding: 5,
    	dist: -80,
    	numVisible: 3
    });

});
