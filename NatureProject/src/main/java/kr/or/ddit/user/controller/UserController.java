package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.user.service.UserService;
import kr.or.ddit.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service=service;
	}
	
	@Value("#{appInfo['kakao.client.id']}")
	private String restApiKey;
	
	@Value("#{appInfo['kakao.redirect.url']}")
	private String redirectUrl;
	
	
	@GetMapping("/kakao_login")
	public String getKakaoCode(@RequestParam("code") String code) throws IOException {
		log.info("code : {}" , code);
		String userInfo = service.getUserInfo(code);
		
		return "index";
	}
	
	//로그인 화면
	@GetMapping("/default_login")
	public String loginForm(Model model) {
		String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+restApiKey+"&redirect_uri="+redirectUrl;
		model.addAttribute("location", location);
		return "user/login";
	}
	
	@GetMapping("/signUp")
	public String signUpPage() {
		return "user/signUp";
	}
	
	// 회원가입
	@PostMapping("/signUp")
	@ResponseBody
	public Map<String, Object> signUp(
			@Validated@RequestBody UserVO userVO,
			Errors errors){
		Map<String, Object> resultMap = new HashMap<>();
		if(errors.hasErrors()) {
			resultMap.put("error", errors.getFieldError().getDefaultMessage());
			log.info("error : {}", errors);
			log.info("필드 error : {}", errors.getFieldError());
			log.info("필드 error 디폴트메세지: {}", errors.getFieldError().getDefaultMessage());
			return resultMap;
		}
		int cnt = service.insertUser(userVO);
		if(cnt>0) {
			resultMap.put("ok", "회원가입완료");
		}else {
			resultMap.put("no", "회원가입실패 다시시도");
		}
		return resultMap;
	
	}
	// 아이디 중복체크
	@GetMapping("/findId/{userId}")
	@ResponseBody
	public Map<String, String> findId(@PathVariable String userId) {
		Map<String, String> resultMap = new HashMap<>();
		UserVO userVO =  service.findId(userId);
		if(userVO==null) {
			resultMap.put("ok", userId + " 사용가능한 아이디입니다");
		}else {
			resultMap.put("no", userId + " 이미 있는 아이디입니다.");
		}
		return resultMap;
	}
	
	//이메일 인증코드 보내기
	@PostMapping("/emailCode")
	@ResponseBody
	public Map<String, Object> sendMail(@RequestParam("email") String email){
		Map<String, Object> resultMap = new HashMap<>();
		String mailCode = service.sendMail(email);
		resultMap.put("mailCode", mailCode);
		return resultMap;
	}
	
	
}
