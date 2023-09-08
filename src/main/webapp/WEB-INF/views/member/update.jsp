<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<body>
<div id="infoTable" align="center">
<form action="/member/update" method="post">
	<table>
		<tr><td>계정</td><td><input type="text" readonly="readonly" value="${data.username}" id="username" name="username"></td></tr>
		<tr><td>이메일</td><td><input type="text" value="${data.email}" id="email" name="email"/></td></tr>
		<tr><td>휴대폰번호</td><td><input type="text" value="${data.mobile}" id="mobile" name="mobile"></td></tr>
		<tr><td>성별</td><td>
			<c:choose>
				<c:when test="${data.gender eq 'true' }">
					남자
				</c:when>	
				<c:when test="${data.gender eq 'false' }">
					여자
				</c:when>	
			</c:choose>
		</td></tr>
		<tr><td>생년월일</td><td>${data.birthDate}</td></tr>
		<tr><td>가입일</td><td>${data.regDate}</td></tr>
		<tr><td>
		<input type="submit" value="개인 정보 수정" id="changeMemberInfo">
		<input type="button" value="비밀번호 변경" onclick="#" id="changePassword">
		<input type="button" value="회원 탈퇴" onclick="location.href='/member/delete'" id="deleteMemberInfo">
		</td></tr>
	</table>
	</form>
</div>
</body>
</html>
