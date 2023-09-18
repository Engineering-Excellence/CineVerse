<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/note/insertNote" method="post">
<table>
<tr><td>발신인</td><td><input type="text" value="${data.username}" readonly="readonly" name="noteWriter"></td></tr>
<tr><td>수신인</td><td><input type="text" name="noteListener"></td></tr>
<tr><td>내 용</td><td><input type="text" name="content"></td></tr>
</table>
<input type="submit" id="writenote" name="wirtenote" class="write-link send">
</form>
${note }
<table border="1">
<c:forEach items="${note}" var="notelist" >
<tr><td>쪽지번호</td><td>발신자</td><td>내용</td></tr>
<tr><td>${notelist.noteNo }</td><td>${notelist.noteWriter }</td><td>${notelist.content}</td></tr>
</c:forEach>
</table>
</body>
</html>