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
            <div class="reserve-title">극장</div>
            <div class="theater-list" id="theater-list">
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
<%--                <div class="item">--%>
<%--                    <div class="right top">영화 이름</div>--%>
<%--                </div>--%>
            </div>
        </div>

        <div class="day-part">
            <div class="reserve-title">날짜</div>
            <div class="reserve-date"></div>
        </div>

        <div class="movie-part">
            <div class="reserve-title">영화</div>
            <div class="movie-list" id="movie-title-list">
                <%--                <div class="item">--%>
                <%--                    <div class="right top">영화 이름</div>--%>
                <%--                </div>--%>
                <%--                <div class="item">--%>
                <%--                    <div class="right top">영화 이름</div>--%>
                <%--                </div>--%>
                <%--                <div class="item">--%>
                <%--                    <div class="right top">영화 이름</div>--%>
                <%--                </div>--%>
                <%--                <div class="item">--%>
                <%--                    <div class="right top">영화 이름</div>--%>
                <%--                </div>--%>
                <%--                <div class="item">--%>
                <%--                    <div class="right top">영화 이름</div>--%>
                <%--                </div>--%>
            </div>
        </div>

        <div class="time-part">
            <form action="ReserveSeat.mv" method="post" id="reserveForm">
                <div class="reserve-title">정보</div>
                <div class="ticket-list">
<%--                    <div class="ticket-item">--%>
<%--                        <div class="ticket-item-info">1관</div>--%>
<%--                        <div class="ticket-item-info">17:10</div>--%>
<%--                        <div class="ticket-item-info">13/130</div>--%>
<%--                    </div>--%>
                </div>
            </form>
        </div>
    </div>
</section>
