<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/ticket.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/ticket.js" defer></script>

<section>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <h2>좌석정보</h2>
        </div>
    </div>

    <div class="reserve-container">
        <div class="movie-part">
            <div class="reserve-title">영화</div>
            <div class="movie-list">
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
            </div>
        </div>
        <div class="day-part">
            <div class="reserve-title">날짜</div>
            <div class="reserve-date"></div>
        </div>
        <div class="movie-part">
            <div class="reserve-title">극장</div>
            <div class="movie-list">
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
                <div class="movie-item">
                    <div class="right top">영화 이름</div>
                </div>
            </div>
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
