var boxOfficeApiKey = "65ae15380317da54a96c6c7306a46bf4";
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
        url: `http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=${boxOfficeApiKey}&targetDt=${getDateFormatString(today)}&itemPerPage=5`,
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
        url: `http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=${boxOfficeApiKey}&targetDt=${getDateFormatString(today)}&weekGb=0&itemPerPage=5`,
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
});

const getDateFormatString = (date) => {
    let ret = "";
    ret += date.getFullYear();
    ret += pad(date.getMonth() + 1,2);
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