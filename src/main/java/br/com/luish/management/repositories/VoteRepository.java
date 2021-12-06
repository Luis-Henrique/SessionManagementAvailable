package br.com.luish.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luish.management.models.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer>{

}
