package br.com.luish.management.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.luish.management.models.Convocation;
import br.com.luish.management.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ConvocationServiceTests {
	
	@Autowired
	ConvocationService service;
	
	// teste de integridade do objeto especificado
		@Test
		public void findById() throws ObjectNotFoundException {

			Convocation convocation = service.find(5);
			assertNotNull(convocation);
			assertEquals(convocation.getTitle(), "Teste1");

		} 

}
