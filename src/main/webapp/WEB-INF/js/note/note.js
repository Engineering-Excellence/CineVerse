$(".dialogPartner").each((i, e)=>{ 
    $(e).click((event) => {
        const partnerName = $(e).find("li").find("span").text();
        // console.log(  $(e).find("li").find("span").text());
        $.ajax({            
            url :   "/note/dialog",
            type :  "post",
            contentType : "application/json",
            data : JSON.stringify({
                id: partnerName// 상대이름    
            }),
            success : (data) => {
                chatData(data);
                $("#partnerName").text(partnerName);
                $("#noteListener").val(partnerName);
                $(".chat-list").animate({
                    scrollTop: $(".chat-list").height()
                }, 350);
            }
        });
    });
})

const sendNote = ()=>{ //쪽지전송 함수
    $.ajax({     
        url :   "/note/insertNote",
        type :  "post",
        contentType : "application/json",
        data : JSON.stringify({
            noteWriter : $("#myAccount").val(),
            noteListener : $("#noteListener").val(),
            content : $("#content").val()
        }),
        success : (data) => {
            if(data){
                let html = "";
                html += `<div class="bubble me">`
                html += $("#content").val()
                html += `</div>`
                $(".chat-list").append(html);
            }else{
                alert("fail");
            }
            $("#content").val("");
        }
    });
}

$("#content").keypress((event) => {
    if(event.keyCode == 13 && $("#content").val().length > 0) sendNote();
});

$(".send").click((event) => {
    if($("#content").val().length > 0)  sendNote();
});


function chatData(data){
    let html = "";
    console.log(data);
    // console.log($("#myAccount").val());
    data.forEach(element => {
        if(element.noteWriter == $("#myAccount").val()){
            html += `<div class="bubble me">`
            html += `${element.content }`
            html += `</div>`
        }else{
            html += `<div class="bubble you">`
            html += `${element.content }`
            html += `</div>`
        }
    });
    $(".chat-list").html("").append(html);
}


