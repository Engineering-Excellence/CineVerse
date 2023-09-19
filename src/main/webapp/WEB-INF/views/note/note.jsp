<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/note/note.js" defer></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/note/note.css"/>

	<sec:authorize access="isAuthenticated()">
		<sec:authentication var="user" property="principal"/>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
            <script>
            
            </script>
        </sec:authorize>
<section>
 <div class="wrapper">
        <div class="note-container">
            <div class="left">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">List</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Search</button>
					</li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
						<ul id="partner-people" class="people">
							<div class="partner-list">
							</div>
						</ul>
					</div>
					<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
						<div class="top">
							<input id="search-input" type="text" placeholder="Search" />
						</div>
						<ul class="people" id="search-people">
							<div class="search-list">
							</div>
						</ul>
					</div>
				</div>
            </div>
				<div class="right">
	                <div class="top"><span>상대방: <span class="name partnerName" id="partnerName"></span></span></div>
		                <div class="chat active-chat" data-chat="person2">
		                	<div class="chat-list">
		                	</div>
		                </div>
		                <div class="write">
	               			<input type="hidden" id="myAccount" value="${data.username}" name="noteWriter"> <%-- 발신인 - 현재 접속중인 계정 --%>
		               		<input type="hidden" id="noteListener" class="partnerName" value="${partnerName}" name="noteListener"> <%-- 수신인 -접속중인 아이디를 값으로 보냈기에 자기혼잣말이 출력되었음 --%>
							<input type="text" id="content" name="content">
							<button class="write-link send"> </button>
		                </div>
	            </div>
            
        </div>
    </div>
</section>
