<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Quote Start -->
    <div class="container-fluid py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <h1 class="display-5 mb-5">로그인</h1>
            </div>
            <form action="${pageContext.request.contextPath }/login" method="post">
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="bg-light rounded p-4 p-sm-5 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="row g-3">
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="text" class="form-control border-0" id="userId" name="userId">
                                    <label for="userId">아이디</label>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-floating" style="display:flex;">
                                    <input type="password" class="form-control border-0" id="userPw" name="userPw">
                                    <label for="userPw">비밀번호</label>
                                </div>
                            </div>
                            <div>
	            				<button class="btn btn-primary" type="submit">로그인하기</button>
	            				<a id="kakao-login-btn" href="${location }">
								  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222"
								    alt="카카오 로그인 버튼" />
								</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
    <!-- Quote End -->


