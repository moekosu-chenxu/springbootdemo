package com.moekosu.testspringboot;

import com.moekosu.constant.User;
import com.moekosu.constant.UserLogin;
import com.moekosu.dao.UserLoginMsgMapper;
import com.moekosu.dao.UserMapper;
import com.moekosu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 测试类 FIXME 损坏 Mapper无法注入
 */
// RunWith是JUnit的注解，SpringRunner.class是Spring-test提供的测试单元类
@RunWith(SpringRunner.class)
// Spring boot 测试引导入口, classes 主程序入口
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringbootApplicationTests {

//	@Autowired
//	private TestRestTemplate testRestTemplate;

	@Autowired
	private UserLoginMsgMapper userLoginMsgMapper;

//	@Autowired
//	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetUserList()
	{
		List<UserLogin> list = userLoginMsgMapper.getUserLoginMsgList();
//		List<UserLogin> list = userService.getUserLoginMsgList();

		for(UserLogin u: list)
		{
			System.out.println(u.toString());
		}
	}

}
