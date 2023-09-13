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
            <div class="sort-wrapper">
                <div class="sort-rate sort-selected">예매율순</div>
            </div>
            <div class="movie-list">
                <select id="movie-select" name="selectName">
                    <option value="">영화를 선택하세요.</option>
                    <!-- 			이 밑으로는 디비에서 불러온 값을 넣습니다. 반복문을 사용하여 작업해주시면 됩니다. -->
                    <c:forEach var="movie" items="${movieList }">
                        <option value="${movie.movie_title }">    ${movie.movie_grade }
                            <a href="MovieDetail.mv?movie_idx=${movie.movie_idx }">${movie.movie_title }</a>
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
                <input type="hidden" name="session_id" value="${sessionScope.sId }">
                <input type="submit" value="좌석 선택하기" class="submit_btn">
            </form>
        </div>
    </div>
</section>
