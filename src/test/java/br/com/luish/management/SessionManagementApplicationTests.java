package br.com.luish.management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.luish.management.services.ConvocationService;

@SpringBootTest
class SessionManagementApplicationTests {

	@Autowired
	ConvocationService service;

	@Test
	void contextLoads() {
	}

}
