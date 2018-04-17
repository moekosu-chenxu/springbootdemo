package com.moekosu.testspringboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试类 FIXME 损坏 Mapper无法注入
 */
// RunWith是JUnit的注解，SpringRunner.class是Spring-test提供的测试单元类
@RunWith(SpringRunner.class)
// Spring boot 测试引导入口, classes 主程序入口
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringbootApplicationTests {


	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetUserList()
	{
	}

}
