'use strict'

$(() => {

    // 프로필 이미지 파일 업로드
    $('#profileImg').click(() => {
        console.log('clicked')
        let fileInput = $('#uploadProfile')[0];
        fileInput.click()
    })
    $('#uploadProfile').on('change', function () {
        let fileInput = $(this)[0];
        let file = fileInput.files[0];
        let formData = new FormData()
        formData.append('file', file)

        $.ajax({
            url: '/member/uploadProfile',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: () => location.reload(),
            error: error => console.error(error)
        })
    })

    // 프로필 이미지 초기화
    $('#deleteProfile').click(() => {
        $.ajax({
            url: '/member/deleteProfile',
            type: 'POST',
            success: () => location.reload(),
            error: error => console.error(error)
        })
    })

    //개인정보 변경(이메일, 전화번호)
    $('#changeInfo').click(function (event) {
        console.log($("#email").val() + " " + $("#mobile").val());
        if (!checkValidate($("#email").val(), emailRegex)) {
            console.log("email");
            swal(
                'Error!',
                '이메일 입력양식이 잘못되었습니다.',
                'error'
            );
            return;
        }

        if (!checkValidate($("#mobile").val(), mobileRegex)) {
            console.log("mobile");
            swal(
                'Error!',
                '전화번호 양식입력이 잘못되었습니다.',
                'error'
            )
            return;
        }

        $.ajax({
            url: "/member/update",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                username: $('#username').val(),
                email: $('#email').val(),
                mobile: $('#mobile').val()
            }),
            success: function (data) {
                swal(
                    '성공!',
                    '데이터변경에 성공하였습니다.',
                    'success'
                );
            },
            error: (e) => {
                swal(
                    '실패!',
                    '회원정보 변경에 실패하였습니다.',
                    'error'
                );
            }
        });
    });

    //비밀번호 변경
    $('#changePW').click(function (event) {
        if (!checkValidate($("#newPassword").val(), passwordRegex)) {
            swal(
                'Error!',
                '새 비밀번호 입력양식이 잘못되었습니다.',
                'error'
            );
            return;
        }

        if ($("#newPassword").val() !== $("#confirmPassword").val()) {
            swal(
                'Error!',
                '새 비밀번호가 서로 일치하지 않습니다.',
                'error'
            );
            return;
        }

        $.ajax({
            url: "/member/updatePassword",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                oldPassword: $('#oldPassword').val(),
                newPassword: $('#newPassword').val(),
            }),
            success: function (data) {
                console.log(data);
                if (data) {
                    swal(
                        '성공!',
                        '비밀번호 변경에 성공하였습니다.',
                        'success'
                    );
                } else {
                    swal(
                        '실패!',
                        '현재 사용 중인 비밀번호가 일치하지 않습니다.',
                        'error'
                    );
                }
            },
            error: (e) => {
                swal(
                    '실패!',
                    '비밀번호 변경에 실패하였습니다.',
                    'error'
                );
            }
        })
    });

    //삭제
    $('#deleteMember').click(function (event) {
        console.log($('#deleteInfoMember').val());
        $.ajax({
            url: "/member/delete",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                username: $('#username').val(),
                password: $('#deleteInfoMember').val()
            }),
            success: res => {
                console.log(res)
                if (res) {
                    swal(
                        '성공!',
                        '회원탈퇴가 되었습니다.',
                        'success'
                    );
                } else {
                    swal(
                        '실패!',
                        '회원탈퇴가 되지 않았습니다.',
                        'error'
                    );
                }
            },
            error: (e) => {
                console.error(e);
            }
        });
    });
})