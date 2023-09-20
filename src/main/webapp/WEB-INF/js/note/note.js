'use strict'
function throttle(callback, delay) {
    let timer
    return event => {
        if (timer) return;
        timer = setTimeout(() => {
            callback(event);
            timer = null;
        }, delay, event)
    }
}
function Trie() {
    this.output = new Set();
    this.fail = null;
    this.next = new Map();
}

Trie.prototype.insert = (self, key, stringIdx, menuIdx) => {
    if (key.length == stringIdx) {
        self.output.add(menuIdx);
    } else {
        let curr = key.charAt(stringIdx);
        self.output.add(menuIdx);
        if (!self.next.has(curr)) self.next.set(curr, new Trie());
        self.next.get(curr).insert(self.next.get(curr), key, stringIdx + 1, menuIdx);
    }
}

Trie.prototype.find = (self, key, stringIdx) => {
    if (key.length == stringIdx) return self.output;
    let curr = key.charAt(stringIdx);
    if (!self.next.has(curr)) return [];
    return self.next.get(curr).find(self.next.get(curr), key, stringIdx + 1);
}

let userList;
let partnerList;
let root = new Trie();

$.ajax({
    url :   "/member/lists",
    type :  "post",
    success : (data) => {
        userList = data;
        userList.forEach((u, idx) => {
            for (let i = 0; i < u.length; i++)
                root.insert(root, u.substring(i), 0, idx);
        })
        showListPeople(userList);
        addDialogPartnerHandler(".search-list");

    }
});

$("#home-tab").click(() => {
    getPartnerList();
});
const getPartnerList = () => {
    $.ajax({
        url :   "/note/partnerList",
        type :  "post",
        success : (data) => {
            partnerList = data;
            let html = "";
            partnerList.forEach((p) => {
                html += `<div class="dialogPartner" name="dialogPartner">`
                html += `<li class="person" data-chat="person1">`
                html += `<span class="name">${p}</span>`
                html += `</li>`
                html += `</div>`
            })
            $(".partner-list").html("").append(html);
            addDialogPartnerHandler(".partner-list");
        }
    });
}
const showListPeople = (list) => {
    let html = "";
    list.forEach((idx) => {
        html += `<div class="dialogPartner" name="dialogPartner">`
        html += `<li class="person" data-chat="person1">`
        html += `<span class="name">${userList[idx]}</span>`
        html += `</li>`
        html += `</div>`
    })
    $(".search-list").html("").append(html);
}

$("#search-input").keyup(throttle(() => {
    let list = root.find(root, $("#search-input").val(), 0);
    showListPeople(list);
    addDialogPartnerHandler(".search-list");
}, 300))

const addDialogPartnerHandler = (selector) => {
    $(`${selector} .dialogPartner`).each((i, e)=>{
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
}

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

getPartnerList();