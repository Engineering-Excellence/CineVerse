'use strict'

$(() => {
    // 프로필 이미지 파일 업로드

    $("#profileImg").click(() => {
        console.log('clicked')
        let fileInput = $('#uploadProfile')[0];
        fileInput.click()
    })

    $("#uploadProfile").on('change', function () {
        let fileInput = $(this)[0];
        let file = fileInput.files[0];
        let formData = new FormData()
        formData.append('file', file)

        $.ajax({
            url: '/member/upload',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: data => console.log(data),
            error: error => console.error(error)
        })
    })
})
