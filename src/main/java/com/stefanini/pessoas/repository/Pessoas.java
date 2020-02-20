package com.stefanini.pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stefanini.pessoas.model.Pessoa;

public interface Pessoas extends JpaRepository<Pessoa, Long> {
	
	 public Pessoa findByCpf(String cpf);
	 
	 @Query("SELECT MAX(id) FROM Pessoa")
	 public Long nextId();

}
