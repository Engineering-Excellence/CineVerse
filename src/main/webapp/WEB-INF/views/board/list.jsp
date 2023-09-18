<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

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
		<c:forEach var="data" items="${data}">
			<tbody>

				<tr onClick="location.href='/board/view/${data.boardNo}'">
					<th scope="row" class="center content-no">${data.boardNo }</th>
					<td class="content-title">${data.boardTitle}</td>
					<td>${data.username }</td>
					<td class="center">${data.boardDate }</td>
					<td class="center">${data.boardView }</td>
				</tr>

			</tbody>
		</c:forEach>
	</table>
	<div class="write-btn">
		<button class="learn-more" onClick="location.href='/board/write'">
			<span class="circle" aria-hidden="true"> <span
				class="icon arrow"></span>
			</span> <span class="button-text">글 작성</span>
		</button>
	</div>
</section> 


