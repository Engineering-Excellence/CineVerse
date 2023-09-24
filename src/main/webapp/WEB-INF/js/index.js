'use strict'

const apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
const boxOfficeApiKey = "65ae15380317da54a96c6c7306a46bf4";

$(document).ready(function () {

    $(".fullpage").fullpage({

        navigation: true,
        navigationPosition: 'right',
        navigationTooltips: [
            "hello",
            "welcome",
            "good-day",
            "nice"
        ],

        controlArrows: true,
        slidesNavigation: true,
        slidesNavPosition: 'bottom',
        keyboardScrolling: true,
    });

    let today = new Date();
    today.setDate(today.getDate() - 1);
    $.ajax({
        url: `https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=${boxOfficeApiKey}&targetDt=${getDateFormatString(today)}&itemPerPage=5`,
        type: "get",
        async: true,
        success: (data) => {
            data = data["boxOfficeResult"]["dailyBoxOfficeList"];
            let html = "";
            data.forEach((i) => {
                html += `<div class='carousel__item'>`
                html += `<div class='carousel__item-head'>`
                html += `${i["rank"]}`
                html += `</div>`
                html += `<div class='carousel__item-body'>`
                html += `<p class='title'>${i["audiCnt"]}명 (${i["audiChange"][0] == '-' ? i["audiChange"] : "+" + i["audiChange"]}%)</p>`
                html += `<p>${i["movieNm"]}</p>`
                html += `</div>`
                html += `</div>`
            })
            $("#daily-boxoffice").append(html);
        },
    });

    today.setDate(today.getDate() - 6);
    $.ajax({
        url: `https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=${boxOfficeApiKey}&targetDt=${getDateFormatString(today)}&weekGb=0&itemPerPage=5`,
        type: "get",
        async: true,
        success: (data) => {
            data = data["boxOfficeResult"]["weeklyBoxOfficeList"];
            let html = "";
            data.forEach((i) => {
                html += `<div class='carousel__item'>`
                html += `<div class='carousel__item-head'>`
                html += `${i["rank"]}`
                html += `</div>`
                html += `<div class='carousel__item-body'>`
                html += `<p class='title'>${i["audiCnt"]}명 (${i["audiChange"][0] == '-' ? i["audiChange"] : "+" + i["audiChange"]}%)</p>`
                html += `<p>${i["movieNm"]}</p>`
                html += `</div>`
                html += `</div>`
            })
            $("#weekly-boxoffice").append(html);
        },
    });

    if (isLogin) {
        $.ajax({
            url: "/movie/loved/" + username,
            type: "get",
            async: true,
            success: (res) => {
                if (res.length > 0) {
                    let loved = res[Math.floor(Math.random() * res.length)];
                    let html = "";
                    $.ajax({
                        url: `https://api.themoviedb.org/3/movie/${loved}/recommendations?api_key=${apiKey}&language=ko-KR`,
                        type: "get",
                        async: false,
                        success: (data) => {
                            let list = data["results"].filter((d) => d["adult"] == false);
                            for (let i = 0; i < Math.min(3, list.length); i++) {
                                html += `<div class="Movie">`;
                                html += `<img src = "http://image.tmdb.org/t/p/w342${list[i]["poster_path"]}" class="movie-img">`;
                                html += `<div class="Summary">`;
                                html += `<h2>${list[i]["title"]}</h2>`;
                                html += `<p>${list[i]["overview"].substr(0, 150)}${list[i]["overview"].length >= 150 ? "..." : ""}</p>`;
                                html += `<a href="/movie/view?id=${list[i]["id"]}" class="moivie-sum">상세보기</a>`;
                                html += `</div>`;
                                html += `</div>`;
                            }
                        },
                    });
                    $(".Background").append(html);
                } else {
                    $.ajax({
                        url: `https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&region=KR&page=1&language=ko-KR`,
                        type: "get",
                        async: true,
                        success: (res) => {
                            let html = "";
                            let list = res["results"];
                            for (let i = 0; i < Math.min(3, list.length); i++) {
                                html += `<div class="Movie">`;
                                html += `<img src = "http://image.tmdb.org/t/p/w342${list[i]["poster_path"]}" class="movie-img">`;
                                html += `<div class="Summary">`;
                                html += `<h2>${list[i]["title"]}</h2>`;
                                html += `<p>${list[i]["overview"].substr(0, 150)}${list[i]["overview"].length >= 150 ? "..." : ""}</p>`;
                                html += `<a href="/movie/view?id=${list[i]["id"]}" class="moivie-sum">상세보기</a>`;
                                html += `</div>`;
                                html += `</div>`;
                            }
                            $(".Background").append(html);
                        },
                    });
                }
            },
        });
    } else {
        $.ajax({
            url: `https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&region=KR&page=1&language=ko-KR`,
            type: "get",
            async: true,
            success: (res) => {
                let html = "";
                let list = res["results"];
                for (let i = 0; i < Math.min(3, list.length); i++) {
                    html += `<div class="Movie">`;
                    html += `<img src = "http://image.tmdb.org/t/p/w342${list[i]["poster_path"]}" class="movie-img">`;
                    html += `<div class="Summary">`;
                    html += `<h2>${list[i]["title"]}</h2>`;
                    html += `<p>${list[i]["overview"].substr(0, 150)}${list[i]["overview"].length >= 150 ? "..." : ""}</p>`;
                    html += `<a href="/movie/view?id=${list[i]["id"]}" class="moivie-sum">상세보기</a>`;
                    html += `</div>`;
                    html += `</div>`;
                }
                $(".Background").append(html);
            },
        });
    }
});

const getDateFormatString = (date) => {
    let ret = "";
    ret += date.getFullYear();
    ret += pad(date.getMonth() + 1, 2);
    ret += pad(date.getDate(), 2);
    return ret;
}

const pad = (number, length) => {
    var str = '' + number;
    while (str.length < length) {
        str = '0' + str;
    }
    return str;
}

var bg = document.querySelector('.item-bg');
var items = document.querySelectorAll('.news__item');
var item = document.querySelector('.news__item');

function cLog(content) {
    console.log(content)
}

if ($(window).width() > 800) {
    $(document).on("mouseover", ".news__item", function (_event, _element) {

        var newsItem = document.querySelectorAll('.news__item');
        newsItem.forEach(function (element, index) {
            element.addEventListener('mouseover', function () {
                var x = this.getBoundingClientRect().left;
                var y = this.getBoundingClientRect().top;
                var width = this.getBoundingClientRect().width;
                var height = this.getBoundingClientRect().height;

                $('.item-bg').addClass('active');
                $('.news__item').removeClass('active');
                // $('.news__item').removeClass('active');

                bg.style.width = width + 'px';
                bg.style.height = height + 'px';
                bg.style.transform = 'translateX(' + x + 'px ) translateY(' + y + 'px)';
            });

            element.addEventListener('mouseleave', function () {
                $('.item-bg').removeClass('active');
                $('.news__item').removeClass('active');
            });
        });
    });
}

var swiper = new Swiper('.news-slider', {
    effect: 'coverflow',
    grabCursor: true,
    loop: true,
    centeredSlides: true,
    keyboard: true,
    spaceBetween: 0,
    slidesPerView: 'auto',
    speed: 300,
    coverflowEffect: {
        rotate: 0,
        stretch: 0,
        depth: 0,
        modifier: 3,
        slideShadows: false
    },
    breakpoints: {
        480: {
            spaceBetween: 0,
            centeredSlides: true
        }
    },
    simulateTouch: true,
    navigation: {
        nextEl: '.news-slider-next',
        prevEl: '.news-slider-prev'
    },
    pagination: {
        el: '.news-slider__pagination',
        clickable: true
    },
    on: {
        init: function () {
            var activeItem = document.querySelector('.swiper-slide-active');

            var sliderItem = activeItem.querySelector('.news__item');

            $('.swiper-slide-active .news__item').addClass('active');

            var x = sliderItem.getBoundingClientRect().left;
            var y = sliderItem.getBoundingClientRect().top;
            var width = sliderItem.getBoundingClientRect().width;
            var height = sliderItem.getBoundingClientRect().height;


            $('.item-bg').addClass('active');

            bg.style.width = width + 'px';
            bg.style.height = height + 'px';
            bg.style.transform = 'translateX(' + x + 'px ) translateY(' + 113 + 'px)';
            //console.log(x + " " + y);
        }
    }
});

swiper.on('touchEnd', function () {
    $('.news__item').removeClass('active');
    $('.swiper-slide-active .news__item').addClass('active');
});

swiper.on('slideChange', function () {
    $('.news__item').removeClass('active');
});

swiper.on('slideChangeTransitionEnd', function () {
    $('.news__item').removeClass('active');
    var activeItem = document.querySelector('.swiper-slide-active');

    var sliderItem = activeItem.querySelector('.news__item');

    $('.swiper-slide-active .news__item').addClass('active');

    var x = sliderItem.getBoundingClientRect().left;
    var y = sliderItem.getBoundingClientRect().top;
    var width = sliderItem.getBoundingClientRect().width;
    var height = sliderItem.getBoundingClientRect().height;


    $('.item-bg').addClass('active');

    bg.style.width = width + 'px';
    bg.style.height = height + 'px';
    bg.style.transform = 'translateX(' + x + 'px ) translateY(' + y + 'px)';
});


var introTL = gsap.timeline();
introTL.to(".intro", .1, {fontFamily: "Anton"})
introTL.to(".intro", .1, {fontFamily: "Jost"})
introTL.to(".intro", .1, {fontFamily: "Alkatra"})
introTL.to(".intro", .1, {fontFamily: "Nova Oval"})
introTL.to(".intro", .1, {fontFamily: "Oswald"})
introTL.to(".intro", .1, {fontFamily: "PT Serif"})
introTL.to(".intro", .1, {fontFamily: "Lexend"})
introTL.to(".intro", .1, {fontFamily: "Poppins"})
introTL.to(".intro", .1, {fontFamily: "Titillium Web"})
introTL.to(".intro", 1, {scaleY: 0, ease: "expo.inOut"})
introTL.to(".intro__red", 1, {scaleY: 2, ease: "expo.inOut"}, "-=1.25")

// TITLE
function separateWordsAndLetters(text) {
    let words = text.split(/\s+|<br>/);
    let result = "";
    for (let i = 0; i < words.length; i++) {
        let word = words[i];
        result += "<div class='flex'>";
        for (let j = 0; j < word.length; j++) {
            result += "<span>" + word[j] + "</span>";
        }
        result += "</div>&nbsp;";
    }
    return result;
}

var clipH1 = document.querySelector(".clip h1")
let separatedClipH1 = separateWordsAndLetters(clipH1.innerHTML);
clipH1.innerHTML = separatedClipH1;
var clipH1Letters = document.querySelectorAll(".clip h1 span")
gsap.set(clipH1Letters, {y: "120%", scale: -.5})
gsap.to(clipH1Letters, {duration: 1.5, y: "10%", scale: 1, ease: "expo.inOut", delay: 0.6, stagger: 0.025})

var clipH1Stroke = document.querySelector(".clip .h1__stroke")
let separatedClipH1Stroke = separateWordsAndLetters(clipH1Stroke.innerHTML);
clipH1Stroke.innerHTML = separatedClipH1Stroke;
var clipH1StrokeLetters = document.querySelectorAll(".clip .h1__stroke span")
gsap.set(clipH1StrokeLetters, {y: "120%", scale: -.5})
gsap.to(clipH1StrokeLetters, {duration: 1.5, y: "10%", scale: 1, ease: "expo.inOut", delay: 0.6, stagger: 0.025})

//CURSOR
var cursor = document.querySelector(".cursor")

document.querySelector(".clip__inner").addEventListener("mousemove", (e) => {
    var x = e.clientX
    var y = e.clientY

    gsap.to(".cursor", .5, {duration: 0, x: x, y: y})
})

document.querySelector(".clip__inner").addEventListener("mouseenter", () => {
    gsap.to(".cursor", .5, {scale: 1, ease: "expo.inOut"})
})

document.querySelector(".clip__inner").addEventListener("mouseleave", () => {
    gsap.to(".cursor", .5, {scale: 0, ease: "expo.inOut"})
})
