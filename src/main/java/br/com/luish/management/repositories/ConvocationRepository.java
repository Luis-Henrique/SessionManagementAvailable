package br.com.luish.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luish.management.models.Convocation;

@Repository
public interface ConvocationRepository extends JpaRepository<Convocation, Integer>{

	List<Convocation> findByTitle(String title);
	
}
