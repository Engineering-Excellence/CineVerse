<%@ page import="java.util.Map" %>
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
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap/bootstrap.bundle.js" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
</head>

<body>
    <%
        request.setCharacterEncoding("UTF-8");

        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.printf("%s : %s%n", entry.getKey(), String.join(", ", entry.getValue()));
        }
    %>

<header class="annotation">@Annotation
    <a href="/logout"><img class="logout" src="images/logout.png" alt="로그아웃"/></a>
</header>
