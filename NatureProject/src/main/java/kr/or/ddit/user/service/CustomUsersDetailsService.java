package kr.or.ddit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.UserDAO;
import kr.or.ddit.vo.UserVO;

@Service
public class CustomUsersDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDAO dao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO= dao.findId(username);
		if(userVO ==null) {
			throw new UsernameNotFoundException(username+ "은 존재하지 않는 사용자입니다");
		}
		return userVO;
	}



}
