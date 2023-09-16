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
})
