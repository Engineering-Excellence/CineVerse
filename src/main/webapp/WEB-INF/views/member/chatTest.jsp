<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        .container{
            width: 500px;
            margin: 0 auto;
            padding: 25px
        }
        .container h1{
            text-align: left;
            padding: 5px 5px 5px 5px;
            color: black;
            margin-bottom: 20px;
        }

        .chatting{
            border : 1px solid gray;
            width: 700px;
            height: 700px;
            overflow: auto;
        }
        .chatting p{
            text-align: left;
        }
        input{
            width: 450px;
            height: 50px;
        }

        th{
            width : 100px;
        }
        #yourMsg{
            width: 700px;
        }
        .me{
            color: blue;
        }

        .other{
            color: red;
        }
    </style>
</head>
<body>
<div class="container" id="container">
    <h1 id="title_room">채팅방제목</h1>
    <div id="chatting" class="chatting">
    </div>
    <div id="yourMsg">
        <table class="inputTable">
            <tr>
                <th>메시지</th>
                <th><input id="msg" placeholder="보내실 메시지를 입력하세요."></th>
                <th><button onclick="send()" id="sendBtn">보내기</button></th>
            </tr>
        </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script>

    var userId = '${name}';
    var socket;
    var stompClient;

    function connect() {
        //StompConfig.java에 설정된 endpoint로 SockJS 객체, StompClient 객체 생성
        socket = new SockJS("/stomp/connection");
        console.log(socket);
        //do Handshake
        stompClient = Stomp.over(socket);

        // connect(header,연결 성공시 콜백,에러발생시 콜백)
        stompClient.connect({}, function () {
                //subscribe(subscribe url,해당 url로 메시지를 받을때마다 실행할 함수)
                sub = stompClient.subscribe('/sub/chat/room/1', function (e) {
                    //e.body에 전송된 data가 들어있다
                    showMessage(JSON.parse(e .body));
                });
                console.log("연결 완료")
            },
            function(e){
                //에러 콜백
                alert('에러발생!!!!!!');
            }
        );
    }

    connect();

    //엔터 눌렀을때 전송
    $('#msg').keypress(function(e){
        if(e.keyCode===13) send();
    });

    //화면에 메시지를 표시하는 함수
    function showMessage(data){
        if(data.sender===userId){
            $('#chatting').append("<p class='me'>"+data.sender+" : "+data.contents+"</p>");
        } else {
            $('#chatting').append("<p class='other'>"+data.sender+" : "+data.contents+"</p>");
        }
    }

    //메시지 브로커로 메시지 전송
    function send(){
        data = {
            'sender' :userId,
            'contents': $("#msg").val()
        };
        // send(destination,헤더,페이로드)
        stompClient.send("/pub/chat/message", {}, JSON.stringify(data));
        $("#msg").val('');
    }

</script>
</body>
</html>