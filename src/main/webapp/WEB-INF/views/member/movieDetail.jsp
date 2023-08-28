<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieDetail.css">

<body class="movie-detail">
<div class="movie-detail-main">
    <div class="movie-img">
        <img src="${pageContext.request.contextPath}/images/비공식작전.jpg">
    </div>
    <div class="movie-content">
        <h2>
            <strong>비공식 작전</strong>
        </h2>
        <div class="movie-content-ul">
            <ul>
                <li>
                    <p>2023.00.00 개봉</p>
                </li>
                <li class="time"><img src="${pageContext.request.contextPath}/images/clock.png">
                    <p>180분</p></li>
                <li>
                    <p class="rating">15세이상 관람가</p>
                </li>
            </ul>
        </div>
        <table>
            <tr>
                <th>장르</th>
                <td>드라마 / 한국</td>
            </tr>
            <tr>
                <th>감독</th>
                <td>김성훈</td>
            </tr>
            <tr>
                <th>배우</th>
                <td>하정우, 주지훈, 임형국, 김종수, 박혁권, 유승목, 김응수</td>
            </tr>
        </table>
        <button onclick="location.href='#'">예매하기</button>
    </div>
</div>
</body>
