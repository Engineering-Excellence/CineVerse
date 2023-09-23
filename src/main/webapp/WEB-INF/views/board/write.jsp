<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/write.css">
<script
	src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
<section class="write-section">

	<h1 class="board-title">글작성</h1>

	<form action="/board/write" method="POST">
		<div class="board-content-div">
			<!-- 관리자에게만 공지박스 노출 -->
			<div class="write-title">
			<div style="display:flex;">
				<p class="write-p">제목</p>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="checkbox">
						<input type="hidden" name="isNotice" value="false" /> 
						<input id="notice" class="form-check-input" type="checkbox" name="notice" checked="${true}" /> 
						<label name="cb" for="notice" class="notice-label">공지글</label>
					</div>
				</sec:authorize>
			</div>
				<input type="text" name="boardTitle" class="board-title-input"
					placeholder="글 제목을 입력하세요.">
				<textarea name="boardContent" id="editor" placeholder="글 내용을 입력하세요."> </textarea>

				<!-- 등록하기 버튼 -->
				<div class="write-btn write-btn-margin">
					<button class="learn-more" type="submit">
						<span class="circle" (aria-hidden)="true">
						<span class="icon arrow"></span></span>
						<span class="button-text">등록하기</span>
					</button>
				</div>
			</div>
		</div>
	</form>

	<span class="write-btn write-btn-margin">
		<button class="learn-more" onClick="location.href='/board/list'">
			<span class="circle" aria-hidden="true"> <span
				class="icon arrow"></span>
			</span> <span class="button-text">목록으로</span>
		</button>
	</span>
</section>
<script>
    // 3. CKEditor5를 생성할 textarea 지정
    ClassicEditor
        .create(document.querySelector('#editor'), {
            height: 250,
            // 제거 하고싶은 플러그인 (배열)
            removePlugins: ['ImageUpload']
        })
        .catch(error => {
            console.error(error);
        });
</script>
