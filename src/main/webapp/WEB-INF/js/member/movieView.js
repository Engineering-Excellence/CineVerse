'use strict'

var apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
var movieId = new URLSearchParams(window.location.search).get("id");
$.ajax({
    url: `https://api.themoviedb.org/3/movie/${movieId}?api_key=${apiKey}&language=ko-KR&region=KR&append_to_response=release_dates`,
    type: "get",
    async: true,
    success: (detail) => {
        let releaseData = detail["release_dates"]["results"].filter((d) => d["iso_3166_1"] == "KR")
        // detail["release_d1ates"]["results"].forEach((d) => {
        //     if (d["iso_3166_1"] == "KR") {
        //         releaseData = d["release_dates"][0];
        //     }
        // })
        if (releaseData.length == 0) releaseData = detail["release_dates"]["results"][0]["release_dates"][0];
        else releaseData = releaseData[0]["release_dates"][0];
        console.log(releaseData);

        let html = "";
        let genresList = "";
        for (let i = 0; i < detail["genres"].length; i++) {
            genresList += detail["genres"][i]["name"];
            if (i < detail["genres"].length - 1) genresList += ", ";
        }
        let runtime = "";
        runtime += Math.floor(detail["runtime"] / 60) > 0 ? Math.floor(detail["runtime"] / 60) + "시간 " : "";
        runtime += detail["runtime"] % 60 + "분"

        let director;
        let writer;
        let actor;
        let actorListStr = "";
        $.ajax({
            url: `https://api.themoviedb.org/3/movie/${movieId}/credits?api_key=${apiKey}&language=ko-KR`,
            type: "get",
            async: false,
            success: (data) => {
                actor = data["cast"].filter((d) => d["known_for_department"] == "Acting");
                writer = data["crew"].filter((d) => d["known_for_department"] == "Directing" && d["job"] != undefined && d["job"] == "Writer");
                director = data["crew"].filter((d) => d["known_for_department"] == "Directing" && d["job"] != undefined && d["job"] == "Director");
                for (let i = 0; i < Math.min(3, actor.length); i++) {
                    actorListStr += actor[i]["name"];
                    if (i < Math.min(3, actor.length) - 1) actorListStr += ", ";
                }
                if (3 < actor.length) actorListStr += " 외 " + (actor.length - 3) + "명";
            },
        });

        let userLoved;
        if (isLogin) {
            $.ajax({
                url: "/movie/loved/" + username,
                type: "get",
                async: false,
                success: (res) => {
                    userLoved = res;
                },
            });
            html += `<div>`
            html += `<div class="heart-div">`
            if (userLoved.includes(movieId)) html += `<div class="heart love active"></div>`
            else html += `<div class="heart"></div>`
            html += `</div>`
            html += `</div>`
            html += `</div>`
        }

        html += `<div class="movie-poster play-trailer"><img class="poster-img" src="http://image.tmdb.org/t/p/w500/${detail["poster_path"]}"></div>`
        html += `<div id="movie-content">`
        html += `<div class="movie-ratings"><span class="star">★</span><span class="score">${detail["vote_average"]}</span><span class="score-out-of">/ 10 (TMDB)</span></div>`
        html += `<div class="movie-title">${detail["title"]}<span class="movie-year">${releaseData["release_date"].substr(0, 10)}</span></div>`
        html += `<div class="movie-details"><span class="movie-rating">${releaseData["certification"].length == 0 ? "-" : releaseData["certification"]}</span><span class="movie-duration"><img src="/images/clock.png" class="clock">${detail["runtime"] == 0 ? " - " : runtime}</span><span class="movie-genre">${genresList}</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Director</span><span class="name">${director.length == 0 ? "-" : director[0]["name"]}</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Writer</span><span class="name">${writer.length == 0 ? "-" : writer[0]["name"]}</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Cast</span><span class="name">${actorListStr}</span></div>`
        html += `<div class="movie-synopsis">${detail["overview"]}</div>`
        html += `<button class="open-chat" type="button">오픈톡 바로가기</button>`
        html += `</div>`
        $('.moviecard').append(html);
        $(".open-chat").click(() => {
            let name = '_blank';
            let left = (screen.width - 600) / 2;
            let top = (screen.height - 800) / 2;
            let specs = 'menubar=no,status=no,toolbar=no,resizable=no,innerWidth=600,innerHeight=800,chrome=1,centerscreen=1,top=' + top + ',left=' + left;
            let newWindow = window.open(`/member/chat?id=${movieId}`, name, specs);
            newWindow.focus();
        });

        if (isLogin) {
            $(".heart-div").click(() => {
                    if (userLoved.includes(movieId)) {
                        $.ajax({
                            url: "/movie/loved/" + username + "/" + movieId,
                            type: "delete",
                            async: false,
                            success: (ret) => {
                                if (ret) {
                                    $(".heart-div").find('.heart').toggleClass('love');
                                    $(".heart-div").find('.line, .heart').addClass("active").delay(300).queue((next) => {
                                        $(".heart-div").removeClass("active");
                                        next();
                                    });
                                    userLoved = userLoved.filter(v => v != movieId);
                                }
                            },
                        });
                    }
                    else {
                        $.ajax({
                            url: "/movie/loved/" + username + "/" + movieId,
                            type: "post",
                            async: false,
                            success: (ret) => {
                                if (ret) {
                                    $(".heart-div").find('.heart').toggleClass('love');
                                    $(".heart-div").find('.line, .heart').addClass("active").delay(300).queue((next) => {
                                        $(".heart-div").removeClass("active");
                                        next();
                                    });
                                    userLoved.push(movieId);
                                }
                            },
                        });
                    }
            });
        }
    },
});

// 영화 추천 돌려서 가져오기
$.ajax({
    url: `https://api.themoviedb.org/3/movie/${movieId}/recommendations?api_key=${apiKey}&language=ko-KR`,
    type: "get",
    success: (data) => {
        let html = "";
        console.log(data)
        let list = data["results"].filter((d) => d["adult"] == false);
        let posterUrl = "http://image.tmdb.org/t/p/w342";
        for (let i = 0; i < Math.min(4, list.length); i++) {
            html += `<div class="news-card" >`
            html += `<a href="/movie/view?id=${list[i]["id"]}" class="news-card__card-link"></a>`
            html += `<img src="${posterUrl + list[i]["poster_path"]}" alt="" class="news-card__image">`
            html += `<div class="news-card__text-wrapper">`
            html += `<h2 class="news-card__title">${list[i]["title"]}</h2>`
            html += `<div class="news-card__post-date">${list[i]["release_date"]}</div>`
            html += `<div class="news-card__details-wrapper">`
            html += `<p class="news-card__excerpt">${list[i]["overview"].substr(0, 100)}${list[i]["overview"].length >= 100 ? "..." : ""}</p>`
            html += `<a href="/movie/view?id=${list[i]["id"]}" class="news-card__read-more">상세보기</a>`
            html += `</div>`
            html += `</div>`
            html += `</div>`
        }
        $(".content-wrapper").append(html);
    },
});

