<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css"> --%>

<section>
	<p>${data.boardTitle}</p>
	<p>${data.boardContent}</p>
	<form action="/board/delete/${data.boardNo }" method="post">
		<input type="submit" id="deleteBtn" value="삭제">
	</form>
	<a href="/board/update/${data.boardNo }">수정</a>
	
	<div>
	<c:forEach var="r" items="${reply}">
			${ r.replyContent }
	<form action="/reply/delete/${r.replyNo}" method="post">
		<input type="text" name="boardNo" hidden="hidden" value="${data.boardNo}">
		<input type="submit" id="deleteBtn" value="삭제">
	</form>
	</c:forEach>
	</div>
	<form action="/reply/insert" method="post">
		<div>
		<p>댓글쓰기</p>
			<input type="text" value="${data.boardNo }" name="boardNo">
			<input type="text" name="replyContent">
		</div>
		<input type="submit" value="제출">
	</form>
</section>
<script>

	
</script>