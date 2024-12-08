const cPath = document.body.dataset.contextPath;

const userIdTag = document.querySelector("#userId");
const emailTag = document.querySelector("#userEmail");
const userPwTag = document.querySelector("#userPw");
const checkUserPwTag = document.querySelector("#checkUserPw");
const userNameTag = document.querySelector("#userName");
const userBirTag = document.querySelector("#userBir");
let sendMailCodeValue="";

let idCheckResult=false;
// 아이디 중복확인
function idCheck(){
	if(!userIdTag.value){
		alert('아이디를 입력해주세요');
	}else{
		let userId = userIdTag.value;
		$.ajax({
			url:`${cPath}/findId/${userId}`,
			dataType:'json',
			success:function(res){
				console.log("res",res);
				if(res.ok){
					idCheckResult=true;
					alert(res.ok);
				}else{
					alert(res.no);
				}
			}
		})
	}
}

// 이메일 유효성 검사
function emailValidate(email){
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if(!email_regex.test(email)){ 
		return false; 
	}else{
		return true;
	}
}

//이메일 인증코드 전송
function sendMailCode(){
	let email = emailTag.value;

	if(!email){
		alert("이메일을 입력해주세요");
	}else{
		if(emailValidate(email)){
			let url=`${cPath}/emailCode`;
			$.ajax({
				url:url,
				data:{email:email},
				method:'post',
				dataType:'json',
				success:function(res){
					if(res.mailCode){
						alert("메일이 전송되었습니다.");
						sendMailCodeValue=res.mailCode;
						let isInputTag = document.querySelector("#mailCode");
						if(!isInputTag){
							let inputTag = document.createElement("input");
							inputTag.setAttribute("id","mailCode");
							inputTag.setAttribute("class","form-control border-0");
							inputTag.setAttribute("type","text");
							inputTag.setAttribute("placeholder","인증코드");
							inputTag.setAttribute("onkeyup","mailCodeCheck(this)");
							emailTag.closest(".col-sm-12").appendChild(inputTag);
							let spanTag =document.createElement("span");
							spanTag.setAttribute("id","checkMailCode");
							inputTag.closest(".col-sm-12").appendChild(spanTag);
						}
					}
				}
			})
		}else{
			alert("유효하지 않은 이메일입니다.");
		}
	}
}
// 메일인증코드 작성할때 
function mailCodeCheck(mailCodeTag){
	console.log("sendMailCodeValue",sendMailCodeValue);
	if(mailCodeTag.value == sendMailCodeValue){
		checkMailCode.innerHTML="인증코드가 일치합니다";
	}else{
		checkMailCode.innerHTML="인증코드가 일치하지않습니다.";
	}
}

// 회원가입
function signUp(){
	let mailCodeTag = document.querySelector("#mailCode");
	if(!userIdTag.value){
		alert("아이디를 입력하세요")
	}else if(!idCheckResult){
		alert("아이디중복확인을 해주세요")
	}else if(!emailTag.value){
		alert("이메일을 입력하세요")
	}else if(!mailCodeTag){
		alert("인증코드를 발급받아주세요");
	}else if(mailCodeTag.value != sendMailCodeValue){
		alert("인증번호가 일치하지 않음");
	}else if(userPwTag.value.length<8){
		alert("비밀번호 8자리이상");
	}else if(userPwTag.value!=checkUserPwTag.value){
		alert("비밀번호다름");
	}else if(!userNameTag.value){
		alert("이름을 입력해주세요");
	}else if(!userBirTag.value){
		alert("생일을 입력해주세요");
	}else{
		console.log("email:",emailTag.value);
		let url= `${cPath}/signUp`;
		let data = {
			userId:userIdTag.value,
			userEmail:emailTag.value,
			userPw:userPwTag.value,
			userName:userNameTag.value,
			userBir:userBirTag.value
		}
		$.ajax({
			url:url,
			method:"post",
			data:JSON.stringify(data),
			dataType:"json",
			contentType:'application/json',
			success:function(res){
				if(res.ok){
					alert(res.ok);
					location.href=`${cPath}/`
				}else if(res.no){
					alert(res.no)
				}else{
					console.log("에러들 : ",res.error);
					alert(res.error);
				}
			}
			
		})
	}
}
