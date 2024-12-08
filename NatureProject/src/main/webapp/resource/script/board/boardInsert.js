/**
 * 
 */

const cPath = document.body.dataset.contextPath;
const boardInsertForm = document.querySelector("#boardInsertForm");
const fileListTag = document.querySelector("#fileList");
function boardInsert(){
	let formData = new FormData(boardInsertForm);
	console.log("작성버튼");
	let files = boardInsertForm.boardFile.files;
	console.log("files",files);
	let boardSj = boardInsertForm.boardSj.value;
	let boardCn = boardInsertForm.boardCn.value;
	console.log("boardSj",boardSj);
	console.log("boardCn",boardCn);
	let url = `${cPath}/board/insert`;
/*	let data = {
		boardSj:boardSj,
		boardCn:boardCn,
		files:files
	}*/
	$.ajax({
		url:url,
		data:formData,
		method:boardInsertForm.method,
		processData:false,
		contentType:false,
		dataType:"json",
		sucess:function(resp){
			console.log("resp",resp);
		}
	})
}

function fileInput(fileInput){
	fileListTag.innerHTML="";
    const fileList = fileInput.files;
	console.log("fileList",fileList);
	for(let i=0;i<fileList.length;i++){
		let name = fileList[i].name;
		console.log("파일이름",name);
		let liTag = document.createElement("li");
		liTag.innerHTML=name;
		fileListTag.appendChild(liTag);
	}
};
function insertCancel(){
	console.log("작성취소버튼");	
}