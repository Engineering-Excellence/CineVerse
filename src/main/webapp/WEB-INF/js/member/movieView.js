var apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
$.ajax({
    url: `https://api.themoviedb.org/3/movie/${new URLSearchParams(window.location.search).get("id")}?api_key=${apiKey}&language=ko-KR&region=KR&append_to_response=release_dates`,
    type: "get",
    async: false,
    success: (detail) => {
        let releaseData = "";
        detail["release_dates"]["results"].forEach((d) => {
            if (d["iso_3166_1"] == "KR") {
                releaseData = d["release_dates"][0];
            }
        })
        let html = "";
        let genresList = "";
        for (let i = 0; i < detail["genres"].length; i++) {
            genresList += detail["genres"][i]["name"];
            if (i < detail["genres"].length - 1) genresList += ", ";
        }
        let runtime = "";
        runtime += Math.floor(detail["runtime"] / 60) > 0 ? Math.floor(detail["runtime"] / 60) + "시간 " : "";
        runtime += detail["runtime"] % 60 + "분"

        html += `<div class="movie-poster play-trailer"><img class="poster-img" src="http://image.tmdb.org/t/p/w500/${detail["poster_path"]}"></div>`
        html += `<div id="movie-content">`
        html += `<div class="movie-ratings"><span class="star">★</span><span class="score">${detail["vote_average"]}</span><span class="score-out-of">/ 10 (TMDB)</span></div>`
        html += `<div class="movie-title"><a href="http://www.imdb.com/title/tt3397884" target="_blank">${detail["title"]}</a><span class="movie-year">${releaseData["release_date"].substr(0, 10)}</span></div>`
        html += `<div class="movie-details"><span class="movie-rating">${releaseData["certification"].length == 0 ? "-" : releaseData["certification"]}</span><span class="movie-duration"><img src="/images/clock.png" class="clock">${detail["runtime"] == 0 ? " - " : runtime}</span><span class="movie-genre">${genresList}</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Director</span><span class="name">Denis Villeneuve</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Writer</span><span class="name">Taylor Sheridan</span></div>`
        html += `<div class="movie-castcrew"><span class="title">Cast</span><span class="name">Emily Blunt, Josh Brolin, Benicio Del Toro</span></div>`
        html += `<div class="movie-synopsis">${detail["overview"]}</div>`
        html += `<button class="open-chat" type="button">오픈톡 바로가기</button>`
        html += `</div>`
        $('.moviecard').append(html);
    },
});

// 감독, 등장인물 api로 돌려서 가져오기
// 영화 추천 돌려서 가져오기