$(document).ready(() => {
  $("#updateMemberInfo").click(() => {
    $(".contact-info").html("");
    let html = "";
    html += `<form action="/member/update" method="post">`
    html += `<div>`
    html += `<input type="hidden" value="${username}" id="username" name="username"/>`
    html += `<ul class="list-titles">`
    html += `<li>Email</li>`
    html += `<li>Mobile</li>`
    html += `<li>Gender</li>`
    html += `<li>Birth</li>`
    html += `<li>Join-date</li>`
    html += `</ul>`
    html += `<ul class="list-content ">`
    html += `<li><input type="text" value="${email}" id="email" name="email"/></li>`
    html += `<li><input type="text" value="${mobile}" id="mobile" name="mobile"></li>`
    html += `<li>`
    if (gender) html += `MALE`
    else html += `FEMALE`
    html += `</li>`
    html += `<li>${birthDate}</li>`
    html += `<li>${regDate}</li>`
    html += `</ul>`
    html += `<input type="submit" id="changeMemberInfo" value="개인 정보 수정" name = "updateMemberInfo">`
    html += `</div>`
    html += `</form>`
    $(".contact-info").append(html);
  });

  $("#updatePassword").click(() => {
    $(".contact-info").html("");
    let html = "";
    html += `<form action="/member/updatePassword" method="post">`
    html += `<div>`
    html += `<ul class="list-titles">`
    html += `<li>현재 비밀번호</li>`
    html += `<li>새 비밀번호</li>`
    html += `<li>비밀번호 확인</li>`
    html += `</ul>`
    html += `<ul class="list-content">`
    html += `<li><input type="password" name="oldPassword" id="oldPassword" placeholder="사용중인 비밀번호를 입력해주세요."></li>`
    html += `<li><input type="password" name="newPassword" id="newpassword" placeholder="사용할 비밀번호를 입력해주세요."></li>`
    html += `<li><input type="password" name="confirmPassword" id="confirmPassword"	placeholder="한번 더 입력해주세요."></li>`
    html += `</ul>`
    html += `<input type="submit" value="비밀번호 수정" id="changePassword" name="changePassword">`
    html += `</div>`
    html += `</form>`
    $(".contact-info").append(html);
  });

  $("#deleteMember").click(() => {
      $(".contact-info").html("");
      let html ="";
      html += `<form action="/member/delete" method="post">`
      html += `<div>`
      html += `<ul class="list-titles">`
      html += `<li>사용중인 비밀번호</li>`
      html += `</ul>`
      html += `<ul class="list-content">`
      html += `<li><input type="password" name="password" id="deleteInfoMember"/></li>`
      html += `</ul>`
      html += `</div>`
      html += `<input type="submit" value="회원탈퇴" id="deleteMemberInfo">`
      html += `</form>`
      html += `사용중인 비밀번호를 입력하시면 회원 탈퇴가 진행됩니다. <br/>탈퇴시 복구가 불가능합니다.`
      $(".contact-info").append(html);
  });
  $("noteBox").click(()=>{
    $(".contact-info").html("");
    let html ="";
  })
});