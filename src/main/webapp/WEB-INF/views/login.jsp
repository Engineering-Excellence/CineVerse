<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Spring Legacy Final Project">
    <meta name="author" content="Team Annotation">
    <title>@Annotation</title>
    <link rel="icon" href="#">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/userHeader.css"/>
    <%--<link rel="stylesheet" href="../css/main.css">--%>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js"></script>
</head>
<body>
    <header>
        <div class="main-logo">
            <a href="/"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
        </div>
        <div class="main-menu"> 
            <ul class="menu-list">
                <li class="menu-li"><a href="/movie/list" class="menu-list-name">전체영화</a></li>
                <li class="menu-li"><a href="/ticket/list" class="menu-list-name">예매현황</a></li>
                <li class="menu-li"><a href="/board/list" class="menu-list-name">게시판</a></li>
            </ul>
        </div>
        <div class="main-login">
            <a href="/login" class="menu-list-name">로그인</a>
        </div>
    </header>
    
<section>

	<form action="/login" method="post">
		<div class="login-form">
			<div class="login-id">
				<input type="text" required class="login login-id-text"
					name="username"> <span>ID</span>
			</div>
			<div class="login-pwd">
				<input type="password" required class="login login-pwd-text"
					id="login-pwd" name="password"> <span>Password</span>
				<div id="toggle-pwd" onclick="showHide();"></div>
			</div>
			<div class="login-btn-div">
				<input type="submit" class="login-btn" value="로그인">
			</div>
			<ul>
				<li><a href="/member/join">회원가입</a></li>
				<li><a href="#">비밀번호 재설정</a></li>
			</ul>
		</div>
	</form> 
</section>
<footer>footer</footer>
</body>
