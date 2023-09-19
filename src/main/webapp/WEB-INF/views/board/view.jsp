<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css"> --%>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/board/list.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/board/view.css">

<sec:authorize access="isAuthenticated()">
    <sec:authentication var="user" property="principal"/>
</sec:authorize>
<section>
    <container>
        <forum id="mg-4">
            <inner>
                <topic-title>
                    <a class="title">${data.boardTitle}</a>
                    <sub>${data.boardDate}</sub>
                    <sub>${data.username }</sub>
                </topic-title> <!-- <descr></descr> -->
                ${data.boardContent}
            </inner>
        </forum>
    </container>

    <%-- <div class="board-div">
    <div class="board-title">
        <table>
            <tr>
                <th colspan="2">${data.boardTitle}</th>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${data.username }</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${data.boardDate}</td>
            </tr>
        </table>
    </div>
    <div class="board-content">
        ${data.boardContent}
    </div>
</div> --%>
    <div class="btn-list">
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:if test="${user.username eq data.username}">
                    <div class="write-btn">
                        <button class="learn-more"
                                onClick="location.href='/board/update/${data.boardNo }'">
                            <span class="circle" aria-hidden="true"> <span
                                    class="icon arrow"></span>
                            </span> <span class="button-text">수정</span>
                        </button>
                    </div>
                </c:if>

                <form action="/board/delete/${data.boardNo }" method="post">
                    <div class="write-btn">
                        <button class="learn-more" type="submit">
					<span class="circle" aria-hidden="true"> <span
                            class="icon arrow"></span>
					</span> <span class="button-text">삭제</span>
                        </button>
                    </div>
                </form>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <c:if test="${user.username eq data.username}">
                    <div class="write-btn">
                        <button class="learn-more"
                                onClick="location.href='/board/update/${data.boardNo }'">
                        <span class="circle" aria-hidden="true"> <span
                                class="icon arrow"></span>
                        </span> <span class="button-text">수정</span>
                        </button>
                    </div>

                    <form action="/board/delete/${data.boardNo }" method="post">
                        <div class="write-btn">
                            <button class="learn-more" type="submit">
					<span class="circle" aria-hidden="true"> <span
                            class="icon arrow"></span>
					</span> <span class="button-text">삭제</span>
                            </button>
                        </div>
                    </form>
                </c:if>
            </sec:authorize>
        </sec:authorize>
        <div class="write-btn">
            <button class="learn-more" onClick="location.href='/board/list'">
				<span class="circle" aria-hidden="true"> <span
                        class="icon arrow"></span>
				</span> <span class="button-text">목록</span>
            </button>
        </div>
    </div>
    <sec:authorize access="isAuthenticated()">
        <p class="reply">댓글</p>
        <div class="reply-div">

            <form action="/reply/insert" method="post" class="reply-form">
                <div>

                    <input type="text" value="${data.boardNo }" name="boardNo" hidden>
                    <textarea name="replyContent" class="reply-content-input" placeholder="댓글을 작성해주세요."></textarea>

                </div>
                <div class="reply-submit-btn">
                    <input type="submit" class="board-view-submit" value="등록">
                </div>
            </form>
        </div>
    </sec:authorize>

    <c:forEach var="r" items="${reply}">
        <div class="line">
            <div class="reply-title">
                <div class="reply-name">
                    <p>${ r.username }&nbsp;</p>
                    <p class="reply-date">
                        <fmt:formatDate value="${ r.replyDate }" pattern="MM/dd hh:mm" type="date"/>
                    </p>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <c:if test="${user.username eq r.username}">
                        <form action="/reply/delete/${r.replyNo}" method="post">
                            <div class="reply-delete">
                                <input type="text" name="boardNo" hidden="hidden" value="${data.boardNo}">
                                <input type="submit" id="deleteBtn" value="삭제">
                            </div>
                        </form>
                    </c:if>
                </sec:authorize>

            </div>
            <p>${ r.replyContent }</p>


        </div>
    </c:forEach>
</section>
