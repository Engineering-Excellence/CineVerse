<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
<%--    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js" defer></script>--%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/chat.css">
    <sec:authorize access="!isAuthenticated()">
        <script>
            alert("로그인 후 이용 가능합니다");
            window.close();
        </script>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <script>
            var nickName = '<sec:authentication property="principal.username"/>'
        </script>
    </sec:authorize>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/member/chat.js"></script>
</head>
<body>
<div class="chat-container">
    <div class="chat-header">
        <div class="chat-header-wrapper">
        </div>
        <div class="chat-people-count-wrapper">
            <img src="${pageContext.request.contextPath}/images/person-solid.svg"/>
            <div class="chat-people-count">
            </div>
        </div>
    </div>
    <div class="chat-main">
        <div class="chat-main-wrapper">
<%--            <div class="chat">--%>
<%--                <div class="chat-box-header mine">--%>
<%--                    <div class="chat-nickname">내 닉네임</div>--%>
<%--                </div>--%>
<%--                <div class="chat-box-main mine">--%>
<%--                    <div class="chat-content">내가 보낸 메세지</div>--%>
<%--                    <div class="chat-time-wrapper">--%>
<%--                        <div class="chat-time">00:00</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="chat">--%>
<%--                <div class="chat-box-header opponent">--%>
<%--                    <div class="chat-nickname">상대 닉네임</div>--%>
<%--                </div>--%>
<%--                <div class="chat-box-main opponent">--%>
<%--                    <div class="chat-content">상대가 보낸 메세지</div>--%>
<%--                    <div class="chat-time-wrapper">--%>
<%--                        <div class="chat-time">00:00</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>
    <div class="chat-footer">
        <div class="input-group input-group-sm">
            <textarea class="form-control shadow-none" id="chat-input"></textarea>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="chat-btn">보내기</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>