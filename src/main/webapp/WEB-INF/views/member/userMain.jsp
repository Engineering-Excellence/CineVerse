<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/userMain.css">

<body class="user-main-body">
<div class="movie-main-top">
    <p class="movie-list">
        영화목록 <a href="#">더많은영화보기+</a>
    </p>
</div>
<div class="user-main">
    <div class="movie-one poster">

        <img src="${pageContext.request.contextPath}/images/비공식작전.jpg">
        <div class="movie-link">
            <button onclick="location.href='#'">상세정보</button>
            <button onclick="location.href='#'">예매하기</button>
        </div>
    </div>
    <div class="movie-two poster">

        <img src="${pageContext.request.contextPath}/images/밀수.jpg">
        <div class="movie-link">
            <button onclick="location.href='#'">상세정보</button>
            <button onclick="location.href='#'">예매하기</button>
        </div>

    </div>
    <div class="movie-three poster">
        <img src="${pageContext.request.contextPath}/images/엘리멘탈.jpg">
        <div class="movie-link">
            <button onclick="location.href='#'">상세정보</button>
            <button onclick="location.href='#'">예매하기</button>
        </div>
    </div>
    <div class="movie-four poster">
        <img src="${pageContext.request.contextPath}/images/콘트리트유토피아.jpg">
        <div class="movie-link">
            <button onclick="location.href='#'">상세정보</button>
            <button onclick="location.href='#'">예매하기</button>
        </div>
    </div>
</div>
</body>
