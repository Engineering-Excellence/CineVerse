'use strict'

// $(".love").click(function () {
//     console.log("test");
//     $(this).find('.heart').toggleClass('love');
//
//     $(this).find('.line, .heart').addClass("active").delay(300).queue(function (next) {
//         $(this).removeClass("active");
//         next();
//     });
// });


// var animateButton = function(e) {
//
//   e.preventDefault;
//   //reset animation
//   e.target.classList.remove('animate');
//
//   e.target.classList.add('animate');
//   setTimeout(function(){
//     e.target.classList.remove('animate');
//   },700);
// };
//
// var bubblyButtons = document.getElementsByClassName("bubbly-button");
//
// for (var i = 0; i < bubblyButtons.length; i++) {
//   bubblyButtons[i].addEventListener('click', animateButton, false);
// }

var currPage = 0; // 현재 페이지
// (api 호출 페이지는 currPage/2 + 1 해서 20개씩 받고
// 데이터는 20개씩 받은 것을 10개, 10개씩 나눠서 보여줘야 한다
// 첫 호출 20개 중 10개는 바로 보여주고, 다음 10개는 더보기 버튼을 누르면 보여주며
// 이후 다시 더보기 버튼을 누르면 api 호출을 하는 방식
// 0부터 시작하므로 page가 짝수면 새로 호출하여 10개를, 홀수면 이미 있던 데이터에서 나머지를 보여주는 상황
var apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
var currData;
var userLoved;

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
}

const getMovieData = (apiPage) => {
    let listUrl = `https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&region=KR&page=${apiPage}&language=ko-KR`;
    let posterUrl = "http://image.tmdb.org/t/p/w342/";
    $.ajax({
        url: listUrl,
        type: "get",
        success: (data) => {
            currData = data;
            showListwithPage(currData, currPage);
            console.log(data);
        },
    });
}

const showListwithPage = (data, page) => {
    let list = data["results"]
    let html = "";
    let start = page % 2 == 0 ? 0 : 10;
    let end = page % 2 == 0 ? Math.min(9, list.length - 1) : list.length - 1;

    for (let i = start; i <= end; i++) {
        let rank = -1;
        $.ajax({
            url: `https://api.themoviedb.org/3/movie/${list[i]["id"]}?api_key=${apiKey}&language=ko-KR&region=KR&append_to_response=release_dates`,
            type: "get",
            async: false,
            success: (detail) => {
                detail["release_dates"]["results"].forEach((d) => {
                    if (d["iso_3166_1"] == "KR") {
                        rank = d["release_dates"][0]["certification"];
                    }
                })
            },
        });
        html += `<div class="movie-list-item">`

        if (isLogin) {
            html += `<div>`
            html += `<div class="heart-div" id="${list[i]["id"]}">`
            html += `<div class="love action">`
            if (userLoved.includes(String(list[i]["id"]))) html += `<div class="heart love active"></div>`
            else html += `<div class="heart"></div>`// 여기서 만약에 이 영화가 해당 유저의 찜목록에 들어있다면 이미 칠해진 하트로 바꾸기 클래스에 love active 2개 추가
            html += `</div>`
            html += `</div>`
            html += `</div>`
        }

        html += `<div class="container-movie">`
        html += `<div class="card">`
        html += `<div class="card__background"><img src="http://image.tmdb.org/t/p/w342${list[i]["poster_path"]}"></div>`
        html += `<div class="card__shadow"></div>`
        html += `<div class="card__info">`
        html += `<div class="card__name">${list[i]["title"]}</div>`
        html += `<div class="card__about">`
        html += `<span class="age">${rank.length == 0 ? "-" : rank}</span>`
        html += `<span class="country">  |  ${list[i]["release_date"]}</span></div>`
        html += `<div class="card__description">${list[i]["overview"].substr(0, 150)}${list[i]["overview"].length >= 150 ? "..." : ""}</div>`
        html += `</div>`
        html += `<div class="card__follow"><a href="/movie/view?id=${list[i]["id"]}"><span>상세보기</span></a></div>`
        html += `</div>`
        html += `</div>`
        html += `</div>`
    }

    $(".movie-list").append(html);
    if (isLogin) setLoveBtnHandler();

    if ($(".movie-list-item").length == currData["total_results"]) {
        $("#more-btn").text("마지막 페이지 입니다")
        $("#more-btn").off("click").on("click", (e) => {
            $("#more-btn").click((e) => {
                e.target.classList.remove('animate');
                e.target.classList.add('animate');
                setTimeout(function () {
                    e.target.classList.remove('animate');
                }, 700);
            });
        })
    }
}

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
            }
            else {
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

$("#more-btn").on("click", (e) => {
    e.target.classList.remove('animate');
    e.target.classList.add('animate');
    setTimeout(function () {
        e.target.classList.remove('animate');
    }, 700);

    if (++currPage % 2 == 0) getMovieData(currPage / 2 + 1);
    else showListwithPage(currData, currPage);
});

getMovieData(1);
$(".search-btn").click((e) => {
    window.location.href = "/movie/search?query=" + $("#input").val();
});

$("#input").keypress((e) => {
    if (e.keyCode == 13) window.location.href = "/movie/search?query=" + $("#input").val();
})