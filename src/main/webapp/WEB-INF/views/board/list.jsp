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

				<tr onClick="location.href='/board/view?boardNo=${data.boardNo}'">
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
<!-- </section> -->


<!-- <span>이메일 </span> -->
	<div class="form-group last mb-4 email_input">
		<label for="memail" id="mailTxt">이메일을 입력해주세요</label> <input
			type="text" class="form-control" name="memail" id="memail">
	</div>
	<!-- <span>이메일 인증번호</span> -->
	<button class="btn btn-outline-primary" type="button" id="checkEmail">인증번호</button>


	<div class="form-group last mb-4 check_input">
		<label for="memailconfirm" id="memailconfirmTxt">인증번호를 입력해주세요</label>
		<input type="text" class="form-control" id="memailconfirm">
	</div>

</section>


<script>
//이메일 인증번호 요청 버튼 클릭 이벤트 처리
$("#checkEmail").click(function() {
var memail = $("#memail").val();

$.ajax({
   type: "POST",
   url: "/mail/confirm",
/*    dataType:"json", */
   data: {
      "email": memail
   },
   success: function(data) {
      alert("해당 이메일로 인증번호 발송이 완료되었습니다. 확인 부탁드립니다.");
      console.log("data: " + data);
      chkEmailConfirm(data);
   },
   error: function() {
      alert("이메일 인증번호 요청 중 오류가 발생했습니다.");
   }
});
});

//이메일 인증번호 확인 함수
function chkEmailConfirm(data) {
var memailconfirm = $("#memailconfirm").val();
var memailconfirmTxt = $("#memailconfirmTxt");

if (data !== memailconfirm) {
   memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>");
   $("#emconfirmchk").css({
      "color": "#FA3E3E",
      "font-weight": "bold",
      "font-size": "10px"
   });
} else {
   memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>");
   $("#emconfirmchk").css({
      "color": "#0D6EFD",
      "font-weight": "bold",
      "font-size": "10px"
   });
}
}


// 이메일 인증번호

/* var $checkEmail = $("#checkEmail"); 

$checkEmail.click(function() {
   $.ajax({
      type : "POST",
      url : "login/mailConfirm",
      data : {
         "email" : $memail.val()
      },
      success : function(data){
         alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
         console.log("data : "+data);
         chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);
      }
   })
})

	// 이메일 인증번호 체크 함수
	function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
		$memailconfirm.on("keyup", function(){
			if (data != $memailconfirm.val()) { //
				emconfirmchk = false;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
				$("#emconfirmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
				"font-size" : "10px"

				})
				//console.log("중복아이디");
			} else { // 아니면 중복아님
				emconfirmchk = true;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

				$("#emconfirmchk").css({
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"

				})
			}
		})
	} */
</script>
