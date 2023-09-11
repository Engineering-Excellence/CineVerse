<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
    <sec:authentication var="user" property="principal" />
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
<%--                <sec:authentication property="principal.username"/>--%>

            <sec:authorize access="isAuthenticated()">
                <a href="#" class="menu-list-name">마이페이지</a>
                <a href="/logout" class="menu-list-name">로그아웃</a>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a href="/login" class="menu-list-name">로그인</a>
            </sec:authorize>
        </div>
    </header>