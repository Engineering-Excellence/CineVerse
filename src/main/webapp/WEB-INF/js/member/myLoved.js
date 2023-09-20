'use strict'

const apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
let userLoved;

const setLoveBtnHandler = () => {
    $(".heart-div").each((idx, e) => {
        $(e).off("click").on("click", () => {
            if (userLoved.includes($(e).attr("id"))) {
                $.ajax({
                    url: "/movie/loved/" + username + "/" + $(e).attr("id"),
                    type: "delete",
                    async: false,
                    success: (ret) => {
                        if (ret) {
                            $(e).find('.heart').toggleClass('love');
                            $(e).find('.line, .heart').addClass("active").delay(300).queue((next) => {
                                $(e).removeClass("active");
                                next();
                            });
                            userLoved = userLoved.filter(v => v != $(e).attr("id"));
                        }
                    },
                });
            } else {
                $.ajax({
                    url: "/movie/loved/" + username + "/" + $(e).attr("id"),
                    type: "post",
                    async: false,
                    success: (ret) => {
                        if (ret) {
                            $(e).find('.heart').toggleClass('love');
                            $(e).find('.line, .heart').addClass("active").delay(300).queue((next) => {
                                $(e).removeClass("active");
                                next();
                            });
                            userLoved.push($(e).attr("id"));
                        }
                    },
                });
            }
            // 이 영화 id를 ajax를 통해 db에 해당 유저의 찜목록에 추가 시키기
            // 이미 있 는 경우는 다시 삭제
        })
    })
}

if (isLogin) {
    $.ajax({
        url: "/movie/loved/" + username,
        type: "get",
        async: false,
        success: (res) => {
            console.log(res);
            userLoved = res;
        },
    });

    let html = "";
    userLoved.forEach((e) => {
        $.ajax({
            url: `https://api.themoviedb.org/3/movie/${e}?api_key=${apiKey}&language=ko-KR&region=KR&append_to_response=release_dates`,
            type: "get",
            async: false,
            success: (res) => {
                console.log(res);
                let rank = "";
                res["release_dates"]["results"].forEach((d) => {
                    if (d["iso_3166_1"] == "KR") {
                        rank = d["release_dates"][0]["certification"];
                    }
                })

                html += `<div class="movie-list-item">`

                if (isLogin) {
                    html += `<div>`
                    html += `<div class="heart-div" id="${e}">`
                    html += `<div class="love action">`
                    if (userLoved.includes(String(e))) html += `<div class="heart love active"></div>`
                    else html += `<div class="heart"></div>`// 여기서 만약에 이 영화가 해당 유저의 찜목록에 들어있다면 이미 칠해진 하트로 바꾸기 클래스에 love active 2개 추가
                    html += `</div>`
                    html += `</div>`
                    html += `</div>`
                }

                html += `<div class="container-movie">`
                html += `<div class="card">`
                html += `<div class="card__background"><img src="http://image.tmdb.org/t/p/w342${res["poster_path"]}"></div>`
                html += `<div class="card__shadow"></div>`
                html += `<div class="card__info">`
                html += `<div class="card__name">${res["title"]}</div>`
                html += `<div class="card__about">`
                html += `<span class="age">${rank.length == 0 ? "-" : rank}</span>`
                html += `<span class="country">  |  ${res["release_date"]}</span></div>`
                html += `<div class="card__description">${res["overview"].substr(0, 150)}${res["overview"].length >= 150 ? "..." : ""}</div>`
                html += `</div>`
                html += `<div class="card__follow"><a href="/movie/view?id=${res["id"]}"><span>상세보기</span></a></div>`
                html += `</div>`
                html += `</div>`
                html += `</div>`
            },
        });
    })
    $(".movie-list").append(html);
    if (isLogin) setLoveBtnHandler();
}
