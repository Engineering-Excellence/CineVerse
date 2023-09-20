'use strict'

$(() => {
    const $body = $("body")
    $body.addClass("login")
})

let password = document.getElementById('login-pwd');
let togglePassword = document.getElementById('toggle-pwd');
('toggle');

function showHide() {
    if (password.type === 'password') {
        password.setAttribute('type', 'text');
        togglePassword.classList.add('hide');
    } else {
        password.setAttribute('type', 'password');
        togglePassword.classList.remove('hide');
    }
}

let wrapper = document.querySelector('.wrapper'),
    signUpLink = document.querySelector('.link .signup-link'),
    signInLink = document.querySelector('.link .signin-link');

signUpLink.addEventListener('click', () => {
    wrapper.classList.add('animated-signin');
    wrapper.classList.remove('animated-signup');
});

signInLink.addEventListener('click', () => {
    wrapper.classList.add('animated-signup');
    wrapper.classList.remove('animated-signin');
});

// 이메일 인증번호
/* var $checkEmail = $("#checkEmail");

$checkEmail.click(function() {
   $.ajax({
      type : "POST",
      url : "login/mailConfirm",
      data : {
         "email" : $memail.val()
      },
      success : function(data){
         alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
         console.log("data : "+data);
         chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);
      }
   })
})

	// 이메일 인증번호 체크 함수
	function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
		$memailconfirm.on("keyup", function(){
			if (data != $memailconfirm.val()) { //
				emconfirmchk = false;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
				$("#emconfirmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
				"font-size" : "10px"
				})
				//console.log("중복아이디");
			} else { // 아니면 중복아님
				emconfirmchk = true;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

				$("#emconfirmchk").css({
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			}
		})
	} */