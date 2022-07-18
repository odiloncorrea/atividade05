package com.cefet.contas.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.contas.api.entities.Tipo;
import com.cefet.contas.api.repositories.TipoRepository;

@Service
public class TipoService {
	private static final Logger log = LoggerFactory.getLogger(TipoService.class);
	
	@Autowired
	private TipoRepository categoriaRepository;
	
	public Tipo save(Tipo categoria) {
		log.info("Persistindo categoria: {}", categoria);
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Tipo> findOne(Long id) {
		log.info("Buscando categoria por id: {}", id);
		return categoriaRepository.findById(id);
	}
	
	public List<Tipo> findAllList() {
		log.info("Listando as categorias");
		return categoriaRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo categoria: {}", id);
		categoriaRepository.deleteById(id);
	}
	
	public Optional<Tipo> findByDescricao(String descricao) {
		log.info("Buscando uma categoria para a descrição {}", descricao);
		return categoriaRepository.findByDescricao(descricao);
	}
}
