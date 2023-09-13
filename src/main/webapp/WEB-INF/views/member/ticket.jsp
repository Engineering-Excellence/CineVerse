<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/ticket.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/ticket.js" defer></script>

<section class="breadcrumbs">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <h2>좌석정보</h2>
        </div>
    </div>

    <div class="reserve-container">
        <div class="movie-part">
            <div class="reserve-title">영화</div>
            <div class="movie-list">
                <select id="movie-select" name="selectMovie">
                    <option value="">영화를 선택하세요.</option>
                    <c:forEach var="movie" items="${movieList}">
                        <option value="${movie.title}">    <%--${movie.movie_grade}--%>
                            <%--<a href="MovieDetail.mv?movie_idx=${movie.movie_idx}">--%>${movie.title}</a>
                        </option>
                    </c:forEach>
                </select>
                <div class="movie_picture">
                </div>
            </div>
        </div>
        <div class="movie-part">
            <div class="reserve-title">극장</div>
            <div class="movie-list">
                <select id="theater-select" name="selectTheater">
                    <option value="">극장을 선택하세요.</option>
                    <c:forEach var="movie" items="${movieList}">
                        <option value="${movie.branch}">    <%--${movie.movie_grade}--%>
                            <%--<a href="MovieDetail.mv?movie_idx=${movie.movie_idx}">--%>${movie.branch}</a>
                        </option>
                    </c:forEach>
                </select>
                <div class="movie_picture">
                </div>
            </div>
        </div>
        <div class="day-part">
            <div class="reserve-title">날짜</div>
            <div class="reserve-date"></div>
        </div>
        <div class="time-part">
            <form action="ReserveSeat.mv" method="post" id="reserveForm">
                <div class="reserve-title">시간</div>
                <div class="theater-list">
                </div>
                <%--<input type="hidden" name="session_id" value="${sessionScope.sId}">--%>
                <input type="submit" value="상영시간" class="submit_btn">
            </form>
        </div>
    </div>
</section>
