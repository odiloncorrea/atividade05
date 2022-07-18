package com.cefet.contas.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "tipo")
public class Tipo implements Serializable{

	private static final long serialVersionUID = 8221170720128173159L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	@NotEmpty(message = "Descrição é obrigatório.")
	@Length(min = 3, max = 200, message = "Descrição deve conter entre 3 e 200 caracteres.")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	
}
