<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%@ include file="/WEB-INF/views/layouts/header.jsp" %>
<%--<%@ include file="/WEB-INF/views/layouts/side.jsp" %>--%>

<%-- controller에서 context path를 굳이 파싱해서 보내지 않아도 jsp 선에서 구할 수 있음 --%>
<c:set var="paths" value="${fn:split(requestScope['javax.servlet.forward.request_uri'], '/')}"/>


<c:if test="${paths[0] eq 'member'}">
    <c:if test="${paths[1] eq 'join'}">
     <%--  <%@ include file="/WEB-INF/views/member/join.jsp" %>  --%>
      
       <%@ include file="/WEB-INF/views/member/movieDetail.jsp" %>   
<%--        <%@ include file="/WEB-INF/views/member/movieList.jsp" %>  
  	   <%@ include file="/WEB-INF/views/member/userMain.jsp" %>  
	   --%>
    </c:if>
</c:if>

<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>