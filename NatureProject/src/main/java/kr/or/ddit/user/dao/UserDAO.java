package kr.or.ddit.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.UserVO;

@Mapper
public interface UserDAO {
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return
	 */
	public UserVO findId(@Param("userId") String userId);
	
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
}
