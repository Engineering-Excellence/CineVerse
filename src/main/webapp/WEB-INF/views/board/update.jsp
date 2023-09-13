<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/write.css">
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
<script src="${pageContext.request.contextPath}/js/board/boardWrite.js" defer></script>
<section>
    <h1>CKEditor</h1>
    <form action="/board/update" method="POST">
    <input type="text" value="${data.boardNo}" name="boardNo">
    <input type="text" name="boardTitle"  name="boardTitle" placeholder="제목을 입력하세요" value="${data.boardTitle }">

      <textarea name="boardContent" name="boardContent" id="editor">${data.boardContent}</textarea>
    
    <p><input type="submit" value="수정"></p>
    
    </form>
</section>