<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/movieList.css"> --%>
<%-- <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/board/list.css"> --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/board/view.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.9/sweetalert2.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.9/sweetalert2.min.js"></script>

<sec:authorize access="isAuthenticated()">
	<sec:authentication var="user" property="principal" />
</sec:authorize>
<section>
	<div class="board-title">
		<a href="/board/list">게시판 목록 ></a>
		<p class="title-name">${data.boardTitle}</p>
		<div class="board-user">
			<div class="user-info">
				<img src="${pageContext.request.contextPath}/images/user.png">
				<div class="user-info-name">
					<p class="user-name">${data.username}</p>
					<p>
						<fmt:formatDate value="${data.boardDate}"
							pattern="yyyy.MM.dd hh:mm" type="date" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;조회수 ${data.boardView}
					</p>
				</div>
			</div>

			<div class="btn-list">
				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<c:if test="${user.username eq data.username}">
							<div class="write-btn">
								<button class="learn-more update-btn"
									onClick="location.href='/board/update/${data.boardNo }'">
									<span class="button-text btn-update"><img
										src="${pageContext.request.contextPath}/images/update.png">수정</span>
								</button>
							</div>
						</c:if>


						<div class="write-btn text-center">
							<button class="learn-more delete-btn" id="deleteButton"
								type="submit">
								<span class="button-text"><img
									src="${pageContext.request.contextPath}/images/delete.png">
									삭제</span>
							</button>
						</div>

					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_USER')">
						<c:if test="${user.username eq data.username}">
							<div class="write-btn">
								<button class="learn-more  update-btn"
									onClick="location.href='/board/update/${data.boardNo }'">
									<span class="button-text"><img
										src="${pageContext.request.contextPath}/images/update.png">수정</span>
								</button>
							</div>

							<%--    <form action="/board/delete/${data.boardNo }" method="post"> --%>
							<div class="write-btn text-center">
								<button class="learn-more delete-btn" id="deleteButton"
									type="submit">
									<span class="button-text"><img
										src="${pageContext.request.contextPath}/images/delete.png">
										삭제</span>
								</button>
							</div>
							<!--     </form> -->
						</c:if>
					</sec:authorize>
				</sec:authorize>
			</div>

		</div>
		<div class="board-content">${data.boardContent}</div>
		<div class="board-reply">
			<p class="reply">댓글</p>
			<c:forEach var="r" items="${reply}">
				<div class="line">
					<div class="reply-title">
						<img class="reply-user"
							src="${pageContext.request.contextPath}/images/replyuser.png">
						<div class="reply-name">
							<p>${ r.username }&nbsp;</p>
							<p>${ r.replyContent }</p>
							<p class="reply-date">
								<fmt:formatDate value="${ r.replyDate }"
									pattern="yyyy.MM.dd hh:mm" type="date" />
							</p>
						</div>
					</div>
					<div class="reply-delete">
						<sec:authorize access="isAuthenticated()">
							<c:if test="${user.username eq r.username}">
								<%-- <form action="/reply/delete/${r.replyNo}" method="post"> 
								<div class="reply-delete">
									<input type="text" name="boardNo" hidden="hidden"
										value="${data.boardNo}">
									<%-- <input type="text" name="replyNo" hidden="hidden" value="${r.replyNo}"> --%>
									<input type="submit" class="deleteBtn" id="${r.replyNo}" value="삭제">
								</div>
								<!-- </form> -->
							</c:if>
						</sec:authorize>
					</div>




				</div>
			</c:forEach>
		<sec:authorize access="isAuthenticated()">
			<form action="/reply/insert" method="post" class="reply-form">


					<input type="text" value="${data.boardNo }" name="boardNo" hidden>
					<textarea name="replyContent" class="reply-content-input" placeholder="댓글을 작성해주세요."></textarea>


				<div class="reply-submit-btn">
					 <button type="submit"><img src="${pageContext.request.contextPath}/images/check.png">등록</button>
				</div>
			</form>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<textarea name="replyContent" class="reply-content-input" placeholder="로그인 후 이용 가능합니다." readonly></textarea>
		</sec:authorize>
		</div>



		<%-- 
	 <div class="btn-list">
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <c:if test="${user.username eq data.username}">
                    <div class="write-btn">
                        <button class="learn-more"
                                onClick="location.href='/board/update/${data.boardNo }'">
                            <span class="circle" aria-hidden="true"> <span
                                    class="icon arrow"></span>
                            </span> <span class="button-text">수정</span>
                        </button>
                    </div>
                </c:if>

                <form action="/board/delete/${data.boardNo }" method="post">
                    <div class="write-btn">
                        <button class="learn-more" type="submit">
					<span class="circle" aria-hidden="true"> <span
                            class="icon arrow"></span>
					</span> <span class="button-text">삭제</span>
                        </button>
                    </div>
                </form>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <c:if test="${user.username eq data.username}">
                    <div class="write-btn">
                        <button class="learn-more"
                                onClick="location.href='/board/update/${data.boardNo }'">
                        <span class="circle" aria-hidden="true"> <span
                                class="icon arrow"></span>
                        </span> <span class="button-text">수정</span>
                        </button>
                    </div>

                    <form action="/board/delete/${data.boardNo }" method="post">
                        <div class="write-btn">
                            <button class="learn-more" type="submit">
					<span class="circle" aria-hidden="true"> <span
                            class="icon arrow"></span>
					</span> <span class="button-text">삭제</span>
                            </button>
                        </div>
                    </form>
                </c:if>
            </sec:authorize>
        </sec:authorize>
        <div class="write-btn">
            <button class="learn-more" onClick="location.href='/board/list'">
				<span class="circle" aria-hidden="true"> <span
                        class="icon arrow"></span>
				</span> <span class="button-text">목록</span>
            </button>
        </div>
    </div>
    <sec:authorize access="isAuthenticated()">
        <p class="reply">댓글</p>
        <div class="reply-div">

            <form action="/reply/insert" method="post" class="reply-form">
                <div>

                    <input type="text" value="${data.boardNo }" name="boardNo" hidden>
                    <textarea name="replyContent" class="reply-content-input" placeholder="댓글을 작성해주세요."></textarea>

                </div>
                <div class="reply-submit-btn">
                    <input type="submit" class="board-view-submit" value="등록">
                </div>
            </form>
        </div>
    </sec:authorize>

    <c:forEach var="r" items="${reply}">
        <div class="line">
            <div class="reply-title">
                <div class="reply-name">
                    <p>${ r.username }&nbsp;</p>
                    <p class="reply-date">
                        <fmt:formatDate value="${ r.replyDate }" pattern="yyyy.MM.dd hh:mm" type="date"/>
                    </p>
                </div>
                <sec:authorize access="isAuthenticated()">
                    <c:if test="${user.username eq r.username}">
                        <form action="/reply/delete/${r.replyNo}" method="post">
                            <div class="reply-delete">
                                <input type="text" name="boardNo" hidden="hidden" value="${data.boardNo}">
                                <input type="submit" id="deleteBtn" value="삭제">
                            </div>
                        </form>
                    </c:if>
                </sec:authorize>

            </div>
            <p>${ r.replyContent }</p>


        </div>
    </c:forEach> --%>
</section>

<script>
	var boardNo = "${data.boardNo}";

	$('#deleteButton').click(function() {
		swal({
			title : 'Are you sure?',
			text : "정말로 삭제하시겠습니까?",
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Yes, delete it!'
		}).then(function() {
			$.ajax({
				type : "POST",
				url : "/board/delete/" + boardNo,
				data : {
					boardNo : boardNo,
				},
				success : function() {
					swal({
						title : 'Deleted!',
						text : '삭제가 완료되었습니다.',
						type : 'success'
					}).then(function() {
						window.location.href = "/board/list";
					});
				},
				error : function() {
					alert("오류가 발생하였습니다.");
				}
			});
		});
	});

	var replyNo = "${r.replyNo}";

	$('.deleteBtn').each((idX, e) => {
		$(e).click(() => {
			swal({
				title : 'Are you sure?',
				text : "정말로 삭제하시겠습니까?",
				type : 'warning',
				showCancelButton : true,
				confirmButtonColor : '#3085d6',
				cancelButtonColor : '#d33',
				confirmButtonText : 'Yes, delete it!'
			}).then(function() {
				$.ajax({
					type : "POST",
					url : "/reply/delete/" + $(e).attr("id"),
					success : function() {
						swal({
							title : 'Deleted!',
							text : '삭제가 완료되었습니다.',
							type : 'success'
						}).then(function() {
							window.location.reload();
						});
					},
					error : function() {
						alert("오류가 발생하였습니다.");
					}
				});
			});
		});
	});
</script>
