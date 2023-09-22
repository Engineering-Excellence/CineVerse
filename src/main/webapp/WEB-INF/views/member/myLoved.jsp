<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css">
<script src="${pageContext.request.contextPath}/js/member/myLoved.js" defer></script>
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
    </div>
</section>
