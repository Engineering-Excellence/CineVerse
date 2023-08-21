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
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" href="../css/member/header.css"/>
    <%--<link rel="stylesheet" href="../css/main.css">--%>
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap/bootstrap.bundle.js" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
    <script type="text/javascript" src="../js/validation.js"></script>
</head>

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
