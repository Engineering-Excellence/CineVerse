var idDupCheck = false;

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
                username: $("#username").val()
            }),
            contentType: "application/json",
            success: (res) => {
                console.log(res);
                if (!res) {
                    $("#username").attr("readonly", "readonly");
                    $("#check-dup").toggleClass("checked");
                    idDupCheck = true;
                    alert("사용 가능한 아이디입니다")
                } else {
                    alert("아이디가 중복됩니다");
                    return;
                }
            },
        });
    }
    else {
        $("#username").removeAttr("readonly");
        $("#check-dup").toggleClass("checked");
        $("#username").focus();
        idDupCheck = false;
    }
});

$("#next").click(() => {
    if (!idDupCheck) {
        alert("아이디 중복 확인을 해주세요");
        return;
    }
    if (!checkValidate($("#password").val(), passwordRegex)) {
        alert("비밀번호가 조건에 맞지 않습니다\n8자 이상, 대소문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상이 포함되어야 합니다");
        return;
    }
    if ($("#password").val() !== $("#password-confirm").val()) {
        alert("비밀번호가 일치하지 않습니다");
        return;
    }
    $("#join-container").addClass("right-panel-active");
});

$("#prev").click(() => {
    $("#join-container").removeClass("right-panel-active");
});



$("#join-form").submit(() => {
    if ($("#name").val().length == 0) {
        alert("이름을 입력해주세요");
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
    if ($("#mobile").val().length == 0) {
        alert("전화번호를 입력해주세요");
        return false;
    }
    if (!checkValidate($("#mobile").val(), mobileRegex)) {
        alert("올바른 전화번호가 아닙니다");
        return false;
    }
    if ($("#email").val().length == 0) {
        alert("올바른 이메일이 아닙니다")
        return false;
    }
    return true;
});