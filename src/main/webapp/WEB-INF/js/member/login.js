let password = document.getElementById('login-pwd');
let togglePassword = document.getElementById('toggle-pwd');
('toggle');

function showHide(){
    if(password.type === 'password') {
        password.setAttribute('type', 'text');
        togglePassword.classList.add('hide');
    } 
    else{
        password.setAttribute('type', 'password');
        togglePassword.classList.remove('hide');
    }
}