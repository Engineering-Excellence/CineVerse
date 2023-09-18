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
                <div class="top">
                    <input type="text" placeholder="Search" />
                    <a href="javascript:;" class="search"></a>
                </div>
                <ul class="people">
						<div class="partner-list">
					<c:forEach items="${notePartner}" var="notelist" begin="0" end="${notePartner.size() -1 }">
		                    <div class="dialogPartner" name="dialogPartner">
		                    <li class="person" data-chat="person1">
			                       <span class="name">${notelist}</span>
									<%-- <span class="time">${notelist.noteDate }</span><br/> --%>
									<%-- <span class="preview">${notelist.content}<br/></span> --%>
									
		                    </li>
	                    	</div>
					</c:forEach> 
	                    </div><!-- 닫기 -->
                </ul>
            </div>
				<div class="right">
	                <div class="top"><span>상대방: <span class="name partnerName" id="partnerName"></span></span></div>
		                <div class="chat active-chat" data-chat="person2">
		                	<div class="chat-list">
<!-- 				                    <div class="bubble you"> -->
<%-- 				                    	${notelist.content } --%>
<!-- 				                    </div> -->
<!-- 				                    <div class="bubble me"> -->
<%-- 										${notelist.content } --%>
<!-- 				                    </div> -->
		                	</div>
		                </div>
<!-- 		                <form action="/note/insertNote" method="post"> -->
		                <div class="write">
	               			<input type="hidden" id="myAccount" value="${data.username}" name="noteWriter"> <%-- 발신인 - 현재 접속중인 계정 --%>
		               		<input type="hidden" id="noteListener" class="partnerName" value="${partnerName}" name="noteListener"> <%-- 수신인 -접속중인 아이디를 값으로 보냈기에 자기혼잣말이 출력되었음 --%>
							<input type="text" id="content" name="content">
							<button class="write-link send"> </button>
		                </div>
<!-- 		                </form> -->
	            </div>
            
        </div>
    </div>
</section>
