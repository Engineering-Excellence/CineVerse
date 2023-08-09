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
    <title>index.jsp</title>
    <link rel="icon" href="#">
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap/bootstrap.bundle.js" defer></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" defer></script>
</head>

<body>
<fmt:requestEncoding value="UTF-8"/>
<div class="container">
    <div class="body d-md-flex align-items-center justify-content-between">
        <div class="box-1 mt-md-0 mt-5">
            <img src="https://images.pexels.com/photos/2033997/pexels-photo-2033997.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500"
                 class="" alt="">
        </div>
        <div class=" box-2 d-flex flex-column h-100">
            <div class="mt-5">
                <p class="mb-1 h-1">Log in</p>
                <p class="text-muted mb-2">영화 감상의 시작</p>
                <div class="d-flex flex-column ">
                    <p class="text-muted mb-2">로그인 하기</p>
                    <div class="d-flex align-items-center">
                        <form action="/login" method="post">
                            <table>
                                <tr>
                                    <td>
                                        username
                                    </td>
                                    <td>
                                        <input type="text" name="username" id="username"><br>
                                    </td>
                                    <td rowspan="2">
                                        <input type="submit" value="로그인">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        password
                                    </td>
                                    <td>
                                        <input type="password" name="password" id="password">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div class="mt-3">
                        <p class="mb-0 text-muted">아직 회원이 아니세요?</p>
                        <div class="btn btn-primary">회원가입<span class="fas fa-chevron-right ms-1"></span></div>
                    </div>
                </div>
            </div>
            <div class="mt-auto">
                <p class="footer text-muted mb-0 mt-md-0 mt-4">By register you agree with our
                    <span class="p-color me-1">terms and conditions</span>and
                    <span class="p-color ms-1">privacy policy</span>
                </p>
            </div>
        </div>
        <span class="fas fa-times"></span>
    </div>
</div>
</body>

</html>
