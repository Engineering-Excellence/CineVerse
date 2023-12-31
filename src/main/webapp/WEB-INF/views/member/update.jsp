<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    @import url(https://fonts.googleapis.com/css?family=Varela+Round);
    @import url(https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700);
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/myPage.css"/>
<script src="${pageContext.request.contextPath}/js/member/memberMypage.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member/update.js" defer></script>
<script>
    // 분리 불가
    <sec:authorize access="isAuthenticated()">
    var username = '${data.username}'
    var password = '${data.password}'
    var email = '${data.email}'
    var mobile = '${data.mobile}'
    var gender =
    ${data.gender}
    var birthDate = '${data.birthDate}'
    var regDate = '${data.regDate}'
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
    swal(
        'Error!',
        '로그인해주세요.',
        'error'
    )
    window.location.href = "/login";
    </sec:authorize>
</script>
<section class="resume-wrapper">
    <div class="profile section-padding">
        <div class="container">
            <div class="picture-resume-wrapper">
                <div class="picture-resume">
                    <span>
                        <img id="profileImg" src="${relPath}"
                             onerror="this.onerror=null; this.src='https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg';"/>
                    </span>
                    <!-- Font Awesome link -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
                    <!-- 프로필 이미지 초기화 버튼-->
                    <input type="file" class="form-control" id="uploadProfile"
                           aria-describedby="inputGroupFileAddon04" aria-label="Upload"
                           accept="image/*" hidden="hidden"/>
                    <svg version="1.1" viewBox="0 0 350 350">
                        <defs>
                            <filter id="goo">
                                <feGaussianBlur in="SourceGraphic" stdDeviation="8" result="blur"/>
                                <feColorMatrix in="blur" mode="matrix"
                                               values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 21 -9"
                                               result="cm"/>
                            </filter>
                        </defs>
                        <g filter="url(#goo)">
                            <circle id="main_circle" class="st0" cx="171.5" cy="175.6" r="130"/>
                            <circle id="circle" class="bubble0 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble1 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble2 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble3 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble4 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble5 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble6 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble7 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble8 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble9 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                            <circle id="circle" class="bubble10 st1" cx="171.5" cy="175.6"
                                    r="122.7"/>
                        </g>
                    </svg>
                </div>
                <div class="deletebtn-div">
                    <button class="btn btn-danger" id="deleteProfile">
                        <span><i class="fas fa-trash-alt"></i><p class="delete-img">이미지 삭제</p></span>
                    </button>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="name-wrapper">
            <h1>
                계정 <br/>${data.username}</h1>
            <!-- YOUR NAME AND LAST NAME  -->
        </div>
        <div class="clearfix"></div>
        <br/>
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <button class="nav-link active"
                        data-bs-toggle="tab" data-bs-target="#nav-home" type="button"
                        role="tab" aria-controls="nav-home" aria-selected="true">정보수정
                </button>
                <button class="nav-link" data-bs-toggle="tab"
                        data-bs-target="#nav-profile" type="button" role="tab"
                        aria-controls="nav-profile" aria-selected="false">비밀번호 변경
                </button>
                <button class="nav-link" data-bs-toggle="tab"
                        data-bs-target="#nav-contact" type="button" role="tab"
                        aria-controls="nav-contact" aria-selected="false">회원탈퇴
                </button>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-home"
                 role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">
                <!--                 <form action="/member/update" method="post" id="memberAllInfo"> -->
                <div>
                    <input type="hidden" value="${data.username}" id="username"
                           name="username"/>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Email</span>
                        <input type="text" class="form-control" value="${data.email }" name="email" id="email">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Mobile</span>
                        <input type="text" class="form-control" value="${data.mobile }" name="mobile" id="mobile">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Gender</span>
                        <c:choose>
                            <c:when test="${data.gender eq 'true' }">
                                <input type="text" class="form-control" value="MALE" disabled="disabled">
                            </c:when>
                            <c:when test="${data.gender eq 'false' }">
                                <input type="text" class="form-control" value="FEMALE" disabled="disabled">
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Join-date</span>
                        <input type="text" class="form-control" readonly="readonly" value="${data.regDate }"
                               disabled="disabled">
                    </div>
                    <!--                         <input type="submit" id="changeInfo" class="btn" value="개인정보 변경" onclick="updateMemberInfo"> -->
                    <button id="changeInfo" class="btn">변경</button>
                </div>
                <!--                 </form> -->
            </div>

            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                 aria-labelledby="nav-profile-tab" tabindex="0">
                <!--                 <form action="/member/updatePassword" method="post"> -->
                <div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">현재 비밀번호</span>
                        <input type="password" id="oldPassword" name="oldPassword" class="form-control"
                               placeholder="사용중인 비밀번호를 입력해주세요.">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">새 비밀번호</span>
                        <input type="password" id="newPassword" name="newPassword" class="form-control"
                               placeholder="사용할 비밀번호를 입력해주세요.">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">비밀번호 확인</span>
                        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control"
                               placeholder="사용할 비밀번호를 한번 더 입력해주세요.">
                    </div>
                    <input type="submit" class="btn" id="changePW" value="비밀번호 변경">
                </div>
                <!--                 </form> -->
            </div>

            <div class="tab-pane fade" id="nav-contact" role="tabpanel"
                 aria-labelledby="nav-contact-tab" tabindex="0">
                <!--                 <form action="/member/delete" method="post"> -->

                <div class="input-group mb-3">
                    <span class="input-group-text">비밀번호 입력</span>
                    <input type="password" name="password" id="deleteInfoMember" class="form-control"
                           placeholder="사용중인 비밀번호를 입력하세요.">
                </div>
                <input type="submit" class="btn" id="deleteMember" value="회원 탈퇴">
                <!--                 </form> -->
                <br/>
                사용중인 비밀번호를 입력하시면 회원 탈퇴가 진행됩니다. <br/>탈퇴시 복구가 불가능합니다.
            </div>
            <div class="tab-pane fade" id="nav-disabled" role="tabpanel"
                 aria-labelledby="nav-disabled-tab" tabindex="0"></div>
        </div>
    </div>
    <div class="experience section-padding">
        <div class="container">
            <!-- <h3 class="experience-title">Experience</h3> -->
            <div class="experience-wrapper">
                <div class="company-wrapper clearfix">
                    <div class="experience-title">내가 쓴 글</div>
                    <div>
                        <table>
                            <c:if test="${board.size() > 0}">
                                <c:forEach var="i" begin="0" end="${Math.min(2, board.size() - 1)}">
                                    <tr>
                                        <td>
                                            <div class="board-preview">
                                                <div class="board-preview-boardNo">${board[i].boardNo}</div>
                                                <div class="board-preview-boardTitle"><a
                                                        href="/board/view/${board[i].boardNo}">${board[i].boardTitle}</a>
                                                </div>
                                                <div class="board-preview-boardView">${board[i].boardView}</div>
                                                <div class="board-preview-boardDate">${board[i].boardDate}</div>
                                            </div>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <%--                            <tr>--%>
                            <%--                                <td>글1</td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>글2</td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>글3</td>--%>
                            <%--                            </tr>--%>
                        </table>
                    </div>
                    <!-- NAME OF THE COMPANY YOUWORK WITH  -->
                    <!-- THE TIME YOU WORK WITH THE COMPANY  -->
                </div>
                <a href="/board/search?keyword=${data.username}&searchType=2">전체보기</a>
            </div>
            <!-- <div class="job-wrapper clearfix">
      <div class="company-description">
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce a elit facilisis, adipiscing leo in, dignissim magna.</p>
      </div>
    </div> -->
            <div class="experience-wrapper">
                <div class="company-wrapper clearfix">
                    <div class="experience-title">내가 쓴 댓글</div>
                    <div>
                        <table>
                            <c:if test="${reply.size() > 0}">
                                <c:forEach var="i" begin="0" end="${Math.min(2, reply.size() - 1)}">
                                    <tr>
                                        <td>
                                            <div class="reply-preview">
                                                <div class="reply-preview-boardNo">${reply[i].boardNo}</div>
                                                <div class="reply-preview-replyContent"><a
                                                        href="/board/view/${reply[i].boardNo}">${reply[i].replyContent}</a>
                                                </div>
                                                <div class="reply-preview-replyDate">
                                                    <fmt:formatDate value="${reply[i].replyDate}"
                                                                    pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
                                                </div>
                                            </div>

                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <%--                            <tr>--%>
                            <%--                                <td>글1</td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>글2</td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>글3</td>--%>
                            <%--                            </tr>--%>
                        </table>
                    </div>
                    <!-- NAME OF THE COMPANY YOUWORK WITH  -->
                    <!-- THE TIME YOU WORK WITH THE COMPANY  -->
                </div>
                <a href="/member/reply">전체보기</a>
            </div>
            <!--Skill experience-->
            <div class="section-wrapper clearfix">
                <h3 class="section-title">내가 찜한 영화</h3>
                <div class="img-div">
                    <%--						<a href="/movie/view/{}">--%>
                    <%--							<img class="loved-img" src="${pageContext.request.contextPath}/images/test.jpg">--%>
                    <%--						</a>--%>
                    <%--						<a href="/movie/view/{}">--%>
                    <%--							<img class="loved-img" src="${pageContext.request.contextPath}/images/test.jpg">--%>
                    <%--						</a>--%>
                    <%--						<a href="/movie/view/{}">--%>
                    <%--							<img class="loved-img" src="${pageContext.request.contextPath}/images/test.jpg">--%>
                    <%--						</a>--%>
                </div>
            </div>
            <a href="/movie/myLoved">전체보기</a>
        </div>
    </div>
</section>