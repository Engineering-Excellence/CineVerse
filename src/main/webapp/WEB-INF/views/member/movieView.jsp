<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieView.css">
<script src="${pageContext.request.contextPath}/js/member/movieView.js" defer></script>
<script>
    <sec:authorize access="isAuthenticated()">
    var isLogin = true;
    var username = "<sec:authentication property="principal.username"/>"
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
    var isLogin = false;
    </sec:authorize>
</script>

   <section class="view-main">
      <!-- 검색 -->
      <div class="moviecard">
<%--                <div>--%>
<%--                  <div class="heart-div">--%>
<%--                    <div class="love action">--%>
<%--                      <div class="heart"></div>--%>
<%--                    </div>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--        <div class="movie-poster play-trailer"><img class="poster-img" src="${pageContext.request.contextPath}/images/test.jpg"></div>--%>
<%--        <div id="movie-content">--%>
<%--          <div class="movie-ratings"><span class="star">★</span><span class="score">7.7</span><span class="score-out-of">/ 10 (IMDB)</span></div>--%>
<%--          <div class="movie-title"><a href="http://www.imdb.com/title/tt3397884" target="_blank">Sicario</a><span class="movie-year">2015</span></div>--%>
<%--          <div class="movie-details"><span class="movie-rating">R</span><span class="movie-duration"><img src="${pageContext.request.contextPath}/images/clock.png" class="clock">2h 1min</span><span class="movie-genre">Action, Crime, Drama</span></div>--%>
<%--          <div class="movie-castcrew"><span class="title">Director</span><span class="name">Denis Villeneuve</span></div>--%>
<%--          <div class="movie-castcrew"><span class="title">Writer</span><span class="name">Taylor Sheridan</span></div>--%>
<%--          <div class="movie-castcrew"><span class="title">Cast</span><span class="name">Emily Blunt, Josh Brolin, Benicio Del Toro</span></div>--%>
<%--          <div class="movie-synopsis">An idealistic FBI agent is enlisted by a government task force to aid in the escalating war against drugs at the border area between the U.S. and Mexico.</div>--%>
<%--          <button class="open-chat" type="button">오픈톡 바로가기</button>--%>
<%--        </div>--%>
      </div>
      
         <div style="padding-left: 180px;">
            <h2>이런 영화는 어떠세요?</h2>
         </div>
         <div class="content-wrapper">
<%--            <div class="news-card">--%>
<%--                <a href="#" class="news-card__card-link"></a>--%>
<%--                <img src="https://images.pexels.com/photos/247599/pexels-photo-247599.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260" alt="" class="news-card__image">--%>
<%--                <div class="news-card__text-wrapper">--%>
<%--                  <h2 class="news-card__title">Amazing Title</h2>--%>
<%--                  <div class="news-card__post-date">Jan 29, 2018</div>--%>
<%--                  <div class="news-card__details-wrapper">--%>
<%--                    <p class="news-card__excerpt">Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis beatae&hellip;</p>--%>
<%--                    <a href="#" class="news-card__read-more">상세보기</a>--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--              </div>--%>
          </div>
    </section>