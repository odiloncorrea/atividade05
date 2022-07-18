package com.cefet.contas.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefet.contas.api.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	Optional<Conta> findByDescricao(String descricao);
}
