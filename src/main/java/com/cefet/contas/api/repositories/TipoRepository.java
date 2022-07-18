package com.cefet.contas.api.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.contas.api.entities.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{
	
	Optional<Tipo> findByDescricao(String descricao); 

}
