<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/replyList.css">

<section>
    <div class="reply-list-wrapper">
        <div class="reply-list">
            <c:forEach var="r" items="${reply}">
                <div class="reply-card">
                    <div class="reply-img">
                        <img src="${pageContext.request.contextPath}/images/replyuser.png">
                    </div>
                    <div class="reply-comment">
                        <div class="board-title">
                            <a href="/board/view/${r.boardNo}">${r.boardNo} | ${r.boardTitle}</a>
                        </div>
                        <div class="comment">${r.replyContent}</div>
                        <div class="date">
                            <fmt:formatDate value="${r.replyDate}"
                                            pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>