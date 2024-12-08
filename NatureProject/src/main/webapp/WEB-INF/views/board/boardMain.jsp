<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Projects Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto wow fadeInUp" data-wow-delay="0.1s" style="max-width: 500px;">
                <p class="fs-5 fw-bold text-primary">커뮤니티 게시판</p>
            </div>
            <button id="insertBoard">작성하기</button>
            <div class="row wow fadeInUp" data-wow-delay="0.3s">
                <div class="col-12 text-center">
                    <ul class="list-inline rounded mb-5" id="portfolio-flters">
                        <li class="mx-2" >최신순</li>
                        <li class="mx-2" >조회수</li>
                    </ul>
                	<input type="text" placeholder="제목">
                	<button>검색</button>
                </div>
            </div>
            <c:forEach items="${boardList }" var="board">
            <div>
            	<p>제목 : ${board.boardSj}</p>
            	<p>조회수 : ${board.boardViews}</p>
            	<p>작성일 : ${board.boardWriteDate}</p>
            </div>
            </c:forEach>
        </div>
    </div>
    <!-- Projects End -->
    
    <script src="${pageContext.request.contextPath }/resource/script/board/boardMain.js"></script>