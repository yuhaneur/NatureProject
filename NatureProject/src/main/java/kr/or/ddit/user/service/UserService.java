package kr.or.ddit.user.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import kr.or.ddit.vo.UserVO;

public interface UserService {
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return
	 */
	public UserVO findId(String userId);
	
	/**
	 * 회원가입
	 * @param userVO
	 * @return
	 */
	public int insertUser(UserVO userVO);
	
	/**
	 * 회원정보수정
	 * @param userVO
	 * @return
	 */
	public int updateUser(UserVO userVO);
	
	/**
	 * 회원탈퇴
	 * @param userVO
	 * @return
	 */
	public int deleteUser(UserVO userVO);
	
	/**
	 * 이메일 인증코드 보내기
	 * @param email
	 * @return
	 */
	public String sendMail(String email);
	
	/**
	 * 카카오 코드받은걸로 accessToken 요청해서 받아오기
	 * @param code
	 * @return
	 */
	public ResponseEntity<String> getAccessToken(String code) throws IOException;
	
	/**
	 * 토큰으로 유저정보 받아오기
	 * @param accessToken
	 * @return
	 */
	public String getUserInfo(String accessToken) throws IOException;
}
