package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails,Serializable{
	private String userCode; 
	@NotBlank
	private String userId;
	@NotBlank
	private String userPw;
	@NotBlank
	@Email(regexp = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String userEmail;
	@NotBlank
	private String userName;
	private String userCreate;
	@NotBlank
	private String userBir; 
	private String userStatus; 
	private String userRole;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        if ("admin".equalsIgnoreCase(this.userRole)) {
        	//singletonList 불변 리스트 추가 삭제 수정 작업 불가능
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
	}
	@Override
	public String getPassword() {
		return this.userPw;
	}
	@Override
	public String getUsername() {
		return userId != null ? userId : userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return this.userStatus.equalsIgnoreCase("N");
	}
}
