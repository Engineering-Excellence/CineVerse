<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://fonts.googleapis.com/css?family=Inter:400,500,600,700&amp;display=swap">
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js" defer></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/Physics2DPlugin3.min.js" defer></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/resetPwd.css">
    

<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/member/resetPwdMail.js" defer></script>
<script src="https://kit.fontawesome.com/9e5ba2e3f5.js"
        crossorigin="anonymous" defer></script>

<section>

    <div class="wrapper">
        
        <div class="form-container sign-in">
                <h2>비밀번호 재설정</h2>
                <div class="form-group">
                    <input type="text" required name="username" id="username"> <i class="fas fa-user"></i> <label for="username">username</label>
                </div>
                <div class="form-group">
                     <input type="text" class="form-control check-input" id="memail" name="email" >
                     <i class="fas fa-lock"></i> <label>email</label>
                </div>
                    <button class="btn join-btn" type="button" id="resetEmail">전송
                    </button>
<!--                 <div class="link">
                    <p>
                        Don't have an account?<a href="#" class="signup-link"> sign up</a>
                    </p>
                </div> -->
            
        </div>
    </div>
</section>

