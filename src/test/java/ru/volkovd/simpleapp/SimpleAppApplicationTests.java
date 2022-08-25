package ru.volkovd.simpleapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SimpleAppApplicationTests {
	@Test
	void contextLoads() {
		log.info("This is test");
	}
}
