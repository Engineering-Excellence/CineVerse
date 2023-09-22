"use strict"

 $(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    const username = urlParams.get('username');

    $("#code").val(code);
    $("#username").val(username);

     $("#reset-pwd").submit(() => {
  if (!checkValidate($("#password").val(), passwordRegex)) {
              swal(
                  '실패!',
                  '비밀번호가 조건에 맞지 않습니다\n8자 이상, 대소문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상이 포함되어야 합니다.',
                  'error'
              )
              return false
          }
          if ($("#password").val() !== $("#password-confirm").val()) {
              swal(
                  '실패!',
                  '비밀번호가 일치하지 않습니다.',
                  'error'
              )
              return false
          }
  
          return true
      })
 })

    