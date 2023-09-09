<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/layouts/header.jsp" %>
<%--<%@ include file="/WEB-INF/views/layouts/side.jsp" %>--%>

<%-- controller에서 context path를 굳이 파싱해서 보내지 않아도 jsp 선에서 구할 수 있음 --%>
<c:set var="paths" value="${fn:split(requestScope['javax.servlet.forward.request_uri'], '/')}"/>

<c:choose>
    <c:when test="${not empty paths and paths[0] eq 'member'}">
        <c:choose>
            <c:when test="${paths[1] eq 'join'}">
                <%@ include file="/WEB-INF/views/member/join.jsp" %>
            </c:when>
        </c:choose>
    </c:when>
	<c:when test="${not empty paths and paths[0] eq 'movie'}">
        <c:choose>
            <c:when test="${paths[1] eq 'list'}">
                <%@ include file="/WEB-INF/views/member/movieList.jsp" %>
            </c:when>
            <c:when test="${paths[1] eq 'view'}">
                <%@ include file="/WEB-INF/views/member/movieView.jsp" %>
            </c:when>
        </c:choose>
    </c:when>
	<c:when test="${not empty paths and paths[0] eq 'ticket'}">
        <c:choose>
            <c:when test="${paths[1] eq 'list'}">
                <%@ include file="/WEB-INF/views/member/ticket.jsp" %>
            </c:when>
           
        </c:choose>
    </c:when>
    <c:when test="${not empty paths and paths[0] eq 'board'}">
        <c:choose>
            <c:when test="${paths[1] eq 'list'}">
                <%@ include file="/WEB-INF/views/member/boardList.jsp" %>
            </c:when>
           
        </c:choose>
    </c:when>
    <c:otherwise>
        <%@ include file="/WEB-INF/views/member/userMain.jsp" %>
    </c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>
