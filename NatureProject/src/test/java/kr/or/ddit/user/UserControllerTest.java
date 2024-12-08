package kr.or.ddit.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.or.ddit.user.controller.UserController;
import kr.or.ddit.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService service;
	
	@InjectMocks
	private UserController controller; 
	@Test
	void testFindId() {
		String userId= "asd";
		Map<String, String> result =  controller.findId(userId);
		Map<String, String> expected = new HashMap<>();
        expected.put("ok", userId + "사용가능한 아이디입니다");
        assertEquals(expected, result); 
	}
}
