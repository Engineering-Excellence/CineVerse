<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="!hasRole('ROLE_ADMIN')">
    <c:redirect url="/login"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Title</title>
</head>
<body>
<c:set var="paths" value="${fn:split(requestScope['javax.servlet.forward.request_uri'], '/')}"/>
<h2>ADMIN HOME</h2>
<c:forEach items="${paths}" var="i">
    ${i}
</c:forEach>
${data}
</body>
</html>
