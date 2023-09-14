<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/header.css"/>
<script src="${pageContext.request.contextPath}/js/login.js" defer></script>
<%@ include file="/WEB-INF/views/layouts/header.jsp" %>

<!-- 이미 로그인된 상태에서는 로그인 페이지 진입시 홈 화면으로 이동 -->
<sec:authorize access="isAuthenticated()">
    <c:redirect url="/"/>
</sec:authorize>

<section>
    <div class="user-login-main">
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
    </div>
</section>

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
