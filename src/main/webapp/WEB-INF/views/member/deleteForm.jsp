<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member/delete" method="post">
		<div>
			<p>회원탈퇴를 진행하시면 회원정보가 바로 삭제됩니다. 탈퇴를 원하시면 밑에 비밀번호를 입력하십시오.</p>
			<br/>
			<input type="password" id="deleteInfoMember" placeholder="현재 사용중인 비밀번호를 입력하세요.">
			<input type="submit" value="회 원 탈 퇴" id="deleteMemberInfo">
		</div>
	</form>
</body>
</html>