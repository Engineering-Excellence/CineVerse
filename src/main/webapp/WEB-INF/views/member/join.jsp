<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>join.jsp</title>
    <link rel="icon" href="#">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
</head>

<body>
<fmt:requestEncoding value="UTF-8"/>
<form action="/member/join" method="post">
    <table>
        <tr>
            <th>ID</th>
            <td colspan="2">
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <th>PW</th>
            <td colspan="2">
                <input type="password" name="password" id="password">
            </td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td colspan="2">
                <input type="text" name="mobile" id="mobile" placeholder="010-xxxx-xxxx">
            </td>
        </tr>
        <tr>
            <th>이메일</th>
            <td colspan="2">
                <input type="email" name="email" id="email" placeholder="xxxx@example.com">
            </td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <input type="radio" name="gender" value="1">남성
            </td>
            <td>
                <input type="radio" name="gender" value="0">여성
            </td>
        </tr>
        <tr>
            <th>생일</th>
            <td colspan="2">
                <input type="date" name="birthDate" id="birthDate" placeholder="0000-00-00">
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="submit">
            </td>
        </tr>
    </table>
</form>
</body>

</html>
