<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/board/list.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/movieList.css">

<section>
    <h1 class="board-title">전체 게시판</h1>
    <div class="board-search search-div">
        <div id="search">
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
            <th scope="col" class="center content-no">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col" class="center">작성일</th>
            <th scope="col" class="center">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="notice" items="${notice}">
            <tr onClick="location.href='/board/view/${notice.boardNo}'">
                <th scope="row" class="center content-no">${notice.boardNo}</th>
                <td class="content-title">${notice.boardTitle}</td>
                <td>${notice.username}</td>
                <td class="center">${notice.boardDate}</td>
                <td class="center">${notice.boardView}</td>
            </tr>
        </c:forEach>
        <c:forEach var="boardList" items="${boardList}">
            <tr onClick="location.href='/board/view/${boardList.boardNo}'">
                <th scope="row" class="center content-no">${boardList.boardNo}</th>
                <td class="content-title">${boardList.boardTitle}</td>
                <td>${boardList.username}</td>
                <td class="center">${boardList.boardDate}</td>
                <td class="center">${boardList.boardView}</td>
            </tr>
        </c:forEach>
        </tbody>
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
