package br.com.luish.management.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luish.management.models.Session;
import br.com.luish.management.models.Vote;
import br.com.luish.management.models.enums.SessionResult;
import br.com.luish.management.models.enums.SessionStatus;
import br.com.luish.management.models.enums.VoteValue;
import br.com.luish.management.repositories.VoteRepository;
import br.com.luish.management.services.exceptions.OperationNotAllowedException;
import br.com.luish.management.services.exceptions.ConvocationFinishedException;
import br.com.luish.management.utils.DateUtil;

@Service
public class VoteService {
	
	@Autowired
	VoteRepository repository;
	
	@Autowired
	SessionService sessionService;
	
	public Vote insert(Vote obj, Integer id) {
		
		Session session = sessionService.find(id);
		
		obj.setSession(session);
		
		if(obj.getSession().getStatus() == SessionStatus.ENCERRADA.getCode())
			throw new OperationNotAllowedException("Sessão encerrada! Id: " + obj.getSession().getId() + ", Tipo: " + Session.class.getName());
		
		
		if(obj.getSession().getStatus() == SessionStatus.ABERTA.getCode() && (!DateUtil.isValidTime(obj.getSession().getTime())) ) {
			session.setStatus(SessionStatus.ENCERRADA.getCode());
			session.setVotes(findAllVotes(obj.getSession().getId()));
			session.setResult(createResultSession(session.getVotes()));
			sessionService.update(session);
			throw new ConvocationFinishedException("Sessão já encerrada! Id: " + obj.getSession().getId() + ", Tipo: " + Session.class.getName());
		}
		
		for (Vote aux : session.getVotes()) {
			if(aux.getCpf().equals(obj.getCpf()))
				throw new OperationNotAllowedException("Voto já realizado!"  + Session.class.getName());
		}
		
		session.setTotalVotes(session.getTotalVotes() + 1);
		sessionService.update(session);
		
		return repository.save(obj);
		
	}
	
	public List<Vote> findAllVotes(Integer id) {
		
		List<Vote> aux = new ArrayList<>();
		List<Vote> votes = repository.findAll();
		
		for(Vote vote : votes) {
			if(vote.getSession().getId() == id)
				aux.add(vote);
		}
		
		return aux;
		
	}
	
	private Integer createResultSession(List<Vote> votes) {
		
		int yes = 0;
		int not = 0;
		
		if(votes.size() == 0 || votes == null)
			return SessionResult.SEMVOTOS.getCode();
		
		for(Vote aux : votes) {
			if(aux.getVoteValue() == VoteValue.SIM.getCode()) {
				yes += 1;
				continue;
			}
				

			not += 1;
		}
		
		if(yes == not)
			return SessionResult.EMPATADO.getCode();
		
		
		if(yes > not)
			return SessionResult.APROVADO.getCode();
		
		
			return SessionResult.REPROVADO.getCode();
		
	}

	public void operationNotPermitted() {
		throw new OperationNotAllowedException("CPF inválido!"  + Session.class.getName());
		
	}
	
	
	


}
