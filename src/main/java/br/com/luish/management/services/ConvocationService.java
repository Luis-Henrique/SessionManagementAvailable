package br.com.luish.management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luish.management.models.Convocation;
import br.com.luish.management.repositories.ConvocationRepository;
import br.com.luish.management.services.exceptions.InternalErrorException;
import br.com.luish.management.services.exceptions.ObjectNotFoundException;
import br.com.luish.management.utils.DateUtil;

@Service
public class ConvocationService {

	@Autowired
	ConvocationRepository repository;

	public Convocation insert(Convocation obj) {

		obj.setDate(DateUtil.getDateNow());

		if (!isDuplicate(obj)) {
			DateUtil.onlyDate(obj.getDate());
			return repository.save(obj);
		}

		return null;

	}

	private boolean isDuplicate(Convocation obj) {

		List<Convocation> topics = repository.findByTitle(obj.getTitle());

		for (Convocation aux : topics) {

			if (aux.getTitle().equals(null))
				return false;

			if (aux.getTitle().equals(obj.getTitle())) {
				if ((DateUtil.equalsDate(obj.getDate(), aux.getDate()))) {
					return true;
				}
			}

		}

		return false;

	}
	
	public Convocation find(Integer id) {

		Optional<Convocation> obj;

		if (id == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Convocation.class.getName());
		}

		try {

			obj = repository.findById(id);

			if ( obj == null) {
				throw new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Convocation.class.getName());
			}

		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Convocation.class.getName());
		} catch (Exception e) {
			throw new InternalErrorException("Internal error! Id: " + id + ", Tipo: " + Convocation.class.getName());
		}

		return obj.orElse(null);

	}


}
