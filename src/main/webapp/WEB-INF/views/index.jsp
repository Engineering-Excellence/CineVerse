<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Spring Legacy Final Project">
    <meta name="author" content="Team Annotation">
    <title>CineVerse</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://unpkg.co/gsap@3/dist/gsap.min.js"></script>
    <!-- 풀페이지 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.min.js"
            integrity="sha512-gSf3NCgs6wWEdztl1e6vUqtRP884ONnCNzCpomdoQ0xXsk06lrxJsR7jX5yM/qAGkPGsps+4bLV5IEjhOZX+gg=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.css"
          integrity="sha512-TD/aL30dNLN0VaHVoh9voFlNi7ZuWQYtV4bkIJv2ulZ8mEEkZJ7IyGvDthMKvIUwzLmPONnjQlAi55HTERVXpw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/userHeader.css">


    <link src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/css/swiper.min.css">
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/js/swiper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    <%-- <link rel="icon" href="#">
    <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css">


    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <!-- 풀페이지 -->
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.min.js"
        integrity="sha512-gSf3NCgs6wWEdztl1e6vUqtRP884ONnCNzCpomdoQ0xXsk06lrxJsR7jX5yM/qAGkPGsps+4bLV5IEjhOZX+gg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/fullPage.js/3.1.2/fullpage.css"
        integrity="sha512-TD/aL30dNLN0VaHVoh9voFlNi7ZuWQYtV4bkIJv2ulZ8mEEkZJ7IyGvDthMKvIUwzLmPONnjQlAi55HTERVXpw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
        crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.scss">



    <link
        src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/css/swiper.min.css">
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.3.5/js/swiper.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.scss">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/header.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/login.css">
    <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/member/userHeader.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js" defer></script>
    <script
        src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"
        defer></script>
    <script type="text/javascript"
        src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"
        defer></script> --%>
    <script>
        <sec:authorize access="isAuthenticated()">
        var isLogin = true;
        var username = "<sec:authentication property="principal.username"/>"
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
        var isLogin = false;
        </sec:authorize>
    </script>
</head>

<body>
<fmt:requestEncoding value="UTF-8"/>
<div class="main-menu">
    <a href="/"><img src="${pageContext.request.contextPath}/images/logo.png" class="main-logo"></a>
    <ul class="main-row">
        <li class="main-cell"><a href="/movie/list" class="menu-a">전체영화</a></li>
        <li class="main-cell"><a href="/movie/ticket" class="menu-a">예매현황</a></li>
        <li class="main-cell"><a href="/board/list" class="menu-a">게시판</a></li>
        <li class="main-cell"><a href="/note/note" class="menu-a">쪽지</a></li>
        <sec:authorize access="isAuthenticated()">
            <li class="main-cell"><a href="/member/update" class="menu-a">마이페이지</a></li>
            <li class="main-cell"><a href="/logout" class="menu-a">로그아웃</a></li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li class="main-cell"><a href="/login" class="menu-a">로그인</a></li>
        </sec:authorize>
    </ul>
</div>

<div class="fullpage">

    <div class="section main-section1">
        <main class="flex__col">
            <nav class="menu flex">
                <a href="#" class="menu__left">
                    Lorem ipsum
                </a>
                <div class="menu__right flex__col">
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </nav>
            <section class="intro flex">
                Loading
                <div class="intro__red flex">
                    <div>AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</div>
                </div>
            </section>
            <section class="clip flex">
                <div class="clip__inner flex__col">
                    <h1 class="flex">CINEVERSE</h1>
                    <div class="h1__stroke flex">CINEVERSE</div>
                    <p>SOTD Challenge : Insprired by <a href="https://aanstekelijk.nl/">aanstekelijk.nl</a> website
                        animation (SOTD, Apr 13, 2023)</p>
                    <figure>
                        <video loop autoplay muted
                               poster="https://images.unsplash.com/photo-1494253188410-ff0cdea5499e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80">
                            <source src="https://www.paulrogerdev.fr/codepen/pexels-artem-podrez-4832087-1280x720-30fps.mp4"
                                    type="video/mp4">
                        </video>
                    </figure>
                    <video class="clip__bg" loop autoplay muted
                           poster="https://images.unsplash.com/photo-1494253188410-ff0cdea5499e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80">
                        <source src="https://www.paulrogerdev.fr/codepen/pexels-artem-podrez-4832087-1280x720-30fps.mp4"
                                type="video/mp4">
                    </video>
                    <svg width="0" height="0">
                        <defs>
                            <clipPath id="svgClipPath" clipPathUnits="objectBoundingBox">
                                <path d="M0.648438 0.00390625 L0.296875 0.00390625 L0.0976562 0.15625 L-0.00390625 1.003906 L0.644531 1.003906 L0.820312 0.882812 ZM0.648438 0.00390625"/>
                                <clipPath>
                        </defs>
                    </svg>
                </div>
            </section>
        </main>
        <div class="cursor flex">Hello</div>
        <div class="noise"></div>
    </div>

    <div class="section main-section2">

        <div class='wrapper'>
            <div><h2 class="boxoffice-title">일일박스오피스</h2></div>
            <div class='carousel' id="daily-boxoffice">
                <%--          <div class='carousel__item'>--%>
                <%--            <div class='carousel__item-head'>--%>
                <%--              1--%>
                <%--            </div>--%>
                <%--            <div class='carousel__item-body'>--%>
                <%--              <p class='title'>spouting whale</p>--%>
                <%--              <p>Unicode: U+1F433</p>--%>
                <%--            </div>--%>
                <%--          </div>--%>
                <%--          <div class='carousel__item'>--%>
                <%--            <div class='carousel__item-head'>--%>
                <%--              2--%>
                <%--            </div>--%>
                <%--            <div class='carousel__item-body'>--%>
                <%--              <p class='title'>whale</p>--%>
                <%--              <p>Unicode: U+1F40B</p>--%>
                <%--            </div>--%>
                <%--          </div>--%>
                <%--          <div class='carousel__item'>--%>
                <%--            <div class='carousel__item-head'>--%>
                <%--              3--%>
                <%--            </div>--%>
                <%--            <div class='carousel__item-body'>--%>
                <%--              <p class='title'>dolphin</p>--%>
                <%--              <p>Unicode: U+1F42C</p>--%>
                <%--            </div>--%>
                <%--          </div>--%>
                <%--          <div class='carousel__item'>--%>
                <%--            <div class='carousel__item-head'>--%>
                <%--              4--%>
                <%--            </div>--%>
                <%--            <div class='carousel__item-body'>--%>
                <%--              <p class='title'>fish</p>--%>
                <%--              <p>Unicode: U+1F41F</p>--%>
                <%--            </div>--%>
                <%--          </div>--%>
                <%--         <div class='carousel__item'>--%>
                <%--            <div class='carousel__item-head'>--%>
                <%--              5--%>
                <%--            </div>--%>
                <%--            <div class='carousel__item-body'>--%>
                <%--              <p class='title'>tropical fish</p>--%>
                <%--              <p>Unicode: U+1F420</p>--%>
                <%--            </div>--%>
                <%--          </div>--%>

            </div>
            <div><h2 class="boxoffice-title">주간박스오피스</h2></div>
            <div class='carousel' id="weekly-boxoffice">
                <%--            <div class='carousel__item'>--%>
                <%--                <div class='carousel__item-head'>--%>
                <%--                  1--%>
                <%--                </div>--%>
                <%--                <div class='carousel__item-body'>--%>
                <%--                  <p class='title'>spouting whale</p>--%>
                <%--                  <p>Unicode: U+1F433</p>--%>
                <%--                </div>--%>
                <%--              </div>--%>
                <%--              <div class='carousel__item'>--%>
                <%--                <div class='carousel__item-head'>--%>
                <%--                  2--%>
                <%--                </div>--%>
                <%--                <div class='carousel__item-body'>--%>
                <%--                  <p class='title'>whale</p>--%>
                <%--                  <p>Unicode: U+1F40B</p>--%>
                <%--                </div>--%>
                <%--              </div>--%>
                <%--              <div class='carousel__item'>--%>
                <%--                <div class='carousel__item-head'>--%>
                <%--                  3--%>
                <%--                </div>--%>
                <%--                <div class='carousel__item-body'>--%>
                <%--                  <p class='title'>dolphin</p>--%>
                <%--                  <p>Unicode: U+1F42C</p>--%>
                <%--                </div>--%>
                <%--              </div>--%>
                <%--              <div class='carousel__item'>--%>
                <%--                <div class='carousel__item-head'>--%>
                <%--                  4--%>
                <%--                </div>--%>
                <%--                <div class='carousel__item-body'>--%>
                <%--                  <p class='title'>fish</p>--%>
                <%--                  <p>Unicode: U+1F41F</p>--%>
                <%--                </div>--%>
                <%--              </div>--%>
                <%--             <div class='carousel__item'>--%>
                <%--                <div class='carousel__item-head'>--%>
                <%--                  5--%>
                <%--                </div>--%>
                <%--                <div class='carousel__item-body'>--%>
                <%--                  <p class='title'>tropical fish</p>--%>
                <%--                  <p>Unicode: U+1F420</p>--%>
                <%--                </div>--%>
                <%--              </div>--%>
            </div>
        </div>

    </div>
    <%--  --%>
    <%--  <div class="section main-section3">--%>
    <%--    <div class="wrapper-list">--%>
    <%--        <!-- <div class="background-list">--%>
    <%--          <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132206/news-slider/background.webp" alt="">--%>
    <%--        </div> -->--%>
    <%--        <div class="item-bg"></div>--%>
    <%--      --%>
    <%--        <div class="news-slider">--%>
    <%--          <div class="news-slider__wrp swiper-wrapper">--%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">24</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-2.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">25</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-3.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">26</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-4.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">27</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-2.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">28</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-5.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__item swiper-slide">--%>
    <%--              <a href="#" class="news__item">--%>
    <%--                <div class="news-date">--%>
    <%--                  <span class="news-date__title">29</span>--%>
    <%--                  <span class="news-date__txt">May</span>--%>
    <%--                </div>--%>
    <%--                <div class="news__title">--%>
    <%--                  Lorem Ipsum Dolor Sit Amed--%>
    <%--                </div>--%>
    <%--      --%>
    <%--                <p class="news__txt">--%>
    <%--                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...--%>
    <%--                </p>--%>
    <%--      --%>
    <%--                <div class="news__img">--%>
    <%--                  <img src="https://res.cloudinary.com/muhammederdem/image/upload/q_60/v1537132205/news-slider/item-4.webp" alt="news">--%>
    <%--                </div>--%>
    <%--              </a>--%>
    <%--            </div>--%>
    <%--          </div>--%>
    <%--      --%>
    <%--          <div class="news-slider__ctr">--%>
    <%--      --%>
    <%--            <div class="news-slider__arrows">--%>
    <%--              <button class="news-slider__arrow news-slider-prev">--%>
    <%--                <span class="icon-font">--%>
    <%--                  <svg class="icon icon-arrow-left"><use xlink:href="#icon-arrow-left"></use></svg>--%>
    <%--                </span>--%>
    <%--              </button>--%>
    <%--              <button class="news-slider__arrow news-slider-next">--%>
    <%--                <span class="icon-font">--%>
    <%--                  <svg class="icon icon-arrow-right"><use xlink:href="#icon-arrow-right"></use></svg>--%>
    <%--                </span>--%>
    <%--              </button>--%>
    <%--            </div>--%>
    <%--      --%>
    <%--            <div class="news-slider__pagination"></div>--%>
    <%--      --%>
    <%--          </div>--%>
    <%--      --%>
    <%--        </div>--%>
    <%--      --%>
    <%--      </div>--%>
    <%--      --%>
    <%--      <svg hidden="hidden">--%>
    <%--        <defs>--%>
    <%--          <symbol id="icon-arrow-left" viewBox="0 0 32 32">--%>
    <%--            <title>arrow-left</title>--%>
    <%--            <path d="M0.704 17.696l9.856 9.856c0.896 0.896 2.432 0.896 3.328 0s0.896-2.432 0-3.328l-5.792-5.856h21.568c1.312 0 2.368-1.056 2.368-2.368s-1.056-2.368-2.368-2.368h-21.568l5.824-5.824c0.896-0.896 0.896-2.432 0-3.328-0.48-0.48-1.088-0.704-1.696-0.704s-1.216 0.224-1.696 0.704l-9.824 9.824c-0.448 0.448-0.704 1.056-0.704 1.696s0.224 1.248 0.704 1.696z"></path>--%>
    <%--          </symbol>--%>
    <%--          <symbol id="icon-arrow-right" viewBox="0 0 32 32">--%>
    <%--            <title>arrow-right</title>--%>
    <%--            <path d="M31.296 14.336l-9.888-9.888c-0.896-0.896-2.432-0.896-3.328 0s-0.896 2.432 0 3.328l5.824 5.856h-21.536c-1.312 0-2.368 1.056-2.368 2.368s1.056 2.368 2.368 2.368h21.568l-5.856 5.824c-0.896 0.896-0.896 2.432 0 3.328 0.48 0.48 1.088 0.704 1.696 0.704s1.216-0.224 1.696-0.704l9.824-9.824c0.448-0.448 0.704-1.056 0.704-1.696s-0.224-1.248-0.704-1.664z"></path>--%>
    <%--          </symbol>--%>
    <%--        </defs>--%>
    <%--      </svg>  --%>
    <%--  </div>--%>
    <div class="section main-section4">
        <div class="Background">
            <h1><br>
                <br>
                <br>
                <br>
                <br>
                <br>이런 영화는 <Br>어떠세요?
            </h1>


            <%--        <!--첫번째 영화-->--%>
            <%--       <div class="Movie">--%>
            <%--         <img src = "https://i.ytimg.com/vi/kCTisFDa9oo/maxresdefault.jpg" class="movie-img">--%>
            <%--         <div class="Summary">--%>
            <%--          <h2>Spider-Man--%>
            <%--              <br>스파이더맨</h2>--%>
            <%--          <p>마블 스튜디오 스파이더맨 시리즈의 첫 번째 작품이자 마블 시네마틱 유니버스 페이즈 3의 네 번째 작품이다. 새로운 스파이더맨 역에는 《더 임파서블》에서 좋은 연기를 보여 준 톰 홀랜드가 캐스팅되어 《캡틴 아메리카: 시빌 워》에서 첫 모습을 보였다.</p>--%>
            <%--          <a href="#" class="moivie-sum">상세보기</a>--%>
            <%--         </div>--%>
            <%--       </div>--%>
            <%--        --%>
            <%--        <!-- 두번째 영화 --> --%>
            <%--       <div class="Movie">--%>
            <%--         --%>
            <%--         <img src = "https://allears.net/wp-content/uploads/2020/10/scale.jpeg" class="movie-img">--%>
            <%--    --%>
            <%--         <div class="Summary">--%>
            <%--          <h2>Black Panther--%>
            <%--            <br>블랙팬서</h2>--%>
            <%--          <p>블랙 팬서 실사영화 시리즈의 첫 번째 영화이자, 마블 시네마틱 유니버스 페이즈 《3의 6번째 작품》에 해당한다. 마블 시네마틱 유니버스, 더 나아가 슈퍼히어로 영화 최초의 아카데미 작품상 후보작이며 마블 시네마틱 유니버스 최초의 아카데미 수상 작품이다.</p>--%>
            <%--          <a href="#" class="moivie-sum">상세보기</a>--%>
            <%--         </div>--%>
            <%--       </div>--%>
            <%--    --%>
            <%--       <!-- 세번째 영화  -->--%>
            <%--       <div class="Movie">--%>
            <%--         --%>
            <%--         <img src = "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https:%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2412713F5815F6550A" width=50% class="movie-img">--%>
            <%--    --%>
            <%--         <div class="Summary">--%>
            <%--          <h2>Doctor Strange--%>
            <%--            <br>닥터 스트레인지</h2>--%>
            <%--          <p>이 작품은 마블 시네마틱 유니버스의 《14번째 작품》에 해당한다. 영화 닥터 스트레인지는 불의의 사고로 절망에 빠진 신경외과 의사 스티븐 스트레인지가 에인션트 원을 만나, 세상을 구원할 강력한 능력을 얻게 되면서 히어로로 거듭나게 되는 이야기를 그리고 있다.</p>--%>
            <%--          <a href="#" class="moivie-sum">상세보기</a>--%>
            <%--          </div>--%>
            <%--       </div>--%>
        </div>
    </div>
</div>

</body>


<!--

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
-->
</html>
