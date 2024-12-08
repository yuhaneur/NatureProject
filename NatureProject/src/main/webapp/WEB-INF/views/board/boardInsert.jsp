<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<form id="boardInsertForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath }/board/boardInsert" class="row g-3" style="width:70%; margin-left:10%;">
		<input type="text" name="boardSj" placeholder="제목">
		<textarea rows="10" cols="5" name="boardCn" placeholder="내용"></textarea>
		<input type="file" onchange="fileInput(this)" name="boardFile" multiple="multiple" accept="image/gif,image/jpeg,image/png">
		<ul id="fileList"></ul>
		<div>
			<input type="button" onclick="boardInsert()" value="작성하기">
			<input type="button" onclick="insertCancel()" value="취소">
		</div>
	</form>
<script src="${pageContext.request.contextPath }/resource/script/board/boardInsert.js"></script>
