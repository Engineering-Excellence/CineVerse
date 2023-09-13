<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css"> --%>
<section>
<c:forEach var="data" items="${data}">
<a href="/board/view/${data.boardNo}">${data.boardTitle}</a>
</c:forEach> 


<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">Larry the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
<a href="/board/view">상세보기</a>
</section>