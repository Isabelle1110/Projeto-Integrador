function scrollCarousel(direction) {
    const carousel = document.getElementById("carousel");
    const cardWidth = 350; // tamanho médio para rolar exatamente 1 card
    carousel.scrollBy({
        left: direction * cardWidth,
        behavior: "smooth"
    });
}
