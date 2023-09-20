"use strict"

let idDupCheck = false
let mailCheck = false
let code = ""
$(() => {

    $("#check-dup").click((e) => {
        e.preventDefault()
        if ($("#username").val().length === 0) {
            swal(
                '실패!',
                '아이디를 입력해주세요.',
                'error'
            )
            return
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
                    console.log(res)
                    if (!res) {
                        $("#username").attr("readonly", "readonly")
                        $("#check-dup").toggleClass("checked")
                        idDupCheck = true
                        swal(
                            '성공!',
                            '사용 가능한 아이디입니다.',
                            'success'
                        )
                    } else {
                        swal(
                            '실패!',
                            '아이디가 중복됩니다.',
                            'error'
                        )
                        return
                    }
                },
            })
        } else {
            $("#username").removeAttr("readonly")
            $("#check-dup").toggleClass("checked")
            $("#username").focus()
            idDupCheck = false
        }
    })

    $("#join-form").submit(() => {
        if (!idDupCheck) {
            swal(
                '실패!',
                '아이디 중복 확인을 해주세요.',
                'error'
            )
            return false
        }
        if (!mailCheck) {
            swal(
                '실패!',
                '이메일 인증을 해주세요.',
                'error'
            )
            return false
        }
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
        if ($("#mobile").val().length === 0) {
            swal(
                '실패!',
                '전화번호를 입력해주세요.',
                'error'
            )
            return false
        }
        if (!checkValidate($("#mobile").val(), mobileRegex)) {
            swal(
                '실패!',
                '올바른 전화번호가 아닙니다.',
                'error'
            )
            return false
        }
        if ($("#birth-date").val().length === 0) {
            swal(
                '실패!',
                '생년월일을 입력해주세요.',
                'error'
            )
            return false
        }
        if ($("input[name='gender']:checked").val() == undefined) {
            swal(
                '실패!',
                '성별을 선택해주세요.',
                'error'
            )
            return false
        }
        return true
    })

    //이메일 인증번호 요청 버튼 클릭 이벤트 처리
    $("#checkEmail").click(function () {
        let memail = $("#memail").val()
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
            url: "/mail/confirm",
            /*    dataType:"json", */
            data: {
                email: memail,
            },
            success: function (data) {
                swal(
                    '성공!',
                    '해당 이메일로 인증번호 발송이 완료되었습니다. 확인 부탁드립니다.',
                    'success'
                )
                code = data
            },
            error: function () {
                swal(
                    '실패!',
                    '이메일 인증번호 요청 중 오류가 발생했습니다.',
                    'error'
                )
            },
        })
    })

    //이메일 인증번호 확인 함수
    document.getElementById("confirmEmail").addEventListener('click', chkEmailConfirm)

    function chkEmailConfirm(e) {
        e.preventDefault()
        let memailconfirm = $("#memailconfirm").val()

        if (code !== memailconfirm) {
            swal(
                '실패!',
                '인증번호가 불일치 합니다. 다시 확인해주세요.',
                'error'
            )
        } else if ("" === memailconfirm) {
            swal(
                '실패!',
                '인증번호가 불일치 합니다. 다시 확인해주세요.',
                'error'
            )
        } else {
            swal(
                '성공!',
                '인증번호가 일치합니다.',
                'success'
            )
            mailCheck = true
            $("#memail").attr("readonly", "readonly")
        }
    }

    $('#join-form').submit(e => {

        e.preventDefault()

        const memberData = {
            username: $('#username').val(),
            password: $('#password').val(),
            email: $('#memail').val(),
            mobile: $('#mobile').val(),
            birthDate: $('#birth-date').val(),
            gender: $("input[name='gender']:checked").val()
        }

        $.ajax({
            cache: false,
            url: '/member/join',
            type: 'POST',
            data: JSON.stringify(memberData),
            contentType: 'application/json; charset=utf-8',
            success: (res) => {
                if (res) {
                    swal(
                        '성공!',
                        '회원가입에 성공했습니다.',
                        'success'
                    ).then(() => location.href = '/login')
                } else {
                    swal(
                        '실패',
                        '회원가입에 실패했습니다.',
                        'error'
                    )
                }
            },
            error: res => console.error(res),
            complete: () => console.log(memberData)
        })
    })
})

// $("#authCode").on("focusout", function() {
//     const inputCode = $("#authCode").val() //인증번호 입력 칸에 작성한 내용 가져오기

//     if(Number(inputCode) === code){
//         alert("인증번호가 일치합니다.")
//     }else{
//         alert('인증번호가 불일치 합니다. 다시 확인해주세요!')
//     }
// })
