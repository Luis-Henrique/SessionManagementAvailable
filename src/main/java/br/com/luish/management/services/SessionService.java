package br.com.luish.management.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luish.management.models.Convocation;
import br.com.luish.management.models.Session;
import br.com.luish.management.models.Vote;
import br.com.luish.management.models.enums.SessionResult;
import br.com.luish.management.models.enums.SessionStatus;
import br.com.luish.management.models.enums.VoteValue;
import br.com.luish.management.repositories.SessionRepository;
import br.com.luish.management.repositories.VoteRepository;
import br.com.luish.management.services.exceptions.ObjectNotFoundException;
import br.com.luish.management.utils.ConstantesUtil;
import br.com.luish.management.utils.DateUtil;

@Service
public class SessionService {

	@Autowired
	SessionRepository repository;

	@Autowired
	ConvocationService convocationService;

	@Autowired
	VoteRepository voteRepository;

	public Session find(Integer id) {

		Optional<Session> obj;

		if (id == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Session.class.getName());
		}

		try {

			obj = repository.findById(id);

			if (obj == null) {
				throw new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Session.class.getName());
			}

		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Session.class.getName());
		}

		try {

			if (obj.get().getStatus() == SessionStatus.ABERTA.getCode()
					&& (!DateUtil.isValidTime(obj.get().getTime()))) {
				finalizeSession(obj.get());
			}
		} catch (Exception e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Session.class.getName());
		}

		return obj.orElse(null);

	}

	public List<Session> findAll() {

		List<Session> sessions = repository.findAll();

		for (Session session : sessions) {

			if (session.getStatus() == SessionStatus.ABERTA.getCode() && (!DateUtil.isValidTime(session.getTime()))) {
				finalizeSession(session);
			}

		}

		return repository.findAll();

	}

	public Session insert(Session obj, Integer id) {

		Convocation convocation = convocationService.find(id);
		
		if(convocation == null)
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Session.class.getName());

		obj.setTime(System.currentTimeMillis() + ((obj.getTime()) * ConstantesUtil.ONE_MINUTE_IN_MILLIS));
		obj.setTopic(convocation);
		obj.setResult(SessionResult.ANDAMENTO.getCode());
		obj.setTotalVotes(0);
		obj.setStatus(SessionStatus.ABERTA.getCode());

		return repository.save(obj);

	}

	public void update(Session obj) {

		repository.save(obj);

	}

	private void finalizeSession(Session obj) {
		obj.setStatus(SessionStatus.ENCERRADA.getCode());
		obj.setVotes(findAllVotes(obj.getId()));
		obj.setResult(createResultSession(obj.getVotes()));
		update(obj);
	}

	public List<Vote> findAllVotes(Integer id) {

		List<Vote> aux = new ArrayList<>();
		List<Vote> votes = voteRepository.findAll();

		for (Vote vote : votes) {
			if (vote.getSession().getId() == id)
				aux.add(vote);
		}

		return aux;

	}

	private Integer createResultSession(List<Vote> votes) {

		int yes = 0;
		int not = 0;

		if (votes.size() == 0 || votes == null)
			return SessionResult.SEMVOTOS.getCode();

		for (Vote aux : votes) {
			if (aux.getVoteValue() == VoteValue.SIM.getCode()) {
				yes += 1;
				continue;
			}

			not += 1;
		}

		if (yes == not)
			return SessionResult.EMPATADO.getCode();

		if (yes > not)
			return SessionResult.APROVADO.getCode();

		return SessionResult.REPROVADO.getCode();

	}

}
