<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/member/updatePassword" method="post">
			<div>
				<h3>현재 비밀번호</h3>
				<input type="password" id="oldPassword"	placeholder="현재 사용중인 비밀번호를 입력하시요"> <br />
				<h3>새 비밀번호</h3>
				<br /> <input type="password" id="newPassword"	placeholder="사용할 비밀번호를 입력해주세요."><br />
				<h3>비밀번호 확인</h3>
				<input type="password" id="confirmPassword"	placeholder="한번 더 입력해주세요">
			</div>
			<input type="submit" value="비밀번호 수정" id="changePassword">
		</form>
	</div>
</body>
</html>