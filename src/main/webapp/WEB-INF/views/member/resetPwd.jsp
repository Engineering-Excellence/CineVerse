<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://fonts.googleapis.com/css?family=Inter:400,500,600,700&amp;display=swap">
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js" defer></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/Physics2DPlugin3.min.js" defer></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/resetPwd.css">

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/member/resetPwd.js" defer></script>
<script src="https://kit.fontawesome.com/9e5ba2e3f5.js"
        crossorigin="anonymous" defer></script>

<c:if test="${!valid}">
	<script>
		swal("실패", "만료된 링크 입니다", "error")
		.then(() => {
			window.location.href = "/login";
		});
	</script>
</c:if> 
<section>
	
    <div class="wrapper">
       
        <div class="form-container sign-in">
                <h2>비밀번호 재설정</h2>
                <p></p>
            <form action="/member/resetpwd" method="post" id="reset-pwd">  
                <div class="form-group">
                	<input type="text" hidden name="username" id="username">
                	<input type="text" hidden name="code" id="code">
                    <input type="password" required name="password" id="password"> <i class="fas fa-user"></i> <label for="password">새로운 비밀번호</label>
                </div>
                <div class="form-group">
                     <input type="password" class="form-control check-input" name="newpassword" id="password-confirm">
                     <i class="fas fa-lock"></i> <label for="password-confirm">비밀번호 확인</label>
                </div>
                    <button class="btn-outline-primary join-btn" type="submit">변경 </button>
			</form>
            
        </div>
    </div>
</section>


