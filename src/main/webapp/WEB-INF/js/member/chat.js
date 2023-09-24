'use strict'

const apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
let movieId = new URLSearchParams(window.location.search).get("id");

$(function () {
    $.ajax({
        url: `https://api.themoviedb.org/3/movie/${movieId}?api_key=${apiKey}&language=ko-KR&region=KR&append_to_response=release_dates`,
        type: "get",
        async: true,
        success: (detail) => {
            $(".chat-header-wrapper").text(detail["title"]);
        },
    });

    // var ws = new WebSocket("ws://localhost:8080/chat/" + movieId + "/" + nickName);
    let ws = new WebSocket("wss://13.125.65.147:8443/chat/" + movieId + "/" + nickName);
    ws.onopen = function (e) { // 연결 시 실행
        console.log("info : connection opened.");
        // 대충 채팅방에 입장하셨습니다 메세지 띄우기 및 다른 사용자들에게 입장했음을 알리는 메세지 보내도록 하기
    }

    ws.onmessage = function (e) { // 서버로부터 메세지를 받았을 때 실행
        console.log(e.data); //전달 받은 메세지 띄우기
        let data = JSON.parse(e.data);
        console.log(data);
        let html = "";

        if (data["type"] == 0) {
            html += `<div class="chat">`
            html += `<div class="chat-box-main notice">`
            html += `<div class="chat-content">${data.nickname} 님이 입장하셨습니다.</div>`
            html += `</div>`
            html += `</div>`
            $(".chat-people-count").text(data["people"]);
        } else if (data["type"] == 2) {
            html += `<div class="chat">`
            html += `<div class="chat-box-main notice">`
            html += `<div class="chat-content">${data.nickname} 님이 퇴장하셨습니다.</div>`
            html += `</div>`
            html += `</div>`
            $(".chat-people-count").text(data["people"]);
        } else {
            html += '<div class="chat">';
            html += '<div class="chat-box-header ';
            if (nickName == data["nickname"]) html += 'mine">';
            else html += 'opponent">';
            html += `<div class="chat-nickname">${data["nickname"]}</div>`;
            html += '</div>';
            html += '<div class="chat-box-main ';
            if (nickName == data["nickname"]) html += 'mine">';
            else html += 'opponent">';
            html += `<div class="chat-content">${data["message"]}</div>`;
            html += '<div class="chat-time-wrapper">';
            html += '<div class="chat-time">' + new Date().toTimeString().split(' ')[0] + '</div>';
            html += '</div>';
            html += '</div>';
            html += '</div>';
        }
        $(".chat-main-wrapper").append(html);
        $(".chat-main").animate({
            scrollTop: $(".chat-main-wrapper").height()
        }, 350);
    }

    ws.onclose = function (e) { // 연결 종료 시 실행
        console.log("info : connection closed");
    };

    ws.onerror = function (e) {
        console.log("error")
    };

    $("#chat-input").keypress((e) => {
        if (e.keyCode != 13) {
            return;
        }
        e.preventDefault();
        sendMessage(ws);
    })
    $("#chat-btn").click((e) => {
        e.preventDefault();
        sendMessage(ws);
    });
});

const sendMessage = (ws) => {
    if ($("#chat-input").val().length == 0) return;
    ws.send(JSON.stringify({
        type: "1",
        nickname: nickName,
        message: $("#chat-input").val()
    }));
    $("#chat-input").val("");
}