package test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * This annotation will start your IoC container so that your autowiring will work correctly
 * 
 * This test is given to us by default from start.spring.io
 * and it is used as a "sanity test" so that you don't spend hours trying to fix a broken test...only to find out
 * spring was never actually running to begin with
 */
@SpringBootTest
class TestSpring {

	@Test
	void contextLoads() {
	}

}
