package kr.or.ddit.user.service;

import java.io.IOException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.user.dao.UserDAO;
import kr.or.ddit.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

	private final UserDAO dao;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public UserServiceImpl(UserDAO dao) {
		this.dao=dao;
	}
	
	@Value("#{appInfo['kakao.client.id']}")
	private String restApiKey;
	
	@Value("#{appInfo['kakao.redirect.url']}")
	private String redirectUrl;
	@Value("#{appInfo['kakao.client.secret']}")
	private String clientSecret;
	
	@Override
	public UserVO findId(String userId) {
		return dao.findId(userId);
	}

	@Override
	public int insertUser(UserVO userVO) {
		int cnt  = 0;
		if(dao.findId(userVO.getUserId())==null) {
			userVO.setUserPw(passwordEncoder.encode(userVO.getUserPw()));
			cnt = dao.insertUser(userVO);
		}
		return cnt;
	}

	@Override
	public int updateUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String sendMail(String email) {
	      String mailCode = RandomCode(8);
	      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	      String fromMail = "yuhaneur@naver.com";
	      simpleMailMessage.setFrom(fromMail);
	      simpleMailMessage.setTo(email);
	      String subject = "메일인증코드입니다";
	      simpleMailMessage.setSubject(subject);
	      simpleMailMessage.setText(mailCode);
	      
	      mailSender.send(simpleMailMessage);
	      return mailCode;
	}
	public String RandomCode(int length) {
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder mailCode = new StringBuilder(length);
		
		for(int i=0; i<length;i++) {
			int index = secureRandom.nextInt(CHARACTERS.length());
			mailCode.append(CHARACTERS.charAt(index));
		}
		
		return mailCode.toString();
	}
	@Override
	public ResponseEntity<String> getAccessToken(String code) throws IOException {
		//카카오의 OAuth2 API는 URL 인코딩(키,값)된 폼 데이터 형식을 요구
	    String url = "https://kauth.kakao.com/oauth/token";
	    
	    // 요청 헤더 설정
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
	    // 요청 본문 설정
//	    String body = "grant_type=authorization_code" +
//	    		"&client_id=" + restApiKey +
//	    		"&client_secret=" + clientSecret + 
//	    		"&redirect_uri=" + redirectUrl +
//	    		"&code=" + code;
	    StringBuilder sb = new StringBuilder();
	    sb.append("grant_type=authorization_code&client_id=");
	    sb.append(restApiKey);
	    sb.append("&client_secret=");
	    sb.append(clientSecret);
	    sb.append("&redirect_uri=");
	    sb.append(redirectUrl);
	    sb.append("&code=");
	    sb.append(code);

	    // 요청 엔티티 생성
	    HttpEntity<String> entity = new HttpEntity<>(sb.toString(), headers);

	    // RestTemplate을 이용한 POST 요청
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		return response;
	}

	@Override
	public String getUserInfo(String code) throws IOException {
		ResponseEntity<String> accessToken = getAccessToken(code);
		log.info("accessToken : {}" , accessToken);
		ObjectMapper objectMapper = new ObjectMapper();
		//JSON 문자열을 트리 구조로 파싱해 JsonNode 객체를 생성
		JsonNode jsonNode = objectMapper.readTree(accessToken.getBody());
		log.info("토큰 json 변환 : {}" , jsonNode);
		String url = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정 (Authorization에 Bearer 토큰 추가)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jsonNode.get("access_token").asText());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 카카오 API로 사용자 정보 요청
        ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            String.class
        );
        
        String result = response.toString();
        System.out.println("result 서비스 : " + result );
        // 카카오 로그인 사용자 정보 시큐리티에 넘기기(프린시퍼)
        String responseBody = response.getBody();
        JsonNode jsonNode2 = objectMapper.readTree(responseBody);
        // JsonNode.path는 필드가 없을경우 null대신 기본값을 반환함 반대로 get은 null값으로 반환함
        String name = jsonNode2.path("properties").path("nickname").asText();
        String email = jsonNode2.path("kakao_account").path("email").asText();
        System.out.println("nickname  : " + name );
        System.out.println("email  : " + email );
        UserVO userVO = new UserVO();
        userVO.setUserId(name);
        userVO.setUserEmail(email);
        
        UserDetails userDetails = userVO;
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
		return result.toString();
	}

}
