<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/write.css">
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>
<script src="${pageContext.request.contextPath}/js/board/boardWrite.js" defer></script>
<section>
    <h1>CKEditor</h1>
    <form action="/board/write" method="POST">
    
    <input type=text" name="boardTitle" placeholder="제목을 입력하세요">

      <textarea name="boardContent" id="editor"></textarea>
    
    <p><input type="submit" value="전송"></p>
    
    </form>
</section>