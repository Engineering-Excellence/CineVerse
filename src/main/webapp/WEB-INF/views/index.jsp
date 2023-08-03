<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, height=device-height initial-scale=1">
    <meta name="description" content="Spring Legacy Project for Daebo">
    <meta name="author" content="Team Annotation">
    <title>로그인 화면</title>
    <link rel="icon" href="#">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js" defer></script>
</head>

<body>
<fmt:requestEncoding value="UTF-8"/>
<div id="wrap" class="wrap">
    <div class="main-top">
        <header id="header" class="header">Annotation</header>
    </div>
    <div id="container" class="container" style="margin-top: 30px;">
        <form action="loginOK" method="post">
            <p>Annotation</p>
            <hr width="650">
            <table width="650" border="2" cellpadding="5" cellspacing="0">
                <tr>
                    <th class="thTag">아이디</th>
                    <td class="tdTag">
                        <input id="accountID" class="form-control" type="text" name="accountID"
                               autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <th class="thTag">비밀번호</th>
                    <td class="tdTag">
                        <input id="accountPassword" class="form-control" type="password" name="accountPassword">
                    </td>
                </tr>
                <tr>
                    <td id="tdTag2" colspan="2" width="500" align="center">
                        <input id="submit" class="btn btn-primary" type="submit" value="로그인"></input>
                        <input class="btn btn-success" type="button" value="회원가입" width="82"
                               onclick="location.href='registerForm'"/>
                        <input class="btn btn-warning" type="button" value="ID 찾기" width="120"
                               onclick="location.href='idFindView'"/>
                        <input class="btn btn-warning" type="button" value="PW 찾기" width="120"
                               onclick="location.href='pwFindView'"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>

</html>