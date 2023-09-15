<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/list.css"> 
<section>                             
			<th class="board-th">작성자</th>
			<td class="board-td">${data.username }</td>
		</tr>
	
		<tr>
			<th class="board-th">작성일</th>
			<td class="board-td">${data.boardDate}</td>
		</tr>
		<tr>
			<td></td>
		</tr>
	
	</table>
	<div>${data.boardContent }</div>
</div>
	<div class="btn-list">
		<div class="write-btn">
		<button class="learn-more" onClick="location.href='/board/update/${data.boardNo }'">
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
			<div class="write-btn">
		<button class="learn-more" onClick="location.href='/board/list'">
			<span class="circle" aria-hidden="true"> <span
				class="icon arrow"></span>
			</span> <span class="button-text">목록</span>
		</button>
	</div>
	</div>
	<p>댓글</p>

	<div class="reply-div">
	<form action="/reply/insert" method="post" class="reply-form">
		<div>
			
			<input type="text" value="${data.boardNo }" name="boardNo" hidden> 
			<input type="text" name="replyContent" class="reply-content-input">
		</div>
		<input type="submit" class="board-view-submit" value="제출">
	</form>
	</div>
		<div>
		<c:forEach var="r" items="${reply}">
		<table>
			<tr>
			<td>${ r.replyContent }</td>
			<td><form action="/reply/delete/${r.replyNo}" method="post">
				<input type="text" name="boardNo" hidden="hidden" value="${data.boardNo}"> 
				<input type="submit" id="deleteBtn" value="삭제">
			</form>
			</td>
			</tr>
		</table>
		</c:forEach>
	</div>
</section>
<script>
	
</script>