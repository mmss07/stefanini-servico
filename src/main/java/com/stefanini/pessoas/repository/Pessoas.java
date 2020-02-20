package com.stefanini.pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanini.pessoas.model.Pessoa;

public interface Pessoas extends JpaRepository<Pessoa, Long> {
	
	 public Pessoa findByCpf(String cpf);

}
