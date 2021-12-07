package br.com.luish.management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.luish.management.models.Session;
import br.com.luish.management.models.enums.SessionStatus;
import br.com.luish.management.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class SessionServiceTests {

	@Autowired
	SessionService service;

	// teste de sessao especificada está encerrada e computou votos
	@Test
	public void sessionFinished() throws ObjectNotFoundException {

		Session session = service.find(5);
		assertEquals(session.getStatus(), SessionStatus.ENCERRADA.getCode());
		assertNotEquals(session.getTotalVotes(), 0);

	}

}
