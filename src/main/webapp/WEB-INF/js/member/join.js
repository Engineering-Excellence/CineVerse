"use strict";

var idDupCheck = false;
var mailCheck = false;
var code = "";

$("#check-dup").click((e) => {
  e.preventDefault();
  if ($("#username").val().length == 0) {
    alert("아이디를 입력해주세요");
    return;
  }

  if (!idDupCheck) {
    $.ajax({
      url: "/member/check",
      type: "post",
      data: JSON.stringify({
        username: $("#username").val(),
      }),
      contentType: "application/json",
      success: (res) => {
        console.log(res);
        if (!res) {
          $("#username").attr("readonly", "readonly");
          $("#check-dup").toggleClass("checked");
          idDupCheck = true;
          alert("사용 가능한 아이디입니다");
        } else {
          alert("아이디가 중복됩니다");
          return;
        }
      },
    });
  } else {
    $("#username").removeAttr("readonly");
    $("#check-dup").toggleClass("checked");
    $("#username").focus();
    idDupCheck = false;
  }
});

$("#join-form").submit(() => {
  if (!idDupCheck) {
    alert("아이디 중복 확인을 해주세요");
    return false;
  }
  if (!mailCheck) {
    alert("이메일 인증을 해주세요");
    return false;
  }
  if (!checkValidate($("#password").val(), passwordRegex)) {
    alert("비밀번호가 조건에 맞지 않습니다\n8자 이상, 대소문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상이 포함되어야 합니다");
    return false;
  }
  if ($("#password").val() !== $("#password-confirm").val()) {
    alert("비밀번호가 일치하지 않습니다");
    return false;
  }
  if ($("#mobile").val().length == 0) {
    alert("전화번호를 입력해주세요");
    return false;
  }
  if (!checkValidate($("#mobile").val(), mobileRegex)) {
    alert("올바른 전화번호가 아닙니다");
    return false;
  }
  if ($("#birth-date").val().length == 0) {
    alert("생년월일을 입력해주세요");
    return false;
  }
  if ($("input[name='gender']:checked").val() == undefined) {
    alert("성별을 선택해주세요");
    return false;
  }
  return true;
});

//이메일 인증번호 요청 버튼 클릭 이벤트 처리
$("#checkEmail").click(function () {
  var memail = $("#memail").val();
  if ($("#memail").val().length == 0 || !checkValidate($("#memail").val(), emailRegex)) {
    alert("올바른 이메일이 아닙니다");
    return;
  }

  $.ajax({
    type: "POST",
    url: "/mail/confirm",
    /*    dataType:"json", */
    data: {
      email: memail,
    },
    success: function (data) {
      alert("해당 이메일로 인증번호 발송이 완료되었습니다. 확인 부탁드립니다.");
      code = data;
    },
    error: function () {
      alert("이메일 인증번호 요청 중 오류가 발생했습니다.");
    },
  });
});


//이메일 인증번호 확인 함수

 document.getElementById("confirmEmail").addEventListener('click',chkEmailConfirm);

function chkEmailConfirm(e) {
    e.preventDefault();
  let memailconfirm = $("#memailconfirm").val();

  if (code !== memailconfirm) {
    alert("인증번호가 불일치 합니다. 다시 확인해주세요!");
  } 
  else if("" === memailconfirm){
  	alert("인증번호가 불일치 합니다. 다시 확인해주세요!");
  }
  else {
    alert("인증번호가 일치합니다.");
    mailCheck = true;
    $("#memail").attr("readonly", "readonly");
    
  }
}


// $("#authCode").on("focusout", function() {
//     const inputCode = $("#authCode").val(); //인증번호 입력 칸에 작성한 내용 가져오기

//     if(Number(inputCode) === code){
//         alert("인증번호가 일치합니다.");
//     }else{
//         alert('인증번호가 불일치 합니다. 다시 확인해주세요!');
//     }
// });
