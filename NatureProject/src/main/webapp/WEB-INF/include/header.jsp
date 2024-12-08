<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Navbar Start -->
	
    <nav class="navbar navbar-expand-lg bg-white navbar-light sticky-top p-0">
    
    
        <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h1 class="m-0">Gardener</h1>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto p-4 p-lg-0">
            <security:authentication property="principal" var="principal"/>
            	<security:authorize access="isAuthenticated()">
            		<p>${principal} </p>
            		<p>${principal.userId} 님 환영합니다</p>
            	</security:authorize>
                <a href="/" class="nav-item nav-link active">Home</a>
                <a href="/board" class="nav-item nav-link">Community</a>
                <a href="service.html" class="nav-item nav-link">Product</a>
                <a href="contact.html" class="nav-item nav-link">Map</a>
            </div>
			<security:authorize access="!isAuthenticated()">
            	<a href="/default_login" class="btn btn-primary py-4 px-lg-4 rounded-0 d-none d-lg-block">로그인<i class="fa fa-arrow-right ms-3"></i></a>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
            	<a href="/logout" class="btn btn-primary py-4 px-lg-4 rounded-0 d-none d-lg-block">로그아웃<i class="fa fa-arrow-right ms-3"></i></a>
			</security:authorize>
			
            <a href="/signUp" class="btn btn-primary py-4 px-lg-4 rounded-0 d-none d-lg-block">회원가입<i class="fa fa-arrow-right ms-3"></i></a>
        </div>
    </nav>
 
    <!-- Spinner End -->
    <!-- Navbar End -->