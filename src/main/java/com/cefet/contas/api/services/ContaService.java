package com.cefet.contas.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.contas.api.entities.Conta;
import com.cefet.contas.api.repositories.ContaRepository;

@Service
public class ContaService {

	private static final Logger log = LoggerFactory.getLogger(ContaService.class);
	
	@Autowired
	private ContaRepository compraRepository;
	
	public Conta save(Conta compra) {
		log.info("Persistindo compra: {}", compra);
		return compraRepository.save(compra);
	}
	
	public Optional<Conta> findOne(Long id) {
		log.info("Buscando compra por id: {}", id);
		return compraRepository.findById(id);
	}
	
	public List<Conta> findAllList() {
		log.info("Listando as compras");
		return compraRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo compra: {}", id);
		compraRepository.deleteById(id);
	}	
	
	public Optional<Conta> findByDescricao(String descricao) {
		log.info("Buscando um Compra para a descrição {}", descricao);
		return compraRepository.findByDescricao(descricao);
	}
	
}
