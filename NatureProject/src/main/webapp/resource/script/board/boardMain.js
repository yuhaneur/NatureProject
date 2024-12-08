/**
 * 
 */
const cPath = document.body.dataset.contextPath;
const insertBoardBtn = document.querySelector("#insertBoard");
insertBoardBtn.addEventListener("click",function(){
	location.href=`${cPath}/board/insert`;
})