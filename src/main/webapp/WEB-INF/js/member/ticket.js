'use strict'

const theater = [{
    "name": "강동",
    "code": "1341"
},
    {
        "name": "홍대",
        "code": "1212"
    },
    {
        "name": "상암",
        "code": "1211"
    },
    {
        "name": "송파",
        "code": "1381"
    },
    {
        "name": "강남",
        "code": "1372"
    },
    {
        "name": "동대문",
        "code": "1003"
    },
    {
        "name": "강남대로",
        "code": "0023"
    },
    {
        "name": "목동현대백화점",
        "code": "0041"
    },
    {
        "name": "목동",
        "code": "1581"
    },
    {
        "name": "마곡",
        "code": "1572"
    },
    {
        "name": "성수",
        "code": "1331"
    },
    {
        "name": "센트럴",
        "code": "1371"
    },
    {
        "name": "상봉",
        "code": "1311"
    }]; // 일단은 테스트용 데이터, 나중에 db에서 가져오던 말던

var ticketingData;
var megaBoxList;
var titleSet = new Set();
var isTheaterSelected = false;
var isDateSelected = false;

$(() => {
    theater.forEach((i) => {
        let html = "";
        html += `<div class="theater-item item" id="${i["code"]}">`
        html += `<div class="right top">${i["name"]}</div>`
        html += `</div>`
        $(".theater-list").append(html);
    })
    addTheaterHandler()
});
const addMovieHandler = () => {
    $(".movie-item").each((i, e) => {
        $(e).click(() => {
            if ($(e).hasClass("selected")) {
                $(e).removeClass("selected");
                $(".ticket-list").html("");
            } else {
                $(".movie-item").each((i, e_) => {
                    $(e_).removeClass("selected");
                })
                $(e).addClass("selected");
                let title = $(e).find(".right").text();
                let filteredList = ticketingData.filter(data => data["title"] == title);
                console.log(filteredList);
                let html = "";

                if (filteredList.length == 0) {
                    html += `<div class="item">`
                    html += `<div class="ticket-item-info">상영 정보가 없습니다.</div>`
                    html += `</div>`
                } else {
                    filteredList.forEach((item) => {
                        html += `<div class="ticket-item">`
                        html += `<div class="ticket-item-info">${item["company"]}</div>`
                        html += `<div class="ticket-item-info">${item["theater"]}</div>`
                        html += `<div class="ticket-item-info">${item["startTime"]}</div>`
                        html += `<div class="ticket-item-info">${item["restSeat"]}/${item["totSeat"]}</div>`
                        html += `</div>`
                    });
                }

                $(".ticket-list").html("");
                $(".ticket-list").append(html);
            }
        });
    })
}

const addDateHandler = () => {
    $(".date-item").each((i, e) => {
        $(e).click(() => {
            if ($(e).hasClass("selected")) {
                $(e).removeClass("selected");
                isDateSelected = false;
            } else {
                $(".date-item").each((i, e_) => {
                    $(e_).removeClass("selected");
                })
                $(e).addClass("selected");
                isDateSelected = true;
            }
            getMovieData();
        });
    })
}
const addTheaterHandler = () => {
    $(".theater-item").each((i, e) => {
        $(e).click(() => {
            if ($(e).hasClass("selected")) {
                $(e).removeClass("selected");
                isTheaterSelected = false;
            } else {
                $(".theater-item").each((i, e_) => {
                    $(e_).removeClass("selected");
                })
                $(e).addClass("selected");
                isTheaterSelected = true;
            }
            getMovieData();
        });
    })
}

const getMovieData = () => {
    if (isTheaterSelected && isDateSelected) {
        $("#movie-title-list").html("");
        $(".ticket-list").html("");

        $.ajax({
            type: "post",
            url: "/movie/crawl",
            contentType: "application/json",
            data: JSON.stringify({
                theaterNo: $(".theater-list").find(".selected").attr("id"),
                date: $(".reserve-date").find(".selected").find("input").val()
            }),
            success: (res) => {
                ticketingData = res;
                res.forEach((r) => {
                    titleSet.add(r["title"]);
                })

                let html = "";
                titleSet.forEach((i) => {
                    html += `<div class="movie-item item">`
                    html += `<div class="right top">${i}</div>`
                    html += `</div>`
                });
                $("#movie-title-list").append(html);
                addMovieHandler();
            },
        })
    } else {
        $("#movie-title-list").html("");
        $(".ticket-list").html("");
    }
}

// 현재 날짜를 불러올 Date 객체 생성
// Mon Dec 26 2022 16:07:25 GMT+0900 (한국 표준시)
const date = new Date();
//  마지막 날짜(현재 날짜로부터 일주일간)를 불러오기 위해 새로운 객체, 년도와 월을 가져옴(getMonth()는 1월이 0으로 처리되기 때문에)
//  필수로 +1를 처리해야한다.
//  Sat Dec 31 2022 00:00:00 GMT+0900 (한국 표준시)
const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0)

// 요일값을 불러오기 위한 배열 생성과, 연도, 월을 가져올 변수를 지정한다. 월에는 +1 필수
const weekOfDay = ["일", "월", "화", "수", "목", "금", "토"]
const year = date.getFullYear()
const month = date.getMonth() + 1

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

const dayClickEvent = button => {
    button.addEventListener("click", () => {
        const movieDateWrapperActive = document.querySelectorAll(".movie-date-wrapper-active")
        movieDateWrapperActive.forEach((list) => {
            list.classList.remove("movie-date-wrapper-active")
        })
        button.classList.add("active")
    })
}

//  현재 날짜의 일부터 lastDay()까지 +시켜가며 반복
//  그만큼 버튼과 span영역을 만들기
for (let i = date.getDate(); i < date.getDate() + 7; i++) {
    let html = "";
    const dayOfWeek = weekOfDay[new Date(year + "-" + month + "-" + i).getDay()];

    if (dayOfWeek == "토") html += `<div class="date-item item saturday">`;
    else if (dayOfWeek == "일") html += `<div class="date-item item sunday">`;
    else html += `<div class="date-item item">`;
    let date = new Date(year + "-" + month + "-" + i);
    console.log(date)
    html += `<div class="right top date-text">${dayOfWeek} ${date.getMonth() + 1}/${date.getDate()}</div>`
    html += `<input hidden value="${getDateFormatString(date)}">`
    html += `</div>`
    $(".reserve-date").append(html);
}
addDateHandler();