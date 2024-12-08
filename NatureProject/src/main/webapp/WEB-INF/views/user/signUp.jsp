<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Quote Start -->
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">회원가입</h1>
            </div>
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3">
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="text" class="form-control border-0" id="userId" name="userId">
                                    <label for="userId">아이디</label>
                                	<button type="button" class="btn btn-primary col-sm-2" onclick="idCheck()">중복확인</button>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="email" class="form-control border-0" id="userEmail" name="userEmail">
                                    <label for="userEmail">이메일</label>
                                    
                                	<button type="button" class="btn btn-primary col-sm-2" onclick="sendMailCode()">인증발송</button>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="password" class="form-control border-0" id="userPw" name="userPw">
                                    <label for="userPw">비밀번호</label>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="password" class="form-control border-0" id="checkUserPw" >
                                    <label for="checkUserPw">비밀번호 확인</label>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-floating" style="display:flex;">
                                    <input type="text" class="form-control border-0" id="userName" name="userName">
                                    <label for="userName">이름</label>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-floating" style="display:flex;">
                                    <input type="date" class="form-control border-0" id="userBir" name="userBir" onkeydown="return false;">
                                    <label for="userBir">생일</label>
                                </div>
                            </div>
            				<button class="btn btn-primary" onclick="signUp()">회원가입하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Quote End -->
    <script src="${pageContext.request.contextPath }/resource/script/user/signUp.js"></script>
    