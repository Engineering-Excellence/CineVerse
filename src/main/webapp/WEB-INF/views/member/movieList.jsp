<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css">

<body class="movie-list">
<div class="movie-main-top">
    <p class="movie-list">MOVIE LIST</p>
</div>
<div class="movie-list-main">
    <div class="search">
        <select class="search-select">
            <option>제목</option>
        </select> <input class="search-text" type="text"> <input
            class="search-btn" type="submit" value="검색"/>
    </div>
    <div class="movie">
        <div class="movie-poster">
            <img class="poster-img" src="${pageContext.request.contextPath}/images/비공식작전.jpg">
            <p class="movie-name">비공식작전</p>
            <p class="release-date">개봉일 2023.00.00</p>
            <!-- <div class="time"> -->
            <label> <img class="time-img" src="${pageContext.request.contextPath}/images/clock.png">
                <p>180분</p>
            </label>
            <button class="detail movie-btn" onclick="location.href='#'">상세정보</button>
            <button class="ticketing movie-btn" onclick="location.href='#'">예매하기</button>
        </div>
    </div>
</div>
</body>
