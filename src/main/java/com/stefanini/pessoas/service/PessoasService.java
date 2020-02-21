package com.stefanini.pessoas.service;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.pessoas.model.Pessoa;
import com.stefanini.pessoas.repository.Pessoas;
import com.stefanini.pessoas.util.StringUtil;

@RestController
@RequestMapping("/pessoas")
public class PessoasService {
	
	@Autowired
	private Pessoas pessoas;
	
	//@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa) {
		if(StringUtil.isCPF(pessoa.getCpf())) {
			pessoa = pessoas.save(pessoa);
			return ResponseEntity.ok(pessoa);
		}else {
			return ResponseEntity.notFound().build();
		}
		
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
	
	
	
	@GetMapping("/nextId")
	public ResponseEntity<Pessoa> nextId() {
		Pessoa pessoa = new Pessoa();
		Long contador = new Long(0);
		List<Pessoa> findAll = pessoas.findAll();
		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {			
			contador++;			
			iterator.next();
		}
		pessoa.setId(contador+1);
		if (contador == 0) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	
	
	@PutMapping("/{id}")	
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
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











