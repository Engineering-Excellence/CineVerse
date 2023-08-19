<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="../js/member/join.js" defer></script>
<link rel="stylesheet" href="../css/member/join.css"/>

<div class="user-join-main">
    <div class="join-top">
        <h2>SIGN UP</h2>
    </div>
        <div class="join-container" id="join-container">
            <form action="/member/join" method="post">
                <div class="form-container sign-up-container">
                    <div class="join-form">
                        <!-- <h2>Create Account</h2> -->
                        <input class="join-input" name="name" type="text" placeholder="이름" />

                        <p class="join-input-p">생년월일</p>
                        <input class="join-input" type="date" name="birthDate"/>
                        <p class="join-input-gender">성별</p>
                        <label>
                            <label>
                                <input type="radio" name="gender" value="1"/>
                                <span>남</span>
                            </label>
                            <label>
                                <input type="radio" name="gender" value="0" />
                                <span>여</span>
                            </label>
                        </label>
                        <input class="join-input" name="mobile" type="text" placeholder="전화번호" />
                        <input class="join-input" name="email" type="email" placeholder="Email" />
                        <input type="submit" class="join-btn" value="Sign Up"></input>
                    </div>
                </div>
                <div class="form-container sign-in-container">
                    <div class="join-form">
                        <!-- <h2>Sign in</h2> -->
                        <div class="join-id">
                            <input class="join-input-id" name="username" type="text" placeholder="아이디" />
                            <input class="join-input-btn" type="button" value="중복확인" />
                        </div>
                        <input class="join-input" id="password" name="password" type="password" placeholder="비밀번호" />
                        <input class="join-input" id="password-confirm" type="password" placeholder="비밀번호 확인" />

                        <input type="submit" class="join-btn" value="Sign Up"></input>
                    </div>
                </div>
            </form>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome!</h1>
                        <p>정보를 입력해주세요</p>
                        <button class="ghost join-btn" id="signIn">이전으로</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Welcome!</h1>
                        <p>정보를 입력해주세요</p>
                        <button class="ghost join-btn" id="signUp">다음으로</button>
                    </div>
                </div>
            </div>
        </div>
</div>



