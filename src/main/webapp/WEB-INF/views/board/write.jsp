<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<<<<<<< Updated upstream
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/write.css"> --%>
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script type="text/javascript" src="/assets/js/summernote-lite.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css">
<link rel="stylesheet" type="text/css" href="/assets/css/summernote-lite.css" />		
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board/list.css">
		
<!-- 
<!--      jQuery, bootstrap 테이블이 완벽하게 안나옴 
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    summernote css/js
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>  
    -->
    <!-- include libraries(jQuery, bootstrap) -->
<!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

include summernote css/js
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script> -->

    
<section class="write-section">

<h1 class="board-title">글작성</h1>
<form action="/board/write" method="POST">
<p class="write-p">제목</p>
<input type="text" class="board-title" name="boardTitle" placeholder="제목을 입력하세요.">
<p class="write-p">내용</p>
<!-- <div id="summernote" name="boardContent"></div> -->
<textarea name="boardContent" id="summernote" class="summernote"></textarea>

<div class="write-btn write-btn-margin">
  <button class="learn-more" type="submit">
    <span class="circle" aria-hidden="true">
      <span class="icon arrow"></span>
    </span>
    <span class="button-text">등록하기</span>
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
/* summernoteInit : function(){
    
	var fonts = ['맑은 고딕', '돋움', '궁서', '굴림', '굴림체', '궁서체', '나눔 고딕', '바탕', '바탕체', '새굴림', 'HY견고딕', 'HY견명조', 'HY궁서B', 'HY그래픽M', 'HY목각파임B', 'HY신명조', 'HY얕은샘물M', 'HY엽서L', 'HY엽서M', 'HY중고딕', 'HY헤드라인M', '휴먼매직체', '휴먼모음T', '휴먼아미체', '휴먼둥근헤드라인', '휴먼편지체', '휴먼옛체'];
				    				
	// 에디터 삽입
	$('#summernote').summernote({
		placeholder: '자유롭게 글을 작성할 수 있습니다.
	명예훼손이나 상대방을 비방, 불쾌감을 주는 글, 욕설, 남을 모욕하는 글은 임의로 제제가 있을 수 있습니다.',
		tabsize: 2,
		minHeight : null,
		maxHeight : null,
		lang: 'ko-KR',
		height: 500,
		// close prettify Html
	    prettifyHtml:false,
	    fontNames: fonts.sort(),
	       imageTitle: {
	       	specificAltField: true,
	       },
	       popover: {
	           image: [
	           	['imageResize', ['resizeFull', 'resizeHalf', 'resizeQuarter', 'resizeNone']],
	               ['float', ['floatLeft', 'floatRight', 'floatNone']],
	               ['remove', ['removeMedia']],
	               ['custom', ['imageTitle']],
	           ],
	       },
		toolbar: [
			['Font Style', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['style']],
			['style', ['bold', 'italic', 'underline']],
			['font', ['strikethrough']],
			['color', ['color']],
			['para', ['paragraph']],
			['height', ['height']],
			['Insert', ['table']],
			['Insert', ['picture']],
			['Insert', ['video']],
			['Insert', ['link']],
			['misc', ['emoji']],
			['insert',  ['map']],
		        ['highlight', ['highlight']],
			['code', ['fullscreen', 'codeview']]
		],
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				var $_this = $(this);
			    	for (var i = files.length - 1; i >= 0; i--) {
		    		// 첨부파일 callback
		    		blogWrite.fn.sendFile(files[i], this, welEditable);
		    	}
				    	 */
/* 	    	// image gallery
	    		// init the state from the input
	      		$(".image-checkbox").each(function () {
	      			if ($(this).find('input[type="checkbox"]').first().attr("checked")) {
	      				$(this).addClass('image-checkbox-checked');
	       			} else {
	      				$(this).removeClass('image-checkbox-checked');
	      			}
	       		});
	      		// sync the state to the input
	      		$(".image-checkbox").on("click", function (e) {
	      			$(this).toggleClass('image-checkbox-checked');
	      			var $checkbox = $(this).find('input[type="checkbox"]');
	      			$checkbox.prop("checked",!$checkbox.prop("checked"));
	      			if($checkbox.prop("checked")){
	      				$($_this).summernote('insertImage', $(this).find('input[type="checkbox"]').val());
	      			}
	      			e.preventDefault();
	      		});
		}
	}
	});

 */



/*
$('.summernote').summernote({
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
     height: 300,
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
