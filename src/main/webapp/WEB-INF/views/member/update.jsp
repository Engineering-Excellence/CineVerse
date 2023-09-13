<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
@import url(https://fonts.googleapis.com/css?family=Varela+Round);

@import
	url(https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700);
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/myPage.css" />
<script src="${pageContext.request.contextPath}/js/member/memberMypage.js"></script>
</head>
<body style="margin: 0px;">
	<script>
		<sec:authorize access="isAuthenticated()">
			var username = '${data.username}';
			var password = '${data.password}';
			var email = '${data.email}';
			var mobile = '${data.mobile}';
			var gender = ${data.gender};
			var birthDate = '${data.birthDate}';
			var regDate = '${data.regDate}';
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			alert("로그인해주세요");
			window.location.href = "/login";
		</sec:authorize>
	</script>

	<div class="resume-wrapper">
		<section class="profile section-padding">
			<div class="container">
				<div class="picture-resume-wrapper">
					<div class="picture-resume">
						<span><img
							src="https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg"
							alt="" /></span>
						<svg version="1.1" viewBox="0 0 350 350">
  
  <defs>
    <filter id="goo">
      <feGaussianBlur in="SourceGraphic" stdDeviation="8" result="blur" />
      <feColorMatrix in="blur" mode="matrix"
								values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 21 -9"
								result="cm" />
    </filter>
  </defs>
  
  
<g filter="url(#goo)">  
  
  <circle id="main_circle" class="st0" cx="171.5" cy="175.6" r="130" />
  
  <circle id="circle" class="bubble0 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble1 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble2 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble3 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble4 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble5 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble6 st1" cx="171.5" cy="175.6"	r="122.7" />
  <circle id="circle" class="bubble7 st1" cx="171.5" cy="175.6"	r="122.7" />
  <circle id="circle" class="bubble8 st1" cx="171.5" cy="175.6"	r="122.7" />
  <circle id="circle" class="bubble9 st1" cx="171.5" cy="175.6" r="122.7" />
  <circle id="circle" class="bubble10 st1" cx="171.5" cy="175.6" r="122.7" />

</g>  
</svg>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="name-wrapper">
					<h1>
						계정 <br />${data.username}</h1>
					<!-- YOUR NAME AND LAST NAME  -->
				</div>
				<div class="clearfix"></div>
				<br/>
				<button href="/member/update" class="user-a" id="updateMemberInfo">정보수정</button>
				<button href="/member/updatePassword" class="user-a" id="updatePassword">비밀번호 변경</button>
				<button href="/member/deleteForm" class="user-a" id="deleteMember">회원탈퇴</button>
				<form action="" method="post">
				<div class="contact-info clearfix">
					<!-- 개인정보 내용 -->
					<ul class="list-titles">
						<li>Email</li>
						<li>Mobile</li>
						<li>Gender</li>
						<li>Birth</li>
						<li>Join-date</li>
					</ul>
					<ul class="list-content ">
						<li><input type="text" value="${data.email}" id="email"
							name="email" /></li>
						<!-- YOUR PHONE NUMBER  -->
						<li><input type="text" value="${data.mobile}" id="mobile"
							name="mobile"></li>
						<!-- YOUR EMAIL -->
						<li><c:choose>
								<c:when test="${data.gender eq 'true' }">
						MALE
					</c:when>
								<c:when test="${data.gender eq 'false' }">
						FEMALE
					</c:when>
							</c:choose></li>
						<!-- YOUR WEBSITE  -->
						<li>${data.birthDate}</li>
						<!-- 생일 -->
						<li>${data.regDate}</li>
						<!-- YOUR STATE AND COUNTRY  -->
					</ul>
				</div>
				</form>
			</div>
		</section>

		<section class="experience section-padding">
			<div class="container">
				<!-- <h3 class="experience-title">Experience</h3> -->

				<div class="experience-wrapper">
					<div class="company-wrapper clearfix">
						<div class="experience-title">내가 쓴 글</div>
						<div>
							<table>
								<tr>
									<td>글1</td>
								</tr>
								<tr>
									<td>글2</td>
								</tr>
								<tr>
									<td>글3</td>
								</tr>
							</table>
						</div>
						<!-- NAME OF THE COMPANY YOUWORK WITH  -->
						<!-- THE TIME YOU WORK WITH THE COMPANY  -->
					</div>
					<a href="#">더보기 +</a>
				</div>
				<!-- <div class="job-wrapper clearfix">
          <div class="company-description">
          	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce a elit facilisis, adipiscing leo in, dignissim magna.</p>  <!-- JOB DESCRIPTION  
          </div>
        </div> -->

				<div class="experience-wrapper">
					<div class="company-wrapper clearfix">
						<div class="experience-title">내가 쓴 댓글</div>
						<div>
							<table>
								<tr>
									<td>글1</td>
								</tr>
								<tr>
									<td>글2</td>
								</tr>
								<tr>
									<td>글3</td>
								</tr>
							</table>
						</div>
						<!-- NAME OF THE COMPANY YOUWORK WITH  -->
						<!-- THE TIME YOU WORK WITH THE COMPANY  -->
					</div>
					<a href="#">더보기 +</a>
				</div>
				<!--Skill experience-->

				<div class="section-wrapper clearfix">
					<h3 class="section-title">내가 찜한 영화</h3>
					<div class="img-div">
						<img class="loved-img" src="밀수.jpg"> <img class="loved-img"
							src="밀수.jpg"> <img class="loved-img" src="밀수.jpg">
					</div>
				</div>
				<a href="#">더보기 +</a>
			</div>

		</section>
	</div>
</body>