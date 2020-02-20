package com.stefanini.pessoas.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.pessoas.model.Pessoa;
import com.stefanini.pessoas.repository.Pessoas;

@RestController
@RequestMapping("/pessoas")
public class PessoasService {
	
	@Autowired
	private Pessoas pessoas;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa) {
		return pessoas.save(pessoa);
	}
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoas.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id) {
		Pessoa pessoa = pessoas.findOne(id);
		
		if (pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Pessoa> findByCpf(@PathVariable String cpf) {
		Pessoa pessoa = pessoas.findByCpf(cpf);
		
		if (pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	
	
	@PutMapping("/{id}")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Pessoa pessoa) {
		Pessoa existente = pessoas.findOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(pessoa, existente, "id");
		
		existente = pessoas.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Pessoa pessoa = pessoas.findOne(id);
		
		if (pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		pessoas.delete(pessoa);
		
		return ResponseEntity.noContent().build();
	}
}











