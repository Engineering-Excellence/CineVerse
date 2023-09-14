<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
    
    
    
</section> --%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/write.css"> --%>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>

<!-- <script src="/js/summernote/summernote-lite.js"></script>
<script src="/js/summernote/lang/summernote-ko-KR.js"></script> 
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/list.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css"> -->
<section class="write-section">

<h1>글작성</h1>
<form action="/board/update" method="POST">
<input type="text" value="${data.boardNo}" name="boardNo" hidden>
<p class="write-p">제목</p>
<input type="text" class="board-title" name="boardTitle" placeholder="제목을 입력하세요." value="${data.boardTitle }">
<p class="write-p">내용</p>
<textarea name="boardContent" id="summernote" class="summernote">${data.boardContent}</textarea>

<div class="write-btn write-btn-margin">
  <button class="learn-more" type="submit">
    <span class="circle" aria-hidden="true">
      <span class="icon arrow"></span>
    </span>
    <span class="button-text">수정하기</span>
  </button>
  </div>
   </form>
  <div class="write-btn write-btn-margin">
  <button class="learn-more" onClick="location.href='/board/list'">
    <span class="circle" aria-hidden="true">
      <span class="icon arrow"></span>
    </span>
    <span class="button-text">목록으로</span>
  </button>
  </div>
</section>
<script>
/* $('.summernote').summernote({
	  height: 150,
	  lang: "ko-KR"
		  toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
	});
	 */
/* $(document).ready(function() {
    $('#summernote').summernote({
            height: 300,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
    });
}); */
/* $(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
}); */
/* $(function () {

    /* Summernote Validation 

    var summernoteForm = $('.form-validate-summernote');
    var summernoteElement = $('.summernote');

    var summernoteValidator = summernoteForm.validate({
        errorElement: "div",
        errorClass: 'is-invalid',
        validClass: 'is-valid',
        ignore: ':hidden:not(.summernote),.note-editable.card-block',
        errorPlacement: function (error, element) {
            // Add the `help-block` class to the error element
            error.addClass("invalid-feedback");
            console.log(element);
            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.siblings("label"));
            } else if (element.hasClass("summernote")) {
                error.insertAfter(element.siblings(".note-editor"));
            } else {
                error.insertAfter(element);
            }
        }
    });

    summernoteElement.summernote({
        height: 300,
        callbacks: {
            onChange: function (contents, $editable) {
                // Note that at this point, the value of the `textarea` is not the same as the one
                // you entered into the summernote editor, so you have to set it yourself to make
                // the validation consistent and in sync with the value.
                summernoteElement.val(summernoteElement.summernote('isEmpty') ? "" : contents);

                // You should re-validate your element after change, because the plugin will have
                // no way to know that the value of your `textarea` has been changed if the change
                // was done programmatically.
                summernoteValidator.element(summernoteElement);
            }
        }
    });

});
 */
 
 $('#summernote').summernote({
     placeholder: '내용을 작성해주세요.',
     tabsize: 3,
     height: 320,
     toolbar: [
       ['style', ['style']],
       ['font', ['bold', 'underline', 'clear']],
       ['color', ['color']],
       ['para', ['ul', 'ol', 'paragraph']],
       ['table', ['table']],
       ['insert', ['link', 'picture', 'video']],
       ['view', ['fullscreen', 'codeview', 'help']]
     ]
   });
</script>