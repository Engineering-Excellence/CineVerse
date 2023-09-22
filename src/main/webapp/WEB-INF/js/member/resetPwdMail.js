"use strict"

//이메일 인증번호 요청 버튼 클릭 이벤트 처리
    $("#resetEmail").click(function () {
        let memail = $("#memail").val()
        let username = $("#username").val();
        if ($("#memail").val().length == 0 || !checkValidate($("#memail").val(), emailRegex)) {
            swal(
                '실패!',
                '올바른 이메일이 아닙니다.',
                'error'
            )
            return
        }

        $.ajax({
            type: "POST",
            url: "/mail/resetpwdmail",
            data: {
                email: memail,
                username: username,
            },
            success: function (data) {
                if (data) {
                    swal(
                        '성공!',
                        '해당 이메일로 발송이 완료되었습니다. 확인 부탁드립니다.',
                        'success'
                    )
                }
                else {
                    swal(
                        '실패!',
                        '이메일이 일치하지 않습니다.',
                        'error'
                    )
                }
            },
            error: function () {
                swal(
                    '실패!',
                    '이메일 요청 중 오류가 발생했습니다.',
                    'error'
                )
            },
        })
    })