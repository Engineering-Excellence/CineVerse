<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css">
<script src="${pageContext.request.contextPath}/js/member/movieList.js" defer></script>
<script>
    <sec:authorize access="isAuthenticated()">
    var isLogin = true;
    var username = "<sec:authentication property="principal.username"/>"
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
    var isLogin = false;
    </sec:authorize>
</script>
<section>
	<div ><a href="/movie/list" class="movie-title-now">현재 상영중인 영화</a></div>
    <div class="search-div">
    <sec:authorize access="isAuthenticated()">
    	<button class="heart-btn" onclick="location.href='/movie/myLoved'"><img src="${pageContext.request.contextPath}/images/heart.png" class="heart-img">찜목록 바로가기</button>
     </sec:authorize>
        
        <div id="search">
            <input id="input" placeholder="영화 제목을 입력하세요"/>
            <button class="search-btn">Search</button>
        </div>

    </div>
    <div class="master">
        <div class="movie-list">
            <%--    <div class="movie-list-item">--%>
            <%--  <div>--%>
            <%--    <div class="heart-div">--%>
            <%--      <div class="love action">--%>
            <%--        <div class="heart"></div>--%>
            <%--      </div>--%>
            <%--    </div>--%>
            <%--  </div>--%>
            <%--    <div class="container-movie">--%>
            <%--        <div class="card">--%>
            <%--        <div class="card__background"><img src=""></div>--%>
            <%--        <div class="card__shadow"></div>--%>
            <%--        <div class="card__info">--%>
            <%--          <div class="card__name">omar bourhaouta</div>--%>
            <%--          <div class="card__about"><span class="age">22</span>, <span class="country">morocco</span></div>--%>
            <%--          <div class="card__description">I'm a web designer and front-end web developer.</div>--%>
            <%--        </div>--%>
            <%--        <div class="card__follow"><a href="#"><span>상세보기</span></a></div>--%>
            <%--        </div>--%>
            <%--    </div>--%>
            <%--    </div>--%>
        </div>
        <div class="see-more">
            <button class="bubbly-button" id="more-btn">See More</button>
        </div>
    </div>
</section>
