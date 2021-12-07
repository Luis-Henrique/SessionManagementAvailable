package br.com.luish.management.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.luish.management.models.Vote;
import br.com.luish.management.models.enums.SessionResult;
import br.com.luish.management.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class VoteServiceTests {

	@Autowired
	VoteService service;
	
	// teste de sessao aprovada
	@Test
	public void sessionApproved() throws ObjectNotFoundException {
		
		List<Vote> votes = service.findAllVotes(5);
		
		assertEquals(service.createResultSession(votes), SessionResult.APROVADO.getCode());
		
	}
}
