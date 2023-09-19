<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://fonts.googleapis.com/css?family=Inter:400,500,600,700&amp;display=swap">
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.2.6/gsap.min.js"></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/16327/Physics2DPlugin3.min.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/login.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/member/userHeader.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/member/join.js" defer></script>
<script src="${pageContext.request.contextPath}/js/login.js" defer></script>
<script src="https://kit.fontawesome.com/9e5ba2e3f5.js"
        crossorigin="anonymous"></script>
<%@ include file="/WEB-INF/views/layouts/header.jsp" %>

<!-- 이미 로그인된 상태에서는 로그인 페이지 진입시 홈 화면으로 이동 -->
<sec:authorize access="isAuthenticated()">
    <c:redirect url="/"/>
</sec:authorize>

<section>
    <!--     <div class="user-login-main">
        <div class="login-top">
            <p class="movie-login">LOGIN</p>
        </div>
        <form action="/login" method="post">
            <div class="login-form">
                <div class="login-id">
                    <input type="text" required class="login login-id-text" name="username">
                    <span>ID</span>
                </div>
                <div class="login-pwd">
                    <input type="password" required class="login login-pwd-text" id="login-pwd" name="password">
                    <span>Password</span>
                    <div id="toggle-pwd" onclick="showHide();"></div>
                </div>
                <div class="login-btn-div">
                    <input type="submit" class="login-btn" value="로그인"/>
                </div>
                <ul>
                    <li>
                        <a href="/member/join">회원가입</a>
                    </li>
                    <li>
                        <a href="#">비밀번호 재설정</a>
                    </li>
                </ul>
            </div>
        </form>
    </div> -->


    <div class="wrapper">
        <div class="form-container sign-up" style="align-items: flex-start">
            <form action="/member/join" method="post" id="join-form" style="margin-top:5%">
                <span id="emailAuthWarn"></span>
                <h2>sign up</h2>
                <div class="form-group" style="display: flex;">
                    <input class="join-input-id" class="check-input" id="username"
                           name="username" type="text" style="width: 70%;"/> <label for="">ID</label>
                    <i class="fas fa-user"></i>
                    <button class="join-input-btn join-btn" id="check-dup">중복확인</button>
                </div>
                <div class="form-group" style="display: flex;">
                    <input type="text" class="form-control check-input" id="memail"
                           name="email" style="width: 70%;"> <label for="memail">email</label>
                    <i class="fas fa-at"></i>
                    <button class="btn-outline-primary join-btn" type="button"
                            id="checkEmail">인증번호
                    </button>
                </div>
                <div class="form-group certification-number" style="display: flex;">
                    <input type="text" class="form-control authCode" id="memailconfirm"
                           style="width: 70%;"> <label for="memailconfirm"
                                                       id="memailconfirmTxt">인증번호 입력</label>

                    <button class="join-input-btn join-btn" id="confirmEmail">인증번호
                        확인
                    </button>
                </div>
                <div class="form-group">
                    <input class="join-input" id="password" name="password"
                           type="password"/> <label for="">password</label> <i
                        class="fas fa-lock"></i>
                </div>
                <div class="form-group">
                    <input class="join-input" id="password-confirm" type="password"/>
                    <label for="">confirm password</label> <i class="fas fa-lock"></i>
                </div>

                <div class="form-group">
                    <input class="join-input" id="mobile" name="mobile" type="text"/>
                    <label for="">phone</label> <i class="fa-solid fa-mobile-retro"></i>
                </div>
                <label for="" class="label-name">생년월일</label>
                <div class="form-group" style="margin-top: 2px;">
                    <input class="join-input" type="date" id="birth-date"
                           name="birthDate"/>
                    <!-- <i class="fas fa-lock"></i> -->
                </div>
                <div>
                    <label for="" class="label-name">성별</label>
                    <div>
                        <label> <input type="radio" name="gender" value="1"/> <span><i
                                class="fa-solid fa-person"></i>&nbsp;&nbsp;남</span>
                        </label> <label> <input type="radio" name="gender" value="0"/> <span><i
                            class="fa-solid fa-person-dress"></i>&nbsp;&nbsp;여</span>
                    </label>
                    </div>
                </div>
                <button type="submit" class="btn">sign up</button>

                <div class="link">
                    <p>
                        You already have an account?<a href="#" class="signin-link">
                        sign in</a>
                    </p>
                </div>
            </form>


        </div>
        <div class="form-container sign-in">
            <form action="/login" method="post">
                <h2>login</h2>
                <div class="form-group">
                    <input type="text" required name="username"> <i
                        class="fas fa-user"></i> <label for="">username</label>
                </div>
                <div class="form-group">
                    <input type="password" required name="password"> <i
                        class="fas fa-lock"></i> <label for="">password</label>
                </div>
                <div class="forgot-pass">
                    <a href="#">forgot password?</a>
                </div>
                <button type="submit" class="btn">login</button>

                <div class="link">
                    <p>
                        Don't have an account?<a href="#" class="signup-link"> sign up</a>
                    </p>
                </div>
            </form>

        </div>
    </div>
</section>

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
