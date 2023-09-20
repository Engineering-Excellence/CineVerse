<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/board/list.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/movieList.css">
<script src="https://kit.fontawesome.com/9e5ba2e3f5.js"
        crossorigin="anonymous"></script>
<section>
    <h1 class="board-title">전체 게시판</h1>
    <div class="board-search search-div">
        <div id="search" style="display:flex;">
            <form action="/board/search" method="get">
                <select class="search-select" id="searchType" name="searchType">
                    <option value="1">제목</option>
                    <option value="2">작성자</option>
                </select>
                <input id="searchInput" placeholder="검색어를 입력하세요" name="keyword"/>
                <button class="search-btn" id="searchBtn">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
        	<th></th>
            <th scope="col" class="center content-no">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col" class="center">작성일</th>
            <th scope="col" class="center">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="notice" items="${notice}">
            <tr onClick="location.href='/board/view/${notice.boardNo}'" class="notice-table">
            	<td class="notice-icon"><img src="${pageContext.request.contextPath}/images/megaphone.png" class="megaphone"></td>
                <td scope="row" class="center content-no">${notice.boardNo}</td>
                <th class="notice-title">${notice.boardTitle}</th>
                <td>${notice.username}</td>
                <td class="center">${notice.boardDate}</td>
                <td class="center">${notice.boardView}</td>
            </tr>
        </c:forEach>
        <c:forEach var="boardList" items="${boardList}">
            <tr onClick="location.href='/board/view/${boardList.boardNo}'">
            	<td></td>
                <td scope="row" class="center content-no">${boardList.boardNo}</td>
                <td class="content-title">${boardList.boardTitle}</td>
                <td>${boardList.username}</td>
                <td class="center">${boardList.boardDate}</td>
                <td class="center">${boardList.boardView}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table class="paging-table">
        <tr>
            <td>
                <!-- <!— 처음 이전 링크 —> -->
                <c:if test="${page > 1}">
                    <a href="list?page=1"><i class="fa-solid fa-angles-left"></i></a>
                    <a href="list?page=${page - 1}"><i class="fa-solid fa-angle-left"></i></a>
                </c:if>
                <c:if test="${page <= 1}">
                    <span style="color: gray"><i class="fa-solid fa-angles-left"></i></span>
                    <span style="color: gray"><i class="fa-solid fa-angle-left"></i></span>
                </c:if>

                <!-- <!— 블록 범위 출력 —> -->
                <c:forEach begin="${fromPage}" end="${toPage}" var="i">
                    <c:if test="${i == page}"><a>${i}</a></c:if>
                    <c:if test="${i != page}">
                        <a href="list?page=${i}">${i}</a>
                    </c:if>
                </c:forEach>

                <!-- <!— 다음, 이후 링크 —> -->
                <c:if test="${page < allPage}">
                    <a href="list?page=${page + 1}"><i class="fa-solid fa-angle-right"></i></a>
                    <a href="list?page=${allPage}"><i class="fa-solid fa-angles-right"></i></a>
                </c:if>
                <c:if test="${page >= allPage}">
                    <span style="color: gray"><i class="fa-solid fa-angle-right"></i></span>
                    <span style="color: gray"><i class="fa-solid fa-angles-right"></i></span>
                </c:if>
            </td>
        </tr>
    </table>
    <div class="write-btn">
        <button class="learn-more" onClick="location.href='/board/write'">
            <!-- 로그인한 사용자에게만 글 작성 버튼 노출 -->
            <sec:authorize access="isAuthenticated()">
			<span class="circle" aria-hidden="true">
                <span class="icon arrow"></span>
			</span>
                <span class="button-text">글 작성</span>
            </sec:authorize>
        </button>
    </div>
</section> 
