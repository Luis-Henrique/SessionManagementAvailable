package br.com.luish.management.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luish.management.models.Session;
import br.com.luish.management.services.SessionService;

@RestController
@RequestMapping(value = "/session")
public class SessionResource {
	
	@Autowired 
	SessionService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	//public ResponseEntity<Session> insert(@PathVariable Integer id, @RequestBody Session obj) {
	public ResponseEntity<Session> insert(
			@PathVariable Integer id, 
			@RequestParam(value="time", defaultValue = "1") Integer time) {
	
		Session obj = new Session();
		obj.setTime(time);
		
		obj = service.insert(obj, id);
		
		// retornar codigo 201 e uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Session> find(@PathVariable Integer id) {
		
		Session obj =  service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Session>> findAll() {
		
		List<Session> list =  service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}

}
