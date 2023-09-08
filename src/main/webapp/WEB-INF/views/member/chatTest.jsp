<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            var roomId = 1;
            var ws = new WebSocket("ws://localhost:8080/socket/" + roomId);
            // var ws = new WebSocket("ws://15.165.146.31:8090//socket/" + roomId);
            ws.onopen = function(e){ // 연결 시 실행
                console.log("info : connection opened.");
                // 대충 채팅방에 입장하셨습니다 메세지 띄우기 및 다른 사용자들에게 입장했음을 알리는 메세지 보내도록 하기
            }

            ws.onmessage = function(e){ // 서버로부터 메세지를 받았을 때 실행
                console.log(e.data); //전달 받은 메세지 띄우기
            }

            ws.onclose = function(e){ // 연결 종료 시 실행
                console.log("info : connection closed");
                // 다른 사람들에게 이 사람이 퇴장했음을 알리는 메세지 보내도록 하기
            };

            ws.onerror = function(e){
                console.log("error")
            };


            $("#btn").on("click",function(e){
                e.preventDefault();
                // ws.send($("#testInput").val());

                ws.send(JSON.stringify({
                    nickname: $("#nickname").val(),
                    message: $("#testInput").val()
                }))
                // 보낼떄 JSON.stringfy 써서 오브젝트로 보내보기
            });
        });
    </script>
</head>
<body>
<h1>Socket Test Page</h1>
<input type="text" id="testInput">
<input type="text" name="" id="nickname">
<button type="button" id="btn">전송</button>

</body>
</html>