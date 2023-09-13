'use strict'

$(() => {
    // for문을 사용하여 현재부터 마지막날까지의 시간 값을 구함
    for (let i = 0; i <= $(".movie-date-wrapper").length; i++) {
        // 날짜 버튼 클릭시 해당 일의 날짜와 선택된 영화 값을 선택하여 ajax로 보냄
        $(".movie-date-wrapper").eq(i).on("click", () => {
            // 클릭한 날짜를 yyyy-mm-dd의 형식으로 바꿈, toISOString() 지정시 한국 시간과 9시간이 차이나기 때문에
            // 별도로 시간값을 +9 해주면 현재 시간이 된다.
            let clickDate = new Date(date.getFullYear(), date.getMonth(), $(".movie-day").eq(i).html(), date.getHours() + 9).toISOString().split("T")[0]
// 				alert(clickDate)
// 				alert($(".movie-select > option:selected").val())

            $.ajax({
                type: "get", // AJAX 로 요청 시 HTTP 요청 방식(GET or POST) 지정
                url: "ReserveSelectPro.mv", // 컨트롤러로 보낼 주소를 지정
                data: { // 전송할 데이터(파라미터) 지정(일반 파라미터일 경우 중괄호로 묶음)
                    // 폼 데이터를 가져와서 파라미터로 표현(전송)하는 경우
                    // 파라미터명: 데이터 형식으로 지정
                    movie_title: $("#movie-select").val(), // select option에서 선택한 값
                    reserve_date: clickDate // 선택한 날짜
                },
                dataType: "text", // 응답 데이터에 대한 타입 지정(일반 데이터는 text 이며 HTML 코드도 포함 가능, 자바스크립트 포함되면 html 사용)
                success: response => { // 요청에 대한 처리 성공 시(= 정답 응답) 처리할 함수 정의
                    // 익명 함수 파라미터로 응답 데이터가 전달됨(처리 페이지의 응답 결과)
                    $(".theater-list").html(response)
                },
                error: (xhr, textStatus, errorThrown) => {
                    // 요청에 대한 처리 실패 시(= 에러 발생 시) 실행되는 이벤트
                    $("#resultArea").html("xhr = " + xhr + "<br>textStatus = " + textStatus + "<br>errorThrown = " + errorThrown)
                }
            })
        })
    }

    // 상영 시간 값을 지정할 때 영화 제목, 상영 날짜, 상영시간을 모두 선택하지 않으면 좌석 페이지로 넘어갈 수 없도록 제어
    $('#reserveForm').submit(() => {
// 	        	 alert($("input[name=reserved_date]").val())
        if ($("#movie-select").val() == "" || $("input[name=reserved_date]").val() == null || $(".timeButton.active").val() == null) {
            alert("영화와 상영날짜와 상영시간을 모두 선택하세요!")
            return false
        }
    })

    var url_href = window.location.href
    var url = new URL(url_href) // URL 객체 선언
    const urlParams = new URLSearchParams(location.search) // URL에서 파라미터만 가져와서 객체 생성
// 	          alert(location.search)
    // 파라미터에 영화 제목에 해당되는 값이 있을 경우 selectBox에 해당되는 값을 넣음
    if (urlParams.has('movie_title')) {
        $("#movie-select").val(url.searchParams.get("movie_title"))
    }
})

// 현재 날짜를 불러올 Date 객체 생성
// Mon Dec 26 2022 16:07:25 GMT+0900 (한국 표준시)
const date = new Date()
//  마지막 날짜(현재 날짜로부터 일주일간)를 불러오기 위해 새로운 객체, 년도와 월을 가져옴(getMonth()는 1월이 0으로 처리되기 때문에)
//  필수로 +1를 처리해야한다.
//  Sat Dec 31 2022 00:00:00 GMT+0900 (한국 표준시)
const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0)

// 예매 날짜를 넣을 클래스 요소 선택(위에서 설정한 <div class="reserve-date"></div> 부분에 넣을 값)
const reserveDate = document.querySelector(".reserve-date")

// 요일값을 불러오기 위한 배열 생성과, 연도, 월을 가져올 변수를 지정한다. 월에는 +1 필수
const weekOfDay = ["일", "월", "화", "수", "목", "금", "토"]
const year = date.getFullYear()
const month = date.getMonth() + 1

const dayClickEvent = button => {
    button.addEventListener("click", () => {
        const movieDateWrapperActive = document.querySelectorAll(".movie-date-wrapper-active")
        movieDateWrapperActive.forEach((list) => {
            list.classList.remove("movie-date-wrapper-active")
        })
        button.classList.add("movie-date-wrapper-active")
    })
// 			alert($(button).attr("id"))
    $(".movie-date-wrapper-active").attr("id", "btn_selected")
}

const change_btn = e => {
    var btns = document.querySelectorAll(".timeButton")
    btns.forEach((btn, i) => {
        if (e.currentTarget == btn) {
            btn.classList.add("active")
// 			      alert($(".timeButton").val())
            $("input[name=selected_time]").val($(".timeButton.active").val())
        } else {
            btn.classList.remove("active")
        }
    })
    console.log(e.currentTarget)
}

//  현재 날짜의 일부터 lastDay()까지 +시켜가며 반복
//  그만큼 버튼과 span영역을 만들기
for (var i = date.getDate(); i <= lastDay.getDate(); i++) {

    const button = document.createElement("button")
    const spanWeekOfDay = document.createElement("span")
    const spanDay = document.createElement("span")

    // class넣고 이름 지정하기
    button.classList = "movie-date-wrapper"
    // 해당 일 + 요일값을 출력할 버튼
    spanWeekOfDay.classList = "movie-week-of-day"
    spanDay.classList = "movie-day"

    //weekOfDay
    const dayOfWeek = weekOfDay[new Date(year + "-" + month + "-" + i).getDay()]

    //요일 넣기
    if (dayOfWeek == "토") {
        spanWeekOfDay.classList.add("saturday")
        spanDay.classList.add("saturday")
    } else if (dayOfWeek == "일") {
        spanWeekOfDay.classList.add("sunday")
        spanDay.classList.add("sunday")
    }
    spanWeekOfDay.innerHTML = dayOfWeek
    button.append(spanWeekOfDay)
    //날짜 넣기
    spanDay.innerHTML = i
    button.append(spanDay)
    //button.append(i)
    reserveDate.append(button)

    dayClickEvent(button)
}