package br.com.luish.management.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luish.management.models.Vote;
import br.com.luish.management.services.VoteService;
import br.com.luish.management.utils.CPFUtil;

@RestController
@RequestMapping(value = "/vote")
public class VoteResource {
	
	@Autowired 
	VoteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Vote> insert(@PathVariable Integer id, @RequestBody Vote obj) {
		
		if(!CPFUtil.isValid(obj.getCpf()))
			service.operationNotPermitted();

		obj = service.insert(obj, id);
		
		// retornar codigo 201 e uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
