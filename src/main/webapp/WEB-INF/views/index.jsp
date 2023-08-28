<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/login.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
</head>

<body class="login">
<fmt:requestEncoding value="UTF-8"/>
<div class="user-header">
    <p>LOGO</p>
    <ul>
        <li><a href="/login">로그인</a></li>
        <li><a href="/member/join">회원가입</a></li>
    </ul>
</div>

<div class="user-menu">
    <ul>
        <li>
            <a href="#">영화</a>
        </li>
        <li>
            <a href="#">예매</a>
        </li>
        <li>
            <a href="#">푸드스토어</a>
        </li>
    </ul>
</div>

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
                <input type="submit" class="login-btn" value="로그인">
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

<div class="user-footer">footer</div>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

</html>
